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
</head>
<body onload="goPage(1,9);">
	<h3 align="center">我的所授科目</h3>
	<hr>
	<c:if test="${empty cList }">
  		暂时没有录入所授科目信息！
  	</c:if>
	<c:if test="${!empty cList}">
		<div class="table-responsive">
			<table class="table table-striped" align="center">
				<thead>
					<tr align="center">
						<td>科目名称</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody id="Tbody">
					<c:forEach items="${cList}" var="c">
						<tr align="center">
							<td>${c.courseName }</td>
							<td><a href="javascript:if(confirm('确定要删除吗?此操作将删除与该科目相关的作业&成绩!!'))window.location.href='${pageContext.request.contextPath}/servlet/UserServlet?operation=deleteCourse&courseId=${c.courseId}'">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<%@ include file="../../common/page.jsp"%>
		</div>
	</c:if>
</body>
</html>
