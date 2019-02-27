<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML >
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
<link rel="stylesheet" type="text/css" href="./css/addreader.css">
<link rel="stylesheet" type="text/css"
	href="./bootstrap3x/css/bootstrap.css">
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
		<li><a
			href="/Library/user?msg=findUserPage&currentPage=${userPageResult.currentPage}">读者管理</a></li>
		<li class="active">添加读者</li>
		<li><span class="glyphicon glyphicon-flag"></span><a
			href="/Library/cmd?msg=addbook">添加书籍</a></li>
		<li><span class="red">${userErrorMsg}</span></li>
	</ul>
	<div id="ar_main">
		<form action="/Library/user?msg=adduser" method="post">
			<p>
				<span class="tb_left">姓&emsp;&emsp;名&emsp;&emsp;</span><input
					type="text" placeholder="真实姓名" required="required" name="username">
			</p>
			<p>
				<span class="tb_left">性&emsp;&emsp;别&emsp;&emsp;</span><input
					type="radio" value="男" name="sex" checked="checked"
					style="margin-left: 30px;"><span style="margin-right: 50px">&emsp;男</span><input
					type="radio" value="女" name="sex"><span
					style="margin-right: 50px;">&emsp;女</span>
			</p>
			<p>
				<span class="tb_left">电&emsp;&emsp;话&emsp;&emsp;</span><input
					type="text" placeholder="联系方式" required="required" name="iphone">
			</p>
			<p>
				<span class="tb_left">邮&emsp;&emsp;箱&emsp;&emsp;</span> <input
					type="text" placeholder="常用邮箱" required="required" name="email">
			</p>
			<p>
				<span class="tb_left">证件号码&emsp;&emsp;</span> <input type="text"
					placeholder="身份证号码" required="required" name="idcard">
			</p>
			<p>

				<textarea rows="3" cols="35" placeholder="添加相关备注,100字以内" name="note"></textarea>
			</p>
			<p>

				<input type="submit" id="ar_sub" value="郑要提交"> <input
					type="reset" id="ar_reset">
			</p>
		</form>
	</div>

</body>
</html>
