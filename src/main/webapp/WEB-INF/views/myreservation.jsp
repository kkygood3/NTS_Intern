<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<header class="header_tit">
				<h1 class="logo">
					<a href="/main" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span>
					</a> <a href="/main" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span>
					</a>
				</h1>
				<a href="/myreservation?reservationEmail=${sessionScope.reservationEmail}" class="btn_my"> 
					<span class="viewReservation" title="예약확인">${sessionScope.reservationEmail}</span>
				</a>
			</header>
		</div>
		<hr>
		<div class="ct">
			<div class="section_my">
				<!-- 예약 현황 -->
				<div class="my_summary">
					<ul class="summary_board">
						<li class="item">
							<!--[D] 선택 후 .on 추가 link_summary_board --> <a href="#" class="link_summary_board on"> <i class="spr_book2 ico_book2"></i>
								<em class="tit">전체</em>
								<span class="figure">${response.totalCount}</span>
							</a>
						</li>
						<li class="item"><a href="#" class="link_summary_board">
								<i class="spr_book2 ico_book_ss"></i> <em class="tit">이용예정</em>
								<span class="figure TODO">${response.todoReservationResponse.totalCount}</span>
							</a></li>
						<li class="item"><a href="#" class="link_summary_board">
								<i class="spr_book2 ico_check"></i> <em class="tit">이용완료</em>
								<span class="figure DONE">${response.doneReservationResponse.totalCount}</span>
							</a></li>
						<li class="item"><a href="#" class="link_summary_board">
								<i class="spr_book2 ico_back"></i> <em class="tit">취소·환불</em>
								<span class="figure CANCEL">${response.cancelReservationResponse.totalCount}</span>
							</a></li>
					</ul>
				</div>
				<!--// 예약 현황 -->

				<!-- 내 예약 리스트 -->
				<div class="wrap_mylist">
					<ul class="list_cards" ng-if="bookedLists.length > 0">
						<!--[D] 예약확정: .confirmed, 취소된 예약&이용완료: .used 추가 card -->
						<li class="card" style="display: none;">
							<div class=link_booking_details>
								<div class="card_header">
									<div class="left"></div>
									<div class="middle">
										<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book2 -->
										<i class="spr_book2 ico_clock"></i> <span class="tit">예약
											신청중</span>
									</div>
									<div class="right"></div>
								</div>
							</div>
							<article class="card_item">
								<a href="#" class="link_booking_details">
									<div class="card_body">
										<div class="left"></div>
										<div class="middle">
											<div class="card_detail">
												<em class="booking_number">No.0000001</em>
												<h4 class="tit">서비스명/상품명1</h4>
												<ul class="detail">
													<li class="item"><span class="item_tit">일정</span> <em class="item_dsc"> 2000.0.00.(월)2000.0.00.(일) </em></li>
													<li class="item"><span class="item_tit">내역</span> <em class="item_dsc"> 내역이 없습니다. </em></li>
													<li class="item"><span class="item_tit">장소</span> <em class="item_dsc"> 내역이 없습니다. </em></li>
													<li class="item"><span class="item_tit">업체</span> <em class="item_dsc"> 업체명이 없습니다. </em></li>
												</ul>
												<div class="price_summary">
													<span class="price_tit">결제 예정금액</span> <em class="price_amount"> <span>000,000,000</span> <span class="unit">원</span>
													</em>
												</div>
												<!-- [D] 예약 신청중, 예약 확정 만 취소가능, 취소 버튼 클릭 시 취소 팝업 활성화 -->
												<div class="booking_cancel">
													<button class="btn">
														<span>취소</span>
													</button>
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
								</a> <a href="#" class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
							</article>
						</li>
						<li class="card confirmed" data-status="TODO">
							<div class="link_booking_details">
								<div class="card_header">
									<div class="left"></div>
									<div class="middle">
										<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
										<i class="spr_book2 ico_check2"></i> <span class="tit">예약확정</span>
									</div>
									<div class="right"></div>
								</div>
							</div>
							<!-- 예약 리스트 없음 -->
								<div class="err"
									<c:if test="${response.todoReservationResponse.totalCount != 0}">
										style="display: none;"
									</c:if>
								>
									<i class="spr_book ico_info_nolist"></i>
									<h1 class="tit">확정된 예약 리스트가 없습니다</h1>
								</div>
								<!--// 예약 리스트 없음 -->
							<c:forEach var="reservationDisplayInfo" items="${response.todoReservationResponse.reservationDisplayInfos}">
								<article class="card_item" data-id=${reservationDisplayInfo.id}
															data-product-id=${reservationDisplayInfo.productId}
															data-display-info-id=${reservationDisplayInfo.displayInfoId}
															data-category-name=${reservationDisplayInfo.categoryName}
															data-product-description='${reservationDisplayInfo.productDescription}'
															data-reservation-date=${reservationDisplayInfo.reservationDate}>
									<a class="link_booking_details">
										<div class="card_body">
											<div class="left"></div>
											<div class="middle">
												<div class="card_detail">
													<em class="booking_number">${reservationDisplayInfo.id}</em>
													<h4 class="tit">${reservationDisplayInfo.categoryName}/${reservationDisplayInfo.productDescription}</h4>
													<ul class="detail">
														<li class="item"><span class="item_tit">일정</span> <em class="item_dsc"> ${reservationDisplayInfo.reservationDate} </em></li>
														<li class="item"><span class="item_tit">내역</span> <em class="item_dsc"> 내역이 없습니다. </em></li>
														<li class="item"><span class="item_tit">장소</span> <em class="item_dsc"> ${reservationDisplayInfo.placeName} </em></li>
														<li class="item"><span class="item_tit">업체</span> <em class="item_dsc"> 업체명이 없습니다. </em></li>
													</ul>
													<div class="price_summary">
														<span class="price_tit">결제 예정금액</span> <em class="price_amount"> <span>${reservationDisplayInfo.totalPrice}</span> <span class="unit">원</span>
														</em>
													</div>
													<!-- [D] 예약 신청중, 예약 확정 만 취소가능, 취소 버튼 클릭 시 취소 팝업 활성화 -->
													<div class="booking_cancel">
														<button class="btn">
															<span>취소</span>
														</button>
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
									</a> <a href="#" class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
								</article>
							</c:forEach>
						</li>
						<c:if test="${response.todoReservationResponse.totalCount > 3}">
							<!-- [D] 더보기 -->
							<a class="btn_reservation_more TODO" onclick="moreReservations('TODO');">
								<span>더보기</span>
								<i class="fn fn-down2"></i>
							</a>
						</c:if>
						<li class="card used" data-status="DONE">
							<div class="link_booking_details">
								<div class="card_header">
									<div class="left"></div>
									<div class="middle">
										<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
										<i class="spr_book2 ico_check2"></i> <span class="tit">이용
											완료</span>
									</div>
									<div class="right"></div>
								</div>
							</div>

							<!-- 예약 리스트 없음 -->
								<div class="err"
									<c:if test="${response.doneReservationResponse.totalCount != 0}">
										style="display: none;"
									</c:if>
								>
									<i class="spr_book ico_info_nolist"></i>
									<h1 class="tit">완료된 예약 리스트가 없습니다</h1>
								</div>
								<!--// 예약 리스트 없음 -->

							<c:forEach var="reservationDisplayInfo" items="${response.doneReservationResponse.reservationDisplayInfos}">
								<article class="card_item">
									<a class="link_booking_details">
										<div class="card_body">
											<div class="left"></div>
											<div class="middle">
												<div class="card_detail">
													<em class="booking_number">${reservationDisplayInfo.id}</em>
													<h4 class="tit">${reservationDisplayInfo.categoryName}/${reservationDisplayInfo.productDescription}</h4>
													<ul class="detail">
														<li class="item"><span class="item_tit">일정</span> <em class="item_dsc"> ${reservationDisplayInfo.reservationDate} </em></li>
														<li class="item"><span class="item_tit">내역</span> <em class="item_dsc"> 내역이 없습니다. </em></li>
														<li class="item"><span class="item_tit">장소</span> <em class="item_dsc"> ${reservationDisplayInfo.placeName} </em></li>
														<li class="item"><span class="item_tit">업체</span> <em class="item_dsc"> 업체명이 없습니다. </em></li>
													</ul>
													<div class="price_summary">
														<span class="price_tit">결제 예정금액</span> <em class="price_amount"> <span>${reservationDisplayInfo.totalPrice}</span> <span class="unit">원</span>
														</em>
													</div>
													<div class="booking_cancel">
														<a> <button class="btn"> <span>예매자 리뷰 남기기</span> </button></a>
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
							</c:forEach>
							
						</li>
						<c:if test="${response.doneReservationResponse.totalCount > 3}">
							<!-- [D] 더보기 -->
							<a class="btn_reservation_more DONE" onclick="moreReservations('DONE');">
								<span>더보기</span>
								<i class="fn fn-down2"></i>
							</a>
						</c:if>
						<li class="card used cancel" data-status="CANCEL">
							<div class="link_booking_details">
								<div class="card_header">
									<div class="left"></div>
									<div class="middle">
										<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
										<i class="spr_book2 ico_cancel"></i> <span class="tit">취소된 예약</span>
									</div>
									<div class="right"></div>
								</div>
							</div>
							<!-- 예약 리스트 없음 -->
								<div class="err"
									<c:if test="${response.cancelReservationResponse.totalCount != 0}">
										style="display: none;"
									</c:if>
								>
									<i class="spr_book ico_info_nolist"></i>
									<h1 class="tit">취소된 예약 리스트가 없습니다</h1>
								</div>
							<!--// 예약 리스트 없음 -->
							<div >	
							<c:forEach var="reservationDisplayInfo" items="${response.cancelReservationResponse.reservationDisplayInfos}">
							<article class="card_item">
								<a class="link_booking_details">
									<div class="card_body">
										<div class="left"></div>
										<div class="middle">
											<div class="card_detail">
												<em class="booking_number">${reservationDisplayInfo.id}</em>
												<h4 class="tit">${reservationDisplayInfo.categoryName}/${reservationDisplayInfo.productDescription}</h4>
												<ul class="detail">
													<li class="item"><span class="item_tit">일정</span> <em class="item_dsc"> ${reservationDisplayInfo.reservationDate} </em></li>
													<li class="item"><span class="item_tit">내역</span> <em class="item_dsc"> 내역이 없습니다. </em></li>
													<li class="item"><span class="item_tit">장소</span> <em class="item_dsc"> ${reservationDisplayInfo.placeName} </em></li>
													<li class="item"><span class="item_tit">업체</span> <em class="item_dsc"> 업체명이 없습니다. </em></li>
												</ul>
												<div class="price_summary">
													<span class="price_tit">결제 예정금액</span> <em class="price_amount"> <span>${reservationDisplayInfo.totalPrice}</span> <span class="unit">원</span>
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
							</c:forEach>
							</div>
						</li>
						<c:if test="${response.cancelReservationResponse.totalCount > 3}">
							<!-- [D] 더보기 -->
							<a class="btn_reservation_more CANCEL" onclick="moreReservations('CANCEL');">
								<span>더보기</span>
								<i class="fn fn-down2"></i>
							</a>
						</c:if>
					</ul>
				</div>
				<!--// 내 예약 리스트 -->
			</div>
		</div>
		<hr>
	</div>
	<footer>
		<div class="gototop">
			<a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span>
			</a>
		</div>
		<div id="footer" class="footer">
			<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및
				환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
			<span class="copyright">© NAVER Corp.</span>
		</div>
	</footer>

	<!-- 취소 팝업 -->
	<!-- [D] 활성화 display:block, 아니오 버튼 or 닫기 버튼 클릭 시 숨김 display:none; -->
	<div class="popup_booking_wrapper" style="display: none;">
		<div class="dimm_dark" style="display: block"></div>
		<div class="popup_booking refund">
			<h1 class="pop_tit">
				<span>서비스명/상품명</span> <small class="sm">2000.0.00.(월)2000.0.00.(일)</small>
			</h1>
			<div class="nomember_alert">
				<p>취소하시겠습니까?</p>
			</div>
			<div class="pop_bottom_btnarea">
				<div class="btn_gray">
					<a class="btn_bottom"><span>아니오</span></a>
				</div>
				<div class="btn_green">
					<a class="btn_bottom"><span>예</span></a>
				</div>
			</div>
			<!-- 닫기 -->
			<a href="#" class="popup_btn_close" title="close"> <i class="spr_book2 ico_cls"></i>
			</a>
			<!--// 닫기 -->
		</div>
	</div>
	<!--// 취소 팝업 -->
	<script type="text/template" id="template-reservation">
		<article class="card_item" data-id={{id}}
								data-product-id={{productId}}
								data-display-info-id={{displayInfoId}}
								data-category-name={{categoryName}}
								data-product-description='{{productDescription}}'
								data-reservation-date={{reservationDate}}>
			<a class="link_booking_details">
				<div class="card_body">
					<div class="left"></div>
					<div class="middle">
						<div class="card_detail">
							<em class="booking_number">{{id}}</em>
							<h4 class="tit">{{categoryName}}/{{productDescription}}</h4>
							<ul class="detail">
								<li class="item"><span class="item_tit">일정</span> <em class="item_dsc"> {{reservationDate}} </em></li>
								<li class="item"><span class="item_tit">내역</span> <em class="item_dsc"> 내역이 없습니다. </em></li>
								<li class="item"><span class="item_tit">장소</span> <em class="item_dsc"> {{placeName}} </em></li>
								<li class="item"><span class="item_tit">업체</span> <em class="item_dsc"> 업체명이 없습니다. </em></li>
							</ul>
							<div class="price_summary">
								<span class="price_tit">결제 예정금액</span> <em class="price_amount"> <span>{{totalPrice}}</span> <span class="unit">원</span>
								</em>
							</div>
							{{#if cancelFlag }}
							{{else}}
								{{#isAfterToday reservationDate }}
									<!-- [D] 예약 신청중, 예약 확정 만 취소가능, 취소 버튼 클릭 시 취소 팝업 활성화 -->
									<div class="booking_cancel">
										<button class="btn">
											<span>취소</span>
										</button>
									</div>
								{{else}}
									<div class="booking_cancel">
										<a> <button class="btn"> <span>예매자 리뷰 남기기</span> </button></a>
									</div>
								{{/isAfterToday}}
							{{/if}}
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
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.1.0/handlebars.min.js"></script>
	<script type="text/javascript" src="/js/util.js"></script>
	<script type="text/javascript">
		var reservationsContainer = document.querySelector(".card.confirmed");
		var popupWrapper = document.querySelector(".popup_booking_wrapper");
		var reservationEmail = new URL(window.location.href).searchParams.get("reservationEmail");
		var reservationElement;
		
		const DEFAULT_LIMIT = 3;

		Handlebars.registerHelper("isAfterToday", function(date, options) {
			var now = new Date();
			var date = new Date(date);

			if (now < date) {
				return options.fn(this);
			}
			return options.inverse(this);
		});

		function registerEvent() {
			reservationsContainer.addEventListener("click", function(evt) {
				var target = evt.target;
				if (evt.target.tagName === "SPAN") {
					target = evt.target.parentElement;
				}
				if (target.className !== "btn") {
					return;
				}
				reservationElement = target;
				showCancleConfirmPopup();
			});
			popupWrapper.addEventListener("click", function(evt) {
				var target = evt.target;
				if (target.tagName === "SPAN" || target.tagName === "I") {
					target = target.parentElement;
				}
				if (target.className !== "btn_bottom"
						&& target.className !== "popup_btn_close") {
					return;
				}
				var text = target.firstElementChild.innerText;
				if (text === "예") {
					cancleMyReservations();
				}
				popupWrapper.style.display = "none";
			});
		}

		function showCancleConfirmPopup() {
			var wrapper = reservationElement.closest(".card_item");
			var categoryName = wrapper.dataset.categoryName;
			var productDescription = wrapper.dataset.productDescription;
			var reservationDate = wrapper.dataset.reservationDate;
			console.log(productDescription);
			popupWrapper.querySelector("span").innerText = categoryName + "/" + productDescription;
			popupWrapper.querySelector("small").innerText = reservationDate;
			popupWrapper.style.display = "block";
		}

		function cancleMyReservations() {
			var wrapper = reservationElement.closest(".card_item");
			var targetContainer = document.querySelector(".card.used.cancel");
			var reservationInfoId = wrapper.dataset.id;
			var url = "/api/reservations/" + reservationInfoId
			var method = "PUT";
			ajax(callbackRefreshCancelReservations(wrapper, targetContainer), url, method);
		}

		function callbackRefreshCancelReservations(element, targetContainer) {
			return function() {
				var originalContainer = element.closest(".card");
				targetContainer.appendChild(element);

				// 옮긴후 컨테이너에 예약이 없을경우 예약없음 엘리멘트 표시
				if (originalContainer.querySelectorAll(".card_item").length === 0) {
					originalContainer.querySelector(".err").style.display = "block";
				}
				targetContainer.querySelector(".err").style.display = "none";

				var cancleCount = targetContainer.querySelectorAll(".card_item").length * 1 - 1;

				targetContainer.querySelectorAll(".card_item").forEach(e => e.parentNode.removeChild(e));

				moreReservations("TODO", 1);
				moreReservations("CANCEL", cancleCount);
			}
		}

		function moreReservations(status, limit) {
			if (!limit) {
				limit = DEFAULT_LIMIT;
			}
			var container = document.querySelector("[data-status=" + status
					+ "]");
			var status = container.dataset.status;
			var count = container.querySelectorAll(".card_item").length;

			var url = "/api/reservations?reservationEmail=" + reservationEmail
					+ "&status=" + status + "&start=" + count + "&limit=" + limit;

			ajax(callbackAddReservationElements(container), url);
		}

		function callbackAddReservationElements(container) {
			return function(response) {
				var template = document.querySelector("#template-reservation").innerText;
				var bindTemplate = Handlebars.compile(template);
				var resultHTML = response.reservationDisplayInfos.reduce(
						function(prev, reservation) {
							return prev + bindTemplate(reservation);
						}, "");
				container.innerHTML += resultHTML;

				var status = container.dataset.status;
				var currCount = container.querySelectorAll(".card_item").length;
				// 개수 갱신
				document.querySelector(".figure." + status).innerText = response.totalCount;

				// 예매한 개수가 현재 엘리먼트로 보여주는 개수보다 적거나 같다면 더보기버튼을 숨깁니다.
				if (response.totalCount <= currCount) {
					document.querySelector(".btn_reservation_more." + status).style.display = "none";
				}
			}
		}

		registerEvent();
	</script>
</body>

</html>
