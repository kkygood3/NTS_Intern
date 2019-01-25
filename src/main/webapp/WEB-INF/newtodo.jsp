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
	<section id="reg-section">
		<form method="post" action="/add" accept-charset="utf-8">
			<h1>할 일 등록</h1>
			<article>어떤일인가요?</article>
			<article>
				<input type="text" name="input-title"
					placeholder="swift 공부하기(24자까지)" required>
			</article>
			<br><br>
			<article>누가 할일인가요?</article>
			<article>
				<input type="text" name="input-name" placeholder="홍길동" required>
			</article>
			<br><br>
			<article>우선순위를 선택하세요.</article>
			<article>
				<input type="radio" name="input-sequence" value="1" required>1순위
				<input type="radio" name="input-sequence" value="2" required>2순위
				<input type="radio" name="input-sequence" value="3" required>3순위
			</article>
			<br><br>
			<article>
				<input type="button" name="back-btn"value="< 이전" onClick="location.href='/main'">
				<input type="reset" name="clear-todo" value="내용지우기">
				<input type="submit" name="submit-todo" value="제출">
			</article>
			<br><br>
		</form>
	</section>
</body>
</html>