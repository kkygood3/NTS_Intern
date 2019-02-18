// 다음번 가져올 product 시작점. 페이징 처리에 사용.
var productStart = 0;
// 사용자가 선택중인 categoryId. 0일때 카테고리 구별 없음
var currentCategory = 0;

/**
 * /api/categories 응답 결과로 home 페이지 상단의 카테고리 item 출력
 * 
 * @param ajax
 *            JSON response
 */
function loadCategoriesCallback(response){
	var categoryList = response.categoryList;
	var categoryContainer = document.querySelector('ul.event_tab_lst');
	
	var template = document.querySelector('script#categoryItem').innerHTML;
	var resultHtml = '';
	for(var i = 0 ; i < categoryList.length; i++){
		resultHtml += template
							.replace('{name}',categoryList[i].name)
							.replace('{id}',categoryList[i].id);
	}
	
	categoryContainer.innerHTML += resultHtml;
}


// promotion 이미지에 무한 슬라이딩 효과를 적용
function setPromotionMove() {
	var bannerList = document.querySelectorAll('.visual_img>.item');
	var leftDistance = 0;
	var itemSize = bannerList.length;
	var curIdx = 0;
	
	setInterval(moveStep, 4000);

	// 4초에 한번 배너를 다음 페이지로 넘기는 역할을 하는 함수
	function moveStep(){
		
		// 모든 배너 이미지를 매 주기마다 -100% 이동
		leftDistance -= 100;

		for (var i = 0; i < bannerList.length; i++) {
			bannerList[i].style.left = leftDistance + '%';
		}

		curIdx++;
		/**
		 * 마지막장의 다음장은 미리 첫번째 이미지로 설정 if(마지막장을 넘어 갔을때)
		 */
		if (curIdx >= itemSize - 1) {
			
			// 마지막 +1에 위치한 첫번째 이미지를 출력하는 도중에, 모든 이미지를 왼쪽으로 이동
			setTimeout(resetPromotionPos, 1100);
			
			/**
			 * 마지막장에서 첫장으로 반복하기위해 복구하는 함수 모든 이미지의 transitionDuration을 0s로 초기화 ->
			 * 모든 이미지를 다시 시작점으로 이동 -> 다시 transitionDuration을 1s로 초기화
			 */
			function resetPromotionPos() {
				
				for (var i = 0; i < bannerList.length; i++) {
					bannerList[i].style.transitionDuration = '0s';
				}

				for (var i = 0; i < bannerList.length; i++) {
					bannerList[i].style.left = '0';
				}
				
				setTimeout(reattachTransition, 100);
				
				function reattachTransition() {
					for (var i = 0; i < bannerList.length; i++) {
						bannerList[i].style.transitionDuration = '1s';
					}
					curIdx = 0;
					leftDistance = 0;
				}
				
			}
			
		}
		
	}
}

/**
 * /api/promotions 응답 결과로 home 페이지 상단의 프로모션 이미지 item 출력
 * 
 * @param ajax
 *            JSON response
 */
function loadPromotionsCallback(response) {
	var itemCount = response.totalCount;
	var promotionList = response.promotionList;
	var promotionContainer = document.querySelector('ul.visual_img');
	
	var template = document.querySelector('script#promotionItem').innerHTML;
	var resultHtml = '';
	for (var i = 0; i < promotionList.length; i++) {
		resultHtml += template
							.replace('{promotionImageUrl}', promotionList[i].productImageUrl)
							.replace('{promotionImageUrl}', promotionList[i].productImageUrl)
							.replace('{productId}', promotionList[i].productId)
							.replace('{displayInfoId}', promotionList[i].displayInfoId);
	}

	// 무한 슬라이딩 효과를 위해 첫번째 장을 하나 더 삽입
	if (promotionList.length > 0) {
		resultHtml += template
							.replace('{promotionImageUrl}', promotionList[0].productImageUrl)
							.replace('{promotionImageUrl}', promotionList[0].productImageUrl)
							.replace('{productId}', promotionList[0].productId)
							.replace('{displayInfoId}', promotionList[0].displayInfoId);
	}

	promotionContainer.innerHTML = resultHtml;

	setPromotionMove();
}

/**
 * /api/products 응답 결과로 home 페이지 하단에 product item 출력.
 * 
 * @param ajax
 *            JSON response
 */
function loadProductsCallback(response) {
	// 한 페이지에 출력할 상품 개수
	const PAGING_LIMIT = 4;

	var itemCount = response.totalCount;
	var productList = response.productList;
	var productContainer = document.querySelectorAll('.lst_event_box');
	
	var productCountTextArea = document.querySelector('.event_lst_txt>span');
	
	var template = document.querySelector('script#productItem').innerText;
	var resultHtml = new Array(2);
	resultHtml[0] = '';
	resultHtml[1] = '';
	// [1], [2] section에 번갈아가면서 삽입
	for (var i = 0; i < productList.length; i++) {
		resultHtml[i % 2] += template
							.replace('{productImageUrl}', productList[i].productImageUrl)
							.replace('{description}', productList[i].productDescription)
							.replace('{description}', productList[i].productDescription)
							.replace('{id}', productList[i].displayInfoId)
							.replace('{placeName}', productList[i].placeName)
							.replace('{content}', productList[i].productContent)
							.replace('{productId}', productList[i].productId)
							.replace('{displayInfoId}', productList[i].displayInfoId);
	}
	productContainer[0].innerHTML += resultHtml[0];
	productContainer[1].innerHTML += resultHtml[1];
	productStart += PAGING_LIMIT;
	
	var moreProductBtn = document.querySelector('.btn');
	
	// 더이상 가져올 상품이 없으면 더보기 버튼 숨기기
	if (itemCount <= productStart) {
		moreProductBtn.style.display = 'none';
	} else {
		moreProductBtn.style.display = 'initial';
	}

	productCountTextArea.innerText = itemCount + '개';
}

function mapProductParameters(categoryId, start) {
	return 'api/products?categoryId=' + categoryId + '&start=' + start;
}

// 카테고리 탭을 변경하면 하단의 상품 목록을 바꿔서 출력하기위한 이벤트 등록
function initTabClickEvent() {
	document.querySelector('ul.event_tab_lst').addEventListener('click',function(btnEvent) {
		var selectedTab = event.target;
		
		if (selectedTab.tagName === 'SPAN') {
			selectedTab = selectedTab.parentElement;
		}

		if (selectedTab.tagName === 'A') {
			var categoryId = selectedTab.parentElement.getAttribute('data-category');
			
			// 현재 탭과 다른 탭을 클릭했을 때
			if (categoryId != currentCategory) {
				currentCategory = categoryId;
				productStart = 0;

				document.querySelectorAll('a.anchor').forEach(element=>element.classList.remove('active'));
				selectedTab.classList.add('active');

				var containers = document.querySelectorAll('.lst_event_box');
				containers[0].innerHTML = '';
				containers[1].innerHTML = '';

				requestAjax(loadProductsCallback,mapProductParameters(currentCategory,productStart));
			}
		}
	});
}

document.addEventListener('DOMContentLoaded', function() {
	// 페이지 첫 로딩시 할 일

	// 1. 카테고리 목록 가져오기
	requestAjax(loadCategoriesCallback, 'api/categories');

	// 2. 상품 목록 가져오기
	requestAjax(loadProductsCallback, mapProductParameters(0, 0));

	// 3. promotion 가져오기
	requestAjax(loadPromotionsCallback, 'api/promotions');

	// 4. 더보기 버튼 event 등록
	document.querySelector('.btn').addEventListener('click',evt=>requestAjax(loadProductsCallback, mapProductParameters(currentCategory, productStart)));

	// 5. 탭 변경 event 등록
	initTabClickEvent();
});