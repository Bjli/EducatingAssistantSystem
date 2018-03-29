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
<style type="text/css">
.connect-box {
	width: 90%;
	margin-left: 5%;
}
</style>
<title>查看自己作业</title>
</head>
<body onload="goPage(1,9);">
	<h4 align="center">作业列表</h4>
	<hr>
	<div class="connect-box">
		<c:if test="${empty nList }">
  		未提交过作业！
  	</c:if>
		<c:if test="${!empty nList}">
			<table class="table table-hover" align="center">
				<thead>
					<tr>
						<td width="25%">题目要求</td>
						<td width="15%">发布者</td>
						<td>我的作答</td>
						<td width="20%">完成时间</td>
						<td>状态</td>
					</tr>
				</thead>
				<tbody id="Tbody">
					<c:forEach items="${nList}" var="c">
						<tr>
							<td><a
								href="${pageContext.request.contextPath}/servlet/NoticeServlet?operation=getNotice&id=${c.workid}">${c.worktitle}</a></td>
							<td>${c.workuser }</td>
							<td><a
								href="${pageContext.request.contextPath}/servlet/AnswerServlet?operation=getAnswer&id=${c.answerid}">点击查看作答详情</a>
							<td>${c.date }</td>
							<td>${c.state}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<%@ include file="../../common/page.jsp"%>
		</c:if>
	</div>
</body>
</html>