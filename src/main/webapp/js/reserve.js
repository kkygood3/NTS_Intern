document.addEventListener("DOMContentLoaded", function() {
	bookingPage.getBookingPage(bookingPage.displayInfoId);
});

var bookingPage = {
	getBookingPage: function(displayInfoId){
		let httpRequest;
		
		this.compileHendlebars.convertTypeName();
		
		if (window.XMLHttpRequest) {
			httpRequest =  new XMLHttpRequest();
			
			httpRequest.onreadystatechange = function() {
				let jsonResponse;
				
				if (httpRequest.readyState === 4 && httpRequest.status === 200) {
					jsonResponse = JSON.parse(httpRequest.responseText);
					
					this.renderDisplayInfo(jsonResponse);
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
	
	container: {
		displayInfoContainer : document.querySelector(".store_details")
	},
	
	template: {
		displayInfoTemplate : document.querySelector("#diplayInfoTemplate").innerHTML
	},

	compileHendlebars: {
		bindTemplate : function(template){
			return Handlebars.compile(template);
		},
		
		priceType : {
			A : "성인",
			Y : "청소년",
			B : "유아",
			D : "장애인",
			C : "지역주민",
			E : "어얼리버드",
			V : "VIP",
			R : "R석",
			S : "S석"
		},
		
		convertTypeName: function(){
			Handlebars.registerHelper('convertTypeName', function(typeName) {
				return this.priceType[typeName];
			}.bind(this));
		}
	},
	
	renderDisplayInfo: function(jsonResponse){
		var bindDisplayInfo = this.compileHendlebars.bindTemplate(this.template.displayInfoTemplate);
		
		this.container.displayInfoContainer.innerHTML = bindDisplayInfo(jsonResponse);
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