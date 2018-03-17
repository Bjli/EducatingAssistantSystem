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
	<h4 align="center">成绩列表</h4>
	<hr>
	<c:if test="${empty tList }">
  		尚未上传成绩！
  	</c:if>
	<c:if test="${!empty tList}">
		<table border="1" width="60%" align="center">
			<tr align="center">
				<td width="25%">学号</td>
				<td>姓名</td>
				<td>作业名称</td>
				<td>分数</td>
				<td width="25%">评语</td>
			</tr>
			<c:forEach items="${tList}" var="c">
				<tr height="15" align="center">
					<td>${c.userId }</td>
					<td>${c.userName }</td>
					<td>${c.workTitle }</td>
					<td>${c.score }</td>
					<td>${c.remark}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>
