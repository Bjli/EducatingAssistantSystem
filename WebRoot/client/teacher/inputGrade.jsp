<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<style type="text/css">
.answer {
	background: white;
	whith: 80%;
}

.inputGrade {
	background: #EDF6FA;
	padding: 10px 15px;
}

p {
	text-indent: 2em;
}

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
</style>
<script type="text/javascript">
function Check_form()  
{    
    if ( document.getElementById("score").value==""||document.getElementById("score")==null)   
    {  
        alert('请输入成绩!');   
        return false;  
    }
        return true;
}
</script>
</head>
<body>
	<div class="answer">
		<h2 align="center">${answerinfo.worktitle}</h2>
		<h4 align="left">解：</h4>
		<p>${answerinfo.content}</p>
		<br> <br>
	</div>
	<div class="inputGrade">
		<hr>
		<h4 align="center">录入成绩</h4>
		<form
			action="${pageContext.request.contextPath}/servlet/GradeServlet?operation=inputGrade&workTitle=${answerinfo.worktitle}&userId=${answerinfo.userid}&workId=${answerinfo.workid}&userName=${answerinfo.username}" onsubmit="return Check_form()" method="post">
			<table align="center">
				<tr>
					<td>成绩：</td>
					<td><input type="number" name="score" id="score" min="0" max="100" /></td>
				</tr>
				<tr>
					<td>评语：</td>
					<td><input type="text" name="remark" value="无"></td>
				</tr>
				<tr align="center">
					<td colspan="2"><input type="submit" value="提交成绩"></td>
				</tr>
			</table>
		</form>
		${message}
	</div>
</body>
</html>
