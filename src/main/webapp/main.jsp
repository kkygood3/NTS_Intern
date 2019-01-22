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
		<c:set var="length" scope="request" value="${fn:length(todoLabel)-1}"/>
		<c:forEach var="labelIdx" begin="0" end="${length}">
			<c:set var="curLabel" value="${todoLabel[labelIdx]}"/>
			<article class='art_${curLabel}'>
			<div class='div_title'>${curLabel}</div>
			<c:forEach var="target" items="${todoList}">
				<c:if test="${target.type == curLabel}">
					<p>
						<span class='do_name'>
							<!-- 너무 긴 문자는 생략 -->
							<c:if test="${fn:length(target.title)>17}">
								${fn:substring(target.title,0,17)}...	
							</c:if>
							<c:if test="${fn:length(target.title)<=17}">
								${target.title}
							</c:if>
						</span><br>
						<span class='do_description'>
							등록날짜:${target.regdate}. 
							<c:if test="${fn:length(target.name)>6}">
								${fn:substring(target.name,0,6)}...	
							</c:if>
							<c:if test="${fn:length(target.name)<=6}">
								${target.name}.
							</c:if>
							 우선순위 ${target.sequence}
						</span>
						<c:if test="${labelIdx != length}">
							<button name="${target.id}">→</button>
						</c:if>
					</p>
				</c:if>
			</c:forEach>
			</article>
		</c:forEach>
	</section>
</body>

<script>
	function clickEvent(event) {
		var btn = event.target;
		var type = btn.parentElement.parentElement.getElementsByTagName('div')[0].innerText;

		var oReq = new XMLHttpRequest();
		oReq.addEventListener("load", function() {
			console.log("success");
		})
		oReq.open("get", "update?id=" + btn.getAttribute("name") + "&type="
				+ type);
		oReq.send();

		if (type === 'TODO') {
			//TODO에 있는 버튼을 눌렀을 때
			var artDoing = document.getElementsByClassName('art_DOING')[0];
			var clickedTag = btn.parentElement;
			artDoing.innerHTML += "<p>" + clickedTag.innerHTML + "</p>";
			clickedTag.remove();

			var btns = document.getElementsByTagName('button');

			for (var i = 0; i < btns.length; i++)
				btns[i].addEventListener('click', clickEvent);
		} else {
			//DOING에 있는 버튼을 눌렀을 때
			var artDone = document.getElementsByClassName('art_DONE')[0];
			var clickedTag = btn.parentElement;
			artDone.innerHTML += "<p>" + clickedTag.innerHTML + "</p>";
			clickedTag.remove();

			var pTags = document.getElementsByClassName("art_DONE")[0]
					.getElementsByTagName("p");
			pTags[pTags.length - 1].getElementsByTagName('button')[0].remove();
		}
	}

	var btns = document.getElementsByTagName('button');

	for (var i = 0; i < btns.length; i++)
		btns[i].addEventListener('click', clickEvent);
</script>
</html>