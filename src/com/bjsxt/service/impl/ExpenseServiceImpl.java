package com.bjsxt.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bjsxt.bean.Auditing;
import com.bjsxt.bean.Expense;
import com.bjsxt.bean.ExpenseItem;
import com.bjsxt.bean.Payment;
import com.bjsxt.mapper.AuditingMapper;
import com.bjsxt.mapper.ExpenseItemMapper;
import com.bjsxt.mapper.ExpenseMapper;
import com.bjsxt.mapper.PaymentMapper;
import com.bjsxt.service.ExpenseService;
import com.bjsxt.util.Constants;
import com.bjsxt.util.DateChange;
import com.bjsxt.util.MyBatisUtil;

public class ExpenseServiceImpl implements ExpenseService {
	/**
	 * 如何知道报销单的编号，给了报销单明细？
	 */

	/**
	 * 如何防止出现报销单提交，而报销明细异常? 统一提交或者回滚
	 */

	/**
	 * 用ThreadLocal保证线程获取session的唯一性
	 * 
	 * 首先，ThreadLocal 不是用来解决共享对象的多线程访问问题的， 一般情况下，通过ThreadLocal.set()
	 * 到线程中的对象是该线程自己使用的对象 ，其他线程是不需要访问的，也访问不到的。各个线程中访问的是不同的对象。
	 */
	@Override
	public void add(Expense expense) {
		SqlSession session = MyBatisUtil.getSqlsession();
		ExpenseMapper mapper = session.getMapper(ExpenseMapper.class);
		ExpenseItemMapper itemMapper = session.getMapper(ExpenseItemMapper.class);
		// 获取最大编号(序列的下一个值)
		String sexpid = mapper.nextVal();
		int expid = 0;
		if(sexpid==null) {
			// 表中没有数据
			expid = 1;
		}else {
		expid = Integer.parseInt(sexpid);
		}
		try {
			// 添加报销单信息
			expense.setExpid(expid);
			mapper.save(expense);

			// 添加多条报销单明细的信息
			List<ExpenseItem> list = expense.getItemList();
			for (int i = 0; i < list.size(); i++) {
				ExpenseItem item = list.get(i);
				item.setExpid(expid);
				itemMapper.save(item);
			}
			// 要提交就都提交
			session.commit();
		} catch (Exception e) {
			// 一个出错全部回滚
			session.rollback();
			e.printStackTrace();
			throw new RuntimeException(e.toString());
		}

		MyBatisUtil.closeSqlSession();
	}

	@Override
	public List<Expense> getToAudit(String auditorid) {
		SqlSession session = MyBatisUtil.getSqlsession();
		ExpenseMapper mapper = session.getMapper(ExpenseMapper.class);
		List<Expense> list = mapper.findByAuditorId(auditorid);
		for (Expense expense : list) {
			expense.setExpTime(DateChange.getDate(expense.getExpTime()));
		}
		return list;
	}

	/**
	 * 增加一个审核单 修改报销单中的审核人数据
	 */
	@Override
	public void audit(Auditing auditing) {
		SqlSession session = MyBatisUtil.getSqlsession();
		ExpenseMapper expenseMapper = session.getMapper(ExpenseMapper.class);
		AuditingMapper auditingMapper = session.getMapper(AuditingMapper.class);
		PaymentMapper paymentMapper = session.getMapper(PaymentMapper.class);
		auditing.setAuditTime(new java.sql.Timestamp(auditing.getAuditTime().getTime()));// 设置日期格式
		try {
			String result = auditing.getResult();

			if (result.equals("通过")) {// 通过

				if (auditing.getAuditor().getPosid() == 6) {// 是财务
					try {
						// 添加支出记录
						Expense expe = expenseMapper.findById(auditing.getExpid());
						Payment pm = new Payment();
						pm.setAmount(expe.getTotalAmount());//报销金额
						pm.setExpEmpId(expe.getEmpid());//报销人编号
						pm.setExpid(auditing.getExpid());
						pm.setPayEmpId(auditing.getAuditor().getEmpid());//审核人  支出人
						pm.setPayTime(new java.sql.Timestamp(new Date().getTime()));
						//pm.setPayTime(new Date());
						paymentMapper.save(pm);
						
						// 修改报销单状态
						Expense exp = new Expense();
						exp.setExpid(auditing.getExpid());
						exp.setLastResult(auditing.getResult());
						exp.setStatus(Constants.EXPENSE_STATUS_CASHED);
						exp.setNextAuditorId(null);//没有下一个处理人，审核结束
						expenseMapper.update(exp);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					session.commit();
				} else {// 不是财务

					// 获取报销单金额
					int expid = auditing.getExpid();
					Expense expe = expenseMapper.findById(expid);
					if (expe.getTotalAmount() > 2500) {// 金额大于2500

						if (auditing.getAuditor().getEmpid().equals(Constants.POSITION_CEOID)) {// 当前审核人是总裁
							// 添加审核记录
							auditingMapper.save(auditing);
							// 修改报销单状态 下一个处理人：财务
							Expense exp = new Expense();
							exp.setExpid(auditing.getExpid());
							exp.setStatus(Constants.EXPENSE_STATUS_PASS);
							exp.setLastResult(auditing.getResult());
							exp.setNextAuditorId( Constants.POSITION_CFOID);// 财务
							expenseMapper.update(exp);
							// 修改报销单状态
						} else {
							// 添加审核记录
							auditingMapper.save(auditing);
							// 修改报销单状态 下一个处理人：总裁
							Expense exp = new Expense();
							exp.setExpid(auditing.getExpid());
							exp.setStatus(Constants.EXPENSE_STATUS_AUDITING);
							exp.setLastResult(auditing.getResult());
							exp.setNextAuditorId(Constants.POSITION_CEOID);// 总裁
//							exp.setNextAuditorId(auditing.getAuditor().getMgr().getEmpid());//总裁
							expenseMapper.update(exp);
						}
					} else {// 小于等于2500
							// 添加审核记录
						auditingMapper.save(auditing);
						// 修改报销单状态
						Expense exp = new Expense();
						exp.setExpid(auditing.getExpid());
						exp.setStatus(Constants.EXPENSE_STATUS_PASS);
						exp.setLastResult(auditing.getResult());
						exp.setNextAuditorId(Constants.POSITION_CFOID);// 财务
						expenseMapper.update(exp);
					}
				}
			} else {// 拒绝或者打回
					// 添加审核记录
				auditingMapper.save(auditing);
				// 修改报销单状态
				Expense exp = new Expense();
				exp.setExpid(auditing.getExpid());

				if (auditing.getResult().equals("打回")) {// 打回
					exp.setStatus(Constants.EXPENSE_STATUS_BACK);
				} else {// 拒绝
					exp.setStatus(Constants.EXPENSE_STATUS_REJECT);
				}
				exp.setLastResult(auditing.getResult());
				exp.setNextAuditorId(null);// 拒绝 打回 没有下个审核人
				expenseMapper.update(exp);
			}

			session.commit();
		} catch (Exception e) {
			// 一个出错全部回滚
			session.rollback();
			e.printStackTrace();
			throw new RuntimeException(e.toString());
		}

		MyBatisUtil.closeSqlSession();
	}

	@Override
	public List<Payment> findPayment() {
		SqlSession session = MyBatisUtil.getSqlsession();
		PaymentMapper mapper = session.getMapper(PaymentMapper.class);		
		return mapper.sel(null, 0);
	}

}
