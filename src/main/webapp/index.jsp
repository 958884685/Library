<%@page import="com.library.entity.Book"%>
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
<link rel="stylesheet" type="text/css" href="./css/index.css">
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
			<li><a href="/Library/cmd?msg=books" target="_black">图书管理</a></li>
			<li><a href="/Library/cmd?msg=reader" target="_black">读者管理</a></li>
			<li><a href="/Library/cmd?msg=sysset">更改口令</a></li>
			<li><a href="/Library/cmd?msg=about">关于</a></li>
			<li><a href="/Library/cmd?msg=logout">退出系统</a></li>
		</ul>
		<!-- 有查找内容 搜索框上移 -->
		<c:if test="${find_BU!=null}">
			<form class="navbar-form navbar-left" action="/Library/search"
				method="post">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search"
						name="findmsg" required="required"><input
						value="findAnything" type="hidden" name="msg">
				</div>
				<button type="submit" class="btn btn-default">搜索</button>
			</form>
		</c:if>
	</div>
	</nav>

	<ul class="breadcrumb">
		<li><a href="/Library/cmd?msg=index">首页</a></li>
		<li class="active">索引</li>

		<c:if test="${find_BU!=null}">
			<li><span><a href="/Library/search?msg=removeEverything">清除</a></span></li>
		</c:if>
	</ul>

	<!--没有查找内容 搜索框下移  -->
	<c:if test="${find_BU==null}">
		<div id="main_find">
			<form action="/Library/search" class="navbar-form navbar-left"
				method="post">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search"
						name="findmsg" required="required"> <input
						value="findAnything" type="hidden" name="msg">
				</div>
				<div style="display: inline-block;">
					<button type="submit" class="btn btn-default">搜索</button>
				</div>
			</form>
		</div>
	</c:if>
	<table class="table table-striped">

		<!--遍历查找 BU信息 -->
		<c:if test="${find_BU!=null}">
			<c:forEach items="${find_BU.getListData()}" var="BU">
				<tr>
					<c:if test="${BU.getClass()=='class com.library.entity.Book'}">
						<td>${BU.bookid}</td>
						<td>${BU.bookname}</td>
						<td>${BU.type}</td>
						<td>${BU.author}</td>
						<td>${BU.price}</td>
						<td>${BU.state}</td>
						<td><a
							href="/Library/book?msg=findABook&id=${BU.id}&bookid=${BU.bookid}">修改</a>
						</td>
					</c:if>

					<c:if test="${BU.getClass()=='class com.library.entity.User'}">
						<td>${BU.username}</td>
						<td>${BU.sex}</td>
						<td>${BU.iphone}</td>
						<td>${BU.email}</td>
						<td>${BU.idcard}</td>
						<td>${BU.note}</td>
						<td><a
							href="/Library/book?msg=findABook&id=${BU.id}&bookid=${BU.idcard}">修改</a>
						</td>
					</c:if>
				</tr>
			</c:forEach>

			<!--BU 分页导航  -->
			<tr>
				<td colspan="8" align="center"><nav
						aria-label="Page navigation">
					<ul class="pagination ">
						<li><a
							href="/Library/search?msg=findAnything&currentPage=${find_BU.firstPage}&findmsg=${findmsg}"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
						<li><a
							href="/Library/search?msg=findAnything&currentPage=${find_BU.previousPage}&findmsg=${findmsg}">&lt;</a></li>

						<c:forEach begin="${find_BU.beginPage}" end="${find_BU.endPage}"
							step="1" var="PageNum">

							<c:if test="${PageNum==find_BU.currentPage}">
								<li><span style="color: red;">${PageNum}</span></li>
							</c:if>
							<c:if test="${PageNum!=find_BU.currentPage}">
								<li><a
									href="/Library/search?msg=findAnything&currentPage=${PageNum}&findmsg=${findmsg}">${PageNum}</a></li>
							</c:if>


						</c:forEach>

						<li><a
							href="/Library/search?msg=findAnything&currentPage=${find_BU.nextPage}&findmsg=${findmsg}">&gt;</a></li>
						<li><a
							href="/Library/search?msg=findAnything&currentPage=${find_BU.lastPage}&findmsg=${findmsg}"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</ul>
					</nav></td>
			</tr>

		</c:if>
	</table>
</body>
</html>
