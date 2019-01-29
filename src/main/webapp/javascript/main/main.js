document.addEventListener("DOMContentLoaded", function() {
	sendGetPromotionsAjax();
	sendGetCategorisAjax();
	sendGetProductsAjax();
});

//////////////////////////////////////////////////////////////////////////////////////////
//////////////////		promotion
//////////////////////////////////////////////////////////////////////////////////////////
function sendGetPromotionsAjax() {
	var httpRequest = new XMLHttpRequest();
	
	if(!httpRequest) {
		return false;
	}
	httpRequest.open("GET", "./promotion");
	httpRequest.onreadystatechange = function getPromotions() {
		if (httpRequest.readyState === XMLHttpRequest.DONE) {
			if (httpRequest.status === 200) {
		//	    alert(httpRequest.responseText);
			    var promotions = JSON.parse(httpRequest.responseText);
			    makePromotionSlide(promotions);
			} else {
				return false;
			}
		} else {
			return false;
		}
	};
	httpRequest.send();
}

function makePromotionSlide(promotions) {
	promotions;
	// make slide
}


//////////////////////////////////////////////////////////////////////////////////////////
//////////////////		category
//////////////////////////////////////////////////////////////////////////////////////////


function sendGetCategorisAjax() {
	var httpRequest = new XMLHttpRequest();
	
	if(!httpRequest) {
		return false;
	}
	httpRequest.open("GET", "./category");
	httpRequest.onreadystatechange = function getCategoris() {
		if (httpRequest.readyState === XMLHttpRequest.DONE) {
			if (httpRequest.status === 200) {
		//	    alert(httpRequest.responseText);
			    var categoris = JSON.parse(httpRequest.responseText);
			    makeCategorySelectionBar(promotions);
			} else {
				return false;
			}
		} else {
			return false;
		}
	};
	httpRequest.send();
}

function makeCategorySelectionBar(categoris) {
	// make slide
}


//////////////////////////////////////////////////////////////////////////////////////////
//////////////////		product
//////////////////////////////////////////////////////////////////////////////////////////


function sendGetProductsAjax() {
	httpRequest = new XMLHttpRequest();
	
	if(!httpRequest) {
		return false;
	}
	httpRequest.open("GET", "./product");
	httpRequest.onreadystatechange = function getProducts() {

		if (httpRequest.readyState === XMLHttpRequest.DONE) {
			if (httpRequest.status === 200) {
	//		    alert(httpRequest.responseText);
			    var products = JSON.parse(httpRequest.responseText);
			    makeProductList(products);
			} else {
				return false;
			}
		}
	};
	httpRequest.send();
}



function makeProductList(products) {
//	var html = document.querySelector("product_item");
//
//	var resultHTML = "";
//	products.forEach((product) => {
//	    resultHTML = html.replace("{id}", product.id)
//	    				.replace("{description}", product.description)
//	    				.replace("{place_name}", product.place_name)
//	    				.replace("{content}", product.content);
//
//		var uls = document.querySelectorAll(".lst_event_box");
//		var index = uls[0].offsetHeight < uls[1].offsetHeight ? 0 : 1;
//		uls[index].innerHtml += resultHTML;
//	});

}