<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<style type="text/css">
	.gradeList{
	padding:10px 10px;
	
	}
	</style> 
  </head>
  <body>
    <h4 align="center">成绩列表</h4>
    <hr>
    <div class="gradeList">
    	<c:if test="${empty grade }">
  		未发现相关成绩！
  	</c:if>
	<c:if test="${!empty grade}">
    	<table border="1" width="60%" align="center">
    		<tr align="center">
    			<td width="25%">学号</td>
    			<td>作业名称</td>
    			<td>分数</td>
    			<td width="25%">评语</td>
    		</tr>
    		<%int sum=0;%>
    		<c:forEach items="${grade}" var="c">
    			<tr height="15" align="center">
    			<td>${c.userId }</td>
    			<%sum++;%>
    			<td>${c.workTitle }</td>
    			<td>${c.score}</td>
    			<td>${c.remark}</td>
    			</tr>
    		</c:forEach>
    		<tr align="right"><td colspan="4">
    		总计：<%=sum %>次成绩记录
    		</td>
    		</tr>>
    	</table>
    </c:if>
    </div>
  </body>
</html>
