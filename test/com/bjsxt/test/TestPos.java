package com.bjsxt.test;

import org.junit.Test;

import com.bjsxt.bean.Department;
import com.bjsxt.bean.Position;
import com.bjsxt.service.DepartmentService;
import com.bjsxt.service.PositionService;
import com.bjsxt.service.impl.DepartmentServiceImpl;
import com.bjsxt.service.impl.PositionServiceImpl;

public class TestPos {
	@Test
	public void testSave() {
		PositionService ds = new PositionServiceImpl();
		Position dept = new Position();
		dept.setPosid(6);
		dept.setPname("裁办");
		dept.setPdesc("502");
		int i = ds.add(dept);
		System.out.println(i);
	}
	@Test
	public void testSelDeptById() {
		PositionService ds = new PositionServiceImpl();
		Position dept = new Position();
		dept.setPosid(2);		
		int i = ds.check(2);
		System.out.println(i);
	}
	@Test
	public void testsel() {
		PositionService ds = new PositionServiceImpl();
		Position ps = ds.findById(1);
		System.out.println(ps);
	}
}
