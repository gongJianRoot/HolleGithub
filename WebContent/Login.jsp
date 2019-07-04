<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>登录页面</title>
</head>

<body>
	<form action="login.action" method="post">
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="userName" value="root" /></td>
			</tr>
			<tr>
				<td>密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
				<td><input type="text" name="userPwd" value="root" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="submit" value="登录" /> <input
					type="reset" name="reset" value="取消" /></td>
			</tr>
		</table>
	</form>
	
	<form action="register.jsp" method="post">
		<input type="submit" id="register" name="register" value="注册" />
	</form>
</body>
</html>