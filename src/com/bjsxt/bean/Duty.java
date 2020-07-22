package com.bjsxt.bean;

import java.util.Date;

/*
 * 考勤
 */
public class Duty {
	private int dtID;//签到id
	private String empid;//员工id
	private Date dtDate;//签到日期
	private String signinTime;//签到时间
	private String signoutTime;//签退时间
	private Employee emp;//员工信息
	public int getDtID() {
		return dtID;
	}
	public void setDtID(int dtID) {
		this.dtID = dtID;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public Date getDtDate() {
		return dtDate;
	}
	public void setDtDate(Date dtDate) {
		this.dtDate = dtDate;
	}
	public String getSigninTime() {
		return signinTime;
	}
	public void setSigninTime(String signinTime) {
		this.signinTime = signinTime;
	}
	public String getSignoutTime() {
		return signoutTime;
	}
	public void setSignoutTime(String signoutTime) {
		this.signoutTime = signoutTime;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	public Duty() {
		super();
	}
	public Duty(int dtID, String empid, Date dtDate, String signinTime, String signoutTime) {
		super();
		this.dtID = dtID;
		this.empid = empid;
		this.dtDate = dtDate;
		this.signinTime = signinTime;
		this.signoutTime = signoutTime;
	}
	@Override
	public String toString() {
		return "Duty [dtID=" + dtID + ", empid=" + empid + ", dtDate=" + dtDate + ", signinTime=" + signinTime
				+ ", signoutTime=" + signoutTime + ", emp=" + emp + "]";
	}
	
	
}
