<%@ page language="java" import="java.util.*,java.text.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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

<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" />
<script language="JavaScript"
	src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/cloud.js"
	type="text/javascript"></script>
<script language="javascript">
	$(function() {
		$('.loginbox').css({
			'position' : 'absolute',
			'left' : ($(window).width() - 692) / 2
		});
		$(window).resize(function() {
			$('.loginbox').css({
				'position' : 'absolute',
				'left' : ($(window).width() - 692) / 2
			});
		})
	});
</script>
</head>

<body
	style="background-color: #1c77ac; background-image: url(../images/light.png); background-repeat: no-repeat; background-position: center top; overflow: hidden;">

	<div class="logintop">
		<span>欢迎登录实验报告管理教学平台! &nbsp;&nbsp; <%
 	Date date = new Date();
 	SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 %> <%=time.format(date)%>
		</span>
	</div>

	<div id="mainBody">
		<div id="cloud1" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
	</div>

	<div
		style="border: solid 1px white; width: 400px; margin-left: 40%; margin-top: 200px">
		<form
			action="${pageContext.request.contextPath}/servlet/UserServlet?operation=login"
			method="post">
			<table align="center" border="0">
				<tr height="50">
					<td colspan="3" align="center"><font size="6" color="white">用户登录</font>
						<br></td>
				</tr>
				<tr height="40">
					<td>用户名:</td>
					<td><input type="text" class="form-control" name="userID" /></td>
					<td>
						<font size="3" color="white">&nbsp;&nbsp;学号或教师编号</font></td>
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
					<td>密&nbsp;码:</td>
					<td><input type="text" class="form-control" name="password" /></td>
					<td><a href="/TeachingAssistant/common/register.jsp"><font
							size="3" color="white">&nbsp;&nbsp;还没有账户？</font></a></td>
				</tr>
				<tr height="40">
					<td><input type="submit" class="btn btn-default" value="登录" /></td>
					
					<td colspan="2" align="center"><a
						href="/TeachingAssistant/common/findPWD.jsp">找回密码</a></td>
				</tr>

			</table>
		</form>
		${message}
	</div>
	<div class="loginbm">版权所有 @李吉波 2018</div>
</body>
</html>
