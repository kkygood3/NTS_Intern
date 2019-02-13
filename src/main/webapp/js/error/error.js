document.addEventListener("DOMContentLoaded", function() {
	addGotoMainButtonClickEvent();
});

function addGotoMainButtonClickEvent() {
	var gotoMainButton = document.getElementsByClassName("goto_main")[0];
	gotoMainButton.addEventListener("click", function(event){
		location.href="/";
	});
}
