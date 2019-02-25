document.addEventListener("DOMContentLoaded", () => {

	template.setting();
	template.setUseDateFormat(0,10);
	
	var ajax = new Ajax();
	ajax.get("/api/promotions", printPromotions);
	ajax.get("/api/categories", printCategories);
	ajax.get("/api/products", printProducts);
	
	addClickEventCategoryChange();
	addClickEventMoreBtn();
	
});

function printPromotions(responseObj) {
	var promotionList = document.querySelector("#promotion_list");
	var promotionItems = responseObj.promotionList;
	promotionItems.forEach((promotionItem) => {
		promotionList.innerHTML += template.parsePromotionToHtml(promotionItem);
	});

	slidePromotion();
}

function slidePromotion(){
	var promotionList = document.querySelector("#promotion_list");
	var slide = new Slide(promotionList);
	slide.autoSlideRight(2000);
}

function printCategories(responseObj) {
	var categoryList = document.querySelector("#category_list");

	var categoryItems = responseObj.categoryList;
	categoryItems.forEach((categoryItem) =>{
		categoryList.innerHTML += template.parseCategoryToHtml(categoryItem);
	});
}

function printProducts(responseObj) {
	var productLists = document.querySelectorAll(".lst_event_box");
	var productListsLength = productLists.length;
	var productCount = getProductsCount();

	var totalCount = responseObj.totalCount;
	var productItems = responseObj.productList;

	document.querySelector("#totalCount").innerText = totalCount;
	productItems.forEach((productItem) => {
		productLists[productCount % productListsLength].innerHTML += template.parseProductToHtml(productItem);
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

function addClickEventCategoryChange(){
	document.querySelector("#category_list").addEventListener("click", (evt) => {
	    var paths = evt.path;
	    
	    paths.some((path) => {
	        if(path.tagName === "A" && path.className === "anchor"){
	            document.querySelector(".anchor.active").className = "anchor";
	            path.className = "anchor active";

	            clearProductLists();

	            var categoryId = path.parentElement.dataset.category;
	            
	            var ajax = new Ajax();
	            ajax.get("/api/products?categoryId=" + categoryId, printProducts);

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
	    
	    var ajax = new Ajax();
	    ajax.get("/api/products?categoryId=" + categoryId + "&start=" + productCount, printProducts);
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