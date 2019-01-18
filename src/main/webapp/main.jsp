<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>TODO LIST</title>
</head>
<body>

	<h1>나의 해야할 일들</h1>
	<a href="todoForm">새로운 TODO 등록</a>
	<p> TODO </p>
	<ul class="todo">
	<c:forEach items="${todos}" var="todo">
		<li>
			<form action="nextStep" method="POST">
				<input type="hidden" name="id" value="${todo.getId()}">
				<p>${todo.getTitle()}</p>
				<p>${todo.getRegdate()}</p>
				<p>${todo.getName()}</p>
				<p>우선순위 : ${todo.getSequence()}</p>
				<input type="hidden" name="type" value="${todo.getType()}">
				<input type="submit" value="→">
			</form>
		</li>
	</c:forEach>
	</ul>
	<p> DOING </p>
	<ul class="doing"></ul>
	<p> DONE </p>
	<ul class="done"></ul>
</body>
</html>