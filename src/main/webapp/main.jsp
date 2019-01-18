<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang=ko>
<head>
<meta charset="utf-8">
<title>이재원 TO-DO INDEX 페이지 입니다</title>
<link rel="stylesheet" href="/jaewonlee/css/base.css">
</head>

<body>
	<header>
		<p class="header_left_text">나의 해야할 일들</p>
		<div class="button">
			<a href="/jaewonlee/todoform" id="generate_todo"
				class="header_right_button">새로운 TODO 등록</a>
		</div>
	</header>
	<section>
		<table>
			<tr>
				<th>TODO ${todoList.size()}</th>
				<th>DOING</th>
				<th>DONE</th>
			</tr>
			<tr>
				<td>
					<ul>
						<c:forEach items="${todoList}" var="item">
							<li id="todo_${item.getId()}">
								<h3 class="content_title">${item.getTitle()}</h3>
								<p>
									등록날짜 : ${item.getRegdate()}, ${item.getName()} 우선순위
									${item.getSequence()}
									<button class="content_move_button">-></button>
								</p>
							</li>
						</c:forEach>

					</ul>
				</td>
				<td>
					<ul>
						<c:forEach items="${doingList}" var="item">
							<li id="todo_${item.getId()}">
								<h3 class="content_title">${item.getTitle()}</h3>
								<p>
									등록날짜 : ${item.getRegdate()}, ${item.getName()} 우선순위
									${item.getSequence()}
									<button class="content_move_button">-></button>
								</p>
							</li>
						</c:forEach>
					</ul>
				</td>
				<td>
					<ul>

						<c:forEach items="${doneList}" var="item">
							<li id="todo_${item.getId()}">
								<h3 class="content_title">${item.getTitle()}</h3>
								<p>
									등록날짜 : ${item.getRegdate()}, ${item.getName()} 우선순위
									${item.getSequence()}
									<button class="content_move_button">-></button>
								</p>
							</li>
						</c:forEach>


					</ul>
				</td>
			</tr>
		</table>
	</section>

</body>
</html>
