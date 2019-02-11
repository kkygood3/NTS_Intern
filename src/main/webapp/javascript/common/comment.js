function sendGetCommentsAjax(start = 0, limit = 3) {
	sendGetAjax("./comment_display_info?product_id=" + displayInfo.productId + "&start=" + start + "&limit" + limit, makeCommentList);
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
	ul.innerHTML += innerHtml;
	

	if(endOfProducts(displayInfo.commentCount)) {
		setMoreButtonVisibility("hidden");
	}
}

function calcLoadedComments() {
	var ul = document.getElementsByClassName("list_short_review")[0];
	return ul.childElementCount;
}


function setMoreButtonVisibility(visibility) {
	var moreButton = document.querySelector(".more > .btn");
	moreButton.style.visibility = visibility;
}


function endOfProducts(commentCount) {
	if (calcLoadedComments() == commentCount) {
		return true;
	} else {
		return false;
	}
}
