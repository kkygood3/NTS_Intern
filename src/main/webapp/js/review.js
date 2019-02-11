

document.addEventListener("DOMContentLoaded", function() {
	var displayInfoId = document.location.search.substr(4);
	console.log(displayInfoId);
	displayInfoRequest(appendComments, displayInfoId);
});
