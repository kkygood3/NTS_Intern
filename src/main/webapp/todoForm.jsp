<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>TODO LIST</title>
<link rel="stylesheet" href="css/todo.css">
</head>
<body>
	<h1>할일 등록</h1>
	<form action="todoAdd" method="POST">
		<p>어떤일인가요?</p>
		<input type="text" name="title" placeholder="swift 공부하기(24자까지)" maxlength="24" required>
		<p>누가 할일인가요?</p>
		<input type="text" name="name" placeholder="홍길동" required>
		<section id="radio_section">
			<p>우선순위</p>
			<input type="radio" name="sequence" value="1" id="more_important" required>
			<label for="more_important">1순위</label>
			
			<input type="radio" name="sequence" value="2" id="important" required>
			<label for="important">2순위</label>
			
			<input type="radio" name="sequence" value="3" id="less_important" required>
			<label for="less_important">3순위</label>
		</section>
		<a href="main" class="previous_page">&lt;이전</a>
		<input type="submit" value="제출">
		<input type="reset" value="내용지우기">
	</form>
</body>
</html>
