<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
<title>네이버 예약</title>
<link href="/resources/css/style.css" rel="stylesheet">
</head>

<body>
	<div id="container">
		<c:import url="common/header.jsp" charEncoding="utf-8"/>
		<div class="ct">
			<div class="wrap_review_list">
				<div class="review_header">
					<div class="top_title gr">
						<a href="/display/detail/${ displayInfoId }" class="btn_back" title="이전 화면으로 이동"> <i class="fn fn-backward1"></i>
						</a>
						<h2>
							<a class="title" href="#">오디컴퍼니 주식회사</a>
						</h2>
					</div>
				</div>
				<div class="section_review_list">
					<div class="review_box">
						<h3 class="title_h3">예매자 한줄평</h3>
						<div class="short_review_area" data-id="${displayInfoId}">

							<!-- Star Point, Average score, comment size -->
							<div id="score" class="grade_area">
							</div>

							<!-- Comments -->
							<ul id="comment_ul" class="list_short_review">
							</ul>

						</div>
						<p class="guide">
							<i class="spr_book2 ico_bell"></i> <span>네이버 예약을 통해 실제 방문한 이용자가 남긴 평가입니다.</span>
						</p>
					</div>
				</div>
			</div>
		</div>
		<hr>
	</div>
	<c:import url="common/footer.jsp" charEncoding="utf-8"/>
	
<script src="/resources/javascript/ajax.js"></script>
<script src="/resources/javascript/review.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"></script>


<script id="comment_template" type="text/template">
	<li class="list_item">
	<div>
		{{#if commentImages}}
			<div class="review_area">
				<div class="thumb_area">
					{{#each commentImages}}
						<a href="#" class="thumb" title="이미지 크게 보기"> 
							<img width="90" height="90" class="img_vertical_top" src="/download/img/{{fileId}}" alt="리뷰이미지">
						</a> 
						<span class="img_count">{{commentImages.length}}</span> 
					{{/each}}
				</div>
				<h4 class="resoc_name">{{productDescription}}</h4>
				<p class="review">{{comment}}</p>
			</div>
		{{else}}
			<div class="review_area no_img">
				<h4 class="resoc_name">{{productDescription}}</h4>
				<p class="review">{{comment}}</p>
			</div>
		{{/if}}
	
		<div class="info_area">
			<div class="review_info">
				<span class="grade">{{score}}.0</span> <span class="name">{{reservationEmail}}</span> <span class="date">{{reservationDate}} 방문</span>
			</div>
		</div>
	</div>
	</li>
</script>



<script id="score_template" type="text/template">
	<span class="graph_mask"> <em class="graph_value" style="width: 0;"></em></span>
	<strong class="text_value"> <span id="avgerage_score">{{avgScore}}</span> <em class="total">5.0</em>
	</strong> <span class="join_count"><em class="green">{{comments.length}}건</em> 등록</span>
</script>
</body>
</html>
