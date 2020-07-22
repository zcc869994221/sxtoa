package com.bjsxt.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bjsxt.bean.Duty;

public interface DutyMapper {
	/**
	 * 
	 * @Title: find   
	 * @Description:判断是否已经签到
	 * @param empid
	 * @return List<Duty>      
	 * @throws
	 */
	List<Duty> find(String empid);
	/**
	 * 
	 * @Title: save   
	 * @Description:保存签到信息
	 * @param duty
	 * @return int      
	 * @throws
	 */
	int save(Duty duty);
	/**
	 * 
	 * @Title: signout   
	 * @Description: 更新签退信息
	 * @param signoutTime
	 * @param dtID
	 * @return int      
	 * @throws
	 */
	int signout(String signoutTime,int dtID);
	/**
	 * 
	 * @Title: find1  
	 * @Description:根据条件查找考勤信息
	 * @param empid
	 * @param deptno
	 * @param dtDate
	 * @return List<Duty>      
	 * @throws
	 */
	List<Duty> find1(@Param("empid") String empid,@Param("deptno") int deptno,@Param("dtDate") Date dtDate);
}
