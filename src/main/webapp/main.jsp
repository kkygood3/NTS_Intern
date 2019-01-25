<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/base.css">
<meta charset="UTF-8">
<title>TO-DO LIST</title>
</head>
<body>
	<header>
		<section id="menu">
			<div id="myTodo">
				 나의 해야할 일들
			</div>
			<a href="/todoForm" id="regTodo">새로운 TODO 등록</a>
		</section>
	</header>
	<section>
		<div id="content">
			<article class="article-list">
				<ul id="ul-done" class="ul-list">
					<li class="li-title"><h1>DONE</h1></li>
					<li class="li-content">
						<h3>title 7</h3>
						<p>regdate, name, sequence</p>
					</li>
					<li class="li-content">
						<h3>title 8</h3>
						<p>regdate, name, sequence</p>
					</li>
					<li class="li-content">
						<h3>title 9</h3>
						<p>regdate, name, sequence</p>
					</li>
				</ul>
			</article>
			<article class="article-list">
				<ul id="ul-doing" class="ul-list">
					<li class="li-title"><h1>DOING</h1></li>
					<li class="li-content">
						<h3>title 3</h3>
						<p>regdate, name, sequence</p>
					</li>
					<li class="li-content">
						<h3>title 4</h3>
						<p>regdate, name, sequence</p>
					</li>
					<li class="li-content">
						<h3>title 5</h3>
						<p>regdate, name, sequence</p>
					</li>
					<li class="li-content">
						<h3>title 6</h3>
						<p>regdate, name, sequence</p>
					</li>
				</ul>
			</article>
			<article class="article-list">
				<ul id="ul-todo" class="ul-list">
					<li class="li-title"><h1>TODO</h1></li>
					<c:forEach var="todo" items="${ todos }">
						<li class="li-content">
							<h3>${ todo.getTitle() }</h3>
							<p>등록날짜: ${ todo.getRegdate() }, ${ todo.getName() }, ${ todo.getSequence() }</p>
							<button name="${ todoDto.getId() }">→</button>
						</li>
					</c:forEach>
				</ul>
			</article>
		</div>
	</section>
</body>
<script type="text/javascript" src="/js/typeUpdate.js" ></script>
</html>