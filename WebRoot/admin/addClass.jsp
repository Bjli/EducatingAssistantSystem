<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>班级注册</title>
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

.Box {
	padding: 10px 0px;
	padding-left: 33%;
	align: center;
}
</style>
<script type="text/javascript">
	function Check_form() {
		if (document.getElementById("className").value == ""
				|| document.getElementById("className") == null) {
			alert('请输入班级名称');
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<h3 align="center">班级注册</h3>
	<hr>
	<div class="Box">
		<form
			action="${pageContext.request.contextPath}/servlet/UserServlet?operation=addClass"
			onsubmit="return Check_form()" method="post">
			请输入班级名称：<input type="text" name="className" id="className"> <input
				type="submit" value="注册" style="width: 150px; height: 35px">
		</form>
	</div>
	${message}
</body>
</html>