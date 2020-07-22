package com.bjsxt.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bjsxt.bean.Payment;

public interface PaymentMapper {
	/**
	 * 
	 * @Title: save   
	 * @Description:保存支出信息
	 * @param pm
	 * @return int      
	 * @throws
	 */
	void save(Payment pm);
	
	List<Payment> sel(@Param("payEmpId") String payEmpId,@Param("type") int type);
	
	List<HashMap<String, Object>> getPieData(int i);
}
