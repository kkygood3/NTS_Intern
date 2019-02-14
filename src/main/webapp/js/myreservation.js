document.addEventListener("DOMContentLoaded", function() {
	getMyReservationPage();
});

function getMyReservationPage(){
	this.setEvent = new SetEvent();
	
	setEvent.scrollTop();
}

function SetEvent(){}

SetEvent.prototype.scrollTop = function(){
	document.querySelector(".lnk_top").addEventListener("click", function(){
		event.preventDefault();
		document.documentElement.scrollTop = 0;
	});
}