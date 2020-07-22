package com.bjsxt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjsxt.bean.Department;
import com.bjsxt.service.DepartmentService;
import com.bjsxt.service.impl.DepartmentServiceImpl;

/**
 * Servlet implementation class DepartmentServlet
 */
@WebServlet("/DepartmentServlet")
public class DepartmentServlet extends BaseServlet {
	
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//接收请求数据
    	int deptno = Integer.parseInt(req.getParameter("deptno"));
    	String deptName = req.getParameter("deptName");
    	String location = req.getParameter("location");
    	//处理请求数据
    	DepartmentService ds = new DepartmentServiceImpl();
		Department dept = new Department(deptno,deptName,location);
		int i = ds.add(dept);
		System.out.println(i);
    	//响应处理结果
    	if(i>0) {
    		resp.sendRedirect(req.getContextPath()+"/DepartmentServlet?method=findAll");
    	}else {
    		req.setAttribute("error","添加失败");
    		//此时必须使用转发，要复用req中的数据
    		req.getRequestDispatcher("system/deptAdd.jsp").forward(req,resp);
    	}
    	
    } 
    
    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    	System.out.println("我被执行");
    	//处理请求信息
    	DepartmentService ds = new DepartmentServiceImpl();
    	List<Department> list = ds.findAll();
    	req.setAttribute("list", list);
    	//跳转到页面
    	req.getRequestDispatcher("system/deptList.jsp").forward(req, resp);
    }
   
    public void delDept(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//处理请求信息
    	int deptno = Integer.parseInt(req.getParameter("deptno"));
    	System.out.println("删除编号为"+deptno+"的用户");
    	DepartmentService ds = new DepartmentServiceImpl();
    	boolean b = ds.delDept(deptno);
    	if(b==true) {
    		resp.getWriter().write("删除成功!");
    	}else {
    		resp.getWriter().write("删除失败！");
    	}    		   	   	   	   	
    }
   
    public void  findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//接收请求信息
    	int deptno = Integer.parseInt(req.getParameter("deptno"));
    	//处理请求信息
    	DepartmentService ds = new DepartmentServiceImpl();
    	Department dept = ds.findById(deptno);
    	
    	//请求转发
    	req.setAttribute("dept", dept);
    	req.getRequestDispatcher("system/deptUpdate.jsp").forward(req, resp);
 		   	   	   	   	
    }
    
    public void  updateDept(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//接收请求信息
    	int deptno = Integer.parseInt(req.getParameter("deptno"));
    	String deptName = req.getParameter("deptName");
    	String location = req.getParameter("location");
    	//处理请求信息
    	Department dept = new Department(deptno,deptName,location);
    	DepartmentService ds = new DepartmentServiceImpl();
    	//响应处理结果
    	boolean b = ds.updateDept(dept);
    	if(b==true) {//更新成功刷新部门列表，并跳转到部门管理
    		resp.sendRedirect("DepartmentServlet?method=findAll");
    	}else {
    		req.setAttribute("error","添加失败");
    		//此时必须使用转发，要复用req中的数据
    		req.getRequestDispatcher("system/deptUpdate.jsp").forward(req,resp);
    	}
		   	   	   	   	
    }
}
