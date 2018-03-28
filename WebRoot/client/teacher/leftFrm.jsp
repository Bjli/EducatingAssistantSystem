<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap-3.3.7/css/bootstrap.min.css">
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.7/jquery-3.3.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.7/js/bootstrap.min.js"></script>

<style type="text/css">
.lefttop {
	background: url(../../images/lefttop.gif) repeat-x;
	height: 40px;
	color: #fff;
	font-size: 14px;
	line-height: 42px;
}

.lefttop span {
	margin-left: 8px;
	margin-top: 10px;
	margin-right: 8px;
	background: url(../../images/leftico.png) no-repeat;
	width: 60px;
	height: 21px;
	float: left;
}

.menu {
	margin-left: 5px;
}

.menuson {
	line-height: 30px;
	font-weight: normal;
}

.menuson li {
	cursor: pointer;
}

.menuson li.active {
	position: relative;
	background: url(../../images/libg.png) repeat-x;
	line-height: 30px;
	color: #fff;
}

.menuson li cite {
	display: block;
	float: left;
	margin-left: 30px;
	background: url(../../images/list.gif) no-repeat;
	width: 16px;
	height: 16px;
}

.menuson li.active cite {
	background: url(../../images/list1.gif) no-repeat;
}

.menuson li.active i {
	display: block;
	background: url(../../images/sj.png) no-repeat;
	width: 6px;
	height: 11px;
	position: absolute;
	right: 0;
	z-index: 10000;
	top: 9px;
	right: -1px;
}

.menuson li a {
	display: block;
	*display: inline;
	*padding-top: 4px;
}

.menuson li.active a {
	color: #fff;
}

.title {
	cursor: pointer;
}
.link_me {
	position: fixed;
	bottom: 20px;
}
</style>

<script language="JavaScript"
	src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript">
	$(function() {

		$(".menuson li").click(function() {
			$(".menuson li.active").removeClass("active")
			$(this).addClass("active");
		});

		$('.title').click(function() {
			var $ul = $(this).next('ul');
			$('dd').find('ul').slideUp();
			if ($ul.is(':visible')) {
				$(this).next('ul').slideUp();
			} else {
				$(this).next('ul').slideDown();
			}
		});
	})
</script>
</head>


<body style="background: #f0f9fd;">
	<div class="lefttop">
		<span></span>主菜单
	</div>
	<div class="menu">
		<div class="panel-group" id="accordion">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne"> <span><img
								src="../../images/leftico01.png" /></span>&nbsp&nbsp系统信息
						</a>
					</h4>
				</div>
				<div id="collapseOne" class="panel-collapse collapse in">
					<div class="panel-body">
						<ul class="menuson">
							<li><cite></cite><a
								href="/TeachingAssistant/common/modifyPWD.jsp"
								target="middleFrame">修改密码</a><i></i></li>
							<li><cite></cite><a
								href="${pageContext.request.contextPath}/servlet/UserServlet?operation=logout"
								target=_parent>注销登录</a><i></i></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseTwo"> <span><img
								src="../../images/leftico03.png" /></span>&nbsp&nbsp成绩管理
						</a>
					</h4>
				</div>
				<div id="collapseTwo" class="panel-collapse collapse">
					<div class="panel-body">
			<ul class="menuson">
				<li><cite></cite><a
					href="${pageContext.request.contextPath}/servlet/AnswerServlet?operation=checkAnswer"
					target="middleFrame">批改作业</a><i></i></li>
				<li><cite></cite><a
					href="${pageContext.request.contextPath}/servlet/GradeServlet?operation=tCheckGrade"
					target="middleFrame">查看成绩</a><i></i></li>
				<li><cite></cite><a
					href="${pageContext.request.contextPath}/client/teacher/seachGrade.jsp"
					target="middleFrame">成绩检索</a><i></i></li>
			</ul>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseThree"> <span><img
								src="../../images/leftico02.png" /></span>&nbsp&nbsp通知管理
						</a>
					</h4>
				</div>
				<div id="collapseThree" class="panel-collapse collapse">
					<div class="panel-body">
						<ul class="menuson">
							<li><cite></cite><a href="../../common/releaseNotice.jsp"
								target="middleFrame">发布通知</a><i></i></li>
							<li><cite></cite><a
								href="${pageContext.request.contextPath}/servlet/NoticeServlet?operation=checkNotice"
								target="middleFrame">查看通知</a><i></i></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseFour"> <span><img
								src="../../images/leftico04.png" /></span>&nbsp&nbsp文件管理
						</a>
					</h4>
				</div>
				<div id="collapseFour" class="panel-collapse collapse">
					<div class="panel-body">
						<ul class="menuson">
							<li><cite></cite><a href="../../common/uploadFile.jsp"
								target="middleFrame">上传文件</a><i></i></li>
							<li><cite></cite><a
								href="${pageContext.request.contextPath}/servlet/FileServlet?operation=checkFile"
								target="middleFrame">查看文件</a><i></i></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="link_me">
		<table>
			<tr>
				<td colspan="2"><font size="2" face="arial" color="grey">如遇到问题或在使用中有什么建议，请联系作者，谢谢。</font></td>
			<tr>
				<td><img alt="email" src="../../images/email.png" width="20px"
					height="20px"></td>
				<td><font size="3" face="arial" color="grey">: ljb_nwuer@163.com</font></td>
			</tr>
		</table>
	</div>
	</div>
</body>

</html>
