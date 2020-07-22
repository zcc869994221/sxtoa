package com.bjsxt.test;



import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.bjsxt.bean.Employee;
import com.bjsxt.mapper.EmployeeMapper;
import com.bjsxt.service.EmployeeService;
import com.bjsxt.service.impl.EmployeeServiceImpl;
import com.bjsxt.util.MyBatisUtil;

public class TestEmp {
	
	@Test
	public void testSave(){
		SqlSession session = MyBatisUtil.getSqlsession();
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		Employee emp = new Employee();
		emp.setEmpid("89");
//		emp.setPassword("2");
//		emp.setRealName("张三");
//		emp.setSex("男");
//		emp.setBirthDate(new Date(2018-01-01));
//		emp.setHireDate(new Date(2018-01-01));
		emp.setLeaveDate(new Date(2018-01-01));
//		emp.setOnDuty(1);
//		emp.setEmpType(2);
//		emp.setPhone("123");
//		emp.setQq("123");
//		emp.setEmerContactPerson("123");
//		emp.setIdCard("123");
//		emp.setDeptno(1);
//		emp.setMgrid("");
//		emp.setPosid(1);
//		Department dept = new Department();
//		dept.setDeptno(1);
//		emp.setDept(dept);
//		Position position = new Position();
//		position.setPosid(1);
//		emp.setPosition(position);
//		Employee mgr = new Employee();
//		mgr.setEmpid("2");
//		emp.setMgr(mgr);
//		EmployeeService es = new EmployeeServiceImpl();
//		int i = es.add(emp);
		int i = mapper.save(emp);
		System.out.println(i);
	}
	
	@Test
	public void testCheck() {
		EmployeeServiceImpl es = new EmployeeServiceImpl();
		int i = es.check("1");
		
	}
	@Test
	public void testFindAll() {
		SqlSession session = MyBatisUtil.getSqlsession();
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		List<Employee> list = mapper.findAll();
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}
	@Test
	public void testTimeSet() throws ParseException {
		EmployeeService es = new EmployeeServiceImpl();
		List<Employee> list = es.findAll();
		for (Employee employee : list) {
			System.out.println(employee.getHireDate());
		}
		
	}
	
	@Test
	public void testFindEmp() {
		EmployeeService es = new EmployeeServiceImpl();
		Employee e = new Employee();
		
		
		List<Employee> list = es.findEmp(e);
		for (Employee employee : list) {
			System.out.println(employee);
		}
		
	}
	@Test
	public void testDel() {
//		SqlSession session = MyBatisUtil.getSqlsession();
//		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
//		int i = mapper.delEmp("29");
//		System.out.println(i);
		EmployeeService es = new EmployeeServiceImpl();
		boolean b = es.delEmp("29");
		System.out.println(b);
	}
	
}
