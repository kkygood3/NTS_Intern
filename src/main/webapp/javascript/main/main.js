var readyToSlide = false;

document.addEventListener("DOMContentLoaded", function() {
	sendGetPromotionsAjax();
	sendGetThumbnailsAjax();
	sendGetCategorissAjax();
	makePromotionSlide();
	addButtonClickEvent();
	addCategoryClickEvent();
});
