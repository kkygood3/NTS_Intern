<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>>Error Page</title>
</head>
<body>
	<h2>에러가발생하였습니다.</h2>
	<h3>에러상세내용</h3>
	<ul>
		<li><strong>Error Code</strong> : ${requestScope['javax.servlet.error.status_code']} </li>
		<li><strong>Error Message</strong> : ${requestScope['javax.servlet.error.message']}</li>
		<li><strong>Error Type</strong> : ${requestScope['javax.servlet.error.exception_type']}</li>
		<li><strong>Error Object</strong> : ${requestScope['javax.servlet.error.exception']}</li>
		<li><strong>Error URI</strong> : ${requestScope['javax.servlet.error.request_uri']}</li>
	</ul>
	<a href="/main">메인페이지로 이동</a>
</body>
</html>