/**
 * @description : get method로 Promotion List를 요청
 */
function promotionListRequest(){
	var requestInit = { method: "GET",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            }
	}
	var url = "/api/promotions";
	
	dataRequest("",requestInit,url)
		.then(result =>{
			appendVisualImgList(result.items);
	});
}


/**
 * @description : 수신된 Promotion List를 HTML의 visual_img UL에 추가
 */
function appendVisualImgList(items){
	var visualImgUl = document.querySelector(".visual_img");
	var appendVisualImgHTML = document.querySelector("#promotionItem").innerText;
	
	for(var i=0, len=items.length;i<len;i++){
		var li = replaceVisualImgHTML(items[i], appendVisualImgHTML);
		visualImgUl.innerHTML += li;
	}
	setPromotionAnimation();
}

/**
 * @description : 수신된 item과 html mapping
 */
function replaceVisualImgHTML(item, html){
	var productImageUrl = item["productImageUrl"];
	
	return  html.replace("${productImageUrl}",productImageUrl);
}

/**
 * @description : Promotion Animation 설정
 */
function setPromotionAnimation(){
	var visualImgUl = document.querySelector(".visual_img");
	var childCount = visualImgUl.childElementCount;
	var shiftPixel = 414;
	var interval = 1000;
	
	visualImgUl.style.right = 0;
	
	setTimeout(function run(){
		visualImgUl.style.transition = "right 1s";
		visualImgUl.style.right = parseInt(visualImgUl.style.right) + shiftPixel + "px";
		
		if(parseInt(visualImgUl.style.right) > shiftPixel){
			visualImgUl.innerHTML += visualImgUl.firstElementChild.outerHTML;
			visualImgUl.style.transition = "right 0s";
			visualImgUl.style.right = 0;
			visualImgUl.removeChild(visualImgUl.firstElementChild);
		}
		
	    setTimeout(run, interval);
	}, interval / 2);
}


function promotionReset(){
	promotionListRequest();
}