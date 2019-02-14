document.addEventListener("DOMContentLoaded", function() {
	bookingPage.getBookingPage(bookingPage.displayInfoId);
});

var bookingPage = {
	getBookingPage: function(displayInfoId){
		let httpRequest;
		
		if (window.XMLHttpRequest) {
			httpRequest =  new XMLHttpRequest();
			
			httpRequest.onreadystatechange = function() {
				let jsonResponse;
				
				if (httpRequest.readyState === 4 && httpRequest.status === 200) {
					jsonResponse = JSON.parse(httpRequest.responseText);
					
				}
			}.bind(this)
			
			httpRequest.open("GET", "../../api/products/" + displayInfoId);
			httpRequest.setRequestHeader("Content-type", "charset=utf-8");
			httpRequest.send();
		}
		
		this.goToPrevPage();
		this.scrollTop();
	},
	
	displayInfoId : window.location.href.match(/detail\/\d+/)[0].split("/")[1],
	
	elements: {
		btnBack : document.querySelector(".btn_back"),
		btnTop : document.querySelector(".lnk_top")
	},
	
	goToPrevPage: function(){
		this.elements.btnBack.addEventListener("click", function(){
			event.preventDefault();
			history.back();
		});
	},
	
	scrollTop: function(){
		this.elements.btnTop.addEventListener("click", function(){
			event.preventDefault();
			document.documentElement.scrollTop = 0;
		});
	}
}