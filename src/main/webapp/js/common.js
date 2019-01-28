document.addEventListener("DOMContentLoaded", function() {
	getProductsByCategory(0);
	slideImage();
});

// 이미지 슬라이드 : TODO css transform 속성으로 수정
var slides = document.querySelectorAll(".visual_img li");
var currentSlide = 0;
var nextSlide = 1;

var left = 0;
const imgWidth = slides[nextSlide].clientWidth;
slides[currentSlide].style.left = "0px";
slides[nextSlide].style.left = imgWidth + "px";
function slideImage() {
	left -= 30;
	slides[currentSlide].style.left = left + "px";
	slides[nextSlide].style.left = imgWidth + left + "px";
	if(slides[nextSlide].offsetLeft <= 0) {
		left = 0;
		slides[currentSlide].style.left = imgWidth + "px";
		slides[currentSlide].className = "waiting";
		currentSlide = nextSlide;
		nextSlide = (nextSlide + 1) % slides.length;
		slides[currentSlide].className = "slide_img";
		slides[nextSlide].className = "next_img";
	}
	requestAnimationFrame(slideImage);
}


// 탭 메뉴 : TODO 이벤트위임 방식으로 수정
let nowCatagoryId = 0;

var tab = document.querySelector(".section_event_tab").querySelectorAll(".anchor");
for(var i = 0; i < tab.length; i++){
	tab[i].addEventListener("click", function(){
		var nowActive = this.parentNode.parentNode.querySelector("[data-category='"+nowCatagoryId+"']").firstElementChild;
		nowActive.className = "anchor";
		this.className += " active";
		nowCatagoryId = this.parentNode.dataset.category;
		console.log(nowCatagoryId);
		getProductsByCategory(nowCatagoryId);
	});
}

var productContainer = document.querySelectorAll(".lst_event_box");
function getProductsByCategory(categoryId) {
	
	if (window.XMLHttpRequest) {
		var httpRequest =  new XMLHttpRequest();
		
		httpRequest.onreadystatechange = function() {  
		    if (httpRequest.readyState === 4 && httpRequest.status === 200) {
		    	var jsonResponse = JSON.parse(httpRequest.responseText);

		    	var countProduct = document.querySelector(".event_lst_txt span");
		    	countProduct.innerHTML = jsonResponse["totalCount"];
		    	
		    	var productTemplate = document.querySelector("#itemList").textContent;
		    	
		    	productContainer[0].innerHTML = "";
		    	productContainer[1].innerHTML = "";
		    	
		    	for(var i = 0, len = jsonResponse["items"].length; i < len; i++){
		    		var id = jsonResponse["items"][i]["productId"];
			    	var description = jsonResponse["items"][i]["productDescription"];
			    	var placeName = jsonResponse["items"][i]["placeName"];
			    	var content = jsonResponse["items"][i]["productContent"];
			    	var imgUrl = jsonResponse["items"][i]["productImageUrl"];
			    	
			    	var targetElement = productContainer[i % 2];
			    	targetElement.innerHTML += eval("`"+productTemplate+"`");
		    	}
		    }
		}
		
		var url = "./api/products?categoryId=" + categoryId;
		httpRequest.open("GET", url);
		httpRequest.setRequestHeader("Content-type", "charset=utf-8");
		httpRequest.send();
	}
}