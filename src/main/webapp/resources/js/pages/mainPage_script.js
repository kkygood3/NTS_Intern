/**
 * Copyright 2019 NAVER Corp. All rights reserved. Except in the case of
 * internal use for NAVER, unauthorized use of redistribution of this software
 * are strongly prohibited.
 */

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

window.addEventListener("DOMContentLoaded", function () {
    mainPage.init();
});

var mainPage = {
    domElements: {
        tabButtonUl: document.querySelector(".section_event_tab ul"),
        tabButtonLi: document.querySelectorAll(".section_event_tab ul li"),
        showMoreButton: document.querySelector(".more button"),
        slideContainer: document.querySelector(".visual_img"),
        productLists: document.querySelectorAll(".lst_event_box"),
        productNumberInd: document.querySelector(".event_lst_txt span"),
    },

    templates: {
        newProductItem: document.querySelector("#itemList").innerHTML,
        promoTemplate: document.querySelector("#promotionItem").innerHTML
    },

    urls: {
        CATEGORIES: "/reservation/api/categories",
        PROMOS: "/reservation/api/promotions",
        PRODUCTS: "/reservation/api/products",
    },
    state: {
        categoryData: "",
        loadedProductCount: 0,
        currentCategory: 0
    },
    parser : new DOMParser(),

    /**
	 * @init() : will be loaded with body onload, initialization function group
	 */
    init: function () {
    	this.initTab();
    	this.fetchPromos();
    	this.fetchCategoryCounts();
    },

    /**
	 * @initTab() : tab active css change and load more button visibility
	 *            control
	 */
    initTab: function () {
        this.domElements.tabButtonUl.addEventListener("click", (e) => {
            if (e.target == this.domElements.tabButtonUl) {
                return;
            }
            this.domElements.tabButtonLi.forEach((item) => {
                let iter = item.firstElementChild;
                if (iter.classList.contains("active")) {
                    iter.classList.remove("active");
                }
            });
            let tab = e.target.closest("li");
            tab.firstElementChild.classList.add("active");
            this.switchCategory(tab.dataset.category);
        });

        document.querySelector(".more").addEventListener("click", (e) => {
        	this.fetchProducts(this.state.currentCategory, this.state.loadedProductCount);
        });
    },

    /**
	 * @fetchCategoryCounts() : fetch total number of rows in db by category
	 */
    fetchCategoryCounts: function () {
        let request = new XhrRequest("GET", this.urls.CATEGORIES);
        request.setCallback((respText) => {
        	this.state.categoryData = JSON.parse(respText);
            var totalProductsCount = 0;
            this.state.categoryData.items.filter((item) => {
                totalProductsCount += item.count;
                return;
            });
            this.state.categoryData.items.push({count: totalProductsCount, id: 0, name: "전체"});
            this.fetchProducts();
        });
        request.send();
    },

    /**
	 * @fetchPromos() : fetch all information related to promotions
	 */
    fetchPromos: function () {
        let request = new XhrRequest("GET", this.urls.PROMOS);
        request.setCallback((respText) => {
                let promotionData = JSON.parse(respText).items;
                promotionData.forEach((item) => {
                    item.productImageUrl = "img/" + item.productImageUrl;
                });
                this.renderPromoItems(promotionData);
            });
        request.send();
    },

    /**
	 * @fetchProducts() : fetch products to be loaded on the next step by
	 *                  category, if categoryId == 0 || not in between 1 to 5,
	 *                  all category will be searched
	 */
    fetchProducts: function () {
        products_params = "?start=" + this.state.loadedProductCount
            + "&categoryId=" + this.state.currentCategory;
        let getProductUrl = this.urls.PRODUCTS + products_params;
        
        let request = new XhrRequest("GET", getProductUrl);
        request.setCallback((respText) => {
            let productData = JSON.parse(respText);
            productData.items.forEach((item) => {
                item.productImageUrl = "img/" + item.productImageUrl;
            });
            this.domElements.productNumberInd.innerText = productData.totalCount + "개";
            this.renderProductItems(productData);
        });
        request.send();
    },

    /**
	 * @switchCategory() : current category will be switched, and related
	 *                   operation will be done
	 */
    switchCategory: function (category) {
        /*
		 * When category switch action, remove all the elements in the list and
		 * fetch + render items obtained. Force visibility of load more button
		 * page returns to 0;
		 */
        if (category != this.state.currentCategory) {
        	this.domElements.productLists.forEach((list) => {
                list.innerHTML = "";
            });
        	this.state.currentCategory = category;
        	this.state.loadedProductCount = 0;
            this.fetchProducts();
            this.domElements.showMoreButton.style.visibility = "visible";
        }
    },

    /**
	 * @renderProductItems() : Loaded product items will be deployed on html,
	 *                       split the list of products by the order in the
	 *                       data;
	 */
    renderProductItems: function (productData) {
        let bindTemplate = Handlebars.compile(this.templates.newProductItem);
        productData.items.forEach((item) => {
            let newProduct = this.parser.parseFromString(bindTemplate(item), "text/html").body.firstChild;
            this.domElements.productLists[this.state.loadedProductCount % 2].appendChild(newProduct);
            this.state.loadedProductCount++;
        });
        if (this.state.categoryData.items) {
        	this.state.categoryData.items.forEach((data) => {
                if (data.id == this.state.currentCategory) {
                    if (data.count <= this.state.loadedProductCount) {
                    	this.domElements.showMoreButton.style.visibility = "hidden";
                    }
                }
            });
        }
    },

    /**
	 * @renderPromoItems() : Loaded promo items will be deployed on html
	 */
    renderPromoItems: function (promotionData) {
        arrayToElementRenderer(promotionData, this.domElements.slideContainer, this.templates.promoTemplate)
        let animation = new SlidingAnimation(this.domElements.slideContainer);
        animation.init({animationSpeed: 4, animationStopDuration: 1000});
        animation.startAutoAnimation();
    }
};
