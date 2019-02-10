var displayInfoResponse = {
	displayInfo : {},
	displayInfoImage : {},
	productImages : [],

	loadDisplayInfoResponse : function(callback, displayInfoId) {
		if (!isNumber(displayInfoId)) {
			alert("잘못된 파라미터임니다333333333");
			window.history.back();
			return;
		}
		var url = "/api/products/" + displayInfoId;
		var xhr = getXMLHttpRequest(url);
		xhr.send();

		xhr.addEventListener("load", function(e) {
			var response = e.target.response;
			if (response.isError) {
				alert(response.errorMsg);
				return;
			}
			callback(response);
		});
	}
}

var commentResponse = {
	comments : [],
	totalCount : 0,
	averageScore : 0,

	loadCommentResponse : function(callback, productId, start, limit) {
		if (!isNumber(productId) || !isNumber(start)) {
			alert("잘못된 파라미터임니다");
			window.history.back();
			return;
		}
		var url = "/api/products/" + productId + "/comments?start=" + start + "&limit=" + limit;
		var xhr = getXMLHttpRequest(url);
		xhr.send();

		xhr.addEventListener("load", function(e) {
			var response = e.target.response;
			if (response.isError) {
				alert(response.errorMsg);
				return;
			}
			callback(response);
		});
	}
}
