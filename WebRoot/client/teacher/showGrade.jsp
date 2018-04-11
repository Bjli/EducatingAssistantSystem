<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap-3.3.7/css/bootstrap.min.css">
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.7/jquery-3.3.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.7/js/bootstrap.min.js"></script>

<style type="text/css">
.gradeList {
	padding: 10px 10px;
}

.export_but {
	margin-left: 80%;
}
</style>
<script src="${pageContext.request.contextPath}/js/highcharts.js"></script>
</head>
<body onload="goPage(1,9);">
	<h4 align="center">成绩列表</h4>
	<hr>
	<div class="gradeList">
		<c:if test="${empty grade }">
  		未发现相关成绩！
  	    </c:if>
		<c:if test="${!empty grade}">
			<table class="table" align="center">
				<thead>
					<tr align="center">
						<td width="25%">学号</td>
						<td>姓名</td>
						<td>作业名称</td>
						<td>分数</td>
						<td width="25%">评语</td>
					</tr>
				</thead>
				<tbody id="Tbody">
					<%
						int sum = 0;//总的成绩记录数
							int count1 = 0;//[0,60)
							int count2 = 0;//[60,75)
							int count3 = 0;//[75,85)
							int count4 = 0;//[85,99]
					%>
					<c:forEach items="${grade}" var="c">
						<c:if test="${c.score < 60}">
							<tr class="danger" align="center">
								<td>${c.userId }</td>
								<td>${c.userName }</td>
								<%
									sum++;
												count1++;
								%>
								<td>${c.workTitle }</td>
								<td>${c.score}</td>
								<td>${c.remark}</td>
							</tr>
						</c:if>
						<c:if test="${c.score >= 85}">
							<tr class="success" align="center">
								<td>${c.userId }</td>
								<td>${c.userName }</td>
								<%
									sum++;
												count4++;
								%>
								<td>${c.workTitle }</td>
								<td>${c.score}</td>
								<td>${c.remark}</td>
							</tr>
						</c:if>
						<c:if test="${c.score >= 60 && c.score <85}">
							<tr class="warning" align="center">
								<td>${c.userId }</td>
								<td>${c.userName }</td>
								<%
									sum++;
								%>
								<td>${c.workTitle }</td>
								<td>${c.score}</td>
								<c:if test="${c.score >= 75}">
									<%
										count3++;
									%>
								</c:if>
								<c:if test="${c.score < 75}">
									<%
										count2++;
									%>
								</c:if>
								<td>${c.remark}</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
			<div class="export_but">
				<form
					action="${pageContext.request.contextPath}/servlet/GradeServlet?operation=ExportExcel"
					method="post" name="useradd">
					<input type="hidden" name="ways" value="${ways}"> <input
						type="hidden" name="op" value="teacher"> <input
						type="hidden" name="condition" value="${condition}">
						<input type="hidden" name="courseId" value="${courseId}"> <br>
					<input type="submit" class="btn btn-primary" value="导出成绩单">
				</form>
			</div>
			<%@ include file="../../common/page.jsp"%>
			<br>
			<hr>
			<c:if test="${ways == 'Title' }">
				<div id="container"
					style="width: 450px; height: 300px; margin: 0 auto"></div>
				<script language="JavaScript">
					$(document)
							.ready(
									function() {
										var chart = {
											plotBackgroundColor : null,
											plotBorderWidth : null,
											plotShadow : false
										};
										var title = {
											text : '学生成绩分布占比'
										};
										var tooltip = {
											pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
										};
										var plotOptions = {
											pie : {
												allowPointSelect : true,
												cursor : 'pointer',
												dataLabels : {
													enabled : true,
													format : '<b>{point.name}</b>: {point.percentage:.1f} %',
													style : {
														color : (Highcharts.theme && Highcharts.theme.contrastTextColor)
																|| 'black'
													}
												}
											}
										};
										var series = [ {
											type : 'pie',
											name : 'chart share',
											data : [
													[ '优秀(85分以上)',<%=count4%>],
													[ '良好(75-85分)',<%=count3%>],
													[ '中等(60-75分)',<%=count2%>],
													[ '不及格(60分以下)',<%=count1%>] ]
										} ];

										var json = {};
										json.chart = chart;
										json.title = title;
										json.tooltip = tooltip;
										json.series = series;
										json.plotOptions = plotOptions;
										$('#container').highcharts(json);
									});
				</script>
			</c:if>
			<c:if test="${ways == 'Uid' }">
				<div id="container"
					style="width: 450px; height: 300px; margin: 0 auto"></div>
				<script language="JavaScript">
				var array = new Array();
				<c:forEach items="${grade}" var="item">
				array.push(${item.score});
			    </c:forEach>
					$(document).ready(
							function() {
								var title = {
									text : '学生个人成绩走势'
								};
								var subtitle = {
									text : 'Source: grade show'
								};
								var xAxis = {
									categories : [ '1', '2', '3', '4', '5',
											'6', '7', '8', '9', '10', '11',
											'12', '13', '14', '15', '16', '17',
											'18', '19', '20' ]
								};
								var yAxis = {
									title : {
										text : 'Score'
									},
									plotLines : [ {
										value : 0,
										width : 1,
										color : '#808080'
									} ]
								};

								var legend = {
									layout : 'vertical',
									align : 'right',
									verticalAlign : 'middle',
									borderWidth : 0
								};

								var series = [ {
									name : '${condition}',
									data : array
								} ];

								var json = {};

								json.title = title;
								json.subtitle = subtitle;
								json.xAxis = xAxis;
								json.yAxis = yAxis;
								json.legend = legend;
								json.series = series;

								$('#container').highcharts(json);
							});
				</script>
			</c:if>

		</c:if>
	</div>
</body>
</html>
