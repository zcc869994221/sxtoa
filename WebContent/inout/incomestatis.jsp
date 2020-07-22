<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"  style="height: 100%">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
			        <!-- baidu Map api -->
<script type="text/javascript" src="https://api.map.baidu.com/api?v=1.2&ak=gypEU5ByeoXGYzp6aIDuZLFGymzkoOct"></script>
</head>

	   <body style="height: 100%; margin: 0">
	   <div >
	   	<h1 align="center">公司收入统计柱状图</h1>
	   </div>	
       <div id="container" style="height: 90%"></div>
       <script type="text/javascript" src="js/echarts.min.js"></script>
       <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts-gl/dist/echarts-gl.min.js"></script>
       <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts-stat/dist/ecStat.min.js"></script>
       <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/extension/dataTool.min.js"></script>
       <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/map/js/china.js"></script>
       <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/map/js/world.js"></script>
       <script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=xfhhaTThl11qYVrqLZii6w8qE5ggnhrY&__ec_v__=20190126"></script>
       <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/extension/bmap.min.js"></script>
       <script type="text/javascript" src="js/jquery.js"></script>
       <script type="text/javascript">
       $(function(){
    	   //发送ajax请求
    	   $.ajax({
    		   url:"InOutServlet?method=getBarData",
    		   success:function(data){
    			   //json String -> json obj
    			   eval("var arr="+data);//arr=[['a', 'b', 'c', 'd', 'e', 'f', 'g'],[120, 200, 150, 80, 70, 110, 130]]
    			  // alert(arr);
    			 //初始化，获取div
    	    	   var dom = document.getElementById("container");
    				var myChart = echarts.init(dom);
    				var app = {};
    				//
    				option = null;
    				option = {
    					/* title:{
    							text:'尚学堂收入统计图表'
    						}, */
    				    xAxis: {
    				        type: 'category',
    				        data: arr[0]	
    				    },
    				    yAxis: {
    				        type: 'value'
    				    },
    				    series: [{
    				        data: arr[1],	
    				        type: 'bar'
    				    }]
    				};
    				//
    				if (option && typeof option === "object") {
    				    myChart.setOption(option, true);
    				} 
    		   }
    	   });
    	    
       })
			
	   </script>
			       
   </body>
</html>
