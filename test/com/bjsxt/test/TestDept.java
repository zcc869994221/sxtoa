package com.bjsxt.test;

import java.util.List;

import org.junit.Test;

import com.bjsxt.bean.Department;
import com.bjsxt.service.DepartmentService;
import com.bjsxt.service.impl.DepartmentServiceImpl;

public class TestDept {
	
	@Test
	public void testSave() {
		DepartmentService ds = new DepartmentServiceImpl();
		Department dept = new Department();
		dept.setDeptno(6);
		dept.setDeptName("裁办");
		dept.setLocation("502");
		int i = ds.add(dept);
		System.out.println(i);
	}
	@Test
	public void testSelDeptById() {
		DepartmentService ds = new DepartmentServiceImpl();
		Department dept = new Department();
		dept.setDeptno(6);		
		int i = ds.check(6);
		System.out.println(i);
	}
	@Test
	public void testFindAll() {
		DepartmentService ds = new DepartmentServiceImpl();
		List<Department> list = ds.findAll();
		for (Department department : list) {
			System.out.println(department);
		}
	}
	
	@Test
	public void testDelDept() {
		DepartmentService ds = new DepartmentServiceImpl();
		boolean b = ds.delDept(3);
		System.out.println(b);
	}
	
	@Test
	public void testfindById() {
		DepartmentService ds = new DepartmentServiceImpl();
		Department dept = ds.findById(1);
		System.out.println(dept);
	}
	
	@Test
	public void testUpdateDept() {
		DepartmentService ds = new DepartmentServiceImpl();
		Department dept = new Department();
		dept.setDeptno(8);
		dept.setDeptName("裁办");
		dept.setLocation("502");
		boolean b= ds.updateDept(dept);
		System.out.println(b);
	}
}
