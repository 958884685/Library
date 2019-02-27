<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>厦门市黄地村图书馆</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<link rel="stylesheet" type="text/css" href="./css/top.css">
<link rel="stylesheet" type="text/css" href="./css/about.css">
<link rel="stylesheet" type="text/css"
	href="./bootstrap3x/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="./bootstrap3x/js/bootstrap.min.js">
</head>

<body>
	<div id="head_img">
		<span id="head_name"> <c:if test="${admin_in_session!=null}">欢迎光临,${admin_in_session.adminname}</c:if></span>
	</div>

	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<ul class="nav navbar-nav">
			<li class="active"><a href="/Library/cmd?msg=index">首页</a></li>
			<li><a href="/Library/cmd?msg=books" target="_black">图书馆里</a></li>
			<li><a href="/Library/cmd?msg=reader" target="_black">读者管理</a></li>
			<li><a href="/Library/cmd?msg=sysset">更改口令</a></li>
			<li><a href="/Library/cmd?msg=about">关于</a></li>
			<li><a href="/Library/cmd?msg=logout">退出系统</a></li>
		</ul>
		<form class="navbar-form navbar-left" action="/Library/search"
			method="post">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search"
					name="findmsg" required="required"> <input
					value="findAnything" type="hidden" name="msg">
			</div>
			<button type="submit" class="btn btn-default">搜索</button>
		</form>
	</div>
	</nav>

	<ul class="breadcrumb">
		<li><a href="/Library/cmd?msg=index">首页</a></li>
		<li class="active">关于</li>
	</ul>


	<div id="left">
		<div style="text-align: center;" id="about_main">
			<p>
				<span class="about_left">图书名称:</span> <span class="about_right">厦门市黄地村图书馆</span>
			</p>
			<p>
				<span class="about_left">地&emsp;&emsp;址:</span> <span
					class="about_right">厦门市集美区后溪镇黄地村</span>
			</p>
			<p>
				<span class="about_left">负&nbsp;&nbsp;责&nbsp;&nbsp;人:</span> <span
					class="about_right">刘鸿伟</span>
			</p>
			<p>
				<span class="about_left">联系方式:</span> <span class="about_right">15805901020</span>
			</p>
			<p>
				<span class="about_left">邮&emsp;&emsp;箱:</span> <span
					class="about_right">958884685@qq.com</span>
			</p>
			<p>
				<span class="about_left">统&emsp;&emsp;建:</span> <span
					class="about_right">2018/01/01</span>
			</p>
		</div>

	</div>
	<div id="right">
		<p>
			<span>简&emsp;&emsp;介:</span>
		</p>
		<p>
			<span>&emsp;&emsp;陌上花的学问陌上花的学问陌上花的学问陌上花的学问陌上花的学问陌上花的学问陌上花的学问陌上花的学问陌上花的学问陌上花的学问陌上花的学问陌上花的学问陌上花的学问陌上花的学问陌上花的学问陌上花的学问陌上花的学问</span>
		</p>
	</div>
</body>
</html>
