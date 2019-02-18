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

</head>

<body>
	<div id="container">
		<div class="header">
		<%@ include file="/WEB-INF/views/header.jsp"%>
		</div>
		<hr>
		<div class="ct">
			<div class="section_my">
				<!-- 예약 현황 -->
				<div class="my_summary">
					<ul class="summary_board">
						<li class="item">
							<!--[D] 선택 후 .on 추가 link_summary_board -->
							<a href="#" class="link_summary_board on"> <i class="spr_book2 ico_book2"></i> <em class="tit">전체</em> <span class="figure">6</span> </a>
						</li>
						<li class="item">
							<a href="#" class="link_summary_board"> <i class="spr_book2 ico_book_ss"></i> <em class="tit">이용예정</em> <span class="figure">2</span> </a>
						</li>
						<li class="item">
							<a href="#" class="link_summary_board"> <i class="spr_book2 ico_check"></i> <em class="tit">이용완료</em> <span class="figure">2</span> </a>
						</li>
						<li class="item">
							<a href="#" class="link_summary_board"> <i class="spr_book2 ico_back"></i> <em class="tit">취소·환불</em> <span class="figure">2</span> </a>
						</li>
					</ul>
				</div>
				<!--// 예약 현황 -->

				<!-- 내 예약 리스트 -->
				<div class="wrap_mylist">
					<ul class="list_cards" ng-if="bookedLists.length > 0">
						<!--[D] 예약확정: .confirmed, 취소된 예약&이용완료: .used 추가 card -->
						<!-- content -->
					</ul>
				</div>
				<!--// 내 예약 리스트 -->
			</div>
		</div>
	<hr>
	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>

	<!-- 취소 팝업 -->
	<!-- [D] 활성화 display:block, 아니오 버튼 or 닫기 버튼 클릭 시 숨김 display:none; -->
	<div class="popup_booking_wrapper" style="display:none;">
		<div class="dimm_dark" style="display:block"></div>
		<div class="popup_booking refund">
			<h1 class="pop_tit">
				<span>서비스명/상품명</span>
				<small class="sm">2000.0.00.(월)2000.0.00.(일)</small>
			</h1>
			<div class="nomember_alert">
				<p>취소하시겠습니까?</p>
			</div>
			<div class="pop_bottom_btnarea">
				<div class="btn_gray">
					<a href="#" class="btn_bottom"><span>아니오</span></a>
				</div>
				<div class="btn_green">
					<a href="#" class="btn_bottom"><span>예</span></a>
				</div>
			</div>
			<!-- 닫기 -->
			<a href="#" class="popup_btn_close" title="close">
				<i class="spr_book2 ico_cls"></i>
			</a>
			<!--// 닫기 -->
		</div>
	</div>
	<!--// 취소 팝업 -->
	<script type="text/javascript">
		function displayInfo() {
			return {
				"email" : "${sessionScope.email}",
			};
		}
	</script>
	<script type= "text/javascript" src= "/js/common/error.js" ></script>
	<script type= "text/javascript" src= "/js/common/sendAjax.js" ></script>
	<script type= "text/javascript" src= "http://code.jquery.com/jquery-latest.min.js"></script>
	<script type= "text/javascript" src= "https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.1.0/handlebars.min.js"></script>
	<script type= "text/javascript" src= "/js/myreservation/myreservation.js" ></script>
</body>
</html>