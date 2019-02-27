<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="utf-8">
<meta name="description"
	content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
<title>네이버 예약</title>
<link href="/css/style.css" rel="stylesheet">
</head>

<body>
	<div id="container">
		<!-- [D] 예약하기로 들어오면 header에 fade 클래스 추가로 숨김 -->
		<div class="header fade">
			<header class="header_tit">
				<h1 class="logo">
					<a href="#" class="lnk_logo" title="네이버"> <span
						class="spr_bi ico_n_logo">네이버</span>
					</a> <a href="#" class="lnk_logo" title="예약"> <span
						class="spr_bi ico_bk_logo">예약</span>
					</a>
				</h1>
				<a href="#" class="btn_my"> <span title="예약확인">예약확인</span>
				</a>
			</header>
		</div>
		<div class="ct">
			<div class="wrap_review_list">
				<div class="review_header">
					<div class="top_title gr">
						<a onclick="goDetailPage();" class="btn_back" title="이전 화면으로 이동"> <i
							class="fn fn-backward1"></i>
						</a>
						<h2>
							<a class="title" href="#">오디컴퍼니 주식회사</a>
						</h2>
					</div>
				</div>
				<div class="section_review_list">
					<div class="review_box">
						<h3 class="title_h3">예매자 한줄평</h3>
						<div class="short_review_area">
							<div class="grade_area">
								<span class="graph_mask">
									<em class="graph_value" style="width: 88%;"></em>
								</span> 
								<strong class="text_value">
									<span></span>
									<em class="total">5.0</em>
								</strong> 
								<span class="join_count"><em class="green"></em>등록</span>
							</div>
							<ul class="list_short_review">
							</ul>
						</div>
						<p class="guide">
							<i class="spr_book2 ico_bell"></i> 
							<span>네이버 예약을 통해 실제 방문한 이용자가 남긴 평가입니다.</span>
						</p>
						<a class="btn_review_more" onclick="comment.moreComments();">
							<span>더보기</span>
							<i class="fn fn-down2"></i>
						</a>
					</div>
				</div>
			</div>
		</div>
		<hr>
	</div>
	<footer>
		<div class="gototop">
			<a href="#" class="lnk_top"><span class="lnk_top_text">TOP</span></a>
		</div>
		<div id="footer" class="footer">
			<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및
				환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
			<span class="copyright">© NAVER Corp.</span>
		</div>
	</footer>
	<script type="text/template" id="template-comment">
		<li class="list_item">
			<div>
				<div class="review_area">
					{{#if saveFileName}}
						<div class="thumb_area">
							<img width="90" height="90" class="img_vertical_top" src="/{{saveFileName}}" alt="리뷰이미지">
							<span class="img_count" style="display: none;">1</span>
						</div>
					{{/if}}
					<h4 class="resoc_name"></h4>
					<p class="review">{{comment}}</p>
				</div>
				<div class="info_area">
					<div class="review_info">
						<span class="grade">{{score}}</span>
						<span class="name">{{reservationName}}</span>
						<span class="date">{{reservationDate}} 방문</span>
					</div>
				</div>
			</div>
		</li>
	</script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.1.0/handlebars.min.js"></script>
	<script type="text/javascript" src="/js/util.js"></script>
	<script type="text/javascript">
		var productId = parseInt(window.location.pathname.split("/")[2]);
		var displayInfoId = parseInt(new URL(window.location.href).searchParams.get("displayInfoId"));
		const REQUEST_COMMENT_LIMIT = 10;
		
		var comment = {
			init : function(productId, start, limit) {
				this.loadCommentResponse(this.initComment.bind(this), productId, start, limit);
			},

			loadCommentResponse : function(callback, productId, start, limit) {
				if (!isNumber(productId) || !isNumber(start)) {
					alert("잘못된 파라미터임니다 메인페이지로 이동합니다.");
					location.href="/main";
					return;
				}
				var url = "/api/products/" + productId + "/comments?start=" + start + "&limit=" + limit;
				ajax(callback, url);
			},

			initComment : function(response) {
				this.setCountDOM(response);
				this.setAvgScoreDOM(response);
				this.setCommentDOM(response);
			},
			// 상품평 총 개수 설정
			setCountDOM : function(response) {
				var countTextDiv = document.querySelector(".join_count .green");
				countTextDiv.innerText = response.totalCount + " 건";
			},
			// 상품평 평점 설정
			setAvgScoreDOM : function(response) {
				var scoreTextDiv = document.querySelector(".text_value span");
				scoreTextDiv.innerText = response.averageScore.toFixed(1);
				var maxScore = parseFloat(document.querySelector(".text_value .total").innerText);
				document.querySelector(".graph_value").style.width = response.averageScore
						/ maxScore * 100 + "%";
			},
			// 상품평 추가
			setCommentDOM : function(response) {
				var template = document.querySelector("#template-comment").innerText;
				var bindTemplate = Handlebars.compile(template);
				var resultHTML = response.comments.reduce(function(prev, comment) {
					return prev + bindTemplate(comment);
				}, "");
				var commentsDiv = document.querySelector(".list_short_review");
				commentsDiv.innerHTML += resultHTML;
				
				var currCount = document.querySelector(".list_short_review").childElementCount;
				if (response.totalCount <= currCount) {
					document.querySelector(".btn_review_more").style.display = "none";
				}
			},
			// 상품평 더보기
			moreComments : function() {
				var currCount = document.querySelector(".list_short_review").childElementCount;
				this.loadCommentResponse(this.setCommentDOM.bind(this), productId, currCount, REQUEST_COMMENT_LIMIT);
			}
		}

		function goDetailPage() {
			location.href="detail?displayInfoId=" + displayInfoId;
		}

		document.addEventListener("DOMContentLoaded", function(event) {
			comment.init(productId, 0, REQUEST_COMMENT_LIMIT);
		});
	</script>
</body>
</html>
