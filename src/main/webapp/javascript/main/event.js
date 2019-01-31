function addButtonClickEvent() {
	var moreButton = document.querySelector(".more > .btn");
	moreButton.addEventListener("click", function(event){
		sendGetThumbnailsAjax();
	});
}

function addCategoryClickEvent() {
	var categoryButton = document.querySelector(".section_event_tab > .event_tab_lst");
	categoryButton.addEventListener("click", function(event){
		var categoryId = event.target.id.replace("category_", "");
		if (event.target.tagName != "SPAN") {
			return;
		}
		deleteAllThumbnail();
		sendGetThumbnailsAjax(categoryId);
	});
}
