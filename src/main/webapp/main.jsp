<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">
<title>할일목록 화면</title>
<link rel="stylesheet" href="./css/layout.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<div class="content">
		<div id="header">나의 해야할 일들</div>
		<button class="button_todo">새로운 TODO 등록</button>
		<div id="list_board">
			<div class="container">
				<span class="title">TODO</span>
				<!-- card 샘플 
				<div class="card">
					<span class="title">테스트1</span><br/>
					<span>
						등록날자 0000.00.00 배진우 우선순위1
						<button>→</button>
					</span>
				</div>
				 -->
			</div>
			<div class="container">
				<span class="title">DOING</span>
			</div>
			<div class="container">
				<span class="title">DONE</span>
			</div>
		</div>
	</div>
</body>
</html>