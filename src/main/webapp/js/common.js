document.addEventListener("DOMContentLoaded", function() {
	getCategories();
	getProductsByCategory();
	getPromotions();
});

let selectedCategoryId;
let start = 0;
let displayedProduct = 0;

let tab = document.querySelector(".section_event_tab").querySelector(".event_tab_lst");
tab.addEventListener("click", function(event){
	let anchorElement;
	let previousActive = document.querySelector(".active");
	let productContainer = document.querySelectorAll(".lst_event_box");
	
	if(event.target.className === "anchor"){
		anchorElement = event.target;
	} else if(event.target.className === "category_name"){
		anchorElement = event.target.parentNode;
	} else {
		return;
	}

	previousActive.className = "anchor";
	
	selectedCategoryId = anchorElement.parentNode.dataset.category;
	anchorElement.className += " active";
	
	productContainer.forEach(function(container){
		container.innerHTML = "";
	});
	
	start = 0;
	displayedProduct = 0;
	btnShowMore.hidden = false;
	
	getProductsByCategory(selectedCategoryId, start);
});

let btnShowMore = document.querySelector(".more");
btnShowMore.addEventListener("click", function(event){
	const productsPerPage = 4;
	start += productsPerPage;
	getProductsByCategory(selectedCategoryId, start);
});

function getCategories() {
	let httpRequest;
	
	if (window.XMLHttpRequest) {
		httpRequest =  new XMLHttpRequest();
		
		httpRequest.onreadystatechange = function() {  
			let jsonResponse;
			let categoryContainer = document.querySelector(".event_tab_lst");
			let categoryTemplate = document.querySelector("#categories").innerHTML;
			
			if (httpRequest.readyState === 4 && httpRequest.status === 200) {
				jsonResponse = JSON.parse(httpRequest.responseText);

				categoryContainer.innerHTML += categoryTemplate.replace("{id}", "")
															   .replace("{name}", "전체리스트")
															   .replace("anchor", "anchor active");
				
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

function getProductsByCategory(categoryId, start = 0) {
	let httpRequest;
	let url;
	
	if (window.XMLHttpRequest) {
		httpRequest =  new XMLHttpRequest();
		
		httpRequest.onreadystatechange = function() {
			let jsonResponse;
			let countProduct = document.querySelector(".event_lst_txt span");
			let productContainer = document.querySelectorAll(".lst_event_box");
			let containerIndex = 0;
			let productTemplate = document.querySelector("#itemList").innerHTML;
			
			if (httpRequest.readyState === 4 && httpRequest.status === 200) {
				jsonResponse = JSON.parse(httpRequest.responseText);

				countProduct.innerHTML = jsonResponse["totalCount"];
				displayedProduct += jsonResponse["items"].length;
				
				jsonResponse["items"].forEach(function(item){
					productContainer[containerIndex].innerHTML += productTemplate.replace("{id}", item.productId)
																				 .replace(/{description}/gi, item.productDescription)
																				 .replace("{placeName}", item.placeName)
																				 .replace("{content}", item.productContent)
																				 .replace("{imgUrl}", item.productImageUrl);
					
					containerIndex = (containerIndex + 1) % productContainer.length;
				})
				
				if(displayedProduct >= jsonResponse["totalCount"]){
					btnShowMore.hidden = true;
				}
			}
		}
		
		if(categoryId === null | categoryId === undefined){
			url = "./api/products?start=" + start;
		} else {
			url = "./api/products?categoryId=" + categoryId + "&start=" + start;
		}
		httpRequest.open("GET", url);
		httpRequest.setRequestHeader("Content-type", "charset=utf-8");
		httpRequest.send();
	}
}

function getPromotions() {
	let httpRequest;
	
	if (window.XMLHttpRequest) {
		httpRequest =  new XMLHttpRequest();

		httpRequest.onreadystatechange = function() {
			let jsonResponse;
			let promotionContainer = document.querySelector(".visual_img");
			let promotionTemplate = document.querySelector("#promotionItem").innerHTML;
			let executeAnimationTime;
			
			if (httpRequest.readyState === 4 && httpRequest.status === 200) {
				jsonResponse = JSON.parse(httpRequest.responseText);

				jsonResponse["items"].forEach(function(item){
					promotionContainer.innerHTML += promotionTemplate.replace("{imgUrl}", item.productImageUrl)
																	 .replace("{description}", item.productDescription)
																	 .replace("{placeName}", item.placeName)
																	 .replace("{content}", item.productContent);
				});

				executeAnimationTime = performance.now();
				slideImage(0, 1, 2, executeAnimationTime);
			}
		}

		httpRequest.open("GET", "./api/promotions");
		httpRequest.setRequestHeader("Content-type", "charset=utf-8");
		httpRequest.send();
	}
}

function slideImage(slideFirst, slideMiddle, slideLast, executeAnimationTime){
	let now = performance.now();
	let slides = document.querySelectorAll(".visual_img li");
	const promotionWidth = slides[slideFirst].clientWidth;
	const animationTime = 5000;

	slides[slideFirst].style.left = "0";
	slides[slideFirst].className = "item slide_first";
	slides[slideMiddle].style.left = promotionWidth + "px";
	slides[slideMiddle].className = "item slide_middle";
	slides[slideLast].style.left = promotionWidth * 2 + "px";
	slides[slideLast].className = "item slide_last";
	
	if(now - executeAnimationTime > animationTime) {
		slides[slideFirst].className = "item";
		slideFirst = slideMiddle;
		slideMiddle = slideLast;
		slideLast = (slideLast + 1) % slides.length;
		executeAnimationTime = performance.now();
	}
	
	requestAnimationFrame(function(){
		slideImage(slideFirst, slideMiddle, slideLast, executeAnimationTime);
	});
}