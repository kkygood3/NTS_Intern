<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
</head>
<body>
</body>
<script>
	alert('서비스에 문제가 발생했습니다.\r\n메인 페이지로 돌아갑니다.\r\ncause : ${cause}');
	window.location.href = "/";
</script>
</html>