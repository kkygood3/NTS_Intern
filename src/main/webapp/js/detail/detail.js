
/**
 * @description : displayInfo ajax 요청
 */
function displayInfoRequest(callBack, displayInfoId){
	
	var request = {
			method:"GET",
			contentType:"application/x-www-form-urlencoded",
			queryString:""
	}
	var requestUri = "/api/products/" + displayInfoId;
	sendRequest(request, requestUri, callBack);
}

/**
 * @description : detail page initialization
 */
function detailsInit(displayInfo){
	var displayInfoObject = displayInfoToObj(displayInfo, 3);
	displayInfoObject.init();
	displayInfoObject.render();
}

/**
 * @description : mapper로부터 각 속성들을 구한 후 object 생성
 */
function displayInfoToObj(displayInfo, maxCommentNum){
	
	var displayInfoObject = {
			init:function(){
				this.contents = getContents(displayInfo);
				this.productImages = getProductImages(displayInfo);
				this.comments = getComments(displayInfo, maxCommentNum);
				this.detailInfo = getDetailInfo(displayInfo);
				this.path = getPath(displayInfo);
			},
			render:function(){
				this.contents.init();
				this.productImages.init();
				this.comments.init();
				this.detailInfo.init();
				this.path.init();
			}
	}
	
	return displayInfoObject;
}

document.addEventListener("DOMContentLoaded", function() {
	var displayInfoId = document.location.search.substr(4);
	displayInfoRequest(detailsInit, displayInfoId);
});