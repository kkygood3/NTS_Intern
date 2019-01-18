<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>할일 등록</h1>
	<form action="" method="POST">
		<p>어떤일인가요?</p>
		<input type="text" name="title" placeholder="swift 공부하기(24자까지)" maxlength="24">
		<p>누가 할일인가요?</p>
		<input type="text" name="owner" placeholder="홍길동">
		<section id="radio_section">
			<p>우선순위</p>
			<input type="radio" name="priority" value="more_important" id="more_important">
			<label for="more_important">1순위</label>
			
			<input type="radio" name="priority" value="important" id="important">
			<label for="important">2순위</label>
			
			<input type="radio" name="priority" value="less_important" id="less_important">
			<label for="less_important">3순위</label>
		</section>
		<a href="main">&lt;이전</a>
		<input type="submit">
		<button>내용지우기</button>
	</form>
</body>
</html>