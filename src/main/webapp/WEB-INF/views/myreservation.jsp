<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="utf-8">
	<meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
	<title>네이버 예약</title>
	<link href="css/style.css" rel="stylesheet">

</head>

<body>
	<div id="container">
		<div class="header">
			<header class="header_tit">
				<h1 class="logo">
					<a href="/" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
					<a href="/" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
				</h1>
				<a href="#" class="btn_my"> <span title="내예약" class="viewReservation"></span> </a>
			</header>
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
						<li class="card" style="display: none;">
							<div class=link_booking_details >
								<div class="card_header">
									<div class="left"></div>
									<div class="middle">
										<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book2 -->
										<i class="spr_book2 ico_clock"></i>
										<span class="tit">예약 신청중</span>
									</div>
									<div class="right"></div>
								</div>
							</div>
						</li>
						<li class="card confirmed" style="display: none;">
							<div class="link_booking_details" style="margin-top: 5px;">
								<div class="card_header">
									<div class="left"></div>
									<div class="middle">
										<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
										<i class="spr_book2 ico_check2"></i>
										<span class="tit">예약 확정</span>
									</div>
									<div class="right"></div>
								</div>
							</div>
							</li>
							<li class="card used" style="display: none;">
								<div class="link_booking_details" style="margin-top: 5px;">
									<div class="card_header">
										<div class="left"></div>
										<div class="middle">
											<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
											<i class="spr_book2 ico_check2"></i>
											<span class="tit">이용 완료</span>
										</div>
										<div class="right"></div>
									</div>
								</div>
							</li>
							<li class="card used cancel" style="display: none;">
								<div class="link_booking_details" style="margin-top: 5px;">
									<div class="card_header">
										<div class="left"></div>
										<div class="middle">
											<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
											<i class="spr_book2 ico_cancel"></i>
											<span class="tit">취소된 예약</span>
										</div>
										<div class="right"></div>
									</div>
								</div>
							</li>
						</ul>
					</div>
					<!--// 내 예약 리스트 -->

					<!-- 예약 리스트 없음 -->
					<div class="err" style="display:none;"> <i class="spr_book ico_info_nolist"></i>
						<h1 class="tit">예약 리스트가 없습니다</h1>
					</div>
					<!--// 예약 리스트 없음 -->
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
		<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.1.0/handlebars.min.js"></script>
		<script src="js/common.js"></script>
		<script type="myTemplate" id="reservationTemplate">
			<article class="card_item">
				<a href="#" class="link_booking_details">
					<div class="card_body">
						<div class="left"></div>
						<div class="middle">
							<div class="card_detail">
								<em class="booking_number">No.{{index}}</em>
								<h4 class="tit">서비스명/상품명</h4>
								<ul class="detail">
									<li class="item">
										<span class="item_tit">일정</span>
										<em class="item_dsc">
										{{displayDate}}
										</em>
									</li>
									<li class="item">
										<span class="item_tit">내역</span>
										<em class="item_dsc">
										{{productDescription}}
										</em>
									</li>
									<li class="item">
										<span class="item_tit">장소</span>
										<em class="item_dsc">
										{{placeName}}
										</em>
									</li>
									<li class="item">
										<span class="item_tit">업체</span>
										<em class="item_dsc">
										업체명이 없습니다.
										</em>
									</li>
								</ul>
								<div class="price_summary">
									<span class="price_tit">결제 예정금액</span>
									<em class="price_amount">
										<span>{{price}}</span>
										<span class="unit">원</span>
									</em>
								</div>
							</div>
						</div>
						<div class="right"></div>
					</div>
					<div class="card_footer">
						<div class="left"></div>
						<div class="middle"></div>
						<div class="right"></div>
					</div>
				</a>
			</article>
		</script>
		<script>
		function loadReservationInfoCallback(response){
			var reservationList = response.myReservationResponse;
			
			if(reservationList.length > 0){
				// 예약정보 Template
				var reservationTemplate = document.querySelector('#reservationTemplate').innerText;
				var bindCommentTemplate = Handlebars.compile(reservationTemplate);
				
				var containers = document.querySelectorAll('li.card');
				var confirmContainer = containers[1];
				var completeContainer = containers[2];
				var cancelContainer = containers[3];
				
				for(var i = 0 ; i < reservationList.length; i++){
					var targetReservation = reservationList[i];
					
					targetReservation.price = targetReservation.price.toString().replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,')
					targetReservation.index = i+1;
					var reservationItem = bindCommentTemplate(targetReservation);
					
					/**
					 * 0 보다 크면 cancel으로 이동.
					 * 0 이라면 
					 *  -> 날짜가 오늘보다 이전이면 conplete으로
					 *  -> 날짜가 오늘 이후라면 confirm으로
					 */
					if(targetReservation.cancelFlag > 0){
						cancelContainer.innerHTML += reservationItem;
						cancelContainer.style.display = 'initial';
					} else {
						var today = new Date();
						var targetDate = new Date(targetReservation.displayDate)
						// 오늘보다 이전이라면
						if(targetDate < today) {
							completeContainer.innerHTML += reservationItem;
							completeContainer.style.display = 'initial';
						} else {
							confirmContainer.innerHTML += reservationItem;
							confirmContainer.style.display = 'initial';
						}
					}	
				}
			} else {
				var emptyReserveMsg = document.querySelector('.err');
				emptyReserveMsg.style.display = 'initial';
			}
		}
			
			
			
			
		document.addEventListener('DOMContentLoaded', function() {
			//alert('${email}');
			//1. api/reservations get으로 예약정보를 가져오기
			requestAjax(loadReservationInfoCallback, 'api/reservations?reservationEmail=${email}');
			//2. 화면에 예약정보 뿌려주기
			
			
			
		});
		</script>
			
	</body>

	</html>