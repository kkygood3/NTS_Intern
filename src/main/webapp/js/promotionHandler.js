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
	setRightMoveAnimation("#promotion_ul", 414, 1000);
}

