<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="UTF-8">
<title>Todo List</title>
</head>
<body>

	<div class="content">
		<header class="header_wrap">
			<h2 class="rotated_title">나의 해야할 일들</h2>

			<a href="/register.jsp" class="go_register_button">새로운 TODO등록</a>
		</header>

		<section class="content_main">
			<!-- TODO -->
			<aside class="content_box">
				<h3 class="title">TODO</h3>

				<c:forEach items="${ todoList }" var="todoList">
					<article class="card">
						<h3>${ todoList.title }</h3>
						등록날짜: ${ todoList.regdate }, ${ todoList.name }, ${ todoList.sequence }
						<button>→</button>
					</article>
				</c:forEach>
				
			</aside>

			<!-- DOING -->
			<aside class="content_box">
				<h3 class="title">DOING</h3>

				<c:forEach items="${ doingList }" var="doingList">
					<article class="card">
						<h3>${ doingList.title }</h3>
						등록날짜: ${ doingList.regdate }, ${ doingList.name }, ${ doingList.sequence }
						<button>→</button>
					</article>
				</c:forEach>
			</aside>

			<!-- DONE -->
			<aside class="content_box">
				<h3 class="title">DONE</h3>
				<c:forEach items="${ doneList }" var="doneList">
					<article class="card">
						<h3>${ doneList.title }</h3>
						등록날짜: ${ doneList.regdate }, ${ doneList.name }, ${ doneList.sequence }
						<button>→</button>
					</article>
				</c:forEach>
			</aside>
		</section>

		<div style="clear: both;"></div>
	</div>
</body>
</html>