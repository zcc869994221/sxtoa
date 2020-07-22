package com.bjsxt.service;

import java.util.List;

import com.bjsxt.bean.Auditing;
import com.bjsxt.bean.Expense;
import com.bjsxt.bean.Payment;

/*
 * 既可以处理报销单，也可以处理报销单明细
 */
public interface ExpenseService {

	void add(Expense expense);
	/**
	 * 
	 * @Title: getToAudit   
	 * @Description:获取指定审核人要审核的报表
	 * @param auditorid
	 * @return List<Expense>      
	 * @throws
	 */
	List<Expense> getToAudit(String auditorid);
	/**
	 * 
	 * @Title: audit   
	 * @Description:审核报销单
	 * @param auditing void      
	 * @throws
	 */
	void audit(Auditing auditing);
	/**
	 * 
	 * @Title: findPayment   
	 * @Description:查找所有支出
	 * @return List<Payment>      
	 * @throws
	 */
	List<Payment> findPayment();

}
