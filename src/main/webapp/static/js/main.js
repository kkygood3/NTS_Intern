/**
 * @desc 카테고리 불러오기 및 리스트 불러오기
 */
function init() {
	setCategories();
}

/**
 * @desc categories 셋팅
 */
function setCategories(){
	
	var categorySendHeader = {
		method : 'GET',
		uri : '/api/category'
	};
	
	// categoryMap => key -> 'categories', value -> category list ( id,name) 
	sendAjax(categorySendHeader, '', function(categoryMap) {
		var categoryTemplate = document.querySelector('#categories-template').content;
		var categories = categoryMap.categories;
		
		categories.forEach(function(category) {
			
			categoryTemplate.querySelector('.item').dataset.category = category.id;
			categoryTemplate.querySelector('span').innerText = category.name;
			
			var categoryLi = document.importNode(categoryTemplate, true);
			document.querySelector('.event_tab_lst.tab_lst_min').appendChild(categoryLi);
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
 * @returns
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
		}
	});
}

document.addEventListener("DOMContentLoaded", function() {
	init();
	categoryClickEvent();
});
