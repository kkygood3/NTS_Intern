/**
 * 
 */

var promotionData = null;
var productData = null;
const tabButton = document.querySelectorAll("div.section_event_tab ul li");
var currentCategory = 0;
var page = 0;
const promoContainer = document.querySelector("ul.visual_img");
var promoContainerWidth = promoContainer.offsetWidth;
var displayedItemCount= 0;
const productLists = document.querySelectorAll(".lst_event_box");
const newProductItem = document.querySelector("#itemList").innerHTML;

function init(){
	initTab();
	fetchPromos();
	fetchProducts(0,0);	
}

var initTab = () =>{
	console.log("tabInit");
	tabButton.forEach((item) => {
		item.addEventListener("click",(e)=> {
			tabButton.forEach((item)=> {
				let iter = item.firstElementChild;
				if(iter.classList.contains("active"))
				iter.classList.remove("active");
			});
			let tab = e.target.closest("li");
			tab.firstElementChild.classList.add("active");
			switchCategory(tab.dataset.category);
			page = 0;
		});
	});
}

var fetchPromos =()=>{
	let xhr = new XMLHttpRequest();
	xhr.open("GET", "/reservation/api/promotions", true);
	xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
	xhr.onreadystatechange = function(aEvt) {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				console.log("promoInit");
				var promoTemplate = document.querySelector("#promotionItem").innerHTML;
				promotionData = JSON.parse(xhr.responseText).items;
				var resultHTML = "";
				for(var promoItem in promotionData){
					resultHTML += promoTemplate.replace("{promotionImageUrl}",promotionData[promoItem].productImageUrl)
				}
				promoContainer.innerHTML = resultHTML;
			} else {
				alert("Error fetching promotions");
			}
		}
	};
	xhr.send();
}

var fetchProducts = (categoryId, start)=>{
	let xhr = new XMLHttpRequest();
	xhr.open("GET","/reservation/api/products/"+start+"/"+categoryId,true);
	xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
	xhr.onreadystatechange = function(aEvt) {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			console.log("product init");
			if (xhr.status === 200) {
				productData = JSON.parse(xhr.responseText);
				document.querySelector("p.event_lst_txt span").innerText = productData.totalCount + "개";
				loadItems();
			} else {
				alert("Error fetching products");
			}
		}
	};
	xhr.send();
}

var switchCategory = (category)=>{
	if(category!=currentCategory){
		productLists.forEach((list)=>{
			while (list.firstChild) {
				list.removeChild(list.firstChild);
			}
		});
		currentCategory = category;
		loadItems();
	}
}

var loadItems= () =>{
	let count = 0;
	productData.items.forEach((item)=> {
		 productLists[count%2].innerHTML+=newProductItem.replace("{id}",item.productId)
		 .replace ("{description}",item.productDescription)
		 .replace ("{placeName}",item.placeName)
		 .replace ("{content}",item.productContent)
		 .replace ("{imageUrl}",item.productImageUrl);;
		 count++;
	});

	
	
	// if(productDataIndex == productData.items.length){
	// document.querySelector("div.more").remove();
	// }
}

function animation(){
	var imgList = promoContainer.getElementsByTagName("li");

	if(count == -1 || count== imgList.length-1){
		for(var i = 1; i<imgList.length;i++){
			imgList[i].style.left = -414 * (i-1) +"px";
		}		
	}
	// console.log(imgList[count].style.left);
	imgList[count].style.left = imgList[count].style.left -414;
	// setTimeout(function(){
	// requestAnimationFrame(animation);
	// }, 3000);
}
