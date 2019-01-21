<%@page import="com.nts.dao.TodoDao"%>
<%@page import="javax.swing.text.Document"%>
<%@page import="java.util.List"%>
<%@page import="com.fasterxml.jackson.core.type.TypeReference"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="com.fasterxml.jackson.core.JsonParser"%>
<%@page import="com.mysql.cj.xdevapi.DbDoc"%>
<%@page import="com.nts.dto.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%!final String[] todoLabel = {"Todo", "Doing", "Done"};%>
<head>
<meta charset="utf-8">
<title>JS Bin</title>
<style>
html {
	min-width: 1230px;
	height: 100%;
	width: 100%;
	text-align: center;
	background: #ebeded;
}

body {
	background: white;
	width: 80%;
	height: 70%;
	padding: 20px;
	display: inline-block;
	overflow: auto;
}

ul {
	display: inline-block;
}

li {
	list-style: none;
}

section {
	margin-top: 50px;
	margin-left: 50px;
	padding: 0px;
	text-align: left;
}

.title_tag {
	font-size: 1.8em;
	font-weight: bold;
	color: #2f5164;
	float: left;
	transform: rotate(-30deg)
}

.add_new_do {
	display: inline-block;
	text-decoration: none;
	font-weight: bold;
	color: white;
	background: #20adee;
	text-align: center;
	width: 200px;
	height: 30px;
	padding-top: 5px;
	float: right;
}

.art_container>article {
	display: inline-block;
	width: 30%;
	float: left;
	margin-left: 5px;
}

table {
	width: 100%;
}

.do_title {
	background: #345461;
	color: white;
	font-size: 1.2em;
}

.do_item {
	background: #cee5ef;
}

th {
	height: 75px;
	width: 200px;
	border-style: solid;
	border-width: 5px;
	border-color: white;
	padding-left: 20px;
}

.do_name {
	font-weight: bold;
}

.do_description {
	font-weight: lighter;
	font-size: 0.8em;
}

div {
	padding: 17px;
	margin-top: 6px;
}

.div_title {
	background: #345461;
	height: 35px;
	font-weight: bold;
	color: white;
	font-size: 1.6em;
}

p {
	padding: 17px;
	margin-top: 5px;
	margin-bottom: 0px;
	background: #cee5ef;
	padding-right: 5px;
}

button {
	height: 23px;
	float: right;
}
</style>
</head>

<body class="container">
	<br>
	<br>
	<span class="title_tag">나의 해야할 일들</span>
	<a href="register" class="add_new_do">새로운 TODO 등록</a>
	<br>
	<section class="art_container">
		<%
			ArrayList<TodoDto>[] todoSeqence = new ArrayList[todoLabel.length];
			for (int i = 0; i < todoSeqence.length; i++)
				todoSeqence[i] = new ArrayList<TodoDto>();

			request.setCharacterEncoding("utf-8");
			String jsonText = (String) request.getAttribute("json");

			ObjectMapper mapper = new ObjectMapper();

			List<TodoDto> jsonItems = mapper.readValue(jsonText,
					mapper.getTypeFactory().constructCollectionType(List.class, TodoDto.class));

			for (int labelIdx = 0; labelIdx < todoLabel.length; labelIdx++) {
				out.print("<article class='art_" + todoLabel[labelIdx] + "'><div class='div_title'>"
						+ todoLabel[labelIdx] + "</div>");
				for (TodoDto target : jsonItems) {
					if (target.getType().equalsIgnoreCase(todoLabel[labelIdx])) {
						String description = "등록날짜:" + target.getRegdate().split(" ")[0].replace('-', '/') + ". "
								+ target.getName() + ". " + "우선순위 " + target.getSequence();
						out.print("<p>");
						out.print("<span class='do_name'>");
						out.print(target.getTitle());
						out.print("</span><br>");
						out.print("<span class='do_description'>");
						out.print(description);
						out.print("</span>");

						//버튼
						if (labelIdx != todoLabel.length - 1) {
							String btnValue = target.getType() + "|" + target.getTitle() + "|" + description;
							out.print("<button>→</button>");
						}
						out.print("</p>");
					}
				}
				out.print("</article>");
			}
		%>
	</section>
	<script>
	function clickEvent(event){
		var type = event.target.parentElement.parentElement.getElementsByTagName('div')[0].innerText;
		
		if(type ==='Todo'){
			var artDoing = document.getElementsByClassName('art_Doing')[0];
			var clickedTag = event.target.parentElement;
			artDoing.innerHTML += "<p>"+clickedTag.innerHTML+"</p>";
			clickedTag.remove();
			
			var btns = document.getElementsByTagName('button');

			for(var i = 0; i < btns.length; i++)
				btns[i].addEventListener('click',clickEvent);
		}else{
			var artDone = document.getElementsByClassName('art_Done')[0];
			var clickedTag = event.target.parentElement;
			artDone.innerHTML += "<p>"+clickedTag.innerHTML+"</p>";
			clickedTag.remove();
			
			var pTags = document.getElementsByClassName("art_Done")[0].getElementsByTagName("p");
			pTags[pTags.length-1].getElementsByTagName('button')[0].remove();
		}
	}
	
	var btns = document.getElementsByTagName('button');

	for(var i = 0; i < btns.length; i++)
		btns[i].addEventListener('click',clickEvent);
	</script>
</body>
</html>