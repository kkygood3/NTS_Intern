<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>TODO LIST</title>
<link rel="stylesheet" href="css/todo.css">
</head>
<body>
	<h1 id="main_title">나의 해야할 일들</h1>
	<a href="todoForm" id="new_todo">새로운 TODO 등록</a>
	<div class="todos">
		<div>
			<p> TODO </p>
			<ul id="todo">
				<c:forEach items="${todos}" var="todo">
					<c:if test="${todo.getType()==\"TODO\"}">
						<li data-todo_id = "${todo.getId()}" data-todo_type = "${todo.getType()}">
							<p class="title">${todo.getTitle()}</p>
							<p class="regdate">등록날짜 : ${todo.getRegdate().split(" ")[0]}</p>
							<p class="name">${todo.getName()}</p>
							<p class="sequence">우선순위 : ${todo.getSequence()}</p>
							<button class="go_next">→</button>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
		<div>
			<p> DOING </p>
			<ul id="doing">
				<c:forEach items="${todos}" var="todo">
					<c:if test="${todo.getType()==\"DOING\"}">
						<li data-todo_id = "${todo.getId()}" data-todo_type = "${todo.getType()}">
							<p class="title">${todo.getTitle()}</p>
							<p class="regdate">등록날짜 : ${todo.getRegdate().split(" ")[0]}</p>
							<p class="name">${todo.getName()}</p>
							<p class="sequence">우선순위 : ${todo.getSequence()}</p>
							<button class="go_next">→</button>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
		<div>
			<p> DONE </p>
			<ul id="done">
				<c:forEach items="${todos}" var="todo">
					<c:if test="${todo.getType()==\"DONE\"}">
						<li data-todo_id = "${todo.getId()}" data-todo_type = "${todo.getType()}">
							<p class="title">${todo.getTitle()}</p>
							<p class="regdate">등록날짜 : ${todo.getRegdate().split(" ")[0]}</p>
							<p class="name">${todo.getName()}</p>
							<p class="sequence">우선순위 : ${todo.getSequence()}</p>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
<script src="js/typeChange.js"></script> 
</html>