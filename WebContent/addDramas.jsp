<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>添加剧目信息页面</title>
</head>
<style type="text/css">
h3 {
	color: red;
}
</style>
<body>
<!-- 添加剧场信息表单 -->
	<form action="addOneDrama.action" method="post">
	<h3>请添加剧目信息：</h3>
		<table>
			<tr>
				<td>话剧名称：</td>
				<td><input type="text" id="dramaName" name="dramaName"/></td>
			</tr>
			<tr>
				<td>剧场名称：</td>
				<td><input type="text" id="theaterName" name="theaterName"/>(元)</td>
			</tr>
			<tr>
				<td>演出价格：</td>
				<td><input type="text" id="ticketPrice" name="ticketPrice"/></td>
			</tr>
			<tr>
				<td>剧情简介：</td>
				<td><textarea id="dramaIntro" name="dramaIntro" style="width: 150px;height: 100px;"></textarea></td>
			</tr>
		</table>
		<input type="submit" id="submit" name="submit" value="完成"/>
		<input type="reset" id="reset" name="reset" value="重置"/>
	</form>
</body>
</html>