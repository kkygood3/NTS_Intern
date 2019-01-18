<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="/static/css/newtodo.css">
<title>TODO LIST</title>
</head>
<body>

	<header>
		<h1>할일 등록</h1>
	</header>

	<section>
		<form method="POST" action="/newtodo">
			<div class="todo_regist">
				<h3>어떤 일 인가요?</h3>
				<input type="text" placeholder="swift 공부하기 (24까지)" maxlength="24" required/>
			</div>
			<div class="todo_regist">
				<h3>누가 할일 인가요?</h3>
				<input type="text" placeholder="홍길동" required/>
			</div>
			<div class="todo_regist">
				<h3>우선 순위를 선택하세요</h3>
				<input type="radio" name="priority" value="1" required/> <span>1순위</span> 
				<input type="radio" name="priority" value="2" required/> <span>2순위</span> 
				<input type="radio" name="priority" value="3" required/> <span>3순위</span>
			</div>
			<div class="todo_regist">
				<button type="button" onclick="window.location.href='/main'" id="back_page">← 이전</button>
				<button type="submit" class="blue-btn">제출</button>
				<button type="reset" class="blue-btn">내용 지우기</button>
			</div>
		</form>
	</section>

</body>
</html>