function addButtonClickEvent() {
	var moreButton = document.querySelector(".more > .btn");
	moreButton.addEventListener('click', function(event){
		sendGetThumbnailsAjax();
	});
}

function addCategoryClickEvent() {
	var Category = document.querySelector(".section_event_tab > .event_tab_lst");
	moreButton.addEventListener('click', function(event){
		deleteAllThumbnail();
		sendGetThumbnailsAjax();
	});
}
