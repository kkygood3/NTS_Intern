function sendGetCommentsAjax() {
	var start = calcLoadedComments();
	sendGETAjax("./comment_display_info?start="+ start + "&product_id=" + categoryId, setSectionEventLst);
}

function calcLoadedComments() {
	// TODO: 구현한거아님 해야함
	return ul.childElementCount + uls[1].childElementCount;
}

function makeCommentList(thumbnailInfos) {
}

function endOfComments(productCount) {
}

function setMoreButtonVisibility(visibility) {
}
