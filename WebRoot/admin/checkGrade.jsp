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

<body>
	<h4 align="center">成绩列表</h4>
	<hr>
	<c:if test="${empty gList }">
  		尚未上传成绩！
  	</c:if>
	<c:if test="${!empty gList}">
		<table class="table table-striped" align="center">
			<thead>
				<tr align="center">
					<td width="20%">学号</td>
					<td>作业题目</td>
					<td>分数</td>
					<td>评语</td>
					<td>老师</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<%
					int sum = 0;
				%>
				<c:forEach items="${gList}" var="c">
					<tr height="5" align="center">
						<%
							sum++;
						%>
						<td>${c.userId }</td>
						<td>${c.workTitle }</td>
						<td>${c.score }</td>
						<td>${c.remark }</td>
						<td>${c.teacherName }</td>
						<td><a href="#">无</a>
					</tr>
				</c:forEach>
				<tr align="center">
					<td colspan="5">总计发现：<%=sum%>条成绩记录
					</td>
					<td align="center">
						<form
							action="${pageContext.request.contextPath}/servlet/GradeServlet?operation=ExportExcel"
							method="post" name="useradd">
							<input type="hidden" name="op" value="admin"> <br> <input
								type="submit" class="btn btn-primary" value="导出成绩单">
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</c:if>
</body>
</html>
