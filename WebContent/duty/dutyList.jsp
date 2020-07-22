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
		<link href="css/select.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="js/jquery.js"></script>
		
		<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
		<script type="text/javascript" src="js/select-ui.min.js"></script>
		<script type="text/javascript" src="editor/kindeditor.js"></script>
		<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
		$(document).ready(function(e) {
		    $(".select1").uedSelect({
				width : 200		  
			});
			
		});
		</script>
		<script type="text/javascript">
			$(document).ready(function(){
			  $(".click").click(function(){
			  $(".tip").fadeIn(200);
			  });
			  
			  $(".tiptop a").click(function(){
			  $(".tip").fadeOut(200);
			});
			
			  $(".sure").click(function(){
			  $(".tip").fadeOut(100);
			});
			
			  $(".cancel").click(function(){
			  $(".tip").fadeOut(100);
			});
			
			  
			  //获取所有的部门列表并输出，通过ajax
				  $.ajax({
					url:"DutyServlet?method=findAllDept",
					type:"post",
					success:function(jsonStr){
						  //所有部门输出
						  //jsonStr变成json Object
						  eval("var arr="+jsonStr);
						 //拼接所有的option字符串
						 var str = '<option value="0" selected="selected">全部</option>';
						 for (var i = 0; i < arr.length; i++) {
							str+='<option value="'+arr[i].deptno+'">'+arr[i].deptName+'</option>';
						}
						 //一次性写入select depton中
						 $("#deptno").html(str);
					}
				  
			  });
			  
			  //给查询按钮绑定事件
			  $("#btn1").click(function(){
				  //获取用户名
				  var empid = $("#empid").val();
				  //获取部门
				    var deptno = $("#deptno").val();
				  //获取考勤时间
				    var dtDate = $("#dtDate").val();
				 // alert(empid+deptno+dtDate);
				  //ajax请求得到符合条件的员工
				    $.ajax({
						url:"DutyServlet?method=findDuty",
						type:"post",
						data:{"empid":empid,"deptno":deptno,"dtDate":dtDate},
						dataType:"text",
						success:function(result){
							//转成对象
							eval("var arr="+result);
							//拼接字符串
							var str='';
							for (var i = 0; i < arr.length; i++) {
								if(i%2!=0){
									str+=	'<tr class="odd">'+
									'<td>'+
									'	<input name="" type="checkbox" value="" />'+
									'</td>'+
									'<td>'+arr[i].empid+'</td>'+
									'<td>'+arr[i].emp.realName+'</td>'+
									'<td>'+arr[i].emp.dept.deptName+'</td>'+
									'<td>'+arr[i].dtDate+'</td>'+
									'<td>'+arr[i].signinTime+'</td>'+
									'<td>'+arr[i].signoutTime+'</td>'+
								'</tr>';
								}else{
									str+=	'<tr>'+
									'<td>'+
									'	<input name="" type="checkbox" value="" />'+
									'</td>'+
									'<td>'+arr[i].empid+'</td>'+
									'<td>'+arr[i].emp.realName+'</td>'+
									'<td>'+arr[i].emp.dept.deptName+'</td>'+
									'<td>'+arr[i].dtDate+'</td>'+
									'<td>'+arr[i].signinTime+'</td>'+
									'<td>'+arr[i].signoutTime+'</td>'+
								'</tr>';
								}								
							}
							
							$("#tbody").html(str);
						}
					  
				  });
			  })
			  
			  $("#btn2").click(function(){
				//获取用户名
				  var empid = $("#empid").val();
				  //获取部门
				    var deptno = $("#deptno").val();
				  //获取考勤时间
				    var dtDate = $("#dtDate").val();
				  //发送到服务器
				    location.href="DutyServlet?method=exportXls&empid="+empid+"&deptno="+deptno+"&dtDate="+dtDate;
			  })
			});
		</script>

	</head>

	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="#">考勤管理</a></li>
				<li><a href="#">考勤管理</a></li>
			</ul>
		</div>

		<div class="rightinfo">

			<ul class="prosearch">
				<li>
					<label>查询：</label><i>用户名</i>
					<a>
						<input name="empid" id="empid" type="text" class="scinput" />
					</a><i>所属部门</i>
					<a>
						<select class="select1" id="deptno">
						<option value="0">全部</option>					
							</select>
					</a>
					<i>考勤时间</i>
					<a>
						<input name="" type="text" id="dtDate" class="scinput" onclick="WdatePicker()"/>
					</a>	
					<a>
						<input name="" type="button" id="btn1" class="sure" value="查询" />
						
					</a>
					<a>
						 <input name="" type="button" id="btn2" class="scbtn2" value="导出" />
						
					</a>
					
				</li>
				
					
			</ul>

			<div class="formtitle1"><span>考勤管理</span></div>

			<table class="tablelist">
				<thead>
					<tr>
						<th>
							<input name="" type="checkbox" value="" checked="checked" />
						</th>
						<th>用户名<i class="sort"><img src="images/px.gif" /></i></th>
						<th>真实姓名</th>
						<th>所属部门</th>
						<th>出勤日期</th>
						<th>签到时间</th>
						<th>签退时间</th>
					</tr>
				</thead>
				<tbody id="tbody">

				</tbody>
			</table>

			<div class="pagin">
				<div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
				<ul class="paginList">
					<li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
					<li class="paginItem"><a href="javascript:;">1</a></li>
					<li class="paginItem current"><a href="javascript:;">2</a></li>
					<li class="paginItem"><a href="javascript:;">3</a></li>
					<li class="paginItem"><a href="javascript:;">4</a></li>
					<li class="paginItem"><a href="javascript:;">5</a></li>
					<li class="paginItem more"><a href="javascript:;">...</a></li>
					<li class="paginItem"><a href="javascript:;">10</a></li>
					<li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
				</ul>
			</div>

			<div class="tip">
				<div class="tiptop"><span>提示信息</span>
					<a></a>
				</div>

				<div class="tipinfo">
					<span><img src="images/ticon.png" /></span>
					<div class="tipright">
						<p>是否确认对信息的修改 ？</p>
						<cite>如果是请点击确定按钮 ，否则请点取消。</cite>
					</div>
				</div>

				<div class="tipbtn">
					<input name="" type="button" class="sure" value="确定" />&nbsp;
					<input name="" type="button" class="cancel" value="取消" />
				</div>

			</div>

		</div>

		<script type="text/javascript">
			/* $('.tablelist tbody tr:odd').addClass('odd'); */
		</script>

	</body>

</html>