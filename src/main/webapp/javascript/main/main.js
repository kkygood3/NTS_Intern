var readyToSlide = false;


document.addEventListener("DOMContentLoaded", function() {
	sendGetPromotionsAjax();
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
//////////////////		product
//////////////////////////////////////////////////////////////////////////////////////////


function sendGetProductsAjax() {
	httpRequest = new XMLHttpRequest();
	
	if(!httpRequest) {
		return false;
	}
	httpRequest.open("GET", "./thumbnail_info");
	httpRequest.onreadystatechange = function getProducts() {
		if (httpRequest.readyState === XMLHttpRequest.DONE) {
			if (httpRequest.status === 200) {
			    var thumbnailInfos = JSON.parse(httpRequest.responseText);
			    makeProductList(thumbnailInfos);
			} else {
				return false;
			}
		}
	};
	httpRequest.send();
}

function makeProductList(thumbnailInfos) {
//	alert(JSON.stringify(products));
	thumbnailInfos = thumbnailInfos.thumbnail_info_list;
	var html = document.getElementById("product_item").innerHTML;

	var resultHTML = "";
	thumbnailInfos.forEach((thumbnailInfo) => {
//		alert(JSON.stringify(thumbnailInfo));
//		alert(JSON.stringify(thumbnailInfo.description));
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