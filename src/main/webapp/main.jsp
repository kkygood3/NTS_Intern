<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="/css/main.css" />
		<title>나의 해야할 일들</title>
	</head>
	<body>
		<span class="page_title">나의 해야할 일들</span>
		<a href="/newtodo"><span class="right box">새로운 TODO 등록</span></a>
		
		<section class="todo_section">
		
			<article class="todo">
				<div class="title-item">TODO</div>
				<ul class="item_list">
					<c:forEach var="todo" items="${todos['TODO']}">
						<li id="todo_list_${todo.id}" class="item">
							<h2>${todo.title}</h2>
							<fmt:parseDate var="regDate" value="${todo.regdate}" pattern="yyyy-MM-dd HH:mm:ss.s"/>
							<span>
							등록 날짜 : <fmt:formatDate value="${regDate}" pattern="yyyy.MM.dd" />,
							${todo.name}, 우선순위 : ${todo.sequence}
							</span>
							<button class="move" onclick="changeTodoType(${todo.id},'TODO')">➜</button>
						</li>
					</c:forEach>
				</ul>
			</article>
	
			<article class="doing">
				<div class="title-item">DOING</div>
				<ul id="doing_list" class="item_list">
					<c:forEach var="todo" items="${todos['DOING']}">
						<li id="todo_list_${todo.id}" class="item">
							<h2>${todo.title}</h2>
							<fmt:parseDate var="regDate" value="${todo.regdate}" pattern="yyyy-MM-dd HH:mm:ss.s"/>
							<span>
							등록 날짜 : <fmt:formatDate value="${regDate}" pattern="yyyy.MM.dd" />,
							${todo.name}, 우선순위 : ${todo.sequence}
							</span>
							<button class="move" onclick="changeTodoType(${todo.id},'DOING')">➜</button>
						</li>
					</c:forEach>
				</ul>
			</article>
	
			<article class="done">
				<div class="title-item">DONE</div>
				<ul id="done_list" class="item_list">
					<c:forEach var="todo" items="${todos['DONE']}">
						<li id="todo_list_${todo.id}" class="item">
							<h2>${todo.title}</h2>
							<fmt:parseDate var="regDate" value="${todo.regdate}" pattern="yyyy-MM-dd HH:mm:ss.s"/>
							<span>
							등록 날짜 : <fmt:formatDate value="${regDate}" pattern="yyyy.MM.dd" />,
							${todo.name}, 우선순위 : ${todo.sequence}
							</span>
						</li>
					</c:forEach>
				</ul>
			</article>
			
		</section>
	</body>
	<script type="text/javascript" src="/js/move.js"></script>
</html>