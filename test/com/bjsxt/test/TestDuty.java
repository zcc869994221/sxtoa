package com.bjsxt.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.bjsxt.bean.Duty;
import com.bjsxt.mapper.DutyMapper;
import com.bjsxt.service.DutyService;
import com.bjsxt.service.impl.DutyServiceImpl;
import com.bjsxt.util.DateChange;
import com.bjsxt.util.MyBatisUtil;

public class TestDuty {
	@Test
	public void testFind() {
		SqlSession session = MyBatisUtil.getSqlsession();
		DutyMapper mapper = session.getMapper(DutyMapper.class);
		Date now = new Date();//yyyyMMdd hhmmss
		java.sql.Date today = new java.sql.Date(now.getTime());
//		System.out.println(today);	
		List<Duty> i = mapper.find("zs");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = null;
//		java.sql.Date resultDate = null;
//		for (Duty duty : i) {
//			date = duty.getDtDate();
//			System.out.println(date);
//			String strDate = sdf.format(date);
//			try {
//				Date newDate = sdf.parse(strDate);
//				resultDate = new java.sql.Date(newDate.getTime());
//				if(resultDate.getTime()==today.getTime()) {
//					System.out.println("2");
//				}else {
//					System.out.println("1");
//				}
////				System.out.println(resultDate);
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//		}
		
		
		for (Duty duty : i) {
			duty.setDtDate(DateChange.getDate(duty.getDtDate()));
			System.out.println(duty);
		}
		
		
		
//		Duty duty = new Duty();
//		duty.setEmpid("zs");
//		duty.setDtDate(today);
//		System.out.println();
//		DateFormat df = new SimpleDateFormat("hh:mm:ss");
//		String signinTime = df.format(now);
//		duty.setSigninTime(signinTime);
//		int i =  mapper.save(duty);
//		System.out.println(i);
	}
	@Test
	public void testSigninTime() {
		DutyService ds = new DutyServiceImpl();
		int i = ds.signin("zs");
		System.out.println(i);
	}
	
	@Test
	public void signout1() {
		String empid="2";
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
							//判断是否已经签退
								if(duty.getSignoutTime()!=null || duty.getSignoutTime()!="") {//已经签退
									System.out.println("yes");
									
								}
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
		System.out.println(i);
		
	}
	
	@Test
	public void find() {
		SqlSession session = MyBatisUtil.getSqlsession();
		DutyMapper mapper = session.getMapper(DutyMapper.class);
		String empid = "zs";
		int deptno = 0;
		java.sql.Date dtDate = null;
		List<Duty> list = mapper.find1(empid, deptno, dtDate);
//		System.out.println(list);
		for (Duty duty : list) {
			System.out.println(duty);
		}
		
	}
}
