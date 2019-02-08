/**
 * Copyright 2019 NAVER Corp. All rights reserved. Except in the case of
 * internal use for NAVER, unauthorized use of redistribution of this software
 * are strongly prohibited.
 */

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

// library mapping to use forEach
HTMLCollection.prototype.forEach = Array.prototype.forEach;
NodeList.prototype.forEach = Array.prototype.forEach;

window.addEventListener('DOMContentLoaded', function(){
	mainPage.init();
});

var mainPage = {
	domElements : {
		TAB_BUTTON_UL :	document.querySelector(".section_event_tab ul"),
		TAB_BUTTON_LI : document.querySelectorAll(".section_event_tab ul li"),
		SHOW_MORE_BUTTON : document.querySelector(".more button"),
		SLIDE_CONTAINER : document.querySelector(".visual_img"),
		PRODUCT_LISTS : document.querySelectorAll(".lst_event_box"),
		NEW_PRODUCT_ITEM : document.querySelector("#itemList").innerHTML,
		PRODUCT_NUMBER_IND : document.querySelector(".event_lst_txt span"),
		PROMO_TEMPLATE : document.querySelector("#promotionItem").innerHTML
	},
	
	urls : {
		CATEGORIES : "/reservation/api/categories",
		PROMOS : "/reservation/api/promotions",
		PRODUCTS : "/reservation/api/products",
		PRODUCTS_PARAMS : ""
	},
	state : {
		CATEGORY_DATA : "",
		loadedProductCount : 0,
		currentCategory : 0
	},
		
	parser : new DOMParser(),
	
	/**
	 * @init() : will be loaded with body onload, initialization function group
	 */
	init : function() {
		domElements = this.domElements;
		urls = this.urls;
		state = this.state;
		parser = this.parser;
		
		renderPromoItems = this.renderPromoItems;
		renderProductItems = this.renderProductItems;

		initTab = this.initTab;
		fetchPromos = this.fetchPromos;
		fetchProducts = this.fetchProducts;
		fetchProducts = this.fetchProducts;	
		fetchCategoryCounts = this.fetchCategoryCounts;
		switchCategory = this.switchCategory;
		
		initTab();
		fetchPromos();
		fetchProducts();	
		fetchCategoryCounts();
	},
	
	/**
	 * @initTab() : tab active css change and load more button visibility
	 *            control
	 */
	initTab : function() {
		domElements.TAB_BUTTON_UL.addEventListener("click", (e) => {
			if(e.target == domElements.TAB_BUTTON_UL) {
				return;
			}
			domElements.TAB_BUTTON_LI.forEach((item) => {
				let iter = item.firstElementChild;
				if(iter.classList.contains("active")) {
					iter.classList.remove("active");
				}
			});
			let tab = e.target.closest("li");
			tab.firstElementChild.classList.add("active");
			switchCategory(tab.dataset.category);
		});
		
		document.querySelector(".more").addEventListener("click", (e) => {
			fetchProducts(state.currentCategory, state.loadedProductCount);
		});
	},
	
	/**
	 * @fetchCategoryCounts() : fetch total number of rows in db by category
	 */
	fetchCategoryCounts : function () {
		xhrGetRequest(urls.CATEGORIES, (respText) => {
			state.CATEGORY_DATA = JSON.parse(respText);
			var totalProductsCount = 0; 
			state.CATEGORY_DATA.items.filter((item) => {
				totalProductsCount += item.count; 
				return;
			});
			state.CATEGORY_DATA.items.push({count : totalProductsCount, id : 0, name : "전체"});
		});
	},
	
	/**
	 * @fetchCategoryCounts() : fetch all information related to promotions
	 */
	fetchPromos : function () {
		xhrGetRequest(urls.PROMOS, (respText) => {
			let promotionData = JSON.parse(respText).items;
			promotionData.forEach((item) => {
				item.productImageUrl = "img/" + item.productImageUrl;
			});
			renderPromoItems(promotionData);
		});
	},

	/**
	 * @fetchProducts() : fetch products to be loaded on the next step by
	 *                  category, if categoryId == 0 || not in between 1 to 5,
	 *                  all category will be searched
	 */
	fetchProducts : function () {
	    urls.PRODUCTS_PARAMS = "?start=" + state.loadedProductCount
	    		                 + "&categoryId=" + state.currentCategory;
		let getProductUrl = urls.PRODUCTS + urls.PRODUCTS_PARAMS;
		xhrGetRequest(getProductUrl, (respText) => {
			let productData = JSON.parse(respText);
			productData.items.forEach((item) => {
				item.productImageUrl = "img/" + item.productImageUrl;
			});
			domElements.PRODUCT_NUMBER_IND.innerText = productData.totalCount + "개";
			renderProductItems(productData);
		});
	},
	
	/**
	 * @switchCategory() : current category will be switched, and related
	 *                   operation will be done
	 */
	switchCategory : function (category) {
		/*
		 * When category switch action, remove all the elements in the list and
		 * fetch + render items obtained. Force visibility of load more button
		 * page returns to 0;
		 */
		if(category != state.currentCategory) {
			domElements.PRODUCT_LISTS.forEach((list) => {
				list.innerHTML = "";
			});
			state.currentCategory = category;
			state.loadedProductCount = 0;
			fetchProducts();
			domElements.SHOW_MORE_BUTTON.style.visibility = "visible";
		}
	},

	/**
	 * @renderProductItems() : Loaded product items will be deployed on html,
	 *                       split the list of products by the order in the
	 *                       data;
	 */
	renderProductItems : function (productData) {
	    let bindTemplate = Handlebars.compile(domElements.NEW_PRODUCT_ITEM);
	    productData.items.forEach((item) => {
	    	let newProduct = parser.parseFromString(bindTemplate(item), "text/html").body.firstChild;
	    	domElements.PRODUCT_LISTS[state.loadedProductCount % 2].appendChild(newProduct);
	    	state.loadedProductCount++;
	    });
	    if(state.CATEGORY_DATA.items){
	    	state.CATEGORY_DATA.items.forEach((data) => {
		    	if(data.id == state.currentCategory) {
		    		if(data.count <= state.loadedProductCount) {
		    			domElements.SHOW_MORE_BUTTON.style.visibility = "hidden";
		    		} 
		    	}
		    });
	    }
	},

	/**
	 * @renderPromoItems() : Loaded promo items will be deployed on html
	 */
	renderPromoItems : function (promotionData) {
		arrayToLiRenderer(promotionData, domElements.SLIDE_CONTAINER, domElements.PROMO_TEMPLATE)
		let animation = new SlidingAnimation(domElements.SLIDE_CONTAINER);
		animation.changeTiming(4, 1000)
		animation.init(true);
			
	}
};
