<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">
<title>할일등록 화면</title>
<link rel="stylesheet" href="./css/layout.css">
<link rel="stylesheet" href="./css/newtodo.css">
</head>
<body>
	<div class="content">
		<div id="header">할일 등록</div>
		<form>
			어떤일인가요?<br>
			<input type="text" name="title"><br>
			누가 할일인가요?<br>
			<input type="text" name="name"><br>
			우선순위를 선택하세요<br>
			<input type="radio" name="sequence" value=1>1순위
			<input type="radio" name="sequence" value=2>2순위
			<input type="radio" name="sequence" value=3>3순위<br>
			
			<input type="submit" value="< 이전">
			<input type="submit" value="제출">
			<input type="submit" value="내용지우기">
			
		</form>
	</div>
</body>
</html>