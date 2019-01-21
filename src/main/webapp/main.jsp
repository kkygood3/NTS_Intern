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
<link rel="stylesheet" type="text/css" href="css/main.css">
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