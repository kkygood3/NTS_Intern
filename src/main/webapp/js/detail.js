var currentTab = 1;
var MAX_SWIPE = 2;
var PERCENT_COEF = 20;
var displayInfoResponse; 
var displayInformation;

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
}

function initSwipeImage(displayInfoResponse){
	
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
	// 이미지가 1개인 경우
	
	swipeContainer.innerHTML += bindSwipeTemplate(displayInfomation);
	
	document.querySelector('div.store_details>p.dsc').innerHTML = displayInfomation.productContent;
}

function initComment(displayInfoResponse){	
	var commentCount = displayInfomation.commentCount;
	
	var averageScore = displayInfomation.averageScore;
	
	// Comment Template
	var commentTemplate = document.querySelector('#commentItemTemplate').innerText;
	var bindCommentTemplate = Handlebars.compile(commentTemplate);
	
	var commentContainer = document.querySelector('ul.list_short_review');
	
	//TODO 3개만 받아오도록 LIMIT 걸어야함
	for(var i = 0 ; i < 3 && i < commentCount; i++){
		displayInfoResponse[i].reservationDate = convertDateFormat(displayInfoResponse[i].reservationDate);
		commentContainer.innerHTML += bindCommentTemplate(displayInfoResponse[i]);	
	}
	
	// 별점 그래프, 숫자 조정
	document.querySelector('em.graph_value').style.width = (averageScore * PERCENT_COEF) + '%';
	document.querySelector('.text_value>span').innerText = averageScore;
	
	// 우측 상단의 Comment 갯수
	document.querySelector('span.join_count>em.green').innerText = commentCount+'건';
	
	
	// Comment 더보기 버튼
	var reviewMoreBtn = document.querySelector('a.btn_review_more');
	if(commentCount > 3){
		reviewMoreBtn.setAttribute('href','review?id='+displayInfomation.displayInfoId);
	} else{
		reviewMoreBtn.style.display = 'none';
	}
}

function initInfoTab(displayInfoResponse){
	var displayInfo = displayInfomation;
	
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
	
	if(responseData.productImage){
		var image = responseData.productImage.productImage;
		
		displayInfomation.productImage = image;
		swipeContainer.innerHTML += bindSwipeTemplate(displayInfomation);
		
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
	} else {
		swipeAmount.innerText = '1';
		
		document.querySelector('.ico_arr6_lt').style.display = 'none';
		document.querySelector('.ico_arr6_rt').style.display = 'none';

		swipeLeftBtn.style.display = 'none';
		swipeRightBtn.style.display = 'none';
	}
}

function loadDisplayInfoCallback(responseData) {
	displayInfoResponse = responseData.detailDisplay;
	displayInfomation = displayInfoResponse[0];
	
	// 펼쳐보기, 접기 버튼
	initDetailBtn();
	
	// SwipeImage 설정
	initSwipeImage(displayInfoResponse);
	
	// Comment 설정
	initComment(displayInfoResponse);
	
	// 맨 아래의 상세정보, 오시는길 탭 설정
	initInfoTab(displayInfoResponse);
	
	requestAjax(loadExtraImageCallback,'api/products/'+displayInfomation.displayInfoId+'/extra');
}

document.addEventListener('DOMContentLoaded', function() {
	// 페이지 첫 로딩시 할 일
	var id = location.href.split('?')[1].split('=')[1];
	requestAjax(loadDisplayInfoCallback, 'api/products/' + id);
});