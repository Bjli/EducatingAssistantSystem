<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap-3.3.7/css/bootstrap.min.css">
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.7/jquery-3.3.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.7/js/bootstrap.min.js"></script>

<style type="text/css">
.gradeList {
	padding: 10px 10px;
}
</style>
</head>
<body>
	<h4 align="center">成绩列表</h4>
	<hr>
	<div class="gradeList">
		<c:if test="${empty grade }">
  		未发现相关成绩！
  	</c:if>
		<c:if test="${!empty grade}">
			<table class="table" align="center">
				<thead>
					<tr align="center">
						<td width="25%">学号</td>
						<td>姓名</td>
						<td>作业名称</td>
						<td>分数</td>
						<td width="25%">评语</td>
					</tr>
				</thead>
				<tbody>
					<%
						int sum = 0;
					%>
					<c:forEach items="${grade}" var="c">
						<c:if test="${c.score < 60}">
							<tr class="danger" align="center">
								<td>${c.userId }</td>
								<td>${c.userName }</td>
								<%
									sum++;
								%>
								<td>${c.workTitle }</td>
								<td>${c.score}</td>
								<td>${c.remark}</td>
							</tr>
						</c:if>
						<c:if test="${c.score >= 85}">
							<tr class="success" align="center">
								<td>${c.userId }</td>
								<td>${c.userName }</td>
								<%
									sum++;
								%>
								<td>${c.workTitle }</td>
								<td>${c.score}</td>
								<td>${c.remark}</td>
							</tr>
						</c:if>
						<c:if test="${c.score >= 60 && c.score <85}">
							<tr class="warning" align="center">
								<td>${c.userId }</td>
								<td>${c.userName }</td>
								<%
									sum++;
								%>
								<td>${c.workTitle }</td>
								<td>${c.score}</td>
								<td>${c.remark}</td>
							</tr>
						</c:if>
					</c:forEach>
					<tr align="center">
						<td colspan="4">总计：<%=sum%>次成绩记录
						</td>
						<td align="center">
							<form
								action="${pageContext.request.contextPath}/servlet/GradeServlet?operation=ExportExcel"
								method="post" name="useradd">
								<input type="hidden" name="ways" value="${ways}"> <input
									type="hidden" name="op" value="teacher"> <input
									type="hidden" name="condition" value="${condition}"> <br>
								<input type="submit" class="btn btn-primary" value="导出成绩单">
							</form>
						</td>
					</tr>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>
