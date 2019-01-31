<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="UTF-8">
<title>register</title>
</head>
<body>
	<section class="register_content">
		<h2>할 일 등록</h2>
	
		<form action="/todoAdd" method="POST" onsubmit="return isValueEmpty()">
			<label for="title">어떤일인가요?</label><br>
			<input type="text" name="title" id="title" required placeholder="swift 공부하기(24자까지)" maxlength="24">
			<br> <br>
			<label for="name">누가 할일인가요?</label><br>
			<input type="text" name="name" id="name" required placeholder="홍길동"> <br>
	
			<br>
	
			<label for="sequence">우선순위를 선택하세요!</label> <br> 
			<label><input type="radio" name="sequence" value="1" required>1순위</label>
			<label><input type="radio" name="sequence" value="2">2순위</label>
			<label><input type="radio" name="sequence" value="3">3순위</label>
			<br><br>
			<a href="/main" class="previous_button">＜ 이전</a>
			<input type="submit" value="제출" class="register_button">
			<input type="reset" value="내용지우기" class="register_button">
		</form>
	</section>
	<script src="/javascript/script.js"></script>
</body>
</html>