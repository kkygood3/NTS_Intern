/**
 * 사용자 코멘트 처리
 * @author 시윤
 *
 */

function sendGetCommentsAjax(start = 0, limit = 3) {
	sendGetAjax("/product/" + displayInfo().productId + "/comment?start=" + start + "&limit=" + limit, makeCommentList);
}

/**
 * 사용자 코멘트 handlebar로 html 생성
 * @param commentDisplayInfos사용자 코멘트 데이터
 * comment 사용자 코멘트
 * score 별점
 * reservationEmail 사용자
 * reservationDate 방문일
 * saveFileName 이미지 이름
 */
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
