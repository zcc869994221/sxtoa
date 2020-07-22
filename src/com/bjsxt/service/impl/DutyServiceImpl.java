package com.bjsxt.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bjsxt.bean.Duty;
import com.bjsxt.mapper.DutyMapper;
import com.bjsxt.service.DutyService;
import com.bjsxt.util.MyBatisUtil;

public class DutyServiceImpl implements DutyService {
	/**
	 * 签到
	 */
	@SuppressWarnings("deprecation")
	@Override
	public int signin(String empid) {
		SqlSession session = MyBatisUtil.getSqlsession();
		DutyMapper mapper = session.getMapper(DutyMapper.class);
		//判断用户是否已经签到
		Date now = new Date();//yyyyMMdd hhmmss
		java.sql.Date today = new java.sql.Date(now.getTime());
		System.out.println(today);
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		java.sql.Date sdate = null;
		String strDate = "";
		boolean b = false;
		List<Duty> list = mapper.find(empid);
		//遍历集合
		for (Duty duty : list) {
			date = duty.getDtDate();
			strDate = sdf.format(date);			
			try {
				Date newDate = sdf.parse(strDate);
				sdate=new java.sql.Date(newDate.getTime());
				if(sdate.getDate()==today.getDate()) {
					b = true;
					break;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}			
		}		
		//判断
		if(b==true) {//如果已经签到，返回2
			return 2;
		}else {//完成签到
			Duty duty = new Duty();
			duty.setEmpid(empid);
			duty.setDtDate(today);
			//设置签到时间格式
			DateFormat df = new SimpleDateFormat("hh:mm:ss");
			String signinTime = df.format(now);
			duty.setSigninTime(signinTime);
			int j = mapper.save(duty);
			//commit
			if(j>0) {//签到成功
				session.commit();
				return j;
			}
			//rollback
			session.rollback();
			return j;
		}
		
	}
	
	/**
	 * 签退
	 */
	@Override
	public int signout(String empid) {
		SqlSession session = MyBatisUtil.getSqlsession();
		DutyMapper mapper = session.getMapper(DutyMapper.class);
		//得到员工的签到列表
		List<Duty> list = mapper.find(empid);
		//获取当前时间
		Date now = new Date();//yyyyMMdd hhmmss
		java.sql.Date today = new java.sql.Date(now.getTime());
		//设置时间格式
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date = null;
				java.sql.Date sdate = null;
				String strDate = "";
				//获取签退的duty对象的id
				int dtID = 0;
				//遍历集合
				for (Duty duty : list) {
					date = duty.getDtDate();
					strDate = sdf.format(date);			
					try {
						Date newDate = sdf.parse(strDate);
						sdate=new java.sql.Date(newDate.getTime());
						if(sdate.getDate()==today.getDate()) {
//							//判断是否已经签退
//								if(duty.getSignoutTime()!=null) {//已经签退
//									return 2;
//								}
							dtID = duty.getDtID();
							break;
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}			
				}
		//设置签退时间格式		
		DateFormat df = new SimpleDateFormat("hh:mm:ss");
		String signoutTime = df.format(now);		
		int i = mapper.signout(signoutTime, dtID);
		if(i>0) {
			session.commit();
			return i;
		}
		session.rollback();
		return i;
	}
	
	/**
	 * 根据条件查询考勤信息
	 */
	@Override
	public List<Duty> findDuty(String empid, int deptno, java.sql.Date dtDate) {
		SqlSession session = MyBatisUtil.getSqlsession();
		DutyMapper mapper = session.getMapper(DutyMapper.class);
		
		List<Duty> list = mapper.find1(empid,deptno,dtDate);
		//日期格式修改----废弃，用gson修改
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = null;
//		String str = null;
//		Date newDate = null;
//		java.sql.Date d = null;
		for (Duty duty : list) {
//			//修改日期
//			date = duty.getDtDate();
//			str = sdf.format(date);
//			try {
//				newDate = sdf.parse(str);
//				d = new java.sql.Date(newDate.getTime());
//				duty.setDtDate(d);
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
			//修改空的的签退内容
			if(duty.getSignoutTime()==null) {
				duty.setSignoutTime("");
			}
		}
		
		return list;
	}

}
