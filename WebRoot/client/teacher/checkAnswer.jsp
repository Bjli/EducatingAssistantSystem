<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap-3.3.7/css/bootstrap.min.css">
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.7/jquery-3.3.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<title>查看学生作业</title>
<style type="text/css">
.answer-box {
	margin-left: 5%;
	width: 90%;
}
</style>
</head>
<body onload="goPage(1,9);">
	<h4 align="center">作业列表</h4>
	<hr>
	<div class="answer-box">
		<c:if test="${empty nList }">
  		没有人提交过作业！
  	</c:if>
		<c:if test="${!empty nList}">
			<table class="table" align="center">
				<thead>
					<tr>
						<td>题目要求</td>
						<td>学号</td>
						<td>姓名</td>
						<td>完成时间</td>
						<td>状态</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody id="Tbody">
					<c:forEach items="${nList}" var="c">
						<c:if test="${c.state =='已批改'}">
							<tr class="success">
								<td><a
									href="${pageContext.request.contextPath}/servlet/NoticeServlet?operation=getNotice&id=${c.workid}">${c.worktitle}</a></td>
								<td>${c.userid}</td>
								<td>${c.username}</td>
								<td>${c.date }</td>
								<td>${c.state}</td>
								<td><a
									href="javascript:if(confirm('确定要删除吗?'))window.location.href='${pageContext.request.contextPath}/servlet/GradeServlet?operation=deleteGrade&workId=${c.workid}&userId=${c.userid}'">删除成绩</a></td>
							</tr>
						</c:if>
						<c:if test="${c.state =='已提交'}">
							<tr class="warning">
								<td><a
									href="${pageContext.request.contextPath}/servlet/NoticeServlet?operation=getNotice&id=${c.workid}">${c.worktitle}</a></td>
								<td>${c.userid}</td>
								<td>${c.username}</td>
								<td>${c.date }</td>
								<td>${c.state}</td>
								<td><a
									href="${pageContext.request.contextPath}/servlet/AnswerServlet?operation=getAnswer&id=${c.answerid}">批改</a></td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
			<%@ include file="../../common/page.jsp"%>
		</c:if>
	</div>

</body>
</html>