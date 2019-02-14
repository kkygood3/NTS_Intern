let detailContent = {
		displayInfoId : document.querySelector('#reservation').dataset.id,
		productInformation : document.querySelector('#product_information'),
		infomation : document.querySelector('#information'),
		content : document.querySelector('#content'),
		detailContent : document.querySelector('#detail_content'),
		placeInformation : document.querySelector('#place_information'),
		score : document.querySelector('#score'),
		limit : 3
}

document.addEventListener('DOMContentLoaded', ()=>{
	basicSettings();
	addOpenAndCloseEvent();
	addTabEvent();
	addSlideEvent();
});

function basicSettings(){
	ajax(loadDisplayData, 'GET', '/api/products/' + detailContent.displayInfoId);
	ajax(loadCommentData, 'GET', '/api/comment/' + detailContent.displayInfoId + '?limit=' + detailContent.limit);
	ajax(loadProductEtcImage, 'GET', '/api/products/etc/' + detailContent.displayInfoId);
}

function loadProductEtcImage(data){
	if(data == null){
		return;
	}
	let etcImageTemplate = document.querySelector('#etc_image_template');
	applyTemplate(detailContent.productInformation, etcImageTemplate, data);
	detailContent.productInformation.innerHTML += detailContent.productInformation.innerHTML;
}

function loadDisplayData(data){
	loadProductInformation(data);
	loadInformation(data);
	loadContent(data);
	loadDetailContent(data);
	loadPlaceInformation(data);
}

function loadCommentData(data){
	loadComments(data);
	loadScore(data);
}


function applyTemplate(parent, template, data){
	if(data == null) {
		return;
	}
	
	let templateText = template.innerText;
	let bindTemplate = Handlebars.compile(templateText);
	let resultHtml = bindTemplate(data);
	parent.innerHTML = parent.innerHTML + resultHtml;
}


function loadProductInformation(data){
	let productInformationTemplate = document.querySelector('#product_information_template');
	applyTemplate(detailContent.productInformation, productInformationTemplate, data);
}

function loadInformation(data){
	let informationTemplate = document.querySelector('#information_template');
	applyTemplate(detailContent.infomation, informationTemplate, data);
}

function loadContent(data){
	let contentTemplate = document.querySelector('#content_template');
	applyTemplate(detailContent.content, contentTemplate, data);
}

function loadDetailContent(data){
	let detailContentTemplate = document.querySelector('#detail_content_template');
	applyTemplate(detailContent.detailContent, detailContentTemplate, data);
}

function loadPlaceInformation(data){
	let placeInformationTemplate = document.querySelector('#place_information_template');
	applyTemplate(detailContent.placeInformation, placeInformationTemplate, data);
}

function loadComments(data){
	if(data == null) {
		return;
	}

	let commentTemplate = document.querySelector("#comment_template").innerText;
	let bindTemplate = Handlebars.compile(commentTemplate);

	let resultHtml = data.comments.reduce((prev, next)=>{
		return prev + bindTemplate(next);
	}, "");

	let commentUl = document.querySelector('#comment_ul');
	commentUl.innerHTML = resultHtml;
	
}

function loadScore(data){
	if(data == null) {
		return;
	}

	let scoreTemplate = document.querySelector("#score_template");
	applyTemplate(detailContent.score, scoreTemplate, data);

	let scoreValue = document.querySelector('#avgerage_score');
	let starWidth = document.querySelector('.graph_value');
	let percentageScore = (parseFloat(scoreValue.innerText) / 5 ) * 100;
	starWidth.style.width = percentageScore + '%';
}


function loadEtcImage(data){
	
}


function addOpenAndCloseEvent(){
	
	let openButton = document.querySelector('._open');
	let closeButton = document.querySelector('._close');
	let contentDetail = document.querySelector('#content');
	
	openButton.addEventListener('click',e=>{
		openButton.style.display = 'none';
		closeButton.style.display = 'block';
		contentDetail.classList.remove('close3');
	}, true);
	closeButton.addEventListener('click',e=>{
		openButton.style.display = 'block';
		closeButton.style.display = 'none';
		contentDetail.classList.add('close3');
	});
}

function addTabEvent(){
	
	document.querySelector('.info_tab_lst').addEventListener('click', e=>{
		let target = e.target;
		let tagName = target.tagName;

		if(tagName == 'LI'){
			removeTabActive();
			target.firstElementChild.classList.add('active');
			tabShowAndHide(target.classList);
		}else if(tagName == 'A'){
			removeTabActive();
			target.classList.add('active');
			tabShowAndHide(target.parentElement.classList);
		}else if(tagName == 'SPAN'){
			removeTabActive();
			tabShowAndHide(target.parentElement.parentElement.classList);
			target.parentElement.classList.add('active');
		}
		
	});
}

function removeTabActive(){
	Array.from(document.querySelector('.info_tab_lst').children).forEach(child=>{
		child.firstElementChild.classList.remove('active');
	});
}

function tabShowAndHide(classList){
	let detailArea = document.querySelector('.detail_area_wrap');
	let location = document.querySelector('.detail_location');
	if(classList.contains('_detail')){
		location.classList.add('hide');
		detailArea.classList.remove('hide');
	}else{
		detailArea.classList.add('hide');
		location.classList.remove('hide');
	}
}



function addSlideEvent(){
	document.querySelector('.detail_swipe').style.transform = 'translateX(-828px)';
	  document.querySelector('.btn_prev').addEventListener('click', ()=>{
	    movePrev();
	  });
	  document.querySelector('.btn_nxt').addEventListener('click', ()=>{
	    moveNext();
	  });
}

function movePrev(){
	  let slider = document.querySelector('.detail_swipe');
	  let leftValue = slider.offsetLeft;
	  let translateValue = 0;

	  if(slider.style.transform != ''){
	    translateValue = parseInt(slider.style.transform.split('(')[1].split('p')[0]);
	  }

	  slider.style.left = leftValue + 414 + 'px';
	  if(leftValue + translateValue == -414){
	    setTimeout(()=>{
	      slider.style.transform = 'translateX(' + (-1242 - leftValue) + 'px)';
	    }, 100);
	  }
	}

	function moveNext(){
	  let slider = document.querySelector('.detail_swipe');
	  let leftValue = slider.offsetLeft;
	  let translateValue = 0;

	  if(slider.style.transform != ''){
	    translateValue = parseInt(slider.style.transform.split('(')[1].split('p')[0]);
	  }

	  slider.style.left = leftValue - 414 + 'px';
	  if(leftValue + translateValue == -828){
	    setTimeout(()=>{
	      slider.style.transform = 'translateX(' + (0 - leftValue) + 'px)';
	    }, 100);
	  }
	}











