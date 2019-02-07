function sendGetThumbnailsAjax(categoryId = 0) {
	var start = calcLoadedThumbnails();
	start="eee";
	sendGETAjax("./thumbnail_info?start="+ start + "&category_id=" + categoryId, setCounterAndMakeProductList);
}

function calcLoadedThumbnails() {
	var uls = document.getElementsByClassName("lst_event_box");
	return uls[0].childElementCount + uls[1].childElementCount;
}

function setCounterBar(count) {
	if (!parseInt(count)) {
		return false;
	}
	document.querySelector(".event_lst_txt > .pink").innerText = count + "개";
}

function makeThumbnailList(thumbnailInfos) {
	if (!thumbnailInfos) {
		return false;
	}
	var html = document.getElementById("product_item").innerHTML;

	var resultHTML = "";

	var uls = document.getElementsByClassName("lst_event_box");
	var index = uls[0].offsetHeight <= uls[1].offsetHeight ? 0 : 1;
	thumbnailInfos.forEach((thumbnailInfo) => {
	    resultHTML = html.replace("{id}", thumbnailInfo.id)
	    				.replace("{description}", thumbnailInfo.description)
	    				.replace("{description}", thumbnailInfo.description)
	    				.replace("{place_name}", thumbnailInfo.placeName)
	    				.replace("{content}", thumbnailInfo.content)
						.replace("{save_file_name}", thumbnailInfo.saveFileName);
		uls[(index++) & 1].innerHTML += resultHTML;
	});
}

function setCounterAndMakeProductList(thumbnailInfos) {
	if (!thumbnailInfos) {
		return false;
	}
	setCounterBar(thumbnailInfos.product_count);
	makeThumbnailList(thumbnailInfos.thumbnail_info_list);
}

function deleteAllThumbnail() {
	var uls = document.getElementsByClassName("lst_event_box");
	for (let ul of uls) {
		ul.innerHTML = "";
	}
}