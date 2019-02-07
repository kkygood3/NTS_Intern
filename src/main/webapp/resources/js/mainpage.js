document.addEventListener("DOMContentLoaded", () => {
	ajax("GET", "/api/promotions", "", printPromotions);
	ajax("GET", "/api/categories", "", printCategories);
	ajax("GET", "/api/products", "", printProducts);
	
	addClickEventCategoryChange();
	addClickEventMoreBtn();
	slidePromotion();
});

function ajax(method ,url, data, callback) {
	var xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.addEventListener("load", callback);
	xmlHttpRequest.open(method, url + "?" + data);
	xmlHttpRequest.send(data);
}

function printPromotions(evt) {
	var response = evt.currentTarget.response;
	
	var promotionList = document.querySelector("#promotion_list");
	var promotionItems = JSON.parse(response).promotionList;
	promotionItems.forEach((promotionItem) => {
		promotionList.innerHTML += parsePromotionToHtml(promotionItem);
	});

}

function printCategories(evt) {
	var response = evt.currentTarget.response;
	
	var categoryList = document.querySelector("#category_list");

	var categoryItems = JSON.parse(response).categoryList;
	categoryItems.forEach((categoryItem) =>{
		categoryList.innerHTML += parseCategoryToHtml(categoryItem);
	});
}

function printProducts(evt) {
	var response = evt.currentTarget.response;
	
	var productLists = document.querySelectorAll(".lst_event_box");
	var productListsLength = productLists.length;
	var productCount = getProductsCount();

	var responseObject = JSON.parse(response);
	var totalCount = responseObject.totalCount;
	var productItems = responseObject.productList;

	document.querySelector("#totalCount").innerText = totalCount;
	productItems.forEach((productItem) => {
		productLists[productCount % productListsLength].innerHTML += parseProductToHtml(productItem);
		productCount++;
	});

	if (productCount >= totalCount) {
		document.querySelector(".more .btn").style.display = "none";
	}

}

function clearProductLists() {
	var productLists = document.querySelectorAll(".lst_event_box");
	
	productLists.forEach((productList) => {
		productList.innerHTML = "";
	});
}

function slidePromotion(){
	setTimeout(() =>{
		var promotionList = document.querySelector("#promotion_list");
		promotionList.className = "visual_img slide";
		readjustPromotionList(promotionList);
	}, 1000);
}

function readjustPromotionList(promotionList){
	setTimeout(() => {
		promotionList.className = "visual_img";
		movePromotionHeadToTail(promotionList);
		slidePromotion();
	}, 1000);
}

function movePromotionHeadToTail(promotionList){
	var promotionItem = promotionList.firstElementChild;
	promotionList.removeChild(promotionItem);
	promotionList.appendChild(promotionItem);
}

function addClickEventCategoryChange(){
	document.querySelector("#category_list").addEventListener("click", (evt) => {
	    var paths = evt.path;
	    
	    paths.some((path) => {
	        if(path.tagName === "A" && path.className === "anchor"){
	            document.querySelector(".anchor.active").className = "anchor";
	            path.className = "anchor active";

	            clearProductLists();

	            var categoryId = path.parentElement.dataset.category;
	            ajax("GET", "/api/products", "categoryId=" + categoryId, printProducts);

	            document.querySelector(".more .btn").style.display = "inline-block";
	            return true;
	        	}
	        return false;
	    });
	});
}

function addClickEventMoreBtn(){
	document.querySelector(".more .btn").addEventListener("click", () => {
	    var categoryId = document.querySelector(".anchor.active").parentElement.dataset.category;
	    var productCount = getProductsCount();
	    
	    ajax("GET", "/api/products", "categoryId=" + categoryId + "&start=" + productCount, printProducts);
	});
}

function getProductsCount() {
	var productLists = document.querySelectorAll(".lst_event_box");
	var productCount = 0;

	productLists.forEach((productList) => {
		productCount += productList.childElementCount;
	});
	
	return productCount;
}