<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>审核报销单</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#btn1").click(function(){
					//获取表单项
					var expid = $("#expid").val();
					var auditDesc = $("#auditDesc").val();
					var result = "";
						//选择器获得
					var resultArr = $("input[name=result]");
						for (var i = 0; i < resultArr.length; i++) {
							var flag = resultArr[i].checked;
							if(flag){
								result=resultArr[i].value;
								break;
							}
							
						}
					//Ajax提交表单
					//alert(expid+auditDesc+result);
					$.ajax({
						url:"ExpenseServlet?method=audit",
						type:"post",
						data:{
							expid:expid,
							result:result,
							auditDesc:auditDesc
						},
						success:function(data){
							//alert(data);
							if(data=="success"){
								alert("审核成功");
								//window.location.reload();刷新当前窗口
								window.opener.location.reload();//刷新父亲窗口
								window.close();//关闭
							}else{
								alert("审核失败");
							}
						}
						
					});
				})
			})
		</script>
	</head>

	<body>

		<div class="formtitle"><span>审核报销单</span></div>
		<form action="javascript:void(0)">
			<ul class="forminfo">
				<li>
					<label>报销单编号</label>
					<!-- ${param.expid}el表达式 -->
					<input name="" id="expid" type="text" class="dfinput" value="${param.expid}" readonly="readonly"/>
				</li>
				<li>
					<label>审核结果</label>
					<input name="result" type="radio" value="通过"/>通过
					<input name="result" type="radio" value="拒绝"/>拒绝 
					<input name="result" type="radio" value="打回"/>打回 
				</li>
				<li>
					<label>审核意见</label>
					<input id="auditDesc" name="" type="text" class="dfinput" />
				</li>
				<li>
					<label>&nbsp;</label>
					<input name="" id="btn1" type="button" class="btn" value="确认保存" />
				</li>
			</ul>
		</form>
	</body>

</html>