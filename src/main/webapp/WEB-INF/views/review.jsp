<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <title>네이버 예약</title>
    <link href="/css/style.css" rel="stylesheet">
	<script type="text/javascript">
		function displayInfo() {
			return {
				"displayInfoId" : "${displayInfoId}",
				"productId" : "${pageInfo.productId}",
				"commentCount" : "${pageInfo.commentCount}"
			};
		}
	</script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.1.0/handlebars.min.js"></script>
	<script type= "text/javascript" src= "/js/common/error.js" ></script>
	<script type= "text/javascript" src= "/js/common/sendAjax.js" ></script>
	<script type= "text/javascript" src= "/js/comment/comment.js" ></script>
	<script type= "text/javascript" src= "/js/review/event.js" ></script>
	<script type= "text/javascript" src= "/js/review/review.js" ></script>
</head>

<body>
	<div id="container">
		<!-- [D] 예약하기로 들어오면 header에 fade 클래스 추가로 숨김 -->
		<div class="header fade">
			<header class="header_tit">
				<h1 class="logo">
					<a href="#" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
					<a href="#" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
				</h1>
				<a href="#" class="btn_my"> <span title="예약확인">예약확인</span> </a>
			</header>
		</div>
		<div class="ct">
			<div class="wrap_review_list">
				<div class="review_header">
					<div class="top_title gr">
						<a href="/detail/${displayInfoId}" class="btn_back" title="이전 화면으로 이동"> <i class="fn fn-backward1"></i></a>
						<h2><a class="title" href="#">${pageInfo.description}</a></h2>
					</div>
				</div>
				<div class="section_review_list">
					<div class="review_box">
						<h3 class="title_h3">예매자 한줄평</h3>
						<div class="short_review_area">
							<div class="grade_area">
								<span class="graph_mask"><em class="graph_value" style="width:${pageInfo.averageScore * 20}%;"></em></span>
                                <strong class="text_value"><span>${pageInfo.averageScore}</span> <em class="total">5.0</em></strong>
                                <span class="join_count"><em class="green">${pageInfo.commentCount}건</em> 등록</span>
							</div>
							<ul class="list_short_review">
                            <!-- content -->
							</ul>
						</div>
						<p class="guide"> <i class="spr_book2 ico_bell"></i> <span>네이버 예약을 통해 실제 방문한 이용자가 남긴 평가입니다.</span> </p>
					</div>
					
                    <!-- 더보기 -->
                    <div class="more">
                        <button class="btn" value="0"><span>더보기</span></button>
                    </div>
				</div>
			</div>
		</div>
		<hr>
	</div>
	<footer>
		<div class="gototop">
			<a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span> </a>
		</div>
		<div id="footer" class="footer">
			<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
			<span class="copyright">© NAVER Corp.</span>
		</div>
	</footer>
	
	<%@ include file="/template/comment_item.jsp"%>
</body>
</html>
