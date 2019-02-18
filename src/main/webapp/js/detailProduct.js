document.addEventListener('DOMContentLoaded', function() {
	requestAjax(loadDisplayInfoCallback, 'api/products/' + getUrlParameter('displayInfoId'));
});

function loadDisplayInfoCallback(responseData) {
	let displayInfo = responseData.displayInfo;
	let comments = responseData.comments;

	// 타이틀
	//loadTitleImage(responseData);
	
	// 상품 설명
	loadProductExplain(responseData);
	
	// 상세설명 영역 - 상단 코멘트
	loadComments(responseData);
	
	// 상세설명 영역 - 하단 상세정보, 오시는길
	loadInfoTab(displayInfo);
	
	// 펼쳐보기 및 닫기
	loadShowMoreButton();
	
	//requestAjax(loadExtraImageCallback,'api/products/' + displayInfo.displayInfoId + '/extra');
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

function loadTitleImage(displayInfo){
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
		commentContainer.innerHTML += bindCommentTemplate(displayComment[i]);	
	}
	
	// 별점 그래프 및 코멘트 평균 점수
	document.querySelector('em.graph_value').style.width = (averageScore * 20) + '%';
	document.querySelector('.text_value>span').innerText = averageScore.toFixed(1);
	
	// 코멘트 개수
	document.querySelector('span.join_count>em.green').innerText = commentCount+'건';
	
	// 코멘트 더보기 버튼 생성하기
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

function loadExtraImageCallback(responseData){
	let extraImageInformation = responseData.productExtraImage;
	console.log(extraImageInformation);
}