package com.bjsxt.service;

import java.util.List;

import com.bjsxt.bean.Position;

public interface PositionService {
	/**
	 * 
	 * @Title: add   
	 * @Description:添加岗位
	 * @param position
	 * @return int      
	 * @throws
	 */
	int add(Position position);
	/**
	 * 
	 * @Title: check   
	 * @Description:查重
	 * @param i
	 * @return int      
	 * @throws
	 */
	int check(int i);
	/**
	 * 
	 * @Title: findAll   
	 * @Description:查找所有岗位
	 * @return List<Position>      
	 * @throws
	 */
	List<Position> findAll();
	/**
	 * 
	 * @Title: delPos   
	 * @Description:根据id删除岗位
	 * @param posid
	 * @return boolean      
	 * @throws
	 */
	boolean delPos(int posid);
	/**
	 * 
	 * @Title: findById   
	 * @Description:根据id查找岗位
	 * @param posid
	 * @return Position      
	 * @throws
	 */
	Position findById(int posid);
	/**
	 * 
	 * @Title: updateDept   
	 * @Description:更新岗位信息
	 * @param posid
	 * @return boolean      
	 * @throws
	 */
	boolean updateDept(Position pos);

}
