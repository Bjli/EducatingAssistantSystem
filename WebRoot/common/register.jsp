<%@ page language="java" import="java.util.*,java.text.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
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
<style type="text/css">
input {
	border: 1px solid #ccc;
	padding: 7px 0px;
	border-radius: 3px;
	padding-left: 5px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
}

input:focus {
	border-color: #66afe9;
	outline: 0;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
		rgba(102, 175, 233, .6);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
		rgba(102, 175, 233, .6)
}
</style>
</head>

<body
	style="background-color: #1c77ac; background-image: url(../images/light.png); background-repeat: no-repeat; background-position: center top; overflow: hidden;">

	<div class="logintop">
		<span>欢迎注册实验报告管理教学平台! &nbsp;&nbsp; <%
 	Date date = new Date();
 	SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 %> <%=time.format(date)%></span>
	</div>

	<div id="mainBody">
		<div id="cloud1" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
	</div>

	<div
		style="border: solid 1px white; width: 550px; margin-left: 30%; margin-top: 120px">

		<br>
		<form
			action="${pageContext.request.contextPath}/servlet/UserServlet?operation=addUser"
			method="post" name="useradd">
			<table align="center" border="0">
				<tr height="50px">
					<td colspan="2" align="center"><font size="5" color="white">注册账户</font></td>
				</tr>
				<tr height="40">
					<td>用户编号:</td>
					<td><input type="text" name="userID" /> <font size="3"
						color="red">学号或教师编号</font></td>
				</tr>
				<tr height="40">
					<td>用户名:</td>
					<td><input type="text" name="userName" /></td>
				</tr>
				<tr height="40">
					<td>用户类型:</td>
					<td><input type="radio" name="userType" value="教师" checked>教师
						<input type="radio" name="userType" value="学生">学生</td>
				</tr>
				<tr height="40">
					<td>注册班级:</td>
					<td><select name="className" class="form-control">
							<option value="无">教师选项</option>
							<c:forEach items="${cList}" var="c">
								<option value="${c.className }">${c.className }</option>
							</c:forEach>
					</select> </td>
				</tr>
				<tr height="40">
					<td>密&nbsp;&nbsp;码:</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr height="40">
					<td>电话号码:&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><input type="text" name="phoneNumber" id="phone" /></td>
				</tr>
				<tr height="40">
					<td>邮&nbsp;&nbsp;箱:</td>
					<td><input type="text" name="email" id="email" /></td>
				</tr>
				<tr height="40">
					<td align="center"><input type="button"
						onClick="return test()" value="提交"
						Style="width: 100px;height=20px" /></td>
					<td align="right"><input type="reset" value="重置"
						Style="width: 100px;height=20px" /></td>
				</tr>
			</table>
		</form>
		${message}
	</div>
	<div class="loginbm">版权所有 @李吉波 2018</div>
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
