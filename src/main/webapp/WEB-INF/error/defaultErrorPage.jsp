<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${requestScope['javax.servlet.error.status_code']}</h1>
<h1>${requestScope['javax.servlet.error.message']}</h1>
</body>
</html>