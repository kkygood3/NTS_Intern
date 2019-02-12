function displayInfoRequest(callBack, displayInfoId){
	
	var request = {
			method:"GET",
			contentType:"application/x-www-form-urlencoded",
			queryString:""
	}
	var requestUri = "/api/products/" + displayInfoId;
	sendRequest(request, requestUri, callBack);
}

function renderComments(displayInfo){
	var comments = getComments(displayInfo);
	comments.render();
}

document.addEventListener("DOMContentLoaded", function() {
	var displayInfoId = document.location.search.substr(4);
	displayInfoRequest(renderComments, displayInfoId);
});
