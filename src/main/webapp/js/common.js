document.addEventListener("DOMContentLoaded", function() {
	getPromotion();
	getProductsByCategory(0);
});

// 탭 메뉴 : TODO 이벤트위임 방식으로 수정
var productContainer = document.querySelectorAll(".lst_event_box");
var countProduct = document.querySelector(".event_lst_txt span");
var showMoreButton = document.querySelector(".more");
let nowCategoryId = 0;
let start = 0;
let displayedProduct = 0;

var tab = document.querySelector(".section_event_tab").querySelectorAll(".anchor");
for(var i = 0; i < tab.length; i++){
	tab[i].addEventListener("click", function(){
		var nowActive = this.parentNode.parentNode.querySelector("[data-category='"+nowCategoryId+"']").firstElementChild;
		nowActive.className = "anchor";
		this.className += " active";
		nowCategoryId = this.parentNode.dataset.category;
		productContainer[0].innerHTML = "";
		productContainer[1].innerHTML = "";
		start = 0;
		displayedProduct = 0;
		showMoreButton.hidden = false;
		getProductsByCategory(nowCategoryId, start);
	});
}

function getProductsByCategory(categoryId, start = 0) {
	
	if (window.XMLHttpRequest) {
		var httpRequest =  new XMLHttpRequest();
		
		httpRequest.onreadystatechange = function() {  
			if (httpRequest.readyState === 4 && httpRequest.status === 200) {
				var jsonResponse = JSON.parse(httpRequest.responseText);

				countProduct.innerHTML = jsonResponse["totalCount"];
				displayedProduct += jsonResponse["items"].length;
				
				var productTemplate = document.querySelector("#itemList").textContent;
				
				for(var i = 0, len = jsonResponse["items"].length; i < len; i++){
					var id = jsonResponse["items"][i]["productId"];
					var description = jsonResponse["items"][i]["productDescription"];
					var placeName = jsonResponse["items"][i]["placeName"];
					var content = jsonResponse["items"][i]["productContent"];
					var imgUrl = jsonResponse["items"][i]["productImageUrl"];
					
					var targetElement = productContainer[i % 2];
					targetElement.innerHTML += eval("`"+productTemplate+"`");
				}
				
				if(displayedProduct >= jsonResponse["totalCount"]){
					showMoreButton.hidden = true;
				}
			}
		}
		
		var url = "./api/products?categoryId=" + categoryId + "&start=" + start;
		httpRequest.open("GET", url);
		httpRequest.setRequestHeader("Content-type", "charset=utf-8");
		httpRequest.send();
	}
}

const productsPerPage = 4;

showMoreButton.addEventListener("click", function(event){
	start += productsPerPage;
	getProductsByCategory(nowCategoryId, start);
});

let executeAnimationTime;
function getPromotion() {
	if (window.XMLHttpRequest) {
		var httpRequest =  new XMLHttpRequest();

		httpRequest.onreadystatechange = function() {  
			if (httpRequest.readyState === 4 && httpRequest.status === 200) {
				var jsonResponse = JSON.parse(httpRequest.responseText);

				var promotionTemplate = document.querySelector("#promotionItem").textContent;

				for(var i = 0, len = jsonResponse["items"].length; i < len; i++){
					var imgUrl = jsonResponse["items"][i]["productImageUrl"];
					var description = jsonResponse["items"][i]["productDescription"];
					var placeName = jsonResponse["items"][i]["placeName"];
					var content = jsonResponse["items"][i]["productContent"];

					var promotionContainer = document.querySelector(".visual_img");
					promotionContainer.innerHTML += eval("`"+promotionTemplate+"`");
				}
				executeAnimationTime = performance.now();
				slideImage();
			}
		}

		httpRequest.open("GET", "./api/promotions");
		httpRequest.setRequestHeader("Content-type", "charset=utf-8");
		httpRequest.send();
	}
}

let slideFirst = 0;
let slideMiddle = 1;
let slideLast = 2;
const animationTime = 5000;
function slideImage(){
	var now = performance.now();
	var slides = document.querySelectorAll(".visual_img li");
	const promotionWidth = slides[slideFirst].clientWidth;

	slides[slideFirst].style.left = "0";
	slides[slideFirst].className = "item slide_first";
	slides[slideMiddle].style.left = promotionWidth + "px";
	slides[slideMiddle].className = "item slide_middle";
	slides[slideLast].style.left = promotionWidth * 2 + "px";
	slides[slideLast].className = "item slide_last";
	
	if(now - executeAnimationTime > animationTime) {
		slides[slideFirst].className = "item waiting";
		slideFirst = slideMiddle;
		slideMiddle = slideLast;
		slideLast = (slideLast + 1) % slides.length;
		executeAnimationTime = performance.now();
	}
	
	requestAnimationFrame(slideImage);
}