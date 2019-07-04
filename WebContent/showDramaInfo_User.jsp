<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>显示话剧信息页面</title>
</head>
<script src="JS/echarts.js" type="text/javascript"></script>
<script src="JS/jquery-3.3.1.js" type="text/javascript"></script>
<style type="text/css">
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

a:hover {
	pointer-events: painted;
}
</style>
<!-- 引入 echarts.js -->

<script type="text/javascript">
	
</script>
<body onload="showEcharts()">
	登陆成功，欢迎${userName }&nbsp;&nbsp;&nbsp;&nbsp;id为${userId }
	<!-- 查询所有剧目信息，显示当前页 -->
	<form action="">
		<table class="tablelist" align="left">
			<tr class="tablelist">
				<td>序号</td>
				<td>话剧id</td>
				<td>话剧名称</td>
				<td>剧场名称</td>
				<td>票价</td>
				<td>话剧介绍</td>
				<td>我要评分</td>
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
					<td><a
						href="addScoreInfo.action?userId=${userId }&dramaId=${dra.dramaId }&score=1">1</a>&nbsp;&nbsp;
						<a
						href="addScoreInfo.action?userId=${userId }&dramaId=${dra.dramaId }&score=2">2</a>&nbsp;&nbsp;
						<a
						href="addScoreInfo.action?userId=${userId }&dramaId=${dra.dramaId }&score=3">3</a>&nbsp;&nbsp;
						<a
						href="addScoreInfo.action?userId=${userId }&dramaId=${dra.dramaId }&score=4">4</a></td>
				</tr>
			</c:forEach>
		</table>
		<table class="tablelist" align="left">
			<tr class="tablelist">
				<td>平均得分</td>
			</tr>
			<c:forEach items="${scoreInfoVoList }" var="score">
				<tr>
					<td>${score.scoreAvg }</td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />

	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="main" style="width: 1550px; height: 300px;"></div>
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
	</script>
</body>
</html>