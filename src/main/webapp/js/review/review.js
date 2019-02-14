/**
 * 리뷰페이지 메인로직 처리
 * @author 시윤
 *
 */

document.addEventListener("DOMContentLoaded", function() {
	sendGetCommentsAjax(0, 6);
	addMoreButtonClickEvent();
});
