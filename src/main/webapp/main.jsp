<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" --%>
<%--@ page import="java.util.*" --%>
<!--  c:forEach items="${todos}" var="todo" /-->
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>TODO LIST</title>
</head>
<body>
	<h1>나의 해야할 일들</h1>
	<a href="todoForm">새로운 TODO 등록</a>
	<ul class="todo">
		<li></li>
	</ul>
	<ul class="doing"></ul>
	<ul class="done"></ul>
</body>
</html>