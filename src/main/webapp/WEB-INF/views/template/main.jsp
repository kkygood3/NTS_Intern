<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="rv-template" id="promotion_item">
<li class="item" style="background-image: url(./{sava_file_name_list});"></li>
</script>

<script type="rv-template" id="product_item">
<li class="item" id="thumb_{product_id}_{display_info_id}">
	<a class="anchor">
		<div class="item_preview">
			<img alt="{description}" class="img_thumb" src="./{save_file_name}">
		</div>
		<div class="event_txt">
			<h4 class="event_txt_tit"> <span>{description}</span> <small class="sm">{place_name}</small> </h4>
			<p class="event_txt_dsc">{content}</p>
		</div>
	</a>
</li>
</script>
    
<script type="rv-template" id="category_item">
<li class="item">
	<a class="anchor"><span id="category_{id}">{name}</span><a>
</li>
</script>
