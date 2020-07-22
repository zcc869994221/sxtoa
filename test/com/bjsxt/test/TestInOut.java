package com.bjsxt.test;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.bjsxt.mapper.InOutMapper;
import com.bjsxt.mapper.PaymentMapper;
import com.bjsxt.service.InOutService;
import com.bjsxt.service.impl.InOutServiceImpl;
import com.bjsxt.util.MyBatisUtil;

public class TestInOut {
	
	@Test
	public void testfindStaticsData() {
		SqlSession session = MyBatisUtil.getSqlsession();
		InOutMapper mapper = session.getMapper(InOutMapper.class);
		List<HashMap<String, Object>> list = mapper.findStaticsData();
		String str = "[\"";
		for (int index = 0; index < list.size(); index++) {
			HashMap<String, Object> map = list.get(index);
			if(index<list.size()-1) {
				str+=map.get("ICTYPE")+"\", \"";
			}else {
				str+=map.get("ICTYPE");
			}
		}
		str+="\"]";
		System.out.println(str);
		String str2 = "[\"";
		for (int index = 0; index < list.size(); index++) {
			HashMap<String, Object> map = list.get(index);
			if(index<list.size()-1) {
				str2+=map.get("SUM(AMOUNT)")+"\", \"";
			}else {
				str2+=map.get("SUM(AMOUNT)");
			}
		}
		str2+="\"]";
		String str3="["+str+","+str2+"]";
		System.out.println(str3);
	}
	
	@Test
	public void test() {
		InOutService is = new InOutServiceImpl();
		String barData = is.getBarData();
		System.out.println(barData);
	}
	
	@Test
	public void getPie() {
		SqlSession session = MyBatisUtil.getSqlsession();
		PaymentMapper mapper = session.getMapper(PaymentMapper.class);
		List<HashMap<String, Object>> list = mapper.getPieData(0);
		String str = "[";
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, Object> map = list.get(i);
			//[{value:335+parseInt(val), name:'通信费用'},
			if(i==0) {
			str+="{value:"+map.get("SUM(AMOUNT)")+"+parseInt(val), name:\""+map.get("TYPE")+"\"}";
			}
			str+="{value:"+map.get("SUM(AMOUNT)")+", name:\""+map.get("TYPE")+"\"}";
			if(i<list.size()-1) {
				str+=",";
			}
		}
		str+="]";
		
		System.out.println(str);
	}
}
