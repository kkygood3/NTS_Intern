/**
 * @desc 전역 변수
 */
var globalVariable = {
	getProductCount : 0,	// 해당 카테고리의 현재 리스트로 보여진 product의 갯수
	imageItemIndex: 0,
	promotionLength : -1 	// 프로모션 전체 길이
};
var sendAjax = require('./sendAjax');

/**
 * @desc 카테고리 불러오기 및 리스트 불러오기
 */
function init() {
	setCategories();
	setPromotions();
	setProducts({start: 0,categoryId : 0,isCategoryClicked: false});
}

/**
 * @desc Categories 셋팅
 */
function setCategories(){
	
	var categorySendHeader = {
		method : 'GET',
		uri : '/api/categories'
	};
	
	// categoryResponse => key : {'items', value -> category list ( id,name) }, { totalCount , value->총 개수 }
	sendAjax(categorySendHeader, '', function(categoryResponse) {
		
		var categoryTemplate = document.querySelector('#categories-template').content;
		var items = categoryResponse.items;
	
		items.forEach(function(category) {
			var itemQuerySelector = categoryTemplate.querySelector('.item');
			
			itemQuerySelector.dataset.category = category.id;
			itemQuerySelector.dataset.count = category.count;
			categoryTemplate.querySelector('span').innerText = category.name;
			
			var categoryLi = document.importNode(categoryTemplate, true);
			document.querySelector('.event_tab_lst.tab_lst_min').appendChild(categoryLi);
		});
		
	});
}

/**
 * @desc promotion 슬라이더 애니메이션
 * @returns
 */
function setAnimatePromotions(){
	
	if(globalVariable.imageItemIndex > globalVariable.promotionLength) {
		globalVariable.imageItemIndex = 0;
	}
	document.querySelector('.visual_img').style.transform = 'translateX('+(-100* globalVariable.imageItemIndex)+'%)';
	globalVariable.imageItemIndex++;
}
/**
 * @desc Products 셋팅
 * @params sendProductData { start, categoryId ,isCategoryClicked} 
 */
function setProducts(sendProductData) {

	var productSendHeader = {
		method : 'GET',
		uri : '/api/products?start='+sendProductData.start+'&categoryId='+sendProductData.categoryId
	};
	
	// productResponse => { items : productList , totalCount : 카테고리별 총 갯수)
	sendAjax(productSendHeader, '', function(productResponse) {
		
		document.querySelector('.pink').innerText = productResponse.totalCount+'개';
		
		var productUl = document.querySelectorAll('.lst_event_box');
		
		if(sendProductData.isCategoryClicked) {
			productUl.forEach(function(ele){
				ele.innerHTML = '';
			});
		}
		
		var productTemplate = document.querySelector('#products-template').content;
		var items = productResponse.items;
		
		items.forEach(function(product, index) {
			
			productTemplate.querySelector('.item_book').href = '/' + product.displayInfoId;
			
			var img = productTemplate.querySelector('img');
			img.src = '/static/' + product.productImageUrl;
			img.alt = product.productDescription;
			
			productTemplate.querySelector('.event_txt_tit > span').innerText = product.productDescription;
			productTemplate.querySelector('.sm').innerText = product.placeName;
			productTemplate.querySelector('.event_txt_dsc').innerText = product.productContent;
			
			var productLi = document.importNode(productTemplate,true);
			productUl[index%2].appendChild(productLi);
			
			globalVariable.getProductCount+=1;
		});
		
		if(productResponse.totalCount <= globalVariable.getProductCount){
			document.querySelector('#moreButton').className = 'btn hide';
		}
	});
}

/**
 * @desc Promotions 셋팅
 */
function setPromotions(){

	var promotionSendHeader = {
		method : 'GET',
		uri : '/api/promotions'
	};
	
	// promotionResponse => {items : {id, productId, productImageUrl}}
	sendAjax(promotionSendHeader,'',function(promotionResponse){
		
		var promotionTemplate = document.querySelector('#promotions-template').content;
		var promotionUl = document.querySelector('.visual_img');
		
		var items = promotionResponse.items;
		
		items.forEach(function(promotion){
			promotionTemplate.querySelector('.item').style.backgroundImage = 'url("/static/'+promotion.productImageUrl+'")';
			
			var promotionLi = document.importNode(promotionTemplate,true);
			promotionUl.appendChild(promotionLi);
			globalVariable.promotionLength++;
		});
	});
	
}

/**
 * @desc category anchor 태그에 active class 지우기
 * @param e { event } 
 * @returns
 */
function removeAnchorActiveClass(e){
	
	var categoryAnchorTag = e.currentTarget.querySelectorAll('.anchor');
	
	categoryAnchorTag.forEach(function(ele){
		ele.className = 'anchor';
	});
}

/**
 * @desc category li click event 걸어주기
 */
function categoryClickEvent(){
	document.querySelector('.event_tab_lst.tab_lst_min').addEventListener('click',function(e){
		
		var target = e.target;
		var targetTagName = target.tagName.toLowerCase();
		
		if(targetTagName === 'ul'){
			return;
		}
		
		globalVariable.getProductCount = 0;
		removeAnchorActiveClass(e);
		
		var categoryId;
		
		if (targetTagName === 'li') {
			target.firstChild.className = 'anchor active';
			categoryId = target.dataset.category;
		} else if (targetTagName === 'a') {
			target.className = 'anchor active';
			categoryId = target.parentNode.dataset.category;
		} else if (targetTagName === 'span') {
			target.parentNode.className = 'anchor active';
			categoryId = target.parentNode.parentNode.dataset.category;
		} 
		
		setProducts({start: 0,categoryId: categoryId,isCategoryClicked: true});
		
		var moreButton = document.querySelector('#moreButton');
		
		moreButton.className = 'open btn';
		moreButton.dataset.start = 0;
		moreButton.dataset.category = categoryId;
		
	});
}

/**
 * @desc 더보기 버튼 클릭 이벤트
 * @returns
 */
function moreButtonClickEvent(){
	
	var moreButton = document.querySelector('#moreButton');
	
	moreButton.addEventListener('click',function(){
		
		moreButton.dataset.start = parseInt(moreButton.dataset.start)+1;
		setProducts({start: moreButton.dataset.start,categoryId: moreButton.dataset.category,isCategoryClicked: false});
	});
}

document.addEventListener('DOMContentLoaded', function() {
	
	init();
	categoryClickEvent();
	moreButtonClickEvent();
	
	setInterval(setAnimatePromotions,2000);
});
