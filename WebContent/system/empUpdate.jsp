<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<base href="<%=basePath%>" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>更新员工</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="css/select.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
		<script type="text/javascript" src="js/select-ui.min.js"></script>
		<script type="text/javascript" src="editor/kindeditor.js"></script>
		<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
		$(document).ready(function(e) {
		    $(".select1").uedSelect({
				width : 345			  
			});
		    
		});
		
		function check(empid){
			//alert(empid);		    				   			
			$.ajax({
				type: "post",
				   url: "EmployeeServlet",
				   data: "empid="+empid+"&method=empCheck",
				   success: function(meg){
					   
					 // alert(meg);
					  if(meg =='该用户名已被占用'){
						  $("#iid").html('<font style="color:red;">'+meg+'</font>');
					  }else{
						  $("#iid").html('<font style="color:green;">'+meg+'</font>');
					  }
					  
				   }
			});	
		}
		</script>
		<script type="text/javascript">
	/* 富文本编辑器 */
		KE.show({id:"ecp",width:"800px",height:"300px"});
	</script>
	</head>

	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="#">人事管理</a></li>
				<li><a href="#">修改员工信息</a></li>
			</ul>
		</div>

		<div class="formbody">
			<div class="formtitle"><span>基本信息</span></div>
<form action="EmployeeServlet?method=updEmp" method="post">
<input type="hidden" value="${emp.password}" name="password"/>
			<ul class="forminfo">
				<li>
					<label>用户名</label>
					<input id="empid" name="empid" type="text" class="dfinput" value="${emp.empid}"  style="float: left;" readonly="readonly"/><span id="iid" style="float: left;"><i>必须唯一，也可以根据真实姓名自动生成</i></span></li>
				<li>
					<li>
						<label>真实姓名</label>
						<input name="realName" type="text" class="dfinput" value="${emp.realName}" /><i></i></li>
					<li>
						<label>性别</label><cite>
						<c:if test="${emp.sex=='男'}">
							<input name="sex" type="radio" value="男" checked="checked" />男&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="sex" type="radio" value="女" />女<i>也可以根据身份证号自动获取</i></cite>
						</c:if>
						<c:if test="${emp.sex=='女'}">
							<input name="sex" type="radio" value="男"  />男&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="sex" type="radio" value="女" checked="checked"/>女<i>也可以根据身份证号自动获取</i></cite>
						</c:if>					
					</li>
					<li>
						<label>出生日期</label>
						<input name="birthDate" type="text" class="dfinput" value="${emp.birthDate}" onfocus="WdatePicker({skin:'whyGreen',lang:'en'})"/><i>也可以根据身份证号自动获取</i></li>
					<li>
					<li>
						<label>入职时间</label>
						<input name="hireDate" type="text" class="dfinput" value="${emp.hireDate}" onfocus="WdatePicker({skin:'whyGreen',lang:'en'})"/><i></i></li>
					
					<li>
						<label>离职时间</label>
						<input name="leaveDate" type="text" class="dfinput" value="${emp.leaveDate}" onfocus="WdatePicker({skin:'whyGreen',lang:'en'})"/><i></i></li>
					<li>
						<label>是否在职</label><cite>
						<c:if test="${emp.onDuty==1 }">
							<input name="onDuty" type="radio" value="1" checked="checked" />是&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="onDuty" type="radio" value="0" />否</cite>
						</c:if>		
						<c:if test="${emp.onDuty==0 }">
							<input name="onDuty" type="radio" value="1" />是&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="onDuty" type="radio" value="0" checked="checked" />否</cite>
						</c:if>					
					</li>
					<li>
						<label>所属部门<b>*</b></label>
						<div class="vocation">
							<select class="select1" name="deptno">
							<c:forEach items="${list2}" var="dept">
								<c:if test="${dept.deptno==emp.deptno}">
								<option value="${dept.deptno}" selected="selected">${dept.deptName}</option>	
								</c:if>
								<c:if test="${dept.deptno!=emp.deptno}">
								<option value="${dept.deptno}">${dept.deptName}</option>	
								</c:if>				
							</c:forEach>								
							</select>
						</div>

					</li>
					<li>
						<label>直接上级<b>*</b></label>						
						<div class="vocation">
							<select class="select1" name="mgrid">
							<option value="0">无上级</option>
							<c:forEach items="${list3}" var="mgr">
								<c:if test="${mgr.empid == emp.mgrid }">
									<option value="${mgr.empid}" selected="selected">${mgr.realName}</option>
								</c:if>
								<c:if test="${mgr.empid != emp.mgrid }">
									<option value="${mgr.empid}">${mgr.realName}</option>
								</c:if>
								
							</c:forEach>
							</select>							
						</div>
					&nbsp;&nbsp;<input name="" type="text" class="dfinput"  placeholder="也可以在此输入首字母帮助显示"/></li>
					</li>
					<li>
						<label>联系方式</label>
						<input name="phone" type="text" class="dfinput" value="${emp.phone }"/>
					</li>
					<li>
						<label>QQ号</label>
						<input name="qq" type="text" class="dfinput" value="${emp.qq }"/>
					</li>
					<li>
						<label>紧急联系人信息</label>
						<textarea name="emerContactPerson" id="ecp" cols="" rows="" class="textinput">${emp.emerContactPerson }</textarea>
					</li>
					<li>
						<label>身份证号</label>
						<input name="idCard" type="text" class="dfinput" value="${emp.idCard }"/>
					</li>
					<li>
						<label>&nbsp;</label>
						<input name="" type="submit" class="btn" value="确认保存" />
					</li>
			</ul>
</form>
		</div>

	</body>

</html>