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
			<c:forEach var="target" items="${TODOlist}">
				<p>
					<span class='do_name'>
						${target.title}
					</span><br>
					<span class='do_description'>
						등록날짜:${target.regdate}. ${target.name}. 우선순위 ${target.sequence}
					</span>
					<button name="${target.id}">→</button>
				</p>
			</c:forEach>
		</article>
		
		<article class='art_DOING'>
			<div class='div_title'>DOING</div>
			<c:forEach var="target" items="${DOINGlist}">
				<p>
					<span class='do_name'>
						${target.title}
					</span><br>
					<span class='do_description'>
						등록날짜:${target.regdate}. ${target.name}. 우선순위 ${target.sequence}
					</span>
					<button name="${target.id}">→</button>
				</p>
			</c:forEach>
		</article>
		
		<article class='art_DONE'>
			<div class='div_title'>DONE</div>
			<c:forEach var="target" items="${DONElist}">
				<p>
					<span class='do_name'>
						${target.title}
					</span><br>
					<span class='do_description'>
						등록날짜:${target.regdate}. ${target.name}. 우선순위 ${target.sequence}
					</span>
				</p>
			</c:forEach>
		</article>
	</section>
</body>

<script>
	function updateRequest(btn, type) {
		var oReq = new XMLHttpRequest();
		oReq.addEventListener("load", function(e) {
			if (e.target.response != "success") {
				alert('Update fail');
			}
		});
		oReq.open("get", "update?id=" + btn.getAttribute("name") + "&type="+ type);
		oReq.send();
	}

	function clickEvent(event) {
		var btn = event.target;
		var type = btn.parentElement.parentElement.getElementsByTagName('div')[0].innerText;
		updateRequest(btn, type)

		if (type === 'TODO') {
			//TODO에 있는 버튼을 눌렀을 때
			var artDoing = document.getElementsByClassName('art_DOING')[0];
			var clickedTag = btn.parentElement;
			artDoing.appendChild(clickedTag);

			var btns = document.getElementsByTagName('button');

			for (var i = 0; i < btns.length; i++)
				btns[i].addEventListener('click', clickEvent);
		} else {
			//DOING에 있는 버튼을 눌렀을 때
			var artDone = document.getElementsByClassName('art_DONE')[0];
			var clickedTag = btn.parentElement;
			artDone.appendChild(clickedTag)

			var pTags = document.getElementsByClassName("art_DONE")[0].getElementsByTagName("p");
			pTags[pTags.length - 1].getElementsByTagName('button')[0].remove();
		}
	}

	var btns = document.getElementsByTagName('button');

	for (var i = 0; i < btns.length; i++)
		btns[i].addEventListener('click', clickEvent);
</script>
</html>