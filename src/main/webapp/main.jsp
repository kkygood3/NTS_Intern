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
		<div class="container">
			<h1>
				나의 해야 할 일들
			</h1>
			<a class="add_btn" href="./todoform">새로운 TODO 등록</a>
			<div class="todo_list_container">
				<ul>
					<li class="todo_cell todo_category">TODO</li>
					<li class="todo_cell todo_content">
						<h1>자바스크립트 공부하기</h1>
						<h5>등록날짜:2019.01.17, 홍길동, 우선순위 1</h5>
						<button class="next_btn">&rarr;</button>
					</li>
					<li class="todo_cell todo_content">
						<h1>자바스크립트 공부하기</h1>
						<h5>등록날짜:2019.01.17, 홍길동, 우선순위 1</h5>
						<button class="next_btn">&rarr;</button>
					</li>
				</ul>	
				<ul>
					<li class="todo_cell todo_category">DOING</li>
					<li class="todo_cell todo_content">
						<h1>자바스크립트 공부하기</h1>
						<h5>등록날짜:2019.01.17, 홍길동, 우선순위 1</h5>
						<button class="next_btn">&rarr;</button>
					</li>
					<li class="todo_cell todo_content">
						<h1>자바스크립트 공부하기</h1>
						<h5>등록날짜:2019.01.17, 홍길동, 우선순위 1</h5>
						<button class="next_btn">&rarr;</button>
					</li>
					<li class="todo_cell todo_content">
						<h1>자바스크립트 공부하기</h1>
						<h5>등록날짜:2019.01.17, 홍길동, 우선순위 1</h5>
						<button class="next_btn">&rarr;</button>
					</li>
				</ul>
				<ul>
					<li class="todo_cell todo_category">DONE</li>
					<li class="todo_cell todo_content">
						<h1>자바스크립트 공부하기</h1>
						<h5>등록날짜:2019.01.17, 홍길동, 우선순위 1</h5>
						<button class="next_btn">&rarr;</button>
					</li>
					<li class="todo_cell todo_content">
						<h1>자바스크립트 공부하기</h1>
						<h5>등록날짜:2019.01.17, 홍길동, 우선순위 1</h5>
						<button class="next_btn">&rarr;</button>
					</li>
				</ul>
			</div>
		</div>
	</body>
</html>