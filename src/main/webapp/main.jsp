<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Copyright 2019 NAVER Corp.
All rights reserved.
Except in the case of internal use for NAVER,
unauthorized use of redistribution of this software are strongly prohibited. -->

<!-- 
	main.jsp implementation
	Author: Jaewon Lee, lee.jaewon@nts-corp.com
 -->

<!DOCTYPE html>
<html lang=ko>
<head>
<meta charset="utf-8">
<title>이재원 TO-DO INDEX 페이지 입니다</title>
<link rel="stylesheet" href="/jaewonlee/css/base.css">
</head>

<body onload="init()">

	<header>
		<p class="header_left_text">나의 해야할 일들</p>
		<div class="header_button_wrapper">
			<a href="/jaewonlee/todoform" id="generate_todo"
				class="header_button_inner">새로운 TODO 등록</a>
		</div>
	</header>

	<section class="section_main">
		<table>
			<tr>
				<th>TODO</th>
				<th>DOING</th>
				<th>DONE</th>
			</tr>
			<tr>
				<td>
					<ul data-section="TODO">
						<c:forEach items="${todoList}" var="item">
							<li class="todoItem" data-id="${item.getId()}">
								<h3 class="content_title">${item.getTitle()}</h3>
								<p>
									등록날짜 : ${item.getRegdate()}, ${item.getName()} 우선순위
									${item.getSequence()}
									<button class="content_move_button">&rarr;</button>
								</p>
							</li>
						</c:forEach>
					</ul>
				</td>

				<td>
					<ul data-section="DOING">
						<c:forEach items="${doingList}" var="item">
							<li class="todoItem" data-id="${item.getId()}">
								<h3 class="content_title">${item.getTitle()}</h3>
								<p>
									등록날짜 : ${item.getRegdate()}, ${item.getName()} 우선순위
									${item.getSequence()}
									<button class="content_move_button">&rarr;</button>
								</p>
							</li>
						</c:forEach>
					</ul>
				</td>

				<td>
					<ul data-section="DONE">
						<c:forEach items="${doneList}" var="item">
							<li class="todoItem" data-id="${item.getId()}">
								<h3 class="content_title">${item.getTitle()}</h3>
								<p>등록날짜 : ${item.getRegdate()}, ${item.getName()} 우선순위
									${item.getSequence()}</p>
							</li>
						</c:forEach>
					</ul>
				</td>
			</tr>
		</table>
	</section>
	
	<script type="text/javascript" src="js/todoScript.js"></script>
	
</body>
</html>
