package com.bjsxt.service;

import java.util.List;

import com.bjsxt.bean.Department;

public interface DepartmentService {
	/**
	 * 
	 * @Title: add   
	 * @Description:添加部门
	 * @param dept
	 * @return int      
	 * @throws
	 */
	public int add(Department dept);
	/**
	 * 
	 * @Title: check   
	 * @Description:检查部门id是否重复
	 * @param deptno
	 * @return int      
	 * @throws
	 */
	public int check(int deptno);
	/**
	 * 
	 * @Title: findAll   
	 * @Description:查找所有部门
	 * @return List<Department>      
	 * @throws
	 */
	public List<Department> findAll();
	/**
	 * 
	 * @Title: delDept   
	 * @Description:根据编号删除部门
	 * @param deptno
	 * @return boolean      
	 * @throws
	 */
	public boolean delDept(int deptno);
	/**
	 * @param deptno 
	 * 
	 * @Title: findById   
	 * @Description:根据编号查找部门
	 * @return Department      
	 * @throws
	 */
	public Department findById(int deptno);
	/**
	 * 
	 * @Title: updateDept   
	 * @Description:更新用户信息
	 * @param dept
	 * @return boolean      
	 * @throws
	 */
	public boolean updateDept(Department dept);
	
}
