package com.bjsxt.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.bjsxt.bean.Department;
import com.bjsxt.bean.Duty;
import com.bjsxt.bean.Employee;
import com.bjsxt.service.DepartmentService;
import com.bjsxt.service.DutyService;
import com.bjsxt.service.impl.DepartmentServiceImpl;
import com.bjsxt.service.impl.DutyServiceImpl;
import com.bjsxt.util.DateChange;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class DutyServlet
 */
@WebServlet("/DutyServlet")
public class DutyServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	public void signin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		Employee emp = (Employee) req.getSession().getAttribute("emp");
		String empid = emp.getEmpid();
		//调用业务层完成签到操作
		DutyService ds = new DutyServiceImpl();
		int n  = ds.signin(empid); //1成功，0失败，2已经签到
		System.out.println(n);
		//返回内容
		resp.getWriter().print(n);
	}
	
	public void signout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		Employee emp = (Employee) req.getSession().getAttribute("emp");
		String empid = emp.getEmpid();
		//调用业务层完成签到操作
		DutyService ds = new DutyServiceImpl();
		int n  = ds.signout(empid); //1成功，0失败，2已经签退
		System.out.println(n);
		//返回内容
		resp.getWriter().print(n);
	}
	
	public void findAllDept(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//调用业务层获取所有部门
		DepartmentService ds = new DepartmentServiceImpl();
		List<Department> list = ds.findAll();
		//获取gson，把集合变成字符串
		Gson gson = new Gson();
		String json = gson.toJson(list);
		resp.getWriter().write(json);
	}
	
	public void findDuty(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数
		String empid = req.getParameter("empid");
		String sdeptno = req.getParameter("deptno");//防止空指针异常
		int deptno=0;
		try {
			deptno = Integer.parseInt(sdeptno);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		String sdtDate = req.getParameter("dtDate");//防止非法数据
		java.sql.Date dtDate = null;
		try {
			dtDate = java.sql.Date.valueOf(sdtDate);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		//设置响应格式
		resp.setContentType("text/html;charset=utf-8");
		//调用业务层
		DutyService ds = new DutyServiceImpl();
		List<Duty> list = ds.findDuty(empid,deptno,dtDate);
		//获取gson，把集合变成字符串
					//用gson修改日期格式
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String json = gson.toJson(list);
				resp.getWriter().write(json);
		
	}
	
	public void exportXls(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数
				String empid = req.getParameter("empid");
				String sdeptno = req.getParameter("deptno");//防止空指针异常
				int deptno=0;
				try {
					deptno = Integer.parseInt(sdeptno);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				String sdtDate = req.getParameter("dtDate");//防止非法数据
				java.sql.Date dtDate = null;
				try {
					dtDate = java.sql.Date.valueOf(sdtDate);
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
		//调用业务层	
				DutyService ds = new DutyServiceImpl();
				List<Duty> list = ds.findDuty(empid,deptno,dtDate);
				for (Duty duty : list) {
					duty.setDtDate(DateChange.getDate(duty.getDtDate()));
				}
		//返回outputStream
			createExcel(list,resp);	
	}
	
	private static void createExcel(List<Duty> list,HttpServletResponse resp) {
        // 创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个工作表
        HSSFSheet sheet = workbook.createSheet("考勤信息");
        
        CellRangeAddress region = new CellRangeAddress(0, // first row
                0, // last row
                0, // first column
                2 // last column
        );
        sheet.addMergedRegion(region);
        
        HSSFRow hssfRow = sheet.createRow(0);
        HSSFCell headCell = hssfRow.createCell(0);
        headCell.setCellValue("尚学堂考勤信息");
        
        // 设置单元格格式居中
        HSSFCellStyle cellStyle = workbook.createCellStyle();
    	cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);     
        headCell.setCellStyle(cellStyle);
        
        
        // 添加表头行
        hssfRow = sheet.createRow(1);
        // 添加表头内容
        headCell = hssfRow.createCell(0);
        headCell.setCellValue("用户名");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(1);
        headCell.setCellValue("真实姓名");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(2);
        headCell.setCellValue("所处部门");
        headCell.setCellStyle(cellStyle);
        
        headCell = hssfRow.createCell(3);
        headCell.setCellValue("出勤日期");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(4);
        headCell.setCellValue("签到时间");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(5);
        headCell.setCellValue("签退时间");
        headCell.setCellStyle(cellStyle);
        // 添加数据内容
        for (int i = 0; i < list.size(); i++) {
            hssfRow = sheet.createRow((int) i + 2);
            Duty duty = list.get(i);

            // 创建单元格，并设置值
            HSSFCell cell = hssfRow.createCell(0);
            cell.setCellValue(duty.getEmpid());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(1);
            cell.setCellValue(duty.getEmp().getRealName());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(2);
            cell.setCellValue(duty.getEmp().getDept().getDeptName());
            cell.setCellStyle(cellStyle);
            cell = hssfRow.createCell(3);
            cell.setCellValue(duty.getDtDate());
            cell.setCellStyle(cellStyle);
            cell = hssfRow.createCell(4);
            cell.setCellValue(duty.getSigninTime());
            cell.setCellStyle(cellStyle);
            cell = hssfRow.createCell(5);
            cell.setCellValue(duty.getSignoutTime());
            cell.setCellStyle(cellStyle);
        }

        // 保存Excel文件
        try {
        	resp.setContentType("application/vnd.ms-excel");
        	resp.setHeader("Content-disposition", "attachment; fileName=duty.xls");//以附件的形式下载文件名duty.xls
            //OutputStream outputStream = new FileOutputStream("D:/sxt签到考勤表.xls");//保存到服务器端本地
        	ServletOutputStream outputStream = resp.getOutputStream();//保存到客户端本地
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
