<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="rv-template" id="ticket_item">
<div class="qty" id="product_price_id_{{productPriceId}}">
	<div class="count_control">
	<!-- [D] 수량이 최소 값이 일때 ico_minus3, count_control_input에 disabled 각각 추가, 수량이 최대 값일 때는 ico_plus3에 disabled 추가 -->
		<div class="clearfix">
			<a class="btn_plus_minus spr_book2 ico_minus3 disabled" title="빼기"></a>
			<input type="tel" class="count_control_input disabled" value="0" readonly title="수량">
			<a class="btn_plus_minus spr_book2 ico_plus3" title="더하기"></a>
		</div>
	<!-- [D] 금액이 0 이상이면 individual_price에 on_color 추가 -->
		<div class="individual_price"><span class="total_price">0</span><span class="price_type">원</span></div>
	</div>
	<div class="qty_info_icon">
		<strong class="product_amount"><span>{{priceTypeName}}</span></strong><strong class="product_price"><span class="price">{{price}}</span><span class="price_type">원</span></strong>
		{{#if discountRate}}<em class="product_dsc">{{orgPrice price discountRate}}원 ({{discountRate}}% 할인가)</em>{{/if}}
	</div>
</div>
</script>