<%@page import="com.nts.dto.TodoDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<form method="get" action="/exchange">
				<input type="submit" value="새로운 TODO 등록" name="todo-btn"
					id="todo-btn">
			</form>
		</div>

		<div id="content">
			<section class="todo">
				<article class="title">TODO</article>
				<article class="list">
					<div class="list-one">자바스크립트 공부하기</div>
					<div class="list-two">등록날짜 ~~~~~~~~~~~~~~~~~~~~~</div>
				</article>
			</section>
			<section class="todo">
				<article class="title">DOING</article>
				<article class="list">
					<div class="list-one">자바스크립트 공부하기</div>
					<div class="list-two">등록날짜 ~~~~~~~~~~~~~~~~~~~~~</div>
				</article>
			</section>
			<section class="todo">
				<article class="title">DONE</article>
				<article class="list">
					<div class="list-one">자바스크립트 공부하기</div>
					<div class="list-two">등록날짜 ~~~~~~~~~~~~~~~~~~~~~</div>
				</article>
			</section>

		</div>
		<!-- <ul id=top>
			<li class="todo-box">TODO</li>
			<li class="todo-box">DOING</li>
			<li class="todo-box">DONE</li>
		</ul> -->
	</section>
	<%
		List<TodoDto> todoList = (List<TodoDto>)request.getAttribute("todoList");

		for (TodoDto todoDto : todoList) {
	%>
	<%=todoDto.getTitle()%><br>
	<%
		}
	%>
	<br>
	<br>
	<%
		List<TodoDto> doingList = (List<TodoDto>)request.getAttribute("doingList");

		for (TodoDto todoDto : doingList) {
	%>
	<%=todoDto.getTitle()%><br>
	<%
		}
	%>
	<br>
	<br>
	<%
		List<TodoDto> doneList = (List<TodoDto>)request.getAttribute("doneList");

		for (TodoDto todoDto : doneList) {
	%>
	<%=todoDto.getTitle()%><br>
	<%
		}
	%>

</body>
</html>