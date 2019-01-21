<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 
	todoForm.jsp implementation
	Author: Jaewon Lee, lee.jaewon@nts-corp.com
 -->
 
<!DOCTYPE html>
<html lang=ko>
<head>
<meta charset="utf-8">
<title>이재원 ADD TO-DO 페이지 입니다</title>
<link rel="stylesheet" href="/jaewonlee/css/base.css">
</head>
<body>
	<section>
		<h1>할일 등록</h1>
		<form action="/jaewonlee/todoadd"  method="post">
			<table>
				<tr>
					<td>
						<p>
							<strong>어떤 일인가요?</strong> <br> 
							<input class="text_input" type="text" placeholder="swift 공부하기(24자까지)" maxlength="24" name="title">
						</p>
					</td>
				</tr>
	
				<tr>
					<td>
						<p>
							<strong>누가 할 일인가요?</strong> <br> 
							<input class="text_input" type="text" placeholder="홍길동" name="name">
						</p>
					</td>
				</tr>
	
				<tr>
					<td>
						<p>
							<strong>우선순위를 선택하세요</strong> <br> 
							<input type="radio" name="sequence" value=1 checked="checked"> 1 
							<input type="radio" name="sequence" value=2> 2 
							<input type="radio" name="sequence" value=3> 3
						</p>
					</td>
				</tr>
	
			</table>
			<input class="addTodo_button" type="button" value="&lt;이전" onclick="history.back()" /> 
			<input class="addTodo_button" type="submit"value="제출" /> 
			<input class="addTodo_button" type="reset" value="내용지우기" />
		</form>		
	</section>
</body>
</html>