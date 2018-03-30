<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap-3.3.7/css/bootstrap.min.css">
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.7/jquery-3.3.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript">
	function Check_form() {
		if (document.getElementById("pwd1").value == ""
				|| document.getElementById("pwd1") == null) {
			alert('请输入密码');
			return false;
		}
		if (document.getElementById("pwd1").value !== document.getElementById("pwd2").value) {
			alert('两次密码输入不一致！');
			return false;
		}
		return true;
	}
</script>

</head>
<body>
<h4 align="center">修改密码</h4>
<hr>
	<div
		style="border: solid 1px black; width: 500px; margin-left: 25%">
		<form
			action="${pageContext.request.contextPath}/servlet/UserServlet?operation=modifyPWD"
			onsubmit="return Check_form()" method="post">
			<table align="center" border="0">
			
				<tr height="40">
					<td>用户编号:</td>
					<td>${sessionScope.userID}</td>
				</tr>
				<tr height="40">
					<td>用户类型:</td>
					<td>${sessionScope.userType}</td>
				</tr>
				<tr height="40">
					<td>新密码:</td>
					<td><input type="password" class="form-control" name="password" id="pwd1" />
					</td>
				</tr>
				<tr height="40">
					<td>确认密码:</td>
					<td><input type="password" class="form-control" name="password2" id="pwd2" />
					</td>
				</tr>
				<tr height="40">
					<td colspan="2" align="center"><input type="submit"
					class="btn btn-default"	value="修改密码" /></td>
				</tr>
			</table>
		</form>
	</div>
	${message}
</body>
</html>
