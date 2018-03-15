<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>作答详情</title>
<style type="text/css">
.answer {
	whith: 80%;
	background: white;
}
 p{text-indent: 2em;}
</style>
</head>
<body>
	<div class="answer">
		<h2 align="center">${answerinfo.worktitle}</h2>
		<h4 align="left">解：</h4>
		<p>${answerinfo.content}</p>
	</div>
</body>
</html>