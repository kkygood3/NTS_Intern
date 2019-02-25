/**
 * 메인페이지 메인로직
 * @author 시윤
 *
 */

var readyToSlide = false;

document.addEventListener("DOMContentLoaded", function() {
	sendGetPromotionsAjax();
	sendGetThumbnailsAjax();
	sendGetCategorissAjax();
	makePromotionSlide();
	addMoreButtonClickEvent();
	addCategoryClickEvent();
	addThumbnailClickEvent();
});
