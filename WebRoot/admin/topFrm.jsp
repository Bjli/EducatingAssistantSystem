<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<style type="text/css">
.topleft img {
	margin-top: -15px;
	margin-left: 10px;
}

.topright {
	height: 88px;
	background: url(../images/topright.jpg) no-repeat right;
	float: right;
	padding-top: 15px;
}
</style>
</head>

<body style="background-color: #066CAD;">

	<div class="topleft">
		<div style="margin-left: 80%; margin-top: 25px; color: white">您好,${sessionScope.userType}:${sessionScope.userName}</div>
		<img src="../images/logo.png" />
		<div style="margin-left: 40%;margin-top:-35px;">
			<font size="6" face="STXingkai" color="white">实验报告教学管理系统</font>
		</div>
	</div>
</body>
</html>
