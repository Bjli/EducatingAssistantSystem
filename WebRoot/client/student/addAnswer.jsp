<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  	<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
	<script type="text/javascript" charset="utf-8" src="/TeachingAssistantSystem/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="/TeachingAssistantSystem/ueditor/ueditor.all.min.js"> </script>
	<script type="text/javascript" charset="utf-8" src="/TeachingAssistantSystem/ueditor/lang/zh-cn/zh-cn.js"></script>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
<title>学生作答</title>
</head>
<body style="background-color:#EDF6FA;">
  	<br>
    <h4 align="center">学生作答</h4>
    <form action="${pageContext.request.contextPath}/servlet/AnswerServlet?operation=addAnswer" method="post">
    <table border="1" width="100%">
    	<tr>
    		<td width="30%">学号:</td>
    		<td>
    			<input type="text" name="userid" />
    		</td>
    	</tr>
    	<tr>
    		<td width="30%">作答时间:</td>
    		<td>
    			<input type="date" name="date"/>
    			<font size="2" color="red">若手动输入，格式为：yyyy-MM-dd，例如2017-02-02</font>
    		</td>
    	</tr>
    	<tr>
    		<td width="30%">操作:&nbsp;&nbsp;&nbsp;&nbsp;</td>
    		<td>
    			<input type="submit" value="保存并提交">
    			<font size="2" color="red">在下方作答</font>
    		</td>
    	</tr>
    </table>
    <textarea name="content" id="myEditor" Style="height:40%;width:99.9%">作答区域!</textarea>
 	<script type="text/javascript">
 		UEDITOR_CONFIG.UEDITOR_HOME_URL = '/TeachingAssistantSystem/ueditor/'; //一定要用这句话，否则你需要去ueditor.config.js修改路径的配置信息
 		UE.getEditor('myEditor');
 	</script>
 	${message}
    </form>
  </body>
</html>