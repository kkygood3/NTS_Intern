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
                    <h2><span class="title"></span></h2>
                </div>
                <div class="group_visual">
                    <div class="container_visual" style="width: 414px;">
                        <ul class="visual_img">
                            <li class="item" style="width: 414px;"> <img alt="" class="img_thumb" src="https://ssl.phinf.net/naverbooking/20170217_264/1487312141947lTddT_JPEG/%B3%D7%C0%CC%B9%F6.jpg?type=ff1242_816"> <span class="img_bg"></span>
                                <div class="preview_txt">
                                    <h2 class="preview_txt_tit"></h2> <em class="preview_txt_dsc">₩12,000 ~ </em><em class="preview_txt_dsc"><span class='date_area'>2017.2.17.(금)~2017.4.18.(화)</span>, 잔여티켓 2769매</em> </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="section_store_details">
                    <div class="store_details">
                        <h3 class="in_tit"></h3>
                        <p class="dsc">
                            장소 : <br> 기간 : 2017.2.17.(금)~2017.4.18.(화)
                        </p>
                        <h3 class="in_tit">관람시간</h3>
                        <p class="dsc">
                            화, 목, 금 일요일 10:00am~06:00pm(입장마감 05:30pm)<br> ‘문화가 있는 날’ 매월 마지막 주 수요일은 오후 8시까지 연장
                        </p>
                        <h3 class="in_tit">요금</h3>
                        <p class="dsc">
                            성인(만 19~64세) 5,000원 / 청소년(만 13~18세) 4,000원<br> 어린이(만 4~12세) 3,000원 / 20인 이상 단체 20% 할인<br> 국가유공자, 장애인, 65세 이상 4,000원
                        </p>
                    </div>
                </div>
                <div class="section_booking_ticket">
                    <div class="ticket_body">
                    </div>
                </div>
                <div class="section_booking_form">
                    <div class="booking_form_wrap">
                        <div class="form_wrap">
                            <h3 class="out_tit">예매자 정보</h3>
                            <div class="agreement_nessasary help_txt"> <span class="spr_book ico_nessasary"></span> <span>필수입력</span> </div>
                            <form class="form_horizontal">
                                <div class="inline_form"> <label class="label" for="name"> <span class="spr_book ico_nessasary">필수</span> <span>예매자</span> </label>
                                    <div class="inline_control"> <input type="text" name="name" id="name" class="text" placeholder="네이버" maxlength="17"> </div>
                                </div>
                                <div class="inline_form"> <label class="label" for="tel"> <span class="spr_book ico_nessasary">필수</span> <span>연락처</span> </label>
                                    <div class="inline_control tel_wrap">
                                        <input type="tel" name="tel" id="tel" class="tel" value="" placeholder="휴대폰 입력 시 예매내역 문자발송">
                                        <div class="warning_msg">형식이 틀렸거나 너무 짧아요</div>
                                    </div>
                                </div>
                                <div class="inline_form"> <label class="label" for="email">  <span class="spr_book ico_nessasary">필수</span>  <span>이메일</span> </label>
                                    <div class="inline_control"> <input type="email" name="email" id="email" class="email" value="" placeholder="crong@codesquad.kr" maxlength="50"> </div>
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
	
	<script type="customTemplate" id="ticketItem">
		<div class="qty" cost="{{cost}}">
			<div class="count_control" ticket>
				<!-- [D] 수량이 최소 값이 일때 ico_minus3, count_control_input에 disabled 각각 추가, 수량이 최대 값일 때는 ico_plus3에 disabled 추가 -->
				<div class="clearfix">
					<a href="#" class="btn_plus_minus spr_book2 ico_minus3 disabled" title="빼기"> </a>
					<input type="tel" class="count_control_input disabled" value="0" readonly title="수량">
					<a href="#" class="btn_plus_minus spr_book2 ico_plus3" title="더하기"></a>
				</div>
				<!-- [D] 금액이 0 이상이면 individual_price에 on_color 추가 -->
				<div class="individual_price">
					<span class="total_price">0</span><span class="price_type">원</span>
				</div>
			</div>

			<div class="qty_info_icon">
				<strong class="product_amount"> <span>{{typeLabel}}</span> </strong> <strong class="product_price"> <span class="price">{{priceWithComma}}</span> <span class="price_type">원</span> </strong> {{#discount discountRate price}}{{discountRate}}{{/discount}}
			</div>
		</div>
	</script>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.1.0/handlebars.min.js"></script>
	<script src="js/common.js"></script>
	<script>
		var PRICE_TYPE ={
			    A : "성인",
			    Y : "청소년",
				B : "유아",
			    S : "셋트",
			    D : "장애인",
			    C : "지역주민",
			    E : "얼리버드",
			    V : "VIP",
			    D : "평일"
			}
		
		function getTypeLabel(priceTypeName){
			var priceType = PRICE_TYPE[priceTypeName];
			
			if(!priceType){
				priceType = priceTypeName + '석';
			}
			
			return priceType
		}
		
		function getPriceNotice(productPrices){
			var resultStr = '';
			var pricesLen = productPrices.length;
			for(var idx = 0 ; idx < pricesLen; idx++){
				if(idx != 0){
					resultStr += '<br>';
				}
				
				var curPrice = productPrices[idx];
				
				resultStr += getTypeLabel(curPrice.priceTypeName)+' '+curPrice.price.toLocaleString()+'원';
			}
			return resultStr;
		}
		
		function loadDisplayInfoCallback(responseData) {
			var displayInfoResponse = responseData.detailDisplay;
			
			var displayInfo = displayInfoResponse.displayInfo;
			var productImages = displayInfoResponse.productImages;
			var productPrices = displayInfoResponse.productPrices;
			
			//페이지 최상단 제목
			document.querySelector('span.title').innerText = displayInfo.productDescription;
			
			//섬네일 이미지
			document.querySelector("img.img_thumb").setAttribute('src',productImages[0].saveFileName);
			
			var minPrice = productPrices[0].price;
			var priceLen = productPrices.length;
			for(var i = 1; i < priceLen; i++){
				var curPrice = productPrices[i].price;
				if(curPrice < minPrice){
					minPrice = curPrice;
				}
			}
			
			//썸네일 최저가 표시
			document.querySelectorAll('em.preview_txt_dsc')[0].innerText = '₩'+minPrice.toLocaleString()+'~';
			
			//썸네일 날짜 표시
			//TODO: 어디서 꺼내야 할지 몰라서 보류
			//document.querySelector('span.date_area').innerText = displayInfo.
			
			var detailDescArea = document.querySelectorAll('.store_details>.dsc');
			
			//장소, 기간
			detailDescArea[0].innerHTML = '장소 : '+displayInfo.placeName;
			detailDescArea[0].innerHTML += '<br>기간 : '+'~기간~';
			
			//관람 시간
			detailDescArea[1].innerHTML = displayInfo.openingHours;
			//요금
			detailDescArea[2].innerHTML = getPriceNotice(productPrices);
			
			
			var ticketContainer = document.querySelector('.ticket_body');
			
			// ticket Template
			var ticketTemplate = document.querySelector('#ticketItem').innerText;
			var bindTicketTemplate = Handlebars.compile(ticketTemplate);
			
			Handlebars.registerHelper("discount", function (discountRate, price) {
			    if (discountRate > 0) {
			        return "<em class='product_dsc'>"+((price*(100-discountRate))/100).toFixed(0).toLocaleString()+"원 ("+discountRate+"% 할인가)</em>";
			    }else{
			    	return "";
			    }
			});
			
			for(var i = 0 ; i < 3 && i < productPrices.length; i++){
				var cost = productPrices[i].price;
				if(productPrices[i].discountRate > 0){
					cost = (cost*(100-productPrices[i].discountRate)/100).toFixed(0);
				}
				
				productPrices[i].priceWithComma = productPrices[i].price.toLocaleString(); 
				productPrices[i].typeLabel = getTypeLabel(productPrices[i].priceTypeName);
				productPrices[i].cost = cost;
				
				ticketContainer.innerHTML += bindTicketTemplate(productPrices[i]);
			}
			
			// ticket 가격 변동 Event 등록
			var totalCountArea = document.querySelector('#totalCount');
			
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
						
						totalCountArea.innerText = parseInt(totalCountArea.innerText) - 1;
					}else if(isPlus){
						if(amountValue == 0){
							btnMinus.classList.remove('disabled');
						}
						
						item.querySelector('.total_price').innerText = (cost * (amountValue + 1)).toLocaleString();
						amountArea.setAttribute('value',amountValue + 1);
						
						totalCountArea.innerText = parseInt(totalCountArea.innerText) + 1;
					}
				}
			});
			
			
			//약관 보기 버튼
			document.querySelector('.section_booking_agreement').addEventListener('click',function(evt){
				
			    var clickedBtn = evt.target;
				if(clickedBtn.classList.contains('fn-down2') || clickedBtn.classList.contains('btn_text')){
					clickedBtn = clickedBtn.parentElement;
			    }

			    if(clickedBtn.classList.contains('btn_agreement')){
			    	evt.preventDefault();
					clickedBtn.parentElement.classList.add('open');
			    }
			});
		}
		
		//예약하기 버튼 활성화 가능 여부 체크
		function initReserveBtn(){
			var reserveBtn = document.querySelector('.bk_btn_wrap');
			var inputs = document.querySelectorAll('.form_horizontal input');
			var agreementCheckBox = document.querySelector('#chk3');
			//예매하기 버튼을 클릭할 수 있는지 체크.
			//유효성 검증은 버튼 클릭시 하므로. 이 함수에서는 버튼 활성/비활성화만 검증한다.
			function checkAvailableReserve(){
				var isAvailableBtn = true;
				
				if(agreementCheckBox.checked){
					for(var i = 0 ; i < inputs.length; i++){
						var targetInput = inputs[i];
						if(targetInput.value.length == 0){
							isAvailableBtn = false;
							break;
						}
					}
				}else{
					isAvailableBtn = false;
				}
				
				function aa(){
					alert('aa');
				}
				
				if(isAvailableBtn) {
					//예약하기 버튼 사용 가능
					reserveBtn.classList.remove('disable');
					reserveBtn.addEventListener('click',aa);
				} else {
					//예약하기 버튼 사용 불가
					reserveBtn.classList.add('disable');
					reserveBtn.removeEventListener('click',aa);
				}
				
				return isAvailableBtn;
			}
			
			//input의 ParentElement인 form.section_booking_form을 찾고 change Event를 등록한다
			//onchange시 예약가능한지 체크하는 함수를 호출한다.
			document.querySelector('.section_booking_form').addEventListener('change',checkAvailableReserve);
		}
		
		document.addEventListener('DOMContentLoaded', function() {
			// 페이지 첫 로딩시 할 일
			var id = location.href.split('?')[1].split('=')[1];
			
			requestAjax(loadDisplayInfoCallback, 'api/products/' + id);
			
			initReserveBtn();
		});
	</script>
</body>



</html>
