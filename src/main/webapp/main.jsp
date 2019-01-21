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
<%!public int max(int left, int right) {
		if (left > right)
			return left;
		else
			return right;
	}

	final String[] todoLabel = {"TODO", "DOING", "DONE"};%>
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
	background: skyblue;
	width: 250px;
	margin: 20px;
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

.do_noitem {
	background: whilte;
}

button {
	float: right;
	margin-right: 10px;
	height: 23px;
}

th {
	height: 75px;
	width: 200px;
	border-style: solid;
	border-width: 5px;
	border-color: white;
	padding-left: 20px;
}

.do_description {
	font-weight: lighter;
	font-size: 0.8em;
}
</style>
</head>

<body class="container">
	<br>
	<br>
	<span class="title_tag">나의 해야할 일들</span>
	<a href="" class="add_new_do">새로운 TODO 등록</a>
	<br>
	<section class="art_container">
		<table>
			<tr class="do_title">
				<th>TODO</th>
				<th>DOING</th>
				<th>DONE</th>
			</tr>
			<%
				ArrayList<TodoDto>[] todoSeqence = new ArrayList[todoLabel.length];
				for (int i = 0; i < todoSeqence.length; i++)
					todoSeqence[i] = new ArrayList<TodoDto>();

				request.setCharacterEncoding("utf-8");
				String jsonText = (String)request.getAttribute("json");

				ObjectMapper mapper = new ObjectMapper();

				List<TodoDto> jsonItems = mapper.readValue(jsonText,
					mapper.getTypeFactory().constructCollectionType(List.class, TodoDto.class));

				for (TodoDto item : jsonItems) {
					for (int seqIdx = 0; seqIdx < todoLabel.length; seqIdx++) {
						if (todoLabel[seqIdx].equals(item.getType())) {
							todoSeqence[seqIdx].add(item);
							break;
						}
					}
				}

				int maxArraySize = todoSeqence[0].size();
				for (int seqIdx = 1; seqIdx < todoSeqence.length; seqIdx++) {
					maxArraySize = max(maxArraySize, todoSeqence[seqIdx].size());
				}

				for (int i = 0; i < maxArraySize; i++) {
					out.print("<tr>");
					for (int seqIdx = 0; seqIdx < todoSeqence.length; seqIdx++) {
						if (i < todoSeqence[seqIdx].size()) {
							TodoDto target = todoSeqence[seqIdx].get(i);
							String formatDate = target.getRegdate().split(" ")[0].replace('-', '/');

							out.print("<th class=\"do_item\"><span class=\"do_name\">" + target.getTitle() +
								"</span><br><span class=\"do_description\">등록날짜:" + formatDate +
								". " + target.getName() +
								". 우선순위 " + target.getSequence() + "</span>");

							if (seqIdx != todoSeqence.length - 1)
								out.print("<button>→</button>");
							out.print("</th>");
						} else {
							out.print("<th class=\"do_noitem\"></th>");
						}
					}
					out.print("</tr>");
				}
			%>
		</table>
	</section>
</body>
</html>