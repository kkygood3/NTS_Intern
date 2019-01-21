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
	<ul id="todo">
		<c:forEach items="${todos}" var="todo">
			<c:if test="${todo.getType()==\"TODO\"}">
				<li>
					<input type="hidden" class="id" value="${todo.getId()}">
					<input type="hidden" class="type" value="${todo.getType()}">
					<p>${todo.getTitle()}</p>
					<p>${todo.getRegdate()}</p>
					<p>${todo.getName()}</p>
					<p>우선순위 : ${todo.getSequence()}</p>
					<p class="submit" id="${todo.getId()}">→</p>
				</li>
			</c:if>
		</c:forEach>
	</ul>
	<p> DOING </p>
	<ul id="doing">
		<c:forEach items="${todos}" var="todo">
			<c:if test="${todo.getType()==\"DOING\"}">
				<li>
					<input type="hidden" class="id" value="${todo.getId()}">
					<input type="hidden" class="type" value="${todo.getType()}">
					<p>${todo.getTitle()}</p>
					<p>${todo.getRegdate()}</p>
					<p>${todo.getName()}</p>
					<p>우선순위 : ${todo.getSequence()}</p>
					<p class="submit">→</p>
				</li>
			</c:if>
		</c:forEach>
	</ul>
	<p> DONE </p>
	<ul id="done">
		<c:forEach items="${todos}" var="todo">
			<c:if test="${todo.getType()==\"DONE\"}">
				<li>
					<input type="hidden" class="id" value="${todo.getId()}">
					<input type="hidden" class="type" value="${todo.getType()}">
					<p>${todo.getTitle()}</p>
					<p>${todo.getRegdate()}</p>
					<p>${todo.getName()}</p>
					<p>우선순위 : ${todo.getSequence()}</p>
				</li>
			</c:if>
		</c:forEach>
	</ul>
</body>
<script src="typeChange.js"></script> 
</html>