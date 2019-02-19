/**
 * @desc 전역변수 
 */
var sendAjax = require('../sendAjax');
/**
 * @function setHandlebarRegistHelper()  : handlebar regist Helper 설정
 * @function getHandlebarTemplateFromHtml(templateId,content) : handlebar로 compile해서 html로 얻어오기
 */
var handlebarsFunction = require('../handlebarsFunction');

var globalVariables = {
	productTransform : 0,			// productImage 현재 Transform 거리
	showProductNumber : 0,			// 현재 보여지고 있는 이미지 number
};

document.addEventListener('DOMContentLoaded', function() {
	getDisplayInfos();
	addDetailButtonEvent();
	
	addFoldButtonEvent();
	addExpandButtonEvent();
});

/**
 * @desc displayInfo 정보 가져온뒤 셋팅
 * @returns
 */
function getDisplayInfos() {
	var displayInfoId = getDisplayInfoId();

	var displayInfoSendHeader = {
		method : 'GET',
		uri : '/api/products/'+displayInfoId
	};
	
	sendAjax(displayInfoSendHeader,'',afterGetDisplayInfoData, function(){
		
		alert('요청한 페이지가 없거나, 전시가 종료된 상품입니다.');
		location.href = '/main';

	});
}

/**
 * @desc displayInfo data를 성공적으로 받앗을 때
 * @param displayInfoResponse
 */
function afterGetDisplayInfoData(displayInfoResponse){

	setProductImages(displayInfoResponse.productImages, displayInfoResponse.displayInfo.productDescription);
	setProductContent(displayInfoResponse.displayInfo.productContent);
	setProductEvent({productEvent : displayInfoResponse.displayInfo.productEvent});
	
	var start = 0;
	var end = 3;
	
	handlebarsFunction.setHandlebarRegistHelper();
	setComments({comments : displayInfoResponse.comments.slice(start, end)}, displayInfoResponse.comments.length, displayInfoResponse.averageScore, displayInfoResponse.displayInfo.productId);
	setProductDetail(displayInfoResponse.displayInfo, displayInfoResponse.displayInfoImage.saveFileName);
}

/**
 * @desc displayInfoId 가져오기
 * @returns displayInfoId
 */
function getDisplayInfoId() {
	return document.querySelector('.display_info_detail').dataset.displayinfoid;
}

/**
 * @desc page 최상단 image 셋팅
 * @param productImages
 */
function setProductImages(productImages, productDescription){
	
	var productImageLength = productImages.length;
	document.querySelector('#product_image_max').innerHTML = productImageLength;
	document.querySelector('.product_images_ul').innerHTML = handlebarsFunction.getHandlebarTemplateFromHtml('#product_images_template', {productImages: productImages});
	
	document.querySelectorAll('.product_title').forEach(function(ele){
		ele.innerText = productDescription; 
	});
	
	if(productImageLength === 1){
		document.querySelector('.prev').remove();
		document.querySelector('.nxt').remove();
		
		document.querySelector('.product_images_ul').firstElementChild.classList.remove('single_slide');

	} else {
		var productImagesUl = document.querySelector('.product_images_ul');
		
		var firstImageLi = productImagesUl.firstElementChild;
		var lastImageLi = productImagesUl.lastElementChild;
		
		firstImageLi.style.left = 0;
		addProductImageNextButtonEvent(productImagesUl,firstImageLi,lastImageLi);
		addProductImagePrevButtonEvent(productImagesUl,firstImageLi,lastImageLi);
	}
}

/**
 * @desc 다음 button 클릭 이벤트
 * @param productImagesUl
 * @param firstImageLi
 * @param lastImageLi
 */
function addProductImageNextButtonEvent(productImagesUl,firstImageLi,lastImageLi){
	
	var nowProductNumber = document.querySelector('.product_number');
	
	document.querySelector('.nxt').addEventListener('click',function(e){

		if(globalVariables.showProductNumber === 0 ){
	
			firstImageLi.style.left = 0;
			lastImageLi.style.left = '100%';

			firstImageLi.setAttribute('class','single_slide slide_out_left');
			lastImageLi.setAttribute('class','single_slide slide_in_right');
			
			globalVariables.showProductNumber = 1;
		} else {
			
			firstImageLi.style.left = '100%';
			lastImageLi.style.left = 0;
			
			firstImageLi.setAttribute('class','single_slide slide_in_right');
			lastImageLi.setAttribute('class','single_slide slide_out_left');

			globalVariables.showProductNumber = 0;
		}
		

		nowProductNumber.innerText = globalVariables.showProductNumber+1;
	});
}

/**
 * @desc 이전 버튼 클릭 이벤트
 * @param productImagesUl
 * @param firstImageLi
 * @param lastImageLi
 */
function addProductImagePrevButtonEvent(productImagesUl,firstImageLi,lastImageLi){
	
	var nowProductNumber = document.querySelector('.product_number');
	document.querySelector('.prev').addEventListener('click',function(e){
		
		if(globalVariables.showProductNumber === 0 ){
	
			firstImageLi.style.left = 0;
			lastImageLi.style.left = '-100%';

			firstImageLi.setAttribute('class','single_slide slide_out_right');
			lastImageLi.setAttribute('class','single_slide slide_in_left');

			globalVariables.showProductNumber = 1;
		} else {
			firstImageLi.style.left = '-100%';
			lastImageLi.style.left = 0;

			firstImageLi.setAttribute('class','single_slide slide_in_left');
			lastImageLi.setAttribute('class','single_slide slide_out_right');

			globalVariables.showProductNumber = 0;
		}
		nowProductNumber.innerText = globalVariables.showProductNumber+1;
	});
}

/**
 * @desc 접기 버튼 이벤트
 */
function addFoldButtonEvent(){
	
	document.querySelector('._close').addEventListener('click',function(){
		myButtonHideOtherButtonOpen(this,'._open');
		
		var details = document.querySelector('.store_details');
		details.classList.add('close3');
	});
}

/**
 * @desc 펼쳐보기 버튼 이벤트
 */
function addExpandButtonEvent(){
	
	document.querySelector('._open').addEventListener('click',function(){
		myButtonHideOtherButtonOpen(this,'._close');
		
		var details = document.querySelector('.store_details');
		details.classList.remove('close3');
	});
}

/**
 * @desc 자신의 버튼 닫고 다른버튼 활성화
 * @param otherId
 * @param _this
 */
function myButtonHideOtherButtonOpen(_this,otherQuery){
	
	_this.classList.remove('open');
	_this.classList.add('hide');
	
	var other = document.querySelector(otherQuery);
	
	other.classList.remove('hide');
	other.classList.add('open');
}

/**
 * @desc 코멘트 갯수, 코멘트 리스트, 코멘트 별점 셋팅
 * @param comments
 * @param commentsLength
 * @param commentAverageScore
 * @param productId
 */
function setComments(comments,commentsLength,commentAverageScore,productId){
	
	document.querySelector('.list_short_review').innerHTML = handlebarsFunction.getHandlebarTemplateFromHtml('#comment_template',comments);
	document.querySelector('#comment_count').innerHTML = commentsLength;
	
	var start = 0;
	var end = 3;
	
	document.querySelector('#comment_average').innerHTML = String(commentAverageScore).substr(start,end);
	document.querySelector('#comment_graph_star').style.width = commentAverageScore * 20 + '%';
	
	if(commentsLength === 0){
		document.querySelector('.btn_review_more').remove();
	} else {
		document.querySelector('.btn_review_more').href = '/review/'+productId;
	}
}



/**
 * @desc product event setting
 * @returns
 */
function setProductEvent(productEvent){
	document.querySelector('.event_info').innerHTML = handlebarsFunction.getHandlebarTemplateFromHtml('#product_event_template',productEvent);
}

/**
 * @desc product 내용 삽입
 * @param productContent
 * @returns
 */
function setProductContent(productContent){
	document.querySelectorAll('.product_content').forEach(function(element){
		element.innerHTML = productContent;
	});
}

/**
 * @desc product 상세 셋팅
 * @param displayInfo
 * @param saveFileName
 * @returns
 */
function setProductDetail(displayInfo, saveFileName){
	
	displayInfo.saveFileName = saveFileName;
	document.querySelector('.product_location').innerHTML = handlebarsFunction.getHandlebarTemplateFromHtml('#detail_location_template',displayInfo);
}


/**
 * @desc detail Button Event 걸어주기
 * @returns
 */
function addDetailButtonEvent(){
	
	document.querySelector('.info_tab_lst').addEventListener('click',function(e){
		
		var target = e.target;
		var targetTagName = e.target.tagName.toLowerCase();
		
		removeAnchorActiveClass(e.currentTarget);
		
		var liTarget;
		
		if (targetTagName === 'li') {
			target.querySelector('a').classList.add('active');
			liTarget = target;
		} else if (targetTagName === 'a') {
			target.className = 'anchor active';
			liTarget = target.parentNode;
		} else if (targetTagName === 'span') {
			target.parentNode.className = 'anchor active';
			liTarget = target.parentNode.parentNode;
		}
		
		changeDetailHide(liTarget);
	});
}

/**
 * @desc anchor 태그 active class 지우기
 * @param currentTarget
 */
function removeAnchorActiveClass(currentTarget){
	
	var detailAnchorTag = currentTarget.querySelectorAll('.anchor');
	
	detailAnchorTag.forEach(function(ele){
		ele.className = 'anchor';
	});
}

/**
 * @desc 상세정보와 오시는길 hide and show
 * @param liTarget
 * @returns
 */
function changeDetailHide(liTarget) {

	if (liTarget.classList.contains('_detail')) {

		document.querySelector('.detail_area_wrap').classList.remove('hide');
		document.querySelector('.detail_location').classList.add('hide');
	} else if (liTarget.classList.contains('_path')) {

		document.querySelector('.detail_area_wrap').classList.add('hide');
		document.querySelector('.detail_location').classList.remove('hide');
	}
}