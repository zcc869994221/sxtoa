package com.bjsxt.mapper;

import java.util.List;

import com.bjsxt.bean.Department;

public interface DepartmentMapper {
	/**
	 * 
	 * @Title: save   
	 * @Description:添加部门
	 * @param dept
	 * @return int      
	 * @throws
	 */
	public int save(Department dept);
	/**
	 * 
	 * @Title: selDeptById   
	 * @Description:通过id查询部门
	 * @param dept
	 * @return Department      
	 * @throws
	 */
	public Department selDeptById(int deptno);
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
	 * @return int      
	 * @throws
	 */
	public int delDept(int deptno);
	/**
	 * 
	 * @Title: updateDept   
	 * @Description:更新部门表
	 * @param dept
	 * @return int      
	 * @throws
	 */
	public int updateDept(Department dept);
}
