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

// HTML Dom element constants for faster access;
const tabButton = document.querySelectorAll("div.section_event_tab ul li");
const promoContainer = document.querySelector("ul.visual_img");
const productLists = document.querySelectorAll(".lst_event_box");
const newProductItem = document.querySelector("#itemList").innerHTML;
const productNumberInd =document.querySelector("p.event_lst_txt span");
const promoContainerWidth = promoContainer.offsetWidth;

// currently saved data for manipulation
var promotionData = null;
var productData = null;
var categoryData = null;
var imgList = null;

// current state;
var currentCategory = 0;
var page = 0;

// will be loaded with body onload
function init(){
	initTab();
	fetchPromos();
	fetchProducts(0,0);	
	fetchCategoryCounts();
}

// tab active css change and load more button visibility control
function initTab(){
	tabButton.forEach((item) => {
		item.addEventListener("click",(e)=> {
			tabButton.forEach((item)=> {
				let iter = item.firstElementChild;
				if(iter.classList.contains("active"))
				iter.classList.remove("active");
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
					document.querySelector("div.more > button").style.visibility = "hidden";
				} 
			}
		});
	});
}

// fetch total number of rows in db by category
function fetchCategoryCounts(){
	let xhr = new XMLHttpRequest();
	xhr.open("GET", "/reservation/api/categories", true);
	xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
	xhr.onreadystatechange = function(aEvt) {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				categoryData = JSON.parse(xhr.responseText).items;
				console.log(categoryData)
			} else {
				alert("Error fetching promotions");
			}
		}
	};
	xhr.send();
}

function fetchPromos(){
	let xhr = new XMLHttpRequest();
	xhr.open("GET", "/reservation/api/promotions", true);
	xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
	xhr.onreadystatechange = function(aEvt) {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				let promoTemplate = document.querySelector("#promotionItem").innerHTML;
				promotionData = JSON.parse(xhr.responseText).items;
				
				let resultHTML = "";
				promotionData.forEach((promoItem)=>{
					resultHTML += promoTemplate.replace("{promotionImageUrl}",promoItem.productImageUrl)
				})
				promoContainer.innerHTML = resultHTML;
				imgList = promoContainer.getElementsByTagName("li");
				requestAnimationFrame(initPromoAnimation);
			} else {
				alert("Error fetching promotions");
			}
		}
	};
	xhr.send();
}

function fetchProducts(categoryId, start){
	if(productData){
		productData.items = null;
	}
	let xhr = new XMLHttpRequest();
	xhr.open("GET","/reservation/api/products?start="+start+"&categoryId="+categoryId,true);
	xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
	xhr.onreadystatechange = function(aEvt) {
		if (xhr.readyState === XMLHttpRequest.DONE) {
				/*
				 * change the current productCountData according to current
				 * category state
				 */
				if (xhr.status === 200) {
					productData = JSON.parse(xhr.responseText);
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
					RenderItems();
				} else {
					alert("Error fetching products");
				}
		}
	}
	xhr.send();
}


function switchCategory(category){
	/*
	 * When category switch action, remove all the elements in the list and
	 * fetch + render items obtained. Force visbility of load more button
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
		RenderItems();
		document.querySelector("div.more > button").style.visibility = "visible";
	}
}

function RenderItems(){
	if(productData.items == null) return;
	let count = 0;
	productData.items.forEach((item)=> {
		 productLists[count%2].innerHTML+=
			   newProductItem.replace("{id}",item.productId)
		 // globally replace, working as replace all
							 .replace (/{description}/gi,item.productDescription)
							 .replace ("{placeName}",item.placeName)
							 .replace ("{content}",item.productContent)
							 .replace ("{imageUrl}",item.productImageUrl);
		 count++;
	});
	
}

function initPromoAnimation(){
	imgList.forEach((item)=>{
		item.style.left = "0px";
	})
	setTimeout(()=>{
		requestAnimationFrame(animation);
	},3000);
}

/**
 * @animationSpeed : to control the speed or animation.
 * 
 * @needToStop : this boolean indicates when the element is arrived in the right
 *             position to be displayed
 * @stopDuration : in milliseconds, determines the stop duration of the
 *               animation when the image arrives in the right position
 * @animation()
 */
function animation(){
	let animationSpeed = 10;
	let needToStop = false;
	let stopDuration = 1000;
	
	for(let iter = 0; iter<animationSpeed; iter++){
		for(let i = 0; i<imgList.length; i++){
			let currentLeft = parseInt(imgList[i].style.left);
			if((currentLeft) <= -promoContainerWidth*(i+1)){
				currentLeft = promoContainerWidth*(imgList.length-1-i);
				needToStop = true;
			}
			imgList[i].style.left = currentLeft-1 + "px";
		}	
		if(needToStop){
			break;
		}
	}
	
	if(needToStop){
		setTimeout(()=>{
			requestAnimationFrame(animation);
		},stopDuration);
	}
	else {
		requestAnimationFrame(animation);
	}
}
