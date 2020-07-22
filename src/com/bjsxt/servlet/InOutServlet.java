package com.bjsxt.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjsxt.service.InOutService;
import com.bjsxt.service.impl.InOutServiceImpl;

/**
 * Servlet implementation class InOutServlet
 */
@WebServlet("/InOutServlet")
public class InOutServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
		public void getBarData(HttpServletRequest req, HttpServletResponse resp) throws IOException{
			
			//调用业务层获取json string
			InOutService is = new InOutServiceImpl();
			String jsonStr=is.getBarData();
			//返回json string
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().write(jsonStr);
		}
		
		public void getPieData(HttpServletRequest req, HttpServletResponse resp) throws IOException{
			
			//调用业务层获取json string
			InOutService is = new InOutServiceImpl();
			String jsonStr=is.getPieData(0);//0代表查询所有时间段 1 2 3
					//"[{value:335+parseInt(val), name:'通信费用'},{value:310, name:'办公室耗材'},{value:234, name:'住宿费用'},{value:135, name:'房租水电'},{value:1548, name:'其他'}]";
			//返回json string
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().write(jsonStr);
		}
}
