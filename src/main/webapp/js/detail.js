function initDetailBtn(){
	var unfoldBtn = document.querySelector('a._open');	
	var foldBtn = document.querySelector('a._close');
	var foldingText = document.querySelector('div.store_details');
	var textArea = document.querySelector('p.dsc');

	if(textArea.scrollHeight > textArea.clientHeight){
		document.querySelector('div.section_store_details').addEventListener('click',function(evt){
			evt.preventDefault();
			var clickedTag  = evt.target;
			
			if(clickedTag.tagName == 'SPAN' || clickedTag.tagName == 'I'){
				clickedTag = clickedTag.parentElement;
			} 
			
			if(clickedTag.className === 'bk_more _open'){
				
				foldingText.classList.remove('close3');
				
				unfoldBtn.style.display = 'none';
				foldBtn.style.display = '';
				
			}else if(clickedTag.className === 'bk_more _close'){
				
				foldingText.classList.add('close3');
				
				unfoldBtn.style.display = '';
				foldBtn.style.display = 'none';
				
			}
		});
	} else {
		unfoldBtn.style.display = 'none';
	}
}

function initSwipeImage(displayInfo){
	
	// 상단 Swipe Image 배너 Template
	var swipeTemplate = document.querySelector('#swipeTemplate').innerText;
	var bindSwipeTemplate = Handlebars.compile(swipeTemplate);
	var swipeContainer = document.querySelector('ul.detail_swipe');
	
	// 이미지가 1개인 경우
	swipeContainer.innerHTML += bindSwipeTemplate(displayInfo);
	
	document.querySelector('div.store_details>p.dsc').innerHTML = displayInfo.productContent;
}

function initComment(displayInfo, displayComment){	
	var commentCount = displayInfo.commentCount;
	var averageScore = displayInfo.averageScore;
	
	// Comment Template
	var commentTemplate = document.querySelector('#commentItemTemplate').innerText;
	var bindCommentTemplate = Handlebars.compile(commentTemplate);
	
	var commentContainer = document.querySelector('ul.list_short_review');
	
	for(var i = 0 ; i < 3 && i < commentCount; i++){
		commentContainer.innerHTML += bindCommentTemplate(displayComment[i]);	
	}
	
	// 별점 그래프, 숫자 조정
	document.querySelector('em.graph_value').style.width = (averageScore * 20) + '%';
	document.querySelector('.text_value>span').innerText = averageScore;
	
	// 우측 상단의 Comment 갯수
	document.querySelector('span.join_count>em.green').innerText = commentCount+'건';
	
	
	// Comment 더보기 버튼
	var reviewMoreBtn = document.querySelector('a.btn_review_more');
	if(commentCount > 3){
		reviewMoreBtn.setAttribute('href','review?id='+displayInfo.displayInfoId);
	} else{
		reviewMoreBtn.style.display = 'none';
	}
}

function initInfoTab(displayInfo){
	
	// [소개]란의 글
	document.querySelector('p.in_dsc').innerText = displayInfo.productContent;
	
	// [오시는 길] - 이미지
	document.querySelector('.store_map').setAttribute('src',displayInfo.displayInfoImage);
	
	// [오시는 길] - 장소 명
	document.querySelector('.store_name').innerText = displayInfo.placeName;
	
	// [오시는 길] - 주소
	var addressWrap = document.querySelector('.store_addr_wrap').querySelectorAll('p');
	addressWrap[0].innerText = displayInfo.placeStreet;
	addressWrap[1].querySelectorAll('span')[1].innerText = displayInfo.placeLot;
	addressWrap[2].innerText = displayInfo.placeName;
	
	// [오시는 길] - 전화번호
	var telephoneArea = document.querySelector('.store_tel');
	telephoneArea.setAttribute('href',displayInfo.telephone);
	telephoneArea.innerText = displayInfo.telephone;
	
	// 상세 정보, 오시는 길 전환 탭
	var detailTab = document.querySelector('ul.info_tab_lst>._detail');
	var detailBody = document.querySelector('.detail_area_wrap');
	
	var pathTab = document.querySelector('ul.info_tab_lst>._path');
	var pathBody = document.querySelector('.detail_location');
	
	var currentTab = 1;
	// 상세정보, 오시는 길 클릭 이벤트
	document.querySelector('ul.info_tab_lst').addEventListener('click',function(evt){
		
		var clickedTab = evt.target;
		if(clickedTab.tagName === 'SPAN'){
			clickedTab = clickedTab.parentElement.parentElement;
		}else if(clickedTab.tagName === 'A'){
			clickedTab = clickedTab.parentElement;
		}
		
		// 첫번째 탭을 클릭했을 때
		if(currentTab == 2 && clickedTab.className.indexOf('_detail') != -1){
			currentTab = 1;
			
			pathTab.classList.remove('active');
			pathTab.firstElementChild.classList.remove('active');
			
			pathBody.classList.add('hide');
			detailBody.classList.remove('hide');
			
			detailTab.classList.add('active');
			detailTab.firstElementChild.classList.add('active');
			
		}else if(currentTab == 1 && clickedTab.className.indexOf('_path') != -1){
			currentTab = 2;
			detailTab.classList.remove('active');
			detailTab.firstElementChild.classList.remove('active');
			
			detailBody.classList.add('hide');
			pathBody.classList.remove('hide');
			
			
			pathTab.classList.add('active');
			pathTab.firstElementChild.classList.add('active');
		}
	
	});
}

function loadExtraImageCallback(responseData){
	var extraImageInformation = responseData.productImage;
	
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
	
	if(extraImageInformation){		
		var firstItem = '<li class="item" style="width: 414px;">'+document.querySelector('ul.detail_swipe>.item').innerHTML+'</li>';
		var secondItem = bindSwipeTemplate(extraImageInformation);
		
		// 2 - 1 - 2 - 1 으로 배치해서 가운데 두개 이미지에서만 컨트롤 할 수 있게 한다.
		// 가장자리 두 이미지 상태에서는 애니메이션 없이 가운데의 같은 이미지로 이동한다.
		swipeContainer.innerHTML = secondItem + firstItem + secondItem + firstItem ;

		var swipeItems = swipeContainer.querySelectorAll('ul.detail_swipe>.item');
				
		// 시작점을 두번째 자리의 1로 변경
		swipeItems.forEach(item => item.style.left = '-100%');
		
		var eventContainer = document.querySelector('.group_visual');
		
		var currentPage = 1;
		var currentLeft = -100;
		// 화살표에 클릭이벤트 추가
		function arrowEventHandler(evt){
			
			var clickedBtn = evt.target;
			
			var isLeftBtnClicked = clickedBtn.classList.contains('ico_arr6_lt');
			var isRightBtnClicked = clickedBtn.classList.contains('ico_arr6_rt');
			if(isLeftBtnClicked | isRightBtnClicked){
				eventContainer.removeEventListener('click',arrowEventHandler);
				if(isLeftBtnClicked){// 왼쪽 클릭
					if(swipePage.innerText === '1'){
						// 1번에서 2번으로
						swipePage.innerText = '2';
					}else{
						// 2번에서 1번으로
						swipePage.innerText = '1';
					}
					currentLeft += 100;
					currentPage--;
					swipeItems.forEach(item => item.style.left = currentLeft+'%');
				} else{// 오른쪽 클릭
					if(swipePage.innerText === '1'){
						// 1번에서 2번으로
						swipePage.innerText = '2';
					}else{
						// 2번에서 1번으로
						swipePage.innerText = '1';
					}
					currentLeft -= 100;
					currentPage++;
					swipeItems.forEach(item => item.style.left = currentLeft+'%');
				}

				setTimeout(function(){
					// currentPage가 0이나 3이면
					// 애니메이션 끄고 0->2 3->1로 옮긴다
					if(currentPage == 0){
						// 애니메이션 OFF
						swipeItems.forEach(item => item.style.transitionDuration = '0s');
						
						// 시작점을 두번째 자리의 1로 변경
						swipeItems.forEach(item => item.style.left = '-200%');
						
						currentLeft = -200;
						currentPage = 2;
					}else if(currentPage == 3){
						// 애니메이션 OFF
						swipeItems.forEach(item => item.style.transitionDuration = '0s');
						
						// 시작점을 두번째 자리의 1로 변경
						swipeItems.forEach(item => item.style.left = '-100%');
						
						currentLeft = -100;
						currentPage = 1;
					}
					// 애니메이션 ON
					setTimeout(()=>{
						swipeItems.forEach(item => item.style.transitionDuration = '1s');
						// 버튼이벤트 ON
						eventContainer.addEventListener('click',arrowEventHandler);
						},50);
				},1100);
			}
		}
		
		eventContainer.addEventListener('click',arrowEventHandler);
	} else {
		swipeAmount.innerText = '1';
		
		document.querySelector('.ico_arr6_lt').style.display = 'none';
		document.querySelector('.ico_arr6_rt').style.display = 'none';

		swipeLeftBtn.style.display = 'none';
		swipeRightBtn.style.display = 'none';
	}
}

function setCommonInfo(displayComment,displayInfo){
	displayComment[0].commentCount = displayInfo.commentCount;
	displayComment[0].averageScore = displayInfo.averageScore;
}

function loadDisplayInfoCallback(responseData) {
	var displayComment = responseData.detailDisplay.detailComment;
	var displayInfo = responseData.detailDisplay.detailDisplayInfo;

	// SwipeImage 설정
	initSwipeImage(displayInfo);
	
	// 맨 아래의 상세정보, 오시는길 탭 설정
	initInfoTab(displayInfo);
	
	// Comment 설정
	initComment(displayInfo, displayComment);
	
	// 펼쳐보기, 접기 버튼
	initDetailBtn();
	
	requestAjax(loadExtraImageCallback,'api/products/'+displayInfo.displayInfoId+'/extra');
}

document.addEventListener('DOMContentLoaded', function() {
	requestAjax(loadDisplayInfoCallback, 'api/products/' + getUrlParameter('id'));
});