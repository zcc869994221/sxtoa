package com.bjsxt.mapper;

import java.util.List;

import com.bjsxt.bean.Expense;

public interface ExpenseMapper {
	/*
	 * 获取下一个值
	 */
	String nextVal();
	/*
	 * 添加报销单
	 */
	void save(Expense expense);
	/*
	 * 找到该empid下能审核的报销单
	 */
	List<Expense> findByAuditorId(String auditorid);
	/*
	 * 修改报销单
	 */
	void update(Expense expense);
	/**
	 * 
	 * @Title: findById   
	 * @Description:根据报销单id获取报销单
	 * @param expid
	 * @return Expense      
	 * @throws
	 */
	Expense findById(int expid);

}
