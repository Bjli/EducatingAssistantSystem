<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body>
	<h4 align="center">个人成绩</h4>
	<hr>
	<c:if test="${empty Grade }">
  		成绩还未发布！
  	</c:if>
	<c:if test="${!empty Grade}">
		<table border="1" width="80%"  align="center">
			<tr height="25">
				<td>作业题目</td>
				<td>成绩</td>
				<td>老师</td>
				<td>评语</td>
			</tr>
			<%int sum=0; %>
			<c:forEach items="${Grade}" var="c">
				<tr>
					<td>${c.workTitle }</td>
					<%sum++; %>
					<td>${c.score }</td>
					<td>${c.teacherName }</td>
					<td>${c.remark }</td>
				</tr>
			</c:forEach>
			<tr align="right">
			<td colspan="4">总计发现:<%=sum %>次成绩记录</td>
			</tr>
		</table>
	</c:if>

</body>
</html>
