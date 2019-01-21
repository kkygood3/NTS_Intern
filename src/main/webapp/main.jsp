<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">
<title>할일목록 화면</title>
<link rel="stylesheet" href="./css/layout.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<div class="content">
		<div id="header">나의 해야할 일들</div>
		<button class="button_todo">새로운 TODO 등록</button>
		<div id="list_board">
			<div class="container">
				<span class="title">TODO</span>
				<c:forEach var="todo" items="${todoList }">
					<div class="card">
						<span class="title">${todo.title }</span><br/>
						<span>
							등록날짜 ${todo.regdate } ${todo.name } 우선순위 ${todo.sequence }
							<button>→</button>
						</span>
					</div>
				</c:forEach>
			</div>
			<div class="container">
				<span class="title">DOING</span>
				<c:forEach var="todo" items="${doingList }">
					<div class="card">
						<span class="title">${todo.title }</span><br/>
						<span>
							등록날짜 ${todo.regdate } ${todo.name } 우선순위 ${todo.sequence }
							<button>→</button>
						</span>
					</div>
				</c:forEach>
			</div>
			<div class="container">
				<span class="title">DONE</span>
				<c:forEach var="todo" items="${doneList }">
					<div class="card">
						<span class="title">${todo.title }</span><br/>
						<span>
							등록날짜 ${todo.regdate } ${todo.name } 우선순위 ${todo.sequence }
							<button>→</button>
						</span>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>