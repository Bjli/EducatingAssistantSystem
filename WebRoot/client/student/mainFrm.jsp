<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>实验报告教学管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <frameset rows="120,*">
  	<frame src="topFrm.jsp" name="topFrame" noresize="noresize" id="topFrame" scrolling="no" frameborder="0"/>
 	<frameset cols="235,*">
    	<frame src="leftFrm.jsp" name="leftFrame" noresize="noresize" id="leftFrame" frameborder="0"/>
    	<frame src="../../common/middleFrm.jsp" name="middleFrame" noresize="noresize" id="middleFrame" frameborder="0"/>
    </frameset>
    
    <noframes>
    	<body>
    		此浏览器不支持框架，请更换浏览器！
    	</body>
    </noframes>
   </frameset>
</html>
