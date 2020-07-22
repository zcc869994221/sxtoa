<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style="height: 100%">

<head>
<base href="<%=basePath%>" />
<!-- baidu Map api -->
<script type="text/javascript"
	src="https://api.map.baidu.com/api?v=1.2&ak=gypEU5ByeoXGYzp6aIDuZLFGymzkoOct"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="js/select-ui.min.js"></script>
<script type="text/javascript" src="editor/kindeditor.js"></script>
       <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>
       <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts-gl/dist/echarts-gl.min.js"></script>
       <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts-stat/dist/ecStat.min.js"></script>
       <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/extension/dataTool.min.js"></script>
       <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/map/js/china.js"></script>
       <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/map/js/world.js"></script>
       <script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=xfhhaTThl11qYVrqLZii6w8qE5ggnhrY&__ec_v__=20190126"></script>
       <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/extension/bmap.min.js"></script>

<script type="text/javascript">
			$(document).ready(function(e) {
			    $(".select1").uedSelect({
					width : 345			  
				});
				
				$("#select1").change();
			});
			function changePie(val){
				$.ajax({
					url:"InOutServlet?method=getPieData",
					data:{type:val},
					type:"POST",
					dataType:"text",
					success:function(data){
						eval("var arr="+data);
						var dom = document.getElementById("container");
						var myChart = echarts.init(dom);
						var app = {};
						option = null;
						option = {
						    title : {
						        text: '尚学堂支出统计',
						        subtext: '报销统计',
						        x:'center'
						    },
						    tooltip : {
						        trigger: 'item',
						        formatter: "{a} <br/>{b} : {c} ({d}%)"
						    },
						    legend: {
						        orient: 'vertical',
						        left: 'left',
						        data: ['通信费用','办公室耗材','住宿费用','房租水电','其他']
						    },
						    series : [
						        {
						            name: '访问来源',
						            type: 'pie',
						            radius : '55%',
						            center: ['50%', '60%'],
						            data:arr,
						            itemStyle: {
						                emphasis: {
						                    shadowBlur: 10,
						                    shadowOffsetX: 0,
						                    shadowColor: 'rgba(0, 0, 0, 0.5)'
						                }
						            }
						        }
						    ]
						};
						
						if (option && typeof option === "object") {
						    myChart.setOption(option,true);
						}
					}
				});
				
			};
			
		</script>
</head>

<body style="height: 100%; margin: 0">
	<div style="height: 10% px; width: 500px; margin: 0px auto;">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 请选择支出时间段： <select
			class="select1" id="select1" onchange="changePie(this.value)">
			<option value="0">当月</option>
			<option value="1">上月</option>
			<option value="2">近3个月</option>
			<option value="3">近半年</option>
		</select>
	</div>

	<div id="container" style="height: 85%"></div>


</body>
</html>