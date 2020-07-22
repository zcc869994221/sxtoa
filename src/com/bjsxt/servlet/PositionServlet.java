package com.bjsxt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjsxt.bean.Department;
import com.bjsxt.bean.Position;
import com.bjsxt.service.DepartmentService;
import com.bjsxt.service.PositionService;
import com.bjsxt.service.impl.DepartmentServiceImpl;
import com.bjsxt.service.impl.PositionServiceImpl;

/**
 * Servlet implementation class PositionServlet
 */
@WebServlet("/PositionServlet")
public class PositionServlet extends BaseServlet {
	
	public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//接收请求数据
    	int posid = Integer.parseInt(req.getParameter("posid"));
    	String pname = req.getParameter("pname");
    	String pdesc = req.getParameter("pdesc");
    	//处理请求数据
    	Position position = new Position(posid,pname,pdesc);
    	PositionService ps = new PositionServiceImpl();
		int i = ps.add(position);
		System.out.println(i);
    	//响应处理结果
    	if(i>0) {
    		resp.sendRedirect(req.getContextPath()+"/PositionServlet?method=findAll");
    	}else {
    		req.setAttribute("error","添加失败");
    		//此时必须使用转发，要复用req中的数据
    		req.getRequestDispatcher("system/positionAdd.jsp").forward(req,resp);
    	}
    	
    }
	
	public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//处理请求信息
		PositionService ps = new PositionServiceImpl();
    	List<Position> list = ps.findAll();
    	req.setAttribute("list", list);
    	//跳转到页面
    	req.getRequestDispatcher("system/positionList.jsp").forward(req, resp);
	}
	
	public void delPos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("我被执行");
    	//处理请求信息
    	int posid = Integer.parseInt(req.getParameter("posid"));
    	System.out.println("删除编号为"+posid+"的岗位");
    	PositionService ps = new PositionServiceImpl();
    	boolean b = ps.delPos(posid);
    	if(b==true) {
    		resp.getWriter().write("删除成功!");
    	}else {
    		resp.getWriter().write("删除失败！");
    	}    		   	   	   	   	
    }
   
    public void  findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//接收请求信息
    	int posid = Integer.parseInt(req.getParameter("posid"));
    	//处理请求信息
    	PositionService ps = new PositionServiceImpl();
    	Position pos = ps.findById(posid);
    	
    	//请求转发
    	req.setAttribute("pos", pos);
    	req.getRequestDispatcher("system/positionUpdate.jsp").forward(req,resp);
 		   	   	   	   	
    }
    
    public void  updatePos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//接收请求信息
    	int posid = Integer.parseInt(req.getParameter("posid"));
    	String pname = req.getParameter("pname");
    	String pdesc = req.getParameter("pdesc");
    	//处理请求信息
    	Position pos = new Position(posid,pname,pdesc);
    	PositionService ps = new PositionServiceImpl();
    	//响应处理结果
    	boolean b = ps.updateDept(pos);
    	if(b==true) {//更新成功刷新部门列表，并跳转到部门管理
    		resp.sendRedirect("PositionServlet?method=findAll");
    	}else {
    		req.setAttribute("error","添加失败");
    		//此时必须使用转发，要复用req中的数据
    		req.getRequestDispatcher("system/positionUpdate.jsp").forward(req,resp);
    	}
		   	   	   	   	
    }
}
