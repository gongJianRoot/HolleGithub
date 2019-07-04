<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>显示话剧信息页面</title>
<style type="text/css">
h4 {
	color: blue;
}

.tablelist {
	border: 1px solid;
	border-collapse: collapse;
}

.tablelist tr, td {
	border: 1px solid;
}

.firsttr {
	background-color: lightblue;
}

h3 {
	color: red;
}
</style>
<!-- 引入 echarts.js -->
<script src="JS/echarts.js" type="text/javascript"></script>
<script src="JS/jquery-3.3.1.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btn1").click(function() {
			showEcharts();
		});
	});
</script>
</head>
<body>
	<h4>登陆成功，欢迎${userName }</h4>
	<!-- 查询所有剧目信息，显示当前页 -->
	<form action="" method="post">
		<table class="tablelist" align="left">
			<tr class="firsttr">
				<td>序号</td>
				<td>话剧id</td>
				<td>话剧名称</td>
				<td>剧场名称</td>
				<td>票价</td>
				<td>话剧介绍</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${dramaList }" var="dra" varStatus="vs">
				<tr
					<c:if test="${vs.index%2==1 }">
		style="background-color: #cccccc"</c:if>>
					<td>${vs.index+1 }</td>
					<td>${dra.dramaId }</td>
					<td>${dra.dramaName }</td>
					<td>${dra.theaterName }</td>
					<td>${dra.ticketPrice }</td>
					<td>${dra.dramaIntro }</td>
					<td><a href="deleteOneDrama.action?dramaId=${dra.dramaId }">删除</a>
						<a href="#">修改</a></td>
				</tr>
			</c:forEach>
		</table>
		<table class="tablelist" align="left">
			<tr class="firsttr">
				<td>平均得分</td>
			</tr>
			<c:forEach items="${scoreInfoVoList }" var="score">
				<tr>
					<td>${score.scoreAvg }</td>
				</tr>
			</c:forEach>
		</table>
		<input type="button" id="btn1" name="btn1" value="我要看统计图" />
		<input type="button" onclick="exportExcel_DramasScore()" value="导出评分表" />
	</form>
	<form action="addDramas.jsp" method="post">
		<input type="submit" id="submit" name="submit" value="我要添加剧目" />
	</form>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="main" style="width: 1550px; height: 260px;"></div>
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));
		// 指定图表的配置项和数据
		var option = {
			title : {
				text : 'Drama_评分详细'
			},
			tooltip : {},
			legend : {
				data : [ '评分' ]
			},
			xAxis : {
				data : []
			},
			yAxis : {},
			series : [ {
				name : '最终评分',
				type : 'bar',
				data : []
			} ]
		};
		/* 显示话剧评分柱状图的方法 */
		function showEcharts() {
			$.ajax({
				url : "getDramaScore_Echarts.action",
				type : "post",
				success : function(xy, zt, dx) {
					option.xAxis.data = xy.dramaName;
					option.series[0].data = xy.dramaScore;
					myChart.setOption(option);
				}
			});
		}
		
		/* 导出所有话剧信息的评分Excel表格的方法 */
		function exportExcel_DramasScore(){
			$.ajax({
				url : "ExportExcel_DramasScoreServlet.do",
				type : "post",
				success : function(xy, zt, dx) {
					alert("导出成功！！！");
				},
				eroor : function(xy){
					alert(xy);
					alert("该表格已经成功导出啦！");
				}
			});
		}
	</script>
</body>
</html>