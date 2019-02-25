<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<script type="rv-template" id="card">
<li class="card {{class}}" id="{{name}}">
	<div class="link_booking_details">
		<div class="card_header">
			<div class="left"></div>
			<div class="middle">
				<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
				<i class="spr_book2 {{ico}}"></i>
				<span class="tit">{{tit}}</span>
			</div>
			<div class="right"></div>
		</div>
	</div>
	<!-- content -->
</li>
</script>

<script type="rv-template" id="card_data">
[
	{
		"class" : "confirmed",
		"name" : "confirmed",
		"ico" : "ico_check2",
		"tit" : "예약 확정"
	},
	{
		"class" : "used",
		"name" : "used",
		"ico" : "ico_check2",
		"tit" : "이용 완료"},
	{
		"class" : "used cancel",
		"name" : "cancel",
		"ico" : "ico_cancel",
		"tit" : "취소된 예약"
	}
]
</script>

<script type="rv-template" id="card_item_confirmed">
<article class="card_item">
	<a href="#" class="link_booking_details">
		<div class="card_body">
			<div class="left"></div>
			<div class="middle">
				<div class="card_detail" id="product_id_{{productId}}">
					<em class="booking_number">No.{{reservationId}}</em>
					<h4 class="tit">{{description}}</h4>
					<ul class="detail">
						<li class="item">
							<span class="item_tit">일정</span>
							<em class="item_dsc">{{reservationDateFormat}}</em>
						</li>
						<li class="item">
							<span class="item_tit">장소</span>
							<em class="item_dsc">{{placeName}}</em>
						</li>
					</ul>
					<div class="price_summary">
						<span class="price_tit">결제 예정금액</span>
						<em class="price_amount">
							<span>{{price}}</span><span class="unit">원</span>
						</em>
					</div>
					<!-- [D] 예약 신청중, 예약 확정 만 취소가능, 취소 버튼 클릭 시 취소 팝업 활성화 -->
					<div class="booking_cancel"><button class="btn"><span>취소</span></button></div>
				</div>
			</div>
			<div class="right"></div>
		</div>
		<div class="card_footer">
			<div class="left"></div><div class="middle"></div><div class="right"></div>
		</div>
	</a>
	<a href="#" class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
</article>
</script>

<script type="rv-template" id="card_item_used">
<article class="card_item">
	<a href="#" class="link_booking_details">
		<div class="card_body">
			<div class="left"></div>
			<div class="middle">
				<div class="card_detail" id="product_id_{{productId}}">
					<em class="booking_number">No.{{reservationId}}</em>
					<h4 class="tit">{{description}}</h4>
					<ul class="detail">
						<li class="item">
							<span class="item_tit">일정</span>
							<em class="item_dsc">{{reservationDateFormat}}</em>
						</li>
						<li class="item">
							<span class="item_tit">장소</span>
							<em class="item_dsc">{{placeName}}</em>
						</li>
					</ul>
					<div class="price_summary">
						<span class="price_tit">결제 예정금액</span>
						<em class="price_amount">
							<span>{{price}}</span><span class="unit">원</span>
						</em>
					</div>
					<!-- [D] 예약 신청중, 예약 확정 만 취소가능, 취소 버튼 클릭 시 취소 팝업 활성화 -->
					<div class="booking_cancel">
						<a href="/detail/{{productId}}/comment"><button class="btn"><span>예매자 리뷰 남기기</span></button></a>
					</div>
				</div>
			</div>
			<div class="right"></div>
		</div>
		<div class="card_footer">
			<div class="left"></div><div class="middle"></div><div class="right"></div>
		</div>
	</a>
</article>
</script>

<script type="rv-template" id="card_item_cancel">
<article class="card_item">
	<a href="#" class="link_booking_details">
		<div class="card_body">
			<div class="left"></div>
			<div class="middle">
				<div class="card_detail" id="product_id_{{productId}}">
					<em class="booking_number">No.{{reservationId}}</em>
					<h4 class="tit">{{description}}</h4>
					<ul class="detail">
						<li class="item">
							<span class="item_tit">일정</span>
							<em class="item_dsc">{{reservationDateFormat}}</em>
						</li>
						<li class="item">
							<span class="item_tit">장소</span>
							<em class="item_dsc">{{placeName}}</em>
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

<script type="rv-template" id="card_item_err">
<!-- 예약 리스트 없음 -->
<div class="err"> <i class="spr_book ico_info_nolist"></i>
	<h1 class="tit">예약 리스트가 없습니다</h1>
</div>
<!--// 예약 리스트 없음 -->
</script>

<script type="rv-template" id="more_botton">
<!-- 더보기 -->
<div class="more">
	<button class="btn" value="0"><span>더보기</span></button>
</div>
</script>
