<script type="rv-template" id="comment_item">
<li class="list_item">
	<div class="review_area">
		<div class="thumb_area img_vertical_top">
			{{#if saveFileName}}
			<img width="90" height="90" class="img_vertical_top" src="/{{saveFileName}}" alt="리뷰이미지">
			{{/if}}
		</div>
		<h4 class="resoc_name">${reviewPageInfo.description}</h4>
		<p class="review">{{comment}}</p>
	</div>
	<div class="info_area">
		<div class="review_info">
			<span class="grade">{{score}}</span>
			<span class="name">{{reservationEmail}}</span>
			<span class="date">{{reservationDate}} 방문</span>
		</div>
  	</div>
</li>
</script>
