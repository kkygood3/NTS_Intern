<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="/static/css/index.css">
<title>TODO LIST</title>
</head>
<body>

	<header>
		<h1 id="my_todo">나의 해야할 일들</h1>
		<a href="/newtodo" class="link_color_white">
			<span id="new_todo_btn">새로운 TODO 등록 </span>
		</a>
	</header>

	<section>
		<ul id="todo" class="progress">
			<li class="title">
				<h1>TODO</h1>
			</li>
			<li>
				<h3 class="content">자바스크립트 공부하기</h3>
				<p class="content">
					등록날짜:2019.01.18 전연빈 우선순위 1 
					<button type="button" >→</button>
				</p>
			</li>
			<li>
				<h3 class="content">자바스크립트 공부하기</h3>
				<p class="content">
					등록날짜:2019.01.18 전연빈 우선순위 1 
					<button type="button" >→</button>
				</p>
			</li>
			<li>
				<h3 class="content">자바스크립트 공부하기</h3>
				<p class="content">
					등록날짜:2019.01.18 전연빈 우선순위 1 
					<button type="button" >→</button>
				</p>
			</li>
		</ul>
		<ul id="doing" class="progress">
			<li class="title">
				<h1>DOING</h1>
			</li>
			<li>
				<h3 class="content">데모페이지 개발</h3>
				<p class="content">
					등록날짜:2019.01.18 전연빈 우선순위 1 
					<button >→</button>
				</p>
			</li>
		</ul>
		<ul id="done" class="progress">
			<li class="title">
				<h1>DONE</h1>
			</li>
			<li>
				<h3 class="content">CSS 학습</h3>
				<p class="content">
					등록날짜:2019.01.18 전연빈 우선순위 1 
				</p>
			</li>
		</ul>
	</section>

</body>
</html>