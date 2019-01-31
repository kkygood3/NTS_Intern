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
/******/ 	__webpack_require__.p = "/static";
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
			
			productTemplate.querySelector('.item_book').href = '/' + product.displayInfoId;
			
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
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vd2VicGFjay9ib290c3RyYXAiLCJ3ZWJwYWNrOi8vLy4vbWFpbi5qcyIsIndlYnBhY2s6Ly8vLi9zZW5kQWpheC5qcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiO0FBQUE7QUFDQTs7QUFFQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7O0FBRUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7OztBQUdBO0FBQ0E7O0FBRUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQSxrREFBMEMsZ0NBQWdDO0FBQzFFO0FBQ0E7O0FBRUE7QUFDQTtBQUNBO0FBQ0EsZ0VBQXdELGtCQUFrQjtBQUMxRTtBQUNBLHlEQUFpRCxjQUFjO0FBQy9EOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQSxpREFBeUMsaUNBQWlDO0FBQzFFLHdIQUFnSCxtQkFBbUIsRUFBRTtBQUNySTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBLG1DQUEyQiwwQkFBMEIsRUFBRTtBQUN2RCx5Q0FBaUMsZUFBZTtBQUNoRDtBQUNBO0FBQ0E7O0FBRUE7QUFDQSw4REFBc0QsK0RBQStEOztBQUVySDtBQUNBOzs7QUFHQTtBQUNBOzs7Ozs7Ozs7Ozs7QUNsRkE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBLGVBQWUsbUJBQU8sQ0FBQyxpQ0FBWTs7QUFFbkM7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBOztBQUVBO0FBQ0EsQ0FBQzs7O0FBR0Q7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0EsY0FBYyxtREFBbUQ7QUFDakU7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7O0FBRUEsK0JBQStCLDRDQUE0QyxHQUFHO0FBQzlFOztBQUVBO0FBQ0E7O0FBRUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBLEdBQUc7O0FBRUgsRUFBRTtBQUNGOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBO0FBQ0EsNEJBQTRCLHNDO0FBQzVCO0FBQ0E7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7O0FBRUEsd0JBQXdCO0FBQ3hCOztBQUVBOztBQUVBOztBQUVBO0FBQ0E7QUFDQTtBQUNBLElBQUk7QUFDSjs7QUFFQTtBQUNBOztBQUVBOztBQUVBOztBQUVBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTs7QUFFQTtBQUNBLEdBQUc7O0FBRUg7QUFDQTtBQUNBO0FBQ0EsRUFBRTtBQUNGOztBQUVBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBOztBQUVBLDBCQUEwQixTQUFTO0FBQ25DOztBQUVBO0FBQ0E7O0FBRUE7O0FBRUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQSxHQUFHO0FBQ0gsRUFBRTs7QUFFRjs7QUFFQTtBQUNBO0FBQ0EsYUFBYSxRO0FBQ2I7QUFDQTtBQUNBOztBQUVBOztBQUVBO0FBQ0E7QUFDQSxFQUFFO0FBQ0Y7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7O0FBRUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0EsRzs7QUFFQTtBQUNBOztBQUVBOztBQUVBO0FBQ0E7QUFDQTtBQUNBLEdBQUc7QUFDSDtBQUNBO0FBQ0EsR0FBRztBQUNIO0FBQ0E7QUFDQSxHOztBQUVBLGVBQWUsMERBQTBEOztBQUV6RTs7QUFFQTtBQUNBO0FBQ0E7O0FBRUEsRUFBRTtBQUNGLEM7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTs7QUFFQTs7QUFFQTtBQUNBLGVBQWUsbUdBQW1HO0FBQ2xILEVBQUU7QUFDRjs7Ozs7Ozs7Ozs7O0FDaE9BO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTs7QUFFQTtBQUNBOztBQUVBOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7O0FBRUEsMEIiLCJmaWxlIjoiaG9tZS5qcyIsInNvdXJjZXNDb250ZW50IjpbIiBcdC8vIFRoZSBtb2R1bGUgY2FjaGVcbiBcdHZhciBpbnN0YWxsZWRNb2R1bGVzID0ge307XG5cbiBcdC8vIFRoZSByZXF1aXJlIGZ1bmN0aW9uXG4gXHRmdW5jdGlvbiBfX3dlYnBhY2tfcmVxdWlyZV9fKG1vZHVsZUlkKSB7XG5cbiBcdFx0Ly8gQ2hlY2sgaWYgbW9kdWxlIGlzIGluIGNhY2hlXG4gXHRcdGlmKGluc3RhbGxlZE1vZHVsZXNbbW9kdWxlSWRdKSB7XG4gXHRcdFx0cmV0dXJuIGluc3RhbGxlZE1vZHVsZXNbbW9kdWxlSWRdLmV4cG9ydHM7XG4gXHRcdH1cbiBcdFx0Ly8gQ3JlYXRlIGEgbmV3IG1vZHVsZSAoYW5kIHB1dCBpdCBpbnRvIHRoZSBjYWNoZSlcbiBcdFx0dmFyIG1vZHVsZSA9IGluc3RhbGxlZE1vZHVsZXNbbW9kdWxlSWRdID0ge1xuIFx0XHRcdGk6IG1vZHVsZUlkLFxuIFx0XHRcdGw6IGZhbHNlLFxuIFx0XHRcdGV4cG9ydHM6IHt9XG4gXHRcdH07XG5cbiBcdFx0Ly8gRXhlY3V0ZSB0aGUgbW9kdWxlIGZ1bmN0aW9uXG4gXHRcdG1vZHVsZXNbbW9kdWxlSWRdLmNhbGwobW9kdWxlLmV4cG9ydHMsIG1vZHVsZSwgbW9kdWxlLmV4cG9ydHMsIF9fd2VicGFja19yZXF1aXJlX18pO1xuXG4gXHRcdC8vIEZsYWcgdGhlIG1vZHVsZSBhcyBsb2FkZWRcbiBcdFx0bW9kdWxlLmwgPSB0cnVlO1xuXG4gXHRcdC8vIFJldHVybiB0aGUgZXhwb3J0cyBvZiB0aGUgbW9kdWxlXG4gXHRcdHJldHVybiBtb2R1bGUuZXhwb3J0cztcbiBcdH1cblxuXG4gXHQvLyBleHBvc2UgdGhlIG1vZHVsZXMgb2JqZWN0IChfX3dlYnBhY2tfbW9kdWxlc19fKVxuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5tID0gbW9kdWxlcztcblxuIFx0Ly8gZXhwb3NlIHRoZSBtb2R1bGUgY2FjaGVcbiBcdF9fd2VicGFja19yZXF1aXJlX18uYyA9IGluc3RhbGxlZE1vZHVsZXM7XG5cbiBcdC8vIGRlZmluZSBnZXR0ZXIgZnVuY3Rpb24gZm9yIGhhcm1vbnkgZXhwb3J0c1xuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5kID0gZnVuY3Rpb24oZXhwb3J0cywgbmFtZSwgZ2V0dGVyKSB7XG4gXHRcdGlmKCFfX3dlYnBhY2tfcmVxdWlyZV9fLm8oZXhwb3J0cywgbmFtZSkpIHtcbiBcdFx0XHRPYmplY3QuZGVmaW5lUHJvcGVydHkoZXhwb3J0cywgbmFtZSwgeyBlbnVtZXJhYmxlOiB0cnVlLCBnZXQ6IGdldHRlciB9KTtcbiBcdFx0fVxuIFx0fTtcblxuIFx0Ly8gZGVmaW5lIF9fZXNNb2R1bGUgb24gZXhwb3J0c1xuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5yID0gZnVuY3Rpb24oZXhwb3J0cykge1xuIFx0XHRpZih0eXBlb2YgU3ltYm9sICE9PSAndW5kZWZpbmVkJyAmJiBTeW1ib2wudG9TdHJpbmdUYWcpIHtcbiBcdFx0XHRPYmplY3QuZGVmaW5lUHJvcGVydHkoZXhwb3J0cywgU3ltYm9sLnRvU3RyaW5nVGFnLCB7IHZhbHVlOiAnTW9kdWxlJyB9KTtcbiBcdFx0fVxuIFx0XHRPYmplY3QuZGVmaW5lUHJvcGVydHkoZXhwb3J0cywgJ19fZXNNb2R1bGUnLCB7IHZhbHVlOiB0cnVlIH0pO1xuIFx0fTtcblxuIFx0Ly8gY3JlYXRlIGEgZmFrZSBuYW1lc3BhY2Ugb2JqZWN0XG4gXHQvLyBtb2RlICYgMTogdmFsdWUgaXMgYSBtb2R1bGUgaWQsIHJlcXVpcmUgaXRcbiBcdC8vIG1vZGUgJiAyOiBtZXJnZSBhbGwgcHJvcGVydGllcyBvZiB2YWx1ZSBpbnRvIHRoZSBuc1xuIFx0Ly8gbW9kZSAmIDQ6IHJldHVybiB2YWx1ZSB3aGVuIGFscmVhZHkgbnMgb2JqZWN0XG4gXHQvLyBtb2RlICYgOHwxOiBiZWhhdmUgbGlrZSByZXF1aXJlXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLnQgPSBmdW5jdGlvbih2YWx1ZSwgbW9kZSkge1xuIFx0XHRpZihtb2RlICYgMSkgdmFsdWUgPSBfX3dlYnBhY2tfcmVxdWlyZV9fKHZhbHVlKTtcbiBcdFx0aWYobW9kZSAmIDgpIHJldHVybiB2YWx1ZTtcbiBcdFx0aWYoKG1vZGUgJiA0KSAmJiB0eXBlb2YgdmFsdWUgPT09ICdvYmplY3QnICYmIHZhbHVlICYmIHZhbHVlLl9fZXNNb2R1bGUpIHJldHVybiB2YWx1ZTtcbiBcdFx0dmFyIG5zID0gT2JqZWN0LmNyZWF0ZShudWxsKTtcbiBcdFx0X193ZWJwYWNrX3JlcXVpcmVfXy5yKG5zKTtcbiBcdFx0T2JqZWN0LmRlZmluZVByb3BlcnR5KG5zLCAnZGVmYXVsdCcsIHsgZW51bWVyYWJsZTogdHJ1ZSwgdmFsdWU6IHZhbHVlIH0pO1xuIFx0XHRpZihtb2RlICYgMiAmJiB0eXBlb2YgdmFsdWUgIT0gJ3N0cmluZycpIGZvcih2YXIga2V5IGluIHZhbHVlKSBfX3dlYnBhY2tfcmVxdWlyZV9fLmQobnMsIGtleSwgZnVuY3Rpb24oa2V5KSB7IHJldHVybiB2YWx1ZVtrZXldOyB9LmJpbmQobnVsbCwga2V5KSk7XG4gXHRcdHJldHVybiBucztcbiBcdH07XG5cbiBcdC8vIGdldERlZmF1bHRFeHBvcnQgZnVuY3Rpb24gZm9yIGNvbXBhdGliaWxpdHkgd2l0aCBub24taGFybW9ueSBtb2R1bGVzXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLm4gPSBmdW5jdGlvbihtb2R1bGUpIHtcbiBcdFx0dmFyIGdldHRlciA9IG1vZHVsZSAmJiBtb2R1bGUuX19lc01vZHVsZSA/XG4gXHRcdFx0ZnVuY3Rpb24gZ2V0RGVmYXVsdCgpIHsgcmV0dXJuIG1vZHVsZVsnZGVmYXVsdCddOyB9IDpcbiBcdFx0XHRmdW5jdGlvbiBnZXRNb2R1bGVFeHBvcnRzKCkgeyByZXR1cm4gbW9kdWxlOyB9O1xuIFx0XHRfX3dlYnBhY2tfcmVxdWlyZV9fLmQoZ2V0dGVyLCAnYScsIGdldHRlcik7XG4gXHRcdHJldHVybiBnZXR0ZXI7XG4gXHR9O1xuXG4gXHQvLyBPYmplY3QucHJvdG90eXBlLmhhc093blByb3BlcnR5LmNhbGxcbiBcdF9fd2VicGFja19yZXF1aXJlX18ubyA9IGZ1bmN0aW9uKG9iamVjdCwgcHJvcGVydHkpIHsgcmV0dXJuIE9iamVjdC5wcm90b3R5cGUuaGFzT3duUHJvcGVydHkuY2FsbChvYmplY3QsIHByb3BlcnR5KTsgfTtcblxuIFx0Ly8gX193ZWJwYWNrX3B1YmxpY19wYXRoX19cbiBcdF9fd2VicGFja19yZXF1aXJlX18ucCA9IFwiL3N0YXRpY1wiO1xuXG5cbiBcdC8vIExvYWQgZW50cnkgbW9kdWxlIGFuZCByZXR1cm4gZXhwb3J0c1xuIFx0cmV0dXJuIF9fd2VicGFja19yZXF1aXJlX18oX193ZWJwYWNrX3JlcXVpcmVfXy5zID0gXCIuL21haW4uanNcIik7XG4iLCIvKipcclxuICogQGRlc2Mg7KCE7JetIOuzgOyImFxyXG4gKi9cclxudmFyIGdsb2JhbFZhcmlhYmxlID0ge1xyXG5cdFx0Z2V0UHJvZHVjdENvdW50IDogMCxcdC8vIO2VtOuLuSDsubTthYzqs6DrpqzsnZgg7ZiE7J6sIOumrOyKpO2KuOuhnCDrs7Tsl6zsp4QgcHJvZHVjdOydmCDqsK/siJhcclxuXHRcdHByb21vdGlvbkluZGV4OiAwLFx0XHQvLyDtmITsnqwg7ZSE66Gc66qo7IWYIOychOy5mCBcclxuXHRcdHByb21vdGlvbkxlbmd0aCA6IC0xIFx0Ly8g7ZSE66Gc66qo7IWYIOyghOyytCDquLjsnbRcclxufTtcclxudmFyIHNlbmRBamF4ID0gcmVxdWlyZSgnLi9zZW5kQWpheCcpO1xyXG5cclxuLyoqXHJcbiAqIEBkZXNjIGRvbSDroZzrlKntm4Qg7Iuc7J6RXHJcbiAqL1xyXG5kb2N1bWVudC5hZGRFdmVudExpc3RlbmVyKCdET01Db250ZW50TG9hZGVkJywgZnVuY3Rpb24oKSB7XHJcblx0XHJcblx0aW5pdCgpO1xyXG5cdGNhdGVnb3J5Q2xpY2tFdmVudCgpO1xyXG5cdG1vcmVCdXR0b25DbGlja0V2ZW50KCk7XHJcblx0XHJcblx0c2V0SW50ZXJ2YWwoc2V0QW5pbWF0ZVByb21vdGlvbnMsMjAwMCk7XHJcbn0pO1xyXG5cclxuXHJcbi8qKlxyXG4gKiBAZGVzYyDsubTthYzqs6Drpqwg67aI65+s7Jik6riwIOuwjyDrpqzsiqTtirgg67aI65+s7Jik6riwXHJcbiAqL1xyXG5mdW5jdGlvbiBpbml0KCkge1xyXG5cdHNldENhdGVnb3JpZXMoKTtcclxuXHRzZXRQcm9tb3Rpb25zKCk7XHJcblx0c2V0UHJvZHVjdHMoe3N0YXJ0OiAwLCBjYXRlZ29yeUlkIDogMCwgaXNDYXRlZ29yeUNsaWNrZWQ6IGZhbHNlfSk7XHJcbn1cclxuXHJcbi8qKlxyXG4gKiBAZGVzYyBDYXRlZ29yaWVzIOyFi+2MhVxyXG4gKi9cclxuZnVuY3Rpb24gc2V0Q2F0ZWdvcmllcygpe1xyXG5cdFxyXG5cdHZhciBjYXRlZ29yeVNlbmRIZWFkZXIgPSB7XHJcblx0XHRtZXRob2QgOiAnR0VUJyxcclxuXHRcdHVyaSA6ICcvYXBpL2NhdGVnb3JpZXMnXHJcblx0fTtcclxuXHRcclxuXHQvLyBjYXRlZ29yeVJlc3BvbnNlID0+IGtleSA6IHsnaXRlbXMnLCB2YWx1ZSAtPiBjYXRlZ29yeSBsaXN0ICggaWQsbmFtZSkgfSwgeyB0b3RhbENvdW50ICwgdmFsdWUtPuy0nSDqsJzsiJggfVxyXG5cdHNlbmRBamF4KGNhdGVnb3J5U2VuZEhlYWRlciwgJycsIGZ1bmN0aW9uKGNhdGVnb3J5UmVzcG9uc2UpIHtcclxuXHRcdFxyXG5cdFx0dmFyIGNhdGVnb3J5VGVtcGxhdGUgPSBkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcjY2F0ZWdvcmllc190ZW1wbGF0ZScpLmNvbnRlbnQ7XHJcblx0XHR2YXIgaXRlbXMgPSBjYXRlZ29yeVJlc3BvbnNlLml0ZW1zO1xyXG5cdFxyXG5cdFx0aXRlbXMuZm9yRWFjaChmdW5jdGlvbihjYXRlZ29yeSkge1xyXG5cdFx0XHR2YXIgaXRlbVF1ZXJ5U2VsZWN0b3IgPSBjYXRlZ29yeVRlbXBsYXRlLnF1ZXJ5U2VsZWN0b3IoJy5pdGVtJyk7XHJcblx0XHRcdFxyXG5cdFx0XHRpdGVtUXVlcnlTZWxlY3Rvci5kYXRhc2V0LmNhdGVnb3J5ID0gY2F0ZWdvcnkuaWQ7XHJcblx0XHRcdGl0ZW1RdWVyeVNlbGVjdG9yLmRhdGFzZXQuY291bnQgPSBjYXRlZ29yeS5jb3VudDtcclxuXHRcdFx0Y2F0ZWdvcnlUZW1wbGF0ZS5xdWVyeVNlbGVjdG9yKCdzcGFuJykuaW5uZXJUZXh0ID0gY2F0ZWdvcnkubmFtZTtcclxuXHRcdFx0XHJcblx0XHRcdHZhciBjYXRlZ29yeUxpID0gZG9jdW1lbnQuaW1wb3J0Tm9kZShjYXRlZ29yeVRlbXBsYXRlLCB0cnVlKTtcclxuXHRcdFx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignI2NhdGVnb3JpZXNfdGFiJykuYXBwZW5kQ2hpbGQoY2F0ZWdvcnlMaSk7XHJcblx0XHR9KTtcclxuXHRcdFxyXG5cdH0pO1xyXG59XHJcblxyXG4vKipcclxuICogQGRlc2MgcHJvbW90aW9uIOyKrOudvOydtOuNlCDslaDri4jrqZTsnbTshZhcclxuICogQHJldHVybnNcclxuICovXHJcbmZ1bmN0aW9uIHNldEFuaW1hdGVQcm9tb3Rpb25zKCl7XHJcblx0XHJcblx0aWYoZ2xvYmFsVmFyaWFibGUucHJvbW90aW9uSW5kZXggPiBnbG9iYWxWYXJpYWJsZS5wcm9tb3Rpb25MZW5ndGgpIHtcclxuXHRcdGdsb2JhbFZhcmlhYmxlLnByb21vdGlvbkluZGV4ID0gMDtcclxuXHR9XHJcblx0XHJcblx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignI3Byb21vdGlvbnNfdWwnKS5zdHlsZS50cmFuc2Zvcm0gPSAndHJhbnNsYXRlWCgnKygtMTAwKiBnbG9iYWxWYXJpYWJsZS5wcm9tb3Rpb25JbmRleCkrJyUpJztcclxuXHRnbG9iYWxWYXJpYWJsZS5wcm9tb3Rpb25JbmRleCsrO1xyXG59XHJcblxyXG4vKipcclxuICogQGRlc2MgUHJvZHVjdHMg7IWL7YyFXHJcbiAqIEBwYXJhbXMgc2VuZFByb2R1Y3REYXRhIHsgc3RhcnQsIGNhdGVnb3J5SWQgLGlzQ2F0ZWdvcnlDbGlja2VkfSBcclxuICovXHJcbmZ1bmN0aW9uIHNldFByb2R1Y3RzKHNlbmRQcm9kdWN0RGF0YSkge1xyXG5cclxuXHR2YXIgcHJvZHVjdFNlbmRIZWFkZXIgPSB7XHJcblx0XHRtZXRob2QgOiAnR0VUJyxcclxuXHRcdHVyaSA6ICcvYXBpL3Byb2R1Y3RzP3N0YXJ0PScrc2VuZFByb2R1Y3REYXRhLnN0YXJ0KycmY2F0ZWdvcnlJZD0nK3NlbmRQcm9kdWN0RGF0YS5jYXRlZ29yeUlkXHJcblx0fTtcclxuXHRcclxuXHQvLyBwcm9kdWN0UmVzcG9uc2UgPT4geyBpdGVtcyA6IHByb2R1Y3RMaXN0ICwgdG90YWxDb3VudCA6IOy5tO2FjOqzoOumrOuzhCDstJ0g6rCv7IiYKVxyXG5cdHNlbmRBamF4KHByb2R1Y3RTZW5kSGVhZGVyLCAnJywgZnVuY3Rpb24ocHJvZHVjdFJlc3BvbnNlKSB7XHJcblx0XHRcclxuXHRcdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJyNwcm9kdWN0c19jb3VudCcpLmlubmVyVGV4dCA9IHByb2R1Y3RSZXNwb25zZS50b3RhbENvdW50O1xyXG5cdFx0XHJcblx0XHR2YXIgcHJvZHVjdFVsID0gZG9jdW1lbnQucXVlcnlTZWxlY3RvckFsbCgnLnByb2R1Y3RzX3VsJyk7XHJcblx0XHRcclxuXHRcdGlmKHNlbmRQcm9kdWN0RGF0YS5pc0NhdGVnb3J5Q2xpY2tlZCkge1xyXG5cdFx0XHRwcm9kdWN0VWwuZm9yRWFjaChmdW5jdGlvbihlbGUpe1xyXG5cdFx0XHRcdGVsZS5pbm5lckhUTUwgPSAnJztcclxuXHRcdFx0fSk7XHJcblx0XHR9XHJcblx0XHRcclxuXHRcdHZhciBwcm9kdWN0VGVtcGxhdGUgPSBkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcjcHJvZHVjdHNfdGVtcGxhdGUnKS5jb250ZW50O1xyXG5cdFx0dmFyIGl0ZW1zID0gcHJvZHVjdFJlc3BvbnNlLml0ZW1zO1xyXG5cdFx0XHJcblx0XHRpdGVtcy5mb3JFYWNoKGZ1bmN0aW9uKHByb2R1Y3QsIGluZGV4KSB7XHJcblx0XHRcdFxyXG5cdFx0XHRwcm9kdWN0VGVtcGxhdGUucXVlcnlTZWxlY3RvcignLml0ZW1fYm9vaycpLmhyZWYgPSAnLycgKyBwcm9kdWN0LmRpc3BsYXlJbmZvSWQ7XHJcblx0XHRcdFxyXG5cdFx0XHR2YXIgaW1nID0gcHJvZHVjdFRlbXBsYXRlLnF1ZXJ5U2VsZWN0b3IoJ2ltZycpO1xyXG5cdFx0XHRpbWcuc3JjID0gJy9zdGF0aWMvJyArIHByb2R1Y3QucHJvZHVjdEltYWdlVXJsO1xyXG5cdFx0XHRpbWcuYWx0ID0gcHJvZHVjdC5wcm9kdWN0RGVzY3JpcHRpb247XHJcblx0XHRcdFxyXG5cdFx0XHRwcm9kdWN0VGVtcGxhdGUucXVlcnlTZWxlY3RvcignLnByb2R1Y3RfZGVzY3JpcHRpb24nKS5pbm5lclRleHQgPSBwcm9kdWN0LnByb2R1Y3REZXNjcmlwdGlvbjtcclxuXHRcdFx0cHJvZHVjdFRlbXBsYXRlLnF1ZXJ5U2VsZWN0b3IoJy5wbGFjZV9uYW1lJykuaW5uZXJUZXh0ID0gcHJvZHVjdC5wbGFjZU5hbWU7XHJcblx0XHRcdHByb2R1Y3RUZW1wbGF0ZS5xdWVyeVNlbGVjdG9yKCcucHJvZHVjdF9jb250ZW50JykuaW5uZXJUZXh0ID0gcHJvZHVjdC5wcm9kdWN0Q29udGVudDtcclxuXHRcdFx0XHJcblx0XHRcdHZhciBwcm9kdWN0TGkgPSBkb2N1bWVudC5pbXBvcnROb2RlKHByb2R1Y3RUZW1wbGF0ZSx0cnVlKTtcclxuXHRcdFx0cHJvZHVjdFVsW2luZGV4JTJdLmFwcGVuZENoaWxkKHByb2R1Y3RMaSk7XHJcblx0XHRcdFxyXG5cdFx0XHRnbG9iYWxWYXJpYWJsZS5nZXRQcm9kdWN0Q291bnQrPTE7XHJcblx0XHR9KTtcclxuXHRcdFxyXG5cdFx0aWYocHJvZHVjdFJlc3BvbnNlLnRvdGFsQ291bnQgPD0gZ2xvYmFsVmFyaWFibGUuZ2V0UHJvZHVjdENvdW50KXtcclxuXHRcdFx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignI21vcmVCdXR0b24nKS5jbGFzc05hbWUgPSAnYnRuIGhpZGUnO1xyXG5cdFx0fVxyXG5cdH0pO1xyXG59XHJcblxyXG4vKipcclxuICogQGRlc2MgUHJvbW90aW9ucyDshYvtjIVcclxuICovXHJcbmZ1bmN0aW9uIHNldFByb21vdGlvbnMoKXtcclxuXHJcblx0dmFyIHByb21vdGlvblNlbmRIZWFkZXIgPSB7XHJcblx0XHRtZXRob2QgOiAnR0VUJyxcclxuXHRcdHVyaSA6ICcvYXBpL3Byb21vdGlvbnMnXHJcblx0fTtcclxuXHRcclxuXHQvLyBwcm9tb3Rpb25SZXNwb25zZSA9PiB7aXRlbXMgOiB7aWQsIHByb2R1Y3RJZCwgcHJvZHVjdEltYWdlVXJsfX1cclxuXHRzZW5kQWpheChwcm9tb3Rpb25TZW5kSGVhZGVyLCcnLGZ1bmN0aW9uKHByb21vdGlvblJlc3BvbnNlKXtcclxuXHRcdFxyXG5cdFx0dmFyIHByb21vdGlvblRlbXBsYXRlID0gZG9jdW1lbnQucXVlcnlTZWxlY3RvcignI3Byb21vdGlvbnNfdGVtcGxhdGUnKS5jb250ZW50O1xyXG5cdFx0dmFyIHByb21vdGlvblVsID0gZG9jdW1lbnQucXVlcnlTZWxlY3RvcignI3Byb21vdGlvbnNfdWwnKTtcclxuXHRcdFxyXG5cdFx0dmFyIGl0ZW1zID0gcHJvbW90aW9uUmVzcG9uc2UuaXRlbXM7XHJcblx0XHRcclxuXHRcdGl0ZW1zLmZvckVhY2goZnVuY3Rpb24ocHJvbW90aW9uKXtcclxuXHRcdFx0cHJvbW90aW9uVGVtcGxhdGUucXVlcnlTZWxlY3RvcignLml0ZW0nKS5zdHlsZS5iYWNrZ3JvdW5kSW1hZ2UgPSAndXJsKFwiL3N0YXRpYy8nK3Byb21vdGlvbi5wcm9kdWN0SW1hZ2VVcmwrJ1wiKSc7XHJcblx0XHRcdFxyXG5cdFx0XHR2YXIgcHJvbW90aW9uTGkgPSBkb2N1bWVudC5pbXBvcnROb2RlKHByb21vdGlvblRlbXBsYXRlLHRydWUpO1xyXG5cdFx0XHRwcm9tb3Rpb25VbC5hcHBlbmRDaGlsZChwcm9tb3Rpb25MaSk7XHJcblx0XHRcdGdsb2JhbFZhcmlhYmxlLnByb21vdGlvbkxlbmd0aCsrO1xyXG5cdFx0fSk7XHJcblx0fSk7XHJcblx0XHJcbn1cclxuXHJcbi8qKlxyXG4gKiBAZGVzYyBjYXRlZ29yeSBhbmNob3Ig7YOc6re47JeQIGFjdGl2ZSBjbGFzcyDsp4DsmrDquLBcclxuICogQHBhcmFtIGUgeyBldmVudCB9IFxyXG4gKiBAcmV0dXJuc1xyXG4gKi9cclxuZnVuY3Rpb24gcmVtb3ZlQW5jaG9yQWN0aXZlQ2xhc3MoZSl7XHJcblx0XHJcblx0dmFyIGNhdGVnb3J5QW5jaG9yVGFnID0gZS5jdXJyZW50VGFyZ2V0LnF1ZXJ5U2VsZWN0b3JBbGwoJy5hbmNob3InKTtcclxuXHRcclxuXHRjYXRlZ29yeUFuY2hvclRhZy5mb3JFYWNoKGZ1bmN0aW9uKGVsZSl7XHJcblx0XHRlbGUuY2xhc3NOYW1lID0gJ2FuY2hvcic7XHJcblx0fSk7XHJcbn1cclxuXHJcbi8qKlxyXG4gKiBAZGVzYyBjYXRlZ29yeSBsaSBjbGljayBldmVudCDqsbjslrTso7zquLBcclxuICovXHJcbmZ1bmN0aW9uIGNhdGVnb3J5Q2xpY2tFdmVudCgpe1xyXG5cdFxyXG5cdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJyNjYXRlZ29yaWVzX3RhYicpLmFkZEV2ZW50TGlzdGVuZXIoJ2NsaWNrJyxmdW5jdGlvbihlKXtcclxuXHRcdFxyXG5cdFx0dmFyIHRhcmdldCA9IGUudGFyZ2V0O1xyXG5cdFx0dmFyIHRhcmdldFRhZ05hbWUgPSB0YXJnZXQudGFnTmFtZS50b0xvd2VyQ2FzZSgpO1xyXG5cdFx0XHJcblx0XHRpZih0YXJnZXRUYWdOYW1lID09PSAndWwnKXtcclxuXHRcdFx0cmV0dXJuO1xyXG5cdFx0fSBcclxuXHRcdFxyXG5cdFx0Z2xvYmFsVmFyaWFibGUuZ2V0UHJvZHVjdENvdW50ID0gMDtcclxuXHRcdHJlbW92ZUFuY2hvckFjdGl2ZUNsYXNzKGUpO1xyXG5cdFx0XHJcblx0XHR2YXIgY2F0ZWdvcnlJZDtcclxuXHRcdFxyXG5cdFx0aWYgKHRhcmdldFRhZ05hbWUgPT09ICdsaScpIHtcclxuXHRcdFx0dGFyZ2V0LmZpcnN0Q2hpbGQuY2xhc3NOYW1lID0gJ2FuY2hvciBhY3RpdmUnO1xyXG5cdFx0XHRjYXRlZ29yeUlkID0gdGFyZ2V0LmRhdGFzZXQuY2F0ZWdvcnk7XHJcblx0XHR9IGVsc2UgaWYgKHRhcmdldFRhZ05hbWUgPT09ICdhJykge1xyXG5cdFx0XHR0YXJnZXQuY2xhc3NOYW1lID0gJ2FuY2hvciBhY3RpdmUnO1xyXG5cdFx0XHRjYXRlZ29yeUlkID0gdGFyZ2V0LnBhcmVudE5vZGUuZGF0YXNldC5jYXRlZ29yeTtcclxuXHRcdH0gZWxzZSBpZiAodGFyZ2V0VGFnTmFtZSA9PT0gJ3NwYW4nKSB7XHJcblx0XHRcdHRhcmdldC5wYXJlbnROb2RlLmNsYXNzTmFtZSA9ICdhbmNob3IgYWN0aXZlJztcclxuXHRcdFx0Y2F0ZWdvcnlJZCA9IHRhcmdldC5wYXJlbnROb2RlLnBhcmVudE5vZGUuZGF0YXNldC5jYXRlZ29yeTtcclxuXHRcdH0gXHJcblx0XHRcclxuXHRcdHNldFByb2R1Y3RzKHtzdGFydDogMCwgY2F0ZWdvcnlJZDogY2F0ZWdvcnlJZCwgaXNDYXRlZ29yeUNsaWNrZWQ6IHRydWV9KTtcclxuXHRcdFxyXG5cdFx0dmFyIG1vcmVCdXR0b24gPSBkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcjbW9yZUJ1dHRvbicpO1xyXG5cdFx0XHJcblx0XHRtb3JlQnV0dG9uLmNsYXNzTmFtZSA9ICdvcGVuIGJ0bic7XHJcblx0XHRtb3JlQnV0dG9uLmRhdGFzZXQuc3RhcnQgPSAwO1xyXG5cdFx0bW9yZUJ1dHRvbi5kYXRhc2V0LmNhdGVnb3J5ID0gY2F0ZWdvcnlJZDtcclxuXHRcdFxyXG5cdH0pO1xyXG59IFxyXG5cclxuLyoqXHJcbiAqIEBkZXNjIOuNlOuztOq4sCDrsoTtirwg7YG066atIOydtOuypO2KuFxyXG4gKiBAcmV0dXJuc1xyXG4gKi9cclxuZnVuY3Rpb24gbW9yZUJ1dHRvbkNsaWNrRXZlbnQoKXtcclxuXHRcclxuXHR2YXIgbW9yZUJ1dHRvbiA9IGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJyNtb3JlQnV0dG9uJyk7XHJcblx0XHJcblx0bW9yZUJ1dHRvbi5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsZnVuY3Rpb24oKXtcclxuXHRcdFxyXG5cdFx0bW9yZUJ1dHRvbi5kYXRhc2V0LnN0YXJ0ID0gcGFyc2VJbnQobW9yZUJ1dHRvbi5kYXRhc2V0LnN0YXJ0KSsxO1xyXG5cdFx0c2V0UHJvZHVjdHMoe3N0YXJ0OiBtb3JlQnV0dG9uLmRhdGFzZXQuc3RhcnQsIGNhdGVnb3J5SWQ6IG1vcmVCdXR0b24uZGF0YXNldC5jYXRlZ29yeSwgaXNDYXRlZ29yeUNsaWNrZWQ6IGZhbHNlfSk7XHJcblx0fSk7XHJcbn1cclxuIiwiLyoqXHJcbiAqIEBkZXNjIHNlbmRBamF4IOu5hOuPmeq4sCDsmpTssq0gKCDqs7XthrUg7ZWo7IiYIClcclxuICogQHBhcmFtIHNlbmRIZWFkZXIgKCBtZXRob2QsIHVyaSlcclxuICogQHBhcmFtIHNlbmREYXRhICgg67O064K8IOuNsOydtO2EsCApXHJcbiAqIEBwYXJhbSBjYWxsYmFjayAoIOuNsOydtO2EsOulvCDqsIDsp4Dqs6Ag7Jio65KkIOyImO2Wie2VoCBjYWxsYmFjayDtlajsiJgpXHJcbiAqIEByZXR1cm5zIHhoci5yZXNwb25zZSAoIEpTT04g7ZiV7YOc66GcIHBhcnNpbmcgKVxyXG4gKi9cclxuZnVuY3Rpb24gc2VuZEFqYXgoc2VuZEhlYWRlciwgc2VuZERhdGEsIGNhbGxiYWNrKSB7XHJcblx0dmFyIHhociA9IG5ldyBYTUxIdHRwUmVxdWVzdCgpO1xyXG5cclxuXHR4aHIub3BlbihzZW5kSGVhZGVyLm1ldGhvZCwgc2VuZEhlYWRlci51cmksIHRydWUpO1xyXG5cclxuXHR4aHIuc2V0UmVxdWVzdEhlYWRlcihcIkNvbnRlbnQtdHlwZVwiLCBcImFwcGxpY2F0aW9uL3gtd3d3LWZvcm0tdXJsZW5jb2RlZFwiKTtcclxuXHR4aHIuc2VuZChzZW5kRGF0YSk7XHJcblxyXG5cdHhoci5vbnJlYWR5c3RhdGVjaGFuZ2UgPSBmdW5jdGlvbigpIHtcclxuXHJcblx0XHRpZiAoeGhyLnJlYWR5U3RhdGUgPT09IHhoci5ET05FICYmIHhoci5zdGF0dXMgPT09IDIwMCkge1xyXG5cdFx0XHRjYWxsYmFjay5hcHBseSh0aGlzLCBbIEpTT04ucGFyc2UoeGhyLnJlc3BvbnNlKSBdKTtcclxuXHRcdH1cclxuXHR9XHJcbn1cclxuXHJcbm1vZHVsZS5leHBvcnRzID0gc2VuZEFqYXg7Il0sInNvdXJjZVJvb3QiOiIifQ==