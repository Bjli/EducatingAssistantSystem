<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	
<style type="text/css">
.lefttop{background:url(../images/lefttop.gif) repeat-x;height:40px;color:#fff;font-size:14px;line-height:42px;}
.lefttop span{margin-left:8px; margin-top:10px;margin-right:8px; background:url(../images/leftico.png) no-repeat; width:60px; height:21px;float:left;}
.leftmenu{width:200px; overflow:hidden; background:url(../images/leftline.gif) repeat-y right;}
.leftmenu dd{background:url(../images/leftmenubg.gif) repeat-x;line-height:35px;font-weight:bold;font-size:14px;border-right:solid 1px #b7d5df;}
.leftmenu dd span{float:left; margin:auto;}
.leftmenu dd .menuson{display:none;}
.leftmenu dd:first-child .menuson{display:block;}
.menuson {line-height:30px; font-weight:normal; }
.menuson li{cursor:pointer;}
.menuson li.active{position:relative; background:url(../images/libg.png) repeat-x; line-height:30px; color:#fff;}
.menuson li cite{display:block; float:left; margin-left:30px; background:url(../images/list.gif) no-repeat; width:16px; height:16px;}
.menuson li.active cite{background:url(../images/list1.gif) no-repeat;}
.menuson li.active i{display:block; background:url(../images/sj.png) no-repeat; width:6px; height:11px; position:absolute; right:0;z-index:10000; top:9px; right:-1px;}
.menuson li a{ display:block; *display:inline; *padding-top:4px;}
.menuson li.active a{color:#fff;}
.title{cursor:pointer;}
</style>

    <script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	  
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>   
  </head>
  
  
  <body style="background:#f0f9fd;">
	<div class="lefttop"><span></span>主菜单</div>
    
    <dl class="leftmenu" >
    <dd>
    <div class="title" ><span><img src="../images/leftico01.png" /></span>&nbsp&nbsp系统信息</div>
    	<ul class="menuson">
        <li><cite></cite><a href="addUser.jsp" target="middleFrame">添加用户</a><i></i></li>
        <li><cite></cite><a href="${pageContext.request.contextPath}/servlet/UserServlet?operation=checkUser" target="middleFrame">查看用户</a><i></i></li>
        <li><cite></cite><a href="/TeachingAssistantSystem/common/modifyPWD.jsp" target="middleFrame">修改密码</a><i></i></li>
        <li><cite></cite><a href="${pageContext.request.contextPath}/servlet/UserServlet?operation=logout" target=_parent>注销登录</a><i></i></li>
        </ul>    
    </dd>
    
    <dd>
    <div class="title"><span><img src="../images/leftico03.png" /></span>&nbsp&nbsp成绩管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="${pageContext.request.contextPath}/servlet/GradeServlet?operation=aCheckGrade" target="middleFrame">查看成绩</a><i></i></li>
                <li><cite></cite><a href="inputGrade.jsp" target="middleFrame">修改成绩</a><i></i></li>
        </ul>     
    </dd>      
    
    <dd>
    <div class="title"><span><img src="../images/leftico02.png" /></span>&nbsp&nbsp通知管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="releaseNotice.jsp" target="middleFrame">发布通知</a><i></i></li>
        <li><cite></cite><a href="${pageContext.request.contextPath}/servlet/NoticeServlet?operation=checkNotice" target="middleFrame">查看通知</a><i></i></li>
        </ul>
    </dd> 
    
    <dd>
    <div class="title"><span><img src="../images/leftico04.png" /></span>&nbsp&nbsp文件管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="uploadFile.jsp" target="middleFrame">上传文件</a><i></i></li>
        <li><cite></cite><a href="${pageContext.request.contextPath}/servlet/FileServlet?operation=checkFile" target="middleFrame">查看文件</a><i></i></li>
        </ul>
    </dd> 
    </dl>
    
</body>

</html>
