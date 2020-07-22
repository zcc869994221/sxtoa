package com.bjsxt.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjsxt.bean.Department;
import com.bjsxt.bean.Employee;
import com.bjsxt.bean.Position;
import com.bjsxt.service.DepartmentService;
import com.bjsxt.service.EmployeeService;
import com.bjsxt.service.PositionService;
import com.bjsxt.service.impl.DepartmentServiceImpl;
import com.bjsxt.service.impl.EmployeeServiceImpl;
import com.bjsxt.service.impl.PositionServiceImpl;
import com.bjsxt.util.PageBean;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends BaseServlet {
	public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String empid = req.getParameter("empid");
		String password = "123456";
		String realName = req.getParameter("realName");
		String sex = req.getParameter("sex");
		String phone = req.getParameter("phone");
		String qq = req.getParameter("qq");
		String emerContactPerson = req.getParameter("emerContactPerson");
		String idCard = req.getParameter("idCard");
		//处理日期类
		java.util.Date birthDate = null;
		java.util.Date hireDate = null;
		java.util.Date leaveDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sbirthDate = req.getParameter("birthDate");
		String shireDate = req.getParameter("hireDate");
		String sleaveDate = req.getParameter("leaveDate");
		try {
			birthDate = sdf.parse(sbirthDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			hireDate = sdf.parse(shireDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			if(sleaveDate!=null) {
			leaveDate = sdf.parse(sleaveDate);			
			}else {
				leaveDate=null;
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int onDuty = Integer.parseInt(req.getParameter("onDuty"));
		int empType = Integer.parseInt(req.getParameter("empType"));
		int deptno = Integer.parseInt(req.getParameter("deptno"));
		int posid = Integer.parseInt(req.getParameter("posid"));
		String mgrid = req.getParameter("mgrid");
		Employee emp = new Employee(empid, password, realName, sex, birthDate, hireDate, leaveDate, onDuty, empType, phone, qq, emerContactPerson, idCard, deptno, posid, mgrid);
		EmployeeService es = new EmployeeServiceImpl();
		int i = es.add(emp);
		if(i>0) {
			resp.sendRedirect("EmployeeServlet?method=findAll");
		}else {
			req.setAttribute("error", "添加员工失败");
			req.getRequestDispatcher("system/empAdd.jsp").forward(req, resp);
		}
	}
	
	
	public void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//点击添加岗位跳转到toAdd方法
		//获取部门信息
		DepartmentService ds = new DepartmentServiceImpl();
		List<Department> list1 = ds.findAll();
//		System.out.println(list1);
		req.setAttribute("list1", list1);
		//获取岗位信息
		PositionService ps = new PositionServiceImpl();
		List<Position> list2 = ps.findAll();
		req.setAttribute("list2", list2);		
		//获取直接上级信息 empType;员工类型   1.普通员工  2.管理人员 含经理、总监、总裁等  3.管理员 
		EmployeeService es = new EmployeeServiceImpl();
		List<Employee> list3 = es.findByType(2);
		req.setAttribute("list3", list3);
		req.getRequestDispatcher("system/empAdd.jsp").forward(req, resp);
	}
	
	
	public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		//获取页码
//		String sindex = req.getParameter("index");
//		int index = 1;//设置默认页码1
//		try {
//			index=Integer.parseInt(sindex);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		//获取PageBean对象
//		PageBean<Employee> pageBean = new PageBean<Employee>();
//		pageBean.setIndex(index);
		//获取所有员工的信息
		EmployeeService es = new EmployeeServiceImpl();
		List<Employee> list = es.findAll();
		req.setAttribute("list", list);
//		es.findAll2(pageBean);
//		req.setAttribute("pageBean", pageBean);
		//获取所有部门的信息
		DepartmentService ds = new DepartmentServiceImpl();
		List<Department> list2 = ds.findAll();
		req.setAttribute("list2", list2);
		req.getRequestDispatcher("system/empList.jsp").forward(req, resp);
	}
	
	public void findEmp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接收查询条件
		String empid = req.getParameter("empid");
		int deptno = Integer.parseInt(req.getParameter("deptno"));
		int onDuty = Integer.parseInt(req.getParameter("onDuty"));
		String shireDate = req.getParameter("hireDate");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date hireDate = null;
		try {
			hireDate = df.parse(shireDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//封装数据
		Employee emp = new Employee();
		emp.setEmpid(empid);
		emp.setOnDuty(onDuty);
		emp.setHireDate(hireDate);
		emp.setDeptno(deptno);
		//把数据存入request中，用于页面刷新后的定位数据
		req.setAttribute("empid", empid);
		req.setAttribute("deptno", deptno);
		req.setAttribute("onDuty", onDuty);
		req.setAttribute("hireDate", shireDate);
		//获取所有员工的信息
		EmployeeService es = new EmployeeServiceImpl();
		List<Employee> list = es.findEmp(emp);
//		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		java.sql.Date resultDate = null;
		for (Employee employee : list) {
			date = employee.getHireDate();
			String strDate = sdf.format(date);
			try {
				java.util.Date newDate = sdf.parse(strDate);
				resultDate = new java.sql.Date(newDate.getTime());
				employee.setHireDate(resultDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		req.setAttribute("list", list);
		//获取所有部门的信息
		DepartmentService ds = new DepartmentServiceImpl();
		List<Department> list2 = ds.findAll();
		req.setAttribute("list2", list2);
		req.getRequestDispatcher("system/empList.jsp").forward(req, resp);
	}
	
	public void delEmp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//处理请求信息
    	String empid = req.getParameter("empid");
    	System.out.println("删除编号为"+empid+"的员工");
    	EmployeeService es = new EmployeeServiceImpl();
    	boolean b = es.delEmp(empid);
    	if(b==true) {
    		resp.getWriter().write("删除成功!");
    	}else {
    		resp.getWriter().write("删除失败！");
    	}    		   	   	   	   	
    }
	
	public void findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接收查询条件
		String empid = req.getParameter("empid");
		//获取员工的信息
		EmployeeService emps = new EmployeeServiceImpl();
		Employee emp = emps.findById(empid);
		//更改日期格式
		java.util.Date newDate = null;
		Date finalDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//修改入职格式
		java.util.Date date = emp.getHireDate();
		String str = sdf.format(date);
		try {
			newDate = sdf.parse(str);
			finalDate = new Date(newDate.getTime());
			emp.setHireDate(finalDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//修改生日格式
		java.util.Date date1 = emp.getBirthDate();
		String str1 = sdf.format(date1);
		try {
			newDate = sdf.parse(str1);
			finalDate = new Date(newDate.getTime());
			emp.setBirthDate(finalDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//修改离职格式
		java.util.Date date2 = emp.getLeaveDate();
		if(date2!=null) {
			String str2 = sdf.format(date2);
			try {
				newDate = sdf.parse(str2);
				finalDate = new Date(newDate.getTime());
				emp.setLeaveDate(finalDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}		
		req.setAttribute("emp", emp);
		//获取部门信息
		DepartmentService ds = new DepartmentServiceImpl();
		List<Department> list2 = ds.findAll();
		req.setAttribute("list2", list2);
		//获取上级信息
		EmployeeService es = new EmployeeServiceImpl();
		List<Employee> list3 = es.findByType(2);
		req.setAttribute("list3", list3);
		req.getRequestDispatcher("system/empUpdate.jsp").forward(req, resp);
	}
	
	public void empCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接收查询条件
		String empid = req.getParameter("empid");
		//获取员工的信息
		EmployeeService emps = new EmployeeServiceImpl();
		Employee emp = emps.findById(empid);
		if(emp==null) {
			resp.getWriter().write("该用户名可用");
		}else {	
			resp.getWriter().write("该用户名已被占用");
		}		
	}
	
	public void updEmp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String empid = req.getParameter("empid");
		String password = req.getParameter("password");
		String realName = req.getParameter("realName");
		String sex = req.getParameter("sex");
		String phone = req.getParameter("phone");
		String qq = req.getParameter("qq");
		String emerContactPerson = req.getParameter("emerContactPerson");
		String idCard = req.getParameter("idCard");
		//处理日期类
		java.util.Date birthDate = null;
		java.util.Date hireDate = null;
		java.util.Date leaveDate = null;
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sbirthDate = req.getParameter("birthDate");
		String shireDate = req.getParameter("hireDate");
		String sleaveDate = req.getParameter("leaveDate");
		
		try {
			birthDate = sdf.parse(sbirthDate.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			hireDate = sdf.parse(shireDate.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			if(sleaveDate!=null && sleaveDate!="") {
			leaveDate = sdf.parse(sleaveDate.toString());		
			}else {
				leaveDate=null;
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int onDuty = Integer.parseInt(req.getParameter("onDuty"));
		int empType = 0;
		int deptno = Integer.parseInt(req.getParameter("deptno"));
		int posid = 0;
		String mgrid = req.getParameter("mgrid");
		Employee emp = new Employee(empid, password, realName, sex, birthDate, hireDate, leaveDate, onDuty, empType, phone, qq, emerContactPerson, idCard, deptno, posid, mgrid);
		EmployeeService es = new EmployeeServiceImpl();
		int i = es.updEmp(emp);
		if(i>0) {
			resp.sendRedirect("EmployeeServlet?method=findAll");
		}else {
			req.setAttribute("error", "更新失败");
			req.getRequestDispatcher("system/empUpdate.jsp").forward(req, resp);
		}
	}
	
	public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取验证码
//		String yzm = req.getParameter("yzm");//用户输入的
//		String randstr = (String) req.getSession().getAttribute("randStr");//正确的验证码
		String yzm="123";
		String randstr = "123";
		//判断验证码
		if(yzm == null || !yzm.equals(randstr)) {
			req.setAttribute("yzmError", "<font style='color:red;'>验证码错误</font>");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;//return不可少
		}
		//接收查询条件
		String empid = req.getParameter("empid");
		String password = req.getParameter("password");
		Employee emp = new Employee();
		emp.setEmpid(empid);
		emp.setPassword(password);
		//获取员工的信息
		EmployeeService es = new EmployeeServiceImpl();
		Employee empl = es.login(emp);
		if(empl!=null) {
		HttpSession session = req.getSession();
		session.setAttribute("emp", empl);
		resp.sendRedirect("main.jsp");
		}else {
			req.setAttribute("error", "<font style='color:red;'>用户名或密码错误</font>");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}		
	}
	
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//用户退出清理session
		HttpSession session = req.getSession();
		session.invalidate();
		resp.sendRedirect("login.jsp");
	}
}
