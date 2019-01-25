<%@page import="com.nts.dto.TodoDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TODO LIST</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<section id="main">
		<div id="corner">나의 해야할 일들</div>
		<div id="todo-register">
			<input type="button" value="새로운 TODO 등록" onClick="location.href='/newtodo'"/>
		</div>

		<div id="content">
			<section class="todo" id="zone-todo">
				<article class="title">TODO</article>
				<c:forEach var="todoDto" items="${todoList}">
					<article class="list">
						<div class="list-one">${todoDto.getTitle() }</div>
						<div class="list-two">
							등록날짜:${todoDto.getRegdate() }, ${todoDto.getName() }, 우선순위
							${todoDto.getSequence() }
							<button name="${todoDto.getId() }">→</button>
						</div>
					</article>
				</c:forEach>
			</section>
			<section class="todo" id="zone-doing">
				<article class="title">DOING</article>
				<c:forEach var="todoDto" items="${doingList}">
					<article class="list">
						<div class="list-one">${todoDto.getTitle() }</div>
						<div class="list-two">
							등록날짜:${todoDto.getRegdate() }, ${todoDto.getName() }, 우선순위
							${todoDto.getSequence() }
							<button name="${todoDto.getId() }">→</button>
						</div>
					</article>
				</c:forEach>
			</section>
			<section class="todo" id="zone-done">
				<article class="title">DONE</article>
				<c:forEach var="todoDto" items="${doneList}">
					<article class="list">
						<div class="list-one">${todoDto.getTitle() }</div>
						<div class="list-two">등록날짜:${todoDto.getRegdate() },
							${todoDto.getName() }, 우선순위 ${todoDto.getSequence() }</div>
					</article>
				</c:forEach>
			</section>

		</div>
	</section>
<script type="text/javascript" src="./js/typeup.js" ></script>
</body>
</html>