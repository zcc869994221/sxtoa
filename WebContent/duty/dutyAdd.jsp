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
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(function(){
		//绑定签到功能
		$("#signin").click(function(){
			$.ajax({
				url:"DutyServlet?method=signin",
				type:"post",				
				success:function(data){
					//显示签到结果
					/* alert(data); */
					if(data==0){
						//$("#result").html("签到失败");
						alert("签到失败");
					}else if(data==1){
						//$("#result").html("签到成功");
						alert("签到成功");
					}else{
						//$("#result").html("您已经完成签到");
						alert("已经完成签到");
					} 
				}
			});
		})
		
		
		//绑定签退功能
		$("#signout").click(function(){
			$.ajax({
				url:"DutyServlet?method=signout",
				type:"post",				
				success:function(data){
					//显示签到结果
					/* alert(data); */
					if(data==0){
						//$("#result").html("您还没有签到哦");
						alert("您还没有签到哦");
					}else if(data==1){
						//$("#result").html("签退成功");
						alert("签退成功");
					}else{
						//$("#result").html("您已经完成签退");
						alert("您已经完成签退");
					} 
				}
			});
		})
	})
	

</script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">考勤管理</a></li>
    <li><a href="#">签到签退</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    
    <ul class="forminfo">
    	<li><label>&nbsp;</label><input name="" type="button" class="btn" value="签到" id="signin"/> 每天签到一次，不可重复签到</li>
        <li><label>&nbsp;</label></li>
    	<li><label>&nbsp;</label></li>
      	<li><label>&nbsp;</label><input name="" type="button" class="btn" value="签退" id="signout"/>可重复签退，以最后一次签退为准</li>
    </ul>
    
    
    </div>
<div id="result"></div>

</body>

</html>
