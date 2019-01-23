<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="ko">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ToDo List Web</title>
		<link rel="stylesheet" href="./css/index.css">
	</head>
	<body>
		<div class="todo_page_container">
			<h1>할일 등록</h1>
			<form class="todo_form" action="./addTodo" method="post" accept-charset="UTF-8">
				<label for="todo_title_input">어떤일인가요?</label>
				<input type="text" id="todo_title_input" name="todo_title" placeholder="swift 공부하기(24글자까지)" maxlength="24" required>
				<label for="todo_name_input">누가 할일인가요?</label>
				<input type="text" id="todo_name_input" name="todo_name" placeholder="홍길동" required>
				<label>우선순위를 선택하세요.</label>
				<div class="radio_group">
					<input type="radio" id="todo_sequence_first" name="todo_sequence" value="1" checked>
					<label for="todo_sequence_first">1순위</label>
					<input type="radio" id="todo_sequence_second" name="todo_sequence" value="2">
					<label for="todo_sequence_second">2순위</label>
					<input type="radio" id="todo_sequence_third" name="todo_sequence" value="3">
					<label for="todo_sequence_third">3순위</label>
				</div>
				<a class="back_btn" href="./main">&lt; 이전</a>
				<input type="submit" class="submit_btn" value="제출"></input>
				<input type="reset" class="reset_btn" value="내용지우기"></input>
			</form>
		</div>
	</body>
</html>

