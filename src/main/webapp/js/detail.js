var currentTab = 1;
var MAX_SWIPE = 2;
var PERCENT_COEF = 20;

function requestAjax(callback, url) {
	var ajaxReq = new XMLHttpRequest();
	ajaxReq.callback = callback;
	ajaxReq.addEventListener('load', function(evt) {
		this.callback(evt.target.response);
	});

	ajaxReq.open('GET', url);
	ajaxReq.responseType = 'json';
	ajaxReq.send()
}


function convertDateFormat(date){
	var originDate = date.split(' ')[0];
	var originDateSplited = originDate.split('-');
	var resultDate = originDateSplited[0];
	
	if(originDateSplited[1].charAt(0) === '0'){
		originDateSplited[1] = originDateSplited[1].charAt(1); 
	}
	
	resultDate += '.'+originDateSplited[1];
	
	if(originDateSplited[2].charAt(0) === '0'){
		originDateSplited[2] = originDateSplited[2].charAt(1); 
	}
	
	resultDate += '.'+originDateSplited[2];
	
	return resultDate;
}

function initDetailBtn(){
	var unfoldBtn = document.querySelector('a._open');	
	var foldBtn = document.querySelector('a._close');
	var foldingText = document.querySelector('div.store_details');
	
	document.querySelector('div.section_store_details').addEventListener('click',function(evt){
		var clickedTag  = evt.target;
		
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
}

function initSwipeImage(jsonData){
	// 상단 Swipe Image 배너 Template
	var swipeTemplate = document.querySelector('#bannerImageTemplate').innerText;
	var bindSwipeTemplate = Handlebars.compile(swipeTemplate);
	var swipeContainer = document.querySelector('ul.detail_swipe');
	
	// Swipe 페이지 수, 총량 표시
	var swipePage = document.querySelector('.figure_pagination').querySelector('.num');
	var swipeAmount = document.querySelector('.figure_pagination').querySelector('.off>span');
	
	// Swipe 이미지 좌우의 버튼
	var swipeLeftBtn = document.querySelector('.ico_arr6_lt');
	var swipeRightBtn = document.querySelector('.ico_arr6_rt');
	
	// 공통 할 일 : 숫자 띄우기
	if(jsonData.productImages.length == 1){
		// 이미지가 1개인 경우
		 
		jsonData.curSaveFileName = jsonData.productImages[0].saveFileName;
		swipeContainer.innerHTML += bindSwipeTemplate(jsonData);
		
		swipeAmount.innerText = '1';
		
		document.querySelector('.ico_arr6_lt').style.display = 'none';
		document.querySelector('.ico_arr6_rt').style.display = 'none';

		swipeLeftBtn.style.display = 'none';
		swipeRightBtn.style.display = 'none';
		
	}else if(jsonData.productImages.length > 1){
		// 이미지가 2개 이상인 경우
		
		for(var idx = 0; idx < MAX_SWIPE; idx ++){
			jsonData.curSaveFileName = jsonData.productImages[idx].saveFileName;
			swipeContainer.innerHTML += bindSwipeTemplate(jsonData);
		}
		
		swipeAmount.innerText = MAX_SWIPE;
		
		// 화살표에 클릭이벤트 추가
		document.querySelector('.group_visual').addEventListener('click',function(evt){
			
			var clickedBtn = evt.target;
			
			// 화살표 버튼 좌우 같은 동작 수행(이미지 2장)
			if(clickedBtn.tagName === 'I'){
				var imageItems = document.querySelector('ul.detail_swipe').querySelectorAll('.item');
				
				if(swipePage.innerText === '1'){
					// 1번에서 2번으로
					swipePage.innerText = '2';
					imageItems.forEach(item => item.style.left = '-100%')
				}else{
					// 2번에서 1번으로
					swipePage.innerText = '1';
					imageItems.forEach(item => item.style.left = '0%')
				}
			}
		});
	}
	
	// 이미지위에 제목 띄우기
	document.querySelector('div.store_details>p.dsc').innerHTML = jsonData.displayInfo.productContent;
}

function initComment(jsonData){
	var comments = jsonData.comments;
	var averageScore = jsonData.averageScore.toFixed(1);
	
	// Comment Template
	var commentTemplate = document.querySelector('#commentItemTemplate').innerText;
	var bindCommentTemplate = Handlebars.compile(commentTemplate);
	
	var commentContainer = document.querySelector('ul.list_short_review');
	for(var i = 0 ; i < 3 && i < comments.length; i++){
		comments[i].reservationDate = convertDateFormat(comments[i].reservationDate);
		comments[i].productDescription = jsonData.displayInfo.productDescription;
		commentContainer.innerHTML += bindCommentTemplate(comments[i]);	
	}
	
	// 별점 그래프, 숫자 조정
	document.querySelector('em.graph_value').style.width = (averageScore * PERCENT_COEF) + '%';
	document.querySelector('.text_value>span').innerText = averageScore;
	
	// 우측 상단의 Comment 갯수
	document.querySelector('span.join_count>em.green').innerText = comments.length+'건';
	
	// Comment 더보기 버튼
	document.querySelector('a.btn_review_more').setAttribute('href','review?id='+jsonData.displayInfo.displayInfoId)
}

function initInfoTab(jsonData){
	var displayInfo = jsonData.displayInfo;
	var displayInfoImage = jsonData.displayInfoImage;
	
	// [소개]란의 글
	document.querySelector('p.in_dsc').innerText = displayInfo.productContent;
	
	// [오시는 길] - 이미지
	document.querySelector('.store_map').setAttribute('src',displayInfoImage.saveFileName);
	
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

function loadDisplayInfoCallback(responseData) {
	var jsonData = responseData.detailDisplay;
	
	// 펼쳐보기, 접기 버튼
	initDetailBtn();
	
	// SwipeImage 설정
	initSwipeImage(jsonData);
	
	// Comment 설정
	initComment(jsonData);
	
	// 맨 아래의 상세정보, 오시는길 탭 설정
	initInfoTab(jsonData);
}

document.addEventListener('DOMContentLoaded', function() {
	// 페이지 첫 로딩시 할 일
	var id = location.href.split('?')[1].split('=')[1];
	requestAjax(loadDisplayInfoCallback, 'api/products/' + id);
});