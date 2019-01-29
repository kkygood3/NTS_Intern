document.addEventListener("DOMContentLoaded", function() {
	getCategories();
	getPromotions();
	getProductsByCategory(0);
});

function getCategories() {
	if (window.XMLHttpRequest) {
		var httpRequest =  new XMLHttpRequest();
		
		httpRequest.onreadystatechange = function() {  
			if (httpRequest.readyState === 4 && httpRequest.status === 200) {
				var jsonResponse = JSON.parse(httpRequest.responseText);

				var categoryContainer = document.querySelector(".event_tab_lst");
				var categoryTemplate = document.querySelector("#categories").innerHTML;
				
				jsonResponse["items"].forEach(function(item){
					categoryContainer.innerHTML += categoryTemplate.replace("{id}", item.id)
																   .replace("{name}", item.name);
				});
			}
		}
		
		httpRequest.open("GET", "./api/categories");
		httpRequest.setRequestHeader("Content-type", "charset=utf-8");
		httpRequest.send();
	}
}

let productContainer = document.querySelectorAll(".lst_event_box");
let countProduct = document.querySelector(".event_lst_txt span");
let showMoreButton = document.querySelector(".more");
let selectedCategoryId = 0;
let start = 0;
let displayedProduct = 0;

let tab = document.querySelector(".section_event_tab").querySelector("ul");
tab.addEventListener("click", function(event){
	var anchorElement;
	if(event.target.tagName === "A"){
		anchorElement = event.target;
	} else if(event.target.tagName === "SPAN"){
		anchorElement = event.target.parentNode;
	}
	
	var previousActive = anchorElement.parentNode.parentNode.querySelector("[data-category='"+selectedCategoryId+"']").firstElementChild;
	previousActive.className = "anchor";
	
	selectedCategoryId = anchorElement.parentNode.dataset.category;
	anchorElement.className += " active";
	
	productContainer[0].innerHTML = "";
	productContainer[1].innerHTML = "";
	
	start = 0;
	displayedProduct = 0;
	showMoreButton.hidden = false;
	
	getProductsByCategory(selectedCategoryId, start);
});

function getProductsByCategory(categoryId, start = 0) {
	if (window.XMLHttpRequest) {
		var httpRequest =  new XMLHttpRequest();
		
		httpRequest.onreadystatechange = function() {  
			if (httpRequest.readyState === 4 && httpRequest.status === 200) {
				var jsonResponse = JSON.parse(httpRequest.responseText);

				countProduct.innerHTML = jsonResponse["totalCount"];
				displayedProduct += jsonResponse["items"].length;
				
				var productTemplate = document.querySelector("#itemList").innerHTML;
				
				let containerIndex = 0;
				jsonResponse["items"].forEach(function(item){
					productContainer[containerIndex].innerHTML += productTemplate.replace("{id}", item.productId)
															  .replace("{description}", item.productDescription)
															  .replace("{placeName}", item.placeName)
															  .replace("{content}", item.productContent)
															  .replace("{imgUrl}", item.productImageUrl);
					
					containerIndex = (containerIndex + 1) % 2;
				})
				
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
	getProductsByCategory(selectedCategoryId, start);
});

let executeAnimationTime;
function getPromotions() {
	if (window.XMLHttpRequest) {
		var httpRequest =  new XMLHttpRequest();

		httpRequest.onreadystatechange = function() {  
			if (httpRequest.readyState === 4 && httpRequest.status === 200) {
				var jsonResponse = JSON.parse(httpRequest.responseText);

				var promotionTemplate = document.querySelector("#promotionItem").innerHTML;

				jsonResponse["items"].forEach(function(item){
					var promotionContainer = document.querySelector(".visual_img");
					promotionContainer.innerHTML += promotionTemplate.replace("{imgUrl}", item.productImageUrl)
																	 .replace("{description}", item.productDescription)
																	 .replace("{placeName}", item.placeName)
																	 .replace("{content}", item.productContent);
				});

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