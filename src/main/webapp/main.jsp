<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="UTF-8">
<title>Todo List</title>
</head>
<body>

	<div class="content">
		<header class="header_wrap">
			<h2 class="rotated_title">나의 해야할 일들</h2>

			<a href="/todoForm" class="go_register_button">새로운 TODO등록</a>
		</header>

		<section class="content_main">
			<!-- TODO -->
			<aside class="content_box" id="todo">
				<h3 class="title">TODO</h3>

				<c:forEach items="${ todoList }" var="todoList">
					<article class="card" id="${ todoList.id }">
						<h3>${ todoList.title }</h3>
						등록날짜: ${ todoList.regdate }, ${ todoList.name }, ${ todoList.sequence }
						<input type="hidden" name="id" value="${ todoList.id }">
						<input type="hidden" name="type" value="${ todoList.type }">
						<button class="type_change_button" onclick="change_type()">→</button>
					</article>
				</c:forEach>
				
			</aside>

			<!-- DOING -->
			<aside class="content_box" id="doing">
				<h3 class="title">DOING</h3>

				<c:forEach items="${ doingList }" var="doingList">
					<article class="card" id="${ doingList.id }">
						<h3>${ doingList.title }</h3>
						등록날짜: ${ doingList.regdate }, ${ doingList.name }, ${ doingList.sequence }
						<input type="hidden" name="id" value="${ doingList.id }">
						<input type="hidden" name="type" value="${ doingList.type }">
						<button class="type_change_button" onclick="change_type()">→</button>
					</article>
				</c:forEach>
			</aside>

			<!-- DONE -->
			<aside class="content_box" id="done">
				<h3 class="title">DONE</h3>
				<c:forEach items="${ doneList }" var="doneList">
					<article class="card" id="${ doneList.id }">
						<h3>${ doneList.title }</h3>
						등록날짜: ${ doneList.regdate }, ${ doneList.name }, ${ doneList.sequence }
					</article>
				</c:forEach>
			</aside>
		</section>

		<div style="clear: both;"></div>
	</div>
</body>

<script>

function change_type(){
	var parent_element = event.srcElement.parentElement;	// button의 parent
	var id = parent_element.id;
	var type = parent_element.children[2].value;
	
	// Ajax
	var httpRequest = new XMLHttpRequest();
	httpRequest.addEventListener("load", function(){
		var result = this.responseText;
		if(result == "success"){
			
			if(type == "TODO"){
				var doing_aside = document.getElementById("doing");
				var doing_aside_childNodes_length = doing_aside.childNodes.length;
				
				// change type
				parent_element.children[2].value = "DOING"
				
				// move next aside
				if(doing_aside_childNodes_length > 0){
					doing_aside.insertBefore(parent_element, doing_aside.childNodes[doing_aside_childNodes_length-1].nextSibling);
				}else{
					doing_aside.insertBefore(parent_element, doing_aside.children[0]);
				}
				
			}else if(type == "DOING"){
				var done_aside = document.getElementById("done");
				var done_aside_childNodes_length = done_aside.childNodes.length;
				
				// change type
				parent_element.children[2].value = "DONE"
				
				// remove button
				parent_element.removeChild(parent_element.childNodes[7]);
				
				// move next aside
				if(done_aside_childNodes_length > 0){
					done_aside.insertBefore(parent_element, done_aside.childNodes[done_aside_childNodes_length - 1].nextSibling);
				}else{
					done_aside.insertBefore(parent_element, done_aside.children[0]);
				}
			}
		}else{
			alert("ERROR");
		}
		
	});
	httpRequest.open("GET", "/changeType?id=" + id + "&type=" + type);
	httpRequest.send();
}
</script>
</html>