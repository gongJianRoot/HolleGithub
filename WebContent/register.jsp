<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>注册页面</title>
<style type="text/css">
h3 {
	color: red;
}
</style>
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
				alert(xy);
				if(xy!=null){
					$("#registerDiv").html("该用户名已经被占用！！！");
					$("#inp1").attr("disabled",true);
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
	<form action="register.action" method="get">
		<h3>请注册：</h3>
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="userName" /></td>
				<td><div id="registerDiv"></div></td>
			</tr>
			<tr>
				<td>密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
				<td><input type="text" name="userPwd" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" id="inp1" name="submit" value="完成" /> <input
					type="reset" name="reset" value="取消" /></td>
			</tr>
		</table>
	</form>
</body>
</html>