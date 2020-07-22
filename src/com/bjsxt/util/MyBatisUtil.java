package com.bjsxt.util;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/**
 * 
 * @ClassName: MyBatisUtil
 * @Description: 
 * 		用ThreadLocal保证线程获取sqlsession的唯一性
 * 
 * @author Zhy
 * @date 2020年2月24日
 *
 */
public class MyBatisUtil {
	private static SqlSessionFactory factory = null;
	private static ThreadLocal<SqlSession> threadLcoal = new ThreadLocal<SqlSession>();//用于存放session
	static {
		try {
			factory =new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("MyBatis.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 获取session
	 */
	public static SqlSession getSqlsession() {
		//从当前线程获取
		SqlSession sqlSession = threadLcoal.get();
		if(sqlSession == null){
			sqlSession = factory.openSession();
			//将sqlSession与当前线程绑定
			threadLcoal.set(sqlSession);
		}
		return sqlSession;
	}
	
	/**
	 * 关闭Session
	 */
	public static void closeSqlSession(){
		//从当前线程获取
		SqlSession sqlSession = threadLcoal.get();
		if(sqlSession != null){
			sqlSession.close();
			threadLcoal.remove();
		}
	}
}
