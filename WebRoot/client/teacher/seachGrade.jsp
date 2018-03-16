<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>成绩检索</title>
<style type="text/css">
input {
	border: 1px solid #ccc;
	padding: 7px 0px;
	border-radius: 3px;
	padding-left: 5px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
}

input:focus {
	border-color: #66afe9;
	outline: 0;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
		rgba(102, 175, 233, .6);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
		rgba(102, 175, 233, .6)
}

.seachBox {
	padding: 10px 0px;
	padding-left:25%;
	align:center;
}
</style>
<script type="text/javascript">
function Check_form()  
{    
    if ( document.getElementById("condition").value==""||document.getElementById("condition")==null)   
    {  
        alert('请输入学号或作业标题!');   
        return false;  
    }
        return true;
}
</script>
</head>
<body>
	<h3 align="center">成绩检索</h3>
	<hr>
	<div class="seachBox">
		<form
			action="${pageContext.request.contextPath}/servlet/GradeServlet?operation=getStuGrade"
			onsubmit="return Check_form()" method="post">
			选择对应的检索类型：<input type="radio" name="ways" value="Uid" checked>学号
			<input type="radio" name="ways" value="Title">作业标题 <br>
			<br> 请输入学号或作业标题：<input type="text" name="condition" id="condition">
			<input type="submit" value="检索" style="width: 150px; height: 35px">
		</form>
	</div>

</body>
</html>