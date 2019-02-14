<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="rv-template" id="visual_area">
<ul class="visual_img">
	<li class="item" style="width: 414px;"> <img alt="" class="img_thumb" src="https://ssl.phinf.net/naverbooking/20170217_264/1487312141947lTddT_JPEG/%B3%D7%C0%CC%B9%F6.jpg?type=ff1242_816"> <span class="img_bg"></span>
		<div class="preview_txt">
			<h2 class="preview_txt_tit"></h2>
			<em class="preview_txt_dsc">₩12,000 ~ </em>
			<em class="preview_txt_dsc">2017.2.17.(금)~2017.4.18.(화), 잔여티켓 2769매</em>
		</div>
	</li>
</ul>
</script>

<script type="rv-template" id="visual_area">
<div class="store_details">
	<h3 class="in_tit"></h3>
	<p class="dsc">장소 : <br> 기간 : 2017.2.17.(금)~2017.4.18.(화)</p>
	<h3 class="in_tit">관람시간</h3>
	<p class="dsc">화, 목, 금 일요일 10:00am~06:00pm(입장마감 05:30pm)<br> ‘문화가 있는 날’ 매월 마지막 주 수요일은 오후 8시까지 연장</p>
	<h3 class="in_tit">요금</h3>
	<p class="dsc">성인(만 19~64세) 5,000원 / 청소년(만 13~18세) 4,000원<br> 어린이(만 4~12세) 3,000원 / 20인 이상 단체 20% 할인<br> 국가유공자, 장애인, 65세 이상 4,000원</p>
</div>
</script>

<script type="rv-template" id="visual_area">
<div class="qty">
	<div class="count_control">
	<!-- [D] 수량이 최소 값이 일때 ico_minus3, count_control_input에 disabled 각각 추가, 수량이 최대 값일 때는 ico_plus3에 disabled 추가 -->
		<div class="clearfix">
			<a href="#" class="btn_plus_minus spr_book2 ico_minus3 disabled" title="빼기"></a>
			<input type="tel" class="count_control_input disabled" value="0" readonly title="수량">
			<a href="#" class="btn_plus_minus spr_book2 ico_plus3" title="더하기"></a>
		</div>
	<!-- [D] 금액이 0 이상이면 individual_price에 on_color 추가 -->
		<div class="individual_price"><span class="total_price">0</span><span class="price_type">원</span></div>
	</div>
	<div class="qty_info_icon">
		<strong class="product_amount"><span>성인</span></strong><strong class="product_price"><span class="price">10,200</span><span class="price_type">원</span></strong><em class="product_dsc">10,200원 (15% 할인가)</em>
	</div>
</div>
</script>

<script type="rv-template" id="visual_area">
<div class="inline_form">
	<label class="label" for="name"> <span class="spr_book ico_nessasary">필수</span> <span>예매자</span> </label>
	<div class="inline_control"> <input type="text" name="name" id="name" class="text" placeholder="네이버" maxlength="17"> </div>
</div>
<div class="inline_form">
	<label class="label" for="tel"> <span class="spr_book ico_nessasary">필수</span> <span>연락처</span> </label>
	<div class="inline_control tel_wrap">
		<input type="tel" name="tel" id="tel" class="tel" value="" placeholder="휴대폰 입력 시 예매내역 문자발송">
		<div class="warning_msg">형식이 틀렸거나 너무 짧아요</div>
	</div>
</div>
<div class="inline_form">
	<label class="label" for="email"><span class="spr_book ico_nessasary">필수</span><span>이메일</span></label>
	<div class="inline_control"><input type="email" name="email" id="email" class="email" value="" placeholder="crong@codesquad.kr" maxlength="50"></div>
</div>
</script>