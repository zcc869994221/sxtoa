package com.bjsxt.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjsxt.bean.Auditing;
import com.bjsxt.bean.Employee;
import com.bjsxt.bean.Expense;
import com.bjsxt.bean.ExpenseItem;
import com.bjsxt.bean.Payment;
import com.bjsxt.service.ExpenseService;
import com.bjsxt.service.impl.ExpenseServiceImpl;
import com.bjsxt.util.DateChange;

/**
 * Servlet implementation class ExpenseServlet
 */
@WebServlet("/ExpenseServlet")
public class ExpenseServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * @Title: add   
	 * @Description:添加报销和报销明细
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException void      
	 * @throws
	 */
	public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接收请求信息
		String[] typeArr = req.getParameterValues("type");//得到数组getParameterValues
		String[] amountArr = req.getParameterValues("amount");
		String[] itemDescArr = req.getParameterValues("itemDesc");		
		//设置集合来存储报销明细
		List<ExpenseItem> itemList = new ArrayList<ExpenseItem>();
		//设置总金额
		double totalAmount = 0;
		//封装到明细中
		for (int i = 0; i < typeArr.length; i++) {
			String type = typeArr[i];
			Double amount = Double.parseDouble(amountArr[i]);
			String itemDesc = itemDescArr[i];
			ExpenseItem item = new ExpenseItem(type, amount, itemDesc);
			itemList.add(item);
			totalAmount+=amount;
		}
		
		String empid = req.getParameter("empid");
		Date expTime = new Date();
		String expDesc = req.getParameter("expDesc");
		String nextAuditorId = req.getParameter("nextAuditorId");
		String lastResult = "新创建";
		String status = "0";//0新创建  1审核中 2审核通过 3审核驳回 4已打款
		Expense expense = new Expense(empid, totalAmount, expTime, expDesc, nextAuditorId, lastResult, status);
				expense.setItemList(itemList);
		//调用业务层完成添加操作
		ExpenseService es = new ExpenseServiceImpl();
		try {
			es.add(expense);
			//成功页面跳转
			resp.sendRedirect(req.getContextPath()+"/myExpense.html");
		} catch (Exception e) {
			e.printStackTrace();
			//失败页面跳转
			req.setAttribute("error", "添加报销单失败");
			req.getRequestDispatcher("expense/expenseAdd.jsp").forward(req, resp);
		}		
	}
	/**
	 * 
	 * @Title: toAudit   
	 * @Description:待审报销
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException void      
	 * @throws
	 */
	public void toAudit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取当前用户编号
		Employee emp = (Employee)req.getSession().getAttribute("emp");
		String auditorid = emp.getEmpid();
		//到业务层查询当前用户需要审核的报销单
		ExpenseService es = new ExpenseServiceImpl();
		List<Expense> expList = es.getToAudit(auditorid);
		//跳转到指定页面
		req.setAttribute("expList", expList);
		req.getRequestDispatcher("expense/toAudit.jsp").forward(req, resp);
	}
	/**
	 * 
	 * @Title: audit   
	 * @Description:审核报销单
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException void      
	 * @throws
	 */
	public void audit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取表单
		int expid = Integer.parseInt(req.getParameter("expid"));
		String result = req.getParameter("result");
		String auditDesc = req.getParameter("auditDesc");
		
		Employee auditor = (Employee)req.getSession().getAttribute("emp");
		Date auditTime = new Date();
		Auditing auditing = new Auditing(expid, result, auditDesc, auditTime, auditor);
		//调用业务层完成审核
		ExpenseService es = new ExpenseServiceImpl();
		try {
			es.audit(auditing);
			resp.getWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().write("error");
		}
		
		//输出结果
	}
	
	public void findPayment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ExpenseService es = new ExpenseServiceImpl();
		List<Payment> list = es.findPayment();
		for (Payment payment : list) {
			payment.setPayTime(DateChange.getDate(payment.getPayTime()));
		}
		req.setAttribute("list", list);
		req.getRequestDispatcher("expense/expenseList.jsp").forward(req, resp);
	}
}
