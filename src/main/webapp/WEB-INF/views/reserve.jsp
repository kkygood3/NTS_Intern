<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<!-- [D] 예약하기로 들어오면 header에 fade 클래스 추가로 숨김 -->
		<div class="header fade">
			<header class="header_tit">
				<h1 class="logo">
					<a href="/main" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
					<a href="/main" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
				</h1>
				<a href="#" class="btn_my"> <span title="예약확인">예약확인</span> </a>
			</header>
		</div>
		<div class="ct">
			<div class="ct_wrap">
				<div class="top_title">
					<a onclick="goDetailPage()" class="btn_back" title="이전 화면으로 이동"> <i class="fn fn-backward1"></i> </a>
					<h2><span class="title">${displayInfo.productDescription}</span></h2>
				</div>
				<div class="group_visual">
					<div class="container_visual" style="width: 414px;">
						<ul class="visual_img">
							<li class="item" style="width: 414px;">
								<img alt="" class="img_thumb" src="/${productImageUrl}">
								<span class="img_bg"></span>
								<div class="preview_txt">
									<h2 class="preview_txt_tit">${displayInfo.productDescription}</h2>
									<em class="preview_txt_dsc">₩<fmt:parseNumber integerOnly="true" value="${minPrice}"/> ~ </em>
									<em class="preview_txt_dsc">${displayInfo.openingHours}</em>
								</div>
							</li>
						</ul>
					</div>
				</div>
				<div class="section_store_details">
					<div class="store_details">
						<h3 class="in_tit"></h3>
						<p class="dsc">
							장소 : ${displayInfo.placeName}
						</p>
						<h3 class="in_tit">관람시간</h3>
						<p class="dsc">
							${displayInfo.openingHours}
						</p>
						<h3 class="in_tit">요금</h3>
						<p class="dsc">
							<c:forEach var="productPrice" items="${productPrices}">
								${productPrice.typeDescription} ${productPrice.price} <br />
							</c:forEach>
							<!-- 성인(만 19~64세) 5,000원 / 청소년(만 13~18세) 4,000원<br> 어린이(만 4~12세) 3,000원 / 20인 이상 단체 20% 할인<br> 국가유공자, 장애인, 65세 이상 4,000원 -->
						</p>
						<p class="dsc">
							티켓 유형별 최대 10매 구매가능합니다.
						</p>
					</div>
				</div>
				<div class="section_booking_ticket">
					<div class="ticket_body">
						<c:forEach var="productPrice" items="${productPrices}">
							<div class="qty" data-id=${productPrice.id}
																data-type=${productPrice.type}
																data-price=${productPrice.price * (100-productPrice.discountRate) / 100}>
								<div class="count_control">
									<!-- [D] 수량이 최소 값이 일때 ico_minus3, count_control_input에 disabled 각각 추가, 수량이 최대 값일 때는 ico_plus3에 disabled 추가 -->
									<div class="clearfix">
										<a class="btn_plus_minus spr_book2 ico_minus3 disabled" title="빼기"></a>
										<input type="tel" class="count_control_input disabled" value="0" readonly title="수량">
										<a class="btn_plus_minus spr_book2 ico_plus3" title="더하기"></a>
									</div>
									<div class="warning_msg">최대 수량입니다.</div>
									<!-- [D] 금액이 0 이상이면 individual_price에 on_color 추가 -->
									<div class="individual_price">
										<span class="total_price">0</span><span class="price_type">원</span>
									</div>
								</div>
								<div class="qty_info_icon">
									<strong class="product_amount"><span>${productPrice.typeDescription}</span></strong>
									<strong class="product_price">
										<span class="price">${productPrice.price}</span>
										<span class="price_type"> 원</span>
									</strong>
									<em class="product_dsc">
										<fmt:parseNumber integerOnly="true" 
													value="${productPrice.price * (100-productPrice.discountRate) / 100}"/>
										 원
										<c:if test="${productPrice.discountRate > 0}">
											(${productPrice.discountRate}% 할인가)
										</c:if>
									</em>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="section_booking_form">
					<div class="booking_form_wrap">
						<div class="form_wrap">
							<h3 class="out_tit">예매자 정보</h3>
							<div class="agreement_nessasary help_txt"> <span class="spr_book ico_nessasary"></span> <span>필수입력</span> </div>
							<form class="form_horizontal">
								<div class="inline_form">
									<label class="label" for="name">
										<span class="spr_book ico_nessasary">필수</span> <span>예매자</span>
									</label>
									<div class="inline_control">
										<input type="text" name="name" id="name" class="text" placeholder="네이버" maxlength="17">
										<div class="warning_msg">한글 및 영문자만 입력가능합니다.</div>
									</div>
								</div>
								<div class="inline_form">
									<label class="label" for="tel">
										<span class="spr_book ico_nessasary">필수</span> <span>연락처</span>
									</label>
									<div class="inline_control tel_wrap">
										<input type="tel" name="tel" id="tel" class="tel" value="" placeholder="휴대폰 입력 시 예매내역 문자발송" maxlength="13">
										<div class="warning_msg">000-000(0)-0000 형식만 가능합니다.</div>
									</div>
								</div>
								<div class="inline_form">
									<label class="label" for="email">
										<span class="spr_book ico_nessasary">필수</span> <span>이메일</span>
									</label>
									<div class="inline_control">
										<input type="email" name="email" id="email" class="email" value="" placeholder="crong@codesquad.kr" maxlength="50">
										<div class="warning_msg">이메일 형식이 틀렸습니다.</div>
									</div>
								</div>
								<div class="inline_form last"> <label class="label" for="message">예매내용</label>
									<div class="inline_control">
										<p class="inline_txt selected">${reservationDate} ,총 <span id='total_count'>0</span>매</p>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="section_booking_agreement">
						<div class="agreement all">
							<input type="checkbox" id="chk3" class="chk_agree">
							<label for="chk3" class="label chk_txt_label">
								<span>이용자 약관 전체동의</span>
							</label>
							<div class="agreement_nessasary">
								<span>필수동의</span>
							</div>
						</div>
						<!-- [D] 약관 보기 클릭 시 agreement에 open 클래스 추가 -->
						<div class="agreement">
							<span class="chk_txt_span"> <i class="spr_book ico_arr_ipc2"></i> <span>개인정보 수집 및 이용 동의</span> </span>
							<a class="btn_agreement"> <span class="btn_text">보기</span> <i class="fn fn-down2"></i> </a>
							<div class="useragreement_details">
								&lt;개인정보 수집 및 이용 동의&gt;<br><br>
								1. 수집항목 : [필수] 이름, 연락처, [선택] 이메일주소<br><br>
								2. 수집 및 이용목적 : 사업자회원과 예약이용자의 원활한 거래 진행, 고객상담, 불만처리 등 민원 처리, 분쟁조정 해결을 위한 기록보존, 네이버 예약 이용 후 리뷰작성에 따른 네이버페이 포인트 지급 및 관련 안내<br><br>
								3. 보관기간<br> - 회원탈퇴 등 개인정보 이용목적 달성 시까지 보관<br> - 단, 상법 및 ‘전자상거래 등에서의 소비자 보호에 관한 법률’ 등 관련 법령에 의하여 일정 기간 보관이 필요한 경우에는 해당 기간 동안 보관함<br><br>
								4. 동의 거부권 등에 대한 고지: 정보주체는 개인정보의 수집 및 이용 동의를 거부할 권리가 있으나, 이 경우 상품 및 서비스 예약이 제한될 수 있습니다.<br>
							</div>
						</div>
						<!-- [D] 약관 보기 클릭 시 agreement에 open 클래스 추가 -->
						<div class="agreement">
							<span class="chk_txt_span"> <i class="spr_book ico_arr_ipc2"></i> <span>개인정보 제3자 제공 동의</span> </span>
							<a class="btn_agreement"> <span class="btn_text">보기</span> <i class="fn fn-down2"></i> </a>
							<div class="useragreement_details custom_details_wrap">
								<div class="custom_details">
									&lt;개인정보 제3자 제공 동의&gt;<br><br>
									1. 개인정보를 제공받는 자 : 미디어앤아트<br><br>
									2. 제공하는 개인정보 항목 : [필수] 네이버 아이디, 이름, 연락처 [선택] 이메일 주소<br><br>
									3. 개인정보를 제공받는 자의 이용목적 : 사업자회원과 예약이용자의 원활한 거래 진행, 고객상담, 불만처리 등 민원 처리, 서비스 이용에 따른 설문조사 및 혜택 제공, 분쟁조정 해결을 위한 기록보존<br><br>
									4. 개인정보를 제공받는 자의 개인정보 보유 및 이용기간 : 개인정보 이용목적 달성 시 까지 보관합니다.<br><br>
									5. 동의 거부권 등에 대한 고지 : 정보주체는 개인정보 제공 동의를 거부할 권리가 있으나, 이 경우 상품 및 서비스 예약이 제한될 수 있습니다.<br>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="box_bk_btn">
					<!-- [D] 약관 전체 동의가 되면 disable 제거 -->
					<div class="bk_btn_wrap disable">
						<button type="button" class="bk_btn">
							<i class="spr_book ico_naver_s"></i> <span>예약하기</span>
						</button>
					</div>
				</div>
			</div>
		</div>
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
	<script type="text/javascript" src="/js/constant/regularExpression.js"></script>
	<script type="text/javascript" src="/js/util.js"></script>
	<script type="text/javascript">
		var productId = parseInt(window.location.pathname.split("/")[2]);
		var displayInfoId = parseInt(new URL(window.location.href).searchParams.get("displayInfoId"));

		// 이전 detail 페이지로 이동
		function goDetailPage() {
			location.href = "detail?displayInfoId=" + displayInfoId;
		}

		// 티켓수 선택 이벤트
		function BookingTicket(ticketBody) {
			this.ticketBody = ticketBody;
			this.tickets = {};
			this.totalCount = 0;
			this.init();
			this.registerEvents();
		}

		BookingTicket.prototype = {
			init: function () {
				ticketBody.querySelectorAll(".qty").forEach(function (element) {
					var type = element.getAttribute("data-type");
					var productPriceId = element.getAttribute("data-id");
					this.tickets[type] = {
						"productPriceId": productPriceId,
						"count": 0
					}
				}.bind(this));
			},
			registerEvents: function () {
				this.ticketBody.addEventListener("click", function (evt) {
					if (evt.target.classList.contains("disabled")) {
						return;
					}
					var wrapper = evt.target.closest(".qty");
					if (evt.target.title === "더하기") {
						this.changeTicket(wrapper, + 1);
					} else if (evt.target.title === "빼기") {
						this.changeTicket(wrapper, -1);
					}
				}.bind(this));
			},
			changeTicket: function (wrapper, value) {
				var countControlInput = wrapper.querySelector('.count_control_input');
				var totalPriceElement = wrapper.querySelector('.total_price');
				var price = wrapper.getAttribute("data-price");
				var type = wrapper.getAttribute("data-type");
				var warningElement = wrapper.querySelector(".count_control .warning_msg");

				if (this.tickets[type].count === 0) {
					countControlInput.classList.remove("disabled");
					totalPriceElement.parentElement.classList.add("on_color");
					wrapper.querySelector('.ico_minus3').classList.remove("disabled");
				}

				if (this.tickets[type].count === 10) {
					wrapper.querySelector('.ico_plus3').classList.remove("disabled");
					hideWarningMsg(warningElement);
				}

				this.tickets[type].count += value;
				this.totalCount += value;
				countControlInput.value = this.tickets[type].count
				totalPriceElement.innerText = this.tickets[type].count * price;

				if (this.tickets[type].count === 0) {
					countControlInput.classList.add("disabled");
					totalPriceElement.parentElement.classList.remove("on_color");
					wrapper.querySelector('.ico_minus3').classList.add("disabled");
				}

				if (this.tickets[type].count === 10) {
					wrapper.querySelector('.ico_plus3').classList.add("disabled");
					showWarningMsg(warningElement);
				}

				document.querySelector("#total_count").innerText = this.totalCount;
			}
		}

		// 예매자 정보 영역
		function BookingForm(bookingForm) {
			this.bookingForm = bookingForm;
			this.isAgree = false;
			this.isValids = {};
			this.inputValues = {};
			this.reservationDate = "";
			this.init();
			this.registerEvents();
		}

		BookingForm.prototype = {
			init: function () {
				var bookingFormWrap = this.bookingForm.querySelector(".booking_form_wrap");
				var inputElements = bookingFormWrap.querySelectorAll("input");
				bookingFormWrap.querySelectorAll("input").forEach(function (element) {
					this.isValids[element.name] = false;
					this.inputValues[element.name] = false;
				}.bind(this));
				this.reservationDate = bookingFormWrap.querySelector(".inline_txt.selected").innerText.split(" ")[0];
			},
			registerEvents: function () {
				// 예매자 정보 입력폼 영역 이벤트등록
				var bookingFormWrap = this.bookingForm.querySelector(".booking_form_wrap");
				bookingFormWrap.addEventListener("change", function (evt) {
					var formContainer = evt.target.closest(".inline_control");
					var warningElement = formContainer.querySelector(".warning_msg");
					var name = evt.target.name;
					var text = evt.target.value;
					var isValid = false;

					if (name === "name") {
						isValid = this.validText(NAME_REGEXP, text);
					} else if (name === "tel") {
						isValid = this.validText(PHONE_REGEXP, text);
					} else if (name === "email") {
						isValid = this.validText(EMAIL_REGEXP, text);
					} else {
						return;
					}
					this.isValids[name] = isValid;
					this.inputValues[name] = text;
					if (isValid) {
						hideWarningMsg(warningElement);
					} else {
						showWarningMsg(warningElement);
					}
				}.bind(this));

				// 약관정보 영역 이벤트 등록
				var bookingAgreement = this.bookingForm.querySelector(".section_booking_agreement");
				bookingAgreement.addEventListener("click", function (evt) {
					var agreementContainer = evt.target.closest(".agreement");
					if (evt.target.className === "btn_agreement" || evt.target.parentElement.className === "btn_agreement") {
						this.toggleAgreementContent(agreementContainer);
					}
					if (evt.target.className === "chk_agree") {
						this.toggleIsAgree();
					}
				}.bind(this));
			},
			validText: function (regExp, text) {
				return regExp.test(text);
			},
			// 약관내용 보기/닫기
			toggleAgreementContent: function (container) {
				if (container.classList.contains("open")) {
					container.classList.remove("open");
					container.querySelector(".btn_text").innerText = "보기";
					container.querySelector(".fn").classList.remove("fn-up2");
					container.querySelector(".fn").classList.add("fn-down2");
				} else {
					container.classList.add("open");
					container.querySelector(".btn_text").innerText = "닫기";
					container.querySelector(".fn").classList.remove("fn-donw2");
					container.querySelector(".fn").classList.add("fn-up2");
				}
			},
			toggleIsAgree: function () {
				this.isAgree = !this.isAgree;
			}
		}

		function BookingSubmit(submitWrap, ticket, form) {
			this.submitWrap = submitWrap;
			this.ticket = ticket;
			this.form = form;
			this.registerEvents();
		}

		BookingSubmit.prototype = {
			registerEvents: function () {
				// watch All is Valid
				this.ticket.ticketBody.addEventListener("click", this.checkTotalValid.bind(this));
				this.form.bookingForm.addEventListener("change", this.checkTotalValid.bind(this));
				// post Submit
				this.submitWrap.addEventListener("click", function (evt) {
					if (this.submitWrap.classList.contains("disable")) {
						return;
					}
					var data = {
						"displayInfoId": displayInfoId,
						"productId": productId,
						"prices": Object.values(this.ticket.tickets).filter(price => price.count > 0),
						"reservationName": this.form.inputValues["name"],
						"reservationTelephone": this.form.inputValues["tel"],
						"reservationEmail": this.form.inputValues["email"],
						"reservationDate": this.form.reservationDate
					}
					var url = "/api/reservations";
					var method = "POST";
					ajax(this.confrimAndGoMyReservationPage, url, method, JSON.stringify(data));
				}.bind(this));
			},
			confrimAndGoMyReservationPage: function (response) {
				if (response.isError) {
					alert("예약하기 실패!!\n 에러내용 : " + response.errorMsg);
					return;
				}
				if (confirm("예약 성공! 나의예약페이지로 이동하시겠습니까?")) {
					location.href="/myreservation?reservationEmail=" + this.form.inputValues["email"];
				}
			},
			
			// 모든 정보가 유효한지 검증 유효한지에 따라  submit 버튼 enable/disable
			checkTotalValid: function () {
				if (this.form.isAgree === false || this.ticket.totalCount === 0) {
					this.disableSubmitBtn();
					return;
				}
				for (var i in this.form.isValids) {
					if (this.form.isValids[i] === false) {
						this.disableSubmitBtn();
						return;
					}
				}
				this.enableSubmitBtn();
			},
			disableSubmitBtn: function () {
				if (this.submitWrap.classList.contains("disable") === false) {
					this.submitWrap.classList.add("disable");
				}
			},
			enableSubmitBtn: function () {
				if (submitWrap.classList.contains("disable") === true) {
					this.submitWrap.classList.remove("disable");
				}
			}
		}

		// 경고메시지 출력
		function showWarningMsg(warningElement) {
			warningElement.style.visibility = "visible";
			warningElement.style.position = "relative";
		}
		// 경고메시지 숨김
		function hideWarningMsg(warningElement) {
			warningElement.style.visibility = "hidden";
			warningElement.style.position = "absolute";
		}

		var ticketBody = document.querySelector(".ticket_body");
		var ticket = new BookingTicket(ticketBody);
		var bookingForm = document.querySelector(".section_booking_form");
		var form = new BookingForm(bookingForm);
		var submitWrap = document.querySelector(".bk_btn_wrap");
		var bookingSubmit = new BookingSubmit(submitWrap, ticket, form);
	</script>

</body>

</html>
