<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<!-- 引入 echarts.js -->
<script src="JS/echarts.js" type="text/javascript"></script>
<script src="JS/jquery-3.3.1.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#inp").blur(function() {
			registerByName();
		});

	});

	function registerByName() {
		var name = $("#inp").val();
		$.ajax({
			url : "registerByName.action",
			type : "post",
			data : {userName:name},
			success : function(xy,zt,dx) {
				if(xy!=null){
					$("#registerDiv").html("该用户名已经被占用！！！");
				}
				if(xy==""){
					$("#registerDiv").html("该用户名可以使用！！！");
				}
			}
		});
	}
</script>
</head>
<body>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="main" style="width: 400px; height: 260px;"></div>
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));
		// 指定图表的配置项和数据
		var option = {
			title : {
				text : 'Drama_剧目评分详细'
			},
			tooltip : {},
			legend : {
				data : [ '评分' ]
			},
			xAxis : {
				data : [ "衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子" ]
			},
			yAxis : {},
			series : [ {
				name : '销量',
				type : 'bar',
				data : [ 4.0, 2.8, 3.6, 1.9, 1.2, 2.5 ]
			} ]
		};
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	</script>
</body>
</html>