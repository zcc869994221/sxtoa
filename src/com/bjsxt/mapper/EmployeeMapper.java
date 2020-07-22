package com.bjsxt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bjsxt.bean.Employee;

public interface EmployeeMapper {
	/**
	 * 
	 * @Title: save   
	 * @Description:添加员工
	 * @param emp
	 * @return int      
	 * @throws
	 */
	public int save(Employee emp);

	public Employee check(String empid);

	public List<Employee> findByType(int i);

	public List<Employee> findAll();

	public List<Employee> findAll2();

	public List<Employee> findEmp(Employee emp);

	public int delEmp(String empid);

	public Employee findById(String empid);

	public int updEmp(Employee emp);

	public List<Employee> findPage(@Param("start") int start,@Param("end") int end);

}
