<%@page import="java.util.ArrayList"%>
<%@page import="com.nts.dto.TodoDto"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<link rel="stylesheet" href="./css/layout.css">
<link rel="stylesheet" href="./css/index.css">
<title>TODO LIST</title>
</head>
<body>
<div class="container">
	<header>
		<h1>나의 해야할 일들</h1>
		<button onclick="location.href='./newtodo.html'">새로운 TODO 등록</button>
	</header>
	<%
	List<TodoDto> todos = (ArrayList<TodoDto>)request.getAttribute("todos");
	int len = todos.size();
	%>
	<div class="content">
		<ul class="list" id="done">
			<li class="list_header">DONE</li>
			<%
				for (int i = 0; i < len; i++) {
					String type = todos.get(i).getType();
					if (!"DONE".equals(type)) continue;		
			%>
			<li class="list_item">
				<p class="title"><%=todos.get(i).getTitle() %></p>
				<p class="discription"><%=todos.get(i) %></p>
				<button onclick="button_clicked(event)">→</button>
			</li>
			<%
				}
			%>
		</ul>
		<ul class="list" id="doing">
			<li class="list_header">DOING</li>
			<%
				for (int i = 0; i < len; i++) {
					String type = todos.get(i).getType();
					if (!"DOING".equals(type)) continue;
			%>
			<li class="list_item" id=<%=todos.get(i).getId() %>>
				<p class="title"><%=todos.get(i).getTitle() %></p>
				<p class="discription"><%=todos.get(i) %></p>
				<button onclick="button_clicked(event)">→</button>
			</li>
			<%
				}
			%>
		</ul>
		<ul class="list" id="todo">
			<li class="list_header">TODO</li>
			<%
				for (int i = 0; i < len; i++) {
				String type = todos.get(i).getType();
					System.out.println("in for statment : " + todos.get(i));
					if (!"TODO".equals(type)) continue;
			%>
			<li class="list_item" id=<%=todos.get(i).getId() %>>
				<p class="title"><%=todos.get(i).getTitle() %></p>
				<p class="discription"><%=todos.get(i) %></p>
				<button onclick="button_clicked(event)">→</button>
			</li>
			<%
				}
			%>
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
 			httpRequest.onreadystatechange = alertContents(e);
 			httpRequest.open("PUT", "./todo/" + e.target.parentElement.id);
 			httpRequest.send();
		}
		function alertContents(e) {
			alert("!");
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
