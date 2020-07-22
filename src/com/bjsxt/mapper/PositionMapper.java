package com.bjsxt.mapper;

import java.util.List;

import com.bjsxt.bean.Position;

public interface PositionMapper {
	
	/**
	 * 
	 * @Title: save   
	 * @Description:添加岗位
	 * @param position
	 * @return int      
	 * @throws
	 */
	int save(Position position);
	
	/**
	 * 
	 * @Title: selPosById   
	 * @Description:查重
	 * @param posid
	 * @return Position      
	 * @throws
	 */
	Position selPosById(int posid);
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
	 * @Title: delPosById   
	 * @Description:根据id删除岗位
	 * @param posid
	 * @return int      
	 * @throws
	 */
	int delPosById(int posid);
	/**
	 * 
	 * @Title: updatePos   
	 * @Description:更新岗位信息根据id
	 * @param posid
	 * @return int      
	 * @throws
	 */
	int updatePos(Position pos);

	

}
