// 상품 설명이 3줄을 넘어갈 때 펼치기, 접기 버튼 설정
function initDetailBtn(displayInfo){
	var unfoldBtn = document.querySelector('a._open');	
	var foldBtn = document.querySelector('a._close');
	var foldingText = document.querySelector('div.store_details');
	
	var detailEventContainer = document.querySelector('div.section_store_details');
	var textArea = document.querySelector('p.dsc');
	textArea.innerText = displayInfo.productContent;
	
	if(textArea.scrollHeight > textArea.clientHeight){
		detailEventContainer.addEventListener('click',function(evt){
			evt.preventDefault();
			var clickedTag  = evt.target;
			
			var isClickedChildElements = (clickedTag.tagName == 'SPAN' || clickedTag.tagName == 'I');
			
			if(isClickedChildElements){
				clickedTag = clickedTag.parentElement;
			} 
			
			var isClickedUnfold = (clickedTag.className === 'bk_more _open');
			var isClickedFold = (clickedTag.className === 'bk_more _close');
			
			if(isClickedUnfold){
				
				foldingText.classList.remove('close3');
				
				unfoldBtn.style.display = 'none';
				foldBtn.style.display = '';
				
			}else if(isClickedFold){
				
				foldingText.classList.add('close3');
				
				unfoldBtn.style.display = '';
				foldBtn.style.display = 'none';
				
			}
		});
	} else {
		unfoldBtn.style.display = 'none';
	}
}

/**
 * Swipe image 첫장을 등록
 * @param displayInfo - 해당 상품의 정보
 */
function initSwipeImage(displayInfo){
	// 상단 Swipe Image 배너 Template
	var swipeTemplate = document.querySelector('#swipeTemplate').innerText;
	var bindSwipeTemplate = Handlebars.compile(swipeTemplate);
	var swipeContainer = document.querySelector('ul.detail_swipe');
	
	swipeContainer.innerHTML += bindSwipeTemplate(displayInfo);
}

/**
 * 댓글 정보 출력
 * @param displayInfo - 해당 상품의 정보
 * @param displayComment - 상품을 평가한 댓글(Array)
 */
function initComment(displayInfo, displayComment){
	// 페이지에 출력할 Comment 갯수
	var COMMENT_PAGING_LIMIT = 3;
	
	var commentCount = displayInfo.commentCount;
	var averageScore = displayInfo.averageScore;
	
	// 평균 점수 그래프, 숫자
	var scoreGraph = document.querySelector('em.graph_value');
	var scoreTextArea = document.querySelector('.text_value>span');
	// Comment 수, Comment 더보기 버튼
	var commentCountTextArea = document.querySelector('span.join_count>em.green');
	var moreCommentBtn = document.querySelector('a.btn_review_more');
	
	// Comment Template
	var commentTemplate = document.querySelector('#commentItemTemplate').innerText;
	var bindCommentTemplate = Handlebars.compile(commentTemplate);
	var commentContainer = document.querySelector('ul.list_short_review');
	
	for(var i = 0 ; i < 3 && i < commentCount; i++){
		commentContainer.innerHTML += bindCommentTemplate(displayComment[i]);	
	}
	
	scoreGraph.style.width = (averageScore * 20) + '%';
	scoreTextArea.innerText = averageScore;
	
	// 우측 상단의 Comment 갯수
	commentCountTextArea.innerText = commentCount+'건';
	
	/**
	 * 전체 Comment가 화면에 출력한 갯수보다 크다면 review페이지로 리다이렉트 링크 삽입
	 * 작거나 같다면 버튼을 숨김
	 */
	if(commentCount > COMMENT_PAGING_LIMIT){
		moreCommentBtn.setAttribute('href','review?id='+displayInfo.displayInfoId);
	} else{
		moreCommentBtn.style.display = 'none';
	}
}
/**
 * 페이지 하단의 상품 소개, 오시는 길 등의 정보를 출력
 * @param displayInfo - 해당 상품의 정보
 */
function initInfoTab(displayInfo){
	// [상세 정보] 탭의 소개
	var descriptionTextArea = document.querySelector('p.in_dsc');
	
	/**
	 * [오시는 길] 탭의
	 * 이미지, 장소, 주소, 전화번호
	 */
	var imageArea = document.querySelector('.store_map');
	var placeTextArea = document.querySelector('.store_name');
	var addressWrap = document.querySelectorAll('.store_addr_wrap p');
	var telephoneTextArea = document.querySelector('.store_tel');
	
	// 상세 정보, 오시는 길 탭 변경 버튼
	var detailTabBtn = document.querySelector('ul.info_tab_lst>._detail');
	var pathTabBtn = document.querySelector('ul.info_tab_lst>._path');
	
	// 상세정보, 오시는 길 탭 내용
	var detailTabContainer = document.querySelector('.detail_area_wrap');
	var pathTabContainer = document.querySelector('.detail_location');
	
	descriptionTextArea.innerText = displayInfo.productContent;
	imageArea.setAttribute('src',displayInfo.displayInfoImage);
	placeTextArea.innerText = displayInfo.placeName;
	
	addressWrap[0].innerText = displayInfo.placeStreet;
	addressWrap[1].querySelectorAll('span')[1].innerText = displayInfo.placeLot;
	addressWrap[2].innerText = displayInfo.placeName;
	
	telephoneTextArea.setAttribute('href',displayInfo.telephone);
	telephoneTextArea.innerText = displayInfo.telephone;
	
	var currentTab = 1;
	// 상세정보, 오시는 길 클릭 이벤트
	document.querySelector('ul.info_tab_lst').addEventListener('click',function(evt){
		var clickedTab = evt.target;
		
		if(clickedTab.tagName === 'SPAN'){
			clickedTab = clickedTab.parentElement;	
		}
		
		if(clickedTab.tagName === 'A'){
			clickedTab = clickedTab.parentElement;
		}
		
		var isClickedDetailTab = (currentTab == 2 && clickedTab.className.indexOf('_detail') != -1);
		var isClickedPathTab = (currentTab == 1 && clickedTab.className.indexOf('_path') != -1);
		
		if(isClickedDetailTab){
			currentTab = 1;
			
			pathTabBtn.classList.remove('active');
			pathTabBtn.firstElementChild.classList.remove('active');
			pathTabContainer.classList.add('hide');
			
			detailTabContainer.classList.remove('hide');
			detailTabBtn.classList.add('active');
			detailTabBtn.firstElementChild.classList.add('active');
			
		}else if(isClickedPathTab){
			currentTab = 2;
			
			detailTabBtn.classList.remove('active');
			detailTabBtn.firstElementChild.classList.remove('active');
			detailTabContainer.classList.add('hide');
			
			pathTabContainer.classList.remove('hide');
			pathTabBtn.classList.add('active');
			pathTabBtn.firstElementChild.classList.add('active');
		}
	
	});
}

/**
 * 해당 상품에 추가로 표시할 이미지가 있을때 swipe에 등록
 * @param response - 추가 이미지 정보
 */
function loadExtraImageCallback(response){
	var extraImage = response.extraImageResponse;
	
	// 상단 Swipe Image 배너 Template
	var swipeTemplate = document.querySelector('#swipeTemplate').innerText;
	var bindSwipeTemplate = Handlebars.compile(swipeTemplate);
	var swipeContainer = document.querySelector('ul.detail_swipe');
	
	// Swipe 페이지 수, 총량 표시
	var swipePage = document.querySelector('.figure_pagination').querySelector('.num');
	var swipeAmount = document.querySelector('.figure_pagination').querySelector('.off>span');
	
	// Swipe 이미지 좌우의 버튼
	var swipeLeftBtn = document.querySelector('.ico_arr6_lt');
	var swipeRightBtn = document.querySelector('.ico_arr6_rt');
	
	if(extraImage){		
		var firstItem = '<li class="item" style="width: 414px;">'+document.querySelector('ul.detail_swipe>.item').innerHTML+'</li>';
		var secondItem = bindSwipeTemplate(extraImage);
		
		// 2 - 1 - 2 - 1 으로 배치해서 가운데 두개 이미지에서만 컨트롤 할 수 있게 한다.
		// 가장자리 두 이미지 상태에서는 애니메이션 없이 가운데의 같은 이미지로 이동한다.
		swipeContainer.innerHTML = secondItem + firstItem + secondItem + firstItem ;

		var swipeItems = swipeContainer.querySelectorAll('ul.detail_swipe>.item');
				
		// 시작점을 두번째 자리의 1로 변경
		swipeItems.forEach(item => item.style.left = '-100%');
		
		var eventContainer = document.querySelector('.group_visual');
		
		var currentPage = 1;
		var currentLeft = -100;
		
		eventContainer.addEventListener('click',arrowEventHandler);
		
		// 화살표에 클릭이벤트 등록
		function arrowEventHandler(evt){
			
			var clickedBtn = evt.target;
			
			var isLeftBtnClicked = clickedBtn.classList.contains('ico_arr6_lt');
			var isRightBtnClicked = clickedBtn.classList.contains('ico_arr6_rt');
			
			if(isLeftBtnClicked | isRightBtnClicked){
				//애니메이션 실행 도중에 새로운 버튼 이벤트를 받지 않음
				eventContainer.removeEventListener('click',arrowEventHandler);
				
				if(isLeftBtnClicked){
					currentLeft += 100;
					currentPage--;
				} else{
					currentLeft -= 100;
					currentPage++;
				}
				swipeItems.forEach(item => item.style.left = currentLeft+'%');
				
				if(swipePage.innerText === '1'){
					swipePage.innerText = '2';
				}else{
					swipePage.innerText = '1';
				}

				setTimeout(function(){
					var isLeftEdge = (currentPage == 0);
					var isRightEdge = (currentPage == 3);
					
					if(isLeftEdge || isRightEdge){
						// 애니메이션 OFF
						swipeItems.forEach(item => item.style.transitionDuration = '0s');
						
						if(isLeftEdge){
							// 시작점을 두번째 자리의 1로 변경
							swipeItems.forEach(item => item.style.left = '-200%');
							
							currentLeft = -200;
							currentPage = 2;
						}else if(isRightEdge){
							// 시작점을 두번째 자리의 1로 변경
							swipeItems.forEach(item => item.style.left = '-100%');
							
							currentLeft = -100;
							currentPage = 1;
						}
						
						// 애니메이션 ON, 버튼 이벤트 재등록
						setTimeout(()=>{
							swipeItems.forEach(item => item.style.transitionDuration = '1s');
							eventContainer.addEventListener('click',arrowEventHandler);
							},50);
					}
				},1100);
			}
		}
	} else {
		swipeAmount.innerText = '1';

		swipeLeftBtn.style.display = 'none';
		swipeRightBtn.style.display = 'none';
	}
}

/**
 * 예매하기 버튼을 눌렀을 때 상품에 해당하는 예약페이지로 이동
 * @param displayInfoId
 */
function initReserveBtn(displayInfoId){
	var reserveBtn = document.querySelector('.bk_btn');
	reserveBtn.addEventListener('click',()=>location.href = 'reserve?id='+displayInfoId);
}

function setCommonInfo(displayComment,displayInfo){
	displayComment[0].commentCount = displayInfo.commentCount;
	displayComment[0].averageScore = displayInfo.averageScore;
}

function loadDisplayInfoCallback(response) {
	var displayComment = response.detailResponse.detailComment;
	var displayInfo = response.detailResponse.detailDisplayInfo;
	
	// 펼쳐보기, 접기 버튼
	initDetailBtn(displayInfo);
	
	// SwipeImage 설정
	initSwipeImage(displayInfo);
	
	// 예매하기 버튼 이벤트 등록
	initReserveBtn(displayInfo.displayInfoId);
	
	// 맨 아래의 상세정보, 오시는길 탭 설정
	initInfoTab(displayInfo);
	
	// Comment 설정
	initComment(displayInfo, displayComment);
	
	requestAjax(loadExtraImageCallback,'api/products/'+displayInfo.displayInfoId+'/extra');
}

document.addEventListener('DOMContentLoaded', function() {
	requestAjax(loadDisplayInfoCallback, 'api/products/' + getUrlParameter('id'));
});