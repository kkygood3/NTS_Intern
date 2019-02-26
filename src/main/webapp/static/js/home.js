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
/******/ 	__webpack_require__.p = "/static/js";
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
  getProductCount: 0,
  // 해당 카테고리의 현재 리스트로 보여진 product의 갯수
  promotionIndex: 0,
  // 현재 프로모션 위치 
  promotionLength: -1 // 프로모션 전체 길이

};

var sendAjax = __webpack_require__(/*! ./sendAjax */ "./sendAjax.js");
/**
 * @desc dom 로딩후 시작
 */


document.addEventListener('DOMContentLoaded', function () {
  init();
  categoryClickEvent();
  moreButtonClickEvent();
  setInterval(setAnimatePromotions, 2000);
});
/**
 * @desc 카테고리 불러오기 및 리스트 불러오기
 */

function init() {
  setCategories();
  setPromotions();
  setProducts({
    start: 0,
    categoryId: 0,
    isCategoryClicked: false
  });
}
/**
 * @desc Categories 셋팅
 */


function setCategories() {
  var categorySendHeader = {
    method: 'GET',
    uri: '/api/categories'
  }; // categoryResponse => key : {'items', value -> category list ( id,name) }, { totalCount , value->총 개수 }

  sendAjax(categorySendHeader, '', function (categoryResponse) {
    var categoryTemplate = document.querySelector('#categories_template').content;
    var items = categoryResponse.items;
    items.forEach(function (category) {
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


function setAnimatePromotions() {
  if (globalVariable.promotionIndex > globalVariable.promotionLength) {
    globalVariable.promotionIndex = 0;
  }

  document.querySelector('#promotions_ul').style.transform = 'translateX(' + -100 * globalVariable.promotionIndex + '%)';
  globalVariable.promotionIndex++;
}
/**
 * @desc Products 셋팅
 * @params sendProductData { start, categoryId ,isCategoryClicked} 
 */


function setProducts(sendProductData) {
  var productSendHeader = {
    method: 'GET',
    uri: '/api/products?start=' + sendProductData.start + '&categoryId=' + sendProductData.categoryId
  }; // productResponse => { items : productList , totalCount : 카테고리별 총 갯수)

  sendAjax(productSendHeader, '', function (productResponse) {
    document.querySelector('#products_count').innerText = productResponse.totalCount;
    var productUl = document.querySelectorAll('.products_ul');

    if (sendProductData.isCategoryClicked) {
      productUl.forEach(function (ele) {
        ele.innerHTML = '';
      });
    }

    var productTemplate = document.querySelector('#products_template').content;
    var items = productResponse.items;
    items.forEach(function (product, index) {
      productTemplate.querySelector('.item_book').href = '/detail/' + product.displayInfoId;
      var img = productTemplate.querySelector('img');
      img.src = '/static/' + product.productImageUrl;
      img.alt = product.productDescription;
      productTemplate.querySelector('.product_description').innerText = product.productDescription;
      productTemplate.querySelector('.place_name').innerText = product.placeName;
      productTemplate.querySelector('.product_content').innerText = product.productContent;
      var productLi = document.importNode(productTemplate, true);
      productUl[index % 2].appendChild(productLi);
      globalVariable.getProductCount += 1;
    });

    if (productResponse.totalCount <= globalVariable.getProductCount) {
      document.querySelector('#moreButton').className = 'btn hide';
    }
  });
}
/**
 * @desc Promotions 셋팅
 */


function setPromotions() {
  var promotionSendHeader = {
    method: 'GET',
    uri: '/api/promotions'
  }; // promotionResponse => {items : {id, productId, productImageUrl}}

  sendAjax(promotionSendHeader, '', function (promotionResponse) {
    var promotionTemplate = document.querySelector('#promotions_template').content;
    var promotionUl = document.querySelector('#promotions_ul');
    var items = promotionResponse.items;
    items.forEach(function (promotion) {
      promotionTemplate.querySelector('.item').style.backgroundImage = 'url("/static/' + promotion.productImageUrl + '")';
      var promotionLi = document.importNode(promotionTemplate, true);
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


function removeAnchorActiveClass(e) {
  var categoryAnchorTag = e.currentTarget.querySelectorAll('.anchor');
  categoryAnchorTag.forEach(function (ele) {
    ele.className = 'anchor';
  });
}
/**
 * @desc category li click event 걸어주기
 */


function categoryClickEvent() {
  document.querySelector('#categories_tab').addEventListener('click', function (e) {
    var target = e.target;
    var targetTagName = target.tagName.toLowerCase();

    if (targetTagName === 'ul') {
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

    setProducts({
      start: 0,
      categoryId: categoryId,
      isCategoryClicked: true
    });
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


function moreButtonClickEvent() {
  var moreButton = document.querySelector('#moreButton');
  moreButton.addEventListener('click', function () {
    moreButton.dataset.start = parseInt(moreButton.dataset.start) + 1;
    setProducts({
      start: moreButton.dataset.start,
      categoryId: moreButton.dataset.category,
      isCategoryClicked: false
    });
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
function sendAjax(sendHeader, sendData, callback, failCallback) {
  var xhr = new XMLHttpRequest();
  xhr.open(sendHeader.method, sendHeader.uri, false);

  if (sendHeader.method === 'GET') {
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  } else {
    xhr.setRequestHeader("Content-type", "application/json");
  }

  xhr.onreadystatechange = function () {
    if (xhr.readyState === xhr.DONE && xhr.status === 200) {
      callback.apply(this, [JSON.parse(xhr.response)]);
    } else if (xhr.status === 400) {
      failCallback();
    }
  };

  xhr.send(sendData);
}

module.exports = sendAjax;

/***/ })

/******/ });
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vd2VicGFjay9ib290c3RyYXAiLCJ3ZWJwYWNrOi8vLy4vbWFpbi5qcyIsIndlYnBhY2s6Ly8vLi9zZW5kQWpheC5qcyJdLCJuYW1lcyI6WyJnbG9iYWxWYXJpYWJsZSIsImdldFByb2R1Y3RDb3VudCIsInByb21vdGlvbkluZGV4IiwicHJvbW90aW9uTGVuZ3RoIiwic2VuZEFqYXgiLCJyZXF1aXJlIiwiZG9jdW1lbnQiLCJhZGRFdmVudExpc3RlbmVyIiwiaW5pdCIsImNhdGVnb3J5Q2xpY2tFdmVudCIsIm1vcmVCdXR0b25DbGlja0V2ZW50Iiwic2V0SW50ZXJ2YWwiLCJzZXRBbmltYXRlUHJvbW90aW9ucyIsInNldENhdGVnb3JpZXMiLCJzZXRQcm9tb3Rpb25zIiwic2V0UHJvZHVjdHMiLCJzdGFydCIsImNhdGVnb3J5SWQiLCJpc0NhdGVnb3J5Q2xpY2tlZCIsImNhdGVnb3J5U2VuZEhlYWRlciIsIm1ldGhvZCIsInVyaSIsImNhdGVnb3J5UmVzcG9uc2UiLCJjYXRlZ29yeVRlbXBsYXRlIiwicXVlcnlTZWxlY3RvciIsImNvbnRlbnQiLCJpdGVtcyIsImZvckVhY2giLCJjYXRlZ29yeSIsIml0ZW1RdWVyeVNlbGVjdG9yIiwiZGF0YXNldCIsImlkIiwiY291bnQiLCJpbm5lclRleHQiLCJuYW1lIiwiY2F0ZWdvcnlMaSIsImltcG9ydE5vZGUiLCJhcHBlbmRDaGlsZCIsInN0eWxlIiwidHJhbnNmb3JtIiwic2VuZFByb2R1Y3REYXRhIiwicHJvZHVjdFNlbmRIZWFkZXIiLCJwcm9kdWN0UmVzcG9uc2UiLCJ0b3RhbENvdW50IiwicHJvZHVjdFVsIiwicXVlcnlTZWxlY3RvckFsbCIsImVsZSIsImlubmVySFRNTCIsInByb2R1Y3RUZW1wbGF0ZSIsInByb2R1Y3QiLCJpbmRleCIsImhyZWYiLCJkaXNwbGF5SW5mb0lkIiwiaW1nIiwic3JjIiwicHJvZHVjdEltYWdlVXJsIiwiYWx0IiwicHJvZHVjdERlc2NyaXB0aW9uIiwicGxhY2VOYW1lIiwicHJvZHVjdENvbnRlbnQiLCJwcm9kdWN0TGkiLCJjbGFzc05hbWUiLCJwcm9tb3Rpb25TZW5kSGVhZGVyIiwicHJvbW90aW9uUmVzcG9uc2UiLCJwcm9tb3Rpb25UZW1wbGF0ZSIsInByb21vdGlvblVsIiwicHJvbW90aW9uIiwiYmFja2dyb3VuZEltYWdlIiwicHJvbW90aW9uTGkiLCJyZW1vdmVBbmNob3JBY3RpdmVDbGFzcyIsImUiLCJjYXRlZ29yeUFuY2hvclRhZyIsImN1cnJlbnRUYXJnZXQiLCJ0YXJnZXQiLCJ0YXJnZXRUYWdOYW1lIiwidGFnTmFtZSIsInRvTG93ZXJDYXNlIiwiZmlyc3RDaGlsZCIsInBhcmVudE5vZGUiLCJtb3JlQnV0dG9uIiwicGFyc2VJbnQiLCJzZW5kSGVhZGVyIiwic2VuZERhdGEiLCJjYWxsYmFjayIsImZhaWxDYWxsYmFjayIsInhociIsIlhNTEh0dHBSZXF1ZXN0Iiwib3BlbiIsInNldFJlcXVlc3RIZWFkZXIiLCJvbnJlYWR5c3RhdGVjaGFuZ2UiLCJyZWFkeVN0YXRlIiwiRE9ORSIsInN0YXR1cyIsImFwcGx5IiwiSlNPTiIsInBhcnNlIiwicmVzcG9uc2UiLCJzZW5kIiwibW9kdWxlIiwiZXhwb3J0cyJdLCJtYXBwaW5ncyI6IjtBQUFBO0FBQ0E7O0FBRUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBOztBQUVBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBOzs7QUFHQTtBQUNBOztBQUVBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBO0FBQ0Esa0RBQTBDLGdDQUFnQztBQUMxRTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBLGdFQUF3RCxrQkFBa0I7QUFDMUU7QUFDQSx5REFBaUQsY0FBYztBQUMvRDs7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0EsaURBQXlDLGlDQUFpQztBQUMxRSx3SEFBZ0gsbUJBQW1CLEVBQUU7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T0FBUCxHQUFpQi9GLFFBQWpCLEMiLCJmaWxlIjoiaG9tZS5qcyIsInNvdXJjZXNDb250ZW50IjpbIiBcdC8vIFRoZSBtb2R1bGUgY2FjaGVcbiBcdHZhciBpbnN0YWxsZWRNb2R1bGVzID0ge307XG5cbiBcdC8vIFRoZSByZXF1aXJlIGZ1bmN0aW9uXG4gXHRmdW5jdGlvbiBfX3dlYnBhY2tfcmVxdWlyZV9fKG1vZHVsZUlkKSB7XG5cbiBcdFx0Ly8gQ2hlY2sgaWYgbW9kdWxlIGlzIGluIGNhY2hlXG4gXHRcdGlmKGluc3RhbGxlZE1vZHVsZXNbbW9kdWxlSWRdKSB7XG4gXHRcdFx0cmV0dXJuIGluc3RhbGxlZE1vZHVsZXNbbW9kdWxlSWRdLmV4cG9ydHM7XG4gXHRcdH1cbiBcdFx0Ly8gQ3JlYXRlIGEgbmV3IG1vZHVsZSAoYW5kIHB1dCBpdCBpbnRvIHRoZSBjYWNoZSlcbiBcdFx0dmFyIG1vZHVsZSA9IGluc3RhbGxlZE1vZHVsZXNbbW9kdWxlSWRdID0ge1xuIFx0XHRcdGk6IG1vZHVsZUlkLFxuIFx0XHRcdGw6IGZhbHNlLFxuIFx0XHRcdGV4cG9ydHM6IHt9XG4gXHRcdH07XG5cbiBcdFx0Ly8gRXhlY3V0ZSB0aGUgbW9kdWxlIGZ1bmN0aW9uXG4gXHRcdG1vZHVsZXNbbW9kdWxlSWRdLmNhbGwobW9kdWxlLmV4cG9ydHMsIG1vZHVsZSwgbW9kdWxlLmV4cG9ydHMsIF9fd2VicGFja19yZXF1aXJlX18pO1xuXG4gXHRcdC8vIEZsYWcgdGhlIG1vZHVsZSBhcyBsb2FkZWRcbiBcdFx0bW9kdWxlLmwgPSB0cnVlO1xuXG4gXHRcdC8vIFJldHVybiB0aGUgZXhwb3J0cyBvZiB0aGUgbW9kdWxlXG4gXHRcdHJldHVybiBtb2R1bGUuZXhwb3J0cztcbiBcdH1cblxuXG4gXHQvLyBleHBvc2UgdGhlIG1vZHVsZXMgb2JqZWN0IChfX3dlYnBhY2tfbW9kdWxlc19fKVxuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5tID0gbW9kdWxlcztcblxuIFx0Ly8gZXhwb3NlIHRoZSBtb2R1bGUgY2FjaGVcbiBcdF9fd2VicGFja19yZXF1aXJlX18uYyA9IGluc3RhbGxlZE1vZHVsZXM7XG5cbiBcdC8vIGRlZmluZSBnZXR0ZXIgZnVuY3Rpb24gZm9yIGhhcm1vbnkgZXhwb3J0c1xuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5kID0gZnVuY3Rpb24oZXhwb3J0cywgbmFtZSwgZ2V0dGVyKSB7XG4gXHRcdGlmKCFfX3dlYnBhY2tfcmVxdWlyZV9fLm8oZXhwb3J0cywgbmFtZSkpIHtcbiBcdFx0XHRPYmplY3QuZGVmaW5lUHJvcGVydHkoZXhwb3J0cywgbmFtZSwgeyBlbnVtZXJhYmxlOiB0cnVlLCBnZXQ6IGdldHRlciB9KTtcbiBcdFx0fVxuIFx0fTtcblxuIFx0Ly8gZGVmaW5lIF9fZXNNb2R1bGUgb24gZXhwb3J0c1xuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5yID0gZnVuY3Rpb24oZXhwb3J0cykge1xuIFx0XHRpZih0eXBlb2YgU3ltYm9sICE9PSAndW5kZWZpbmVkJyAmJiBTeW1ib2wudG9TdHJpbmdUYWcpIHtcbiBcdFx0XHRPYmplY3QuZGVmaW5lUHJvcGVydHkoZXhwb3J0cywgU3ltYm9sLnRvU3RyaW5nVGFnLCB7IHZhbHVlOiAnTW9kdWxlJyB9KTtcbiBcdFx0fVxuIFx0XHRPYmplY3QuZGVmaW5lUHJvcGVydHkoZXhwb3J0cywgJ19fZXNNb2R1bGUnLCB7IHZhbHVlOiB0cnVlIH0pO1xuIFx0fTtcblxuIFx0Ly8gY3JlYXRlIGEgZmFrZSBuYW1lc3BhY2Ugb2JqZWN0XG4gXHQvLyBtb2RlICYgMTogdmFsdWUgaXMgYSBtb2R1bGUgaWQsIHJlcXVpcmUgaXRcbiBcdC8vIG1vZGUgJiAyOiBtZXJnZSBhbGwgcHJvcGVydGllcyBvZiB2YWx1ZSBpbnRvIHRoZSBuc1xuIFx0Ly8gbW9kZSAmIDQ6IHJldHVybiB2YWx1ZSB3aGVuIGFscmVhZHkgbnMgb2JqZWN0XG4gXHQvLyBtb2RlICYgOHwxOiBiZWhhdmUgbGlrZSByZXF1aXJlXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLnQgPSBmdW5jdGlvbih2YWx1ZSwgbW9kZSkge1xuIFx0XHRpZihtb2RlICYgMSkgdmFsdWUgPSBfX3dlYnBhY2tfcmVxdWlyZV9fKHZhbHVlKTtcbiBcdFx0aWYobW9kZSAmIDgpIHJldHVybiB2YWx1ZTtcbiBcdFx0aWYoKG1vZGUgJiA0KSAmJiB0eXBlb2YgdmFsdWUgPT09ICdvYmplY3QnICYmIHZhbHVlICYmIHZhbHVlLl9fZXNNb2R1bGUpIHJldHVybiB2YWx1ZTtcbiBcdFx0dmFyIG5zID0gT2JqZWN0LmNyZWF0ZShudWxsKTtcbiBcdFx0X193ZWJwYWNrX3JlcXVpcmVfXy5yKG5zKTtcbiBcdFx0T2JqZWN0LmRlZmluZVByb3BlcnR5KG5zLCAnZGVmYXVsdCcsIHsgZW51bWVyYWJsZTogdHJ1ZSwgdmFsdWU6IHZhbHVlIH0pO1xuIFx0XHRpZihtb2RlICYgMiAmJiB0eXBlb2YgdmFsdWUgIT0gJ3N0cmluZycpIGZvcih2YXIga2V5IGluIHZhbHVlKSBfX3dlYnBhY2tfcmVxdWlyZV9fLmQobnMsIGtleSwgZnVuY3Rpb24oa2V5KSB7IHJldHVybiB2YWx1ZVtrZXldOyB9LmJpbmQobnVsbCwga2V5KSk7XG4gXHRcdHJldHVybiBucztcbiBcdH07XG5cbiBcdC8vIGdldERlZmF1bHRFeHBvcnQgZnVuY3Rpb24gZm9yIGNvbXBhdGliaWxpdHkgd2l0aCBub24taGFybW9ueSBtb2R1bGVzXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLm4gPSBmdW5jdGlvbihtb2R1bGUpIHtcbiBcdFx0dmFyIGdldHRlciA9IG1vZHVsZSAmJiBtb2R1bGUuX19lc01vZHVsZSA/XG4gXHRcdFx0ZnVuY3Rpb24gZ2V0RGVmYXVsdCgpIHsgcmV0dXJuIG1vZHVsZVsnZGVmYXVsdCddOyB9IDpcbiBcdFx0XHRmdW5jdGlvbiBnZXRNb2R1bGVFeHBvcnRzKCkgeyByZXR1cm4gbW9kdWxlOyB9O1xuIFx0XHRfX3dlYnBhY2tfcmVxdWlyZV9fLmQoZ2V0dGVyLCAnYScsIGdldHRlcik7XG4gXHRcdHJldHVybiBnZXR0ZXI7XG4gXHR9O1xuXG4gXHQvLyBPYmplY3QucHJvdG90eXBlLmhhc093blByb3BlcnR5LmNhbGxcbiBcdF9fd2VicGFja19yZXF1aXJlX18ubyA9IGZ1bmN0aW9uKG9iamVjdCwgcHJvcGVydHkpIHsgcmV0dXJuIE9iamVjdC5wcm90b3R5cGUuaGFzT3duUHJvcGVydHkuY2FsbChvYmplY3QsIHByb3BlcnR5KTsgfTtcblxuIFx0Ly8gX193ZWJwYWNrX3B1YmxpY19wYXRoX19cbiBcdF9fd2VicGFja19yZXF1aXJlX18ucCA9IFwiL3N0YXRpYy9qc1wiO1xuXG5cbiBcdC8vIExvYWQgZW50cnkgbW9kdWxlIGFuZCByZXR1cm4gZXhwb3J0c1xuIFx0cmV0dXJuIF9fd2VicGFja19yZXF1aXJlX18oX193ZWJwYWNrX3JlcXVpcmVfXy5zID0gXCIuL21haW4uanNcIik7XG4iLCIvKipcclxuICogQGRlc2Mg7KCE7JetIOuzgOyImFxyXG4gKi9cclxudmFyIGdsb2JhbFZhcmlhYmxlID0ge1xyXG5cdFx0Z2V0UHJvZHVjdENvdW50IDogMCxcdC8vIO2VtOuLuSDsubTthYzqs6DrpqzsnZgg7ZiE7J6sIOumrOyKpO2KuOuhnCDrs7Tsl6zsp4QgcHJvZHVjdOydmCDqsK/siJhcclxuXHRcdHByb21vdGlvbkluZGV4OiAwLFx0XHQvLyDtmITsnqwg7ZSE66Gc66qo7IWYIOychOy5mCBcclxuXHRcdHByb21vdGlvbkxlbmd0aCA6IC0xIFx0Ly8g7ZSE66Gc66qo7IWYIOyghOyytCDquLjsnbRcclxufTtcclxudmFyIHNlbmRBamF4ID0gcmVxdWlyZSgnLi9zZW5kQWpheCcpO1xyXG5cclxuLyoqXHJcbiAqIEBkZXNjIGRvbSDroZzrlKntm4Qg7Iuc7J6RXHJcbiAqL1xyXG5kb2N1bWVudC5hZGRFdmVudExpc3RlbmVyKCdET01Db250ZW50TG9hZGVkJywgZnVuY3Rpb24oKSB7XHJcblx0XHJcblx0aW5pdCgpO1xyXG5cdGNhdGVnb3J5Q2xpY2tFdmVudCgpO1xyXG5cdG1vcmVCdXR0b25DbGlja0V2ZW50KCk7XHJcblx0XHJcblx0c2V0SW50ZXJ2YWwoc2V0QW5pbWF0ZVByb21vdGlvbnMsMjAwMCk7XHJcbn0pO1xyXG5cclxuXHJcbi8qKlxyXG4gKiBAZGVzYyDsubTthYzqs6Drpqwg67aI65+s7Jik6riwIOuwjyDrpqzsiqTtirgg67aI65+s7Jik6riwXHJcbiAqL1xyXG5mdW5jdGlvbiBpbml0KCkge1xyXG5cdHNldENhdGVnb3JpZXMoKTtcclxuXHRzZXRQcm9tb3Rpb25zKCk7XHJcblx0c2V0UHJvZHVjdHMoe3N0YXJ0OiAwLCBjYXRlZ29yeUlkIDogMCwgaXNDYXRlZ29yeUNsaWNrZWQ6IGZhbHNlfSk7XHJcbn1cclxuXHJcbi8qKlxyXG4gKiBAZGVzYyBDYXRlZ29yaWVzIOyFi+2MhVxyXG4gKi9cclxuZnVuY3Rpb24gc2V0Q2F0ZWdvcmllcygpe1xyXG5cdFxyXG5cdHZhciBjYXRlZ29yeVNlbmRIZWFkZXIgPSB7XHJcblx0XHRtZXRob2QgOiAnR0VUJyxcclxuXHRcdHVyaSA6ICcvYXBpL2NhdGVnb3JpZXMnXHJcblx0fTtcclxuXHRcclxuXHQvLyBjYXRlZ29yeVJlc3BvbnNlID0+IGtleSA6IHsnaXRlbXMnLCB2YWx1ZSAtPiBjYXRlZ29yeSBsaXN0ICggaWQsbmFtZSkgfSwgeyB0b3RhbENvdW50ICwgdmFsdWUtPuy0nSDqsJzsiJggfVxyXG5cdHNlbmRBamF4KGNhdGVnb3J5U2VuZEhlYWRlciwgJycsIGZ1bmN0aW9uKGNhdGVnb3J5UmVzcG9uc2UpIHtcclxuXHRcdFxyXG5cdFx0dmFyIGNhdGVnb3J5VGVtcGxhdGUgPSBkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcjY2F0ZWdvcmllc190ZW1wbGF0ZScpLmNvbnRlbnQ7XHJcblx0XHR2YXIgaXRlbXMgPSBjYXRlZ29yeVJlc3BvbnNlLml0ZW1zO1xyXG5cdFxyXG5cdFx0aXRlbXMuZm9yRWFjaChmdW5jdGlvbihjYXRlZ29yeSkge1xyXG5cdFx0XHR2YXIgaXRlbVF1ZXJ5U2VsZWN0b3IgPSBjYXRlZ29yeVRlbXBsYXRlLnF1ZXJ5U2VsZWN0b3IoJy5pdGVtJyk7XHJcblx0XHRcdFxyXG5cdFx0XHRpdGVtUXVlcnlTZWxlY3Rvci5kYXRhc2V0LmNhdGVnb3J5ID0gY2F0ZWdvcnkuaWQ7XHJcblx0XHRcdGl0ZW1RdWVyeVNlbGVjdG9yLmRhdGFzZXQuY291bnQgPSBjYXRlZ29yeS5jb3VudDtcclxuXHRcdFx0Y2F0ZWdvcnlUZW1wbGF0ZS5xdWVyeVNlbGVjdG9yKCdzcGFuJykuaW5uZXJUZXh0ID0gY2F0ZWdvcnkubmFtZTtcclxuXHRcdFx0XHJcblx0XHRcdHZhciBjYXRlZ29yeUxpID0gZG9jdW1lbnQuaW1wb3J0Tm9kZShjYXRlZ29yeVRlbXBsYXRlLCB0cnVlKTtcclxuXHRcdFx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignI2NhdGVnb3JpZXNfdGFiJykuYXBwZW5kQ2hpbGQoY2F0ZWdvcnlMaSk7XHJcblx0XHR9KTtcclxuXHRcdFxyXG5cdH0pO1xyXG59XHJcblxyXG4vKipcclxuICogQGRlc2MgcHJvbW90aW9uIOyKrOudvOydtOuNlCDslaDri4jrqZTsnbTshZhcclxuICogQHJldHVybnNcclxuICovXHJcbmZ1bmN0aW9uIHNldEFuaW1hdGVQcm9tb3Rpb25zKCl7XHJcblx0XHJcblx0aWYoZ2xvYmFsVmFyaWFibGUucHJvbW90aW9uSW5kZXggPiBnbG9iYWxWYXJpYWJsZS5wcm9tb3Rpb25MZW5ndGgpIHtcclxuXHRcdGdsb2JhbFZhcmlhYmxlLnByb21vdGlvbkluZGV4ID0gMDtcclxuXHR9XHJcblx0XHJcblx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignI3Byb21vdGlvbnNfdWwnKS5zdHlsZS50cmFuc2Zvcm0gPSAndHJhbnNsYXRlWCgnKygtMTAwKiBnbG9iYWxWYXJpYWJsZS5wcm9tb3Rpb25JbmRleCkrJyUpJztcclxuXHRnbG9iYWxWYXJpYWJsZS5wcm9tb3Rpb25JbmRleCsrO1xyXG59XHJcblxyXG4vKipcclxuICogQGRlc2MgUHJvZHVjdHMg7IWL7YyFXHJcbiAqIEBwYXJhbXMgc2VuZFByb2R1Y3REYXRhIHsgc3RhcnQsIGNhdGVnb3J5SWQgLGlzQ2F0ZWdvcnlDbGlja2VkfSBcclxuICovXHJcbmZ1bmN0aW9uIHNldFByb2R1Y3RzKHNlbmRQcm9kdWN0RGF0YSkge1xyXG5cclxuXHR2YXIgcHJvZHVjdFNlbmRIZWFkZXIgPSB7XHJcblx0XHRtZXRob2QgOiAnR0VUJyxcclxuXHRcdHVyaSA6ICcvYXBpL3Byb2R1Y3RzP3N0YXJ0PScrc2VuZFByb2R1Y3REYXRhLnN0YXJ0KycmY2F0ZWdvcnlJZD0nK3NlbmRQcm9kdWN0RGF0YS5jYXRlZ29yeUlkXHJcblx0fTtcclxuXHRcclxuXHQvLyBwcm9kdWN0UmVzcG9uc2UgPT4geyBpdGVtcyA6IHByb2R1Y3RMaXN0ICwgdG90YWxDb3VudCA6IOy5tO2FjOqzoOumrOuzhCDstJ0g6rCv7IiYKVxyXG5cdHNlbmRBamF4KHByb2R1Y3RTZW5kSGVhZGVyLCAnJywgZnVuY3Rpb24ocHJvZHVjdFJlc3BvbnNlKSB7XHJcblx0XHRcclxuXHRcdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJyNwcm9kdWN0c19jb3VudCcpLmlubmVyVGV4dCA9IHByb2R1Y3RSZXNwb25zZS50b3RhbENvdW50O1xyXG5cdFx0XHJcblx0XHR2YXIgcHJvZHVjdFVsID0gZG9jdW1lbnQucXVlcnlTZWxlY3RvckFsbCgnLnByb2R1Y3RzX3VsJyk7XHJcblx0XHRcclxuXHRcdGlmKHNlbmRQcm9kdWN0RGF0YS5pc0NhdGVnb3J5Q2xpY2tlZCkge1xyXG5cdFx0XHRwcm9kdWN0VWwuZm9yRWFjaChmdW5jdGlvbihlbGUpe1xyXG5cdFx0XHRcdGVsZS5pbm5lckhUTUwgPSAnJztcclxuXHRcdFx0fSk7XHJcblx0XHR9XHJcblx0XHRcclxuXHRcdHZhciBwcm9kdWN0VGVtcGxhdGUgPSBkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcjcHJvZHVjdHNfdGVtcGxhdGUnKS5jb250ZW50O1xyXG5cdFx0dmFyIGl0ZW1zID0gcHJvZHVjdFJlc3BvbnNlLml0ZW1zO1xyXG5cdFx0XHJcblx0XHRpdGVtcy5mb3JFYWNoKGZ1bmN0aW9uKHByb2R1Y3QsIGluZGV4KSB7XHJcblx0XHRcdFxyXG5cdFx0XHRwcm9kdWN0VGVtcGxhdGUucXVlcnlTZWxlY3RvcignLml0ZW1fYm9vaycpLmhyZWYgPSAnL2RldGFpbC8nICsgcHJvZHVjdC5kaXNwbGF5SW5mb0lkO1xyXG5cdFx0XHRcclxuXHRcdFx0dmFyIGltZyA9IHByb2R1Y3RUZW1wbGF0ZS5xdWVyeVNlbGVjdG9yKCdpbWcnKTtcclxuXHRcdFx0aW1nLnNyYyA9ICcvc3RhdGljLycgKyBwcm9kdWN0LnByb2R1Y3RJbWFnZVVybDtcclxuXHRcdFx0aW1nLmFsdCA9IHByb2R1Y3QucHJvZHVjdERlc2NyaXB0aW9uO1xyXG5cdFx0XHRcclxuXHRcdFx0cHJvZHVjdFRlbXBsYXRlLnF1ZXJ5U2VsZWN0b3IoJy5wcm9kdWN0X2Rlc2NyaXB0aW9uJykuaW5uZXJUZXh0ID0gcHJvZHVjdC5wcm9kdWN0RGVzY3JpcHRpb247XHJcblx0XHRcdHByb2R1Y3RUZW1wbGF0ZS5xdWVyeVNlbGVjdG9yKCcucGxhY2VfbmFtZScpLmlubmVyVGV4dCA9IHByb2R1Y3QucGxhY2VOYW1lO1xyXG5cdFx0XHRwcm9kdWN0VGVtcGxhdGUucXVlcnlTZWxlY3RvcignLnByb2R1Y3RfY29udGVudCcpLmlubmVyVGV4dCA9IHByb2R1Y3QucHJvZHVjdENvbnRlbnQ7XHJcblx0XHRcdFxyXG5cdFx0XHR2YXIgcHJvZHVjdExpID0gZG9jdW1lbnQuaW1wb3J0Tm9kZShwcm9kdWN0VGVtcGxhdGUsdHJ1ZSk7XHJcblx0XHRcdHByb2R1Y3RVbFtpbmRleCUyXS5hcHBlbmRDaGlsZChwcm9kdWN0TGkpO1xyXG5cdFx0XHRcclxuXHRcdFx0Z2xvYmFsVmFyaWFibGUuZ2V0UHJvZHVjdENvdW50Kz0xO1xyXG5cdFx0fSk7XHJcblx0XHRcclxuXHRcdGlmKHByb2R1Y3RSZXNwb25zZS50b3RhbENvdW50IDw9IGdsb2JhbFZhcmlhYmxlLmdldFByb2R1Y3RDb3VudCl7XHJcblx0XHRcdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJyNtb3JlQnV0dG9uJykuY2xhc3NOYW1lID0gJ2J0biBoaWRlJztcclxuXHRcdH1cclxuXHR9KTtcclxufVxyXG5cclxuLyoqXHJcbiAqIEBkZXNjIFByb21vdGlvbnMg7IWL7YyFXHJcbiAqL1xyXG5mdW5jdGlvbiBzZXRQcm9tb3Rpb25zKCl7XHJcblxyXG5cdHZhciBwcm9tb3Rpb25TZW5kSGVhZGVyID0ge1xyXG5cdFx0bWV0aG9kIDogJ0dFVCcsXHJcblx0XHR1cmkgOiAnL2FwaS9wcm9tb3Rpb25zJ1xyXG5cdH07XHJcblx0XHJcblx0Ly8gcHJvbW90aW9uUmVzcG9uc2UgPT4ge2l0ZW1zIDoge2lkLCBwcm9kdWN0SWQsIHByb2R1Y3RJbWFnZVVybH19XHJcblx0c2VuZEFqYXgocHJvbW90aW9uU2VuZEhlYWRlciwnJyxmdW5jdGlvbihwcm9tb3Rpb25SZXNwb25zZSl7XHJcblx0XHRcclxuXHRcdHZhciBwcm9tb3Rpb25UZW1wbGF0ZSA9IGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJyNwcm9tb3Rpb25zX3RlbXBsYXRlJykuY29udGVudDtcclxuXHRcdHZhciBwcm9tb3Rpb25VbCA9IGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJyNwcm9tb3Rpb25zX3VsJyk7XHJcblx0XHRcclxuXHRcdHZhciBpdGVtcyA9IHByb21vdGlvblJlc3BvbnNlLml0ZW1zO1xyXG5cdFx0XHJcblx0XHRpdGVtcy5mb3JFYWNoKGZ1bmN0aW9uKHByb21vdGlvbil7XHJcblx0XHRcdHByb21vdGlvblRlbXBsYXRlLnF1ZXJ5U2VsZWN0b3IoJy5pdGVtJykuc3R5bGUuYmFja2dyb3VuZEltYWdlID0gJ3VybChcIi9zdGF0aWMvJytwcm9tb3Rpb24ucHJvZHVjdEltYWdlVXJsKydcIiknO1xyXG5cdFx0XHRcclxuXHRcdFx0dmFyIHByb21vdGlvbkxpID0gZG9jdW1lbnQuaW1wb3J0Tm9kZShwcm9tb3Rpb25UZW1wbGF0ZSx0cnVlKTtcclxuXHRcdFx0cHJvbW90aW9uVWwuYXBwZW5kQ2hpbGQocHJvbW90aW9uTGkpO1xyXG5cdFx0XHRnbG9iYWxWYXJpYWJsZS5wcm9tb3Rpb25MZW5ndGgrKztcclxuXHRcdH0pO1xyXG5cdH0pO1xyXG5cdFxyXG59XHJcblxyXG4vKipcclxuICogQGRlc2MgY2F0ZWdvcnkgYW5jaG9yIO2DnOq3uOyXkCBhY3RpdmUgY2xhc3Mg7KeA7Jqw6riwXHJcbiAqIEBwYXJhbSBlIHsgZXZlbnQgfSBcclxuICogQHJldHVybnNcclxuICovXHJcbmZ1bmN0aW9uIHJlbW92ZUFuY2hvckFjdGl2ZUNsYXNzKGUpe1xyXG5cdFxyXG5cdHZhciBjYXRlZ29yeUFuY2hvclRhZyA9IGUuY3VycmVudFRhcmdldC5xdWVyeVNlbGVjdG9yQWxsKCcuYW5jaG9yJyk7XHJcblx0XHJcblx0Y2F0ZWdvcnlBbmNob3JUYWcuZm9yRWFjaChmdW5jdGlvbihlbGUpe1xyXG5cdFx0ZWxlLmNsYXNzTmFtZSA9ICdhbmNob3InO1xyXG5cdH0pO1xyXG59XHJcblxyXG4vKipcclxuICogQGRlc2MgY2F0ZWdvcnkgbGkgY2xpY2sgZXZlbnQg6rG47Ja07KO86riwXHJcbiAqL1xyXG5mdW5jdGlvbiBjYXRlZ29yeUNsaWNrRXZlbnQoKXtcclxuXHRcclxuXHRkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcjY2F0ZWdvcmllc190YWInKS5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsZnVuY3Rpb24oZSl7XHJcblx0XHRcclxuXHRcdHZhciB0YXJnZXQgPSBlLnRhcmdldDtcclxuXHRcdHZhciB0YXJnZXRUYWdOYW1lID0gdGFyZ2V0LnRhZ05hbWUudG9Mb3dlckNhc2UoKTtcclxuXHRcdFxyXG5cdFx0aWYodGFyZ2V0VGFnTmFtZSA9PT0gJ3VsJyl7XHJcblx0XHRcdHJldHVybjtcclxuXHRcdH0gXHJcblx0XHRcclxuXHRcdGdsb2JhbFZhcmlhYmxlLmdldFByb2R1Y3RDb3VudCA9IDA7XHJcblx0XHRyZW1vdmVBbmNob3JBY3RpdmVDbGFzcyhlKTtcclxuXHRcdFxyXG5cdFx0dmFyIGNhdGVnb3J5SWQ7XHJcblx0XHRcclxuXHRcdGlmICh0YXJnZXRUYWdOYW1lID09PSAnbGknKSB7XHJcblx0XHRcdHRhcmdldC5maXJzdENoaWxkLmNsYXNzTmFtZSA9ICdhbmNob3IgYWN0aXZlJztcclxuXHRcdFx0Y2F0ZWdvcnlJZCA9IHRhcmdldC5kYXRhc2V0LmNhdGVnb3J5O1xyXG5cdFx0fSBlbHNlIGlmICh0YXJnZXRUYWdOYW1lID09PSAnYScpIHtcclxuXHRcdFx0dGFyZ2V0LmNsYXNzTmFtZSA9ICdhbmNob3IgYWN0aXZlJztcclxuXHRcdFx0Y2F0ZWdvcnlJZCA9IHRhcmdldC5wYXJlbnROb2RlLmRhdGFzZXQuY2F0ZWdvcnk7XHJcblx0XHR9IGVsc2UgaWYgKHRhcmdldFRhZ05hbWUgPT09ICdzcGFuJykge1xyXG5cdFx0XHR0YXJnZXQucGFyZW50Tm9kZS5jbGFzc05hbWUgPSAnYW5jaG9yIGFjdGl2ZSc7XHJcblx0XHRcdGNhdGVnb3J5SWQgPSB0YXJnZXQucGFyZW50Tm9kZS5wYXJlbnROb2RlLmRhdGFzZXQuY2F0ZWdvcnk7XHJcblx0XHR9IFxyXG5cdFx0XHJcblx0XHRzZXRQcm9kdWN0cyh7c3RhcnQ6IDAsIGNhdGVnb3J5SWQ6IGNhdGVnb3J5SWQsIGlzQ2F0ZWdvcnlDbGlja2VkOiB0cnVlfSk7XHJcblx0XHRcclxuXHRcdHZhciBtb3JlQnV0dG9uID0gZG9jdW1lbnQucXVlcnlTZWxlY3RvcignI21vcmVCdXR0b24nKTtcclxuXHRcdFxyXG5cdFx0bW9yZUJ1dHRvbi5jbGFzc05hbWUgPSAnb3BlbiBidG4nO1xyXG5cdFx0bW9yZUJ1dHRvbi5kYXRhc2V0LnN0YXJ0ID0gMDtcclxuXHRcdG1vcmVCdXR0b24uZGF0YXNldC5jYXRlZ29yeSA9IGNhdGVnb3J5SWQ7XHJcblx0XHRcclxuXHR9KTtcclxufSBcclxuXHJcbi8qKlxyXG4gKiBAZGVzYyDrjZTrs7TquLAg67KE7Yq8IO2BtOumrSDsnbTrsqTtirhcclxuICogQHJldHVybnNcclxuICovXHJcbmZ1bmN0aW9uIG1vcmVCdXR0b25DbGlja0V2ZW50KCl7XHJcblx0XHJcblx0dmFyIG1vcmVCdXR0b24gPSBkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcjbW9yZUJ1dHRvbicpO1xyXG5cdFxyXG5cdG1vcmVCdXR0b24uYWRkRXZlbnRMaXN0ZW5lcignY2xpY2snLGZ1bmN0aW9uKCl7XHJcblx0XHRcclxuXHRcdG1vcmVCdXR0b24uZGF0YXNldC5zdGFydCA9IHBhcnNlSW50KG1vcmVCdXR0b24uZGF0YXNldC5zdGFydCkrMTtcclxuXHRcdHNldFByb2R1Y3RzKHtzdGFydDogbW9yZUJ1dHRvbi5kYXRhc2V0LnN0YXJ0LCBjYXRlZ29yeUlkOiBtb3JlQnV0dG9uLmRhdGFzZXQuY2F0ZWdvcnksIGlzQ2F0ZWdvcnlDbGlja2VkOiBmYWxzZX0pO1xyXG5cdH0pO1xyXG59XHJcbiIsIi8qKlxyXG4gKiBAZGVzYyBzZW5kQWpheCDruYTrj5nquLAg7JqU7LKtICgg6rO17Ya1IO2VqOyImCApXHJcbiAqIEBwYXJhbSBzZW5kSGVhZGVyICggbWV0aG9kLCB1cmkpXHJcbiAqIEBwYXJhbSBzZW5kRGF0YSAoIOuztOuCvCDrjbDsnbTthLAgKVxyXG4gKiBAcGFyYW0gY2FsbGJhY2sgKCDrjbDsnbTthLDrpbwg6rCA7KeA6rOgIOyYqOuSpCDsiJjtlontlaAgY2FsbGJhY2sg7ZWo7IiYKVxyXG4gKiBAcmV0dXJucyB4aHIucmVzcG9uc2UgKCBKU09OIO2Yle2DnOuhnCBwYXJzaW5nIClcclxuICovXHJcbmZ1bmN0aW9uIHNlbmRBamF4KHNlbmRIZWFkZXIsIHNlbmREYXRhLCBjYWxsYmFjaywgZmFpbENhbGxiYWNrKSB7XHJcblx0dmFyIHhociA9IG5ldyBYTUxIdHRwUmVxdWVzdCgpO1xyXG5cclxuXHR4aHIub3BlbihzZW5kSGVhZGVyLm1ldGhvZCwgc2VuZEhlYWRlci51cmksIGZhbHNlKTtcclxuXHJcblx0aWYoc2VuZEhlYWRlci5tZXRob2QgPT09ICdHRVQnKXtcclxuXHRcdHhoci5zZXRSZXF1ZXN0SGVhZGVyKFwiQ29udGVudC10eXBlXCIsIFwiYXBwbGljYXRpb24veC13d3ctZm9ybS11cmxlbmNvZGVkXCIpO1xyXG5cdH0gZWxzZSB7XHJcblx0XHR4aHIuc2V0UmVxdWVzdEhlYWRlcihcIkNvbnRlbnQtdHlwZVwiLFwiYXBwbGljYXRpb24vanNvblwiKTtcclxuXHR9XHJcblx0XHJcblxyXG5cdHhoci5vbnJlYWR5c3RhdGVjaGFuZ2UgPSBmdW5jdGlvbigpIHtcclxuXHRcdFxyXG5cdFx0aWYgKHhoci5yZWFkeVN0YXRlID09PSB4aHIuRE9ORSAmJiB4aHIuc3RhdHVzID09PSAyMDApIHtcclxuXHRcdFx0Y2FsbGJhY2suYXBwbHkodGhpcywgWyBKU09OLnBhcnNlKHhoci5yZXNwb25zZSkgXSk7XHJcblx0XHR9IGVsc2UgaWYgKHhoci5zdGF0dXMgPT09IDQwMCl7XHJcblx0XHRcdGZhaWxDYWxsYmFjaygpO1xyXG5cdFx0fVxyXG5cdH1cclxuXHR4aHIuc2VuZChzZW5kRGF0YSk7XHJcbn1cclxuXHJcbm1vZHVsZS5leHBvcnRzID0gc2VuZEFqYXg7Il0sInNvdXJjZVJvb3QiOiIifQ==