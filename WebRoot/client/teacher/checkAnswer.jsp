<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看自己作业</title>
</head>
<body>
	<h4 align="center">作业列表</h4>
	<hr>
	<c:if test="${empty nList }">
  		没有人提交过作业！
  	</c:if>
	<c:if test="${!empty nList}">
		<table border="1" width="85%" align="center">
			<tr>
				<td width="40%">题目要求</td>
				<td width="15%">学号</td>
				<td width="20%">完成时间</td>
				<td>状态</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${nList}" var="c">
				<tr>
					<td><a
						href="${pageContext.request.contextPath}/servlet/NoticeServlet?operation=getNotice&id=${c.workid}">${c.worktitle}</a></td>
					<td>${c.userid}</td>
					<td>${c.date }</td>
					<td>${c.state}</td>
					<c:if test="${c.state =='已批改'}">
						<td><a href="javascript:if(confirm('确定要删除吗?'))window.location.href='${pageContext.request.contextPath}/servlet/GradeServlet?operation=deleteGrade&workId=${c.workid}&userId=${c.userid}'">删除成绩</a></td>
					</c:if>
					<c:if test="${c.state =='已提交'}">
						<td><a
						href="${pageContext.request.contextPath}/servlet/AnswerServlet?operation=getAnswer&id=${c.answerid}">批改</a></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>