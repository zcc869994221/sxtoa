package com.bjsxt.bean;
/*
 * 报销单明细
 */
public class ExpenseItem {
	private int itemId;//明细编号
	private int expid;//报销单的编号
	private String type;//报销单的类型  通信费用，办公耗材等等
	private double amount;//明细金额
	private String itemDesc;//明细说明
	
	private Expense expense;//报销单
	
	
	public ExpenseItem(String type, double amount, String itemDesc) {
		super();
		this.type = type;
		this.amount = amount;
		this.itemDesc = itemDesc;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getExpid() {
		return expid;
	}

	public void setExpid(int expid) {
		this.expid = expid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}

	public ExpenseItem() {
		super();
	}

	public ExpenseItem(int itemId, int expid, String type, double amount, String itemDesc, Expense expense) {
		super();
		this.itemId = itemId;
		this.expid = expid;
		this.type = type;
		this.amount = amount;
		this.itemDesc = itemDesc;
		this.expense = expense;
	}

	@Override
	public String toString() {
		return "ExpenseItem [itemId=" + itemId + ", expid=" + expid + ", type=" + type + ", amount=" + amount
				+ ", itemDesc=" + itemDesc + ", expense=" + expense + "]";
	}
	
	
}
