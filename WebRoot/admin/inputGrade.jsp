<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
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
</style>
<script type="text/javascript">
	function Check_form() {
		if (document.getElementById("userid").value == ""
				|| document.getElementById("userid") == null) {
			alert('请录入学号!');
			return false;
		}
		if (document.getElementById("title").value == ""
				|| document.getElementById("title") == null) {
			alert('请录入具体作业名称!');
			return false;
		}
		if (document.getElementById("score").value == ""
				|| document.getElementById("score") == null) {
			alert('请录入成绩!');
			return false;
		}
		return true;
	}
</script>

</head>
<body>
	<h4 align="center">修改成绩</h4>
	<hr>
	<br>
	<div style="border: solid 1px black; width: 500px; margin-left: 300px">
		<form
			action="${pageContext.request.contextPath}/servlet/GradeServlet?operation=modifyGrade"
			onsubmit="return Check_form()" method="post">
			<table align="center" border="0">
				<tr height="60">
					<td>学&nbsp;&nbsp;号:</td>
					<td><input type="text" name="userid" id="userid" /></td>
				</tr>
				<tr height="60">
					<td>作业名称:</td>
					<td><input type="text" name="worktitle" id="title"></td>
				</tr>
				<tr height="60">
					<td>分&nbsp;&nbsp;数:</td>
					<td><input type="text" name="modifyscore" id="score" /></td>
				</tr>
				<tr height="60">
					<td>评&nbsp;&nbsp;语:</td>
					<td><input type="text" name="remark" value="无.." /></td>
				</tr>
				<tr height="60">
					<td colspan="2" align="center"><input type="submit" value="提交"
						style="width: 100px; height: 35px" /></td>
				</tr>
			</table>
		</form>
		${message}
	</div>
</body>
</html>
