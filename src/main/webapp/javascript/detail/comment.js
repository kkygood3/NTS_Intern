function sendGetCommentsAjax() {
	var start = calcLoadedComments();
	sendGETAjax("./comment_display_info?start="+ start + "&product_id=" + productId, makeCommentList);
}

function calcLoadedComments() {
	// TODO: 구현한거아님 해야함
	return 0
}

function makeCommentList(commentDisplayInfos) {
	if (!commentDisplayInfos) {
		return;
	}
	var html = document.getElementById("comment_item").innerHTML;

	var resultHTML = "";
	var ul = document.getElementsByClassName("list_short_review")[0];
	
	commentDisplayInfos.forEach((commentDisplayInfo) => {
		alert(JSON.stringify(commentDisplayInfo));
	    resultHTML += html.replace("{comment}", commentDisplayInfo.comment)
		.replace("{score}", commentDisplayInfo.score)
		.replace("{reservation_email}", commentDisplayInfo.reservationEmail)
		.replace("{reservation_date}", commentDisplayInfo.reservationDate)
		.replace("{save_file_name}", commentDisplayInfo.saveFileName);
	});
	
	ul.innerHTML = resultHTML;
}

function endOfComments(productCount) {
}

function setMoreButtonVisibility(visibility) {
}
