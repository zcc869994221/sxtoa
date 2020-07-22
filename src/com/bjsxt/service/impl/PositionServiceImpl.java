package com.bjsxt.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bjsxt.bean.Department;
import com.bjsxt.bean.Position;
import com.bjsxt.mapper.DepartmentMapper;
import com.bjsxt.mapper.PositionMapper;
import com.bjsxt.service.PositionService;
import com.bjsxt.util.MyBatisUtil;

public class PositionServiceImpl implements PositionService {

	@Override
	public int add(Position position) {
		SqlSession session = MyBatisUtil.getSqlsession();
		PositionMapper mapper = session.getMapper(PositionMapper.class);
		int check = this.check(position.getPosid());
		if(check>0) {
			int i = mapper.save(position);			
				session.commit();			
			return i;
		}else {
			return -1;
		}
	}
	
	@Override
	public int check(int posid) {
		SqlSession session = MyBatisUtil.getSqlsession();
		PositionMapper mapper = session.getMapper(PositionMapper.class);
		Position d = mapper.selPosById(posid);
		if(d==null) {
			return 1;
		}else {
			return -1;
		}	
	}

	@Override
	public List<Position> findAll() {
		SqlSession session = MyBatisUtil.getSqlsession();
		PositionMapper mapper = session.getMapper(PositionMapper.class);
		return mapper.findAll();
	}

	@Override
	public boolean delPos(int posid) {
		SqlSession session = MyBatisUtil.getSqlsession();
		PositionMapper mapper = session.getMapper(PositionMapper.class);
		int i = mapper.delPosById(posid);
		if(i>0) {
			session.commit();
			return true;
		}
		session.rollback();
		return false;
	}

	@Override
	public Position findById(int posid) {
		SqlSession session = MyBatisUtil.getSqlsession();
		PositionMapper mapper = session.getMapper(PositionMapper.class);
		return mapper.selPosById(posid);
	}

	@Override
	public boolean updateDept(Position pos) {
		SqlSession session = MyBatisUtil.getSqlsession();
		PositionMapper mapper = session.getMapper(PositionMapper.class);
		int i = mapper.updatePos(pos);
		if(i>0) {
			session.commit();
			return true;
		}
		session.rollback();
		return false;
	}

}
