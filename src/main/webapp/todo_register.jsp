<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width">
<title>JS Bin</title>
<style>
html {
	min-width: 803px;
	height: 100%;
	width: 100%;
	text-align: center;
	background: #ebeded;
}

body {
	background: white;
	width: 80%;
	height: 70%;
	padding: 20px;
	display: inline-block;
	text-align: center;
	overflow: auto;
}

section {
	display: inline-block;
	text-align: left;
	width: 50%;
}

button {
	width: 100px;
	height: 40px;
	border: solid thin;
}

.btn_before {
	float: left;
	font-size: 1.05em;
	text-decoration: underline;
	background: white;
}

.btn_submit, .btn_reset {
	float: right;
	margin-left: 10px;
	color: white;
	background: #22aded;
}
</style>
</head>
<body>
	<h2>할일 등록</h2>
	<section>
		<form action="register_ok" method="post">
			어떤일인가요?<br> <input type="text" placeholder="swift 공부하기(24자까지)"
				maxlength=24 class="whatdo_area" name="whatdo"></input> <br> <br> 누가 할일인가요?<br> <input
				type="text" placeholder="홍길동" maxlength=10 class="name_area" name="wname"></input> <br> <br>
			우선순위를 선택하세요<br>
			<input type="radio" name="priority" value="1" checked>1순위</input>
			<input type="radio" name="priority" value="2">2순위</input>
			<input type="radio" name="priority" value="3">3순위</input>
			<br>
			<br>
			<br>
			<button type="button" class="btn_before">< 이전</button>
			<button type="button" class="btn_reset">내용지우기</button>
			<button type="button" class="btn_submit">제출</button>
		</form>
	</section>
</body>
<script>
	var whatdo = document.getElementsByClassName("whatdo_area")[0];
	var wname = document.getElementsByClassName("name_area")[0];
	
	document.getElementsByClassName("btn_submit")[0].addEventListener("click",function(){
		if(whatdo.value.length === 0){
	        alert("어떤 일인지 써주세요");
	    }else if(wname.value.length === 0){
	        alert("누가 할일인지 써주세요");
	    }else{
	    	document.querySelector("form").submit();
	    }
	})
	
	document.getElementsByClassName("btn_reset")[0].addEventListener("click",function(){
		whatdo.value = '';
		wname.value = '';
	})
	
	document.getElementsByClassName("btn_before")[0].addEventListener("click",function(){
		window.location.href = "main";
	})
</script>

</html>