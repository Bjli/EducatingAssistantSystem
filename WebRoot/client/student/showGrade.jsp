<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap-3.3.7/css/bootstrap.min.css">
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.7/jquery-3.3.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
.connect-box {
width:90%;
margin-left:5%;
}
</style>
</head>
<body>
	<h4 align="center">个人成绩</h4>
	<hr>
	<div class="connect-box">
		<c:if test="${empty Grade }">
  		成绩还未发布！
  	</c:if>
		<c:if test="${!empty Grade}">
			<table class="table table-bordered" align="center">
				<thead>
					<tr height="25">
						<td>作业题目</td>
						<td>成绩</td>
						<td>老师</td>
						<td>评语</td>
					</tr>
				</thead>
				<tbody>
					<%
						int sum = 0;
					%>
					<c:forEach items="${Grade}" var="c">
						<tr>
							<td>${c.workTitle }</td>
							<%
								sum++;
							%>
							<td>${c.score }</td>
							<td>${c.teacherName }</td>
							<td>${c.remark }</td>
						</tr>
					</c:forEach>
					<tr align="right">
						<td colspan="4">总计发现:<%=sum%>次成绩记录
						</td>
					</tr>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>
