
var currentCount = 0;
var currentCategoryId = 0;
 


/**
 * @description : get method로 Product List를 요청
 */
function productListRequest(categoryId, start){
	var sendData = "?categoryId="+categoryId+"&start="+start;
	var requestInit = { method: "GET",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            }
	}
	var url = "/api/products";
	
	dataRequest(sendData,requestInit,url)
		.then(result =>{
		appendEventList(result.items, result.totalCount);
	});
}

/**
 * @description : 수신된 Product List를 HTML의 Tab UL에 추가
 */
function appendEventList(items, count){
	var eventUl = document.querySelectorAll(".lst_event_box");
	var appendEventHTML = document.querySelector("#itemList").innerText;
	
	for(var i=0, len=items.length;i<len;i++){
		var li = replaceEventHTML(items[i], appendEventHTML);
		eventUl[i%2].innerHTML += li;
	}
}

/**
 * @description : 수신된 item과 html mapping
 */
function replaceEventHTML(item, html){
	var displayInfoId = item["displayInfoId"];
	var placeName = item["placeName"];
	var productContent = item["productContent"];
	var productDescription = item["productDescription"];
	var productId = item["productId"];
	var productImageUrl = item["productImageUrl"];
	
	return  html.replace("${displayInfoId}",displayInfoId)
				.replace("${placeName}",placeName)
				.replace("${content}",productContent)
				.replace("${description}",productDescription)
				.replace("${description}",productDescription)
				.replace("${id}",productId)
				.replace("${productImageUrl}",productImageUrl);
}

/**
 * @description : 더보기 버튼 Event Listener 추가
 */
function addMoreButtonListener(){
	var moreButton = document.querySelector(".more").firstElementChild;
	moreButton.removeAttribute("style"); 
	moreButton.removeEventListener("click",moreButtonDown);
	
	if(parseInt(document.querySelector(".event_lst_txt").firstElementChild.innerText) <= 4){
		
		moreButton.setAttribute("style", 'display:none');
		
	}else{
		
		moreButton.addEventListener("click", moreButtonDown, true);
		
	}
}

/**
 * @description : 더보기 버튼 Event
 */
function moreButtonDown(){
		if(currentCount + 4 < parseInt(document.querySelector(".event_lst_txt").firstElementChild.innerText)){
			productListRequest(currentCategoryId, currentCount);
			currentCount += 4;
			
		}else{
			productListRequest(currentCategoryId, currentCount);
			document.querySelector(".more").firstElementChild.setAttribute("style", "display:none");
		}
}

function productReset(categoryId){
	currentCategoryId = categoryId;
	currentCount = 4;
	productListRequest(currentCategoryId, 0);
	
	addMoreButtonListener();
}