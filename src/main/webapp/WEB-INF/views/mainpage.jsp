<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description"
	content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
<title>네이버 예약</title>
<link href="/resources/css/style.css" rel="stylesheet">
</head>
<body>
	<div id="container">
		<c:import url="common/header.jsp" charEncoding="utf-8"/>
		<hr>
		<div class="event">
			<div class="section_visual">
				<div class="group_visual">
					<div class="container_visual">
						<div class="prev_e" style="display: none;">
							<div class="prev_inn">
								<a href="#" class="btn_pre_e" title="이전"> <i
									class="spr_book_event spr_event_pre">이전</i>
								</a>
							</div>
						</div>
						<div class="nxt_e" style="display: none;">
							<div class="nxt_inn">
								<a href="#" class="btn_nxt_e" title="다음"> <i
									class="spr_book_event spr_event_nxt">다음</i>
								</a>
							</div>
						</div>
						<div>
							<div class="container_visual">
								<ul id="main_slide" class="visual_img">
								</ul>
							</div>
							<span class="nxt_fix" style="display: none;"></span>
						</div>
					</div>
				</div>
			</div>
			<div class="section_event_tab">
				<ul id="category" class="event_tab_lst tab_lst_min" style="padding-right: 0px;">
					<li class="item" data-category="0">
						<a class="anchor active"><span>전체리스트</span></a>
					</li>
				</ul>
			</div>
			<div class="section_event_lst">
				<p class="event_lst_txt">
					바로 예매 가능한 행사가  <span id="categoryCount" class="pink"></span>개 있습니다
				</p>
				<div class="wrap_event_box">
					<ul id="productBox1" class="lst_event_box">
					</ul>
					<ul id="productBox2" class="lst_event_box">
					</ul>
					<div class="more">
						<button id="moreButton" class="btn" data-limit="4">
							<span>더보기</span>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<c:import url="common/footer.jsp" charEncoding="utf-8"/>
	<script id="product_template" type="text/template">
		<li class="item">
			<a href="/display/detail/{id}" class="item_book">
				<div class="item_preview">
					<img alt="{description}" class="img_thumb" src="/resources/img/img/{productImageUrl}">
					<span class="img_border"></span>
				</div>
				<div class="event_txt">
					<h4 class="event_txt_tit"> <span>{description}</span> <small class="sm">{placeName}</small> </h4>
					<p class="event_txt_dsc">{content}</p>
				</div>
			</a>
		</li>
	</script>
	<script id="category_template" type="text/template">
		<li class="item" data-category="{categoryId}">
			<a class="anchor"><span>{categoryName}</span></a>
		</li>
	</script>
	<script id="promotion_template" type="text/template">
		<li><img src="/resources/img/img/{productImageUrl}"/></li>
	</script>
	<script src="/resources/javascript/mainpage.js"></script>
</body>
</html>
