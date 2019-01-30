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
const tabButton = document.querySelectorAll("div.section_event_tab ul li");
const showMoreButton = document.querySelector("div.more > button");

const promoContainer = document.querySelector("ul.visual_img");
const productLists = document.querySelectorAll(".lst_event_box");
const newProductItem = document.querySelector("#itemList").innerHTML;
const productNumberInd =document.querySelector("p.event_lst_txt span");
const promoTemplate = document.querySelector("#promotionItem").innerHTML;

const promoContainerWidth = promoContainer.offsetWidth;
const animationSpeed = 4;
const animationStopDuration = 1000;

// currently saved data for manipulation
var promotionData;
var productData;
var categoryData;
var imgList;

// current state;
var currentCategory = 0;
var page = 0;
var promoCount = 1;
var prevPromoCount = 0;

// promoNode, keep changing depends on the current showing element
var prev;
var current;

/**
 * @init() : will be loaded with body onload, initialization function group
 */
function init(){
	initTab();
	fetchPromos();
	fetchProducts(0,0);	
	fetchCategoryCounts();
}

/**
 * @xhrGetRequest() : pre-defined XmlHttpRequest Get method since get method
 *                  will be used frequently
 */
function xhrGetRequest(url,callback, isAsync){
	let xhr = new XMLHttpRequest();
	xhr.open("GET", url, isAsync);
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
	tabButton.forEach((item) => {
		item.addEventListener("click",(e)=> {
			tabButton.forEach((item)=> {
				let iter = item.firstElementChild;
				if(iter.classList.contains("active")){
					iter.classList.remove("active");
				}
			});
			let tab = e.target.closest("li");
			tab.firstElementChild.classList.add("active");
			page = 0;
			switchCategory(tab.dataset.category);
		});
	});
	
	document.querySelector("div.more").addEventListener("click",(e)=>{
		page++;
		fetchProducts(currentCategory,page*4);
		categoryData.items.forEach((data)=>{
			if(data.id == currentCategory){
				if(data.count-4 <= page*4){
					showMoreButton.style.visibility = "hidden";
				} 
			}
		});
	});
}

/**
 * @fetchCategoryCounts() : fetch total number of rows in db by category
 */
function fetchCategoryCounts(){
	xhrGetRequest("/reservation/api/categories",(respText)=>{
		categoryData = JSON.parse(respText);
	},true);
}

/**
 * @fetchCategoryCounts() : fetch all information related to promotions
 */
function fetchPromos(){
	xhrGetRequest("/reservation/api/promotions",(respText)=>{
		promotionData = JSON.parse(respText).items;
		renderPromoItems();
	},true);
}

/**
 * @fetchProducts() : fetch products to be loaded on the next step by category,
 *                  if categoryId == 0 || not in between 1 to 5, all category
 *                  will be searched
 */
function fetchProducts(categoryId, start){
	if(productData){
		productData.items = null;
	}
	xhrGetRequest("/reservation/api/products?start="+start+"&categoryId="+categoryId,(respText)=>{
		productData = JSON.parse(respText);
		if(categoryId ==0){
			productNumberInd.innerText = productData.totalCount + "개";
		}
		else{
			categoryData.items.forEach((data)=>{
				if(data.id == categoryId){
					productNumberInd.innerText = data.count + "개";
				}
			});
		}
		RenderProductItems();
	},true);
}


/**
 * @switchCategory() : current category will be switched, and related operation
 *                   will be done
 */
function switchCategory(category){
	/*
	 * When category switch action, remove all the elements in the list and
	 * fetch + render items obtained. Force visbility of load more button page
	 * returns to 0;
	 */
	if(category!=currentCategory){
		productLists.forEach((list)=>{
			while (list.firstChild) {
				list.removeChild(list.firstChild);
			}
		});
		currentCategory = category;
		page = 0;
		fetchProducts(currentCategory,page*4);
		showMoreButton.style.visibility = "visible";
	}
}

/**
 * @RenderProductItems() : Loaded product items will be deployed on html, split
 *                       the list of products by the order in the data;
 */
function RenderProductItems(){
	let indexCount = 0;	
	let bindTemplate = Handlebars.compile(newProductItem);
	productData.items.forEach((item)=> {
		 productLists[indexCount%2].innerHTML+=
			 bindTemplate(item);
		 indexCount++;
	});	
}

/**
 * @renderPromoItems() : Loaded promo items will be deployed on html
 */
function renderPromoItems(){
	let bindTemplate = Handlebars.compile(promoTemplate);
	
	let resultHTML = "";
	promotionData.forEach((promoItem)=>{
		resultHTML +=bindTemplate(promoItem);
	});
	promoContainer.innerHTML = resultHTML;
	imgList = promoContainer.getElementsByTagName("li");
	requestAnimationFrame(initPromoAnimation);
}

/**
 * @initPromoAnimation() : required setup for the promo animation, and
 *                       initialization of animation fram call
 */
function initPromoAnimation(){
	// fix the layout in case of error
	imgList[0].style.left = 0+"px";
	imgList[0].style.position = "absolute";
	imgList.forEach((item)=>{
		if(item!=imgList[0]){
			item.style.left = promoContainerWidth+"px";
			item.style.position = "absolute";
		}
	});
	
	// timeout since first promoslide should be displayed before transition
	setTimeout(()=>{
		prev = imgList[prevPromoCount];
		current = imgList[promoCount];
		requestAnimationFrame(promoAnimation);
	},3000);
}

/**
 * @animationSpeed : to control the speed or animation, declared as const in
 *                 global variable
 * 
 * @needToStop : this boolean indicates when the element is arrived in the right
 *             position to be displayed
 * 
 * @animationStopDuration : in milliseconds, determines the stop duration of the
 *                        animation when the image arrives in the right position ,
 *                        declared as const in global variable
 * 
 * @promoAnimation() : promotion animation with 2 for loops to control the
 *                   accuracy of the stop-position
 */
function promoAnimation(){
	let needToStop = false;
	
	for(let iter = 0; iter<animationSpeed; iter++){
		prev.style.left = parseInt(prev.style.left)-1 + "px";
		current.style.left = parseInt(current.style.left)-1 + "px";
		if(parseInt(prev.style.left) <= -promoContainerWidth){
			prev.style.left = promoContainerWidth+"px";
			prevPromoCount = promoCount;
			promoCount++;
			if(promoCount == imgList.length){
				promoCount =0;
			}
			needToStop = true;
			break;
		}
	}	
	if(needToStop){
		setTimeout(()=>{
			prev = imgList[prevPromoCount];
			current = imgList[promoCount];
			requestAnimationFrame(promoAnimation);
		},animationStopDuration);
	}
	else {
		requestAnimationFrame(promoAnimation);
	}
}
