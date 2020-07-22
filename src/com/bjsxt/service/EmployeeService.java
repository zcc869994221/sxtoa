package com.bjsxt.service;

import java.util.List;

import com.bjsxt.bean.Employee;
import com.bjsxt.util.PageBean;

public interface EmployeeService {
	/**
	 * 
	 * @Title: add   
	 * @Description:添加员工
	 * @param emp void      
	 * @throws
	 */
	public int add(Employee emp);

	public List<Employee> findByType(int i);

	public List<Employee> findAll();
	public void findAll2(PageBean<Employee> pageBean);
	public List<Employee> findEmp(Employee emp);

	public boolean delEmp(String empid);

	public Employee findById(String empid);

	public int updEmp(Employee emp);

	public Employee login(Employee emp);
	
}
