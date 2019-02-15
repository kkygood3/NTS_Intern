/**
 * 상품 썸네일 관련
 * @author 시윤
 *
 */

function sendGetThumbnailsAjax(categoryId = 0) {
	var start = calcLoadedThumbnails();
	sendGetAjax("./product?start="+ start + "&category_id=" + categoryId, setSectionEventLst);
}

function calcLoadedThumbnails() {
	var uls = document.getElementsByClassName("lst_event_box");
	return uls[0].childElementCount + uls[1].childElementCount;
}

/**
 * 썸네일 html 생성
 * @param thumbnailInfos
 * 		product_id 상품id
 *		display_info_id 화면 id
 *		description 상품 이름
 *		place_name 장소
 *		content 상품 설명
 *		save_file_name 썸네일 이미지
 */
function makeThumbnailList(thumbnailInfos) {
	if (!thumbnailInfos) {
		return false;
	}
	var html = document.getElementById("product_item").innerHTML;

	var resultHTML = "";
	var uls = document.getElementsByClassName("lst_event_box");
	var index = uls[0].offsetHeight <= uls[1].offsetHeight ? 0 : 1;
	thumbnailInfos.forEach((thumbnailInfo) => {
	    resultHTML = html.replace("{product_id}", thumbnailInfo.productId)
	    				.replace("{display_info_id}", thumbnailInfo.displayInfoId)
	    				.replace("{description}", thumbnailInfo.description)
	    				.replace("{description}", thumbnailInfo.description)
	    				.replace("{place_name}", thumbnailInfo.placeName)
	    				.replace("{content}", thumbnailInfo.content)
						.replace("{save_file_name}", thumbnailInfo.saveFileName);
		uls[(index++) & 1].innerHTML += resultHTML;
	});
}

function setSectionEventLst(thumbnailInfos) {
	if (!thumbnailInfos) {
		return false;
	}
	var productCount = thumbnailInfos.productCount;
	setCounterBar(productCount);
	makeThumbnailList(thumbnailInfos.thumbnailInfoList);
	if(endOfProducts(productCount)) {
		setMoreButtonVisibility("hidden");
	}
}

function setCounterBar(count) {
	if (!parseInt(count)) {
		return false;
	}
	document.querySelector(".event_lst_txt > .pink").innerText = count + "개";
}

function endOfProducts(productCount) {
	if (calcLoadedThumbnails() == productCount) {
		return true;
	} else {
		return false;
	}
}

function setMoreButtonVisibility(visibility) {
	var moreButton = document.querySelector(".more > .btn");
	moreButton.style.visibility = visibility;
}

function deleteAllThumbnail() {
	var uls = document.getElementsByClassName("lst_event_box");
	for (let ul of uls) {
		ul.innerHTML = "";
	}
}
