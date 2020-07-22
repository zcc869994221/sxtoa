package com.bjsxt.bean;

import java.util.Date;

public class Income {
	private int icId;
	private double amount;
	private Date icDate;
	private String icType;
	private String inDesc;
	private String userId;
	public int getIcId() {
		return icId;
	}
	public void setIcId(int icId) {
		this.icId = icId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getIcDate() {
		return icDate;
	}
	public void setIcDate(Date icDate) {
		this.icDate = icDate;
	}
	public String getIcType() {
		return icType;
	}
	public void setIcType(String icType) {
		this.icType = icType;
	}
	public String getInDesc() {
		return inDesc;
	}
	public void setInDesc(String inDesc) {
		this.inDesc = inDesc;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Income() {
		super();
	}
	public Income(int icId, double amount, Date icDate, String icType, String inDesc, String userId) {
		super();
		this.icId = icId;
		this.amount = amount;
		this.icDate = icDate;
		this.icType = icType;
		this.inDesc = inDesc;
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Income [icId=" + icId + ", amount=" + amount + ", icDate=" + icDate + ", icType=" + icType + ", inDesc="
				+ inDesc + ", userId=" + userId + "]";
	}
	
}
