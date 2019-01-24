<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/main.css">
<title>main</title>
</head>


<body>

	<header>
		<span id="main_title">나의 해야할 일들</span>
		<a href="/newtodo" id="register_new_todo">새로운 TODO 등록</a>
	</header>
	
	<section>
	
		<ul id = "todo">
			<li class="todo_progress_block" >TODO</li>
			
			<c:forEach items="${todoList.todoDtoList}" var="todo">
				<fmt:parseDate var="regDate" value="${todo.regDate}" pattern="yyyy-MM-dd HH:mm:ss.s"/>
				<li id="${todo.id}" class="todo_list_block">
					<p class="todo_element_title">${todo.title}</p>
					<span class="todo_element_info">
						등록날짜:<fmt:formatDate value="${regDate}" pattern="yyyy.MM.dd"/>,
						 ${todo.name}, 우선순위 ${todo.sequence}
					</span>
					<button class="right_arrow_button">→</button>
				</li>
			</c:forEach>
		</ul>
		
		<ul id = "doing">
			<li class="todo_progress_block" >DOING</li>
			
			<c:forEach items="${todoList.doingDtoList}" var="todo">
				<fmt:parseDate var="regDate" value="${todo.regDate}" pattern="yyyy-MM-dd HH:mm:ss.s"/>
				<li id="${todo.id}" class="todo_list_block">
					<p class="todo_element_title">${todo.title}</p>
					<span class="todo_element_info">
						등록날짜:<fmt:formatDate value="${regDate}" pattern="yyyy.MM.dd"/>,
						 ${todo.name}, 우선순위 ${todo.sequence}
					</span>
					<button class="right_arrow_button">→</button>
				</li>
			</c:forEach>
		</ul>
		
		<ul id = "done">
			<li class="todo_progress_block" >DONE</li>
			
			<c:forEach items="${todoList.doneDtoList}" var="todo">
				<fmt:parseDate var="regDate" value="${todo.regDate}" pattern="yyyy-MM-dd HH:mm:ss.s"/>
				<li id="${todo.id}" class="todo_list_block">
					<p class="todo_element_title">${todo.title}</p>
					<span class="todo_element_info">
						등록날짜:<fmt:formatDate value="${regDate}" pattern="yyyy.MM.dd"/>,
						 ${todo.name}, 우선순위 ${todo.sequence}
					</span>
				</li>
			</c:forEach>
		</ul>		
		
	</section>
	<script src="/js/handler.js" charset="utf-8"></script>
</body>
</html>