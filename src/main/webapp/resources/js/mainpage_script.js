/**
 * 
 */

var promotionData = null;
var productData = null;
var tabButton = document.querySelectorAll("div.section_event_tab ul li");
var currentCategory = 0;
var page = 0;
var promoContainer = document.querySelector("ul.visual_img");
var promoContainerWidth = promoContainer.offsetWidth;


function init(){
	initTab();
	fetchPromos();
	fetchProducts();
}

var initTab = () =>{
	console.log("tabInit");
	tabButton.forEach((item) => {
		item.addEventListener("click",(e)=> {
			tabButton.forEach((item)=> {
				var iter = item.firstElementChild;
				if(iter.classList.contains("active"))
				iter.classList.remove("active");
			});
			var tab = e.target.closest("li");
			tab.firstElementChild.classList.add("active");
			currentCategory = tab.dataset.category;
			console.log(currentCategory);
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
				// console.log(JSON.parse(xhr.responseText));
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
			fetchProducts();
		}
	};
	xhr.send();
}

var fetchProducts = ()=>{
	let xhr = new XMLHttpRequest();
	xhr.open("GET","/reservation/api/products",true);
	xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
	xhr.onreadystatechange = function(aEvt) {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			console.log("product init");
			if (xhr.status === 200) {
				productData = JSON.parse(xhr.responseText).items
			} else {
				alert("Error fetching products");
			}
		}
	};
	xhr.send();
}

var deployItems = (productData)=>{
	let countFour = 0;
	
}

function animation(){
	var imgList = promoContainer.getElementsByTagName("li");

	if(count == -1 || count== imgList.length-1){
		for(var i = 1; i<imgList.length;i++){
			imgList[i].style.left = -414 * (i-1) +"px";
		}		
		count =0;
	}
	// console.log(imgList[count].style.left);
	imgList[count].style.left = imgList[count].style.left -414;
	count ++;
	// setTimeout(function(){
	// requestAnimationFrame(animation);
	// }, 3000);
}
