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

</head>
<body>
	<h4 align="center">添加用户</h4>
	<hr>
	<div style="border: solid 1px black; width: 500px; margin-left: 300px">
		<form
			action="${pageContext.request.contextPath}/servlet/UserServlet?operation=addUser"
			method="post" name="useradd">
			<br>
			<table align="center" border="0">
				<tr height="40">
					<td>用户编号:</td>
					<td><input type="text" class="form-control" name="userID" /></td>
				</tr>
				<tr height="40">
					<td>用户名:</td>
					<td><input type="text" class="form-control" name="userName" /></td>
				</tr>
				<tr height="40">
					<td>用户类型:</td>
					<td><select name="userType">
							<option value="管理员">管理员</option>
							<option value="教师">教师</option>
							<option value="学生">学生</option>
					</select></td>
				</tr>
				<tr height="40">
					<td>密&nbsp;&nbsp;码:</td>
					<td><input type="text" class="form-control" name="password" /></td>
				</tr>
				<tr height="40">
					<td>电话号码:&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><input type="text" class="form-control" name="phoneNumber"
						id="phone" /></td>
				</tr>
				<tr height="40">
					<td>邮&nbsp;&nbsp;箱:</td>
					<td><input type="text" class="form-control" name="email"
						id="email" /></td>
				</tr>
				<tr height="40">
					<td align="center"><input type="button"
						class="btn btn-default" onClick="return test()" value="提交" /></td>
					<td align="center"><input type="reset" class="btn btn-default"
						value="重置" /></td>
				</tr>

			</table>
		</form>
	</div>
	${message}
</body>
<script>
	function test() {
		var temp = document.getElementById("email");
		var temp1 = document.getElementById("phone");
		//对电话的验证
		var mobile = /^(13+\d{9})|(15+\d{9})|(17+\d{9})|(18+\d{9})$/;
		//对电子邮件的验证
		var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;

		if (!mobile.test(temp1.value)) {
			alert('提示\n\n请输入有效的手机号码！');
			mobile.focus();
			return false;
		} else if (!myreg.test(temp.value)) {
			alert('提示\n\n请输入有效的E_mail！');
			myreg.focus();
			return false;
		}
		useradd.submit();
	}
</script>
</html>
