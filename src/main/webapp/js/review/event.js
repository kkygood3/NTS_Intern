function addMoreButtonClickEvent() {
	var moreButton = document.querySelector(".more > .btn");
	moreButton.addEventListener("click", function(event){
		sendGetCommentsAjax(calcLoadedComments());
	});
}

