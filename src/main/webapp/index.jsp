<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%@ page import="java.util.*, com.nts.TodoDAO, com.nts.Todo"%>

<jsp:useBean id = "dao" class = "com.nts.TodoDAO"/>

<%
	List<List<Todo>> list = dao.getList();
	List<Todo> todoList = list.get(0);
	List<Todo> doingList = list.get(1);
	List<Todo> doneList = list.get(2);
%>

<!DOCTYPE html>
<html lang=ko>
<head>
<meta charset="utf-8">
<title>이재원 TO-DO INDEX 페이지 입니다</title>
<link rel="stylesheet" href="css/base.css">
</head>

<body>

	<header>
		<p class="header_left_text">나의 해야할 일들</p>
		<div class='button-wrapper'>
			<button id="generate_todo" class="header_right_button">새로운
				TODO 등록</button>
		</div>
	</header>
	<section>
		<table>
			<tr>
				<th>TODO</th>
				<th>DOING</th>
				<th>DONE</th>
			</tr>
			<tr>
				<td>
					<ul>
						
						<c:forEach items="todoList" var="item">
							<li>
								<h3 class="content_title">11</h3>
								<p>
									등록날짜 : 2018.03.10, 홍길동, 우선순위 1
									<button class="content_move_button">-></button>
								</p>
							</li>
						</c:forEach>
						<%
							for (Todo todo : todoList) {
						%>
						<li>
							<h3 class="content_title">자바스크립트 공부하기공부하기공부하기공부하기공부하기</h3>
							<p>
								등록날짜 : 2018.03.10, 홍길동, 우선순위 1
								<button class="content_move_button">-></button>
							</p>
						</li>
						<%
							}
						%>
					</ul>
				</td>
				<td>
					<ul>
						
						<li>1</li>
						<li>1</li>
						<li>1</li>
					</ul>
				</td>
				<td>
					<ul>
						<li>1</li>
					</ul>
				</td>
			</tr>
		</table>
	</section>

</body>
</html>
