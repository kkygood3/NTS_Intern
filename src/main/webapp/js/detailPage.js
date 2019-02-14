// Rest API로 서버로부터 해당 url의 json데이터를 가져옴
function requestAjax(callback, url) {
    var ajaxReq = new XMLHttpRequest();
    ajaxReq.callback = callback;
    ajaxReq.addEventListener('load', evt => {
        callback(evt.target.response)
    });
    ajaxReq.open('GET', 'api/' + url);
    ajaxReq.responseType = 'json';
    ajaxReq.send();
}

function getUrlParameter(name) {
	var params = location.href.split('?')[1].split('&');
	for (var i = 0; i < params.length; i++) {
		var paramSplited = params[i].split('=');
		var paramName = paramSplited[0];
		var paramValue = paramSplited[1];

		if (paramName === name) {
			return paramValue;
		}
	}
}

function initDetailBtn(){
	var unfoldBtn = document.querySelector('a._open');	
	var foldBtn = document.querySelector('a._close');
	var foldingText = document.querySelector('div.store_details');
	var textArea = document.querySelector('p.dsc');

	if(textArea.scrollHeight > textArea.clientHeight){
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
	} else {
		unfoldBtn.style.display = 'none';
	}
}


function initTitleImage(displayInfo) {
    var titleTemplate = document.querySelector('#bannerImage').innerText;
    var bindTitleTemplate = Handlebars.compile(titleTemplate);
    var titleContainer = document.querySelector('ul.detail_swipe');

    titleContainer.innerHTML += bindTitleTemplate(displayInfo);
	
	document.querySelector('div.store_details>p.dsc').innerHTML = displayInfo.productContent;

}

function initComment(displayCommentInfo) {
    var commentTemplate = document.querySelector('#commentItem').innerText;
    var bindCommentTemplate = Handlebars.compile(commentTemplate);
    var commentContainer = document.querySelector('ul.list_short_review');

    if(displayCommentInfo.length <= 3){
        displayCommentInfo.forEach(comment => {
            commentContainer.innerHTML += bindCommentTemplate(comment);
        });
    } else {
        for(var i = 0; i < 3; ++i) {
            commentContainer.innerHTML += bindCommentTemplate(displayCommentInfo[i]);
        }
    }

    // 별점 설정
    var averageScore = displayCommentInfo.averageScore;
	document.querySelector('em.graph_value').style.width = (averageScore * 20) + '%';
	document.querySelector('.text_value>span').innerText = averageScore;
	
    // 총 댓글 갯수
    var commentCount = displayCommentInfo.commentCount;
	document.querySelector('span.join_count>em.green').innerText = commentCount+'건';
	
	
	// 댓글  더보기 버튼
	var reviewMoreBtn = document.querySelector('a.btn_review_more');
	if(commentCount > 3){
		reviewMoreBtn.setAttribute('href','/');
	} else{
		reviewMoreBtn.style.display = 'none';
	}

}

function initBottomInfoTab(displayInfoResponse){
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


function loadDisplayInfoCallback(responseData) {
    var displayInfoResponse = responseData;
    var displayInfo = displayInfoResponse["displayInfo"];
    var displayProductImages = displayInfoResponse["productImages"];

    // ma 타입의 이미지 정보를 displayInfo에 추가
    displayProductImages.forEach(image => {
        if(image.type === 'ma') displayInfo.saveFileName = image.saveFileName;
    });
    
	// TitleImage 설정
    initTitleImage(displayInfo);

    // 펼쳐보기 버튼 설정
    initDetailBtn();
    
    // productDescription 정보를 displayCommentInfo에 추가
    var displayCommentInfo = displayInfoResponse["comments"];
    displayCommentInfo.forEach(comment => {
        comment.productDescription = displayInfo.productDescription;
    });

    // averageScore, commentCount 정보를 displayCommentInfo에 추가
    displayCommentInfo.averageScore = displayInfoResponse.averageScore;
    displayCommentInfo.commentCount = displayInfoResponse.comments.length;

    // Comment 설정
    initComment(displayCommentInfo);

    // 하단 정보 설정
    initBottomInfoTab(displayInfoResponse);
}


// DOMContentLoaded 초기 설정
document.addEventListener('DOMContentLoaded', function () {
    requestAjax(loadDisplayInfoCallback, 'products/' + getUrlParameter('id'));
});
