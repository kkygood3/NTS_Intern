function setContentAreaMoreButtonDisplay() {
	var moreButton = document.getElementsByClassName("bk_more")[0];
	if (!exsitsMoreContents()) {
		hideElement(moreButton);
	}
}

function setCommentAreaMoreButtonDisplay() {
	var moreButton = document.getElementsByClassName("btn_review_more")[0];
	if (!exsitsMoreComments()) {
		hideElement(moreButton);
	}
}

function hideElement(element) {
	element.style.display = "none";
}

function exsitsMoreComments() {
	if (displayInfo().commentCount > 3) {
		return true;
	}
	return false;
}

function exsitsMoreContents() {
	var content = document.querySelector(".section_store_details .dsc");
	if (content.scrollHeight > content.clientHeight ) {
		return true;
	}
	return false;
}
