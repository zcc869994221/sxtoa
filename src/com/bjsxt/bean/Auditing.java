package com.bjsxt.bean;

import java.util.Date;

/*
 * 审核类
 */
public class Auditing {
	private int auditId;//审核编号
	private int expid;//报销单编号
	private String empid;//审核人编号
	
	private String result;//审核结果  通过，打回，拒绝
	private String auditDesc;//审核意见
	private Date auditTime;//yyyyMMddhhmmss
	private Employee auditor;//审核人
	private Expense exp;//报销单信息
	public int getAuditId() {
		return auditId;
	}
	public void setAuditId(int auditId) {
		this.auditId = auditId;
	}
	public int getExpid() {
		return expid;
	}
	public void setExpid(int expid) {
		this.expid = expid;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getAuditDesc() {
		return auditDesc;
	}
	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public Employee getAuditor() {
		return auditor;
	}
	public void setAuditor(Employee auditor) {
		this.auditor = auditor;
	}
	public Expense getExp() {
		return exp;
	}
	public void setExp(Expense exp) {
		this.exp = exp;
	}
	@Override
	public String toString() {
		return "Auditing [auditId=" + auditId + ", expid=" + expid + ", empid=" + empid + ", result=" + result
				+ ", auditDesc=" + auditDesc + ", auditTime=" + auditTime + ", auditor=" + auditor + ", exp=" + exp
				+ "]";
	}
	public Auditing(int auditId, int expid, String empid, String result, String auditDesc, Date auditTime,
			Employee auditor, Expense exp) {
		super();
		this.auditId = auditId;
		this.expid = expid;
		this.empid = empid;
		this.result = result;
		this.auditDesc = auditDesc;
		this.auditTime = auditTime;
		this.auditor = auditor;
		this.exp = exp;
	}
	public Auditing() {
		super();
	}
	public Auditing(int expid, String result, String auditDesc, Date auditTime, Employee auditor) {
		super();
		this.expid = expid;
		this.result = result;
		this.auditDesc = auditDesc;
		this.auditTime = auditTime;
		this.auditor = auditor;
	}
	
	
}
