<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<h4 align="center">上传全站资源</h4>
	<hr>
	<form action="${pageContext.request.contextPath}/servlet/FileServlet"
		method="post" enctype="multipart/form-data">
		<table align="center" border="1" width="80%">
			<tr>
				<td>上传者:</td>
				<td><input type="text" name="uploader" /></td>
			</tr>
			<tr>
				<td>上传时间:</td>
				<td><input type="date" name="uploadTime" /> <font size="2"
					color="red">若手动输入，格式为：yyyy-MM-dd，例如2017-02-02</font></td>
			</tr>
			<tr>
				<td>文件描述:&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><textarea name="description"
						style="width: 300px; height: 60px;"></textarea> <font size="2"
					color="red">最多可输入100字</font></td>
			</tr>
			<tr>
				<td>选择文件:</td>
				<td><input type="file" name="file" /><font size="2"
					color="red">文件大小不超过50M</font></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" class="btn btn-default" value="上传" />
				</td>
			</tr>
		</table>
	</form>
	${message}
</body>
</html>
