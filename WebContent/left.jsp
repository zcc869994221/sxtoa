<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
	<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script language="JavaScript" src="js/jquery.js"></script>

		<script type="text/javascript">
			$(function(){	
				//导航切换
				$(".menuson .header").click(function(){
					var $parent = $(this).parent();
					$(".menuson>li.active").not($parent).removeClass("active open").find('.sub-menus').hide();
					
					$parent.addClass("active");
					if(!!$(this).next('.sub-menus').size()){
						if($parent.hasClass("open")){
							$parent.removeClass("open").find('.sub-menus').hide();
						}else{
							$parent.addClass("open").find('.sub-menus').show();	
						}
						
						
					}
				});
				
				// 三级菜单点击
				$('.sub-menus li').click(function(e) {
			        $(".sub-menus li.active").removeClass("active")
					$(this).addClass("active");
			    });
				
				$('.title').click(function(){
					var $ul = $(this).next('ul');
					$('dd').find('.menuson').slideUp();
					if($ul.is(':visible')){
						$(this).next('.menuson').slideUp();
					}else{
						$(this).next('.menuson').slideDown();
					}
				});
			})
		</script>

	</head>

	<body style="background:#f0f9fd;">
		<div class="lefttop"><span></span>导航菜单</div>

		<dl class="leftmenu">
		<c:if test="${sessionScope.emp.empType==2 || sessionScope.emp.empType==3}">
			<dd>
				<div class="title"><span><img src="images/leftico03.png" /></span>人事管理</div>
				<ul class="menuson">
					<li><cite></cite><a href="system/deptAdd.jsp" target="rightFrame">添加部门</a><i></i></li>
					<li><cite></cite><a href="DepartmentServlet?method=findAll" target="rightFrame">部门管理</a><i></i></li>
					<li><cite></cite><a href="system/positionAdd.jsp" target="rightFrame">添加岗位</a><i></i></li>
					<li><cite></cite><a href="PositionServlet?method=findAll" target="rightFrame">岗位管理</a><i></i></li>
					<li><cite></cite><a href="EmployeeServlet?method=toAdd" target="rightFrame">添加员工</a><i></i></li>
					<li><cite></cite><a href="EmployeeServlet?method=findAll" target="rightFrame">员工管理</a><i></i></li>
				</ul>
			</dd>
			<dd>
				<div class="title"><span><img src="images/leftico03.png" /></span>考勤管理</div>
				<ul class="menuson">
					<li><cite></cite><a href="duty/dutyAdd.jsp" target="rightFrame">签到签退</a><i></i></li>
					<li><cite></cite><a href="duty/dutyList.jsp" target="rightFrame">考勤管理</a><i></i></li>
					<li><cite></cite><a href="myDuty.html" target="rightFrame">我的考勤</a><i></i></li>
				</ul>
			</dd>
			<dd>
				<div class="title"><span><img src="images/leftico03.png" /></span>报销管理</div>
				<ul class="menuson">
					<li><cite></cite><a href="expense/expenseAdd.jsp" target="rightFrame">添加报销</a><i></i></li>
					<li><cite></cite><a href="ExpenseServlet?method=toAudit" target="rightFrame">待审报销</a><i></i></li>
					<li><cite></cite><a href="myExpense.html" target="rightFrame">我的报销</a><i></i></li>
					<li><cite></cite><a href="myAudit.html" target="rightFrame">我的审核历史</a><i></i></li>					
				</ul>
			</dd>
			<dd>
				<div class="title"><span><img src="images/leftico03.png" /></span>收支管理</div>
				<ul class="menuson">
					<li><cite></cite><a href="incomeAdd.html" target="rightFrame">添加收入</a><i></i></li>
					<li><cite></cite><a href="incomeList.html" target="rightFrame">查看收入</a><i></i></li>
					<li><cite></cite><a href="inout/incomestatis.jsp" target="rightFrame">收入统计</a><i></i></li>
					<li><cite></cite><a href="ExpenseServlet?method=findPayment" target="rightFrame">查看支出</a><i></i></li>
					<li><cite></cite><a href="expense/expensestatis.jsp" target="rightFrame">支出统计</a><i></i></li>
				</ul>
			</dd>
		</c:if>
			

			<!-- 设置普通人的列表 -->
		<c:if test="${sessionScope.emp.empType==1}">		
			<dd>
				<div class="title"><span><img src="images/leftico03.png" /></span>考勤管理</div>
				<ul class="menuson">
					<li><cite></cite><a href="duty/dutyAdd.jsp" target="rightFrame">签到签退</a><i></i></li>

					<li><cite></cite><a href="myDuty.html" target="rightFrame">我的考勤</a><i></i></li>
				</ul>
			</dd>
			<dd>
				<div class="title"><span><img src="images/leftico03.png" /></span>报销管理</div>
				<ul class="menuson">
					<li><cite></cite><a href="expense/expenseAdd.jsp" target="rightFrame">添加报销</a><i></i></li>
					
					<li><cite></cite><a href="myExpense.html" target="rightFrame">我的报销</a><i></i></li>
									
				</ul>
			</dd>
		</c:if>	
			
			
			
			
			
			<dd>
				<div class="title"><span><img src="images/leftico03.png" /></span>个人平台</div>
				<ul class="menuson">
					<li><cite></cite><a href="myInfo.html" target="rightFrame">我的信息</a><i></i></li>
					<li><cite></cite><a href="myPwd.html" target="rightFrame">修改密码</a><i></i></li>
				</ul>
			</dd>
		</dl>

	</body>

</html>