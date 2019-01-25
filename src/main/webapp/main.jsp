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
	<section id="section-content-main">
		<div id="content">
			<article class="article-list">
				<ul id="ul-DONE" class="ul-list">
					<li class="li-title"><h1>DONE</h1></li>
					<c:forEach var="todoDto" items="${ doneList }">
						<li id="li-content-${ todoDto.getId() }" class="li-content">
							<h3>${ todoDto.getTitle() }</h3>
							<span class="span-content">
								등록날짜: ${ todoDto.getRegdate() }, ${ todoDto.getName() }, ${ todoDto.getSequence() }
							</span>
						</li>
					</c:forEach>
				</ul>
			</article>
			<article class="article-list">
				<ul id="ul-DOING" class="ul-list">
					<li class="li-title"><h1>DOING</h1></li>
					<c:forEach var="todoDto" items="${ doingList }">
						<li id="li-content-${ todoDto.getId() }" class="li-content">
							<h3>${ todoDto.getTitle() }</h3>
							<span class="span-content">
								등록날짜: ${ todoDto.getRegdate() }, ${ todoDto.getName() }, ${ todoDto.getSequence() }
								<button class="updateButton">→</button>
							</span>
						</li>
					</c:forEach>
				</ul>
			</article>
			<article class="article-list">
				<ul id="ul-TODO" class="ul-list">
					<li class="li-title"><h1>TODO</h1></li>
					<c:forEach var="todoDto" items="${ todoList }">
						<li id="li-content-${ todoDto.getId() }" class="li-content">
							<h3>${ todoDto.getTitle() }</h3>
							<span class="span-content">
								등록날짜: ${ todoDto.getRegdate() }, ${ todoDto.getName() }, ${ todoDto.getSequence() }
								<button class="updateButton">→</button>
							</span>
						</li>
					</c:forEach>
				</ul>
			</article>
		</div>
	</section>
</body>
<script type="text/javascript" src="/js/updateType.js" ></script>
</html>