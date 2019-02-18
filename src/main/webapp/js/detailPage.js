// Rest API로 서버로부터 해당 url의 json데이터를 가져옴
function requestAjax(callback, url) {
    let ajaxReq = new XMLHttpRequest();
    ajaxReq.callback = callback;
    ajaxReq.addEventListener('load', evt => {
        callback(evt.target.response)
    });
    ajaxReq.open('GET', 'api/' + url);
    ajaxReq.responseType = 'json';
    ajaxReq.send();
}

// Url의 name에 해당하는 Parameter 추출
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

function initTitleImage(displayInfo) {
    let titleTemplate = document.querySelector('#bannerImage').innerText;
    let bindTitleTemplate = Handlebars.compile(titleTemplate);
    let titleContainer = document.querySelector('ul.detail_swipe');

    titleContainer.innerHTML += bindTitleTemplate(displayInfo);
	
    document.querySelector('div.store_details>p.dsc').innerHTML = displayInfo.productContent;
    
    // next, prev 버튼 비활성화
    document.querySelector('div.prev').style.display = 'none';
    document.querySelector('div.nxt').style.display = 'none';

    document.querySelector('div.figure_pagination').firstElementChild.classList.add('off');

}

function initDetailBtn() {
	let unfoldBtn = document.querySelector('a._open');	
	let foldBtn = document.querySelector('a._close');
	let foldingText = document.querySelector('div.store_details');
	let textArea = document.querySelector('p.dsc');

    // text가 기준치보다 많으면 DetailBtn 활성화
	if(textArea.scrollHeight > textArea.clientHeight){
		document.querySelector('div.section_store_details').addEventListener('click', evt => {
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

function initComment(displayCommentInfo, totalComments) {
    let commentTemplate = document.querySelector('#commentItem').innerText;
    let bindCommentTemplate = Handlebars.compile(commentTemplate);
    let commentContainer = document.querySelector('ul.list_short_review');

    if(totalComments <= 3){
        displayCommentInfo.forEach(comment => {
            if(comment.commentImages != 0) {
                comment.saveFileName = comment.commentImages[0].saveFileName;
            }
            commentContainer.innerHTML += bindCommentTemplate(comment);
        });
    } else {
        for(let i = 0; i < 3; ++i) {
            if(displayCommentInfo[i].commentImages != 0) {
                displayCommentInfo[i].saveFileName = displayCommentInfo[i].commentImages[0].saveFileName;
            }
            commentContainer.innerHTML += bindCommentTemplate(displayCommentInfo[i]);
        }
    }

    // 별점 설정
    let averageScore = displayCommentInfo.averageScore;
	document.querySelector('em.graph_value').style.width = (averageScore * 20) + '%';
	document.querySelector('.text_value>span').innerText = averageScore;
	
    // 총 댓글 갯수
    let commentCount = totalComments;
	document.querySelector('span.join_count>em.green').innerText = commentCount+'건';
	
	
	// 댓글 더보기 버튼
	let reviewMoreBtn = document.querySelector('a.btn_review_more');
	if(totalComments > 3){
		reviewMoreBtn.setAttribute('href','/');
	} else{
		reviewMoreBtn.style.display = 'none';
	}

}

function initMoreCommentBtn(displayInfoId) {
    // review 페이지 이동
    document.querySelector('.btn_review_more').setAttribute('href','review?id=' + displayInfoId);
}

function initBottomInfoTab(displayInfoResponse){
    let displayInfo = displayInfoResponse["displayInfo"];
    let displayInfoImage = displayInfoResponse["displayInfoImage"];
	
	// [소개]란의 글
	document.querySelector('p.in_dsc').innerText = displayInfo.productContent;
	
	// [오시는 길] - 이미지
	document.querySelector('.store_map').setAttribute('src',displayInfoImage.saveFileName);
	
	// [오시는 길] - 장소 명
	document.querySelector('.store_name').innerText = displayInfo.placeName;
	
	// [오시는 길] - 주소
	let addressWrap = document.querySelector('.store_addr_wrap').querySelectorAll('p');
	addressWrap[0].innerText = displayInfo.placeStreet;
	addressWrap[1].querySelectorAll('span')[1].innerText = displayInfo.placeLot;
	addressWrap[2].innerText = displayInfo.placeName;

	// [오시는 길] - 전화번호
	let telephoneArea = document.querySelector('.store_tel');
	telephoneArea.setAttribute('href',displayInfo.telephone);
	telephoneArea.innerText = displayInfo.telephone;
	
	// 상세 정보, 오시는 길 전환 탭
	let detailTab = document.querySelector('ul.info_tab_lst>._detail');
	let detailBody = document.querySelector('.detail_area_wrap');
	
	let pathTab = document.querySelector('ul.info_tab_lst>._path');
	let pathBody = document.querySelector('.detail_location');
	
	let currentTab = 1;
	// 상세정보, 오시는 길 클릭 이벤트
	document.querySelector('ul.info_tab_lst').addEventListener('click', evt => {
		
		let clickedTab = evt.target;
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

function setTitleSlide(addtionalDisplayInfo, TitleDisplayImage) {
    let titleTemplate = document.querySelector('#bannerImage').innerText;
    let bindTitleTemplate = Handlebars.compile(titleTemplate);
    let titleContainer = document.querySelector('ul.detail_swipe');

    titleContainer.innerHTML += bindTitleTemplate(addtionalDisplayInfo);
    
    addtionalDisplayInfo.saveFileName = TitleDisplayImage;
    titleContainer.innerHTML += bindTitleTemplate(addtionalDisplayInfo);

    document.querySelector('div.store_details>p.dsc').innerHTML = addtionalDisplayInfo.productContent;
    
    // next, prev 버튼 활성화
    document.querySelector('div.prev').style.display = '';
    document.querySelector('div.nxt').style.display = '';

    document.querySelector('div.figure_pagination').firstElementChild.classList.remove('off');
    document.querySelector('div.figure_pagination').lastElementChild.innerText = '/ 2';

    let currentTitle = 1;
    let titleImageList = document.querySelectorAll('.visual_img > .item');
    let promotionLength = titleImageList.length;
    let leftDistance = 0;

    let btnNext = document.querySelector('a.btn_nxt');

    function nextArrowEventHandler() {
        currentTitle++;
        // 애니메이션 도중 click EventListner 중지
        btnNext.removeEventListener('click',nextArrowEventHandler);
        btnPrev.removeEventListener('click', prevArrowEventHandler);

        if(currentTitle > 2) {
            currentTitle = 1;
            leftDistance -= 100;
            
            titleImageList.forEach((list) => {
                list.style.left = leftDistance + '%';
            });
            
            setTimeout(() => {
                leftDistance = 0;
                titleImageList.forEach((list) => {
                list.style.transitionDuration = '0s';
                list.style.left = leftDistance + '%';
                });

                setTimeout(() => {
                    titleImageList.forEach((list) => {
                    list.style.transitionDuration = '1s';
                    });
                    // 애니메이션이 끝나면 click EventListner 활성화
                    btnNext.addEventListener('click', nextArrowEventHandler);
                    btnPrev.addEventListener('click', prevArrowEventHandler);
                    }, 50);
                
            }, 1150);

        } else {
            leftDistance -= 100;
            titleImageList.forEach((list) => {
                list.style.left = leftDistance + '%';
                });
            setTimeout(() => {
                btnNext.addEventListener('click', nextArrowEventHandler);
                btnPrev.addEventListener('click', prevArrowEventHandler);
            }, 1150);
        }

        document.querySelector('div.figure_pagination').firstElementChild.innerText = currentTitle;
    }

    let btnPrev = document.querySelector('a.btn_prev');

    function prevArrowEventHandler() {
        currentTitle--;
        // 애니메이션 도중 click EventListner 중지
        btnNext.removeEventListener('click',nextArrowEventHandler);
        btnPrev.removeEventListener('click',prevArrowEventHandler);

        if(currentTitle < 1) {
            currentTitle = 2;

            leftDistance = (promotionLength - 1) * -100;

            titleImageList.forEach((list) => {
            list.style.transitionDuration = '0s';
            list.style.left = leftDistance + '%';
            });
            
            // 이미지 위치를 초기화 시키고 애니메이션 실행
            setTimeout(() =>{
                leftDistance += 100;

                titleImageList.forEach((list) => {
                list.style.transitionDuration = '1s';
                list.style.left = leftDistance + '%';
                });
            },0);
            
            setTimeout(() => {
                btnNext.addEventListener('click',nextArrowEventHandler);
                btnPrev.addEventListener('click', prevArrowEventHandler);
            }, 1150);
        } else {
            leftDistance += 100;
                titleImageList.forEach((list) => {
                list.style.left = leftDistance + '%';
                });
            setTimeout(() => {
                // 애니메이션이 끝나면 click EventListner 활성화
                btnNext.addEventListener('click',nextArrowEventHandler);
                btnPrev.addEventListener('click', prevArrowEventHandler);
            }, 1150);
        }

        document.querySelector('div.figure_pagination').firstElementChild.innerText = currentTitle;
    }
    
    btnNext.addEventListener('click', nextArrowEventHandler);
    btnPrev.addEventListener('click', prevArrowEventHandler);
}


function loadDisplayInfoCallback(responseData) {
    let displayInfoResponse = responseData;
    let displayInfo = displayInfoResponse["displayInfo"];
    let displayProductImages = displayInfoResponse["productImages"];
    let totalComments = displayInfoResponse.totalComments;

    let isAddtionalDisplayImage = false;
    let TitleDisplayImage = "";
    // ma 타입의 이미지 정보를 displayInfo에 추가
    // ta 타입의 이미지가 있다면 한장을 additionalDsiplayInfo에 추가
    displayProductImages.forEach(image => {
        if(image.type === 'ma') {
            displayInfo.saveFileName = image.saveFileName;
            TitleDisplayImage = image.saveFileName;
        }
        else if(image.type === 'et') {
            isAddtionalDisplayImage = true;
        }
    });
    
	// TitleImage 설정
    initTitleImage(displayInfo);

    // 펼쳐보기 버튼 설정
    initDetailBtn();
    
    // productDescription 정보를 displayCommentInfo에 추가
    let displayCommentInfo = displayInfoResponse["comments"];
    displayCommentInfo.forEach(comment => {
        comment.productDescription = displayInfo.productDescription;
    });

    // averageScore, commentCount 정보를 displayCommentInfo에 추가
    displayCommentInfo.averageScore = displayInfoResponse.averageScore;
    displayCommentInfo.commentCount = displayInfoResponse.comments.length;

    // Comment 설정
    initComment(displayCommentInfo, totalComments);

    // Comment 더보기 버튼 설정
    initMoreCommentBtn(displayInfo.displayInfoId);

    // 하단 정보 설정
    initBottomInfoTab(displayInfoResponse);

    // init이 모두 끝난 이후 추가적인 etc image가 있었을 경우 슬라이드 작업 진행
    if(isAddtionalDisplayImage) {
        let addtionalDisplayInfo = displayInfoResponse["displayInfo"];

        // 요구사항 - et 이미지가 많아도 최대 1개 등록
        displayProductImages.forEach(image => {
        if(image.type === 'et') addtionalDisplayInfo.saveFileName = image.saveFileName;
        });

        setTitleSlide(addtionalDisplayInfo, TitleDisplayImage);
    }

}

const currentCommentCounts = 3;

// DOMContentLoaded 초기 설정
document.addEventListener('DOMContentLoaded', function () {
    requestAjax(loadDisplayInfoCallback, 'products/' + getUrlParameter('id'));
});

