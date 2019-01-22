<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="UTF-8">
<title>register</title>
</head>
<body>
<section class="register_content">
	<h2>할 일 등록</h2>

	<form class="" action="" method="">
		<!-- label(for) - input(id) 매칭 -->
		<label for="">어떤일인가요?</label><br>
		<input type="text" name="" value="" required placeholder="swift 공부하기(24자까지)" maxlength="24">
		<br> <br>
		<label for="">누가 할일인가요?</label><br>
		<input type="text" name="" value="" required placeholder="홍길동"> <br>

		<br>

		<!-- label(for) - input(id) 매칭 -->
		<label for="">우선순위를 선택하세요!</label> <br> <input type="radio"
			name="sequence" value="" required> <label for="">1순위</label>
		<input type="radio" name="sequence" value=""> <label for="">2순위</label>
		<input type="radio" name="sequence" value=""> <label for="">3순위</label>
		<br><br>
		<a href="/main.jsp" class="previous_button">< 이전</a>
		<input type="submit" name="" value="제출" class="register_button">
		<input type="reset" name="" value="내용지우기" class="register_button">
	</form>
</section>
</body>
</html>