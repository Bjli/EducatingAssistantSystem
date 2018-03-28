<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<style type="text/css">
.connect {
	margin-left: 5%;
	width: 90%;
}
</style>
</head>

<body>
	<h4 align="center">所有用户列表</h4>
	<hr>
	<div class="connect">
		<c:if test="${empty uList }">
  		没有录入用户！
  	</c:if>
		<c:if test="${!empty uList}">
			<table class="table table-hover" align="center">
				<thead>
					<tr align="center">
						<td>用户编号</td>
						<td>姓名</td>
						<td>用户类型</td>
						<td>密码（已加密存储）</td>
						<td>电话号码</td>
						<td>邮箱</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${uList}" var="c">
						<tr>
							<td>${c.userID }</td>
							<td>${c.userName }</td>
							<td>${c.userType }</td>
							<td>${c.password }</td>
							<td>${c.phoneNumber }</td>
							<td>${c.email }</td>
							<td><a
								href="javascript:if(confirm('确定要删除吗?'))window.location.href='${pageContext.request.contextPath}/servlet/UserServlet?operation=deleteUser&userID=${c.userID}'">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>
