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
		<button class="button_todo" onclick="location.href='./newTodo'">새로운 TODO 등록</button>
		<div id="list_board">
			<div class="container" id="todo">
				<span class="title">TODO</span>
				<c:forEach var="todo" items="${todoList}">
					<div class="card" id="${todo.id}" type="${todo.type}">
						<span class="title">${todo.title}</span><br/>
						<span>
							등록날짜 ${todo.regdate} ${todo.name} 우선순위 ${todo.sequence}
						</span>
						<button class="typeNextButton">→</button>
					</div>
				</c:forEach>
			</div>
			<div class="container" id="doing">
				<span class="title">DOING</span>
				<c:forEach var="todo" items="${doingList}">
					<div class="card" id="${todo.id}" type="${todo.type}">
						<span class="title">${todo.title}</span><br/>
						<span>
							등록날짜 ${todo.regdate} ${todo.name} 우선순위 ${todo.sequence}
						</span>
						<button class="typeNextButton">→</button>
					</div>
				</c:forEach>
			</div>
			<div class="container" id="done">
				<span class="title">DONE</span>
				<c:forEach var="todo" items="${doneList}">
					<div class="card" id="${todo.id}" type="${todo.type}">
						<span class="title">${todo.title}</span><br/>
						<span>
							등록날짜 ${todo.regdate} ${todo.name} 우선순위 ${todo.sequence}
						</span>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<script>
		// empty, null, undefined 체크 
		function isEmpty(str) {
			return (!str || 0 === str.length);
		}

		// addEventListener로 모든 타입변경버튼들 클릭이벤트 등록
		var typeNextButtons = document.querySelectorAll(".typeNextButton");
		for (var i =0; i < typeNextButtons.length; i++) {
			typeNextButtons[i].addEventListener("click", function(event) {
				updateTodoType(event.target.parentNode);
			});
		}

		function updateTodoType(card) {
			var xhr = new XMLHttpRequest();
			var url = "./updateTodo";
			var id = card.getAttribute("id");
			var type = card.getAttribute("type");

			if (isEmpty(id) || isEmpty(type)) {
				alert("데이터를 확인해주세요");
				return;
			}

			var params = "id=" + id + "&type=" + type;
			xhr.open("PUT", url + '?' + params);
			xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
			xhr.send();

			xhr.addEventListener("load", function(e) {
				type = nextType(type);
				changeCardToContainer(type, card);
			});
			
			xhr.addEventListener("error", function(e) {
				alert("An error occurred while transferring the file.");
			});
		}
		
		// 다음 상태값으로 변경
		function nextType(type) {
			if (type === "TODO") {
				type = "DOING"
			} else if (type === "DOING") {
				type = "DONE"
			}
			return type;
			
		}

		// Todo Card를 지정된 Type 컨테이너div로 옮겨줍니다.
		function changeCardToContainer(type, card) {
			// 마지막 상태값 DONE인 경우 타입변경 버튼제거
			if (type === "DONE") {
				var button = card.getElementsByClassName("typeNextButton")[0];
				card.removeChild(button);
			}
			card.setAttribute("type", type);
			var container = document.getElementById(type.toLowerCase());
			container.appendChild(card)
		}
	</script>
</body>
</html>