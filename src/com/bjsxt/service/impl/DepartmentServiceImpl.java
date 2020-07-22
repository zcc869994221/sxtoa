package com.bjsxt.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bjsxt.bean.Department;
import com.bjsxt.mapper.DepartmentMapper;
import com.bjsxt.service.DepartmentService;
import com.bjsxt.util.MyBatisUtil;

public class DepartmentServiceImpl implements DepartmentService {

	@Override
	public int add(Department dept) {
		SqlSession session = MyBatisUtil.getSqlsession();
		DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
		int check = this.check(dept.getDeptno());
		if(check>0) {
			int i = mapper.save(dept);			
				session.commit();			
			return i;
		}else {
			return -1;
		}
		
	}

	@Override
	public int check(int deptno) {
		SqlSession session = MyBatisUtil.getSqlsession();
		DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
		Department d = mapper.selDeptById(deptno);
		if(d==null) {
			return 1;
		}else {
			return -1;
		}		
	}

	@Override
	public List<Department> findAll() {
		SqlSession session = MyBatisUtil.getSqlsession();
		DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
		return mapper.findAll();
	}

	@Override
	public boolean delDept(int deptno) {
		SqlSession session = MyBatisUtil.getSqlsession();
		DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
		int i = mapper.delDept(deptno);
		if(i>0) {
			session.commit();
			return true;
		}
		session.rollback();
		return false;		
	}

	@Override
	public Department findById(int deptno) {
		SqlSession session = MyBatisUtil.getSqlsession();
		DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
		
		return mapper.selDeptById(deptno);
	}

	@Override
	public boolean updateDept(Department dept) {
		SqlSession session = MyBatisUtil.getSqlsession();
		DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
		int i = mapper.updateDept(dept);
		if(i>0) {
			session.commit();
			return true;
		}
		session.rollback();
		return false;
	}
	
	

}
