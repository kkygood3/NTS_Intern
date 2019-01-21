<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">
<title>할일등록 화면</title>
<link rel="stylesheet" href="./css/layout.css">
<link rel="stylesheet" href="./css/todoForm.css">
</head>
<body>
	<div class="content">
		<div id="header">할일 등록</div>
		<form>
			<label>어떤일인가요?</label><br>
			<input type="text" name="title" maxlength="24"
				placeholder="swift 공부하기(24자까지)"/><br>
			<label>누가 할일인가요?</label><br>
			<input type="text" name="name"
				placeholder="홍길동"><br>
			<label>우선순위를 선택하세요</label><br>
			<input type="radio" name="sequence" value=1> 1순위  　
			<input type="radio" name="sequence" value=2> 2순위  　
			<input type="radio" name="sequence" value=3> 3순위<br>
			<button name="back">&lt 이전</button>
			<button>내용지우기</button>
			<button type="submit">제출</button>
		</form>
	</div>
</body>
</html>