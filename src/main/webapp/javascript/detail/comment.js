function sendGetCommentsAjax() {
	var start = calcLoadedComments();
	sendGetAjax("./comment_display_info?start="+ start + "&product_id=" + displayInfo.productId, makeCommentList);
}

function calcLoadedComments() {
	// TODO: 구현한거아님 해야함
	return 0
}

function makeCommentList(commentDisplayInfos) {
	if (!commentDisplayInfos) {
		return;
	}
	var template = document.getElementById("comment_item").innerText;
	var bindTemplate = Handlebars.compile(template); 
	
	var innerHtml = "";
	commentDisplayInfos.forEach((commentDisplayInfo) => {
		innerHtml += bindTemplate(commentDisplayInfo);
	});

	var ul = document.getElementsByClassName("list_short_review")[0];
	ul.innerHTML = innerHtml;
}

function endOfComments(productCount) {
}

function setMoreButtonVisibility(visibility) {
}
