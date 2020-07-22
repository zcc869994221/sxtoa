package com.bjsxt.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bjsxt.bean.Employee;
import com.bjsxt.mapper.EmployeeMapper;
import com.bjsxt.service.EmployeeService;
import com.bjsxt.util.MyBatisUtil;
import com.bjsxt.util.PageBean;

import oracle.net.aso.p;

public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public int add(Employee emp) {
		SqlSession session = MyBatisUtil.getSqlsession();
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		//查重
		int j = this.check(emp.getEmpid());
		if(j>0) {
			int i = mapper.save(emp);
			if(i>0) {
				session.commit();
				return i;
			}
			session.rollback();
			return i;
		}
		return 0;
	}

	
	public int check(String empid) {
		int i = 0;
		SqlSession session = MyBatisUtil.getSqlsession();
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		Employee emp = mapper.check(empid);
		if(emp==null) {
			i=1;
		}		
		return i;		
	}


	@Override
	public List<Employee> findByType(int i) {
		SqlSession session = MyBatisUtil.getSqlsession();
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		return mapper.findByType(i);
	}


	@Override
	public List<Employee> findAll() {
		SqlSession session = MyBatisUtil.getSqlsession();
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

		List<Employee> list = mapper.findAll();

//		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		 java.sql.Date resultDate = null;
		for (Employee employee : list) {
			date = employee.getHireDate();
			String strDate = sdf.format(date);
			try {
				Date newDate = sdf.parse(strDate);
				resultDate = new java.sql.Date(newDate.getTime());
				employee.setHireDate(resultDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return list;
		
	}

	@Override
	public void findAll2(PageBean<Employee> pageBean) {
		SqlSession session = MyBatisUtil.getSqlsession();
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		//查询数据库中的总数
		int totalCount = mapper.findAll().size();//效率低，查询所有学生，再数一下  用count
		//使用记录总数计算PageBean中的其他属性
		pageBean.setTotalCount(totalCount);
		//调用mapper层，获取指定页的学生数据，并放入pageBean的List中
		/*
		 * 每页5条记录
		 * 第几页   起始记录号    结束记录号<=	  <
		 * 1      0            4          5
		 * 2      5            9          10
		 * 3      10           14         15
		 * 
		 * index     (index-1)*size          index*size
		 */
		//int start = (pageBean.getIndex()-1)*pageBean.getSize();
		//int end = pageBean.getIndex()*pageBean.getSize();
		int start = pageBean.getStartRow();
		int end = pageBean.getEndRow();
//		List<Employee> list = mapper.findAll();
		List<Employee> list = mapper.findPage(start,end);
//		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		 java.sql.Date resultDate = null;
		for (Employee employee : list) {
			date = employee.getHireDate();
			String strDate = sdf.format(date);
			try {
				Date newDate = sdf.parse(strDate);
				resultDate = new java.sql.Date(newDate.getTime());
				employee.setHireDate(resultDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
	}
	@Override
	public List<Employee> findEmp(Employee emp) {
		SqlSession session = MyBatisUtil.getSqlsession();
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		return mapper.findEmp(emp);
	}


	@Override
	public boolean delEmp(String empid) {
		SqlSession session = MyBatisUtil.getSqlsession();
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		int i = mapper.delEmp(empid);
		if(i>0) {
			session.commit();
			return true;
		}
		session.rollback();
		return false;
	}


	@Override
	public Employee findById(String empid) {
		SqlSession session = MyBatisUtil.getSqlsession();
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		return mapper.findById(empid);
	}


	@Override
	public int updEmp(Employee emp) {
		SqlSession session = MyBatisUtil.getSqlsession();
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		int i = mapper.updEmp(emp);
		if(i>0) {
			session.commit();
			return 1;
		}
		session.rollback();
		return 0;
	}


	@Override
	public Employee login(Employee emp) {
		SqlSession session = MyBatisUtil.getSqlsession();
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		Employee e = mapper.findById(emp.getEmpid());
		if(e!=null) {
			if(e.getPassword().equals(emp.getPassword())) {
				return e;
			}
		}
		return null;
	}
}
