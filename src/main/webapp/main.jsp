<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/style.css">
<meta charset="UTF-8">
<title>Todo List</title>
</head>
<body>

	<div class="content">
		<header class="header_wrap">
			<h2 class="rotated_title">나의 해야할 일들</h2>

			<a href="/todoForm" class="go_register_button">새로운 TODO등록</a>
		</header>

		<section class="content_main">
			<!-- TODO -->
			<aside class="content_box" id="todo">
				<h3 class="title">TODO</h3>

				<c:forEach items="${ todoList }" var="todoList">
					<article class="card" id="${ todoList.id }">
						<h3>${ todoList.title }</h3>
						등록날짜: ${ todoList.regdate }, ${ todoList.name }, ${ todoList.sequence }
						<input type="hidden" name="id" value="${ todoList.id }">
						<input type="hidden" name="type" value="${ todoList.type }">
						<button class="type_change_button" onclick="change_type()">→</button>
					</article>
				</c:forEach>
				
			</aside>

			<!-- DOING -->
			<aside class="content_box" id="doing">
				<h3 class="title">DOING</h3>

				<c:forEach items="${ doingList }" var="doingList">
					<article class="card" id="${ doingList.id }">
						<h3>${ doingList.title }</h3>
						등록날짜: ${ doingList.regdate }, ${ doingList.name }, ${ doingList.sequence }
						<input type="hidden" name="id" value="${ doingList.id }">
						<input type="hidden" name="type" value="${ doingList.type }">
						<button class="type_change_button" onclick="change_type()">→</button>
					</article>
				</c:forEach>
			</aside>

			<!-- DONE -->
			<aside class="content_box" id="done">
				<h3 class="title">DONE</h3>
				<c:forEach items="${ doneList }" var="doneList">
					<article class="card" id="${ doneList.id }">
						<h3>${ doneList.title }</h3>
						등록날짜: ${ doneList.regdate }, ${ doneList.name }, ${ doneList.sequence }
					</article>
				</c:forEach>
			</aside>
		</section>

		<div style="clear: both;"></div>
	</div>
</body>
<script src="/javascript/script.js"></script>
</html>