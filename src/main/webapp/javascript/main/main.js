var readyToSlide = false;
var curCategory = 0;

document.addEventListener("DOMContentLoaded", function() {
	sendGetPromotionsAjax();
	sendGetThumbnailsAjax();
	sendGetCategorissAjax();
	makePromotionSlide();
	addButtonClickEvent();
});
