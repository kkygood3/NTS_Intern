<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>

/* 이름 수정 (index꺼 갖다 쓴거임)*/
.register_button {
	color: #fff;
	background: #20ADEE;
	padding: 6px 20px;
	font-size: 12px;
	margin: 30px 0 0 0;
	display: inline-block;
	border: 0px;
}

.previous_button {
	border: 1px solid #000;
	padding: 5px 15px;
	color: #000;
	font-size: 12px;
}
</style>
<meta charset="UTF-8">
<title>register</title>
</head>
<body>
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
		<label for="">우선순위를 선택하세요</label> <br> <input type="radio"
			name="sequence" value="" required> <label for="">1순위</label>
		<input type="radio" name="sequence" value=""> <label for="">1순위</label>
		<input type="radio" name="sequence" value=""> <label for="">1순위</label>
		<br><br>
		<a href="index.jsp" class="previous_button">< 이전</a>
		<input type="submit" name="" value="제출" class="register_button">
		<input type="reset" name="" value="내용지우기" class="register_button">
	</form>
</body>
</html>