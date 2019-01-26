/**
 * @desc 카테고리 불러오기 및 리스트 불러오기
 */
function init() {
	setCategories();
	setPromotions();
	setProducts({start: 0,categoryId : '',isCategoryClicked: false});
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
		
		var productUl = document.querySelector('.lst_event_box');
		
		if(sendProductData.isCategoryClicked) {
			while(productUl.hasChildNodes()){
				productUl.removeChild(productUl.firstChild);
			}
		}
		
		var productTemplate = document.querySelector('#products-template').content;
		var items = productResponse.items;
		
		items.forEach(function(product) {
			
			productTemplate.querySelector('.item_book').href = '/' + product.displayInfoId;
			
			var img = productTemplate.querySelector('img');
			img.src = '/static/' + product.productImageUrl;
			img.alt = product.productDescription;
			
			productTemplate.querySelector('.event_txt_tit > span').innerText = product.productDescription;
			productTemplate.querySelector('.sm').innerText = product.placeName;
			productTemplate.querySelector('.event_txt_dsc').innerText = product.productContent;
			
			var productLi = document.importNode(productTemplate,true);
			productUl.appendChild(productLi);
			
		});
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
		
		console.log(promotionResponse);
		var promotionTemplate = document.querySelector('#promotions-template').content;
		var promotionUl = document.querySelector('.visual_img');
		
		var items = promotionResponse.items;
		
		items.forEach(function(promotion){
			
			console.log()
			promotionTemplate.querySelector('.item').style.backgroundImage = 'url("/static/'+promotion.productImageUrl+'")';
			
			var promotionLi = document.importNode(promotionTemplate,true);
			promotionUl.appendChild(promotionLi);
		});
	});
}

/**
 * @param sendHeader (method, uri)
 * @param sendData (보낼 데이터 )
 * @param callback ( 데이터를 가지고 온뒤 수행할 callback 함수)
 * @returns xhr.response
 */
function sendAjax(sendHeader, sendData, callback) {
	var xhr = new XMLHttpRequest();

	xhr.open(sendHeader.method, sendHeader.uri, true);

	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(sendData);

	xhr.onreadystatechange = function() {

		if (xhr.readyState === xhr.DONE && xhr.status === 200) {
			callback.apply(this, [ JSON.parse(xhr.response) ]);
		}
	}
}

/**
 * @desc category li click event 걸어주기
 */
function categoryClickEvent(){
	document.querySelector('.event_tab_lst.tab_lst_min').addEventListener('click',function(e){
		
		var target = e.target;
		if(target.tagName.toLowerCase() == 'a'){
			var categoryAnchorTag = e.currentTarget.querySelectorAll('.anchor');
			
			categoryAnchorTag.forEach(function(ele){
				ele.className ="anchor";
			});
			
			target.className = "anchor active";
			
			setProducts({start: 0,categoryId : target.parentNode.dataset.category, isCategoryClicked : true});
		}
	});
}

document.addEventListener("DOMContentLoaded", function() {
	init();
	categoryClickEvent();
});
