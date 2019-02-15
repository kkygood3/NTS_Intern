<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
<link href="/css/error.css" rel="stylesheet">
</head>
<body>
	<div class="message_holder">
		<h1>${errorInfo.status} : ${errorInfo.statusName}</h1>
		<h3>${errorInfo.message}</h3>
	</div>
	<button class="goto_main">메인페이지로 이동</button>
	
	<script type= "text/javascript" src= "/js/error/error.js" ></script>
</body>
</html>
