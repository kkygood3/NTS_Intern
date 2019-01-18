<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="ko">
	<head>
		<meta charset="utf-8">
		<title>ToDo List Web</title>
		<link rel="stylesheet" href="./css/index.css">
	</head>
	<body>
		<div class="todo_page_container">
			<h1>할일 등록</h1>
			<form class="todo_form">
				<label for="todo_title_input">어떤일인가요?</label>
				<input type="text" id="todo_title_input" name="todo_title" placeholder="swift 공부하기(24글자까지)">
				<label for="todo_name_input">누가 할일인가요?</label>
				<input type="text" id="todo_name_input" name="todo_name" placeholder="홍길동">
				<label>우선순위를 선택하세요.</label>
				<div class="radio_group">
					<input type="radio" id="todo_sequence_first" name="todo_sequence" value=1 checked>
					<label for="todo_sequence_first">1순위</label>
					<input type="radio" id="todo_sequence_second" name="todo_sequence" value=2>
					<label for="todo_sequence_second">2순위</label>
					<input type="radio" id="todo_sequence_third" name="todo_sequence" value=3>
					<label for="todo_sequence_third">3순위</label>
				</div>
				<a class="back_btn" href="./main">&lt; 이전</a>
				<button class="submit_btn">제출</button>
				<button class="reset_btn">내용지우기</button>
			</form>
		</div>
	</body>
</html>

