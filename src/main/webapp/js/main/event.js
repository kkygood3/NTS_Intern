/**
 * main 페이지 이벤트 처리
 * @author 시윤
 *
 */

/**
 * 썸네일 더보기 버튼 클릭이벤트
 * 4개씩 더받아온다
 */
function addMoreButtonClickEvent() {
	var moreButton = document.querySelector(".more > .btn");
	moreButton.addEventListener("click", function(event){
		var categoryId = moreButton.value;
		sendGetThumbnailsAjax(categoryId);
	});
}

/**
 * 카테고리 탭메뉴 클릭이벤트
 */
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
		setMoreButtonVisibility("visible");
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

/**
 * 썸네일 클릭이벤트
 * 상세페이지로 이동한다
 */
function addThumbnailClickEvent() {
	var ul = document.getElementsByClassName("wrap_event_box")[0];
	
	ul.addEventListener("click", function(event) {
		if (!event.target.closest("li")) {
			return;
		}
		const thumbnail = event.target.closest("li");
		window.location.href = "/product/" + getProductId(thumbnail) + "/display/" + getDisplayInfoId(thumbnail);
	});
}

function getDisplayInfoId(thumbnail) {
	return thumbnail.id.split("_")[2];
}

function getProductId(thumbnail) {
	return thumbnail.id.split("_")[1];
}
