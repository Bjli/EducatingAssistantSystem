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
.notice-box {
	width: 90%;
	margin-left: 5%;
}
</style>
</head>

<body>
	<h4 align="center">通知列表</h4>
	<hr>
	<div class="notice-box">
		<c:if test="${empty nList }">
  		尚未发布通知！
  	</c:if>
		<c:if test="${!empty nList}">

			<table class="table table-striped" align="center">
				<thead>
					<tr align="center">
						<td width="40%">标题</td>
						<td>发布者</td>
						<td>发布时间</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${nList}" var="c">
						<tr align="center">
							<td><a
								href="${pageContext.request.contextPath}/servlet/NoticeServlet?operation=getNotice&id=${c.id}">${c.title }</a></td>
							<td>${c.author }</td>
							<td>${c.releaseDate }</td>
							<td><a
								href="javascript:if(confirm('确定要删除吗?'))window.location.href='${pageContext.request.contextPath}/servlet/NoticeServlet?operation=deleteNotice&id=${c.id}'">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>

</body>
</html>
