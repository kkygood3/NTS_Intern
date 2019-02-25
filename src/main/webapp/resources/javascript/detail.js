let detailContent = {
	displayInfoId : document.querySelector('#reservation').dataset.id,
	productInformation : document.querySelector('#product_information'),
	infomation : document.querySelector('#information'),
	content : document.querySelector('#content'),
	detailContent : document.querySelector('#detail_content'),
	placeInformation : document.querySelector('#place_information'),
	score : document.querySelector('#score'),
	discount : document.querySelector('#discount'),
	limit : 3
}

document.addEventListener('DOMContentLoaded', ()=>{
	basicSettings();
	addTabToggleEvent();
	addTabActiveEvent();
	document.querySelector('#reservation').addEventListener('click', ()=>{
		location.href = "/reserve/" + detailContent.displayInfoId;
	});
});

function basicSettings(){
	ajax(loadDisplayData, 'GET', '/api/products/' + detailContent.displayInfoId + '?limit=' + detailContent.limit, 'Load Display Fail!!');
	ajax(loadProductEtcImage, 'GET', '/api/products/etc/' + detailContent.displayInfoId, 'Load Product Ect Image Fail!!');
}

function loadDisplayData(data){
	templatingHandlebar(detailContent.productInformation, document.querySelector('#product_information_template'), data);
	templatingHandlebar(detailContent.infomation, document.querySelector('#information_template'), data.displayInfo);
	templatingHandlebar(detailContent.content, document.querySelector('#content_template'), data.displayInfo);
	templatingHandlebar(detailContent.detailContent, document.querySelector('#detail_content_template'), data.displayInfo);
	templatingHandlebar(detailContent.placeInformation, document.querySelector('#place_information_template'), data);
	
	templatingDiscount(data.productPrices);
	templatingComments(data.commentResponse.comments);
	templatingScore(data);
}

function templatingHandlebar(parent, template, data){
	let templateText = template.innerText;
	let bindTemplate = Handlebars.compile(templateText);
	let resultHtml = bindTemplate(data);
	parent.innerHTML = parent.innerHTML + resultHtml;
}


function templatingComments(comments){
	if(comments.length === 0) {
		document.querySelector('.btn_review_more').classList.add('hide');
		return;
	}
	
	let commentTemplate = document.querySelector("#comment_template").innerText;
	let bindTemplate = Handlebars.compile(commentTemplate);

	let resultHtml = comments.reduce((prev, next)=>{
		return prev + bindTemplate(next);
	}, "");

	let commentUl = document.querySelector('#comment_ul');
	commentUl.innerHTML = resultHtml;
	
}

function templatingDiscount(prices){
	let resultHtml = "";
	prices.forEach((price, index)=>{
		if(!price.priceTypeName.match(/[VRS]/)){
			return;
		}
		if(price.discountRate > 0){
			resultHtml += price.priceTypeName + "석 " + price.discountRate + "%";
			
			if(index === prices.length - 1){
				return;
			}
			resultHtml += ", "
		}
	});
	if(resultHtml === ""){
		document.querySelector('.section_event').classList.add('hide');
	}else{
		discount.innerHTML += resultHtml;
	}
}

function templatingScore(data){
	if(data.commentResponse.comments.length === 0) {
		return;
	}

	let scoreTemplate = document.querySelector("#score_template");
	templatingHandlebar(detailContent.score, scoreTemplate, data);

	let scoreValue = document.querySelector('#avgerage_score');
	let starWidth = document.querySelector('.graph_value');
	let percentageScore = (parseFloat(scoreValue.innerText) / 5 ) * 100;
	starWidth.style.width = percentageScore + '%';
}


function loadProductEtcImage(data){
	if(data.saveFileName === null){
		return;
	}
	let etcImageTemplate = document.querySelector('#etc_image_template');
	templatingHandlebar(detailContent.productInformation, etcImageTemplate, data);
	
	setTimeout(()=>{
		detailContent.productInformation.innerHTML += detailContent.productInformation.innerHTML;
	}, 100);
	
	document.querySelector("#product_title");
	
	addSlideButtonEvent();
}


function addTabToggleEvent(){
	
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

function addTabActiveEvent(){
	
	document.querySelector('.info_tab_lst').addEventListener('click', e=>{
		let target = e.target;
		let tagName = target.tagName;

		if(tagName == 'LI'){
			removeTabActive();
			target.firstElementChild.classList.add('active');
			toggleTab(target.classList);
		}else if(tagName == 'A'){
			removeTabActive();
			target.classList.add('active');
			toggleTab(target.parentElement.classList);
		}else if(tagName == 'SPAN'){
			removeTabActive();
			toggleTab(target.parentElement.parentElement.classList);
			target.parentElement.classList.add('active');
		}
		
	});
}

function removeTabActive(){
	Array.from(document.querySelector('.info_tab_lst').children).forEach(child=>{
		child.firstElementChild.classList.remove('active');
	});
}

function toggleTab(classList){
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


function addSlideButtonEvent(){
	document.querySelector('#pagination').classList.remove('hide');
	document.querySelector('#slide_button').classList.remove('hide');
	
	document.querySelector('.detail_swipe').style.transform = 'translateX(-828px)';
	document.querySelector('.btn_prev').addEventListener('click', ()=>{
		movePrev();
	});
	document.querySelector('.btn_nxt').addEventListener('click', ()=>{
		moveNext();
	});
}

function movePrev(){
	let paginationNum = parseInt(document.querySelector('#pagination_num').innerText);
	paginationNum--;
	if(paginationNum < 1){
		paginationNum = 2;
	}
	document.querySelector('#pagination_num').innerText = paginationNum;
	
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
	let paginationNum = parseInt(document.querySelector('#pagination_num').innerText);
	paginationNum++;
	if(paginationNum > 2){
		paginationNum = 1;
	}
	document.querySelector('#pagination_num').innerText = paginationNum;
	
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











