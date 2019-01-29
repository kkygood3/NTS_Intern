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
};
var sendAjax = __webpack_require__(/*! ./sendAjax */ "./sendAjax.js");
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
});


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
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vd2VicGFjay9ib290c3RyYXAiLCJ3ZWJwYWNrOi8vLy4vbWFpbi5qcyIsIndlYnBhY2s6Ly8vLi9zZW5kQWpheC5qcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiO0FBQUE7QUFDQTs7QUFFQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7O0FBRUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7OztBQUdBO0FBQ0E7O0FBRUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQSxrREFBMEMsZ0NBQWdDO0FBQzFFO0FBQ0E7O0FBRUE7QUFDQTtBQUNBO0FBQ0EsZ0VBQXdELGtCQUFrQjtBQUMxRTtBQUNBLHlEQUFpRCxjQUFjO0FBQy9EOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQSxpREFBeUMsaUNBQWlDO0FBQzFFLHdIQUFnSCxtQkFBbUIsRUFBRTtBQUNySTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBLG1DQUEyQiwwQkFBMEIsRUFBRTtBQUN2RCx5Q0FBaUMsZUFBZTtBQUNoRDtBQUNBO0FBQ0E7O0FBRUE7QUFDQSw4REFBc0QsK0RBQStEOztBQUVySDtBQUNBOzs7QUFHQTtBQUNBOzs7Ozs7Ozs7Ozs7QUNsRkE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0EsZUFBZSxtQkFBTyxDQUFDLGlDQUFZO0FBQ25DO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBLGNBQWMsaURBQWlEO0FBQy9EOztBQUVBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBOztBQUVBLCtCQUErQiw0Q0FBNEMsR0FBRztBQUM5RTs7QUFFQTtBQUNBOztBQUVBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQSxHQUFHOztBQUVILEVBQUU7QUFDRjs7QUFFQTtBQUNBO0FBQ0EsNEJBQTRCLHNDO0FBQzVCO0FBQ0E7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7O0FBRUEsd0JBQXdCO0FBQ3hCOztBQUVBOztBQUVBOztBQUVBO0FBQ0E7QUFDQTtBQUNBLElBQUk7QUFDSjs7QUFFQTtBQUNBOztBQUVBOztBQUVBOztBQUVBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTs7QUFFQTtBQUNBLEdBQUc7O0FBRUg7QUFDQTtBQUNBO0FBQ0EsRUFBRTtBQUNGOztBQUVBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBOztBQUVBLDBCQUEwQixTQUFTO0FBQ25DOztBQUVBO0FBQ0E7O0FBRUE7O0FBRUE7QUFDQTs7QUFFQTtBQUNBOztBQUVBLEdBQUc7QUFDSCxFQUFFOztBQUVGOztBQUVBO0FBQ0E7QUFDQSxhQUFhLFE7QUFDYjtBQUNBO0FBQ0E7O0FBRUE7O0FBRUE7QUFDQTtBQUNBLEVBQUU7QUFDRjs7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBOztBQUVBO0FBQ0E7O0FBRUE7O0FBRUE7QUFDQTtBQUNBO0FBQ0EsR0FBRztBQUNIO0FBQ0E7QUFDQSxHQUFHO0FBQ0g7QUFDQTtBQUNBLEc7O0FBRUEsZUFBZSx3REFBd0Q7O0FBRXZFOztBQUVBO0FBQ0E7QUFDQTs7QUFFQSxFQUFFO0FBQ0Y7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTs7QUFFQTs7QUFFQTtBQUNBLGVBQWUsaUdBQWlHO0FBQ2hILEVBQUU7QUFDRjs7QUFFQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQSxDQUFDOzs7Ozs7Ozs7Ozs7QUN4TUQ7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOztBQUVBOztBQUVBO0FBQ0E7O0FBRUE7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQSwwQiIsImZpbGUiOiJob21lLmpzIiwic291cmNlc0NvbnRlbnQiOlsiIFx0Ly8gVGhlIG1vZHVsZSBjYWNoZVxuIFx0dmFyIGluc3RhbGxlZE1vZHVsZXMgPSB7fTtcblxuIFx0Ly8gVGhlIHJlcXVpcmUgZnVuY3Rpb25cbiBcdGZ1bmN0aW9uIF9fd2VicGFja19yZXF1aXJlX18obW9kdWxlSWQpIHtcblxuIFx0XHQvLyBDaGVjayBpZiBtb2R1bGUgaXMgaW4gY2FjaGVcbiBcdFx0aWYoaW5zdGFsbGVkTW9kdWxlc1ttb2R1bGVJZF0pIHtcbiBcdFx0XHRyZXR1cm4gaW5zdGFsbGVkTW9kdWxlc1ttb2R1bGVJZF0uZXhwb3J0cztcbiBcdFx0fVxuIFx0XHQvLyBDcmVhdGUgYSBuZXcgbW9kdWxlIChhbmQgcHV0IGl0IGludG8gdGhlIGNhY2hlKVxuIFx0XHR2YXIgbW9kdWxlID0gaW5zdGFsbGVkTW9kdWxlc1ttb2R1bGVJZF0gPSB7XG4gXHRcdFx0aTogbW9kdWxlSWQsXG4gXHRcdFx0bDogZmFsc2UsXG4gXHRcdFx0ZXhwb3J0czoge31cbiBcdFx0fTtcblxuIFx0XHQvLyBFeGVjdXRlIHRoZSBtb2R1bGUgZnVuY3Rpb25cbiBcdFx0bW9kdWxlc1ttb2R1bGVJZF0uY2FsbChtb2R1bGUuZXhwb3J0cywgbW9kdWxlLCBtb2R1bGUuZXhwb3J0cywgX193ZWJwYWNrX3JlcXVpcmVfXyk7XG5cbiBcdFx0Ly8gRmxhZyB0aGUgbW9kdWxlIGFzIGxvYWRlZFxuIFx0XHRtb2R1bGUubCA9IHRydWU7XG5cbiBcdFx0Ly8gUmV0dXJuIHRoZSBleHBvcnRzIG9mIHRoZSBtb2R1bGVcbiBcdFx0cmV0dXJuIG1vZHVsZS5leHBvcnRzO1xuIFx0fVxuXG5cbiBcdC8vIGV4cG9zZSB0aGUgbW9kdWxlcyBvYmplY3QgKF9fd2VicGFja19tb2R1bGVzX18pXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLm0gPSBtb2R1bGVzO1xuXG4gXHQvLyBleHBvc2UgdGhlIG1vZHVsZSBjYWNoZVxuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5jID0gaW5zdGFsbGVkTW9kdWxlcztcblxuIFx0Ly8gZGVmaW5lIGdldHRlciBmdW5jdGlvbiBmb3IgaGFybW9ueSBleHBvcnRzXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLmQgPSBmdW5jdGlvbihleHBvcnRzLCBuYW1lLCBnZXR0ZXIpIHtcbiBcdFx0aWYoIV9fd2VicGFja19yZXF1aXJlX18ubyhleHBvcnRzLCBuYW1lKSkge1xuIFx0XHRcdE9iamVjdC5kZWZpbmVQcm9wZXJ0eShleHBvcnRzLCBuYW1lLCB7IGVudW1lcmFibGU6IHRydWUsIGdldDogZ2V0dGVyIH0pO1xuIFx0XHR9XG4gXHR9O1xuXG4gXHQvLyBkZWZpbmUgX19lc01vZHVsZSBvbiBleHBvcnRzXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLnIgPSBmdW5jdGlvbihleHBvcnRzKSB7XG4gXHRcdGlmKHR5cGVvZiBTeW1ib2wgIT09ICd1bmRlZmluZWQnICYmIFN5bWJvbC50b1N0cmluZ1RhZykge1xuIFx0XHRcdE9iamVjdC5kZWZpbmVQcm9wZXJ0eShleHBvcnRzLCBTeW1ib2wudG9TdHJpbmdUYWcsIHsgdmFsdWU6ICdNb2R1bGUnIH0pO1xuIFx0XHR9XG4gXHRcdE9iamVjdC5kZWZpbmVQcm9wZXJ0eShleHBvcnRzLCAnX19lc01vZHVsZScsIHsgdmFsdWU6IHRydWUgfSk7XG4gXHR9O1xuXG4gXHQvLyBjcmVhdGUgYSBmYWtlIG5hbWVzcGFjZSBvYmplY3RcbiBcdC8vIG1vZGUgJiAxOiB2YWx1ZSBpcyBhIG1vZHVsZSBpZCwgcmVxdWlyZSBpdFxuIFx0Ly8gbW9kZSAmIDI6IG1lcmdlIGFsbCBwcm9wZXJ0aWVzIG9mIHZhbHVlIGludG8gdGhlIG5zXG4gXHQvLyBtb2RlICYgNDogcmV0dXJuIHZhbHVlIHdoZW4gYWxyZWFkeSBucyBvYmplY3RcbiBcdC8vIG1vZGUgJiA4fDE6IGJlaGF2ZSBsaWtlIHJlcXVpcmVcbiBcdF9fd2VicGFja19yZXF1aXJlX18udCA9IGZ1bmN0aW9uKHZhbHVlLCBtb2RlKSB7XG4gXHRcdGlmKG1vZGUgJiAxKSB2YWx1ZSA9IF9fd2VicGFja19yZXF1aXJlX18odmFsdWUpO1xuIFx0XHRpZihtb2RlICYgOCkgcmV0dXJuIHZhbHVlO1xuIFx0XHRpZigobW9kZSAmIDQpICYmIHR5cGVvZiB2YWx1ZSA9PT0gJ29iamVjdCcgJiYgdmFsdWUgJiYgdmFsdWUuX19lc01vZHVsZSkgcmV0dXJuIHZhbHVlO1xuIFx0XHR2YXIgbnMgPSBPYmplY3QuY3JlYXRlKG51bGwpO1xuIFx0XHRfX3dlYnBhY2tfcmVxdWlyZV9fLnIobnMpO1xuIFx0XHRPYmplY3QuZGVmaW5lUHJvcGVydHkobnMsICdkZWZhdWx0JywgeyBlbnVtZXJhYmxlOiB0cnVlLCB2YWx1ZTogdmFsdWUgfSk7XG4gXHRcdGlmKG1vZGUgJiAyICYmIHR5cGVvZiB2YWx1ZSAhPSAnc3RyaW5nJykgZm9yKHZhciBrZXkgaW4gdmFsdWUpIF9fd2VicGFja19yZXF1aXJlX18uZChucywga2V5LCBmdW5jdGlvbihrZXkpIHsgcmV0dXJuIHZhbHVlW2tleV07IH0uYmluZChudWxsLCBrZXkpKTtcbiBcdFx0cmV0dXJuIG5zO1xuIFx0fTtcblxuIFx0Ly8gZ2V0RGVmYXVsdEV4cG9ydCBmdW5jdGlvbiBmb3IgY29tcGF0aWJpbGl0eSB3aXRoIG5vbi1oYXJtb255IG1vZHVsZXNcbiBcdF9fd2VicGFja19yZXF1aXJlX18ubiA9IGZ1bmN0aW9uKG1vZHVsZSkge1xuIFx0XHR2YXIgZ2V0dGVyID0gbW9kdWxlICYmIG1vZHVsZS5fX2VzTW9kdWxlID9cbiBcdFx0XHRmdW5jdGlvbiBnZXREZWZhdWx0KCkgeyByZXR1cm4gbW9kdWxlWydkZWZhdWx0J107IH0gOlxuIFx0XHRcdGZ1bmN0aW9uIGdldE1vZHVsZUV4cG9ydHMoKSB7IHJldHVybiBtb2R1bGU7IH07XG4gXHRcdF9fd2VicGFja19yZXF1aXJlX18uZChnZXR0ZXIsICdhJywgZ2V0dGVyKTtcbiBcdFx0cmV0dXJuIGdldHRlcjtcbiBcdH07XG5cbiBcdC8vIE9iamVjdC5wcm90b3R5cGUuaGFzT3duUHJvcGVydHkuY2FsbFxuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5vID0gZnVuY3Rpb24ob2JqZWN0LCBwcm9wZXJ0eSkgeyByZXR1cm4gT2JqZWN0LnByb3RvdHlwZS5oYXNPd25Qcm9wZXJ0eS5jYWxsKG9iamVjdCwgcHJvcGVydHkpOyB9O1xuXG4gXHQvLyBfX3dlYnBhY2tfcHVibGljX3BhdGhfX1xuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5wID0gXCIvc3RhdGljXCI7XG5cblxuIFx0Ly8gTG9hZCBlbnRyeSBtb2R1bGUgYW5kIHJldHVybiBleHBvcnRzXG4gXHRyZXR1cm4gX193ZWJwYWNrX3JlcXVpcmVfXyhfX3dlYnBhY2tfcmVxdWlyZV9fLnMgPSBcIi4vbWFpbi5qc1wiKTtcbiIsIi8qKlxyXG4gKiBAZGVzYyDsoITsl60g67OA7IiYXHJcbiAqL1xyXG52YXIgZ2xvYmFsVmFyaWFibGUgPSB7XHJcblx0Z2V0UHJvZHVjdENvdW50IDogMCxcdC8vIO2VtOuLuSDsubTthYzqs6DrpqzsnZgg7ZiE7J6sIOumrOyKpO2KuOuhnCDrs7Tsl6zsp4QgcHJvZHVjdOydmCDqsK/siJhcclxufTtcclxudmFyIHNlbmRBamF4ID0gcmVxdWlyZSgnLi9zZW5kQWpheCcpO1xyXG4vKipcclxuICogQGRlc2Mg7Lm07YWM6rOg66asIOu2iOufrOyYpOq4sCDrsI8g66as7Iqk7Yq4IOu2iOufrOyYpOq4sFxyXG4gKi9cclxuZnVuY3Rpb24gaW5pdCgpIHtcclxuXHRzZXRDYXRlZ29yaWVzKCk7XHJcblx0c2V0UHJvbW90aW9ucygpO1xyXG5cdHNldFByb2R1Y3RzKHtzdGFydDogMCxjYXRlZ29yeUlkIDogMCxpc0NhdGVnb3J5Q2xpY2tlZDogZmFsc2V9KTtcclxufVxyXG5cclxuLyoqXHJcbiAqIEBkZXNjIENhdGVnb3JpZXMg7IWL7YyFXHJcbiAqL1xyXG5mdW5jdGlvbiBzZXRDYXRlZ29yaWVzKCl7XHJcblx0XHJcblx0dmFyIGNhdGVnb3J5U2VuZEhlYWRlciA9IHtcclxuXHRcdG1ldGhvZCA6ICdHRVQnLFxyXG5cdFx0dXJpIDogJy9hcGkvY2F0ZWdvcmllcydcclxuXHR9O1xyXG5cdFxyXG5cdC8vIGNhdGVnb3J5UmVzcG9uc2UgPT4ga2V5IDogeydpdGVtcycsIHZhbHVlIC0+IGNhdGVnb3J5IGxpc3QgKCBpZCxuYW1lKSB9LCB7IHRvdGFsQ291bnQgLCB2YWx1ZS0+7LSdIOqwnOyImCB9XHJcblx0c2VuZEFqYXgoY2F0ZWdvcnlTZW5kSGVhZGVyLCAnJywgZnVuY3Rpb24oY2F0ZWdvcnlSZXNwb25zZSkge1xyXG5cdFx0XHJcblx0XHR2YXIgY2F0ZWdvcnlUZW1wbGF0ZSA9IGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJyNjYXRlZ29yaWVzLXRlbXBsYXRlJykuY29udGVudDtcclxuXHRcdHZhciBpdGVtcyA9IGNhdGVnb3J5UmVzcG9uc2UuaXRlbXM7XHJcblx0XHRcclxuXHRcdGl0ZW1zLmZvckVhY2goZnVuY3Rpb24oY2F0ZWdvcnkpIHtcclxuXHRcdFx0dmFyIGl0ZW1RdWVyeVNlbGVjdG9yID0gY2F0ZWdvcnlUZW1wbGF0ZS5xdWVyeVNlbGVjdG9yKCcuaXRlbScpO1xyXG5cdFx0XHRcclxuXHRcdFx0aXRlbVF1ZXJ5U2VsZWN0b3IuZGF0YXNldC5jYXRlZ29yeSA9IGNhdGVnb3J5LmlkO1xyXG5cdFx0XHRpdGVtUXVlcnlTZWxlY3Rvci5kYXRhc2V0LmNvdW50ID0gY2F0ZWdvcnkuY291bnQ7XHJcblx0XHRcdGNhdGVnb3J5VGVtcGxhdGUucXVlcnlTZWxlY3Rvcignc3BhbicpLmlubmVyVGV4dCA9IGNhdGVnb3J5Lm5hbWU7XHJcblx0XHRcdFxyXG5cdFx0XHR2YXIgY2F0ZWdvcnlMaSA9IGRvY3VtZW50LmltcG9ydE5vZGUoY2F0ZWdvcnlUZW1wbGF0ZSwgdHJ1ZSk7XHJcblx0XHRcdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJy5ldmVudF90YWJfbHN0LnRhYl9sc3RfbWluJykuYXBwZW5kQ2hpbGQoY2F0ZWdvcnlMaSk7XHJcblx0XHR9KTtcclxuXHRcdFxyXG5cdH0pO1xyXG59XHJcblxyXG4vKipcclxuICogQGRlc2MgUHJvZHVjdHMg7IWL7YyFXHJcbiAqIEBwYXJhbXMgc2VuZFByb2R1Y3REYXRhIHsgc3RhcnQsIGNhdGVnb3J5SWQgLGlzQ2F0ZWdvcnlDbGlja2VkfSBcclxuICovXHJcbmZ1bmN0aW9uIHNldFByb2R1Y3RzKHNlbmRQcm9kdWN0RGF0YSkge1xyXG5cclxuXHR2YXIgcHJvZHVjdFNlbmRIZWFkZXIgPSB7XHJcblx0XHRtZXRob2QgOiAnR0VUJyxcclxuXHRcdHVyaSA6ICcvYXBpL3Byb2R1Y3RzP3N0YXJ0PScrc2VuZFByb2R1Y3REYXRhLnN0YXJ0KycmY2F0ZWdvcnlJZD0nK3NlbmRQcm9kdWN0RGF0YS5jYXRlZ29yeUlkXHJcblx0fTtcclxuXHRcclxuXHQvLyBwcm9kdWN0UmVzcG9uc2UgPT4geyBpdGVtcyA6IHByb2R1Y3RMaXN0ICwgdG90YWxDb3VudCA6IOy5tO2FjOqzoOumrOuzhCDstJ0g6rCv7IiYKVxyXG5cdHNlbmRBamF4KHByb2R1Y3RTZW5kSGVhZGVyLCAnJywgZnVuY3Rpb24ocHJvZHVjdFJlc3BvbnNlKSB7XHJcblx0XHRcclxuXHRcdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJy5waW5rJykuaW5uZXJUZXh0ID0gcHJvZHVjdFJlc3BvbnNlLnRvdGFsQ291bnQrJ+qwnCc7XHJcblx0XHRcclxuXHRcdHZhciBwcm9kdWN0VWwgPSBkb2N1bWVudC5xdWVyeVNlbGVjdG9yQWxsKCcubHN0X2V2ZW50X2JveCcpO1xyXG5cdFx0XHJcblx0XHRpZihzZW5kUHJvZHVjdERhdGEuaXNDYXRlZ29yeUNsaWNrZWQpIHtcclxuXHRcdFx0cHJvZHVjdFVsLmZvckVhY2goZnVuY3Rpb24oZWxlKXtcclxuXHRcdFx0XHRlbGUuaW5uZXJIVE1MID0gJyc7XHJcblx0XHRcdH0pO1xyXG5cdFx0fVxyXG5cdFx0XHJcblx0XHR2YXIgcHJvZHVjdFRlbXBsYXRlID0gZG9jdW1lbnQucXVlcnlTZWxlY3RvcignI3Byb2R1Y3RzLXRlbXBsYXRlJykuY29udGVudDtcclxuXHRcdHZhciBpdGVtcyA9IHByb2R1Y3RSZXNwb25zZS5pdGVtcztcclxuXHRcdFxyXG5cdFx0aXRlbXMuZm9yRWFjaChmdW5jdGlvbihwcm9kdWN0LCBpbmRleCkge1xyXG5cdFx0XHRcclxuXHRcdFx0cHJvZHVjdFRlbXBsYXRlLnF1ZXJ5U2VsZWN0b3IoJy5pdGVtX2Jvb2snKS5ocmVmID0gJy8nICsgcHJvZHVjdC5kaXNwbGF5SW5mb0lkO1xyXG5cdFx0XHRcclxuXHRcdFx0dmFyIGltZyA9IHByb2R1Y3RUZW1wbGF0ZS5xdWVyeVNlbGVjdG9yKCdpbWcnKTtcclxuXHRcdFx0aW1nLnNyYyA9ICcvc3RhdGljLycgKyBwcm9kdWN0LnByb2R1Y3RJbWFnZVVybDtcclxuXHRcdFx0aW1nLmFsdCA9IHByb2R1Y3QucHJvZHVjdERlc2NyaXB0aW9uO1xyXG5cdFx0XHRcclxuXHRcdFx0cHJvZHVjdFRlbXBsYXRlLnF1ZXJ5U2VsZWN0b3IoJy5ldmVudF90eHRfdGl0ID4gc3BhbicpLmlubmVyVGV4dCA9IHByb2R1Y3QucHJvZHVjdERlc2NyaXB0aW9uO1xyXG5cdFx0XHRwcm9kdWN0VGVtcGxhdGUucXVlcnlTZWxlY3RvcignLnNtJykuaW5uZXJUZXh0ID0gcHJvZHVjdC5wbGFjZU5hbWU7XHJcblx0XHRcdHByb2R1Y3RUZW1wbGF0ZS5xdWVyeVNlbGVjdG9yKCcuZXZlbnRfdHh0X2RzYycpLmlubmVyVGV4dCA9IHByb2R1Y3QucHJvZHVjdENvbnRlbnQ7XHJcblx0XHRcdFxyXG5cdFx0XHR2YXIgcHJvZHVjdExpID0gZG9jdW1lbnQuaW1wb3J0Tm9kZShwcm9kdWN0VGVtcGxhdGUsdHJ1ZSk7XHJcblx0XHRcdHByb2R1Y3RVbFtpbmRleCUyXS5hcHBlbmRDaGlsZChwcm9kdWN0TGkpO1xyXG5cdFx0XHRcclxuXHRcdFx0Z2xvYmFsVmFyaWFibGUuZ2V0UHJvZHVjdENvdW50Kz0xO1xyXG5cdFx0fSk7XHJcblx0XHRcclxuXHRcdGlmKHByb2R1Y3RSZXNwb25zZS50b3RhbENvdW50IDw9IGdsb2JhbFZhcmlhYmxlLmdldFByb2R1Y3RDb3VudCl7XHJcblx0XHRcdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJyNtb3JlQnV0dG9uJykuY2xhc3NOYW1lID0gJ2J0biBoaWRlJztcclxuXHRcdH1cclxuXHR9KTtcclxufVxyXG5cclxuLyoqXHJcbiAqIEBkZXNjIFByb21vdGlvbnMg7IWL7YyFXHJcbiAqL1xyXG5mdW5jdGlvbiBzZXRQcm9tb3Rpb25zKCl7XHJcblxyXG5cdHZhciBwcm9tb3Rpb25TZW5kSGVhZGVyID0ge1xyXG5cdFx0bWV0aG9kIDogJ0dFVCcsXHJcblx0XHR1cmkgOiAnL2FwaS9wcm9tb3Rpb25zJ1xyXG5cdH07XHJcblx0XHJcblx0Ly8gcHJvbW90aW9uUmVzcG9uc2UgPT4ge2l0ZW1zIDoge2lkLCBwcm9kdWN0SWQsIHByb2R1Y3RJbWFnZVVybH19XHJcblx0c2VuZEFqYXgocHJvbW90aW9uU2VuZEhlYWRlciwnJyxmdW5jdGlvbihwcm9tb3Rpb25SZXNwb25zZSl7XHJcblx0XHRcclxuXHRcdHZhciBwcm9tb3Rpb25UZW1wbGF0ZSA9IGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJyNwcm9tb3Rpb25zLXRlbXBsYXRlJykuY29udGVudDtcclxuXHRcdHZhciBwcm9tb3Rpb25VbCA9IGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJy52aXN1YWxfaW1nJyk7XHJcblx0XHRcclxuXHRcdHZhciBpdGVtcyA9IHByb21vdGlvblJlc3BvbnNlLml0ZW1zO1xyXG5cdFx0XHJcblx0XHRpdGVtcy5mb3JFYWNoKGZ1bmN0aW9uKHByb21vdGlvbil7XHJcblx0XHRcdHByb21vdGlvblRlbXBsYXRlLnF1ZXJ5U2VsZWN0b3IoJy5pdGVtJykuc3R5bGUuYmFja2dyb3VuZEltYWdlID0gJ3VybChcIi9zdGF0aWMvJytwcm9tb3Rpb24ucHJvZHVjdEltYWdlVXJsKydcIiknO1xyXG5cdFx0XHRcclxuXHRcdFx0dmFyIHByb21vdGlvbkxpID0gZG9jdW1lbnQuaW1wb3J0Tm9kZShwcm9tb3Rpb25UZW1wbGF0ZSx0cnVlKTtcclxuXHRcdFx0cHJvbW90aW9uVWwuYXBwZW5kQ2hpbGQocHJvbW90aW9uTGkpO1xyXG5cdFx0XHJcblx0XHR9KTtcclxuXHR9KTtcclxuXHRcclxufVxyXG5cclxuLyoqXHJcbiAqIEBkZXNjIGNhdGVnb3J5IGFuY2hvciDtg5zqt7jsl5AgYWN0aXZlIGNsYXNzIOyngOyasOq4sFxyXG4gKiBAcGFyYW0gZSB7IGV2ZW50IH0gXHJcbiAqIEByZXR1cm5zXHJcbiAqL1xyXG5mdW5jdGlvbiByZW1vdmVBbmNob3JBY3RpdmVDbGFzcyhlKXtcclxuXHRcclxuXHR2YXIgY2F0ZWdvcnlBbmNob3JUYWcgPSBlLmN1cnJlbnRUYXJnZXQucXVlcnlTZWxlY3RvckFsbCgnLmFuY2hvcicpO1xyXG5cdFxyXG5cdGNhdGVnb3J5QW5jaG9yVGFnLmZvckVhY2goZnVuY3Rpb24oZWxlKXtcclxuXHRcdGVsZS5jbGFzc05hbWUgPSAnYW5jaG9yJztcclxuXHR9KTtcclxufVxyXG5cclxuLyoqXHJcbiAqIEBkZXNjIGNhdGVnb3J5IGxpIGNsaWNrIGV2ZW50IOqxuOyWtOyjvOq4sFxyXG4gKi9cclxuZnVuY3Rpb24gY2F0ZWdvcnlDbGlja0V2ZW50KCl7XHJcblx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignLmV2ZW50X3RhYl9sc3QudGFiX2xzdF9taW4nKS5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsZnVuY3Rpb24oZSl7XHJcblx0XHRcclxuXHRcdHZhciB0YXJnZXQgPSBlLnRhcmdldDtcclxuXHRcdHZhciB0YXJnZXRUYWdOYW1lID0gdGFyZ2V0LnRhZ05hbWUudG9Mb3dlckNhc2UoKTtcclxuXHRcdFxyXG5cdFx0aWYodGFyZ2V0VGFnTmFtZSA9PT0gJ3VsJyl7XHJcblx0XHRcdHJldHVybjtcclxuXHRcdH1cclxuXHRcdFxyXG5cdFx0Z2xvYmFsVmFyaWFibGUuZ2V0UHJvZHVjdENvdW50ID0gMDtcclxuXHRcdHJlbW92ZUFuY2hvckFjdGl2ZUNsYXNzKGUpO1xyXG5cdFx0XHJcblx0XHR2YXIgY2F0ZWdvcnlJZDtcclxuXHRcdFxyXG5cdFx0aWYgKHRhcmdldFRhZ05hbWUgPT09ICdsaScpIHtcclxuXHRcdFx0dGFyZ2V0LmZpcnN0Q2hpbGQuY2xhc3NOYW1lID0gJ2FuY2hvciBhY3RpdmUnO1xyXG5cdFx0XHRjYXRlZ29yeUlkID0gdGFyZ2V0LmRhdGFzZXQuY2F0ZWdvcnk7XHJcblx0XHR9IGVsc2UgaWYgKHRhcmdldFRhZ05hbWUgPT09ICdhJykge1xyXG5cdFx0XHR0YXJnZXQuY2xhc3NOYW1lID0gJ2FuY2hvciBhY3RpdmUnO1xyXG5cdFx0XHRjYXRlZ29yeUlkID0gdGFyZ2V0LnBhcmVudE5vZGUuZGF0YXNldC5jYXRlZ29yeTtcclxuXHRcdH0gZWxzZSBpZiAodGFyZ2V0VGFnTmFtZSA9PT0gJ3NwYW4nKSB7XHJcblx0XHRcdHRhcmdldC5wYXJlbnROb2RlLmNsYXNzTmFtZSA9ICdhbmNob3IgYWN0aXZlJztcclxuXHRcdFx0Y2F0ZWdvcnlJZCA9IHRhcmdldC5wYXJlbnROb2RlLnBhcmVudE5vZGUuZGF0YXNldC5jYXRlZ29yeTtcclxuXHRcdH0gXHJcblx0XHRcclxuXHRcdHNldFByb2R1Y3RzKHtzdGFydDogMCxjYXRlZ29yeUlkOiBjYXRlZ29yeUlkLGlzQ2F0ZWdvcnlDbGlja2VkOiB0cnVlfSk7XHJcblx0XHRcclxuXHRcdHZhciBtb3JlQnV0dG9uID0gZG9jdW1lbnQucXVlcnlTZWxlY3RvcignI21vcmVCdXR0b24nKTtcclxuXHRcdFxyXG5cdFx0bW9yZUJ1dHRvbi5jbGFzc05hbWUgPSAnb3BlbiBidG4nO1xyXG5cdFx0bW9yZUJ1dHRvbi5kYXRhc2V0LnN0YXJ0ID0gMDtcclxuXHRcdG1vcmVCdXR0b24uZGF0YXNldC5jYXRlZ29yeSA9IGNhdGVnb3J5SWQ7XHJcblx0XHRcclxuXHR9KTtcclxufVxyXG5cclxuLyoqXHJcbiAqIEBkZXNjIOuNlOuztOq4sCDrsoTtirwg7YG066atIOydtOuypO2KuFxyXG4gKiBAcmV0dXJuc1xyXG4gKi9cclxuZnVuY3Rpb24gbW9yZUJ1dHRvbkNsaWNrRXZlbnQoKXtcclxuXHRcclxuXHR2YXIgbW9yZUJ1dHRvbiA9IGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJyNtb3JlQnV0dG9uJyk7XHJcblx0XHJcblx0bW9yZUJ1dHRvbi5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsZnVuY3Rpb24oKXtcclxuXHRcdFxyXG5cdFx0bW9yZUJ1dHRvbi5kYXRhc2V0LnN0YXJ0ID0gcGFyc2VJbnQobW9yZUJ1dHRvbi5kYXRhc2V0LnN0YXJ0KSsxO1xyXG5cdFx0c2V0UHJvZHVjdHMoe3N0YXJ0OiBtb3JlQnV0dG9uLmRhdGFzZXQuc3RhcnQsY2F0ZWdvcnlJZDogbW9yZUJ1dHRvbi5kYXRhc2V0LmNhdGVnb3J5LGlzQ2F0ZWdvcnlDbGlja2VkOiBmYWxzZX0pO1xyXG5cdH0pO1xyXG59XHJcblxyXG5kb2N1bWVudC5hZGRFdmVudExpc3RlbmVyKCdET01Db250ZW50TG9hZGVkJywgZnVuY3Rpb24oKSB7XHJcblx0XHJcblx0aW5pdCgpO1xyXG5cdGNhdGVnb3J5Q2xpY2tFdmVudCgpO1xyXG5cdG1vcmVCdXR0b25DbGlja0V2ZW50KCk7XHJcbn0pO1xyXG4iLCIvKipcclxuICogQGRlc2Mgc2VuZEFqYXgg67mE64+Z6riwIOyalOyyrSAoIOqzte2GtSDtlajsiJggKVxyXG4gKiBAcGFyYW0gc2VuZEhlYWRlciAoIG1ldGhvZCwgdXJpKVxyXG4gKiBAcGFyYW0gc2VuZERhdGEgKCDrs7Trgrwg642w7J207YSwIClcclxuICogQHBhcmFtIGNhbGxiYWNrICgg642w7J207YSw66W8IOqwgOyngOqzoCDsmKjrkqQg7IiY7ZaJ7ZWgIGNhbGxiYWNrIO2VqOyImClcclxuICogQHJldHVybnMgeGhyLnJlc3BvbnNlICggSlNPTiDtmJXtg5zroZwgcGFyc2luZyApXHJcbiAqL1xyXG5mdW5jdGlvbiBzZW5kQWpheChzZW5kSGVhZGVyLCBzZW5kRGF0YSwgY2FsbGJhY2spIHtcclxuXHR2YXIgeGhyID0gbmV3IFhNTEh0dHBSZXF1ZXN0KCk7XHJcblxyXG5cdHhoci5vcGVuKHNlbmRIZWFkZXIubWV0aG9kLCBzZW5kSGVhZGVyLnVyaSwgdHJ1ZSk7XHJcblxyXG5cdHhoci5zZXRSZXF1ZXN0SGVhZGVyKFwiQ29udGVudC10eXBlXCIsIFwiYXBwbGljYXRpb24veC13d3ctZm9ybS11cmxlbmNvZGVkXCIpO1xyXG5cdHhoci5zZW5kKHNlbmREYXRhKTtcclxuXHJcblx0eGhyLm9ucmVhZHlzdGF0ZWNoYW5nZSA9IGZ1bmN0aW9uKCkge1xyXG5cclxuXHRcdGlmICh4aHIucmVhZHlTdGF0ZSA9PT0geGhyLkRPTkUgJiYgeGhyLnN0YXR1cyA9PT0gMjAwKSB7XHJcblx0XHRcdGNhbGxiYWNrLmFwcGx5KHRoaXMsIFsgSlNPTi5wYXJzZSh4aHIucmVzcG9uc2UpIF0pO1xyXG5cdFx0fVxyXG5cdH1cclxufVxyXG5cclxubW9kdWxlLmV4cG9ydHMgPSBzZW5kQWpheDsiXSwic291cmNlUm9vdCI6IiJ9