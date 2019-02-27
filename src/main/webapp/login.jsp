<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en" class="no-js">
<head>

<title>login</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!-- <link rel="stylesheet" type="text/css" href="./css/login.css"> -->
<link rel="stylesheet" type="text/css" href="./Login/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="./Login/css/demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="./Login/css/component.css" />
<!--[if IE]>
<script src="js/html5.js"></script>
<![endif]-->
</head>

<!-- <body>
	<div id="input_main">
		<div style="font-size:40px ;font-family: STHupo">厦门图书馆里系统</div>
		<br />
		<div>
			<form action="/Library/admin?msg=login" method="post">
				<p>
					&emsp;名&emsp;称： <input type="text" required="required"
						id="username" name="username">
				</p>
				<br />
				<p>
					&emsp;密&emsp;码： <input type="text" required="required"
						id="password" name="password">
				</p>
				<br />
				<p>
					&emsp;<input type="submit" value="登陆" name=""> &emsp;
					&emsp;<input type="reset" value="重置">&emsp; &emsp; <input
						type="button" value="忘记密码">
				</p>
			</form>
		</div>
	</div>
</body> -->

<body>
	<div class="container demo-1">
		<div class="content">
			<div id="large-header" class="large-header">
				<canvas id="demo-canvas"></canvas>
				<div class="logo_box">
					<h3>厦门图书馆欢迎你</h3>
					<form action="/Library/admin?msg=login" name="f" method="post">
						<div class="input_outer">
							<span class="u_user"></span> <input name="username" class="text"
								style="color: #FFFFFF " type="text" placeholder="请输入账户">
						</div>
						<div class="input_outer">
							<span class="us_uer"></span> <input name="password" class="text"
								style="color: #FFFFFF !important; position:absolute; z-index:100;"
								type="password" placeholder="请输入密码">
						</div>
						<div class="mb2">
							<input type="submit" value="朕要登陆"
								style="background-color:#0096e6;width: 333px;height: 50;border-radius:20px;">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- /container -->
	<script src="./Login/js/TweenLite.min.js"></script>
	<script src="./Login/js/EasePack.min.js"></script>
	<script src="./Login/js/rAF.js"></script>
	<script src="./Login/js/demo-1.js"></script>
	<div style="text-align:center;"></div>
</body>

</html>
