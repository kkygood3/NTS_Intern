function loadContent(url, method, loadFunction) {
	var xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.addEventListener("load", loadFunction);
	xmlHttpRequest.open(method, url);
	xmlHttpRequest.send();
}

function printPromotions() {
	var promotionList = document.querySelector("#promotion_list");

	var promotionItems = JSON.parse(this.response).item;
	for (var i = 0, len = promotionItems.length; i < len; i++) {
		promotionList.innerHTML += parsePromotionToHtml(promotionItems[i]);
	}

}

function printCategories() {
	var categoryList = document.querySelector("#category_list");

	var categoryItems = JSON.parse(this.response).item;
	for (var i = 0, len = categoryItems.length; i < len; i++) {
		categoryList.innerHTML += parseCategoryToHtml(categoryItems[i]);
	}
}

function printProducts() {
	var productLists = document.querySelectorAll(".lst_event_box");
	var productListsLength = productLists.length;
	var productCount = getProductsCount();

	var responseObject = JSON.parse(this.response);
	var totalCount = responseObject.totalCount;
	var productItems = responseObject.item;

	document.querySelector("#totalCount").innerText = totalCount;
	for (var i = 0, len = productItems.length; i < len; i++) {
		productLists[productCount % productListsLength].innerHTML += parseProductToHtml(productItems[i]);
		productCount++;
	}

	if (productCount >= totalCount) {
		document.querySelector(".more .btn").style.display = "none";
	}

}

function getProductsCount() {
	var productLists = document.querySelectorAll(".lst_event_box");
	var productCount = 0;
	
	for (var i = 0, len = productLists.length; i < len; i++) {
		productCount += productLists[i].childElementCount;
	}
	return productCount;
}

function clearProductLists() {
	var productLists = document.querySelectorAll(".lst_event_box");
	
	for (var i = 0, len = productLists.length; i < len; i++) {
		productLists[i].innerHTML = "";
	}
}

document.addEventListener("DOMContentLoaded", function(){
	loadContent("/api/promotions", "GET", printPromotions);
	loadContent("/api/categories", "GET", printCategories);
	loadContent("/api/products", "GET", printProducts);
	
	addClickEventCategoryChange();
	addClickEventMoreBtn();
});

function addClickEventCategoryChange(){
	document.querySelector("#category_list").addEventListener("click", function(evt){
	    var paths = evt.path;
	    
	    for(var i=0, len=paths.length; i<len; i++){
	        if(paths[i].tagName === "A" && paths[i].className === "anchor"){
		    	document.querySelector(".anchor.active").className = "anchor";
				paths[i].className = "anchor active";
				
				clearProductLists();
				
				var categoryId = paths[i].parentElement.dataset.category;
				loadContent("/api/products?categoryId=" + categoryId, "GET", printProducts);
				
	    		document.querySelector(".more .btn").style.display = "inline-block";
				break;
	        }
	    }
	});
}
function addClickEventMoreBtn(){
	document.querySelector(".more .btn").addEventListener("click", function(){
	    var categoryId = document.querySelector(".anchor.active").parentElement.dataset.category;
	    var productCount = getProductsCount();
	    
	    loadContent("/api/products?categoryId=" + categoryId + "&start=" + productCount, "GET", printProducts);
	});
}