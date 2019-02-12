<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="code" value="<%= request.getParameter(\"code\")%>"/>
<c:set var="message" value="<%= request.getParameter(\"message\")%>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
<link href="./css/error.css" rel="stylesheet">
</head>
<body>
	<h1>
		<c:if test="${code != null}">${code} : ${message}</c:if> 
	</h1>
	<button onclick="location.href='./'">메인페이지로 이동</button>
</body>
</html>
