<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="UTF-8">
<title>Todo List</title>
</head>
<body>

	<div class="container">
		<header class="header_wrap">
			<h2 class="rotate">나의 해야할 일들</h2>

			<a href="#" class="register_button">새로운 TODO등록</a>
		</header>

		<section class="content_main">
			<!-- TODO -->
			<aside class="content_box">
				<h3 class="title">TODO</h3>

				<article class="card">
					<h3>자바스크립트 공부하기</h3>
					등록날짜:2018.03.10, 홍길동, 우선순위1
					<button>→</button>
				</article>
				<article class="card">
					<h3>자바스크립트 공부하기</h3>
					등록날짜:2018.03.10, 홍길동, 우선순위1
					<button>→</button>
				</article>
			</aside>

			<!-- DOING -->
			<aside class="content_box">
				<h3 class="title">DOING</h3>

				<article class="card">
					<h3>DOINGDOING</h3>
					등록날짜:2018.03.10, 홍길동, 우선순위1
					<button>→</button>
				</article>
				<article class="card">
					<h3>DOINGDOING</h3>
					등록날짜:2018.03.10, 홍길동, 우선순위1
					<button>→</button>
				</article>
				<article class="card">
					<h3>DOINGDOING</h3>
					등록날짜:2018.03.10, 홍길동, 우선순위1
					<button>→</button>
				</article>
				<article class="card">
					<h3>DOINGDOING</h3>
					등록날짜:2018.03.10, 홍길동, 우선순위1
					<button>→</button>
				</article>
			</aside>

			<!-- DONE -->
			<aside class="content_box">
				<h3 class="title">DONE</h3>

				<article class="card">
					<h3>DONEDONE</h3>
					등록날짜:2018.03.10, 홍길동, 우선순위1
					<button>→</button>
				</article>
				<article class="card">
					<h3>DONEDONE</h3>
					등록날짜:2018.03.10, 홍길동, 우선순위1
					<button>→</button>
				</article>
				<article class="card">
					<h3>DONEDONE</h3>
					등록날짜:2018.03.10, 홍길동, 우선순위1
					<button>→</button>
				</article>
			</aside>
		</section>

		<div style="clear: both;"></div>
	</div>
</body>
</html>