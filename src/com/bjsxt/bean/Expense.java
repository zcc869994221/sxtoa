package com.bjsxt.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * 报销单
 */
public class Expense {
	private int expid;//报销单id
	private String empid;//报销人id
	private double totalAmount;//报销单总金额
	private Date expTime;//报销时间
	private String expDesc;//报销单备注信息
	private String nextAuditorId;//下一个审核人的编号
	private String lastResult;//最新的审核结果
	private String status;//报销单的状态  0新创建  1审核中 2审核通过 3审核打回4审核拒绝 5已打款
	private Employee emp;//报销人
	private Employee nextAuditor;//下一个审核人
	private List<ExpenseItem> itemList = new ArrayList<ExpenseItem>();//保存报销单的所有明细
	
	public List<ExpenseItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<ExpenseItem> itemList) {
		this.itemList = itemList;
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
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Date getExpTime() {
		return expTime;
	}
	public void setExpTime(Date expTime) {
		this.expTime = expTime;
	}
	public String getExpDesc() {
		return expDesc;
	}
	public void setExpDesc(String expDesc) {
		this.expDesc = expDesc;
	}
	public String getNextAuditorId() {
		return nextAuditorId;
	}
	public void setNextAuditorId(String nextAuditorId) {
		this.nextAuditorId = nextAuditorId;
	}
	public String getLastResult() {
		return lastResult;
	}
	public void setLastResult(String lastResult) {
		this.lastResult = lastResult;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	public Employee getNextAuditor() {
		return nextAuditor;
	}
	public void setNextAuditor(Employee nextAuditor) {
		this.nextAuditor = nextAuditor;
	}
	public Expense() {
		super();
	}
	public Expense(String empid, double totalAmount, Date expTime, String expDesc, String nextAuditorId,
			String lastResult, String status) {
		super();
		this.empid = empid;
		this.totalAmount = totalAmount;
		this.expTime = expTime;
		this.expDesc = expDesc;
		this.nextAuditorId = nextAuditorId;
		this.lastResult = lastResult;
		this.status = status;
	}
	public Expense(int expid, String empid, double totalAmount, Date expTime, String expDesc, String nextAuditorId,
			String lastResult, String status, Employee emp, Employee nextAuditor) {
		super();
		this.expid = expid;
		this.empid = empid;
		this.totalAmount = totalAmount;
		this.expTime = expTime;
		this.expDesc = expDesc;
		this.nextAuditorId = nextAuditorId;
		this.lastResult = lastResult;
		this.status = status;
		this.emp = emp;
		this.nextAuditor = nextAuditor;
	}
	@Override
	public String toString() {
		return "Expense [expid=" + expid + ", empid=" + empid + ", totalAmount=" + totalAmount + ", expTime=" + expTime
				+ ", expDesc=" + expDesc + ", nextAuditorId=" + nextAuditorId + ", lastResult=" + lastResult
				+ ", status=" + status + ", emp=" + emp + ", nextAuditor=" + nextAuditor + ", itemList=" + itemList
				+ "]";
	}
	
	
}
