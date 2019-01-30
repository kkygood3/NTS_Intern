function loadContent(url, method, loadFunction) {
	var xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.addEventListener("load", loadFunction);
	xmlHttpRequest.open(method, url);
	xmlHttpRequest.send();
}

function printPromotions() {
	var promotionList = document.querySelector("#promotion_list");

	var promotionItems = JSON.parse(this.response).item;
	promotionItems.forEach((promotionItem) => {
		promotionList.innerHTML += parsePromotionToHtml(promotionItem);
	});

}

function printCategories() {
	var categoryList = document.querySelector("#category_list");

	var categoryItems = JSON.parse(this.response).item;
	categoryItems.forEach((categoryItem) =>{
		categoryList.innerHTML += parseCategoryToHtml(categoryItem);
	});
}

function printProducts() {
	var productLists = document.querySelectorAll(".lst_event_box");
	var productListsLength = productLists.length;
	var productCount = getProductsCount();

	var responseObject = JSON.parse(this.response);
	var totalCount = responseObject.totalCount;
	var productItems = responseObject.item;

	document.querySelector("#totalCount").innerText = totalCount;
	productItems.forEach((productItem) => {
		productLists[productCount % productListsLength].innerHTML += parseProductToHtml(productItem);
		productCount++;
	});

	if (productCount >= totalCount) {
		document.querySelector(".more .btn").style.display = "none";
	}

}

function getProductsCount() {
	var productLists = document.querySelectorAll(".lst_event_box");
	var productCount = 0;
	
	productLists.forEach((productList) => {
		productCount + productList.childElementCount;
	});
	
	return productCount;
}

function clearProductLists() {
	var productLists = document.querySelectorAll(".lst_event_box");
	
	productLists.forEach((productList) => {
		productList.innerHTML = "";
	});
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
	    
	    paths.some((path) => {
	        if(path.tagName === "A" && path.className === "anchor"){
	            document.querySelector(".anchor.active").className = "anchor";
	            path.className = "anchor active";

	            clearProductLists();

	            var categoryId = path.parentElement.dataset.category;
	            loadContent("/api/products?categoryId=" + categoryId, "GET", printProducts);

	            document.querySelector(".more .btn").style.display = "inline-block";
	            return true;
	        }
	        return false;
	    });
	});
}
function addClickEventMoreBtn(){
	document.querySelector(".more .btn").addEventListener("click", function(){
	    var categoryId = document.querySelector(".anchor.active").parentElement.dataset.category;
	    var productCount = getProductsCount();
	    
	    loadContent("/api/products?categoryId=" + categoryId + "&start=" + productCount, "GET", printProducts);
	});
}