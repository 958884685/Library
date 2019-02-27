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
<link rel="stylesheet" type="text/css" href="./css/addbook.css">
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
			href="/Library/book?msg=findBookPage&currentPage=${bookPageResult.currentPage}">书籍管理</a></li>
		<li class="active">添加书籍</li>
		<li><span class="glyphicon glyphicon-flag"></span><a
			href="/Library/cmd?msg=adduser">添加读者</a></li>
		<li><span style="color: red;"> ${bookErrorMsg}</span></li>
	</ul>
	<div id="ab_main">
		<form action="/Library/book?msg=addbook" method="post">

			<p class="tb_left">
				<span>编&emsp;&emsp;号</span>&emsp;&emsp;<input type="text"
					placeholder="唯一识别码" required="required" name="bookid">
			</p>
			<p>
				<span class="tb_left">书&emsp;&emsp;名</span>&emsp;&emsp;<input
					type="text" placeholder="书籍名称" required="required" name="bookname">
			</p>
			<p>
				<span class="tb_left">类&emsp;&emsp;型&emsp;&emsp;</span><select
					name="type">
					<option selected="selected" value="哲学类">哲学类</option>
					<option value="社会科学类">社会科学类</option>
					<option value="政治类">政治类</option>
					<option value="法律类">法律类</option>
					<option value="军事类">军事类</option>
					<option value="经济类">经济类</option>
					<option value="文化类">文化类</option>
					<option value="教育类">教育类</option>
					<option value="体育类">体育类</option>
					<option value="语言文字类">语言文字类</option>
					<option value="艺术类">艺术类</option>
					<option value="历史类">历史类</option>
					<option value="地理类">地理类</option>
					<option value="天文学类">天文学类</option>
					<option value="生物学类">生物学类</option>
					<option value="医学卫生类">医学卫生类</option>
					<option value="农业类">农业类</option>
					<option value="其他">其他</option>
				</select>
			</p>
			<p>
				<span class="tb_left">作&emsp;&emsp;者</span>&emsp;&emsp;<input
					type="text" placeholder="作者" required="required" name="author">
			</p>
			<p>
				<span class="tb_left">价&emsp;&emsp;格</span>&emsp;&emsp;<input
					type="text" placeholder="价格" required="required" name="price">
			</p>
			<p>

				<textarea rows="3" cols="35" placeholder="添加相关备注,100字以内"
					name="state"></textarea>
			</p>
			<input type="submit" value="朕要提交" id="ab_sub">&emsp;<input
				type="reset" value="重置" id="ab_reset">
		</form>

	</div>

</body>
</html>
