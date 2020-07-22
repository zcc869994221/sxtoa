package com.bjsxt.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.bjsxt.bean.Auditing;
import com.bjsxt.bean.Expense;
import com.bjsxt.bean.Payment;
import com.bjsxt.mapper.AuditingMapper;
import com.bjsxt.mapper.ExpenseItemMapper;
import com.bjsxt.mapper.ExpenseMapper;
import com.bjsxt.mapper.PaymentMapper;
import com.bjsxt.util.Constants;
import com.bjsxt.util.DateChange;
import com.bjsxt.util.MyBatisUtil;

public class TestExpense {
	@Test
	public void testNextVal() {
		SqlSession session = MyBatisUtil.getSqlsession();
		ExpenseMapper mapper = session.getMapper(ExpenseMapper.class);
		ExpenseItemMapper itemMapper = session.getMapper(ExpenseItemMapper.class);
		// 获取最大编号(序列的下一个值)
//		int expid = mapper.nextVal();
//		System.out.println(expid);
	}

	@Test
	public void testFind() {
		SqlSession session = MyBatisUtil.getSqlsession();
		ExpenseMapper mapper = session.getMapper(ExpenseMapper.class);
		List<Expense> list = mapper.findByAuditorId("admin");
		for (Expense expense : list) {
			expense.setExpTime(DateChange.getDate(expense.getExpTime()));
			System.out.println(expense);
		}
	}

	@Test
	public void testFindById() {
		SqlSession session = MyBatisUtil.getSqlsession();
		ExpenseMapper mapper = session.getMapper(ExpenseMapper.class);
		Expense exp = mapper.findById(5);
		System.out.println(exp);
	}

	@Test
	public void testsave1() {
		SqlSession session = MyBatisUtil.getSqlsession();
		AuditingMapper mapper = session.getMapper(AuditingMapper.class);
		Auditing auditing = new Auditing();
		auditing.setExpid(5);
		mapper.save(auditing);

	}
	@Test
	public void testupd() {
		SqlSession session = MyBatisUtil.getSqlsession();
		ExpenseMapper mapper = session.getMapper(ExpenseMapper.class);
		// 修改报销单状态
		Expense exp = new Expense();
		exp.setExpid(41);
		exp.setLastResult("通过");
		exp.setStatus(Constants.EXPENSE_STATUS_PASS);
		exp.setNextAuditorId("wdnmd");//没有下一个处理人，审核结束
		mapper.update(exp);
		session.commit();

	}
	
	@Test
	public void testsel() {
		SqlSession session = MyBatisUtil.getSqlsession();
		PaymentMapper mapper = session.getMapper(PaymentMapper.class);

		
		List<Payment> list = mapper.sel(null, 0);
		for (Payment payment : list) {
			payment.setPayTime(DateChange.getDate2(payment.getPayTime()));
		}
		for (Payment payment : list) {
			
			System.out.println(payment);
		}
	}
}
