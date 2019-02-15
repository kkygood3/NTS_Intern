<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>네이버 예약!!</title>
<link href="./css/style.css" rel="stylesheet">
<link href="./css/main.css" rel="stylesheet">
<link href="./css/slide.css" rel="stylesheet">
</head>
<body>
	<div id="container">
		<div class="header">
		<%@ include file="/WEB-INF/views/header.jsp"%>
		</div>
		
		<hr>

		<div class="event">
			<div class="section_visual group_visual">
				<div class="group_visual">
					<div class="container_visual">
					<!-- [D] 이전,다음 버튼을 클릭할때마다 캐러셀 형태로 순환 됨 -->
						<ul class="visual_img transition" style="left:0;">
						</ul>
					</div>
					<span class="nxt_fix" style="display:none;"></span>
				</div>
			</div>
			<div class="section_event_tab">
				<ul class="event_tab_lst tab_lst_min">
					<li class="item" data-category="0">
						<a class="anchor active"><span id="category_0">전체리스트</span></a>
					</li>
				<!-- content -->
				</ul>
			</div>
			<div class="section_event_lst">
				<p class="event_lst_txt">바로 예매 가능한 행사가 <span class="pink">10개</span> 있습니다</p>
				<div class="wrap_event_box">
					<!-- [D] lst_event_box 가 2컬럼으로 좌우로 나뉨, 더보기를 클릭할때마다 좌우 ul에 li가 추가됨 -->
					<ul class="lst_event_box">
					</ul>
					<ul class="lst_event_box">
					</ul>
					<!-- 더보기 -->
					<div class="more">
						<button class="btn" value="0"><span>더보기</span></button>
					</div>
				</div>
			</div>
		</div>
	</div>


	<%@ include file="/WEB-INF/views/footer.jsp"%>

	<%@ include file="/template/main.jsp"%>

	<script type= "text/javascript" src= "./js/common/error.js" ></script>
	<script type= "text/javascript" src= "./js/common/sendAjax.js" ></script>
	<script type= "text/javascript" src= "./js/slide/slide.js" ></script>
	<script type= "text/javascript" src= "./js/main/category.js" ></script>
	<script type= "text/javascript" src= "./js/main/promotion.js" ></script>
	<script type= "text/javascript" src= "./js/main/thumbnail.js" ></script>
	<script type= "text/javascript" src= "./js/main/event.js" ></script>
	<script type= "text/javascript" src= "./js/main/main.js" ></script>
</body>
</html>
