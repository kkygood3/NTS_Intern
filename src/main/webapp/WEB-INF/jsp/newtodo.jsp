<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="/css/newtodo.css" />
		<title>할일 등록</title>
	</head>
	<body>
		<section class="new_todo_section">
			<h1 class="title">할일 등록</h1>
			<form method="post" action="/newtodo">
				<p class="input_area">
					어떤일인가요?<br>
					<input class="text_box" type="text" name="title" placeholder="최대 24글자" maxlength="24" required/>
				</p>
				<p class="input_area">
					누가 할일인가요?<br>
					<input class="text_box" type="text" name="name" placeholder="홍길동" maxlength="24" required/>
				</p>
				
				<p class="input_area">
					우선순위를 선택하세요<br>
					<input type="radio" name="sequence" value="1" required>1순위
					<input type="radio" name="sequence" value="2" required>2순위
					<input type="radio" name="sequence" value="3" required>3순위
				</p>
				
				<p></p>
				<a class="btn" href="/main">&lt이전</a>
				<span class="right">
					<input class="btn" type="submit" value="제출">
					<input class="btn" type="reset" value="내용지우기">
				</span>
			</form>
		</section>
	</body>
</html>