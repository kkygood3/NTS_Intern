document.addEventListener("DOMContentLoaded", function() {
	sendGetCommentsAjax();
	sendGetEtcImageAjax();
	addInfoTabClickEvent();
	addMoreButtonClickEvent();
	setContentAreaMoreButtonDisplay();
	setCommentAreaMoreButtonDisplay();
});
