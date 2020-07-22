package com.bjsxt.bean;

import java.util.Date;

/*
 * 支出类
 */
public class Payment {
	private int pmId;//支付编号	自增
	private String payEmpId;//支付人编号  财务
	private String expEmpId;//报销人编号
	private double amount;//支付金额
	private Date payTime;//支付时间
	private int expid;//报销单编号
	
	private Employee payEmp;//支付人
	private Employee expEmp;//报销人
	private Expense exp;//报销单
	public Payment(int pmId, String payEmpId, String expEmpId, double amount, Date payTime, int expid, Employee payEmp,
			Employee expEmp, Expense exp) {
		super();
		this.pmId = pmId;
		this.payEmpId = payEmpId;
		this.expEmpId = expEmpId;
		this.amount = amount;
		this.payTime = payTime;
		this.expid = expid;
		this.payEmp = payEmp;
		this.expEmp = expEmp;
		this.exp = exp;
	}
	public Payment() {
		super();
	}
	public Payment( String payEmpId, String expEmpId, double amount, Date payTime, int expid) {
		super();
		this.payEmpId = payEmpId;
		this.expEmpId = expEmpId;
		this.amount = amount;
		this.payTime = payTime;
		this.expid = expid;
	}
	public int getPmId() {
		return pmId;
	}
	public void setPmId(int pmId) {
		this.pmId = pmId;
	}
	public String getPayEmpId() {
		return payEmpId;
	}
	public void setPayEmpId(String payEmpId) {
		this.payEmpId = payEmpId;
	}
	public String getExpEmpId() {
		return expEmpId;
	}
	public void setExpEmpId(String expEmpId) {
		this.expEmpId = expEmpId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public int getExpid() {
		return expid;
	}
	public void setExpid(int expid) {
		this.expid = expid;
	}
	public Employee getPayEmp() {
		return payEmp;
	}
	public void setPayEmp(Employee payEmp) {
		this.payEmp = payEmp;
	}
	public Employee getExpEmp() {
		return expEmp;
	}
	public void setExpEmp(Employee expEmp) {
		this.expEmp = expEmp;
	}
	public Expense getExp() {
		return exp;
	}
	public void setExp(Expense exp) {
		this.exp = exp;
	}
	@Override
	public String toString() {
		return "Payment [pmId=" + pmId + ", payEmpId=" + payEmpId + ", expEmpId=" + expEmpId + ", amount=" + amount
				+ ", payTime=" + payTime + ", expid=" + expid + ", payEmp=" + payEmp + ", expEmp=" + expEmp + ", exp="
				+ exp + "]";
	}
	
	
}
