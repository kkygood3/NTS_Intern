var comment = {
	init : function(productId, start, limit) {
		this.loadCommentResponse(productId, start, limit);
	},

	loadCommentResponse : function(productId, start, limit) {
		if (!isNumber(productId) || !isNumber(start)) {
			alert("잘못된 파라미터임니다");
			window.history.back();
			return;
		}
		var url = "/api/products/" + productId + "/comments?start=" + start + "&limit=" + limit;
		ajax(this.initComment.bind(this), url);
	},

	initComment : function(response) {
		this.setCountDOM(response);
		this.setAvgScoreDOM(response);
		this.setCommentDOM(response);
	},
	// 상품평 총 개수 설정
	setCountDOM : function(response) {
		var countTextDiv = document.querySelector(".join_count .green");
		countTextDiv.innerText = response.totalCount + " 건";
	},
	// 상품평 평점 설정
	setAvgScoreDOM : function(response) {
		var scoreTextDiv = document.querySelector(".text_value span");
		scoreTextDiv.innerText = response.averageScore.toFixed(1);
		var maxScore = parseFloat(document.querySelector(".text_value .total").innerText);
		document.querySelector(".graph_value").style.width = response.averageScore
				/ maxScore * 100 + "%";
	},
	// 상품평 추가
	setCommentDOM : function(response) {
		var template = document.querySelector("#template-comment").innerText;
		var bindTemplate = Handlebars.compile(template);
		var resultHTML = response.comments.reduce(function(prev, comment) {
			return prev + bindTemplate(comment);
		}, "");
		var commentsDiv = document.querySelector(".list_short_review");
		commentsDiv.innerHTML = resultHTML;

		if (response.totalCount <= 3) {
			document.querySelector(".btn_review_more").style.display = "none";
		}
	},
}