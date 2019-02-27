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
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="./css/top.css">

<link rel="stylesheet" type="text/css"
	href="./bootstrap3x/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="./css/books.css">

</head>

<body>
	<div id="head_img">
		<span id="head_name"> <c:if test="${admin_in_session!=null}">欢迎光临,${admin_in_session.adminname}</c:if>
		</span>
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
		<li class="active">所有书籍</li>

	</ul>

	<table class="table table-striped">
		<!-- <caption style="text-align: center;">推荐书籍</caption> -->
		<tr>
			<th>编号</th>
			<th>书名</th>
			<th>类型</th>
			<th>作者</th>
			<th>价格</th>
			<th>备注</th>
			<th>操作(<a href="/Library/cmd?msg=addbook">添加书籍</a>)
			</th>
		</tr>

		<c:if test="${bookPageResult.listData!=null}">

			<c:forEach items="${bookPageResult.listData}" var="book">
				<tr>
					<td>${book.bookid}</td>
					<td>${book.bookname}</td>
					<td>${book.type}</td>
					<td>${book.author}</td>
					<td>${book.price}</td>
					<td>${book.state}</td>
					<td><a
						href="/Library/book?msg=findABook&id=${book.id}&bookid=${book.bookid}&currentPage=${bookPageResult.currentPage}">修改</a>/
						<a
						href="/Library/book?msg=delectbook&id=${book.id}&bookid=${book.bookid}&currentPage=${bookPageResult.currentPage}">删除</a>
					</td>

				</tr>
			</c:forEach>


			<tr>
				<td colspan="8" align="center"><nav
						aria-label="Page navigation">
					<ul class="pagination ">
						<li><a
							href="/Library/book?msg=findBookPage&currentPage=${bookPageResult.firstPage}"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
						<li><a
							href="/Library/book?msg=findBookPage&currentPage=${bookPageResult.previousPage}">&lt;</a></li>

						<c:forEach begin="${bookPageResult.beginPage}"
							end="${bookPageResult.endPage}" step="1" var="PageNum">

							<c:if test="${PageNum==bookPageResult.currentPage}">
								<li><span style="color: red;">${PageNum}</span></li>
							</c:if>
							<c:if test="${PageNum!=bookPageResult.currentPage}">
								<li><a
									href="/Library/book?msg=findBookPage&currentPage=${PageNum}">${PageNum}</a></li>
							</c:if>


						</c:forEach>

						<li><a
							href="/Library/book?msg=findBookPage&currentPage=${bookPageResult.nextPage}">&gt;</a></li>
						<li><a
							href="/Library/book?msg=findBookPage&currentPage=${bookPageResult.lastPage}"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</ul>
					</nav></td>
			</tr>
		</c:if>

	</table>

</body>
</html>
