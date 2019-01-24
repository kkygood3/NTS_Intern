<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Todo List</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
</head>

<body class="container">
	<br>
	<br>
	<span class="title_tag">나의 해야할 일들</span>
	<a href="register" class="add_new_do">새로운 TODO 등록</a>
	<br>
	<section class="art_container">		
		<article class='art_TODO'>
			<div class='div_title'>TODO</div>
			<c:forEach var="todo" items="${todoList}">
				<p>
					<span class='do_name'>
						${todo.title}
					</span><br>
					<span class='do_description'>
						등록날짜:${todo.regdate}. ${todo.name}. 우선순위 ${todo.sequence}
					</span>
					<button name="${todo.id}">→</button>
				</p>
			</c:forEach>
		</article>
		
		<article class='art_DOING'>
			<div class='div_title'>DOING</div>
			<c:forEach var="doing" items="${doingList}">
				<p>
					<span class='do_name'>
						${doing.title}
					</span><br>
					<span class='do_description'>
						등록날짜:${doing.regdate}. ${doing.name}. 우선순위 ${doing.sequence}
					</span>
					<button name="${doing.id}">→</button>
				</p>
			</c:forEach>
		</article>
		
		<article class='art_DONE'>
			<div class='div_title'>DONE</div>
			<c:forEach var="done" items="${doneList}">
				<p>
					<span class='do_name'>
						${done.title}
					</span><br>
					<span class='do_description'>
						등록날짜:${done.regdate}. ${done.name}. 우선순위 ${done.sequence}
					</span>
				</p>
			</c:forEach>
		</article>
	</section>
</body>

<script>
	function updateRequest(btn, type) {
		var oReq = new XMLHttpRequest();
		oReq.addEventListener('load', function(e) {
			if (e.target.response != 'success') {
				alert('Update fail');
			}
		});
		oReq.open('get', 'update?id=' + btn.getAttribute('name') + '&type=' + type);
		oReq.send();
	}

	function clickEvent(event) {
		if (event.target.tagName === 'BUTTON') {
			var clickedBtn = event.target;
			var parentPTag = clickedBtn.parentElement;
			var type = parentPTag.parentElement.querySelector('div').innerText;

			updateRequest(clickedBtn, type)

			if (type === 'TODO') {
				var artDoing = document.querySelector('.art_DOING');
				artDoing.appendChild(parentPTag);
			} else {
				var artDone = document.querySelector('.art_DONE');
				artDone.appendChild(parentPTag)
				event.target.remove();
			}
		}
	}

	var artContainer = document.querySelector('.art_container');
	artContainer.addEventListener('click', clickEvent);
</script>
</html>