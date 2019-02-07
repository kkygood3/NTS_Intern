
document.addEventListener('DOMContentLoaded', function() {
	getDisplayInfos();
	addDetailButtonEvent();
});

/**
 * @desc displayInfo 정보 가져오기
 * @returns
 */
function getDisplayInfos() {
	var displayInfoId = getDisplayInfoId();

	var displayInfoSendHeader = {
		method : 'GET',
		uri : '/api/products/'+displayInfoId
	};
	
	sendAjax(displayInfoSendHeader,'',function(displayInfoResponse){
		console.log(displayInfoResponse);
		
		setProductImages(displayInfoResponse.productImages);
		setProductContent(displayInfoResponse.displayInfo.productContent);
		setProductEvent({productEvent : displayInfoResponse.displayInfo.productEvent});
		
		var start = 0;
		var end = 3;
		
		setHandlebarRegist();
		setComments({comments : displayInfoResponse.comments.slice(start, end)}, displayInfoResponse.comments.length, String(displayInfoResponse.averageScore).substr(start,end));
		setProductDetail(displayInfoResponse.displayInfo, displayInfoResponse.displayInfoImage.saveFileName);
	});
}

/**
 * @returns displayinfoid
 */
function getDisplayInfoId() {
	return document.querySelector('.display_info_detail').dataset.displayinfoid;
}

/**
 * @desc page 최상단 image 셋팅
 * @param productImages
 * @returns
 */
function setProductImages(productImages){
	
}

/**
 * @desc set comment
 * @returns
 */
function setComments(comments,commentsLength,commentAverageScore){
	
	document.querySelector('.list_short_review').innerHTML = getHandlebarTemplateFromHtml('#comment_template',comments);
	document.querySelector('#comment_count').innerHTML = commentsLength;
	document.querySelector('#comment_average').innerHTML = commentAverageScore;
	document.querySelector('#comment_graph_star').style.width = commentAverageScore * 20 + '%';
}

/**
 * @desc handlebar helper regist 작업 
 * @returns
 */
function setHandlebarRegist(){
	
	Handlebars.registerHelper('userEmailEncrypt', function(reservationEmail) {
		   
		var start = 0;
	    var end = 4;
	    
		var encryptedUserEmail = reservationEmail.substring(start,end)+'****';
	    return new Handlebars.SafeString(encryptedUserEmail);
	});
	
	Handlebars.registerHelper('reservationDateYYYYMMDD', function(reservationDate) {
	    
		var start = 0;
	    var end = 10;
		
	    var reservationDateYYYYMMDD = reservationDate.substring(start,end).replace(/-/g,'.')+'.';
	    return new Handlebars.SafeString(reservationDateYYYYMMDD);
	});
}

/**
 * @desc product event setting
 * @returns
 */
function setProductEvent(productEvent){
	document.querySelector('.event_info').innerHTML = getHandlebarTemplateFromHtml('#product_event_template',productEvent);
}

/**
 * @param productContent
 * @returns
 */
function setProductContent(productContent){
	document.querySelectorAll('.product_content').forEach(function(element){
		element.innerHTML = productContent;
	});
}

/**
 * 
 * @param displayInfo
 * @param saveFileName
 * @returns
 */
function setProductDetail(displayInfo, saveFileName){
	
	displayInfo.saveFileName = saveFileName;
	document.querySelector('.product_location').innerHTML = getHandlebarTemplateFromHtml('#detail_location_template',displayInfo);
}

/**
 * @desc handlebar로 compile해서 html로 만들어주기
 * @param templateId
 * @param compileTemplate
 * @param content
 * @returns html
 */
function getHandlebarTemplateFromHtml(templateId,content){
	
	var template = document.querySelector(templateId).innerHTML;
	var handlebarTemplate = Handlebars.compile(template);
	
	return handlebarTemplate(content);
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
 * @param e
 * @returns
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