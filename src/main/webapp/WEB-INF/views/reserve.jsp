<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="utf-8">
	<meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
	<title>네이버 예약</title>
	<link href="/resources/css/style.css" rel="stylesheet">
</head>

<body>
	<div id="container">
		<div id="display_info" class="ct" data-display-info-id="${displayInfoId}">
			<div class="ct_wrap">
				<div class="top_title">
					<a href="product-display?displayInfoId=${displayInfoId}" class="btn_back" title="이전 화면으로 이동"> <i class="fn fn-backward1"></i> </a>
					<h2><span class="title"></span></h2>
				</div>
				<div class="group_visual">
					<div class="container_visual" style="width: 414px;">
						<ul class="visual_img">
							<li class="item" style="width: 414px;"> <img id="productDisplayImg" alt="" class="img_thumb" src=""> <span class="img_bg"></span>
							<div class="preview_txt">
								<h2 id="productDescription" class="preview_txt_tit"></h2>
							</div>
							</li>
						</ul>
					</div>
				</div>
				<div class="section_store_details">
					<div class="store_details">
						<h3 class="in_tit">요금</h3>
						<p id="price_information" class="dsc">
						</p>
					</div>
				</div>
				<div class="section_booking_ticket">
					<div id="product_price_list" class="ticket_body">
						
					</div>
				</div>
				<div class="section_booking_form">
					<div class="booking_form_wrap">
						<div class="form_wrap">
							<h3 class="out_tit">예매자 정보</h3>
							<div class="agreement_nessasary help_txt"> <span class="spr_book ico_nessasary"></span> <span>필수입력</span> </div>
							<form id="reservation_form" class="form_horizontal">
								<input type="hidden" name="productId" id="productId" value=""/>
								<input type="hidden" name="displayInfoId" id="displayInfoId" value=""/>
								<div class="inline_form"> <label class="label" for="name"> <span class="spr_book ico_nessasary">필수</span> <span>예매자</span> </label>
									<div class="inline_control"> <input type="text" name="name" id="name" class="text" placeholder="네이버" maxlength="17" data-valid-flag="false"> </div>
								</div>
								<div class="inline_form"> <label class="label" for="tel"> <span class="spr_book ico_nessasary">필수</span> <span>연락처</span> </label>
									<div class="inline_control tel_wrap">
										<input type="tel" name="tel" id="tel" class="tel" value="" placeholder="예) 010-0000-1111" data-valid-flag="false">
										<div id="tel_warning" class="warning_msg">형식이 틀렸거나 너무 짧아요</div>
									</div>
								</div>
								<div class="inline_form"> <label class="label" for="email">  <span class="spr_book ico_nessasary">필수</span>  <span>이메일</span> </label>
									<div class="inline_control email_wrap">
										<input type="email" name="email" id="email" class="email" value="" placeholder="crong@codesquad.kr" maxlength="50" data-valid-flag="false">
										<div id="email_warning" class="warning_msg">형식이 틀렸어요</div>
									</div>
								</div>
								<div class="inline_form last"> <label class="label" for="message">예매내용</label>
									<div class="inline_control">
										<input type="hidden" name="date" id="date" value=""/>
										<p class="inline_txt selected"><span id="reservation_date"></span>, 총 <span id="total_count" data-valid-flag="false"></span>매</p>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="section_booking_agreement">
						<div class="agreement all"> <input type="checkbox" id="agreement" class="chk_agree" data-valid-flag="false"> <label for="agreement" class="label chk_txt_label"> <span>이용자 약관 전체동의</span> </label>
							<div class="agreement_nessasary">
								<span>필수동의</span> </div>
						</div>
						<!-- [D] 약관 보기 클릭 시 agreement에 open 클래스 추가 -->
						<div id="privacy_collect_content" class="agreement"> <span class="chk_txt_span"> <i class="spr_book ico_arr_ipc2"></i> <span>개인정보 수집 및 이용 동의</span> </span>
							<a id="btn_privacy_collect_content" class="btn_agreement"> <span class="btn_text">보기</span> <i class="fn fn-down2"></i> </a>
							<div class="useragreement_details">&lt;개인정보 수집 및 이용 동의&gt;<br><br> 1. 수집항목 : [필수] 이름, 연락처, [선택] 이메일주소<br><br> 2. 수집 및 이용목적 : 사업자회원과 예약이용자의 원활한 거래 진행, 고객상담, 불만처리 등 민원 처리, 분쟁조정 해결을 위한 기록보존, 네이버 예약 이용 후 리뷰작성에 따른 네이버페이 포인트 지급 및 관련 안내<br><br> 3. 보관기간<br> - 회원탈퇴 등
								개인정보 이용목적 달성 시까지 보관<br> - 단, 상법 및 ‘전자상거래 등에서의 소비자 보호에 관한 법률’ 등 관련 법령에 의하여 일정 기간 보관이 필요한 경우에는 해당 기간 동안 보관함<br><br> 4. 동의 거부권 등에 대한 고지: 정보주체는 개인정보의 수집 및 이용 동의를 거부할 권리가 있으나, 이 경우 상품 및 서비스 예약이 제한될 수 있습니다.<br></div>
						</div>
						<!-- [D] 약관 보기 클릭 시 agreement에 open 클래스 추가 -->
						<div id="privacy_use_other_content" class="agreement"> <span class="chk_txt_span"> <i class="spr_book ico_arr_ipc2"></i> <span>개인정보 제3자 제공 동의</span> </span>
							<a id="btn_privacy_use_other_content" class="btn_agreement"> <span class="btn_text">보기</span> <i class="fn fn-down2"></i> </a>
							<div class="useragreement_details custom_details_wrap">
								<div class="custom_details">&lt;개인정보 제3자 제공 동의&gt;<br><br> 1. 개인정보를 제공받는 자 : 미디어앤아트<br><br> 2. 제공하는 개인정보 항목 : [필수] 네이버 아이디, 이름, 연락처 [선택] 이메일 주소<br><br> 3. 개인정보를 제공받는 자의 이용목적 : 사업자회원과 예약이용자의 원활한 거래 진행, 고객상담, 불만처리 등 민원 처리, 서비스 이용에 따른 설문조사 및 혜택 제공, 분쟁조정
									해결을 위한 기록보존<br><br> 4. 개인정보를 제공받는 자의 개인정보 보유 및 이용기간 : 개인정보 이용목적 달성 시 까지 보관합니다.<br><br> 5. 동의 거부권 등에 대한 고지 : 정보주체는 개인정보 제공 동의를 거부할 권리가 있으나, 이 경우 상품 및 서비스 예약이 제한될 수 있습니다.<br></div>
							</div>
						</div>
					</div>
				</div>
				<div class="box_bk_btn">
					<!-- [D] 약관 전체 동의가 되면 disable 제거 -->
					<div id="btn_submit_view" class="bk_btn_wrap disable"> <button id="btn_submit" type="button" class="bk_btn" disabled> <i class="spr_book ico_naver_s"></i>  <span>예약하기</span> </button> </div>
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

	<script type="text/template" class="template" data-parse-name="ProductPrice">
		<div class="qty" data-name="product_price_id" data-product-price-id="{{productPriceId}}">
			<div class="count_control">
				<div class="clearfix">
					<a class="btn_plus_minus spr_book2 ico_minus3" title="빼기" data-name="minus"> </a> 
					<input type="tel" class="count_control_input" value="0" readonly title="수량" data-name="count">
					<a class="btn_plus_minus spr_book2 ico_plus3" title="더하기" data-name="plus"></a>
				</div>
				<div class="individual_price on_color"><span class="total_price" data-name="total_price">0</span><span class="price_type">원</span></div>
			</div>
			<div class="qty_info_icon">
				<strong class="product_amount">
					<span>{{priceType.typeName}}</span>
				</strong>
				<strong class="product_price">
					<span class="price">{{price}}</span>
					<span class="price_type">원</span>
				</strong>
				<em class="product_dsc">{{price}}원 ({{discountRate}}% 할인가)</em>
			</div>
		</div>	
	</script>

	<script type="text/javascript" src="/resources/js/handlebars-v4.0.12.js"></script>
	<script type="text/javascript" src="/resources/js/template_maker.js"></script>
	<script type="text/javascript" src="/resources/js/ajax.js"></script>
	<script type="text/javascript" src="/resources/js/page/reserve.js"></script>
</body>

</html>