/**
 * 
 */
const tabButton = document.querySelectorAll("div.section_event_tab ul li");
const promoContainer = document.querySelector("ul.visual_img");
const productLists = document.querySelectorAll(".lst_event_box");
const newProductItem = document.querySelector("#itemList").innerHTML;
const productNumberInd =document.querySelector("p.event_lst_txt span");
const promoContainerWidth = promoContainer.offsetWidth;

var promotionData = null;
var productData = null;
var categoryData = null;
var imgList = null;

var currentCategory = 0;
var page = 0;

var promoCount = 0;

var floorMod = function (n, m) {
    var remain = n % m;
    return Math.floor(remain >= 0 ? remain : remain + m);
};

function init(){
	initTab();
	fetchPromos();
	fetchProducts(0,0);	
	fetchCategoryCounts();
}

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
	xhr.open("GET","/reservation/api/products/"+start+"/"+categoryId,true);
	xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
	xhr.onreadystatechange = function(aEvt) {
		if (xhr.readyState === XMLHttpRequest.DONE) {
				/*
				 * reset the current productData since this would be written
				 * along the new items
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
	imgList[0].style.left = "0px";
	imgList[0].style.zIndex = 0+"";
	
	for(let i = 1; i<imgList.length;i++){
		imgList[i].style.left = "0px";
		imgList[i].style.zIndex = imgList.length-i+"";
	}	
	setTimeout(()=>{
		requestAnimationFrame(animation);
	},3000);
}


function animation(){
	let divisor = 2;
	var imgList = promoContainer.getElementsByTagName("li");
	var needToStop = false;
	for(var i = 0; i<imgList.length;i++){
		let currentLeft = parseInt(imgList[i].style.left);
		if((currentLeft) <= -414*(i+1)+divisor*2){
			currentLeft = 414*(imgList.length-1-i);
			needToStop = true;
		}
		imgList[i].style.left = currentLeft-divisor + "px";
	}	
	
	if(needToStop){
		setTimeout(()=>{
			requestAnimationFrame(animation);
		},1000);
	}
	else {
		requestAnimationFrame(animation);
	}
}
