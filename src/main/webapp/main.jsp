<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="org.json.*"%>


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
				<th>TODO</th>
				<th>DOING</th>
				<th>DONE</th>
			</tr>
			<tr>
				<td>
					<ul>
						<c:if test="${todoList.length() ne 0}">
							<c:forEach begin="0" end="${todoList.length() -1}" var="index">
								<li>
									<h3 class="content_title">${todoList.getJSONObject(index).get("title")}</h3>
									<p>
										등록날짜 : ${todoList.getJSONObject(index).get("regdate")},
										${todoList.getJSONObject(index).get("name")} 우선순위
										${todoList.getJSONObject(index).get("sequence")}
										<button class="content_move_button">-></button>
									</p>
								</li>
							</c:forEach>
						</c:if>
					</ul>
				</td>
				<td>
					<ul>
						<c:if test="${doingList.length() ne 0}">
							<c:forEach begin="0" end="${doingList.length() -1}" var="index">
								<li>
									<h3 class="content_title">${doingList.getJSONObject(index).get("title")}</h3>
									<p>
										등록날짜 : ${doingList.getJSONObject(index).get("regdate")},
										${doingList.getJSONObject(index).get("name")} 우선순위
										${doingList.getJSONObject(index).get("sequence")}
										<button class="content_move_button">-></button>
									</p>
								</li>
							</c:forEach>
						</c:if>
					</ul>
				</td>
				<td>
					<ul>
						<c:if test="${doneList.length() ne 0}">
							<c:forEach begin="0" end="${doneList.length() -1}" var="index">
								<li>
									<h3 class="content_title">${doneList.getJSONObject(index).get("title")}</h3>
									<p>
										등록날짜 : ${doneList.getJSONObject(index).get("regdate")},
										${doneList.getJSONObject(index).get("name")} 우선순위
										${doneList.getJSONObject(index).get("sequence")}
									</p>
								</li>
							</c:forEach>
						</c:if>

					</ul>
				</td>
			</tr>
		</table>
	</section>

</body>
</html>
