/**
 * @description : get method로 Promotion List를 요청
 */
function promotionListRequest(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "/api/promotions", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

	xhr.onreadystatechange = function() {
		if(xhr.readyState==4 && xhr.status==200){
			appendPromotionList(JSON.parse(xhr.responseText).items);
		}
	}

	xhr.send();
}

/**
 * @description : 수신된 Promotion List를 HTML의 Promotion UL에 추가
 */
function appendPromotionList(items){
	var promotionUl = document.querySelector("#promotion_ul");
	var appendPromotionListHTML = document.querySelector("#promotionItem").innerText;
	
	for(var i=0, len=items.length;i<len;i++){
		var li = replaceTemplateHTML(items[i], appendPromotionListHTML);
		promotionUl.innerHTML += li;
	}
	setPromotionAnimation();
}


/**
 * @description : Promotion Animation 설정
 */
function setPromotionAnimation(){
	var promotionUl = document.querySelector("#promotion_ul");
	var childCount = promotionUl.childElementCount;
	var shiftPixels = 414;
	var intervalMillis = 1000;
	
	promotionUl.style.right = 0;
	
	setTimeout(function run(){
		promotionUl.style.transition = "right 1s";
		promotionUl.style.right = parseInt(promotionUl.style.right) + shiftPixels + "px";
		
		if(parseInt(promotionUl.style.right) > shiftPixels){
			promotionUl.innerHTML += promotionUl.firstElementChild.outerHTML;
			promotionUl.style.transition = "right 0s";
			promotionUl.style.right = 0;
			promotionUl.removeChild(promotionUl.firstElementChild);
		}
		
	    setTimeout(run, intervalMillis);
	}, intervalMillis / 2);
}
