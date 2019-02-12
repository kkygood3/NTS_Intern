document.addEventListener('DOMContentLoaded', function() {
	//페이지 첫 로딩시 할 일

	//1. 카테고리 목록 가져오기
	requestAjax(loadCategoriesCallback, 'categories');

	//2. 상품 목록 가져오기
	requestAjax(loadProductsCallback, mapProductParameters(0, 0, productLimit));

	//3. promotion 가져오기
	requestAjax(loadPromotionsCallback, 'promotions');

	//4. 더보기 버튼 event 등록
	document.querySelector('.btn').addEventListener('click',evt=>requestAjax(loadProductsCallback, mapProductParameters(currentCategory, currentStart, productLimit)));

	//5. 탭 변경 event 등록
	setTabClickEvent();
});

var currentStart = 0;
var currentCategory = 0;
const productLimit = 4;

/**
 * api의 Ajax Reqeust를 수행
 * @param callback 호출 함수
 * @param url 호출 URL
 */
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

/**
 * 페이지 상단의 프로모션을 가져오는 함수
 * @param responseData Ajax 통신을 통해 불러온 promotion 관련 JSON 데이터
 */
function loadPromotionsCallback(responseData) {
	var items = responseData.items;

	var template = document.querySelector('#promotionItem').innerHTML;
	var resultHtml = '';
	for (var i = 0; i < items.length; i++) {
		resultHtml += template
							.replace('{promotionImageUrl}', items[i].productImageUrl)
							.replace('{promotionImageUrl}', items[i].productImageUrl);
	}

	if (items.length > 0) {
		resultHtml += template
							.replace('{promotionImageUrl}', items[0].productImageUrl)
							.replace('{promotionImageUrl}', items[0].productImageUrl);
	}

	document.querySelector('ul.visual_img').innerHTML = resultHtml;

	setPromotionMove();
}

/**
 * 페이지 중단의 카테고리를 가져오는 함수
 * @param responseData Ajax 통신을 통해 불러온 category 관련 JSON 데이터
 */
function loadCategoriesCallback(responseData){
	var items = responseData.items;
	
	var template = document.querySelector('#categoryItem').innerHTML;
	var resultHtml = '';
	for(var i = 0 ; i < items.length; i++){
		resultHtml += template
							.replace('{name}',items[i].name)
							.replace('{id}',items[i].id);
	}
	
	document.querySelector('ul.event_tab_lst').innerHTML += resultHtml;
}

/**
 * 페이지 하단의 상품을 가져오는 함수
 * @param responseData Ajax 통신을 통해 불러온 product 관련 JSON 데이터
 */
function loadProductsCallback(responseData) {
	currentStart += 4;
	
	var itemCount = responseData.totalCount;
	var items = responseData.items;
	var template = document.querySelector('#productItem').innerText;
	var resultHtml = new Array(2);
	resultHtml[0] = '';
	resultHtml[1] = '';
	for (var i = 0; i < items.length; i++) {
		resultHtml[i % 2] += template
										.replace('{productImageUrl}', items[i].productImageUrl)
										.replace('{description}', items[i].productDescription)
										.replace('{description}', items[i].productDescription)
										.replace('{id}', items[i].displayInfoId)
										.replace('{placeName}', items[i].placeName)
										.replace('{content}', items[i].productContent);
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

/**
 * URL에 들어가는 상품 관련 parameter를 설정하는 함수
 * @param categoryId 상품들
 * @param start 페이징 쿼리로 가져오는 시작 레코드
 * @param limit 페이징 쿼리에서 레코드 개수를 제한
 * @returns API Controller 레이어에서 사용되는 URL
 */
function mapProductParameters(categoryId, start, limit) {
	return 'products?categoryId=' + categoryId + '&start=' + start + '&limit=' + limit;
}

/**
 * 현재 카테고리에서 다른 카테고리를 클릭하는 이벤트를 통해, 해당 카테고리의 상품을 갱신하는 함수
 */
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

				requestAjax(loadProductsCallback,mapProductParameters(currentCategory,currentStart, productLimit));
			}
		}
	});
}

/**
 * 프로모션의 슬라이드를 움직이는 함수
 */
function setPromotionMove() {
	var items = document.querySelectorAll('.visual_img>.item');
	var leftDistance = 0;
	var itemSize = items.length;
	var currentIndex = 0;
	
	setInterval(() => {
		leftDistance -= 100;

		for (var i = 0; i < itemSize; ++i) {
			items[i].style.left = leftDistance + '%';
		}

		currentIndex++;
		if (currentIndex < itemSize) {
			return;
		}
		
		for (var i = 0; i < itemSize; i++) {
			items[i].style.transitionDuration = '0s';
			items[i].style.left = '0';
		}
		for (var i = 0; i < itemSize; i++) {
			items[i].style.transitionDuration = '1s';
		}
		
		currentIndex = 0;
		leftDistance = 0;
	}, 4000);
}
