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
<link rel="stylesheet" type="text/css" href="./css/sysset.css">
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
					name="findmsg" required="required"><input
					value="findAnything" type="hidden" name="msg">
			</div>
			<button type="submit" class="btn btn-default">搜索</button>
		</form>
	</div>
	</nav>
	<ul class="breadcrumb">
		<li><a href="/Library/cmd?msg=index">首页</a></li>
		<li class="active">更改口令</li>
		<li><span style="color: red;">${AdminFalse}</span></li>
	</ul>
	<div id="sysset_main">
		<form action="/Library/admin?msg=changepw" method="post">
			<p>
				<span class="main_left">用&emsp;户&emsp;名</span><input type="text"
					required="required" name="adminname">
			</p>
			<br />
			<p>
				<span class="main_left">原&emsp;密&emsp;码</span><input type="password"
					required="required" name="password">
			</p>
			<br />
			<p>
				<span class="main_left">新&emsp;密&emsp;码</span><input type="password"
					required="required" name="password1">
			</p>
			<br />
			<p>
				<span class="main_left">确认新密码</span><input type="password"
					required="required" name="password2">
			</p>
			<br />
			<p id="sys_sub">
				<input type="submit" value="朕要提交">
			</p>
			<p id="sys_reset">
				<input type="reset">
			</p>
		</form>
	</div>
</body>
</html>
