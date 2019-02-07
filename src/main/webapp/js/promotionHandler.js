/**
 * @description : get method로 Promotion List를 요청
 */
function promotionListRequest(){
	var request = { method: "GET",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            }
	}
	
	dataRequestGET("/api/promotions", "", request)
		.then(result =>{
			appendPromotionList(result.items);
	});
}


/**
 * @description : 수신된 Promotion List를 HTML의 Promotion UL에 추가
 */
function appendPromotionList(items){
	var promotionUl = document.querySelector("#promotion_ul");
	var appendPromotionListHTML = document.querySelector("#promotionItem").innerText;
	
	for(var i=0, len=items.length;i<len;i++){
		var li = replacePromotionHTML(items[i], appendPromotionListHTML);
		promotionUl.innerHTML += li;
	}
	setPromotionAnimation();
}

/**
 * @description : 수신된 item과 html mapping
 */
function replacePromotionHTML(item, html){
	
	return  html.replace("${productImageUrl}", item.productImageUrl);
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
