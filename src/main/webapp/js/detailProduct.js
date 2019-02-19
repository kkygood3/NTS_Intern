let displayInfoId = document.querySelector('#reservation').dataset.id;

function loadDisplayInfoCallback(responseData) {
	let displayInfo = responseData.displayInfo;
	let comments = responseData.comments;

	// 타이틀 이미지
	loadSwipeImage(responseData.displayInfo);
	requestAjax(loadExtraImage,'api/products/' + displayInfo.displayInfoId + '/extraImage');

	// 상품 설명
	loadProductExplain(responseData);
	
	// 상세설명 영역 - 상단 코멘트
	loadComments(responseData);
	
	// 상세설명 영역 - 하단 상세정보, 오시는길
	loadInfoTab(displayInfo);
	
	// 펼쳐보기 및 닫기
	loadShowMoreButton();	
}

function requestAjax(callback, url){
	let ajaxReq = new XMLHttpRequest();
	ajaxReq.callback = callback;
	ajaxReq.addEventListener('load', function(evt) {
		this.callback(evt.target.response);
	});
	
	ajaxReq.open('GET', url);
	ajaxReq.responseType = 'json';
	ajaxReq.send()
}

function getUrlParameter(name) {
	let params = location.href.split('?')[1].split('&');
	for (let i = 0; i < params.length; i++) {
		let paramSplited = params[i].split('=');
		let paramName = paramSplited[0];
		let paramValue = paramSplited[1];

		if (paramName === name) {
			return paramValue;
		}
	}
}

function loadSwipeImage(displayInfo){
	// 상단 Swipe Image 배너 Template
	let swipeTemplate = document.querySelector('#swipeTemplate').innerText;
	let bindSwipeTemplate = Handlebars.compile(swipeTemplate);
	let swipeContainer = document.querySelector('ul.detail_swipe');
	
	// 메인 이미지 삽입
	swipeContainer.innerHTML += bindSwipeTemplate(displayInfo);

	document.querySelector('div.store_details>p.dsc').innerHTML = displayInfo.productContent;
}

function loadExtraImage(responseData){
	// 상단 Swipe Image 배너 Template
	let swipeTemplate = document.querySelector('#swipeTemplate').innerText;
	let bindSwipeTemplate = Handlebars.compile(swipeTemplate);
	let swipeContainer = document.querySelector('ul.detail_swipe');
	
	// Swipe 페이지 수, 총량 표시
	let swipePage = document.querySelector('.figure_pagination').querySelector('.num');
	let swipeAmount = document.querySelector('.figure_pagination').querySelector('.off>span');
	
	// Swipe 이미지 좌우의 버튼
	let swipeLeftBtn = document.querySelector('.ico_arr6_lt');
	let swipeRightBtn = document.querySelector('.ico_arr6_rt');

	// 엑스트라 이미지가 없을 경우
	if (responseData.productImage == "none") {
		swipeAmount.innerText = '1';
		
		document.querySelector('.ico_arr6_lt').style.display = 'none';
		document.querySelector('.ico_arr6_rt').style.display = 'none';

		swipeLeftBtn.style.display = 'none';
		swipeRightBtn.style.display = 'none';
		
		return;
	}
	
	let extraImageInformation = responseData;
	if(extraImageInformation){		
		let firstItem = '<li class="item" style="width: 414px;">'+document.querySelector('ul.detail_swipe>.item').innerHTML+'</li>';
		let secondItem = bindSwipeTemplate(extraImageInformation);
		
		// 2 - 1 - 2 - 1 으로 배치해서 가운데 두개 이미지에서만 컨트롤 할 수 있게 한다.
		// 가장자리 두 이미지 상태에서는 애니메이션 없이 가운데의 같은 이미지로 이동한다.
		swipeContainer.innerHTML = secondItem + firstItem + secondItem + firstItem ;

		let swipeItems = swipeContainer.querySelectorAll('ul.detail_swipe>.item');
				
		// 시작점을 두번째 자리의 1로 변경
		swipeItems.forEach(item => item.style.left = '-100%');
		
		let eventContainer = document.querySelector('.group_visual');
		
		let currentPage = 1;
		let currentLeft = -100;
		// 화살표에 클릭이벤트 추가
		function arrowEventHandler(evt){
			
			let clickedBtn = evt.target;
			
			let isLeftBtnClicked = clickedBtn.classList.contains('ico_arr6_lt');
			let isRightBtnClicked = clickedBtn.classList.contains('ico_arr6_rt');
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
	}
}

function loadProductExplain(responseData) {
	document.querySelector('div.store_details>p.dsc').innerHTML = responseData.displayInfo.productContent;
}

function loadComments(responseData){
	displayInfo = responseData.displayInfo;
	displayComment = responseData.comments;
	
	let commentCount = responseData.comments.length;
	let averageScore = responseData.averageScore;
	
	// 코멘트 템플릿
	let commentTemplate = document.querySelector('#commentItemTemplate').innerText;
	let bindCommentTemplate = Handlebars.compile(commentTemplate);
	
	let commentContainer = document.querySelector('ul.list_short_review');
	
	for(let i = 0 ; i < 3 && i < commentCount; i++){
		displayComment[i].reservationEmail = displayComment[i].reservationEmail.substring(0,4) + "****";
		commentContainer.innerHTML += bindCommentTemplate(displayComment[i]);	
	}
	
	// 별점 그래프 및 코멘트 평균 점수
	document.querySelector('em.graph_value').style.width = (averageScore * 20) + '%';
	document.querySelector('.text_value>span').innerText = averageScore.toFixed(1);
	
	// 코멘트 개수
	document.querySelector('span.join_count>em.green').innerText = commentCount+'건';
}

function loadInfoTab(displayInfo){
	// 하단 상세설명 - 상세정보의 설명
	document.querySelector('p.in_dsc').innerText = displayInfo.productContent;

	// 하단 상세설명 - 오시는 길
	document.querySelector('.store_map').setAttribute('src',displayInfo.displayInfoImage);
	
	document.querySelector('.store_name').innerText = displayInfo.placeName;
	
	let addressWrap = document.querySelector('.store_addr_wrap').querySelectorAll('p');
	addressWrap[0].innerText = displayInfo.placeStreet;
	addressWrap[1].querySelectorAll('span')[1].innerText = displayInfo.placeLot;
	addressWrap[2].innerText = displayInfo.placeName;
	
	let telephoneArea = document.querySelector('.store_tel');
	telephoneArea.setAttribute('href',displayInfo.telephone);
	telephoneArea.innerText = displayInfo.telephone;
	
	let detailTab = document.querySelector('ul.info_tab_lst>._detail');
	let detailBody = document.querySelector('.detail_area_wrap');
	
	let pathTab = document.querySelector('ul.info_tab_lst>._path');
	let pathBody = document.querySelector('.detail_location');

	// 하단 상세설명 클릭 이벤트
	let currentTab = 1;
	document.querySelector('ul.info_tab_lst').addEventListener('click',function(evt){
		
		let clickedTab = evt.target;
		if(clickedTab.tagName === 'SPAN'){
			clickedTab = clickedTab.parentElement.parentElement;
		} else if(clickedTab.tagName === 'A'){
			clickedTab = clickedTab.parentElement;
		}
		
		if(currentTab == 2 && clickedTab.className.indexOf('_detail') != -1){
			currentTab = 1;
			
			pathTab.classList.remove('active');
			pathTab.firstElementChild.classList.remove('active');
			
			pathBody.classList.add('hide');
			detailBody.classList.remove('hide');
			
			detailTab.classList.add('active');
			detailTab.firstElementChild.classList.add('active');
			
		} else if(currentTab == 1 && clickedTab.className.indexOf('_path') != -1){
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

function loadShowMoreButton(){
	let unfoldBtn = document.querySelector('a._open');	
	let foldBtn = document.querySelector('a._close');
	let foldingText = document.querySelector('div.store_details');
	let textArea = document.querySelector('p.dsc');

	if(textArea.scrollHeight > textArea.clientHeight){
		document.querySelector('div.section_store_details').addEventListener('click',function(evt){
			evt.preventDefault();
			let clickedTag  = evt.target;
			
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

document.addEventListener('DOMContentLoaded', function() {
	requestAjax(loadDisplayInfoCallback, 'api/products/' + getUrlParameter('displayInfoId'));
});