function addButtonClickEvent() {
	var moreButton = document.querySelector(".more > .btn");
	moreButton.addEventListener("click", function(event){
		var categoryId = moreButton.value;
		sendGetThumbnailsAjax(categoryId);
		
	});
}

function addCategoryClickEvent() {
	var categoryButton = document.querySelector(".section_event_tab > .event_tab_lst");
	categoryButton.addEventListener("click", function(event){
		var categoryId = event.target.id.replace("category_", "");
		if (event.target.tagName != "SPAN") {
			return;
		}
		changeCategoryInfo(categoryId);
		changeTabUi();
		deleteAllThumbnail();
		sendGetThumbnailsAjax(categoryId);
	});
}

function changeTabUi() {
	
}

function changeCategoryInfo(categoryId) {
	var moreButton = document.querySelector(".more > .btn");
	moreButton.value = categoryId;
}