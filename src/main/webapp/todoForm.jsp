<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/base.css">
</head>
<body>
	<header>
		<h1>할일 등록</h1>
	</header>
	<section>
		<div>
			<p>어떤일인가요?</p>
			<input type="text" maxlength="24" value="swift 공부하기(24자까지)">
		</div>
		<div>
			<p>누가 할일인가요?</p>
			<input type="text" maxlength="50" value="홍길동">
		</div>
		<div>
			<p>우선순위를 선택하세요</p>
			<input type="radio">1순위
			<input type="radio">2순위
			<input type="radio">3순위
		</div>
	</section>
	<section>
		<div>이전</div>
		<ul>
			<li>제출</li>
			<li>내용지우기</li>
		</ul>
	</section>
</body>
</html>