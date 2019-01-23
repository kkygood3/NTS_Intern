<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ToDo List Web</title>
		<link rel="stylesheet" href="./css/index.css">
	</head>
	<body>
		<div class="container">
			<h1>
				나의 해야 할 일들
			</h1>
			<a class="add_btn" href="./todoForm">새로운 TODO 등록</a>
			<div class="todo_list_container">
				<ul id="todo_list_type_todo_column">
					<li class="todo_cell todo_category">TODO</li>
					<c:forEach items="${TodoList}" var="item">
						<li class="todo_cell todo_content" data-todo-type="${item.getType()}" data-todo-id="${item.getId()}">
							<h1>${item.getTitle()}</h1>
							<h5>등록날짜:${item.getRegDate()}, ${item.getName()}, 우선순위 ${item.getSequence()}</h5>
							<button class="next_btn">&rarr;</button>
						</li>
					</c:forEach>
				</ul>	
				<ul id="todo_list_type_doing_column">
					<li class="todo_cell todo_category">DOING</li>
					<c:forEach items="${DoingList}" var="item">
						<li class="todo_cell todo_content" data-todo-type="${item.getType()}" data-todo-id="${item.getId()}">
							<h1>${item.getTitle()}</h1>
							<h5>등록날짜:${item.getRegDate()}, ${item.getName()}, 우선순위 ${item.getSequence()}</h5>
							<button class="next_btn">&rarr;</button>
						</li>
					</c:forEach>
				</ul>
				<ul id="todo_list_type_done_column">
					<li class="todo_cell todo_category">DONE</li>
					<c:forEach items="${DoneList}" var="item">
						<li class="todo_cell todo_content" data-todo-type="${item.getType()}" data-todo-id="${item.getId()}">
							<h1>${item.getTitle()}</h1>
							<h5>등록날짜:${item.getRegDate()}, ${item.getName()}, 우선순위 ${item.getSequence()}</h5>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<script src="./javascript/main.js"></script>
	</body>
</html>