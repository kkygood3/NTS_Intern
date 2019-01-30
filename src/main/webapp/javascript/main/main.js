var readyToSlide = false;
var curCategory = 0;
document.addEventListener("DOMContentLoaded", function() {
	sendGetPromotionsAjax();
	sendGetProductsAjax();
	makePromotionSlide();
	addButtonClickEvent();
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
function sendGetCategorissAjax() {
	var httpRequest = new XMLHttpRequest();
	
	if(!httpRequest) {
		return false;
	}
	httpRequest.open("GET", "./category");
	httpRequest.onreadystatechange = function getPromotions() {
		if (httpRequest.readyState === XMLHttpRequest.DONE) {
			if (httpRequest.status === 200) {
			    var categoris = JSON.parse(httpRequest.responseText);
			    makeCategoryBar(categoris);
			} else {
				return false;
			}
		} else {
			return false;
		}
	};
	httpRequest.send();
}

function makeCategoryBar(categoris) {
	
}
//////////////////////////////////////////////////////////////////////////////////////////
//////////////////		product
//////////////////////////////////////////////////////////////////////////////////////////


function sendGetProductsAjax() {

	var uls = document.getElementsByClassName("lst_event_box");
	var start = 0;
	start = uls[0].childElementCount + uls[1].childElementCount;
	
	httpRequest = new XMLHttpRequest();
	
	if(!httpRequest) {
		return false;
	}
	httpRequest.open("GET", "./thumbnail_info?start=" + start);
	httpRequest.onreadystatechange = function getProducts() {
		if (httpRequest.readyState === XMLHttpRequest.DONE) {
			if (httpRequest.status === 200) {
			    var thumbnailInfos = JSON.parse(httpRequest.responseText);
			    setCounterBar(thumbnailInfos.product_count)
			    makeProductList(thumbnailInfos.thumbnail_info_list);
			} else {
				return false;
			}
		}
	};
	httpRequest.send();
}

function setCounterBar(count) {
	document.querySelector(".event_lst_txt > .pink").innerText = count + "개";
}

function makeProductList(thumbnailInfos) {
	var html = document.getElementById("product_item").innerHTML;

	var resultHTML = "";
	thumbnailInfos.forEach((thumbnailInfo) => {
	    resultHTML = html.replace("{id}", thumbnailInfo.id)
	    				.replace("{description}", thumbnailInfo.description)
	    				.replace("{description}", thumbnailInfo.description)
	    				.replace("{place_name}", thumbnailInfo.placeName)
	    				.replace("{content}", thumbnailInfo.content)
						.replace("{save_file_name}", thumbnailInfo.saveFileName);
		var uls = document.getElementsByClassName("lst_event_box");
		var index = uls[0].offsetHeight <= uls[1].offsetHeight ? 0 : 1;
		uls[index].innerHTML += resultHTML;
	});
}

function addButtonClickEvent() {
	var moreButton = document.querySelector(".more > .btn");
	moreButton.addEventListener('click', function(event){
		sendGetProductsAjax();
	});
}

