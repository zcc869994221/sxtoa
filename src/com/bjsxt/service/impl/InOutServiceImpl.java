package com.bjsxt.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bjsxt.mapper.InOutMapper;
import com.bjsxt.mapper.PaymentMapper;
import com.bjsxt.service.InOutService;
import com.bjsxt.util.MyBatisUtil;

public class InOutServiceImpl implements InOutService {

	@Override
	public String getBarData() {
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
		return str3;
	}

	@Override
	public String getPieData(int i) {
		SqlSession session = MyBatisUtil.getSqlsession();
		PaymentMapper mapper = session.getMapper(PaymentMapper.class);
		List<HashMap<String, Object>> list = mapper.getPieData(i);
		String str = "[";
		for (int i1 = 0; i1 < list.size(); i1++) {
			HashMap<String, Object> map = list.get(i1);
			//[{value:335+parseInt(val), name:'通信费用'},
			if(i1==0) {
			str+="{value:"+map.get("SUM(AMOUNT)")+"+parseInt(val), name:\""+map.get("TYPE")+"\"},";
			}
			str+="{value:"+map.get("SUM(AMOUNT)")+", name:\""+map.get("TYPE")+"\"}";
			if(i1<list.size()-1) {
				str+=",";
			}
		}
		str+="]";
		
		System.out.println(str);
		return str;
	}

}
