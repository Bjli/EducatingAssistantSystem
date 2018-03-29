<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/page.css">
<script src="${pageContext.request.contextPath}/js/page.js"></script>
<title>分页</title>
</head>
<body>
	<div id="barcon" class="barcon">
		<div id="barcon1" class="barcon1"></div>
		<div id="barcon2" class="barcon2">
			<ul>
				<li><a href="#" id="firstPage">首页</a></li>
				<li><a href="#" id="prePage">上一页</a></li>
				<li><a href="#" id="nextPage">下一页</a></li>
				<li><a href="#" id="lastPage">尾页</a></li>
				<li><select id="jumpWhere">
				</select></li>
				<li><a href="#" id="jumpPage" onclick="jumpPage()">跳转</a></li>
			</ul>
		</div>
	</div>
</body>
</html>