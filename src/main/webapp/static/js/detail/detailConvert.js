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
/******/ 	return __webpack_require__(__webpack_require__.s = "./detail.js");
/******/ })
/************************************************************************/
/******/ ({

/***/ "../handlebarRegistHelper.js":
/*!***********************************!*\
  !*** ../handlebarRegistHelper.js ***!
  \***********************************/
/*! no static exports found */
/***/ (function(module, exports) {

var handlebarRegistHelper = {
	
	/**
	 * @desc handlebar helper regist 작업 
	 * @returns
	 */	
	setHandlebarRegistHelper : function() {
		
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
	},
	
	/**
	 * @desc handlebar로 compile해서 html 얻어오기
	 * @param templateId
	 * @param content
	 * @returns html
	 */
	getHandlebarTemplateFromHtml: function(templateId,content){
		var template = document.querySelector(templateId).innerHTML;
		var handlebarTemplate = Handlebars.compile(template);
		
		return handlebarTemplate(content);
	}
}

module.exports = handlebarRegistHelper;

/***/ }),

/***/ "../sendAjax.js":
/*!**********************!*\
  !*** ../sendAjax.js ***!
  \**********************/
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

/***/ }),

/***/ "./detail.js":
/*!*******************!*\
  !*** ./detail.js ***!
  \*******************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

/**
 * @desc 전역변수 
 */
var sendAjax = __webpack_require__(/*! ../sendAjax */ "../sendAjax.js");

/**
 * @function setHandlebarRegistHelper()  : handlebar regist Helper 설정
 * @function getHandlebarTemplateFromHtml(templateId,content) : handlebar로 compile해서 html로 얻어오기
 */
var handlebarRegistHelper = __webpack_require__(/*! ../handlebarRegistHelper */ "../handlebarRegistHelper.js");

var globalVariables = {
	productTransform : 0,			// productImage 현재 Transform 거리
	showProductNumber : 0,			// 현재 보여지고 있는 이미지 number
};

document.addEventListener('DOMContentLoaded', function() {
	getDisplayInfos();
	addDetailButtonEvent();
	
	addFoldButtonEvent();
	addExpandButtonEvent();
});

/**
 * @desc displayInfo 정보 가져온뒤 셋팅
 * @returns
 */
function getDisplayInfos() {
	var displayInfoId = getDisplayInfoId();

	var displayInfoSendHeader = {
		method : 'GET',
		uri : '/api/products/'+displayInfoId
	};
	
	sendAjax(displayInfoSendHeader,'',function(displayInfoResponse){
		
		setProductImages(displayInfoResponse.productImages, displayInfoResponse.displayInfo.productDescription);
		setProductContent(displayInfoResponse.displayInfo.productContent);
		setProductEvent({productEvent : displayInfoResponse.displayInfo.productEvent});
		
		var start = 0;
		var end = 3;
		
		handlebarRegistHelper.setHandlebarRegistHelper();
		setComments({comments : displayInfoResponse.comments.slice(start, end)}, displayInfoResponse.comments.length, displayInfoResponse.averageScore, displayInfoResponse.displayInfo.productId);
		setProductDetail(displayInfoResponse.displayInfo, displayInfoResponse.displayInfoImage.saveFileName);
	});
}

/**
 * @desc displayInfoId 가져오기
 * @returns displayInfoId
 */
function getDisplayInfoId() {
	return document.querySelector('.display_info_detail').dataset.displayinfoid;
}

/**
 * @desc page 최상단 image 셋팅
 * @param productImages
 */
function setProductImages(productImages, productDescription){
	
	var productImageLength = productImages.length;
	document.querySelector('#product_image_max').innerHTML = productImageLength;
	document.querySelector('.product_images_ul').innerHTML = handlebarRegistHelper.getHandlebarTemplateFromHtml('#product_images_template', {productImages: productImages});
	
	document.querySelectorAll('.product_title').forEach(function(ele){
		ele.innerText = productDescription; 
	});
	
	if(productImageLength === 1){
		document.querySelector('.prev').remove();
		document.querySelector('.nxt').remove();
	} else {
		var productImagesUl = document.querySelector('.product_images_ul');
		
		var firstImageLi = productImagesUl.firstElementChild;
		var lastImageLi = productImagesUl.lastElementChild;
		
		addProductImageNextButtonEvent(productImagesUl,firstImageLi,lastImageLi);
		addProductImagePrevButtonEvent(productImagesUl,firstImageLi,lastImageLi);
	}
}

/**
 * @desc 다음 button 클릭 이벤트
 * @param productImagesUl
 * @param firstImageLi
 * @param lastImageLi
 */
function addProductImageNextButtonEvent(productImagesUl,firstImageLi,lastImageLi){
	
	var nowProductNumber = document.querySelector('.product_number');
	
	document.querySelector('.nxt').addEventListener('click',function(e){
		
		globalVariables.productTransform -= 100;
		productImagesUl.style.transform = 'translateX('+globalVariables.productTransform +'%)';
		
		if(globalVariables.showProductNumber === 0 ){
			productImagesUl.innerHTML += firstImageLi.outerHTML;
		
			globalVariables.showProductNumber = 1;
		} else {
			productImagesUl.innerHTML += lastImageLi.outerHTML;
			globalVariables.showProductNumber = 0;
		}
		nowProductNumber.innerText = globalVariables.showProductNumber+1;
	});
}

/**
 * @desc 이전 버튼 클릭 이벤트
 * @param productImagesUl
 * @param firstImageLi
 * @param lastImageLi
 */
function addProductImagePrevButtonEvent(productImagesUl,firstImageLi,lastImageLi){
	
	var nowProductNumber = document.querySelector('.product_number');
	document.querySelector('.prev').addEventListener('click',function(e){
		
		if(globalVariables.productTransform < 0 ){
			
			if(globalVariables.showProductNumber === 0 ){
				globalVariables.showProductNumber = 1;
			} else {
				globalVariables.showProductNumber = 0;
			}
			nowProductNumber.innerText = globalVariables.showProductNumber+1;
			
			globalVariables.productTransform += 100;
			productImagesUl.style.transform = 'translateX('+globalVariables.productTransform +'%)';
		}
	});
}

/**
 * @desc 접기 버튼 이벤트
 */
function addFoldButtonEvent(){
	
	document.querySelector('._close').addEventListener('click',function(){
		myButtonHideOtherButtonOpen(this,'._open');
		
		var details = document.querySelector('.store_details');
		details.classList.add('close3');
	});
}

/**
 * @desc 펼쳐보기 버튼 이벤트
 */
function addExpandButtonEvent(){
	
	document.querySelector('._open').addEventListener('click',function(){
		myButtonHideOtherButtonOpen(this,'._close');
		
		var details = document.querySelector('.store_details');
		details.classList.remove('close3');
	});
}

/**
 * @desc 자신의 버튼 닫고 다른버튼 활성화
 * @param otherId
 * @param _this
 */
function myButtonHideOtherButtonOpen(_this,otherQuery){
	
	_this.classList.remove('open');
	_this.classList.add('hide');
	
	var other = document.querySelector(otherQuery);
	
	other.classList.remove('hide');
	other.classList.add('open');
}

/**
 * @desc 코멘트 갯수, 코멘트 리스트, 코멘트 별점 셋팅
 * @param comments
 * @param commentsLength
 * @param commentAverageScore
 * @param productId
 */
function setComments(comments,commentsLength,commentAverageScore,productId){
	
	document.querySelector('.list_short_review').innerHTML = handlebarRegistHelper.getHandlebarTemplateFromHtml('#comment_template',comments);
	document.querySelector('#comment_count').innerHTML = commentsLength;
	
	var start = 0;
	var end = 3;
	
	document.querySelector('#comment_average').innerHTML = String(commentAverageScore).substr(start,end);
	document.querySelector('#comment_graph_star').style.width = commentAverageScore * 20 + '%';
	
	if(commentsLength === 0){
		document.querySelector('.btn_review_more').remove();
	} else {
		document.querySelector('.btn_review_more').href = '/review/'+productId;
	}
}



/**
 * @desc product event setting
 * @returns
 */
function setProductEvent(productEvent){
	document.querySelector('.event_info').innerHTML = handlebarRegistHelper.getHandlebarTemplateFromHtml('#product_event_template',productEvent);
}

/**
 * @desc product 내용 삽입
 * @param productContent
 * @returns
 */
function setProductContent(productContent){
	document.querySelectorAll('.product_content').forEach(function(element){
		element.innerHTML = productContent;
	});
}

/**
 * @desc product 상세 셋팅
 * @param displayInfo
 * @param saveFileName
 * @returns
 */
function setProductDetail(displayInfo, saveFileName){
	
	displayInfo.saveFileName = saveFileName;
	document.querySelector('.product_location').innerHTML = handlebarRegistHelper.getHandlebarTemplateFromHtml('#detail_location_template',displayInfo);
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
 * @param currentTarget
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

/***/ })

/******/ });
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vd2VicGFjay9ib290c3RyYXAiLCJ3ZWJwYWNrOi8vLy4uL2hhbmRsZWJhclJlZ2lzdEhlbHBlci5qcyIsIndlYnBhY2s6Ly8vLi4vc2VuZEFqYXguanMiLCJ3ZWJwYWNrOi8vLy4vZGV0YWlsLmpzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7QUFBQTtBQUNBOztBQUVBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTs7QUFFQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTs7O0FBR0E7QUFDQTs7QUFFQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBLGtEQUEwQyxnQ0FBZ0M7QUFDMUU7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQSxnRUFBd0Qsa0JBQWtCO0FBQzFFO0FBQ0EseURBQWlELGNBQWM7QUFDL0Q7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBLGlEQUF5QyxpQ0FBaUM7QUFDMUUsd0hBQWdILG1CQUFtQixFQUFFO0FBQ3JJO0FBQ0E7O0FBRUE7QUFDQTtBQUNBO0FBQ0EsbUNBQTJCLDBCQUEwQixFQUFFO0FBQ3ZELHlDQUFpQyxlQUFlO0FBQ2hEO0FBQ0E7QUFDQTs7QUFFQTtBQUNBLDhEQUFzRCwrREFBK0Q7O0FBRXJIO0FBQ0E7OztBQUdBO0FBQ0E7Ozs7Ozs7Ozs7OztBQ2xGQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOztBQUVBOztBQUVBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBLEdBQUc7O0FBRUg7O0FBRUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0EsR0FBRztBQUNILEVBQUU7O0FBRUY7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTs7QUFFQSx1Qzs7Ozs7Ozs7Ozs7QUN6Q0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOztBQUVBOztBQUVBO0FBQ0E7O0FBRUE7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQSwwQjs7Ozs7Ozs7Ozs7QUN2QkE7QUFDQTtBQUNBO0FBQ0EsZUFBZSxtQkFBTyxDQUFDLG1DQUFhOztBQUVwQztBQUNBO0FBQ0E7QUFDQTtBQUNBLDRCQUE0QixtQkFBTyxDQUFDLDZEQUEwQjs7QUFFOUQ7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQSxDQUFDOztBQUVEO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTs7QUFFQTtBQUNBO0FBQ0EsbUJBQW1CLDREQUE0RDs7QUFFL0U7QUFDQTs7QUFFQTtBQUNBLGVBQWUsMERBQTBEO0FBQ3pFO0FBQ0EsRUFBRTtBQUNGOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBLDBJQUEwSSw2QkFBNkI7O0FBRXZLO0FBQ0EscUM7QUFDQSxFQUFFOztBQUVGO0FBQ0E7QUFDQTtBQUNBLEVBQUU7QUFDRjs7QUFFQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOztBQUVBOztBQUVBOztBQUVBO0FBQ0E7O0FBRUE7QUFDQTs7QUFFQTtBQUNBLEdBQUc7QUFDSDtBQUNBO0FBQ0E7QUFDQTtBQUNBLEVBQUU7QUFDRjs7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBOztBQUVBOztBQUVBO0FBQ0E7QUFDQSxJQUFJO0FBQ0o7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBLEVBQUU7QUFDRjs7QUFFQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBOztBQUVBO0FBQ0E7QUFDQSxFQUFFO0FBQ0Y7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0EsRUFBRTtBQUNGOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBOztBQUVBOztBQUVBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7O0FBRUE7QUFDQTs7QUFFQTtBQUNBOztBQUVBO0FBQ0E7QUFDQSxFQUFFO0FBQ0Y7QUFDQTtBQUNBOzs7O0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBLEVBQUU7QUFDRjs7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7OztBQUdBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7O0FBRUE7QUFDQTs7QUFFQTs7QUFFQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQSxHQUFHO0FBQ0g7QUFDQTtBQUNBLEdBQUc7QUFDSDtBQUNBO0FBQ0E7O0FBRUE7QUFDQSxFQUFFO0FBQ0Y7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTs7QUFFQTtBQUNBO0FBQ0EsRUFBRTtBQUNGOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTs7QUFFQTtBQUNBO0FBQ0EsRUFBRTs7QUFFRjtBQUNBO0FBQ0E7QUFDQSxDIiwiZmlsZSI6ImRldGFpbENvbnZlcnQuanMiLCJzb3VyY2VzQ29udGVudCI6WyIgXHQvLyBUaGUgbW9kdWxlIGNhY2hlXG4gXHR2YXIgaW5zdGFsbGVkTW9kdWxlcyA9IHt9O1xuXG4gXHQvLyBUaGUgcmVxdWlyZSBmdW5jdGlvblxuIFx0ZnVuY3Rpb24gX193ZWJwYWNrX3JlcXVpcmVfXyhtb2R1bGVJZCkge1xuXG4gXHRcdC8vIENoZWNrIGlmIG1vZHVsZSBpcyBpbiBjYWNoZVxuIFx0XHRpZihpbnN0YWxsZWRNb2R1bGVzW21vZHVsZUlkXSkge1xuIFx0XHRcdHJldHVybiBpbnN0YWxsZWRNb2R1bGVzW21vZHVsZUlkXS5leHBvcnRzO1xuIFx0XHR9XG4gXHRcdC8vIENyZWF0ZSBhIG5ldyBtb2R1bGUgKGFuZCBwdXQgaXQgaW50byB0aGUgY2FjaGUpXG4gXHRcdHZhciBtb2R1bGUgPSBpbnN0YWxsZWRNb2R1bGVzW21vZHVsZUlkXSA9IHtcbiBcdFx0XHRpOiBtb2R1bGVJZCxcbiBcdFx0XHRsOiBmYWxzZSxcbiBcdFx0XHRleHBvcnRzOiB7fVxuIFx0XHR9O1xuXG4gXHRcdC8vIEV4ZWN1dGUgdGhlIG1vZHVsZSBmdW5jdGlvblxuIFx0XHRtb2R1bGVzW21vZHVsZUlkXS5jYWxsKG1vZHVsZS5leHBvcnRzLCBtb2R1bGUsIG1vZHVsZS5leHBvcnRzLCBfX3dlYnBhY2tfcmVxdWlyZV9fKTtcblxuIFx0XHQvLyBGbGFnIHRoZSBtb2R1bGUgYXMgbG9hZGVkXG4gXHRcdG1vZHVsZS5sID0gdHJ1ZTtcblxuIFx0XHQvLyBSZXR1cm4gdGhlIGV4cG9ydHMgb2YgdGhlIG1vZHVsZVxuIFx0XHRyZXR1cm4gbW9kdWxlLmV4cG9ydHM7XG4gXHR9XG5cblxuIFx0Ly8gZXhwb3NlIHRoZSBtb2R1bGVzIG9iamVjdCAoX193ZWJwYWNrX21vZHVsZXNfXylcbiBcdF9fd2VicGFja19yZXF1aXJlX18ubSA9IG1vZHVsZXM7XG5cbiBcdC8vIGV4cG9zZSB0aGUgbW9kdWxlIGNhY2hlXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLmMgPSBpbnN0YWxsZWRNb2R1bGVzO1xuXG4gXHQvLyBkZWZpbmUgZ2V0dGVyIGZ1bmN0aW9uIGZvciBoYXJtb255IGV4cG9ydHNcbiBcdF9fd2VicGFja19yZXF1aXJlX18uZCA9IGZ1bmN0aW9uKGV4cG9ydHMsIG5hbWUsIGdldHRlcikge1xuIFx0XHRpZighX193ZWJwYWNrX3JlcXVpcmVfXy5vKGV4cG9ydHMsIG5hbWUpKSB7XG4gXHRcdFx0T2JqZWN0LmRlZmluZVByb3BlcnR5KGV4cG9ydHMsIG5hbWUsIHsgZW51bWVyYWJsZTogdHJ1ZSwgZ2V0OiBnZXR0ZXIgfSk7XG4gXHRcdH1cbiBcdH07XG5cbiBcdC8vIGRlZmluZSBfX2VzTW9kdWxlIG9uIGV4cG9ydHNcbiBcdF9fd2VicGFja19yZXF1aXJlX18uciA9IGZ1bmN0aW9uKGV4cG9ydHMpIHtcbiBcdFx0aWYodHlwZW9mIFN5bWJvbCAhPT0gJ3VuZGVmaW5lZCcgJiYgU3ltYm9sLnRvU3RyaW5nVGFnKSB7XG4gXHRcdFx0T2JqZWN0LmRlZmluZVByb3BlcnR5KGV4cG9ydHMsIFN5bWJvbC50b1N0cmluZ1RhZywgeyB2YWx1ZTogJ01vZHVsZScgfSk7XG4gXHRcdH1cbiBcdFx0T2JqZWN0LmRlZmluZVByb3BlcnR5KGV4cG9ydHMsICdfX2VzTW9kdWxlJywgeyB2YWx1ZTogdHJ1ZSB9KTtcbiBcdH07XG5cbiBcdC8vIGNyZWF0ZSBhIGZha2UgbmFtZXNwYWNlIG9iamVjdFxuIFx0Ly8gbW9kZSAmIDE6IHZhbHVlIGlzIGEgbW9kdWxlIGlkLCByZXF1aXJlIGl0XG4gXHQvLyBtb2RlICYgMjogbWVyZ2UgYWxsIHByb3BlcnRpZXMgb2YgdmFsdWUgaW50byB0aGUgbnNcbiBcdC8vIG1vZGUgJiA0OiByZXR1cm4gdmFsdWUgd2hlbiBhbHJlYWR5IG5zIG9iamVjdFxuIFx0Ly8gbW9kZSAmIDh8MTogYmVoYXZlIGxpa2UgcmVxdWlyZVxuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy50ID0gZnVuY3Rpb24odmFsdWUsIG1vZGUpIHtcbiBcdFx0aWYobW9kZSAmIDEpIHZhbHVlID0gX193ZWJwYWNrX3JlcXVpcmVfXyh2YWx1ZSk7XG4gXHRcdGlmKG1vZGUgJiA4KSByZXR1cm4gdmFsdWU7XG4gXHRcdGlmKChtb2RlICYgNCkgJiYgdHlwZW9mIHZhbHVlID09PSAnb2JqZWN0JyAmJiB2YWx1ZSAmJiB2YWx1ZS5fX2VzTW9kdWxlKSByZXR1cm4gdmFsdWU7XG4gXHRcdHZhciBucyA9IE9iamVjdC5jcmVhdGUobnVsbCk7XG4gXHRcdF9fd2VicGFja19yZXF1aXJlX18ucihucyk7XG4gXHRcdE9iamVjdC5kZWZpbmVQcm9wZXJ0eShucywgJ2RlZmF1bHQnLCB7IGVudW1lcmFibGU6IHRydWUsIHZhbHVlOiB2YWx1ZSB9KTtcbiBcdFx0aWYobW9kZSAmIDIgJiYgdHlwZW9mIHZhbHVlICE9ICdzdHJpbmcnKSBmb3IodmFyIGtleSBpbiB2YWx1ZSkgX193ZWJwYWNrX3JlcXVpcmVfXy5kKG5zLCBrZXksIGZ1bmN0aW9uKGtleSkgeyByZXR1cm4gdmFsdWVba2V5XTsgfS5iaW5kKG51bGwsIGtleSkpO1xuIFx0XHRyZXR1cm4gbnM7XG4gXHR9O1xuXG4gXHQvLyBnZXREZWZhdWx0RXhwb3J0IGZ1bmN0aW9uIGZvciBjb21wYXRpYmlsaXR5IHdpdGggbm9uLWhhcm1vbnkgbW9kdWxlc1xuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5uID0gZnVuY3Rpb24obW9kdWxlKSB7XG4gXHRcdHZhciBnZXR0ZXIgPSBtb2R1bGUgJiYgbW9kdWxlLl9fZXNNb2R1bGUgP1xuIFx0XHRcdGZ1bmN0aW9uIGdldERlZmF1bHQoKSB7IHJldHVybiBtb2R1bGVbJ2RlZmF1bHQnXTsgfSA6XG4gXHRcdFx0ZnVuY3Rpb24gZ2V0TW9kdWxlRXhwb3J0cygpIHsgcmV0dXJuIG1vZHVsZTsgfTtcbiBcdFx0X193ZWJwYWNrX3JlcXVpcmVfXy5kKGdldHRlciwgJ2EnLCBnZXR0ZXIpO1xuIFx0XHRyZXR1cm4gZ2V0dGVyO1xuIFx0fTtcblxuIFx0Ly8gT2JqZWN0LnByb3RvdHlwZS5oYXNPd25Qcm9wZXJ0eS5jYWxsXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLm8gPSBmdW5jdGlvbihvYmplY3QsIHByb3BlcnR5KSB7IHJldHVybiBPYmplY3QucHJvdG90eXBlLmhhc093blByb3BlcnR5LmNhbGwob2JqZWN0LCBwcm9wZXJ0eSk7IH07XG5cbiBcdC8vIF9fd2VicGFja19wdWJsaWNfcGF0aF9fXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLnAgPSBcIi9zdGF0aWMvanMvXCI7XG5cblxuIFx0Ly8gTG9hZCBlbnRyeSBtb2R1bGUgYW5kIHJldHVybiBleHBvcnRzXG4gXHRyZXR1cm4gX193ZWJwYWNrX3JlcXVpcmVfXyhfX3dlYnBhY2tfcmVxdWlyZV9fLnMgPSBcIi4vZGV0YWlsLmpzXCIpO1xuIiwidmFyIGhhbmRsZWJhclJlZ2lzdEhlbHBlciA9IHtcclxuXHRcclxuXHQvKipcclxuXHQgKiBAZGVzYyBoYW5kbGViYXIgaGVscGVyIHJlZ2lzdCDsnpHsl4UgXHJcblx0ICogQHJldHVybnNcclxuXHQgKi9cdFxyXG5cdHNldEhhbmRsZWJhclJlZ2lzdEhlbHBlciA6IGZ1bmN0aW9uKCkge1xyXG5cdFx0XHJcblx0XHRIYW5kbGViYXJzLnJlZ2lzdGVySGVscGVyKCd1c2VyRW1haWxFbmNyeXB0JywgZnVuY3Rpb24ocmVzZXJ2YXRpb25FbWFpbCkge1xyXG5cdFx0XHQgICBcclxuXHRcdFx0dmFyIHN0YXJ0ID0gMDtcclxuXHRcdCAgICB2YXIgZW5kID0gNDtcclxuXHRcdCAgICBcclxuXHRcdFx0dmFyIGVuY3J5cHRlZFVzZXJFbWFpbCA9IHJlc2VydmF0aW9uRW1haWwuc3Vic3RyaW5nKHN0YXJ0LGVuZCkrJyoqKionO1xyXG5cdFx0ICAgIHJldHVybiBuZXcgSGFuZGxlYmFycy5TYWZlU3RyaW5nKGVuY3J5cHRlZFVzZXJFbWFpbCk7XHJcblx0XHR9KTtcclxuXHRcdFxyXG5cdFx0SGFuZGxlYmFycy5yZWdpc3RlckhlbHBlcigncmVzZXJ2YXRpb25EYXRlWVlZWU1NREQnLCBmdW5jdGlvbihyZXNlcnZhdGlvbkRhdGUpIHtcclxuXHRcdCAgICBcclxuXHRcdFx0dmFyIHN0YXJ0ID0gMDtcclxuXHRcdCAgICB2YXIgZW5kID0gMTA7XHJcblx0XHRcdFxyXG5cdFx0ICAgIHZhciByZXNlcnZhdGlvbkRhdGVZWVlZTU1ERCA9IHJlc2VydmF0aW9uRGF0ZS5zdWJzdHJpbmcoc3RhcnQsZW5kKS5yZXBsYWNlKC8tL2csJy4nKSsnLic7XHJcblx0XHQgICAgcmV0dXJuIG5ldyBIYW5kbGViYXJzLlNhZmVTdHJpbmcocmVzZXJ2YXRpb25EYXRlWVlZWU1NREQpO1xyXG5cdFx0fSk7XHJcblx0fSxcclxuXHRcclxuXHQvKipcclxuXHQgKiBAZGVzYyBoYW5kbGViYXLroZwgY29tcGlsZe2VtOyEnCBodG1sIOyWu+yWtOyYpOq4sFxyXG5cdCAqIEBwYXJhbSB0ZW1wbGF0ZUlkXHJcblx0ICogQHBhcmFtIGNvbnRlbnRcclxuXHQgKiBAcmV0dXJucyBodG1sXHJcblx0ICovXHJcblx0Z2V0SGFuZGxlYmFyVGVtcGxhdGVGcm9tSHRtbDogZnVuY3Rpb24odGVtcGxhdGVJZCxjb250ZW50KXtcclxuXHRcdHZhciB0ZW1wbGF0ZSA9IGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IodGVtcGxhdGVJZCkuaW5uZXJIVE1MO1xyXG5cdFx0dmFyIGhhbmRsZWJhclRlbXBsYXRlID0gSGFuZGxlYmFycy5jb21waWxlKHRlbXBsYXRlKTtcclxuXHRcdFxyXG5cdFx0cmV0dXJuIGhhbmRsZWJhclRlbXBsYXRlKGNvbnRlbnQpO1xyXG5cdH1cclxufVxyXG5cclxubW9kdWxlLmV4cG9ydHMgPSBoYW5kbGViYXJSZWdpc3RIZWxwZXI7IiwiLyoqXHJcbiAqIEBkZXNjIHNlbmRBamF4IOu5hOuPmeq4sCDsmpTssq0gKCDqs7XthrUg7ZWo7IiYIClcclxuICogQHBhcmFtIHNlbmRIZWFkZXIgKCBtZXRob2QsIHVyaSlcclxuICogQHBhcmFtIHNlbmREYXRhICgg67O064K8IOuNsOydtO2EsCApXHJcbiAqIEBwYXJhbSBjYWxsYmFjayAoIOuNsOydtO2EsOulvCDqsIDsp4Dqs6Ag7Jio65KkIOyImO2Wie2VoCBjYWxsYmFjayDtlajsiJgpXHJcbiAqIEByZXR1cm5zIHhoci5yZXNwb25zZSAoIEpTT04g7ZiV7YOc66GcIHBhcnNpbmcgKVxyXG4gKi9cclxuZnVuY3Rpb24gc2VuZEFqYXgoc2VuZEhlYWRlciwgc2VuZERhdGEsIGNhbGxiYWNrKSB7XHJcblx0dmFyIHhociA9IG5ldyBYTUxIdHRwUmVxdWVzdCgpO1xyXG5cclxuXHR4aHIub3BlbihzZW5kSGVhZGVyLm1ldGhvZCwgc2VuZEhlYWRlci51cmksIHRydWUpO1xyXG5cclxuXHR4aHIuc2V0UmVxdWVzdEhlYWRlcihcIkNvbnRlbnQtdHlwZVwiLCBcImFwcGxpY2F0aW9uL3gtd3d3LWZvcm0tdXJsZW5jb2RlZFwiKTtcclxuXHR4aHIuc2VuZChzZW5kRGF0YSk7XHJcblxyXG5cdHhoci5vbnJlYWR5c3RhdGVjaGFuZ2UgPSBmdW5jdGlvbigpIHtcclxuXHJcblx0XHRpZiAoeGhyLnJlYWR5U3RhdGUgPT09IHhoci5ET05FICYmIHhoci5zdGF0dXMgPT09IDIwMCkge1xyXG5cdFx0XHRjYWxsYmFjay5hcHBseSh0aGlzLCBbIEpTT04ucGFyc2UoeGhyLnJlc3BvbnNlKSBdKTtcclxuXHRcdH1cclxuXHR9XHJcbn1cclxuXHJcbm1vZHVsZS5leHBvcnRzID0gc2VuZEFqYXg7IiwiLyoqXHJcbiAqIEBkZXNjIOyghOyXreuzgOyImCBcclxuICovXHJcbnZhciBzZW5kQWpheCA9IHJlcXVpcmUoJy4uL3NlbmRBamF4Jyk7XHJcblxyXG4vKipcclxuICogQGZ1bmN0aW9uIHNldEhhbmRsZWJhclJlZ2lzdEhlbHBlcigpICA6IGhhbmRsZWJhciByZWdpc3QgSGVscGVyIOyEpOyglVxyXG4gKiBAZnVuY3Rpb24gZ2V0SGFuZGxlYmFyVGVtcGxhdGVGcm9tSHRtbCh0ZW1wbGF0ZUlkLGNvbnRlbnQpIDogaGFuZGxlYmFy66GcIGNvbXBpbGXtlbTshJwgaHRtbOuhnCDslrvslrTsmKTquLBcclxuICovXHJcbnZhciBoYW5kbGViYXJSZWdpc3RIZWxwZXIgPSByZXF1aXJlKCcuLi9oYW5kbGViYXJSZWdpc3RIZWxwZXInKTtcclxuXHJcbnZhciBnbG9iYWxWYXJpYWJsZXMgPSB7XHJcblx0cHJvZHVjdFRyYW5zZm9ybSA6IDAsXHRcdFx0Ly8gcHJvZHVjdEltYWdlIO2YhOyerCBUcmFuc2Zvcm0g6rGw66asXHJcblx0c2hvd1Byb2R1Y3ROdW1iZXIgOiAwLFx0XHRcdC8vIO2YhOyerCDrs7Tsl6zsp4Dqs6Ag7J6I64qUIOydtOuvuOyngCBudW1iZXJcclxufTtcclxuXHJcbmRvY3VtZW50LmFkZEV2ZW50TGlzdGVuZXIoJ0RPTUNvbnRlbnRMb2FkZWQnLCBmdW5jdGlvbigpIHtcclxuXHRnZXREaXNwbGF5SW5mb3MoKTtcclxuXHRhZGREZXRhaWxCdXR0b25FdmVudCgpO1xyXG5cdFxyXG5cdGFkZEZvbGRCdXR0b25FdmVudCgpO1xyXG5cdGFkZEV4cGFuZEJ1dHRvbkV2ZW50KCk7XHJcbn0pO1xyXG5cclxuLyoqXHJcbiAqIEBkZXNjIGRpc3BsYXlJbmZvIOygleuztCDqsIDsoLjsmKjrkqQg7IWL7YyFXHJcbiAqIEByZXR1cm5zXHJcbiAqL1xyXG5mdW5jdGlvbiBnZXREaXNwbGF5SW5mb3MoKSB7XHJcblx0dmFyIGRpc3BsYXlJbmZvSWQgPSBnZXREaXNwbGF5SW5mb0lkKCk7XHJcblxyXG5cdHZhciBkaXNwbGF5SW5mb1NlbmRIZWFkZXIgPSB7XHJcblx0XHRtZXRob2QgOiAnR0VUJyxcclxuXHRcdHVyaSA6ICcvYXBpL3Byb2R1Y3RzLycrZGlzcGxheUluZm9JZFxyXG5cdH07XHJcblx0XHJcblx0c2VuZEFqYXgoZGlzcGxheUluZm9TZW5kSGVhZGVyLCcnLGZ1bmN0aW9uKGRpc3BsYXlJbmZvUmVzcG9uc2Upe1xyXG5cdFx0XHJcblx0XHRzZXRQcm9kdWN0SW1hZ2VzKGRpc3BsYXlJbmZvUmVzcG9uc2UucHJvZHVjdEltYWdlcywgZGlzcGxheUluZm9SZXNwb25zZS5kaXNwbGF5SW5mby5wcm9kdWN0RGVzY3JpcHRpb24pO1xyXG5cdFx0c2V0UHJvZHVjdENvbnRlbnQoZGlzcGxheUluZm9SZXNwb25zZS5kaXNwbGF5SW5mby5wcm9kdWN0Q29udGVudCk7XHJcblx0XHRzZXRQcm9kdWN0RXZlbnQoe3Byb2R1Y3RFdmVudCA6IGRpc3BsYXlJbmZvUmVzcG9uc2UuZGlzcGxheUluZm8ucHJvZHVjdEV2ZW50fSk7XHJcblx0XHRcclxuXHRcdHZhciBzdGFydCA9IDA7XHJcblx0XHR2YXIgZW5kID0gMztcclxuXHRcdFxyXG5cdFx0aGFuZGxlYmFyUmVnaXN0SGVscGVyLnNldEhhbmRsZWJhclJlZ2lzdEhlbHBlcigpO1xyXG5cdFx0c2V0Q29tbWVudHMoe2NvbW1lbnRzIDogZGlzcGxheUluZm9SZXNwb25zZS5jb21tZW50cy5zbGljZShzdGFydCwgZW5kKX0sIGRpc3BsYXlJbmZvUmVzcG9uc2UuY29tbWVudHMubGVuZ3RoLCBkaXNwbGF5SW5mb1Jlc3BvbnNlLmF2ZXJhZ2VTY29yZSwgZGlzcGxheUluZm9SZXNwb25zZS5kaXNwbGF5SW5mby5wcm9kdWN0SWQpO1xyXG5cdFx0c2V0UHJvZHVjdERldGFpbChkaXNwbGF5SW5mb1Jlc3BvbnNlLmRpc3BsYXlJbmZvLCBkaXNwbGF5SW5mb1Jlc3BvbnNlLmRpc3BsYXlJbmZvSW1hZ2Uuc2F2ZUZpbGVOYW1lKTtcclxuXHR9KTtcclxufVxyXG5cclxuLyoqXHJcbiAqIEBkZXNjIGRpc3BsYXlJbmZvSWQg6rCA7KC47Jik6riwXHJcbiAqIEByZXR1cm5zIGRpc3BsYXlJbmZvSWRcclxuICovXHJcbmZ1bmN0aW9uIGdldERpc3BsYXlJbmZvSWQoKSB7XHJcblx0cmV0dXJuIGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJy5kaXNwbGF5X2luZm9fZGV0YWlsJykuZGF0YXNldC5kaXNwbGF5aW5mb2lkO1xyXG59XHJcblxyXG4vKipcclxuICogQGRlc2MgcGFnZSDstZzsg4Hri6ggaW1hZ2Ug7IWL7YyFXHJcbiAqIEBwYXJhbSBwcm9kdWN0SW1hZ2VzXHJcbiAqL1xyXG5mdW5jdGlvbiBzZXRQcm9kdWN0SW1hZ2VzKHByb2R1Y3RJbWFnZXMsIHByb2R1Y3REZXNjcmlwdGlvbil7XHJcblx0XHJcblx0dmFyIHByb2R1Y3RJbWFnZUxlbmd0aCA9IHByb2R1Y3RJbWFnZXMubGVuZ3RoO1xyXG5cdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJyNwcm9kdWN0X2ltYWdlX21heCcpLmlubmVySFRNTCA9IHByb2R1Y3RJbWFnZUxlbmd0aDtcclxuXHRkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcucHJvZHVjdF9pbWFnZXNfdWwnKS5pbm5lckhUTUwgPSBoYW5kbGViYXJSZWdpc3RIZWxwZXIuZ2V0SGFuZGxlYmFyVGVtcGxhdGVGcm9tSHRtbCgnI3Byb2R1Y3RfaW1hZ2VzX3RlbXBsYXRlJywge3Byb2R1Y3RJbWFnZXM6IHByb2R1Y3RJbWFnZXN9KTtcclxuXHRcclxuXHRkb2N1bWVudC5xdWVyeVNlbGVjdG9yQWxsKCcucHJvZHVjdF90aXRsZScpLmZvckVhY2goZnVuY3Rpb24oZWxlKXtcclxuXHRcdGVsZS5pbm5lclRleHQgPSBwcm9kdWN0RGVzY3JpcHRpb247IFxyXG5cdH0pO1xyXG5cdFxyXG5cdGlmKHByb2R1Y3RJbWFnZUxlbmd0aCA9PT0gMSl7XHJcblx0XHRkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcucHJldicpLnJlbW92ZSgpO1xyXG5cdFx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignLm54dCcpLnJlbW92ZSgpO1xyXG5cdH0gZWxzZSB7XHJcblx0XHR2YXIgcHJvZHVjdEltYWdlc1VsID0gZG9jdW1lbnQucXVlcnlTZWxlY3RvcignLnByb2R1Y3RfaW1hZ2VzX3VsJyk7XHJcblx0XHRcclxuXHRcdHZhciBmaXJzdEltYWdlTGkgPSBwcm9kdWN0SW1hZ2VzVWwuZmlyc3RFbGVtZW50Q2hpbGQ7XHJcblx0XHR2YXIgbGFzdEltYWdlTGkgPSBwcm9kdWN0SW1hZ2VzVWwubGFzdEVsZW1lbnRDaGlsZDtcclxuXHRcdFxyXG5cdFx0YWRkUHJvZHVjdEltYWdlTmV4dEJ1dHRvbkV2ZW50KHByb2R1Y3RJbWFnZXNVbCxmaXJzdEltYWdlTGksbGFzdEltYWdlTGkpO1xyXG5cdFx0YWRkUHJvZHVjdEltYWdlUHJldkJ1dHRvbkV2ZW50KHByb2R1Y3RJbWFnZXNVbCxmaXJzdEltYWdlTGksbGFzdEltYWdlTGkpO1xyXG5cdH1cclxufVxyXG5cclxuLyoqXHJcbiAqIEBkZXNjIOuLpOydjCBidXR0b24g7YG066atIOydtOuypO2KuFxyXG4gKiBAcGFyYW0gcHJvZHVjdEltYWdlc1VsXHJcbiAqIEBwYXJhbSBmaXJzdEltYWdlTGlcclxuICogQHBhcmFtIGxhc3RJbWFnZUxpXHJcbiAqL1xyXG5mdW5jdGlvbiBhZGRQcm9kdWN0SW1hZ2VOZXh0QnV0dG9uRXZlbnQocHJvZHVjdEltYWdlc1VsLGZpcnN0SW1hZ2VMaSxsYXN0SW1hZ2VMaSl7XHJcblx0XHJcblx0dmFyIG5vd1Byb2R1Y3ROdW1iZXIgPSBkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcucHJvZHVjdF9udW1iZXInKTtcclxuXHRcclxuXHRkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcubnh0JykuYWRkRXZlbnRMaXN0ZW5lcignY2xpY2snLGZ1bmN0aW9uKGUpe1xyXG5cdFx0XHJcblx0XHRnbG9iYWxWYXJpYWJsZXMucHJvZHVjdFRyYW5zZm9ybSAtPSAxMDA7XHJcblx0XHRwcm9kdWN0SW1hZ2VzVWwuc3R5bGUudHJhbnNmb3JtID0gJ3RyYW5zbGF0ZVgoJytnbG9iYWxWYXJpYWJsZXMucHJvZHVjdFRyYW5zZm9ybSArJyUpJztcclxuXHRcdFxyXG5cdFx0aWYoZ2xvYmFsVmFyaWFibGVzLnNob3dQcm9kdWN0TnVtYmVyID09PSAwICl7XHJcblx0XHRcdHByb2R1Y3RJbWFnZXNVbC5pbm5lckhUTUwgKz0gZmlyc3RJbWFnZUxpLm91dGVySFRNTDtcclxuXHRcdFxyXG5cdFx0XHRnbG9iYWxWYXJpYWJsZXMuc2hvd1Byb2R1Y3ROdW1iZXIgPSAxO1xyXG5cdFx0fSBlbHNlIHtcclxuXHRcdFx0cHJvZHVjdEltYWdlc1VsLmlubmVySFRNTCArPSBsYXN0SW1hZ2VMaS5vdXRlckhUTUw7XHJcblx0XHRcdGdsb2JhbFZhcmlhYmxlcy5zaG93UHJvZHVjdE51bWJlciA9IDA7XHJcblx0XHR9XHJcblx0XHRub3dQcm9kdWN0TnVtYmVyLmlubmVyVGV4dCA9IGdsb2JhbFZhcmlhYmxlcy5zaG93UHJvZHVjdE51bWJlcisxO1xyXG5cdH0pO1xyXG59XHJcblxyXG4vKipcclxuICogQGRlc2Mg7J207KCEIOuyhO2KvCDtgbTrpq0g7J2067Kk7Yq4XHJcbiAqIEBwYXJhbSBwcm9kdWN0SW1hZ2VzVWxcclxuICogQHBhcmFtIGZpcnN0SW1hZ2VMaVxyXG4gKiBAcGFyYW0gbGFzdEltYWdlTGlcclxuICovXHJcbmZ1bmN0aW9uIGFkZFByb2R1Y3RJbWFnZVByZXZCdXR0b25FdmVudChwcm9kdWN0SW1hZ2VzVWwsZmlyc3RJbWFnZUxpLGxhc3RJbWFnZUxpKXtcclxuXHRcclxuXHR2YXIgbm93UHJvZHVjdE51bWJlciA9IGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJy5wcm9kdWN0X251bWJlcicpO1xyXG5cdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJy5wcmV2JykuYWRkRXZlbnRMaXN0ZW5lcignY2xpY2snLGZ1bmN0aW9uKGUpe1xyXG5cdFx0XHJcblx0XHRpZihnbG9iYWxWYXJpYWJsZXMucHJvZHVjdFRyYW5zZm9ybSA8IDAgKXtcclxuXHRcdFx0XHJcblx0XHRcdGlmKGdsb2JhbFZhcmlhYmxlcy5zaG93UHJvZHVjdE51bWJlciA9PT0gMCApe1xyXG5cdFx0XHRcdGdsb2JhbFZhcmlhYmxlcy5zaG93UHJvZHVjdE51bWJlciA9IDE7XHJcblx0XHRcdH0gZWxzZSB7XHJcblx0XHRcdFx0Z2xvYmFsVmFyaWFibGVzLnNob3dQcm9kdWN0TnVtYmVyID0gMDtcclxuXHRcdFx0fVxyXG5cdFx0XHRub3dQcm9kdWN0TnVtYmVyLmlubmVyVGV4dCA9IGdsb2JhbFZhcmlhYmxlcy5zaG93UHJvZHVjdE51bWJlcisxO1xyXG5cdFx0XHRcclxuXHRcdFx0Z2xvYmFsVmFyaWFibGVzLnByb2R1Y3RUcmFuc2Zvcm0gKz0gMTAwO1xyXG5cdFx0XHRwcm9kdWN0SW1hZ2VzVWwuc3R5bGUudHJhbnNmb3JtID0gJ3RyYW5zbGF0ZVgoJytnbG9iYWxWYXJpYWJsZXMucHJvZHVjdFRyYW5zZm9ybSArJyUpJztcclxuXHRcdH1cclxuXHR9KTtcclxufVxyXG5cclxuLyoqXHJcbiAqIEBkZXNjIOygkeq4sCDrsoTtirwg7J2067Kk7Yq4XHJcbiAqL1xyXG5mdW5jdGlvbiBhZGRGb2xkQnV0dG9uRXZlbnQoKXtcclxuXHRcclxuXHRkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcuX2Nsb3NlJykuYWRkRXZlbnRMaXN0ZW5lcignY2xpY2snLGZ1bmN0aW9uKCl7XHJcblx0XHRteUJ1dHRvbkhpZGVPdGhlckJ1dHRvbk9wZW4odGhpcywnLl9vcGVuJyk7XHJcblx0XHRcclxuXHRcdHZhciBkZXRhaWxzID0gZG9jdW1lbnQucXVlcnlTZWxlY3RvcignLnN0b3JlX2RldGFpbHMnKTtcclxuXHRcdGRldGFpbHMuY2xhc3NMaXN0LmFkZCgnY2xvc2UzJyk7XHJcblx0fSk7XHJcbn1cclxuXHJcbi8qKlxyXG4gKiBAZGVzYyDtjrzss5Drs7TquLAg67KE7Yq8IOydtOuypO2KuFxyXG4gKi9cclxuZnVuY3Rpb24gYWRkRXhwYW5kQnV0dG9uRXZlbnQoKXtcclxuXHRcclxuXHRkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcuX29wZW4nKS5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsZnVuY3Rpb24oKXtcclxuXHRcdG15QnV0dG9uSGlkZU90aGVyQnV0dG9uT3Blbih0aGlzLCcuX2Nsb3NlJyk7XHJcblx0XHRcclxuXHRcdHZhciBkZXRhaWxzID0gZG9jdW1lbnQucXVlcnlTZWxlY3RvcignLnN0b3JlX2RldGFpbHMnKTtcclxuXHRcdGRldGFpbHMuY2xhc3NMaXN0LnJlbW92ZSgnY2xvc2UzJyk7XHJcblx0fSk7XHJcbn1cclxuXHJcbi8qKlxyXG4gKiBAZGVzYyDsnpDsi6DsnZgg67KE7Yq8IOuLq+qzoCDri6TrpbjrsoTtirwg7Zmc7ISx7ZmUXHJcbiAqIEBwYXJhbSBvdGhlcklkXHJcbiAqIEBwYXJhbSBfdGhpc1xyXG4gKi9cclxuZnVuY3Rpb24gbXlCdXR0b25IaWRlT3RoZXJCdXR0b25PcGVuKF90aGlzLG90aGVyUXVlcnkpe1xyXG5cdFxyXG5cdF90aGlzLmNsYXNzTGlzdC5yZW1vdmUoJ29wZW4nKTtcclxuXHRfdGhpcy5jbGFzc0xpc3QuYWRkKCdoaWRlJyk7XHJcblx0XHJcblx0dmFyIG90aGVyID0gZG9jdW1lbnQucXVlcnlTZWxlY3RvcihvdGhlclF1ZXJ5KTtcclxuXHRcclxuXHRvdGhlci5jbGFzc0xpc3QucmVtb3ZlKCdoaWRlJyk7XHJcblx0b3RoZXIuY2xhc3NMaXN0LmFkZCgnb3BlbicpO1xyXG59XHJcblxyXG4vKipcclxuICogQGRlc2Mg7L2U66mY7Yq4IOqwr+yImCwg7L2U66mY7Yq4IOumrOyKpO2KuCwg7L2U66mY7Yq4IOuzhOygkCDshYvtjIVcclxuICogQHBhcmFtIGNvbW1lbnRzXHJcbiAqIEBwYXJhbSBjb21tZW50c0xlbmd0aFxyXG4gKiBAcGFyYW0gY29tbWVudEF2ZXJhZ2VTY29yZVxyXG4gKiBAcGFyYW0gcHJvZHVjdElkXHJcbiAqL1xyXG5mdW5jdGlvbiBzZXRDb21tZW50cyhjb21tZW50cyxjb21tZW50c0xlbmd0aCxjb21tZW50QXZlcmFnZVNjb3JlLHByb2R1Y3RJZCl7XHJcblx0XHJcblx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignLmxpc3Rfc2hvcnRfcmV2aWV3JykuaW5uZXJIVE1MID0gaGFuZGxlYmFyUmVnaXN0SGVscGVyLmdldEhhbmRsZWJhclRlbXBsYXRlRnJvbUh0bWwoJyNjb21tZW50X3RlbXBsYXRlJyxjb21tZW50cyk7XHJcblx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignI2NvbW1lbnRfY291bnQnKS5pbm5lckhUTUwgPSBjb21tZW50c0xlbmd0aDtcclxuXHRcclxuXHR2YXIgc3RhcnQgPSAwO1xyXG5cdHZhciBlbmQgPSAzO1xyXG5cdFxyXG5cdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJyNjb21tZW50X2F2ZXJhZ2UnKS5pbm5lckhUTUwgPSBTdHJpbmcoY29tbWVudEF2ZXJhZ2VTY29yZSkuc3Vic3RyKHN0YXJ0LGVuZCk7XHJcblx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignI2NvbW1lbnRfZ3JhcGhfc3RhcicpLnN0eWxlLndpZHRoID0gY29tbWVudEF2ZXJhZ2VTY29yZSAqIDIwICsgJyUnO1xyXG5cdFxyXG5cdGlmKGNvbW1lbnRzTGVuZ3RoID09PSAwKXtcclxuXHRcdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJy5idG5fcmV2aWV3X21vcmUnKS5yZW1vdmUoKTtcclxuXHR9IGVsc2Uge1xyXG5cdFx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignLmJ0bl9yZXZpZXdfbW9yZScpLmhyZWYgPSAnL3Jldmlldy8nK3Byb2R1Y3RJZDtcclxuXHR9XHJcbn1cclxuXHJcblxyXG5cclxuLyoqXHJcbiAqIEBkZXNjIHByb2R1Y3QgZXZlbnQgc2V0dGluZ1xyXG4gKiBAcmV0dXJuc1xyXG4gKi9cclxuZnVuY3Rpb24gc2V0UHJvZHVjdEV2ZW50KHByb2R1Y3RFdmVudCl7XHJcblx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignLmV2ZW50X2luZm8nKS5pbm5lckhUTUwgPSBoYW5kbGViYXJSZWdpc3RIZWxwZXIuZ2V0SGFuZGxlYmFyVGVtcGxhdGVGcm9tSHRtbCgnI3Byb2R1Y3RfZXZlbnRfdGVtcGxhdGUnLHByb2R1Y3RFdmVudCk7XHJcbn1cclxuXHJcbi8qKlxyXG4gKiBAZGVzYyBwcm9kdWN0IOuCtOyaqSDsgr3snoVcclxuICogQHBhcmFtIHByb2R1Y3RDb250ZW50XHJcbiAqIEByZXR1cm5zXHJcbiAqL1xyXG5mdW5jdGlvbiBzZXRQcm9kdWN0Q29udGVudChwcm9kdWN0Q29udGVudCl7XHJcblx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvckFsbCgnLnByb2R1Y3RfY29udGVudCcpLmZvckVhY2goZnVuY3Rpb24oZWxlbWVudCl7XHJcblx0XHRlbGVtZW50LmlubmVySFRNTCA9IHByb2R1Y3RDb250ZW50O1xyXG5cdH0pO1xyXG59XHJcblxyXG4vKipcclxuICogQGRlc2MgcHJvZHVjdCDsg4HshLgg7IWL7YyFXHJcbiAqIEBwYXJhbSBkaXNwbGF5SW5mb1xyXG4gKiBAcGFyYW0gc2F2ZUZpbGVOYW1lXHJcbiAqIEByZXR1cm5zXHJcbiAqL1xyXG5mdW5jdGlvbiBzZXRQcm9kdWN0RGV0YWlsKGRpc3BsYXlJbmZvLCBzYXZlRmlsZU5hbWUpe1xyXG5cdFxyXG5cdGRpc3BsYXlJbmZvLnNhdmVGaWxlTmFtZSA9IHNhdmVGaWxlTmFtZTtcclxuXHRkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcucHJvZHVjdF9sb2NhdGlvbicpLmlubmVySFRNTCA9IGhhbmRsZWJhclJlZ2lzdEhlbHBlci5nZXRIYW5kbGViYXJUZW1wbGF0ZUZyb21IdG1sKCcjZGV0YWlsX2xvY2F0aW9uX3RlbXBsYXRlJyxkaXNwbGF5SW5mbyk7XHJcbn1cclxuXHJcblxyXG4vKipcclxuICogQGRlc2MgZGV0YWlsIEJ1dHRvbiBFdmVudCDqsbjslrTso7zquLBcclxuICogQHJldHVybnNcclxuICovXHJcbmZ1bmN0aW9uIGFkZERldGFpbEJ1dHRvbkV2ZW50KCl7XHJcblx0XHJcblx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignLmluZm9fdGFiX2xzdCcpLmFkZEV2ZW50TGlzdGVuZXIoJ2NsaWNrJyxmdW5jdGlvbihlKXtcclxuXHRcdFxyXG5cdFx0dmFyIHRhcmdldCA9IGUudGFyZ2V0O1xyXG5cdFx0dmFyIHRhcmdldFRhZ05hbWUgPSBlLnRhcmdldC50YWdOYW1lLnRvTG93ZXJDYXNlKCk7XHJcblx0XHRcclxuXHRcdHJlbW92ZUFuY2hvckFjdGl2ZUNsYXNzKGUuY3VycmVudFRhcmdldCk7XHJcblx0XHRcclxuXHRcdHZhciBsaVRhcmdldDtcclxuXHRcdFxyXG5cdFx0aWYgKHRhcmdldFRhZ05hbWUgPT09ICdsaScpIHtcclxuXHRcdFx0dGFyZ2V0LnF1ZXJ5U2VsZWN0b3IoJ2EnKS5jbGFzc0xpc3QuYWRkKCdhY3RpdmUnKTtcclxuXHRcdFx0bGlUYXJnZXQgPSB0YXJnZXQ7XHJcblx0XHR9IGVsc2UgaWYgKHRhcmdldFRhZ05hbWUgPT09ICdhJykge1xyXG5cdFx0XHR0YXJnZXQuY2xhc3NOYW1lID0gJ2FuY2hvciBhY3RpdmUnO1xyXG5cdFx0XHRsaVRhcmdldCA9IHRhcmdldC5wYXJlbnROb2RlO1xyXG5cdFx0fSBlbHNlIGlmICh0YXJnZXRUYWdOYW1lID09PSAnc3BhbicpIHtcclxuXHRcdFx0dGFyZ2V0LnBhcmVudE5vZGUuY2xhc3NOYW1lID0gJ2FuY2hvciBhY3RpdmUnO1xyXG5cdFx0XHRsaVRhcmdldCA9IHRhcmdldC5wYXJlbnROb2RlLnBhcmVudE5vZGU7XHJcblx0XHR9XHJcblx0XHRcclxuXHRcdGNoYW5nZURldGFpbEhpZGUobGlUYXJnZXQpO1xyXG5cdH0pO1xyXG59XHJcblxyXG4vKipcclxuICogQGRlc2MgYW5jaG9yIO2DnOq3uCBhY3RpdmUgY2xhc3Mg7KeA7Jqw6riwXHJcbiAqIEBwYXJhbSBjdXJyZW50VGFyZ2V0XHJcbiAqL1xyXG5mdW5jdGlvbiByZW1vdmVBbmNob3JBY3RpdmVDbGFzcyhjdXJyZW50VGFyZ2V0KXtcclxuXHRcclxuXHR2YXIgZGV0YWlsQW5jaG9yVGFnID0gY3VycmVudFRhcmdldC5xdWVyeVNlbGVjdG9yQWxsKCcuYW5jaG9yJyk7XHJcblx0XHJcblx0ZGV0YWlsQW5jaG9yVGFnLmZvckVhY2goZnVuY3Rpb24oZWxlKXtcclxuXHRcdGVsZS5jbGFzc05hbWUgPSAnYW5jaG9yJztcclxuXHR9KTtcclxufVxyXG5cclxuLyoqXHJcbiAqIEBkZXNjIOyDgeyEuOygleuztOyZgCDsmKTsi5zripTquLggaGlkZSBhbmQgc2hvd1xyXG4gKiBAcGFyYW0gbGlUYXJnZXRcclxuICogQHJldHVybnNcclxuICovXHJcbmZ1bmN0aW9uIGNoYW5nZURldGFpbEhpZGUobGlUYXJnZXQpIHtcclxuXHJcblx0aWYgKGxpVGFyZ2V0LmNsYXNzTGlzdC5jb250YWlucygnX2RldGFpbCcpKSB7XHJcblxyXG5cdFx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignLmRldGFpbF9hcmVhX3dyYXAnKS5jbGFzc0xpc3QucmVtb3ZlKCdoaWRlJyk7XHJcblx0XHRkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcuZGV0YWlsX2xvY2F0aW9uJykuY2xhc3NMaXN0LmFkZCgnaGlkZScpO1xyXG5cdH0gZWxzZSBpZiAobGlUYXJnZXQuY2xhc3NMaXN0LmNvbnRhaW5zKCdfcGF0aCcpKSB7XHJcblxyXG5cdFx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignLmRldGFpbF9hcmVhX3dyYXAnKS5jbGFzc0xpc3QuYWRkKCdoaWRlJyk7XHJcblx0XHRkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcuZGV0YWlsX2xvY2F0aW9uJykuY2xhc3NMaXN0LnJlbW92ZSgnaGlkZScpO1xyXG5cdH1cclxufSJdLCJzb3VyY2VSb290IjoiIn0=