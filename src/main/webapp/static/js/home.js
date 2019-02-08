/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, { enumerable: true, get: getter });
/******/ 		}
/******/ 	};
/******/
/******/ 	// define __esModule on exports
/******/ 	__webpack_require__.r = function(exports) {
/******/ 		if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
/******/ 			Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
/******/ 		}
/******/ 		Object.defineProperty(exports, '__esModule', { value: true });
/******/ 	};
/******/
/******/ 	// create a fake namespace object
/******/ 	// mode & 1: value is a module id, require it
/******/ 	// mode & 2: merge all properties of value into the ns
/******/ 	// mode & 4: return value when already ns object
/******/ 	// mode & 8|1: behave like require
/******/ 	__webpack_require__.t = function(value, mode) {
/******/ 		if(mode & 1) value = __webpack_require__(value);
/******/ 		if(mode & 8) return value;
/******/ 		if((mode & 4) && typeof value === 'object' && value && value.__esModule) return value;
/******/ 		var ns = Object.create(null);
/******/ 		__webpack_require__.r(ns);
/******/ 		Object.defineProperty(ns, 'default', { enumerable: true, value: value });
/******/ 		if(mode & 2 && typeof value != 'string') for(var key in value) __webpack_require__.d(ns, key, function(key) { return value[key]; }.bind(null, key));
/******/ 		return ns;
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "/static/js/";
/******/
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = "./main.js");
/******/ })
/************************************************************************/
/******/ ({

/***/ "./main.js":
/*!*****************!*\
  !*** ./main.js ***!
  \*****************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

/**
 * @desc 전역 변수
 */
var globalVariable = {
		getProductCount : 0,	// 해당 카테고리의 현재 리스트로 보여진 product의 갯수
		promotionIndex: 0,		// 현재 프로모션 위치 
		promotionLength : -1 	// 프로모션 전체 길이
};
var sendAjax = __webpack_require__(/*! ./sendAjax */ "./sendAjax.js");

/**
 * @desc dom 로딩후 시작
 */
document.addEventListener('DOMContentLoaded', function() {
	
	init();
	categoryClickEvent();
	moreButtonClickEvent();
	
	setInterval(setAnimatePromotions,2000);
});


/**
 * @desc 카테고리 불러오기 및 리스트 불러오기
 */
function init() {
	setCategories();
	setPromotions();
	setProducts({start: 0, categoryId : 0, isCategoryClicked: false});
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
		
		var categoryTemplate = document.querySelector('#categories_template').content;
		var items = categoryResponse.items;
	
		items.forEach(function(category) {
			var itemQuerySelector = categoryTemplate.querySelector('.item');
			
			itemQuerySelector.dataset.category = category.id;
			itemQuerySelector.dataset.count = category.count;
			categoryTemplate.querySelector('span').innerText = category.name;
			
			var categoryLi = document.importNode(categoryTemplate, true);
			document.querySelector('#categories_tab').appendChild(categoryLi);
		});
		
	});
}

/**
 * @desc promotion 슬라이더 애니메이션
 * @returns
 */
function setAnimatePromotions(){
	
	if(globalVariable.promotionIndex > globalVariable.promotionLength) {
		globalVariable.promotionIndex = 0;
	}
	
	document.querySelector('#promotions_ul').style.transform = 'translateX('+(-100* globalVariable.promotionIndex)+'%)';
	globalVariable.promotionIndex++;
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
		
		document.querySelector('#products_count').innerText = productResponse.totalCount;
		
		var productUl = document.querySelectorAll('.products_ul');
		
		if(sendProductData.isCategoryClicked) {
			productUl.forEach(function(ele){
				ele.innerHTML = '';
			});
		}
		
		var productTemplate = document.querySelector('#products_template').content;
		var items = productResponse.items;
		
		items.forEach(function(product, index) {
			
			productTemplate.querySelector('.item_book').href = '/detail/' + product.displayInfoId;
			
			var img = productTemplate.querySelector('img');
			img.src = '/static/' + product.productImageUrl;
			img.alt = product.productDescription;
			
			productTemplate.querySelector('.product_description').innerText = product.productDescription;
			productTemplate.querySelector('.place_name').innerText = product.placeName;
			productTemplate.querySelector('.product_content').innerText = product.productContent;
			
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
		
		var promotionTemplate = document.querySelector('#promotions_template').content;
		var promotionUl = document.querySelector('#promotions_ul');
		
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
	
	document.querySelector('#categories_tab').addEventListener('click',function(e){
		
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
		
		setProducts({start: 0, categoryId: categoryId, isCategoryClicked: true});
		
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
		setProducts({start: moreButton.dataset.start, categoryId: moreButton.dataset.category, isCategoryClicked: false});
	});
}


/***/ }),

/***/ "./sendAjax.js":
/*!*********************!*\
  !*** ./sendAjax.js ***!
  \*********************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * @desc sendAjax 비동기 요청 ( 공통 함수 )
 * @param sendHeader ( method, uri)
 * @param sendData ( 보낼 데이터 )
 * @param callback ( 데이터를 가지고 온뒤 수행할 callback 함수)
 * @returns xhr.response ( JSON 형태로 parsing )
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

module.exports = sendAjax;

/***/ })

/******/ });
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vd2VicGFjay9ib290c3RyYXAiLCJ3ZWJwYWNrOi8vLy4vbWFpbi5qcyIsIndlYnBhY2s6Ly8vLi9zZW5kQWpheC5qcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiO0FBQUE7QUFDQTs7QUFFQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7O0FBRUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7OztBQUdBO0FBQ0E7O0FBRUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQSxrREFBMEMsZ0NBQWdDO0FBQzFFO0FBQ0E7O0FBRUE7QUFDQTtBQUNBO0FBQ0EsZ0VBQXdELGtCQUFrQjtBQUMxRTtBQUNBLHlEQUFpRCxjQUFjO0FBQy9EOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQSxpREFBeUMsaUNBQWlDO0FBQzFFLHdIQUFnSCxtQkFBbUIsRUFBRTtBQUNySTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBLG1DQUEyQiwwQkFBMEIsRUFBRTtBQUN2RCx5Q0FBaUMsZUFBZTtBQUNoRDtBQUNBO0FBQ0E7O0FBRUE7QUFDQSw4REFBc0QsK0RBQStEOztBQUVySDtBQUNBOzs7QUFHQTtBQUNBOzs7Ozs7Ozs7Ozs7QUNsRkE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBLGVBQWUsbUJBQU8sQ0FBQyxpQ0FBWTs7QUFFbkM7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBOztBQUVBO0FBQ0EsQ0FBQzs7O0FBR0Q7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0EsY0FBYyxtREFBbUQ7QUFDakU7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7O0FBRUEsK0JBQStCLDRDQUE0QyxHQUFHO0FBQzlFOztBQUVBO0FBQ0E7O0FBRUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBLEdBQUc7O0FBRUgsRUFBRTtBQUNGOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBO0FBQ0EsNEJBQTRCLHNDO0FBQzVCO0FBQ0E7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7O0FBRUEsd0JBQXdCO0FBQ3hCOztBQUVBOztBQUVBOztBQUVBO0FBQ0E7QUFDQTtBQUNBLElBQUk7QUFDSjs7QUFFQTtBQUNBOztBQUVBOztBQUVBOztBQUVBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTs7QUFFQTtBQUNBLEdBQUc7O0FBRUg7QUFDQTtBQUNBO0FBQ0EsRUFBRTtBQUNGOztBQUVBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBOztBQUVBLDBCQUEwQixTQUFTO0FBQ25DOztBQUVBO0FBQ0E7O0FBRUE7O0FBRUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQSxHQUFHO0FBQ0gsRUFBRTs7QUFFRjs7QUFFQTtBQUNBO0FBQ0EsYUFBYSxRO0FBQ2I7QUFDQTtBQUNBOztBQUVBOztBQUVBO0FBQ0E7QUFDQSxFQUFFO0FBQ0Y7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7O0FBRUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0EsRzs7QUFFQTtBQUNBOztBQUVBOztBQUVBO0FBQ0E7QUFDQTtBQUNBLEdBQUc7QUFDSDtBQUNBO0FBQ0EsR0FBRztBQUNIO0FBQ0E7QUFDQSxHOztBQUVBLGVBQWUsMERBQTBEOztBQUV6RTs7QUFFQTtBQUNBO0FBQ0E7O0FBRUEsRUFBRTtBQUNGLEM7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTs7QUFFQTs7QUFFQTtBQUNBLGVBQWUsbUdBQW1HO0FBQ2xILEVBQUU7QUFDRjs7Ozs7Ozs7Ozs7O0FDaE9BO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTs7QUFFQTtBQUNBOztBQUVBOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7O0FBRUEsMEIiLCJmaWxlIjoiaG9tZS5qcyIsInNvdXJjZXNDb250ZW50IjpbIiBcdC8vIFRoZSBtb2R1bGUgY2FjaGVcbiBcdHZhciBpbnN0YWxsZWRNb2R1bGVzID0ge307XG5cbiBcdC8vIFRoZSByZXF1aXJlIGZ1bmN0aW9uXG4gXHRmdW5jdGlvbiBfX3dlYnBhY2tfcmVxdWlyZV9fKG1vZHVsZUlkKSB7XG5cbiBcdFx0Ly8gQ2hlY2sgaWYgbW9kdWxlIGlzIGluIGNhY2hlXG4gXHRcdGlmKGluc3RhbGxlZE1vZHVsZXNbbW9kdWxlSWRdKSB7XG4gXHRcdFx0cmV0dXJuIGluc3RhbGxlZE1vZHVsZXNbbW9kdWxlSWRdLmV4cG9ydHM7XG4gXHRcdH1cbiBcdFx0Ly8gQ3JlYXRlIGEgbmV3IG1vZHVsZSAoYW5kIHB1dCBpdCBpbnRvIHRoZSBjYWNoZSlcbiBcdFx0dmFyIG1vZHVsZSA9IGluc3RhbGxlZE1vZHVsZXNbbW9kdWxlSWRdID0ge1xuIFx0XHRcdGk6IG1vZHVsZUlkLFxuIFx0XHRcdGw6IGZhbHNlLFxuIFx0XHRcdGV4cG9ydHM6IHt9XG4gXHRcdH07XG5cbiBcdFx0Ly8gRXhlY3V0ZSB0aGUgbW9kdWxlIGZ1bmN0aW9uXG4gXHRcdG1vZHVsZXNbbW9kdWxlSWRdLmNhbGwobW9kdWxlLmV4cG9ydHMsIG1vZHVsZSwgbW9kdWxlLmV4cG9ydHMsIF9fd2VicGFja19yZXF1aXJlX18pO1xuXG4gXHRcdC8vIEZsYWcgdGhlIG1vZHVsZSBhcyBsb2FkZWRcbiBcdFx0bW9kdWxlLmwgPSB0cnVlO1xuXG4gXHRcdC8vIFJldHVybiB0aGUgZXhwb3J0cyBvZiB0aGUgbW9kdWxlXG4gXHRcdHJldHVybiBtb2R1bGUuZXhwb3J0cztcbiBcdH1cblxuXG4gXHQvLyBleHBvc2UgdGhlIG1vZHVsZXMgb2JqZWN0IChfX3dlYnBhY2tfbW9kdWxlc19fKVxuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5tID0gbW9kdWxlcztcblxuIFx0Ly8gZXhwb3NlIHRoZSBtb2R1bGUgY2FjaGVcbiBcdF9fd2VicGFja19yZXF1aXJlX18uYyA9IGluc3RhbGxlZE1vZHVsZXM7XG5cbiBcdC8vIGRlZmluZSBnZXR0ZXIgZnVuY3Rpb24gZm9yIGhhcm1vbnkgZXhwb3J0c1xuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5kID0gZnVuY3Rpb24oZXhwb3J0cywgbmFtZSwgZ2V0dGVyKSB7XG4gXHRcdGlmKCFfX3dlYnBhY2tfcmVxdWlyZV9fLm8oZXhwb3J0cywgbmFtZSkpIHtcbiBcdFx0XHRPYmplY3QuZGVmaW5lUHJvcGVydHkoZXhwb3J0cywgbmFtZSwgeyBlbnVtZXJhYmxlOiB0cnVlLCBnZXQ6IGdldHRlciB9KTtcbiBcdFx0fVxuIFx0fTtcblxuIFx0Ly8gZGVmaW5lIF9fZXNNb2R1bGUgb24gZXhwb3J0c1xuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5yID0gZnVuY3Rpb24oZXhwb3J0cykge1xuIFx0XHRpZih0eXBlb2YgU3ltYm9sICE9PSAndW5kZWZpbmVkJyAmJiBTeW1ib2wudG9TdHJpbmdUYWcpIHtcbiBcdFx0XHRPYmplY3QuZGVmaW5lUHJvcGVydHkoZXhwb3J0cywgU3ltYm9sLnRvU3RyaW5nVGFnLCB7IHZhbHVlOiAnTW9kdWxlJyB9KTtcbiBcdFx0fVxuIFx0XHRPYmplY3QuZGVmaW5lUHJvcGVydHkoZXhwb3J0cywgJ19fZXNNb2R1bGUnLCB7IHZhbHVlOiB0cnVlIH0pO1xuIFx0fTtcblxuIFx0Ly8gY3JlYXRlIGEgZmFrZSBuYW1lc3BhY2Ugb2JqZWN0XG4gXHQvLyBtb2RlICYgMTogdmFsdWUgaXMgYSBtb2R1bGUgaWQsIHJlcXVpcmUgaXRcbiBcdC8vIG1vZGUgJiAyOiBtZXJnZSBhbGwgcHJvcGVydGllcyBvZiB2YWx1ZSBpbnRvIHRoZSBuc1xuIFx0Ly8gbW9kZSAmIDQ6IHJldHVybiB2YWx1ZSB3aGVuIGFscmVhZHkgbnMgb2JqZWN0XG4gXHQvLyBtb2RlICYgOHwxOiBiZWhhdmUgbGlrZSByZXF1aXJlXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLnQgPSBmdW5jdGlvbih2YWx1ZSwgbW9kZSkge1xuIFx0XHRpZihtb2RlICYgMSkgdmFsdWUgPSBfX3dlYnBhY2tfcmVxdWlyZV9fKHZhbHVlKTtcbiBcdFx0aWYobW9kZSAmIDgpIHJldHVybiB2YWx1ZTtcbiBcdFx0aWYoKG1vZGUgJiA0KSAmJiB0eXBlb2YgdmFsdWUgPT09ICdvYmplY3QnICYmIHZhbHVlICYmIHZhbHVlLl9fZXNNb2R1bGUpIHJldHVybiB2YWx1ZTtcbiBcdFx0dmFyIG5zID0gT2JqZWN0LmNyZWF0ZShudWxsKTtcbiBcdFx0X193ZWJwYWNrX3JlcXVpcmVfXy5yKG5zKTtcbiBcdFx0T2JqZWN0LmRlZmluZVByb3BlcnR5KG5zLCAnZGVmYXVsdCcsIHsgZW51bWVyYWJsZTogdHJ1ZSwgdmFsdWU6IHZhbHVlIH0pO1xuIFx0XHRpZihtb2RlICYgMiAmJiB0eXBlb2YgdmFsdWUgIT0gJ3N0cmluZycpIGZvcih2YXIga2V5IGluIHZhbHVlKSBfX3dlYnBhY2tfcmVxdWlyZV9fLmQobnMsIGtleSwgZnVuY3Rpb24oa2V5KSB7IHJldHVybiB2YWx1ZVtrZXldOyB9LmJpbmQobnVsbCwga2V5KSk7XG4gXHRcdHJldHVybiBucztcbiBcdH07XG5cbiBcdC8vIGdldERlZmF1bHRFeHBvcnQgZnVuY3Rpb24gZm9yIGNvbXBhdGliaWxpdHkgd2l0aCBub24taGFybW9ueSBtb2R1bGVzXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLm4gPSBmdW5jdGlvbihtb2R1bGUpIHtcbiBcdFx0dmFyIGdldHRlciA9IG1vZHVsZSAmJiBtb2R1bGUuX19lc01vZHVsZSA/XG4gXHRcdFx0ZnVuY3Rpb24gZ2V0RGVmYXVsdCgpIHsgcmV0dXJuIG1vZHVsZVsnZGVmYXVsdCddOyB9IDpcbiBcdFx0XHRmdW5jdGlvbiBnZXRNb2R1bGVFeHBvcnRzKCkgeyByZXR1cm4gbW9kdWxlOyB9O1xuIFx0XHRfX3dlYnBhY2tfcmVxdWlyZV9fLmQoZ2V0dGVyLCAnYScsIGdldHRlcik7XG4gXHRcdHJldHVybiBnZXR0ZXI7XG4gXHR9O1xuXG4gXHQvLyBPYmplY3QucHJvdG90eXBlLmhhc093blByb3BlcnR5LmNhbGxcbiBcdF9fd2VicGFja19yZXF1aXJlX18ubyA9IGZ1bmN0aW9uKG9iamVjdCwgcHJvcGVydHkpIHsgcmV0dXJuIE9iamVjdC5wcm90b3R5cGUuaGFzT3duUHJvcGVydHkuY2FsbChvYmplY3QsIHByb3BlcnR5KTsgfTtcblxuIFx0Ly8gX193ZWJwYWNrX3B1YmxpY19wYXRoX19cbiBcdF9fd2VicGFja19yZXF1aXJlX18ucCA9IFwiL3N0YXRpYy9qcy9cIjtcblxuXG4gXHQvLyBMb2FkIGVudHJ5IG1vZHVsZSBhbmQgcmV0dXJuIGV4cG9ydHNcbiBcdHJldHVybiBfX3dlYnBhY2tfcmVxdWlyZV9fKF9fd2VicGFja19yZXF1aXJlX18ucyA9IFwiLi9tYWluLmpzXCIpO1xuIiwiLyoqXHJcbiAqIEBkZXNjIOyghOyXrSDrs4DsiJhcclxuICovXHJcbnZhciBnbG9iYWxWYXJpYWJsZSA9IHtcclxuXHRcdGdldFByb2R1Y3RDb3VudCA6IDAsXHQvLyDtlbTri7kg7Lm07YWM6rOg66as7J2YIO2YhOyerCDrpqzsiqTtirjroZwg67O07Jes7KeEIHByb2R1Y3TsnZgg6rCv7IiYXHJcblx0XHRwcm9tb3Rpb25JbmRleDogMCxcdFx0Ly8g7ZiE7J6sIO2UhOuhnOuqqOyFmCDsnITsuZggXHJcblx0XHRwcm9tb3Rpb25MZW5ndGggOiAtMSBcdC8vIO2UhOuhnOuqqOyFmCDsoITssrQg6ri47J20XHJcbn07XHJcbnZhciBzZW5kQWpheCA9IHJlcXVpcmUoJy4vc2VuZEFqYXgnKTtcclxuXHJcbi8qKlxyXG4gKiBAZGVzYyBkb20g66Gc65Sp7ZuEIOyLnOyekVxyXG4gKi9cclxuZG9jdW1lbnQuYWRkRXZlbnRMaXN0ZW5lcignRE9NQ29udGVudExvYWRlZCcsIGZ1bmN0aW9uKCkge1xyXG5cdFxyXG5cdGluaXQoKTtcclxuXHRjYXRlZ29yeUNsaWNrRXZlbnQoKTtcclxuXHRtb3JlQnV0dG9uQ2xpY2tFdmVudCgpO1xyXG5cdFxyXG5cdHNldEludGVydmFsKHNldEFuaW1hdGVQcm9tb3Rpb25zLDIwMDApO1xyXG59KTtcclxuXHJcblxyXG4vKipcclxuICogQGRlc2Mg7Lm07YWM6rOg66asIOu2iOufrOyYpOq4sCDrsI8g66as7Iqk7Yq4IOu2iOufrOyYpOq4sFxyXG4gKi9cclxuZnVuY3Rpb24gaW5pdCgpIHtcclxuXHRzZXRDYXRlZ29yaWVzKCk7XHJcblx0c2V0UHJvbW90aW9ucygpO1xyXG5cdHNldFByb2R1Y3RzKHtzdGFydDogMCwgY2F0ZWdvcnlJZCA6IDAsIGlzQ2F0ZWdvcnlDbGlja2VkOiBmYWxzZX0pO1xyXG59XHJcblxyXG4vKipcclxuICogQGRlc2MgQ2F0ZWdvcmllcyDshYvtjIVcclxuICovXHJcbmZ1bmN0aW9uIHNldENhdGVnb3JpZXMoKXtcclxuXHRcclxuXHR2YXIgY2F0ZWdvcnlTZW5kSGVhZGVyID0ge1xyXG5cdFx0bWV0aG9kIDogJ0dFVCcsXHJcblx0XHR1cmkgOiAnL2FwaS9jYXRlZ29yaWVzJ1xyXG5cdH07XHJcblx0XHJcblx0Ly8gY2F0ZWdvcnlSZXNwb25zZSA9PiBrZXkgOiB7J2l0ZW1zJywgdmFsdWUgLT4gY2F0ZWdvcnkgbGlzdCAoIGlkLG5hbWUpIH0sIHsgdG90YWxDb3VudCAsIHZhbHVlLT7stJ0g6rCc7IiYIH1cclxuXHRzZW5kQWpheChjYXRlZ29yeVNlbmRIZWFkZXIsICcnLCBmdW5jdGlvbihjYXRlZ29yeVJlc3BvbnNlKSB7XHJcblx0XHRcclxuXHRcdHZhciBjYXRlZ29yeVRlbXBsYXRlID0gZG9jdW1lbnQucXVlcnlTZWxlY3RvcignI2NhdGVnb3JpZXNfdGVtcGxhdGUnKS5jb250ZW50O1xyXG5cdFx0dmFyIGl0ZW1zID0gY2F0ZWdvcnlSZXNwb25zZS5pdGVtcztcclxuXHRcclxuXHRcdGl0ZW1zLmZvckVhY2goZnVuY3Rpb24oY2F0ZWdvcnkpIHtcclxuXHRcdFx0dmFyIGl0ZW1RdWVyeVNlbGVjdG9yID0gY2F0ZWdvcnlUZW1wbGF0ZS5xdWVyeVNlbGVjdG9yKCcuaXRlbScpO1xyXG5cdFx0XHRcclxuXHRcdFx0aXRlbVF1ZXJ5U2VsZWN0b3IuZGF0YXNldC5jYXRlZ29yeSA9IGNhdGVnb3J5LmlkO1xyXG5cdFx0XHRpdGVtUXVlcnlTZWxlY3Rvci5kYXRhc2V0LmNvdW50ID0gY2F0ZWdvcnkuY291bnQ7XHJcblx0XHRcdGNhdGVnb3J5VGVtcGxhdGUucXVlcnlTZWxlY3Rvcignc3BhbicpLmlubmVyVGV4dCA9IGNhdGVnb3J5Lm5hbWU7XHJcblx0XHRcdFxyXG5cdFx0XHR2YXIgY2F0ZWdvcnlMaSA9IGRvY3VtZW50LmltcG9ydE5vZGUoY2F0ZWdvcnlUZW1wbGF0ZSwgdHJ1ZSk7XHJcblx0XHRcdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJyNjYXRlZ29yaWVzX3RhYicpLmFwcGVuZENoaWxkKGNhdGVnb3J5TGkpO1xyXG5cdFx0fSk7XHJcblx0XHRcclxuXHR9KTtcclxufVxyXG5cclxuLyoqXHJcbiAqIEBkZXNjIHByb21vdGlvbiDsiqzrnbzsnbTrjZQg7JWg64uI66mU7J207IWYXHJcbiAqIEByZXR1cm5zXHJcbiAqL1xyXG5mdW5jdGlvbiBzZXRBbmltYXRlUHJvbW90aW9ucygpe1xyXG5cdFxyXG5cdGlmKGdsb2JhbFZhcmlhYmxlLnByb21vdGlvbkluZGV4ID4gZ2xvYmFsVmFyaWFibGUucHJvbW90aW9uTGVuZ3RoKSB7XHJcblx0XHRnbG9iYWxWYXJpYWJsZS5wcm9tb3Rpb25JbmRleCA9IDA7XHJcblx0fVxyXG5cdFxyXG5cdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJyNwcm9tb3Rpb25zX3VsJykuc3R5bGUudHJhbnNmb3JtID0gJ3RyYW5zbGF0ZVgoJysoLTEwMCogZ2xvYmFsVmFyaWFibGUucHJvbW90aW9uSW5kZXgpKyclKSc7XHJcblx0Z2xvYmFsVmFyaWFibGUucHJvbW90aW9uSW5kZXgrKztcclxufVxyXG5cclxuLyoqXHJcbiAqIEBkZXNjIFByb2R1Y3RzIOyFi+2MhVxyXG4gKiBAcGFyYW1zIHNlbmRQcm9kdWN0RGF0YSB7IHN0YXJ0LCBjYXRlZ29yeUlkICxpc0NhdGVnb3J5Q2xpY2tlZH0gXHJcbiAqL1xyXG5mdW5jdGlvbiBzZXRQcm9kdWN0cyhzZW5kUHJvZHVjdERhdGEpIHtcclxuXHJcblx0dmFyIHByb2R1Y3RTZW5kSGVhZGVyID0ge1xyXG5cdFx0bWV0aG9kIDogJ0dFVCcsXHJcblx0XHR1cmkgOiAnL2FwaS9wcm9kdWN0cz9zdGFydD0nK3NlbmRQcm9kdWN0RGF0YS5zdGFydCsnJmNhdGVnb3J5SWQ9JytzZW5kUHJvZHVjdERhdGEuY2F0ZWdvcnlJZFxyXG5cdH07XHJcblx0XHJcblx0Ly8gcHJvZHVjdFJlc3BvbnNlID0+IHsgaXRlbXMgOiBwcm9kdWN0TGlzdCAsIHRvdGFsQ291bnQgOiDsubTthYzqs6Drpqzrs4Qg7LSdIOqwr+yImClcclxuXHRzZW5kQWpheChwcm9kdWN0U2VuZEhlYWRlciwgJycsIGZ1bmN0aW9uKHByb2R1Y3RSZXNwb25zZSkge1xyXG5cdFx0XHJcblx0XHRkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcjcHJvZHVjdHNfY291bnQnKS5pbm5lclRleHQgPSBwcm9kdWN0UmVzcG9uc2UudG90YWxDb3VudDtcclxuXHRcdFxyXG5cdFx0dmFyIHByb2R1Y3RVbCA9IGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3JBbGwoJy5wcm9kdWN0c191bCcpO1xyXG5cdFx0XHJcblx0XHRpZihzZW5kUHJvZHVjdERhdGEuaXNDYXRlZ29yeUNsaWNrZWQpIHtcclxuXHRcdFx0cHJvZHVjdFVsLmZvckVhY2goZnVuY3Rpb24oZWxlKXtcclxuXHRcdFx0XHRlbGUuaW5uZXJIVE1MID0gJyc7XHJcblx0XHRcdH0pO1xyXG5cdFx0fVxyXG5cdFx0XHJcblx0XHR2YXIgcHJvZHVjdFRlbXBsYXRlID0gZG9jdW1lbnQucXVlcnlTZWxlY3RvcignI3Byb2R1Y3RzX3RlbXBsYXRlJykuY29udGVudDtcclxuXHRcdHZhciBpdGVtcyA9IHByb2R1Y3RSZXNwb25zZS5pdGVtcztcclxuXHRcdFxyXG5cdFx0aXRlbXMuZm9yRWFjaChmdW5jdGlvbihwcm9kdWN0LCBpbmRleCkge1xyXG5cdFx0XHRcclxuXHRcdFx0cHJvZHVjdFRlbXBsYXRlLnF1ZXJ5U2VsZWN0b3IoJy5pdGVtX2Jvb2snKS5ocmVmID0gJy9kZXRhaWwvJyArIHByb2R1Y3QuZGlzcGxheUluZm9JZDtcclxuXHRcdFx0XHJcblx0XHRcdHZhciBpbWcgPSBwcm9kdWN0VGVtcGxhdGUucXVlcnlTZWxlY3RvcignaW1nJyk7XHJcblx0XHRcdGltZy5zcmMgPSAnL3N0YXRpYy8nICsgcHJvZHVjdC5wcm9kdWN0SW1hZ2VVcmw7XHJcblx0XHRcdGltZy5hbHQgPSBwcm9kdWN0LnByb2R1Y3REZXNjcmlwdGlvbjtcclxuXHRcdFx0XHJcblx0XHRcdHByb2R1Y3RUZW1wbGF0ZS5xdWVyeVNlbGVjdG9yKCcucHJvZHVjdF9kZXNjcmlwdGlvbicpLmlubmVyVGV4dCA9IHByb2R1Y3QucHJvZHVjdERlc2NyaXB0aW9uO1xyXG5cdFx0XHRwcm9kdWN0VGVtcGxhdGUucXVlcnlTZWxlY3RvcignLnBsYWNlX25hbWUnKS5pbm5lclRleHQgPSBwcm9kdWN0LnBsYWNlTmFtZTtcclxuXHRcdFx0cHJvZHVjdFRlbXBsYXRlLnF1ZXJ5U2VsZWN0b3IoJy5wcm9kdWN0X2NvbnRlbnQnKS5pbm5lclRleHQgPSBwcm9kdWN0LnByb2R1Y3RDb250ZW50O1xyXG5cdFx0XHRcclxuXHRcdFx0dmFyIHByb2R1Y3RMaSA9IGRvY3VtZW50LmltcG9ydE5vZGUocHJvZHVjdFRlbXBsYXRlLHRydWUpO1xyXG5cdFx0XHRwcm9kdWN0VWxbaW5kZXglMl0uYXBwZW5kQ2hpbGQocHJvZHVjdExpKTtcclxuXHRcdFx0XHJcblx0XHRcdGdsb2JhbFZhcmlhYmxlLmdldFByb2R1Y3RDb3VudCs9MTtcclxuXHRcdH0pO1xyXG5cdFx0XHJcblx0XHRpZihwcm9kdWN0UmVzcG9uc2UudG90YWxDb3VudCA8PSBnbG9iYWxWYXJpYWJsZS5nZXRQcm9kdWN0Q291bnQpe1xyXG5cdFx0XHRkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcjbW9yZUJ1dHRvbicpLmNsYXNzTmFtZSA9ICdidG4gaGlkZSc7XHJcblx0XHR9XHJcblx0fSk7XHJcbn1cclxuXHJcbi8qKlxyXG4gKiBAZGVzYyBQcm9tb3Rpb25zIOyFi+2MhVxyXG4gKi9cclxuZnVuY3Rpb24gc2V0UHJvbW90aW9ucygpe1xyXG5cclxuXHR2YXIgcHJvbW90aW9uU2VuZEhlYWRlciA9IHtcclxuXHRcdG1ldGhvZCA6ICdHRVQnLFxyXG5cdFx0dXJpIDogJy9hcGkvcHJvbW90aW9ucydcclxuXHR9O1xyXG5cdFxyXG5cdC8vIHByb21vdGlvblJlc3BvbnNlID0+IHtpdGVtcyA6IHtpZCwgcHJvZHVjdElkLCBwcm9kdWN0SW1hZ2VVcmx9fVxyXG5cdHNlbmRBamF4KHByb21vdGlvblNlbmRIZWFkZXIsJycsZnVuY3Rpb24ocHJvbW90aW9uUmVzcG9uc2Upe1xyXG5cdFx0XHJcblx0XHR2YXIgcHJvbW90aW9uVGVtcGxhdGUgPSBkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcjcHJvbW90aW9uc190ZW1wbGF0ZScpLmNvbnRlbnQ7XHJcblx0XHR2YXIgcHJvbW90aW9uVWwgPSBkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcjcHJvbW90aW9uc191bCcpO1xyXG5cdFx0XHJcblx0XHR2YXIgaXRlbXMgPSBwcm9tb3Rpb25SZXNwb25zZS5pdGVtcztcclxuXHRcdFxyXG5cdFx0aXRlbXMuZm9yRWFjaChmdW5jdGlvbihwcm9tb3Rpb24pe1xyXG5cdFx0XHRwcm9tb3Rpb25UZW1wbGF0ZS5xdWVyeVNlbGVjdG9yKCcuaXRlbScpLnN0eWxlLmJhY2tncm91bmRJbWFnZSA9ICd1cmwoXCIvc3RhdGljLycrcHJvbW90aW9uLnByb2R1Y3RJbWFnZVVybCsnXCIpJztcclxuXHRcdFx0XHJcblx0XHRcdHZhciBwcm9tb3Rpb25MaSA9IGRvY3VtZW50LmltcG9ydE5vZGUocHJvbW90aW9uVGVtcGxhdGUsdHJ1ZSk7XHJcblx0XHRcdHByb21vdGlvblVsLmFwcGVuZENoaWxkKHByb21vdGlvbkxpKTtcclxuXHRcdFx0Z2xvYmFsVmFyaWFibGUucHJvbW90aW9uTGVuZ3RoKys7XHJcblx0XHR9KTtcclxuXHR9KTtcclxuXHRcclxufVxyXG5cclxuLyoqXHJcbiAqIEBkZXNjIGNhdGVnb3J5IGFuY2hvciDtg5zqt7jsl5AgYWN0aXZlIGNsYXNzIOyngOyasOq4sFxyXG4gKiBAcGFyYW0gZSB7IGV2ZW50IH0gXHJcbiAqIEByZXR1cm5zXHJcbiAqL1xyXG5mdW5jdGlvbiByZW1vdmVBbmNob3JBY3RpdmVDbGFzcyhlKXtcclxuXHRcclxuXHR2YXIgY2F0ZWdvcnlBbmNob3JUYWcgPSBlLmN1cnJlbnRUYXJnZXQucXVlcnlTZWxlY3RvckFsbCgnLmFuY2hvcicpO1xyXG5cdFxyXG5cdGNhdGVnb3J5QW5jaG9yVGFnLmZvckVhY2goZnVuY3Rpb24oZWxlKXtcclxuXHRcdGVsZS5jbGFzc05hbWUgPSAnYW5jaG9yJztcclxuXHR9KTtcclxufVxyXG5cclxuLyoqXHJcbiAqIEBkZXNjIGNhdGVnb3J5IGxpIGNsaWNrIGV2ZW50IOqxuOyWtOyjvOq4sFxyXG4gKi9cclxuZnVuY3Rpb24gY2F0ZWdvcnlDbGlja0V2ZW50KCl7XHJcblx0XHJcblx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignI2NhdGVnb3JpZXNfdGFiJykuYWRkRXZlbnRMaXN0ZW5lcignY2xpY2snLGZ1bmN0aW9uKGUpe1xyXG5cdFx0XHJcblx0XHR2YXIgdGFyZ2V0ID0gZS50YXJnZXQ7XHJcblx0XHR2YXIgdGFyZ2V0VGFnTmFtZSA9IHRhcmdldC50YWdOYW1lLnRvTG93ZXJDYXNlKCk7XHJcblx0XHRcclxuXHRcdGlmKHRhcmdldFRhZ05hbWUgPT09ICd1bCcpe1xyXG5cdFx0XHRyZXR1cm47XHJcblx0XHR9IFxyXG5cdFx0XHJcblx0XHRnbG9iYWxWYXJpYWJsZS5nZXRQcm9kdWN0Q291bnQgPSAwO1xyXG5cdFx0cmVtb3ZlQW5jaG9yQWN0aXZlQ2xhc3MoZSk7XHJcblx0XHRcclxuXHRcdHZhciBjYXRlZ29yeUlkO1xyXG5cdFx0XHJcblx0XHRpZiAodGFyZ2V0VGFnTmFtZSA9PT0gJ2xpJykge1xyXG5cdFx0XHR0YXJnZXQuZmlyc3RDaGlsZC5jbGFzc05hbWUgPSAnYW5jaG9yIGFjdGl2ZSc7XHJcblx0XHRcdGNhdGVnb3J5SWQgPSB0YXJnZXQuZGF0YXNldC5jYXRlZ29yeTtcclxuXHRcdH0gZWxzZSBpZiAodGFyZ2V0VGFnTmFtZSA9PT0gJ2EnKSB7XHJcblx0XHRcdHRhcmdldC5jbGFzc05hbWUgPSAnYW5jaG9yIGFjdGl2ZSc7XHJcblx0XHRcdGNhdGVnb3J5SWQgPSB0YXJnZXQucGFyZW50Tm9kZS5kYXRhc2V0LmNhdGVnb3J5O1xyXG5cdFx0fSBlbHNlIGlmICh0YXJnZXRUYWdOYW1lID09PSAnc3BhbicpIHtcclxuXHRcdFx0dGFyZ2V0LnBhcmVudE5vZGUuY2xhc3NOYW1lID0gJ2FuY2hvciBhY3RpdmUnO1xyXG5cdFx0XHRjYXRlZ29yeUlkID0gdGFyZ2V0LnBhcmVudE5vZGUucGFyZW50Tm9kZS5kYXRhc2V0LmNhdGVnb3J5O1xyXG5cdFx0fSBcclxuXHRcdFxyXG5cdFx0c2V0UHJvZHVjdHMoe3N0YXJ0OiAwLCBjYXRlZ29yeUlkOiBjYXRlZ29yeUlkLCBpc0NhdGVnb3J5Q2xpY2tlZDogdHJ1ZX0pO1xyXG5cdFx0XHJcblx0XHR2YXIgbW9yZUJ1dHRvbiA9IGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJyNtb3JlQnV0dG9uJyk7XHJcblx0XHRcclxuXHRcdG1vcmVCdXR0b24uY2xhc3NOYW1lID0gJ29wZW4gYnRuJztcclxuXHRcdG1vcmVCdXR0b24uZGF0YXNldC5zdGFydCA9IDA7XHJcblx0XHRtb3JlQnV0dG9uLmRhdGFzZXQuY2F0ZWdvcnkgPSBjYXRlZ29yeUlkO1xyXG5cdFx0XHJcblx0fSk7XHJcbn0gXHJcblxyXG4vKipcclxuICogQGRlc2Mg642U67O06riwIOuyhO2KvCDtgbTrpq0g7J2067Kk7Yq4XHJcbiAqIEByZXR1cm5zXHJcbiAqL1xyXG5mdW5jdGlvbiBtb3JlQnV0dG9uQ2xpY2tFdmVudCgpe1xyXG5cdFxyXG5cdHZhciBtb3JlQnV0dG9uID0gZG9jdW1lbnQucXVlcnlTZWxlY3RvcignI21vcmVCdXR0b24nKTtcclxuXHRcclxuXHRtb3JlQnV0dG9uLmFkZEV2ZW50TGlzdGVuZXIoJ2NsaWNrJyxmdW5jdGlvbigpe1xyXG5cdFx0XHJcblx0XHRtb3JlQnV0dG9uLmRhdGFzZXQuc3RhcnQgPSBwYXJzZUludChtb3JlQnV0dG9uLmRhdGFzZXQuc3RhcnQpKzE7XHJcblx0XHRzZXRQcm9kdWN0cyh7c3RhcnQ6IG1vcmVCdXR0b24uZGF0YXNldC5zdGFydCwgY2F0ZWdvcnlJZDogbW9yZUJ1dHRvbi5kYXRhc2V0LmNhdGVnb3J5LCBpc0NhdGVnb3J5Q2xpY2tlZDogZmFsc2V9KTtcclxuXHR9KTtcclxufVxyXG4iLCIvKipcclxuICogQGRlc2Mgc2VuZEFqYXgg67mE64+Z6riwIOyalOyyrSAoIOqzte2GtSDtlajsiJggKVxyXG4gKiBAcGFyYW0gc2VuZEhlYWRlciAoIG1ldGhvZCwgdXJpKVxyXG4gKiBAcGFyYW0gc2VuZERhdGEgKCDrs7Trgrwg642w7J207YSwIClcclxuICogQHBhcmFtIGNhbGxiYWNrICgg642w7J207YSw66W8IOqwgOyngOqzoCDsmKjrkqQg7IiY7ZaJ7ZWgIGNhbGxiYWNrIO2VqOyImClcclxuICogQHJldHVybnMgeGhyLnJlc3BvbnNlICggSlNPTiDtmJXtg5zroZwgcGFyc2luZyApXHJcbiAqL1xyXG5mdW5jdGlvbiBzZW5kQWpheChzZW5kSGVhZGVyLCBzZW5kRGF0YSwgY2FsbGJhY2spIHtcclxuXHR2YXIgeGhyID0gbmV3IFhNTEh0dHBSZXF1ZXN0KCk7XHJcblxyXG5cdHhoci5vcGVuKHNlbmRIZWFkZXIubWV0aG9kLCBzZW5kSGVhZGVyLnVyaSwgdHJ1ZSk7XHJcblxyXG5cdHhoci5zZXRSZXF1ZXN0SGVhZGVyKFwiQ29udGVudC10eXBlXCIsIFwiYXBwbGljYXRpb24veC13d3ctZm9ybS11cmxlbmNvZGVkXCIpO1xyXG5cdHhoci5zZW5kKHNlbmREYXRhKTtcclxuXHJcblx0eGhyLm9ucmVhZHlzdGF0ZWNoYW5nZSA9IGZ1bmN0aW9uKCkge1xyXG5cclxuXHRcdGlmICh4aHIucmVhZHlTdGF0ZSA9PT0geGhyLkRPTkUgJiYgeGhyLnN0YXR1cyA9PT0gMjAwKSB7XHJcblx0XHRcdGNhbGxiYWNrLmFwcGx5KHRoaXMsIFsgSlNPTi5wYXJzZSh4aHIucmVzcG9uc2UpIF0pO1xyXG5cdFx0fVxyXG5cdH1cclxufVxyXG5cclxubW9kdWxlLmV4cG9ydHMgPSBzZW5kQWpheDsiXSwic291cmNlUm9vdCI6IiJ9