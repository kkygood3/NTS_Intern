function sendGetThumbnailsAjax() {
	var start = calcLoadedThumbnails();
	sendGETAjax("./thumbnail_info?start=" + start, setCounterAndMakeProductList);
}

function calcLoadedThumbnails() {
	var uls = document.getElementsByClassName("lst_event_box");
	return uls[0].childElementCount + uls[1].childElementCount;
}

function setCounterBar(count) {
	document.querySelector(".event_lst_txt > .pink").innerText = count + "개";
}

function makeThumbnailList(thumbnailInfos) {
	var html = document.getElementById("product_item").innerHTML;

	var resultHTML = "";
	thumbnailInfos.forEach((thumbnailInfo) => {
	    resultHTML = html.replace("{id}", thumbnailInfo.id)
	    				.replace("{description}", thumbnailInfo.description)
	    				.replace("{description}", thumbnailInfo.description)
	    				.replace("{place_name}", thumbnailInfo.placeName)
	    				.replace("{content}", thumbnailInfo.content)
						.replace("{save_file_name}", thumbnailInfo.saveFileName);
		var uls = document.getElementsByClassName("lst_event_box");
		var index = uls[0].offsetHeight <= uls[1].offsetHeight ? 0 : 1;
		uls[index].innerHTML += resultHTML;
	});
}

function setCounterAndMakeProductList(thumbnailInfos) {
	setCounterBar(thumbnailInfos.product_count);
	makeThumbnailList(thumbnailInfos.thumbnail_info_list);
}

function deleteAllThumbnail() {
	var uls = document.getElementsByClassName("lst_event_box");
	uls.forEach((ul) => {
		ul.innerHTML = "";
	})
}