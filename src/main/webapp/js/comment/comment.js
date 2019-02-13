function sendGetCommentsAjax(start = 0, limit = 3) {
	sendGetAjax("/product/" + displayInfo().productId + "/comment?start=" + start + "&limit" + limit, makeCommentList);
}

function makeCommentList(commentDisplayInfos) {
	if (!commentDisplayInfos) {
		return;
	}
	var bindTemplate = getBindTemplate("comment_item"); 
	var innerHtml = makeHtmlFromListData(commentDisplayInfos, bindTemplate);

	var ul = document.getElementsByClassName("list_short_review")[0];
	ul.innerHTML += innerHtml;
	
	if (endOfComment(displayInfo().commentCount)) {
		setMoreButtonVisibility("hidden");
	}
}

function getBindTemplate(templateId) {
	var template = document.getElementById(templateId).innerText;
	return Handlebars.compile(template); 
}

function makeHtmlFromListData(commentDisplayInfos, bindTemplate) {
	var html = ""
	commentDisplayInfos.forEach((commentDisplayInfo) => {
		html += bindTemplate(commentDisplayInfo);
	});
	return html;
}

function calcLoadedComments() {
	var ul = document.getElementsByClassName("list_short_review")[0];
	return ul.childElementCount;
}

function setMoreButtonVisibility(visibility) {
	var moreButton = document.querySelector(".more > .btn");
	if (!moreButton) {
		return;
	}
	moreButton.style.visibility = visibility;
}

function endOfComment(commentCount) {
	if (calcLoadedComments() == commentCount) {
		return true;
	} else {
		return false;
	}
}
