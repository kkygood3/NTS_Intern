<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="/static/css/index.css">
<title>TODO LIST</title>
</head>
<body>

	<header>
		<h1 id="my_todo">나의 해야할 일들</h1>
		<a href="/newtodo" class="link_color_white">
			<span id="new_todo_btn">새로운 TODO 등록 </span>
		</a>
	</header>

	<section>
		<ul id="todo" class="progress">
			<li class="title">
				<h1>TODO</h1>
			</li>
			<c:forEach	var="item" items="${result.todoList }">
				<li>
					<h3 class="content">${item.title }</h3>
					<p class="content">
						등록날짜:<fmt:formatDate value="${item.regDate}" pattern="yyyy.MM.dd"/> ${item.personName } 우선순위 ${item.sequence }
						<button type="button" class="next_btn">→</button>
					</p>
				</li>
			</c:forEach>
		</ul>
		<ul id="doing" class="progress">
			<li class="title">
				<h1>DOING</h1>
			</li>
			<c:forEach	var="item" items="${result.doingList }">
				<li>
					<h3 class="content">${item.title }</h3>
					<p class="content">
						등록날짜:<fmt:formatDate value="${item.regDate}" pattern="yyyy.MM.dd"/> ${item.personName } 우선순위 ${item.sequence } 
						<button type="button" class="next_btn">→</button>
					</p>
				</li>
			</c:forEach>
		</ul>
		<ul id="done" class="progress">
			<li class="title">
				<h1>DONE</h1>
			</li>
			<c:forEach	var="item" items="${result.doneList }">
				<li>
					<h3 class="content">${item.title }</h3>
					<p class="content">
						등록날짜:<fmt:formatDate value="${item.regDate}" pattern="yyyy.MM.dd"/> ${item.personName } 우선순위 ${item.sequence }
					</p>
				</li>
			</c:forEach>
		</ul>
	</section>

</body>
</html>