<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/newtodo.css">
<title>todoForm</title>
</head>


<body>
	<p id="register_todo_title">할일 등록</p>
	
	<section>
		<form id="todo_information" action="/todo-add" method="post">
			<ul>
			
				<li class="register_todo_contents">어떤일인가요?<br> 
					<input	name="title" class="message_box" size="60" type="text"
					maxlength=24 placeholder="swift 공부하기(24자까지)" required>
				</li>
				
				<li class="register_todo_contents">누가 할일인가요?<br> 
					<input name="name" class="message_box" size="25" type="text"
					maxlength=10 placeholder="홍길동" required>
				</li>
				
				<li class="register_todo_contents">우선순위를 선택하세요<br> 
					<label class="priority_radio_box"> 
						<input name="sequence" type="radio" value="1" required>1순위
					</label> 
					<label class="priority_radio_box"> 
						<input name="sequence" type="radio" value="2" required>2순위
					</label> 
					<label class="priority_radio_box">
					 	<input name="sequence" type="radio" value="3" required>3순위
					</label>
				</li>
				
			</ul>
			
			<a href="/main" id="previous_page"> < 이전 </a>
			
			<input class="function_button" type="reset" value="내용지우기" />
			
			<input class="function_button" type="submit" value="제출" />
		</form>

	</section>
</body>
</html>