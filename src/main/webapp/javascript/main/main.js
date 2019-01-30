var readyToSlide = false;


document.addEventListener("DOMContentLoaded", function() {
	sendGetPromotionsAjax();
	sendGetCategorisAjax();
	sendGetProductsAjax();
	makePromotionSlide();
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
			    makePromotionSlideHTML(promotions);
			} else {
				return false;
			}
		} else {
			return false;
		}
	};
	httpRequest.send();
}

function makePromotionSlideHTML(promotions) {
//sava_file_name_list
	
	promotions = promotions.save_file_name_list;
	var html = document.getElementById("promotion_item").innerHTML;
		
		var resultHTML = "";
		promotions.forEach((promotion) => {
		    resultHTML += html.replace("{sava_file_name_list}", promotion.save_file_name);
		});

		var ul = document.getElementsByClassName("visual_img")[0];
		ul.innerHTML = resultHTML;
		ul.style.width = (100*promotions.length) + "%";
		readyToSlide = true;
}

function makePromotionSlide() {
	var ul = document.getElementsByClassName("visual_img")[0];
	var left = 0;
	const interval = window.setInterval(()=> {
		if (readyToSlide){
			if (left == -400) {
				left = 0;
				var firstLi = ul.firstElementChild;
				ul.appendChild(firstLi);
				ul.style.left = 0;
			} else {
				ul.style.left = --left + "px";
			}	// 수치는 나중에 다시 계산해야함
		}
	},1);
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
			    makeCategorySelectionBar(categoris);
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
//		var uls = document.getElementsByClassName("lst_event_box");
//		var index = uls[0].offsetHeight < uls[1].offsetHeight ? 0 : 1;
//		uls[index].innerHtml += resultHTML;
//	});

}