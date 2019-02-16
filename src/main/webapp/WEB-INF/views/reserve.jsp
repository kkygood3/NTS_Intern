<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <!-- [D] 예약하기로 들어오면 header에 fade 클래스 추가로 숨김 -->
        <div class="header fade">
            <header class="header_tit">
                <h1 class="logo">
                    <a href="/" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
                    <a href="/" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
                </h1>
                <a href="#" class="btn_my"> <span title="예약확인">예약확인</span> </a>
            </header>
        </div>
        <div class="ct">
            <div class="ct_wrap">
                <div class="top_title">
                    <a href="detail" class="btn_back" title="이전 화면으로 이동"> <i class="fn fn-backward1"></i> </a>
                    <h2><span class="title">${reserveDisplayInfo.productDescription}</span></h2>
                </div>
                <div class="group_visual">
                    <div class="container_visual" style="width: 414px;">
                        <ul class="visual_img">
                            <li class="item" style="width: 414px; cursor: default;"> <img alt="" class="img_thumb" src="${reserveDisplayInfo.productImage}"> <span class="img_bg"></span>
                                <div class="preview_txt">
                                    <h2 class="preview_txt_tit"></h2> <em class="preview_txt_dsc">₩
                                    <c:set var="minValue" scope="page" value="${reservePrice[0].price}"/>
									<c:forEach items="${reservePrice}" var="item">
										<c:if test="${minValue > item.price}">
											<c:set var="minValue" scope="page" value="${item.price}"/>
										</c:if>
									</c:forEach>
									${minValue}
									 ~ </em><em class="preview_txt_dsc"><span class='date_area'>2017.2.17.(금)~2017.4.18.(화)</span>, 잔여티켓 2769매</em> </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="section_store_details">
                    <div class="store_details">
                        <h3 class="in_tit"></h3>
                        <p class="dsc">
                            장소 : ${reserveDisplayInfo.placeName}<br> 기간 : 2017.2.17.(금)~2017.4.18.(화)
                        </p>
                        <h3 class="in_tit">관람시간</h3>
                        <p class="dsc">
                            ${reserveDisplayInfo.openingHours}
                        </p>
                        <h3 class="in_tit">요금</h3>
                        <p class="dsc">
                            성인(만 19~64세) 5,000원 / 청소년(만 13~18세) 4,000원<br> 어린이(만 4~12세) 3,000원 / 20인 이상 단체 20% 할인<br> 국가유공자, 장애인, 65세 이상 4,000원
                            
                        </p>
                    </div>
                </div>
                <div class="section_booking_ticket">
                    <div class="ticket_body">
                       	<c:forEach items="${reservePrice}" var="item">
                       		<c:set var="cost" scope="page" value="${fn:substringBefore(item.price * (100-item.discountRate) / 100,'.')}"/>
	                    	<div class="qty" cost="${cost}">
	                            <div class="count_control">
	                                <!-- [D] 수량이 최소 값이 일때 ico_minus3, count_control_input에 disabled 각각 추가, 수량이 최대 값일 때는 ico_plus3에 disabled 추가 -->
	                                <div class="clearfix">
	                                    <a href="#" class="btn_plus_minus spr_book2 ico_minus3 disabled" title="빼기"> </a> <input type="tel" class="count_control_input disabled" value="0" readonly title="수량">
	                                    <a href="#" class="btn_plus_minus spr_book2 ico_plus3" title="더하기">
	                                    </a>
	                                </div>
	                                <!-- [D] 금액이 0 이상이면 individual_price에 on_color 추가 -->
	                                <div class="individual_price"><span class="total_price">0</span><span class="price_type">원</span></div>
	                            </div>
	                            <div class="qty_info_icon"> <strong class="product_amount"> <span>${item.priceTypeName}</span> </strong> <strong class="product_price"> <span class="price"><fmt:formatNumber value="${item.price}" pattern="#,###"/></span> <span class="price_type">원</span> </strong>
	                            <c:if test="${item.discountRate > 0}">
									<em class="product_dsc"><fmt:formatNumber value="${cost}" pattern="#,###"/>원 (${item.discountRate}% 할인가)</em>
								</c:if>
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
                                <div class="inline_form"> <label class="label" for="name"> <span class="spr_book ico_nessasary">필수</span> <span>예매자</span> </label>
                                    <div class="inline_control tel_wrap"> 
	                                    <input type="text" name="name" id="name" class="text" placeholder="네이버" maxlength="17">
	                                    <div class="warning_msg" style="height: 30px; padding-top:1px;">형식이 틀렸거나 너무 짧아요</div> 
                                    </div>
                                </div>
                                <div class="inline_form"> <label class="label" for="tel"> <span class="spr_book ico_nessasary">필수</span> <span>연락처</span> </label>
                                    <div class="inline_control tel_wrap">
                                        <input type="tel" name="tel" id="tel" class="tel" value="" placeholder="휴대폰 입력 시 예매내역 문자발송">
                                        <div class="warning_msg" style="height: 30px; padding-top:1px;">형식이 틀렸거나 너무 짧아요</div>
                                    </div>
                                </div>
                                <div class="inline_form"> <label class="label" for="email">  <span class="spr_book ico_nessasary">필수</span>  <span>이메일</span> </label>
                                    <div class="inline_control tel_wrap">
                                    	<input type="email" name="email" id="email" class="email" value="" placeholder="crong@codesquad.kr" maxlength="50">
                                    	<div class="warning_msg" style="height: 30px; padding-top:1px;">형식이 틀렸거나 너무 짧아요</div>
                                    </div>
                                </div>
                                <div class="inline_form last"> <label class="label" for="message">예매내용</label>
                                    <div class="inline_control">
                                        <p class="inline_txt selected">2017.2.17, 총 <span id="totalCount">0</span>매</p>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="section_booking_agreement">
                        <div class="agreement all"> <input type="checkbox" id="chk3" class="chk_agree"> <label for="chk3" class="label chk_txt_label"> <span>이용자 약관 전체동의</span> </label>
                            <div class="agreement_nessasary">
                                <span>필수동의</span> </div>
                        </div>
                        <!-- [D] 약관 보기 클릭 시 agreement에 open 클래스 추가 -->
                        <div class="agreement"> <span class="chk_txt_span"> <i class="spr_book ico_arr_ipc2"></i> <span>개인정보 수집 및 이용 동의</span> </span>
                            <a href="#" class="btn_agreement"> <span class="btn_text">보기</span> <i class="fn fn-down2"></i> </a>
                            <div class="useragreement_details">&lt;개인정보 수집 및 이용 동의&gt;<br><br> 1. 수집항목 : [필수] 이름, 연락처, [선택] 이메일주소<br><br> 2. 수집 및 이용목적 : 사업자회원과 예약이용자의 원활한 거래 진행, 고객상담, 불만처리 등 민원 처리, 분쟁조정 해결을 위한 기록보존, 네이버 예약 이용 후 리뷰작성에 따른 네이버페이 포인트 지급 및 관련 안내<br><br> 3. 보관기간<br> - 회원탈퇴 등
                                개인정보 이용목적 달성 시까지 보관<br> - 단, 상법 및 ‘전자상거래 등에서의 소비자 보호에 관한 법률’ 등 관련 법령에 의하여 일정 기간 보관이 필요한 경우에는 해당 기간 동안 보관함<br><br> 4. 동의 거부권 등에 대한 고지: 정보주체는 개인정보의 수집 및 이용 동의를 거부할 권리가 있으나, 이 경우 상품 및 서비스 예약이 제한될 수 있습니다.<br></div>
                        </div>
                        <!-- [D] 약관 보기 클릭 시 agreement에 open 클래스 추가 -->
                        <div class="agreement"> <span class="chk_txt_span"> <i class="spr_book ico_arr_ipc2"></i> <span>개인정보 제3자 제공 동의</span> </span>
                            <a href="#" class="btn_agreement"> <span class="btn_text">보기</span> <i class="fn fn-down2"></i> </a>
                            <div class="useragreement_details custom_details_wrap">
                                <div class="custom_details">&lt;개인정보 제3자 제공 동의&gt;<br><br> 1. 개인정보를 제공받는 자 : 미디어앤아트<br><br> 2. 제공하는 개인정보 항목 : [필수] 네이버 아이디, 이름, 연락처 [선택] 이메일 주소<br><br> 3. 개인정보를 제공받는 자의 이용목적 : 사업자회원과 예약이용자의 원활한 거래 진행, 고객상담, 불만처리 등 민원 처리, 서비스 이용에 따른 설문조사 및 혜택 제공, 분쟁조정
                                    해결을 위한 기록보존<br><br> 4. 개인정보를 제공받는 자의 개인정보 보유 및 이용기간 : 개인정보 이용목적 달성 시 까지 보관합니다.<br><br> 5. 동의 거부권 등에 대한 고지 : 정보주체는 개인정보 제공 동의를 거부할 권리가 있으나, 이 경우 상품 및 서비스 예약이 제한될 수 있습니다.<br></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="box_bk_btn">
                    <!-- [D] 약관 전체 동의가 되면 disable 제거 -->
                    <div class="bk_btn_wrap disable"> <button type="button" class="bk_btn"> <i class="spr_book ico_naver_s"></i>  <span>예약하기</span> </button> </div>
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
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.1.0/handlebars.min.js"></script>
	<script src="js/common.js"></script>
	<script>
		function ReservePrice(price, priceTypeName) {
			this.price = price.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
			this.label = getTypeLabel(priceTypeName);
		}
		
		//요금 설명문 정보 출력
		function initPriceDescription(){
			var priceDescriptionArea = document.querySelector('.store_details :nth-child(6)');
			
			var priceList = new Array();
			<c:forEach var="i" begin="0" end="${fn:length(reservePrice)-1}">
				priceList[${i}] = new ReservePrice('${reservePrice[i].price}','${reservePrice[i].priceTypeName}');
			</c:forEach> 
			
			var resultText = '';
			for(var i = 0; i < priceList.length; i++){
				if(i != 0 ){
					resultText += '\r\n';
				}
				resultText += priceList[i].label + ' ' + priceList[i].price+'원';
			}
			
			priceDescriptionArea.innerText = resultText;
			
			//티켓 영역의 타입 변경
			var ticketPriceAreas = document.querySelectorAll('.product_amount>span');
			ticketPriceAreas.forEach(item => item.innerText = getTypeLabel(item.innerText));
		}
		
		// 티켓 수량 증가, 감소 클릭 이벤트 등록 
		function setTicketClick(){
			var ticketContainer = document.querySelector('.ticket_body');
			var ticketTotalArea = document.querySelector('#totalCount');
			
			ticketContainer.addEventListener('click',function(evt){
				evt.preventDefault();
				
				var clickedTag = evt.target;
				if(clickedTag.tagName == 'A'){
					var btnMinus = clickedTag.parentElement.querySelector('.ico_minus3');
					var btnPlus = clickedTag.parentElement.querySelector('.ico_plus3');
					
					var amountArea = clickedTag.parentElement.querySelector('input');
					var amountValue = parseInt(amountArea.getAttribute('value'));
					
					var isMinus = clickedTag.classList.contains('ico_minus3') && amountValue > 0;
					var isPlus = clickedTag.classList.contains('ico_plus3');
					
					var item = clickedTag.parentElement.parentElement.parentElement;
					var cost = item.getAttribute('cost');
					
					if(isMinus){
						if(amountValue == 1){
							btnMinus.classList.add('disabled');
						}
							
						item.querySelector('.total_price').innerText = (cost * (amountValue - 1)).toLocaleString();
						amountArea.setAttribute('value',amountValue - 1);
						
						ticketTotalArea.innerText = parseInt(ticketTotalArea.innerText) - 1;
					}else if(isPlus){
						if(amountValue == 0){
							btnMinus.classList.remove('disabled');
						}
						
						item.querySelector('.total_price').innerText = (cost * (amountValue + 1)).toLocaleString();
						amountArea.setAttribute('value',amountValue + 1);
						
						ticketTotalArea.innerText = parseInt(ticketTotalArea.innerText) + 1;
					}
				}
			});
		}
		
		
		function setAgreementDesciptionClick(){
			//약관 보기 버튼
			var agreementDescriptionWrap = document.querySelector('.section_booking_agreement');
			agreementDescriptionWrap.addEventListener('click',function(evt){
			    var clickedBtn = evt.target;
			    
			    var isClickedChild = (clickedBtn.classList.contains('fn') || clickedBtn.classList.contains('btn_text'));
				if(isClickedChild){
					clickedBtn = clickedBtn.parentElement;
			    }

			    if(clickedBtn.classList.contains('btn_agreement')){
			    	evt.preventDefault();
			    	var btnTextArea = clickedBtn.querySelector('.btn_text');
			    	var btnArrow = clickedBtn.querySelector('i.fn');
			    	
			    	var isFold = btnArrow.classList.contains('fn-down2');
			    	
			    	if(isFold){
			    		btnTextArea.innerText = '접기';
			    		btnArrow.classList.add('fn-up2');
			    		btnArrow.classList.remove('fn-down2');
			    		
			    		clickedBtn.parentElement.classList.add('open');
			    	} else {
			    		btnTextArea.innerText = '보기';
			    		btnArrow.classList.add('fn-down2');
			    		btnArrow.classList.remove('fn-up2');
			    		
			    		clickedBtn.parentElement.classList.remove('open');
			    	}
			    }
			});
		}
		
		//예약하기 버튼 활성화 가능 여부 체크
		function setReserveClick(){
			var reserveBtn = document.querySelector('.bk_btn_wrap');
			
			var ticketInputsWrap = document.querySelector('.section_booking_ticket');
			var bookerInputsWrap = document.querySelector('.section_booking_form');
			
			var ticketInputs = ticketInputsWrap.querySelectorAll('.ticket_body input');
			var bookerInputs = bookerInputsWrap.querySelectorAll('.form_horizontal input');
			
			var agreementCheckBox = document.querySelector('#chk3');
			
			ticketInputsWrap.addEventListener('click',checkAvailableReserve);
			bookerInputsWrap.addEventListener('change',checkAvailableReserve);
			
			/**
			 * 예매하기 버튼을 클릭할 수 있는지 체크.
			 * 유효성 검증은 버튼 클릭시 하므로 버튼 활성/비활성화만 검증한다.
			 */
			function checkAvailableReserve(){
				var isAvailableBtn = true;
				
				if(agreementCheckBox.checked){
					//티켓을 하나라도 선택했는지 확인
					for(var i = 0 ; i < ticketInputs.length; i++){
						var targetInput = ticketInputs[i];
						if(targetInput.value > 0){
							isAvailableBtn = true;
							break;
						}else{
							isAvailableBtn = false;
						}
					}
					
					//예약자 정보가 빈 칸인지 확인
					for(var i = 0 ; i < bookerInputs.length; i++){
						var targetInput = bookerInputs[i];
						if(targetInput.value.length == 0){
							isAvailableBtn = false;
							break;
						}
					}
				}else{
					isAvailableBtn = false;
				}
				
				if(isAvailableBtn) {
					reserveBtn.classList.remove('disable');
					reserveBtn.addEventListener('click',onReserveClicked);
				} else {
					//예약하기 버튼 사용 불가
					reserveBtn.classList.add('disable');
					reserveBtn.removeEventListener('click',onReserveClicked);
				}
				
				return isAvailableBtn;
			}
		}
		
		//클릭 이벤트 등록을 전담
		function initClickEvents(){
			setTicketClick();
			setAgreementDesciptionClick();
			setReserveClick();
		}
		
		/**
		 * 예약하기 버튼을 클릭했을때 이벤트
		 * 예약자 정보를 검증하고 이상이 없다면 예약 페이지로 이동한다.
		 */
		function onReserveClicked(){
			var bookerInputs = document.querySelectorAll('.form_horizontal input');
			var warningAreas = document.querySelectorAll('.warning_msg');
			
			var bookerName = bookerInputs[0].value;
			var bookerTelephone = bookerInputs[1].value;
			var bookerEmail = bookerInputs[2].value;
			
			var telephoneRegExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
			var emailRegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
			
			// 틀린 영역에 표시하는데 사용할 boolean Array
			var validationArr = new Array(bookerInputs.length);
			for(var i = 0 ; i < validationArr.length; i++){
				validationArr[i] = true;
			}
			
			var isValidName = (bookerName.length > 1);
			var isValidTelephone = telephoneRegExp.test(bookerTelephone); 
			var isValidEmail = emailRegExp.test(bookerEmail);
			
			if(!isValidName){
				validationArr[0] = false;
			}
			
			if(!isValidTelephone){
				validationArr[1] = false;
			}
			
			if(!isValidEmail){
				validationArr[2] = false;
			}
			
			/**
			 * 전부 TRUE이면 예약 페이지로 이동
			 * 하나라도 FALSE이면 형식이 틀렸다는 메세지 출력
			 */
			var isValid = true;
			for(var i = 0 ; i < validationArr.length; i++){
				isValid = isValid && validationArr[i];
				
				if(!validationArr[i]){
					warningAreas[i].style.visibility = 'visible';
					(function(index){
					setTimeout(function(){
						warningAreas[index].style.visibility = 'hidden';
					},1000)})(i);
				}
			}
			
			if(isValid){
				// TODO: form submit으로 결과를 서버에 전송해야 한다.
				alert('OK');
			}
		}
		
		document.addEventListener('DOMContentLoaded', function() {
			var id = location.href.split('?')[1].split('=')[1];
			
			initPriceDescription();
			
			initClickEvents();
		});
	</script>
</body>



</html>
