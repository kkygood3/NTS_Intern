function addMoreButtonClickEvent() {
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
		changeCategoryTabUi(categoryId);
		changeCategoryInfo(categoryId);
		deleteAllThumbnail();
		sendGetThumbnailsAjax(categoryId);
	});
}

function changeCategoryTabUi(curCategoryId) {
	if ((curCategoryId !== "0") && !parseInt(curCategoryId)) {
		return false;
	}
	var preCategoryId = document.querySelector(".more > .btn").value;
	document.getElementsByClassName("active")[0].className = "anchor";
	document.querySelectorAll(".event_tab_lst a.anchor")[curCategoryId].className = "anchor active";
	
}

function changeCategoryInfo(categoryId) {
	if ((categoryId !== "0") && !parseInt(categoryId)) {
		return false;
	}
	var moreButton = document.querySelector(".more > .btn");
	moreButton.value = categoryId;
}