<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>${requestScope['javax.servlet.error.status_code']} ERROR</title>
</head>
<body>
<h1>${requestScope['javax.servlet.error.status_code']}</h1>
<h1>${requestScope['javax.servlet.error.message']}</h1>
</body>
</html>