var currentStart = 0;
var currentCategory = 0;

function requestAjax(callback, url){
	var ajaxReq = new XMLHttpRequest();
	ajaxReq.callback = callback;
	ajaxReq.addEventListener('load', function(evt) {
		this.callback(evt.target.response);
	});
	
	ajaxReq.open('GET', 'api/'+url);
	ajaxReq.responseType = 'json';
	ajaxReq.send()
}

function loadCategoriesCallback(responseData){
	var categoryList = responseData.categoryList;
	
	var template = document.querySelector('#categoryItem').innerHTML;
	var resultHtml = '';
	for(var i = 0 ; i < categoryList.length; i++){
		resultHtml += template
							.replace('{name}',categoryList[i].name)
							.replace('{id}',categoryList[i].id);
	}
	
	document.querySelector('ul.event_tab_lst').innerHTML += resultHtml;
}

function setPromotionMove() {
	var promotionList = document.querySelectorAll('.visual_img>.item');
	var leftDistance = 0;
	var itemSize = promotionList.length;
	var curIdx = 0;
	
	setInterval(moveStep, 4000);

	function moveStep(){
		leftDistance -= 100;

		for (var i = 0; i < itemSize; ++i) {
			promotionList[i].style.left = leftDistance + '%';
		}

		curIdx++;

		if (curIdx >= itemSize) {
			setTimeout(resetPromotionPos, 0);
			
			function resetPromotionPos() {
				for (var i = 0; i < itemSize; i++) {
					promotionList[i].style.transitionDuration = '0s';
					promotionList[i].style.left = '0';
				}
				for (var i = 0; i < itemSize; i++) {
					promotionList[i].style.transitionDuration = '1s';
				}
				
				curIdx = 0;
				leftDistance = 0;
			}
		}
	}
}

function loadPromotionsCallback(responseData) {
	var itemCount = responseData.promotionCount;
	var promotionList = responseData.promotionList;

	var template = document.querySelector('#promotionItem').innerHTML;
	var resultHtml = '';
	for (var i = 0; i < promotionList.length; i++) {
		resultHtml += template
							.replace('{promotionImageUrl}', promotionList[i].productImageUrl)
							.replace('{promotionImageUrl}', promotionList[i].productImageUrl);
	}

	if (promotionList.length > 0) {
		resultHtml += template
							.replace('{promotionImageUrl}', promotionList[0].productImageUrl)
							.replace('{promotionImageUrl}', promotionList[0].productImageUrl);
	}

	document.querySelector('ul.visual_img').innerHTML = resultHtml;

	setPromotionMove();
}

function loadProductsCallback(responseData) {
	currentStart += 4;
	
	var itemCount = responseData.productCount;
	var productList = responseData.productList;
	var template = document.querySelector('#productItem').innerText;
	var resultHtml = new Array(2);
	resultHtml[0] = '';
	resultHtml[1] = '';
	for (var i = 0; i < productList.length; i++) {
		resultHtml[i % 2] += template
										.replace('{productImageUrl}', productList[i].productImageUrl)
										.replace('{description}', productList[i].productDescription)
										.replace('{description}', productList[i].productDescription)
										.replace('{id}', productList[i].displayInfoId)
										.replace('{placeName}', productList[i].placeName)
										.replace('{content}', productList[i].productContent);
	}
	var containers = document.querySelectorAll('.lst_event_box');
	containers[0].innerHTML += resultHtml[0];
	containers[1].innerHTML += resultHtml[1];

	var moreProductBtn = document.querySelector('.btn');
	if (itemCount <= currentStart) {
		moreProductBtn.style.display = 'none';
	} else {
		moreProductBtn.style.display = 'initial';
	}

	document.querySelector('.event_lst_txt>span').innerText = itemCount + '개';
}

function mapProductParameters(categoryId, start) {
	return 'products?categoryId=' + categoryId + '&start=' + start;
}

function setTabClickEvent() {	
	document.querySelector('ul.event_tab_lst').addEventListener('click',function(btnEvent) {

		var selectedTab = event.target;
		
		if (selectedTab.tagName === 'SPAN') {
			selectedTab = selectedTab.parentElement;
		}

		if (selectedTab.tagName === 'A') {
			var categoryId = selectedTab.parentElement.getAttribute('data-category');
			
			if (categoryId != currentCategory) {
				currentCategory = categoryId;
				currentStart = 0;

				document.querySelectorAll('a.anchor').forEach(element=>element.classList.remove('active'));
				selectedTab.classList.add('active');

				var containers = document.querySelectorAll('.lst_event_box');
				containers[0].innerHTML = '';
				containers[1].innerHTML = '';

				requestAjax(loadProductsCallback,mapProductParameters(currentCategory,currentStart));
			}
		}
	});
}

document.addEventListener('DOMContentLoaded', function() {
	//페이지 첫 로딩시 할 일

	//1. 카테고리 목록 가져오기
	requestAjax(loadCategoriesCallback, 'categories');

	//2. 상품 목록 가져오기
	requestAjax(loadProductsCallback, mapProductParameters(0, 0));

	//3. promotion 가져오기
	requestAjax(loadPromotionsCallback, 'promotions');

	//4. 더보기 버튼 event 등록
	document.querySelector('.btn').addEventListener('click',evt=>requestAjax(loadProductsCallback, mapProductParameters(currentCategory, currentStart)));

	//5. 탭 변경 event 등록
	setTabClickEvent();
});
