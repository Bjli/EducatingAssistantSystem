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
	<h4 align="center">所有文件列表</h4>
	<hr>
	<c:if test="${empty fList }">
  		没有文件！
  	</c:if>
	<c:if test="${!empty fList}">
		<div class="table-responsive">
			<table class="table table-striped" align="center">
				<thead>
					<tr>
						<td>文件名</td>
						<td>上传日期</td>
						<td>上传者</td>
						<td>文件描述</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${fList}" var="c">
						<tr>
							<td>${c.name }</td>
							<td>${c.uploadTime }</td>
							<td>${c.uploader }</td>
							<td>${c.description }</td>
							<td><a
								href="javascript:if(confirm('确定要删除吗?'))window.location.href='${pageContext.request.contextPath}/servlet/FileServlet?operation=deleteFile&id=${c.id}'">删除</a>
								<a
								href="${pageContext.request.contextPath}/servlet/FileServlet?operation=downloadFile&id=${c.id}&name=${c.name}">下载</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:if>
</body>
</html>
