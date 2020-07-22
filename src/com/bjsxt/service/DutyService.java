package com.bjsxt.service;

import java.sql.Date;
import java.util.List;

import com.bjsxt.bean.Duty;

public interface DutyService {
	/**
	 * 
	 * @Title: signin   
	 * @Description:签到
	 * @param empid
	 * @return int      
	 * @throws
	 */
	int signin(String empid);
	/**
	 * 
	 * @Title: signout   
	 * @Description:签退
	 * @param empid
	 * @return int      
	 * @throws
	 */
	int signout(String empid);
	/**
	 * 
	 * @Title: findDuty   
	 * @Description:根据条件查询考勤信息
	 * @param empid
	 * @param deptno
	 * @param dtDate
	 * @return List<Duty>      
	 * @throws
	 */
	List<Duty> findDuty(String empid, int deptno, Date dtDate);
}
