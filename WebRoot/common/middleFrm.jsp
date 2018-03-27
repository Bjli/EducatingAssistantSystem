<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<style type="text/css">
.box {
	margin-left: 50px;
	margin-top: 20px;
	border: medium double white;
	width: 810px;
	height: 380px;
}
</style>
</head>
<body style="background-color: #EDF6FA;">
	<br />
	<font size="5" color="black">欢迎使用：<br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;现在时间：<%
			Date date = new Date();
			SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		%> <%=time.format(date)%>
		</font>
	<br>
	<div class="box">
		<table align="center" width="800px" height="200px">
			<tr align="left">
				<td colspan="6">常用网站</td>
			</tr>
			<tr>
				<td><a href="http://www.nwu.edu.cn/">西大官网</a></td>
				<td><a
					href="http://jwxt.nwu.edu.cn/(th2vz445o1fhdt45klzsywba)/Default2.aspx">教务系统</a></td>
				<td><a href="https://jwc.nwu.edu.cn">教务处</a></td>
				<td><a href="http://my.nwu.edu.cn/">西大门户</a></td>
				<td><a href="http://nss.nwu.edu.cn/nav_login">校园网</a></td>
				<td><a
					href="http://www.nwu.edu.cn/home/index/articles/mid/3888.html">校内讲座</a></td>
			</tr>
			<tr>
				<td><a href="http://jiuye.nwu.edu.cn/website/index.aspx">西大就业</a></td>
				<td><a
					href="http://tieba.baidu.com/f?kw=%CE%F7%B1%B1%B4%F3%D1%A7&fr=ala0&tpl=5">西北大学吧</a></td>
				<td><a href="http://lib.nwu.edu.cn/">西大图书馆</a></td>

				<td><a href="http://xyk.nwu.edu.cn/homeLogin.action">校园卡</a></td>
				<td><a href="http://my.nwu.edu.cn/index.portal?.pn=p52">校长信箱</a></td>
				<td><a href="http://tyb.nwu.edu.cn">体育教研部</a></td>

			</tr>
			<tr>
				<td><a href="http://mail.nwu.edu.cn/?cus=1">邮件系统</a></td>
				<td><a
					href="http://www.nwu.edu.cn/home/index/article/mid/3880/id/183641.html">校内电话</a></td>
				<td><a
					href="http://hqjt.nwu.edu.cn/home/index/article/mid/3361/id/59964.html">班车时间</a></td>
				<td><a
					href="http://www.nwu.edu.cn/home/index/articles/mid/782.html">西大校历</a></td>
				<td><a href="https://www.baidu.com">百度</a></td>
				<td><a href="https://www.baidu.com">百度</a></td>
			</tr>
			<tr>

				<td><a href="hdtv.nwu.edu.cn/">西大ipv6直播</a></td>
				<td><a href="http://mirror.lzu.edu.cn/">兰大开源镜像</a></td>
				<td><a href="https://iptv.tsinghua.edu.cn/">清华ipv6电视</a></td>
				<td><a href="https://mirrors.tuna.tsinghua.edu.cn/">清华大学开源镜像</a></td>
				<td><a href="https://www.baidu.com">百度</a></td>
				<td><a href="https://www.google.com.hk">谷歌</a></td>
			</tr>
		</table>
		<hr>
		<table align="center" width="800px" height="150px">
			<tr>
				<td colspan="6">院系导航</td>
			</tr>
			<tr align="center">
				<td><a href="http://wxy.nwu.edu.cn">文学院</a></td>
				<td><a href="http://history.nwu.edu.cn">历史学院</a></td>
				<td><a href="http://culture.nwu.edu.cn">文化遗产学院</a></td>
				<td><a href="http://physics.nwu.edu.cn">物理学院</a></td>
				<td><a href="http://geology.nwu.edu.cn">地质学系</a></td>
				<td><a href="http://ems.nwu.edu.cn/">经济管理学院</a></td>
			</tr>
			<tr align="center">
				<td><a href="http://math.nwu.edu.cn">数学学院</a></td>
				<td><a href="http://pms.nwu.edu.cn">公共管理学院</a></td>
				<td><a href="http://env.nwu.edu.cn">城市与环境学院</a></td>
				<td><a href="http://fxy.nwu.edu.cn">法学院</a></td>
				<td><a href="http://xinwen.nwu.edu.cn/">新闻传播学院</a></td>
				<td><a href="http://wyxy.nwu.edu.cn">外国语学院</a></td>
			</tr>
			<tr align="center">
				<td><a href="http://chin.nwu.edu.cn">化工学院</a></td>
				<td><a href="http://chem.nwu.edu.cn">化学与材料科学学院</a></td>
				<td><a href="http://biology.nwu.edu.cn">生命科学学院</a></td>
				<td><a href="http://ist.nwu.edu.cn">信息科学与技术学院</a></td>
				<td><a href="http://cice.nwu.edu.cn">国际文化交流学院</a></td>
				<td><a href="http://mks.nwu.edu.cn/">马克思主义学院</a></td>

			</tr>
		</table>
	</div>
</body>
</html>
