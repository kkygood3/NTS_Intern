<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.nts.reservation.dto.ErrorMessageDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
	ErrorMessageDto error = (ErrorMessageDto)request.getAttribute("errorDto");
	response.sendError(response.getStatus(),error.getErrorMessage()); 
	%>
</body>
</html>