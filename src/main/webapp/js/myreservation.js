document.addEventListener("DOMContentLoaded", function() {
	getMyReservationPage();
});

function getMyReservationPage(){
	this.setEvent = new SetEvent();
	
	document.querySelector("#totalCnt").innerHTML = Number(document.querySelector("#avilableCnt").innerHTML) 
												+ Number(document.querySelector("#alreadyCnt").innerHTML)
												+ Number(document.querySelector("#canceldCnt").innerHTML);
	
	if(document.querySelector("#totalCnt").innerHTML !== "0"){
		document.querySelector(".err").style.display = "none";
		document.querySelector(".wrap_mylist").style.display = "block";
	} else {
		document.querySelector(".wrap_mylist").style.display = "none";
		document.querySelector(".err").style.display = "block";
	}
	
	setEvent.scrollTop();
}

function SetEvent(){}

SetEvent.prototype.scrollTop = function(){
	document.querySelector(".lnk_top").addEventListener("click", function(){
		event.preventDefault();
		document.documentElement.scrollTop = 0;
	});
}