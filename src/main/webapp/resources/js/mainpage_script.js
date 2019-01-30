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

// HTML Dom element / constants for faster access;
var domElements = {
	TAB_BUTTON_UL :	document.querySelector("div.section_event_tab ul"),
	TAB_BUTTON_LI : document.querySelectorAll("div.section_event_tab ul li"),
	SHOW_MORE_BUTTON : document.querySelector("div.more button"),
	PROMO_CONTAINER : document.querySelector("ul.visual_img"),
	PRODUCT_LISTS : document.querySelectorAll(".lst_event_box"),
	NEW_PRODUCT_ITEM : document.querySelector("#itemList").innerHTML,
	PRODUCT_NUMBER_IND : document.querySelector("p.event_lst_txt span"),
	PROMO_TEMPLATE : document.querySelector("#promotionItem").innerHTML
}

var constants = {
	PROMO_CONATINER_WIDTH : domElements.PROMO_CONTAINER.offsetWidth,
	ANIMATION_SPEED : 4,
	ANIMATION_STOP_DURATION : 1000,
	ITEMS_PER_LOAD : 4
}

var pageData = {
	CATEGORY_DATA:{},
	IMAGE_LIST:[]
}

var urls = {
	CATEGORIES : "/reservation/api/categories",
	PROMOS : "/reservation/api/promotions",
	PRODUCTS : "/reservation/api/products"
}


// current state;
var pageState = {
	currentCategory : 0,
	productPage : 0,
	promoCount : 1,
	prevPromoCount : 0,
	prevPromoImage : "",
	currentPromoImage : ""
}


// promoNode, keep changing depends on the current showing element

/**
 * @init() : will be loaded with body onload, initialization function group
 */
function init(){
	initTab();
	fetchPromos();
	fetchProducts();	
	fetchCategoryCounts();
}

/**
 * @xhrGetRequest() : pre-defined XmlHttpRequest Get method since get method
 *                  will be used frequently
 */
function xhrGetRequest(url,callback){
	let xhr = new XMLHttpRequest();
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
	xhr.onreadystatechange = function(aEvt) {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				callback(xhr.responseText);
			} else {
				alert("Error fetching");
			}
		}
	};
	xhr.send();
}

/**
 * @initTab() : tab active css change and load more button visibility control
 */
function initTab(){
	domElements.TAB_BUTTON_UL.addEventListener("click", (e) =>{
		if(e.target == domElements.TAB_BUTTON_UL){
			return;
		}
		domElements.TAB_BUTTON_LI.forEach((item) => {
			let iter = item.firstElementChild;
			if(iter.classList.contains("active")){
				iter.classList.remove("active");
			}
		});
		let tab = e.target.closest("li");
		tab.firstElementChild.classList.add("active");
		pageState.productPage = 0;
		switchCategory(tab.dataset.category);
	});
	
	document.querySelector("div.more").addEventListener("click",(e) => {
		pageState.productPage++;
		fetchProducts(pageState.currentCategory, pageState.productPage * constants.ITEMS_PER_LOAD);
		pageData.CATEGORY_DATA.items.forEach((data) => {
			if(data.id == pageState.currentCategory){
				if(data.count-constants.ITEMS_PER_LOAD <= pageState.productPage * constants.ITEMS_PER_LOAD){
					domElements.SHOW_MORE_BUTTON.style.visibility = "hidden";
				} 
			}
		});
	});
}

/**
 * @fetchCategoryCounts() : fetch total number of rows in db by category
 */
function fetchCategoryCounts(){
	xhrGetRequest(urls.CATEGORIES,(respText) => {
		pageData.CATEGORY_DATA = JSON.parse(respText);
	});
}

/**
 * @fetchCategoryCounts() : fetch all information related to promotions
 */
function fetchPromos(){
	xhrGetRequest(urls.PROMOS,(respText) => {
		let promotionData = JSON.parse(respText).items;
		promotionData.forEach((item) => {
			item.productImageUrl = "img/" + item.productImageUrl;
		});
		renderPromoItems(promotionData);
	});
}

/**
 * @fetchProducts() : fetch products to be loaded on the next step by category,
 *                  if categoryId == 0 || not in between 1 to 5, all category
 *                  will be searched
 */
function fetchProducts(){
	let getProductUrl = urls.PRODUCTS;
	getProductUrl += "?start=" + Math.round(pageState.productPage*constants.ITEMS_PER_LOAD) 
					+ "&categoryId=" + pageState.currentCategory;
	
	xhrGetRequest(getProductUrl,(respText) => {
		let productData = JSON.parse(respText);
		productData.items.forEach((item) => {
			item.productImageUrl = "img/" + item.productImageUrl;
		});
		if(pageState.currentCategory > 0){
			pageData.CATEGORY_DATA.items.forEach((data) => {
				if(data.id == pageState.currentCategory){
					domElements.PRODUCT_NUMBER_IND.innerText = data.count + "개";
				}
			});
		}
		else{
			domElements.PRODUCT_NUMBER_IND.innerText = productData.totalCount + "개";
		}
		RenderProductItems(productData);
	});
}


/**
 * @switchCategory() : current category will be switched, and related operation
 *                   will be done
 */
function switchCategory(category){
	/*
	 * When category switch action, remove all the elements in the list and
	 * fetch + render items obtained. Force visibility of load more button page
	 * returns to 0;
	 */
	if(category != pageState.currentCategory){
		domElements.PRODUCT_LISTS.forEach((list) => {
			while (list.firstChild) {
				list.removeChild(list.firstChild);
			}
		});
		pageState.currentCategory = category;
		page = 0;
		fetchProducts();
		domElements.SHOW_MORE_BUTTON.style.visibility = "visible";
	}
}

/**
 * @RenderProductItems() : Loaded product items will be deployed on html, split
 *                       the list of products by the order in the data;
 */
function RenderProductItems(productData){
	let indexCount = 0;	
	let bindTemplate = Handlebars.compile(domElements.NEW_PRODUCT_ITEM);
	productData.items.forEach((item) => {
		domElements.PRODUCT_LISTS[indexCount % 2].innerHTML += bindTemplate(item);
		indexCount++;
	});	
}

/**
 * @renderPromoItems() : Loaded promo items will be deployed on html
 */
function renderPromoItems(promotionData){
	let bindTemplate = Handlebars.compile(domElements.PROMO_TEMPLATE);
	
	let resultHTML = "";
	promotionData.forEach((promoItem) => {
		resultHTML += bindTemplate(promoItem);
	});
	domElements.PROMO_CONTAINER.innerHTML = resultHTML;
	pageData.IMAGE_LIST = domElements.PROMO_CONTAINER.getElementsByTagName("li");
	requestAnimationFrame(initPromoAnimation);
}

/**
 * @initPromoAnimation() : required setup for the promo animation, and
 *                       initialization of animation frame call
 */
function initPromoAnimation(){
	// fix the layout in case of error
	pageData.IMAGE_LIST.forEach((item) => {
		if(item == pageData.IMAGE_LIST[0]){
			pageData.IMAGE_LIST[0].style.left = 0 + "px";
		} else {
			item.style.left = constants.PROMO_CONATINER_WIDTH + "px";
		}
		item.style.position = "absolute";
		
	});
	
	// timeout since first promo-slide should be displayed before transition
	setTimeout(()=>{
		pageState.prevPromoImage = pageData.IMAGE_LIST[pageState.prevPromoCount];
		pageState.currentPromoImage = pageData.IMAGE_LIST[pageState.promoCount];
		requestAnimationFrame(promoAnimation);
	},constants.ANIMATION_STOP_DURATION);
}

/**
 * @constants.ANIMATION_SPEED : to control the speed or animation, declared as
 *                            const in global variable
 * 
 * @needToStop : this boolean indicates when the element is arrived in the right
 *             position to be displayed
 * 
 * @constants.ANIMATION_STOP_DURATION : in milliseconds, determines the stop
 *                                    duration of the animation when the image
 *                                    arrives in the right position , declared
 *                                    as const in global variable
 * 
 * @promoAnimation() : promotion animation with 2 for loops to control the
 *                   accuracy of the stop-position
 */
function promoAnimation(){
	let needToStop = false;
	let prevImage = pageState.prevPromoImage;
	let currentImage = pageState.currentPromoImage;
	
	for(let iter = 0; iter < constants.ANIMATION_SPEED; iter++){
		prevImage.style.left = parseInt(prevImage.style.left) - 1 + "px";
		currentImage.style.left = parseInt(currentImage.style.left) - 1 + "px";
		
		if(parseInt(prevImage.style.left) <= -1 * constants.PROMO_CONATINER_WIDTH){
			prevImage.style.left = constants.PROMO_CONATINER_WIDTH + "px";
			pageState.prevPromoCount = pageState.promoCount;
			pageState.promoCount++;
			if(pageState.promoCount == pageData.IMAGE_LIST.length){
				pageState.promoCount = 0;
			}
			needToStop = true;
			break;
		}
	}	
	if(needToStop){
		setTimeout(() => {
			pageState.prevPromoImage = pageData.IMAGE_LIST[pageState.prevPromoCount];
			pageState.currentPromoImage = pageData.IMAGE_LIST[pageState.promoCount];
			requestAnimationFrame(promoAnimation);
		},constants.ANIMATION_STOP_DURATION);
	}
	else {
		requestAnimationFrame(promoAnimation);
	}
}
