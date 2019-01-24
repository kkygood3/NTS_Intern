<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="./css/layout.css">
<link rel="stylesheet" href="./css/index.css">
<title>TODO LIST</title>
</head>
<body>
<div class="container">
	<header>
		<h1>나의 해야할 일들</h1>
		<button onclick="location.href='./todo'">새로운 TODO 등록</button>
	</header>
	<div class="content">
		<ul class="list" id="done">
			<li class="list_header">DONE</li>
			<c:forEach items="${done}" var="item">
				<li class="list_item" id="${item.getId()}">
					<p class="title">${item.getTitle()}</p>
					<p class="discription">${item}</p>
					<button onclick="button_clicked(event)">→</button>
				</li>
			</c:forEach>
		</ul>
		<ul class="list" id="doing">
			<li class="list_header">DOING</li>
			
			<c:forEach items="${doing}" var="item">
				<li class="list_item" id="${item.getId()}">
					<p class="title">${item.getTitle()}</p>
					<p class="discription">${item}</p>
					<button onclick="button_clicked(event)">→</button>
				</li>
			</c:forEach>
		</ul>
		<ul class="list" id="todo">
			<li class="list_header">TODO</li>
			<c:forEach items="${todo}" var="item">
				<li class="list_item" id="${item.getId()}">
					<p class="title">${item.getTitle()}</p>
					<p class="discription">${item}</p>
					<button onclick="button_clicked(event)">→</button>
				</li>
			</c:forEach>
		</ul>
		<div class="clear"></div>
	</div>
</div>
	<script>
		var httpRequest;
		function button_clicked(e) {
 			httpRequest = new XMLHttpRequest();
 			if(!httpRequest) {
 				return false;
 			}
 			id = e.target.parentElement.id,
 			type = e.target.parentElement.parentElement.id

 			httpRequest.open("PUT", "./todo/update?id=" + id + "&type=" + type);
 			httpRequest.onreadystatechange = alertContents(e);
 			httpRequest.send();
		}
		function alertContents(e) {
			var from = e.target.closest('ul').id;
			if (from == "todo") {
				document.getElementById("doing").appendChild(e.target.parentElement); 
			} else if (from == "doing") {
				document.getElementById("done").appendChild(e.target.parentElement);
				e.target.style.visibility = "hidden";
			}
		}	
	</script>
</body>
</html>
