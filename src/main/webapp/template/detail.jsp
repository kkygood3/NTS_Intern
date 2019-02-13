<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="rv-template" id="visual_area">
<div class="pagination">
	<div class="bg_pagination"></div>
	<div class="figure_pagination">
		<span class="num">1</span>
		<span class="num off">/ <span>{image_length}</span></span>
	</div>
</div>
<div class="group_visual">
	<div class="container_visual" style="width: 414px;">
		<ul class="visual_img detail_swipe" style="left:0;">
			<li class="item" style="width: 414px;">
				<img alt="" class="img_thumb" src="/${datailPageInfo.mainImageFile}">
			</li>
			{{#if etcImage}}
			<li class="item" style="width: 414px;"><img alt="" class="img_thumb" src="/{{etcImage}}"></li>
			{{/if}}
		</ul>
	</div>
	{{#if etcImage}}
	<div class="prev">
		<div class="prev_inn">
			<a class="btn_prev" title="이전" onclick="PreviousButtonClickEvent()">
				<!-- [D] 첫 이미지 이면 off 클래스 추가 -->
				<i class="spr_book2 ico_arr6_lt off"></i>
			</a>
		</div>
	</div>
	<div class="nxt">
		<div class="nxt_inn">
			<a class="btn_nxt" title="다음" onclick="NextButtonClickEvent()">
				<i class="spr_book2 ico_arr6_rt"></i>
			</a>
		</div>
	</div>
	{{/if}}
</div>
</script>
