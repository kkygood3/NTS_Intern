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
/******/ 	__webpack_require__.p = "/static/js/detail";
/******/
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = "./detail.js");
/******/ })
/************************************************************************/
/******/ ({

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

document.addEventListener('DOMContentLoaded', function() {
	getDisplayInfos();
	addDetailButtonEvent();
	
	addFoldButtonEvent();
	addExpandButtonEvent();
});

/**
 * @desc displayInfo 정보 가져오기
 * @returns
 */
function getDisplayInfos() {
	var displayInfoId = getDisplayInfoId();

	var displayInfoSendHeader = {
		method : 'GET',
		uri : '/api/products/'+displayInfoId
	};
	
	sendAjax(displayInfoSendHeader,'',function(displayInfoResponse){
		console.log(displayInfoResponse);
		
		setProductImages(displayInfoResponse.productImages, displayInfoResponse.displayInfo.productDescription);
		setProductContent(displayInfoResponse.displayInfo.productContent);
		setProductEvent({productEvent : displayInfoResponse.displayInfo.productEvent});
		
		var start = 0;
		var end = 3;
		
		setHandlebarRegist();
		setComments({comments : displayInfoResponse.comments.slice(start, end)}, displayInfoResponse.comments.length, String(displayInfoResponse.averageScore).substr(start,end), displayInfoResponse.displayInfo.productId);
		setProductDetail(displayInfoResponse.displayInfo, displayInfoResponse.displayInfoImage.saveFileName);
	});
}

/**
 * @returns displayinfoid
 */
function getDisplayInfoId() {
	return document.querySelector('.display_info_detail').dataset.displayinfoid;
}

/**
 * @desc page 최상단 image 셋팅
 * @param productImages
 * @returns
 */
function setProductImages(productImages, productDescription){
	
	var productImageLength = productImages.length;
	document.querySelector('#product_image_max').innerHTML = productImageLength;
	document.querySelector('.product_images_ul').innerHTML = getHandlebarTemplateFromHtml('#product_images_template', {productImages: productImages});
	
	document.querySelectorAll('.product_title').forEach(function(ele){
		ele.innerText = productDescription; 
	});
	
	if(productImageLength === 1){
		document.querySelector('.prev').remove();
		document.querySelector('.nxt').remove();
	} else {
		document.querySelector('.prev').classList.add('hide');
		
		var productImagesUl = document.querySelector('.product_images_ul');
		productImageNextButtonEvent(productImagesUl);
		productImagePrevButtonEvent(productImagesUl);
	}
}

/**
 * @desc 다음 button 클릭 이벤트
 * @param productImagesUl
 */
function productImageNextButtonEvent(productImagesUl){
	
	document.querySelector('.nxt').addEventListener('click',function(e){
		
		myButtonHideOtherButtonOpen(this,'.prev');
		productImagesUl.style.transform = 'translateX(-100%)';
	});
}

/**
 * @desc 이전 버튼 클릭 이벤트
 * @param productImagesUl
 */
function productImagePrevButtonEvent(productImagesUl){
	
	document.querySelector('.prev').addEventListener('click',function(e){
		
		myButtonHideOtherButtonOpen(this,'.nxt');
		productImagesUl.style.transform = 'translateX(0%)';
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
 * @desc set comment
 */
function setComments(comments,commentsLength,commentAverageScore,productId){
	
	document.querySelector('.list_short_review').innerHTML = getHandlebarTemplateFromHtml('#comment_template',comments);
	document.querySelector('#comment_count').innerHTML = commentsLength;
	document.querySelector('#comment_average').innerHTML = commentAverageScore;
	document.querySelector('#comment_graph_star').style.width = commentAverageScore * 20 + '%';
	
	if(commentsLength === 0){
		document.querySelector('.btn_review_more').remove();
	} else {
		document.querySelector('.btn_review_more').href = '/review/'+productId;
	}
}

/**
 * @desc handlebar helper regist 작업 
 * @returns
 */
function setHandlebarRegist(){
	
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
}

/**
 * @desc product event setting
 * @returns
 */
function setProductEvent(productEvent){
	document.querySelector('.event_info').innerHTML = getHandlebarTemplateFromHtml('#product_event_template',productEvent);
}

/**
 * @param productContent
 * @returns
 */
function setProductContent(productContent){
	document.querySelectorAll('.product_content').forEach(function(element){
		element.innerHTML = productContent;
	});
}

/**
 * 
 * @param displayInfo
 * @param saveFileName
 * @returns
 */
function setProductDetail(displayInfo, saveFileName){
	
	displayInfo.saveFileName = saveFileName;
	document.querySelector('.product_location').innerHTML = getHandlebarTemplateFromHtml('#detail_location_template',displayInfo);
}

/**
 * @desc handlebar로 compile해서 html로 만들어주기
 * @param templateId
 * @param compileTemplate
 * @param content
 * @returns html
 */
function getHandlebarTemplateFromHtml(templateId,content){
	
	var template = document.querySelector(templateId).innerHTML;
	var handlebarTemplate = Handlebars.compile(template);
	
	return handlebarTemplate(content);
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
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vd2VicGFjay9ib290c3RyYXAiLCJ3ZWJwYWNrOi8vLy4uL3NlbmRBamF4LmpzIiwid2VicGFjazovLy8uL2RldGFpbC5qcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiO0FBQUE7QUFDQTs7QUFFQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7O0FBRUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7OztBQUdBO0FBQ0E7O0FBRUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQSxrREFBMEMsZ0NBQWdDO0FBQzFFO0FBQ0E7O0FBRUE7QUFDQTtBQUNBO0FBQ0EsZ0VBQXdELGtCQUFrQjtBQUMxRTtBQUNBLHlEQUFpRCxjQUFjO0FBQy9EOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQSxpREFBeUMsaUNBQWlDO0FBQzFFLHdIQUFnSCxtQkFBbUIsRUFBRTtBQUNySTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBLG1DQUEyQiwwQkFBMEIsRUFBRTtBQUN2RCx5Q0FBaUMsZUFBZTtBQUNoRDtBQUNBO0FBQ0E7O0FBRUE7QUFDQSw4REFBc0QsK0RBQStEOztBQUVySDtBQUNBOzs7QUFHQTtBQUNBOzs7Ozs7Ozs7Ozs7QUNsRkE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOztBQUVBOztBQUVBO0FBQ0E7O0FBRUE7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQSwwQjs7Ozs7Ozs7Ozs7QUN2QkE7QUFDQTtBQUNBO0FBQ0EsZUFBZSxtQkFBTyxDQUFDLG1DQUFhOztBQUVwQztBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBLENBQUM7O0FBRUQ7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBLG1CQUFtQiw0REFBNEQ7O0FBRS9FO0FBQ0E7O0FBRUE7QUFDQSxlQUFlLDBEQUEwRDtBQUN6RTtBQUNBLEVBQUU7QUFDRjs7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQSxvSEFBb0gsNkJBQTZCOztBQUVqSjtBQUNBLHFDO0FBQ0EsRUFBRTs7QUFFRjtBQUNBO0FBQ0E7QUFDQSxFQUFFO0FBQ0Y7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOztBQUVBOztBQUVBO0FBQ0E7QUFDQSxFQUFFO0FBQ0Y7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTs7QUFFQTtBQUNBO0FBQ0EsRUFBRTtBQUNGOztBQUVBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBLEVBQUU7QUFDRjs7QUFFQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBOztBQUVBO0FBQ0E7QUFDQSxFQUFFO0FBQ0Y7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7O0FBRUE7O0FBRUE7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQSxFQUFFO0FBQ0Y7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7O0FBRUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0EsRUFBRTs7QUFFRjs7QUFFQTtBQUNBOztBQUVBO0FBQ0E7QUFDQSxFQUFFO0FBQ0Y7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQSxFQUFFO0FBQ0Y7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTs7QUFFQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7O0FBRUE7QUFDQTs7QUFFQTs7QUFFQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQSxHQUFHO0FBQ0g7QUFDQTtBQUNBLEdBQUc7QUFDSDtBQUNBO0FBQ0E7O0FBRUE7QUFDQSxFQUFFO0FBQ0Y7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTs7QUFFQTtBQUNBO0FBQ0EsRUFBRTtBQUNGOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTs7QUFFQTtBQUNBO0FBQ0EsRUFBRTs7QUFFRjtBQUNBO0FBQ0E7QUFDQSxDIiwiZmlsZSI6ImRldGFpbENvbnZlcnQuanMiLCJzb3VyY2VzQ29udGVudCI6WyIgXHQvLyBUaGUgbW9kdWxlIGNhY2hlXG4gXHR2YXIgaW5zdGFsbGVkTW9kdWxlcyA9IHt9O1xuXG4gXHQvLyBUaGUgcmVxdWlyZSBmdW5jdGlvblxuIFx0ZnVuY3Rpb24gX193ZWJwYWNrX3JlcXVpcmVfXyhtb2R1bGVJZCkge1xuXG4gXHRcdC8vIENoZWNrIGlmIG1vZHVsZSBpcyBpbiBjYWNoZVxuIFx0XHRpZihpbnN0YWxsZWRNb2R1bGVzW21vZHVsZUlkXSkge1xuIFx0XHRcdHJldHVybiBpbnN0YWxsZWRNb2R1bGVzW21vZHVsZUlkXS5leHBvcnRzO1xuIFx0XHR9XG4gXHRcdC8vIENyZWF0ZSBhIG5ldyBtb2R1bGUgKGFuZCBwdXQgaXQgaW50byB0aGUgY2FjaGUpXG4gXHRcdHZhciBtb2R1bGUgPSBpbnN0YWxsZWRNb2R1bGVzW21vZHVsZUlkXSA9IHtcbiBcdFx0XHRpOiBtb2R1bGVJZCxcbiBcdFx0XHRsOiBmYWxzZSxcbiBcdFx0XHRleHBvcnRzOiB7fVxuIFx0XHR9O1xuXG4gXHRcdC8vIEV4ZWN1dGUgdGhlIG1vZHVsZSBmdW5jdGlvblxuIFx0XHRtb2R1bGVzW21vZHVsZUlkXS5jYWxsKG1vZHVsZS5leHBvcnRzLCBtb2R1bGUsIG1vZHVsZS5leHBvcnRzLCBfX3dlYnBhY2tfcmVxdWlyZV9fKTtcblxuIFx0XHQvLyBGbGFnIHRoZSBtb2R1bGUgYXMgbG9hZGVkXG4gXHRcdG1vZHVsZS5sID0gdHJ1ZTtcblxuIFx0XHQvLyBSZXR1cm4gdGhlIGV4cG9ydHMgb2YgdGhlIG1vZHVsZVxuIFx0XHRyZXR1cm4gbW9kdWxlLmV4cG9ydHM7XG4gXHR9XG5cblxuIFx0Ly8gZXhwb3NlIHRoZSBtb2R1bGVzIG9iamVjdCAoX193ZWJwYWNrX21vZHVsZXNfXylcbiBcdF9fd2VicGFja19yZXF1aXJlX18ubSA9IG1vZHVsZXM7XG5cbiBcdC8vIGV4cG9zZSB0aGUgbW9kdWxlIGNhY2hlXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLmMgPSBpbnN0YWxsZWRNb2R1bGVzO1xuXG4gXHQvLyBkZWZpbmUgZ2V0dGVyIGZ1bmN0aW9uIGZvciBoYXJtb255IGV4cG9ydHNcbiBcdF9fd2VicGFja19yZXF1aXJlX18uZCA9IGZ1bmN0aW9uKGV4cG9ydHMsIG5hbWUsIGdldHRlcikge1xuIFx0XHRpZighX193ZWJwYWNrX3JlcXVpcmVfXy5vKGV4cG9ydHMsIG5hbWUpKSB7XG4gXHRcdFx0T2JqZWN0LmRlZmluZVByb3BlcnR5KGV4cG9ydHMsIG5hbWUsIHsgZW51bWVyYWJsZTogdHJ1ZSwgZ2V0OiBnZXR0ZXIgfSk7XG4gXHRcdH1cbiBcdH07XG5cbiBcdC8vIGRlZmluZSBfX2VzTW9kdWxlIG9uIGV4cG9ydHNcbiBcdF9fd2VicGFja19yZXF1aXJlX18uciA9IGZ1bmN0aW9uKGV4cG9ydHMpIHtcbiBcdFx0aWYodHlwZW9mIFN5bWJvbCAhPT0gJ3VuZGVmaW5lZCcgJiYgU3ltYm9sLnRvU3RyaW5nVGFnKSB7XG4gXHRcdFx0T2JqZWN0LmRlZmluZVByb3BlcnR5KGV4cG9ydHMsIFN5bWJvbC50b1N0cmluZ1RhZywgeyB2YWx1ZTogJ01vZHVsZScgfSk7XG4gXHRcdH1cbiBcdFx0T2JqZWN0LmRlZmluZVByb3BlcnR5KGV4cG9ydHMsICdfX2VzTW9kdWxlJywgeyB2YWx1ZTogdHJ1ZSB9KTtcbiBcdH07XG5cbiBcdC8vIGNyZWF0ZSBhIGZha2UgbmFtZXNwYWNlIG9iamVjdFxuIFx0Ly8gbW9kZSAmIDE6IHZhbHVlIGlzIGEgbW9kdWxlIGlkLCByZXF1aXJlIGl0XG4gXHQvLyBtb2RlICYgMjogbWVyZ2UgYWxsIHByb3BlcnRpZXMgb2YgdmFsdWUgaW50byB0aGUgbnNcbiBcdC8vIG1vZGUgJiA0OiByZXR1cm4gdmFsdWUgd2hlbiBhbHJlYWR5IG5zIG9iamVjdFxuIFx0Ly8gbW9kZSAmIDh8MTogYmVoYXZlIGxpa2UgcmVxdWlyZVxuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy50ID0gZnVuY3Rpb24odmFsdWUsIG1vZGUpIHtcbiBcdFx0aWYobW9kZSAmIDEpIHZhbHVlID0gX193ZWJwYWNrX3JlcXVpcmVfXyh2YWx1ZSk7XG4gXHRcdGlmKG1vZGUgJiA4KSByZXR1cm4gdmFsdWU7XG4gXHRcdGlmKChtb2RlICYgNCkgJiYgdHlwZW9mIHZhbHVlID09PSAnb2JqZWN0JyAmJiB2YWx1ZSAmJiB2YWx1ZS5fX2VzTW9kdWxlKSByZXR1cm4gdmFsdWU7XG4gXHRcdHZhciBucyA9IE9iamVjdC5jcmVhdGUobnVsbCk7XG4gXHRcdF9fd2VicGFja19yZXF1aXJlX18ucihucyk7XG4gXHRcdE9iamVjdC5kZWZpbmVQcm9wZXJ0eShucywgJ2RlZmF1bHQnLCB7IGVudW1lcmFibGU6IHRydWUsIHZhbHVlOiB2YWx1ZSB9KTtcbiBcdFx0aWYobW9kZSAmIDIgJiYgdHlwZW9mIHZhbHVlICE9ICdzdHJpbmcnKSBmb3IodmFyIGtleSBpbiB2YWx1ZSkgX193ZWJwYWNrX3JlcXVpcmVfXy5kKG5zLCBrZXksIGZ1bmN0aW9uKGtleSkgeyByZXR1cm4gdmFsdWVba2V5XTsgfS5iaW5kKG51bGwsIGtleSkpO1xuIFx0XHRyZXR1cm4gbnM7XG4gXHR9O1xuXG4gXHQvLyBnZXREZWZhdWx0RXhwb3J0IGZ1bmN0aW9uIGZvciBjb21wYXRpYmlsaXR5IHdpdGggbm9uLWhhcm1vbnkgbW9kdWxlc1xuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5uID0gZnVuY3Rpb24obW9kdWxlKSB7XG4gXHRcdHZhciBnZXR0ZXIgPSBtb2R1bGUgJiYgbW9kdWxlLl9fZXNNb2R1bGUgP1xuIFx0XHRcdGZ1bmN0aW9uIGdldERlZmF1bHQoKSB7IHJldHVybiBtb2R1bGVbJ2RlZmF1bHQnXTsgfSA6XG4gXHRcdFx0ZnVuY3Rpb24gZ2V0TW9kdWxlRXhwb3J0cygpIHsgcmV0dXJuIG1vZHVsZTsgfTtcbiBcdFx0X193ZWJwYWNrX3JlcXVpcmVfXy5kKGdldHRlciwgJ2EnLCBnZXR0ZXIpO1xuIFx0XHRyZXR1cm4gZ2V0dGVyO1xuIFx0fTtcblxuIFx0Ly8gT2JqZWN0LnByb3RvdHlwZS5oYXNPd25Qcm9wZXJ0eS5jYWxsXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLm8gPSBmdW5jdGlvbihvYmplY3QsIHByb3BlcnR5KSB7IHJldHVybiBPYmplY3QucHJvdG90eXBlLmhhc093blByb3BlcnR5LmNhbGwob2JqZWN0LCBwcm9wZXJ0eSk7IH07XG5cbiBcdC8vIF9fd2VicGFja19wdWJsaWNfcGF0aF9fXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLnAgPSBcIi9zdGF0aWMvanMvZGV0YWlsXCI7XG5cblxuIFx0Ly8gTG9hZCBlbnRyeSBtb2R1bGUgYW5kIHJldHVybiBleHBvcnRzXG4gXHRyZXR1cm4gX193ZWJwYWNrX3JlcXVpcmVfXyhfX3dlYnBhY2tfcmVxdWlyZV9fLnMgPSBcIi4vZGV0YWlsLmpzXCIpO1xuIiwiLyoqXHJcbiAqIEBkZXNjIHNlbmRBamF4IOu5hOuPmeq4sCDsmpTssq0gKCDqs7XthrUg7ZWo7IiYIClcclxuICogQHBhcmFtIHNlbmRIZWFkZXIgKCBtZXRob2QsIHVyaSlcclxuICogQHBhcmFtIHNlbmREYXRhICgg67O064K8IOuNsOydtO2EsCApXHJcbiAqIEBwYXJhbSBjYWxsYmFjayAoIOuNsOydtO2EsOulvCDqsIDsp4Dqs6Ag7Jio65KkIOyImO2Wie2VoCBjYWxsYmFjayDtlajsiJgpXHJcbiAqIEByZXR1cm5zIHhoci5yZXNwb25zZSAoIEpTT04g7ZiV7YOc66GcIHBhcnNpbmcgKVxyXG4gKi9cclxuZnVuY3Rpb24gc2VuZEFqYXgoc2VuZEhlYWRlciwgc2VuZERhdGEsIGNhbGxiYWNrKSB7XHJcblx0dmFyIHhociA9IG5ldyBYTUxIdHRwUmVxdWVzdCgpO1xyXG5cclxuXHR4aHIub3BlbihzZW5kSGVhZGVyLm1ldGhvZCwgc2VuZEhlYWRlci51cmksIHRydWUpO1xyXG5cclxuXHR4aHIuc2V0UmVxdWVzdEhlYWRlcihcIkNvbnRlbnQtdHlwZVwiLCBcImFwcGxpY2F0aW9uL3gtd3d3LWZvcm0tdXJsZW5jb2RlZFwiKTtcclxuXHR4aHIuc2VuZChzZW5kRGF0YSk7XHJcblxyXG5cdHhoci5vbnJlYWR5c3RhdGVjaGFuZ2UgPSBmdW5jdGlvbigpIHtcclxuXHJcblx0XHRpZiAoeGhyLnJlYWR5U3RhdGUgPT09IHhoci5ET05FICYmIHhoci5zdGF0dXMgPT09IDIwMCkge1xyXG5cdFx0XHRjYWxsYmFjay5hcHBseSh0aGlzLCBbIEpTT04ucGFyc2UoeGhyLnJlc3BvbnNlKSBdKTtcclxuXHRcdH1cclxuXHR9XHJcbn1cclxuXHJcbm1vZHVsZS5leHBvcnRzID0gc2VuZEFqYXg7IiwiLyoqXHJcbiAqIEBkZXNjIOyghOyXreuzgOyImCBcclxuICovXHJcbnZhciBzZW5kQWpheCA9IHJlcXVpcmUoJy4uL3NlbmRBamF4Jyk7XHJcblxyXG5kb2N1bWVudC5hZGRFdmVudExpc3RlbmVyKCdET01Db250ZW50TG9hZGVkJywgZnVuY3Rpb24oKSB7XHJcblx0Z2V0RGlzcGxheUluZm9zKCk7XHJcblx0YWRkRGV0YWlsQnV0dG9uRXZlbnQoKTtcclxuXHRcclxuXHRhZGRGb2xkQnV0dG9uRXZlbnQoKTtcclxuXHRhZGRFeHBhbmRCdXR0b25FdmVudCgpO1xyXG59KTtcclxuXHJcbi8qKlxyXG4gKiBAZGVzYyBkaXNwbGF5SW5mbyDsoJXrs7Qg6rCA7KC47Jik6riwXHJcbiAqIEByZXR1cm5zXHJcbiAqL1xyXG5mdW5jdGlvbiBnZXREaXNwbGF5SW5mb3MoKSB7XHJcblx0dmFyIGRpc3BsYXlJbmZvSWQgPSBnZXREaXNwbGF5SW5mb0lkKCk7XHJcblxyXG5cdHZhciBkaXNwbGF5SW5mb1NlbmRIZWFkZXIgPSB7XHJcblx0XHRtZXRob2QgOiAnR0VUJyxcclxuXHRcdHVyaSA6ICcvYXBpL3Byb2R1Y3RzLycrZGlzcGxheUluZm9JZFxyXG5cdH07XHJcblx0XHJcblx0c2VuZEFqYXgoZGlzcGxheUluZm9TZW5kSGVhZGVyLCcnLGZ1bmN0aW9uKGRpc3BsYXlJbmZvUmVzcG9uc2Upe1xyXG5cdFx0Y29uc29sZS5sb2coZGlzcGxheUluZm9SZXNwb25zZSk7XHJcblx0XHRcclxuXHRcdHNldFByb2R1Y3RJbWFnZXMoZGlzcGxheUluZm9SZXNwb25zZS5wcm9kdWN0SW1hZ2VzLCBkaXNwbGF5SW5mb1Jlc3BvbnNlLmRpc3BsYXlJbmZvLnByb2R1Y3REZXNjcmlwdGlvbik7XHJcblx0XHRzZXRQcm9kdWN0Q29udGVudChkaXNwbGF5SW5mb1Jlc3BvbnNlLmRpc3BsYXlJbmZvLnByb2R1Y3RDb250ZW50KTtcclxuXHRcdHNldFByb2R1Y3RFdmVudCh7cHJvZHVjdEV2ZW50IDogZGlzcGxheUluZm9SZXNwb25zZS5kaXNwbGF5SW5mby5wcm9kdWN0RXZlbnR9KTtcclxuXHRcdFxyXG5cdFx0dmFyIHN0YXJ0ID0gMDtcclxuXHRcdHZhciBlbmQgPSAzO1xyXG5cdFx0XHJcblx0XHRzZXRIYW5kbGViYXJSZWdpc3QoKTtcclxuXHRcdHNldENvbW1lbnRzKHtjb21tZW50cyA6IGRpc3BsYXlJbmZvUmVzcG9uc2UuY29tbWVudHMuc2xpY2Uoc3RhcnQsIGVuZCl9LCBkaXNwbGF5SW5mb1Jlc3BvbnNlLmNvbW1lbnRzLmxlbmd0aCwgU3RyaW5nKGRpc3BsYXlJbmZvUmVzcG9uc2UuYXZlcmFnZVNjb3JlKS5zdWJzdHIoc3RhcnQsZW5kKSwgZGlzcGxheUluZm9SZXNwb25zZS5kaXNwbGF5SW5mby5wcm9kdWN0SWQpO1xyXG5cdFx0c2V0UHJvZHVjdERldGFpbChkaXNwbGF5SW5mb1Jlc3BvbnNlLmRpc3BsYXlJbmZvLCBkaXNwbGF5SW5mb1Jlc3BvbnNlLmRpc3BsYXlJbmZvSW1hZ2Uuc2F2ZUZpbGVOYW1lKTtcclxuXHR9KTtcclxufVxyXG5cclxuLyoqXHJcbiAqIEByZXR1cm5zIGRpc3BsYXlpbmZvaWRcclxuICovXHJcbmZ1bmN0aW9uIGdldERpc3BsYXlJbmZvSWQoKSB7XHJcblx0cmV0dXJuIGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJy5kaXNwbGF5X2luZm9fZGV0YWlsJykuZGF0YXNldC5kaXNwbGF5aW5mb2lkO1xyXG59XHJcblxyXG4vKipcclxuICogQGRlc2MgcGFnZSDstZzsg4Hri6ggaW1hZ2Ug7IWL7YyFXHJcbiAqIEBwYXJhbSBwcm9kdWN0SW1hZ2VzXHJcbiAqIEByZXR1cm5zXHJcbiAqL1xyXG5mdW5jdGlvbiBzZXRQcm9kdWN0SW1hZ2VzKHByb2R1Y3RJbWFnZXMsIHByb2R1Y3REZXNjcmlwdGlvbil7XHJcblx0XHJcblx0dmFyIHByb2R1Y3RJbWFnZUxlbmd0aCA9IHByb2R1Y3RJbWFnZXMubGVuZ3RoO1xyXG5cdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJyNwcm9kdWN0X2ltYWdlX21heCcpLmlubmVySFRNTCA9IHByb2R1Y3RJbWFnZUxlbmd0aDtcclxuXHRkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcucHJvZHVjdF9pbWFnZXNfdWwnKS5pbm5lckhUTUwgPSBnZXRIYW5kbGViYXJUZW1wbGF0ZUZyb21IdG1sKCcjcHJvZHVjdF9pbWFnZXNfdGVtcGxhdGUnLCB7cHJvZHVjdEltYWdlczogcHJvZHVjdEltYWdlc30pO1xyXG5cdFxyXG5cdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3JBbGwoJy5wcm9kdWN0X3RpdGxlJykuZm9yRWFjaChmdW5jdGlvbihlbGUpe1xyXG5cdFx0ZWxlLmlubmVyVGV4dCA9IHByb2R1Y3REZXNjcmlwdGlvbjsgXHJcblx0fSk7XHJcblx0XHJcblx0aWYocHJvZHVjdEltYWdlTGVuZ3RoID09PSAxKXtcclxuXHRcdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJy5wcmV2JykucmVtb3ZlKCk7XHJcblx0XHRkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcubnh0JykucmVtb3ZlKCk7XHJcblx0fSBlbHNlIHtcclxuXHRcdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJy5wcmV2JykuY2xhc3NMaXN0LmFkZCgnaGlkZScpO1xyXG5cdFx0XHJcblx0XHR2YXIgcHJvZHVjdEltYWdlc1VsID0gZG9jdW1lbnQucXVlcnlTZWxlY3RvcignLnByb2R1Y3RfaW1hZ2VzX3VsJyk7XHJcblx0XHRwcm9kdWN0SW1hZ2VOZXh0QnV0dG9uRXZlbnQocHJvZHVjdEltYWdlc1VsKTtcclxuXHRcdHByb2R1Y3RJbWFnZVByZXZCdXR0b25FdmVudChwcm9kdWN0SW1hZ2VzVWwpO1xyXG5cdH1cclxufVxyXG5cclxuLyoqXHJcbiAqIEBkZXNjIOuLpOydjCBidXR0b24g7YG066atIOydtOuypO2KuFxyXG4gKiBAcGFyYW0gcHJvZHVjdEltYWdlc1VsXHJcbiAqL1xyXG5mdW5jdGlvbiBwcm9kdWN0SW1hZ2VOZXh0QnV0dG9uRXZlbnQocHJvZHVjdEltYWdlc1VsKXtcclxuXHRcclxuXHRkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcubnh0JykuYWRkRXZlbnRMaXN0ZW5lcignY2xpY2snLGZ1bmN0aW9uKGUpe1xyXG5cdFx0XHJcblx0XHRteUJ1dHRvbkhpZGVPdGhlckJ1dHRvbk9wZW4odGhpcywnLnByZXYnKTtcclxuXHRcdHByb2R1Y3RJbWFnZXNVbC5zdHlsZS50cmFuc2Zvcm0gPSAndHJhbnNsYXRlWCgtMTAwJSknO1xyXG5cdH0pO1xyXG59XHJcblxyXG4vKipcclxuICogQGRlc2Mg7J207KCEIOuyhO2KvCDtgbTrpq0g7J2067Kk7Yq4XHJcbiAqIEBwYXJhbSBwcm9kdWN0SW1hZ2VzVWxcclxuICovXHJcbmZ1bmN0aW9uIHByb2R1Y3RJbWFnZVByZXZCdXR0b25FdmVudChwcm9kdWN0SW1hZ2VzVWwpe1xyXG5cdFxyXG5cdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJy5wcmV2JykuYWRkRXZlbnRMaXN0ZW5lcignY2xpY2snLGZ1bmN0aW9uKGUpe1xyXG5cdFx0XHJcblx0XHRteUJ1dHRvbkhpZGVPdGhlckJ1dHRvbk9wZW4odGhpcywnLm54dCcpO1xyXG5cdFx0cHJvZHVjdEltYWdlc1VsLnN0eWxlLnRyYW5zZm9ybSA9ICd0cmFuc2xhdGVYKDAlKSc7XHJcblx0fSk7XHJcbn1cclxuXHJcbi8qKlxyXG4gKiBAZGVzYyDsoJHquLAg67KE7Yq8IOydtOuypO2KuFxyXG4gKi9cclxuZnVuY3Rpb24gYWRkRm9sZEJ1dHRvbkV2ZW50KCl7XHJcblx0XHJcblx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignLl9jbG9zZScpLmFkZEV2ZW50TGlzdGVuZXIoJ2NsaWNrJyxmdW5jdGlvbigpe1xyXG5cdFx0bXlCdXR0b25IaWRlT3RoZXJCdXR0b25PcGVuKHRoaXMsJy5fb3BlbicpO1xyXG5cdFx0XHJcblx0XHR2YXIgZGV0YWlscyA9IGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJy5zdG9yZV9kZXRhaWxzJyk7XHJcblx0XHRkZXRhaWxzLmNsYXNzTGlzdC5hZGQoJ2Nsb3NlMycpO1xyXG5cdH0pO1xyXG59XHJcblxyXG4vKipcclxuICogQGRlc2Mg7Y687LOQ67O06riwIOuyhO2KvCDsnbTrsqTtirhcclxuICovXHJcbmZ1bmN0aW9uIGFkZEV4cGFuZEJ1dHRvbkV2ZW50KCl7XHJcblx0XHJcblx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignLl9vcGVuJykuYWRkRXZlbnRMaXN0ZW5lcignY2xpY2snLGZ1bmN0aW9uKCl7XHJcblx0XHRteUJ1dHRvbkhpZGVPdGhlckJ1dHRvbk9wZW4odGhpcywnLl9jbG9zZScpO1xyXG5cdFx0XHJcblx0XHR2YXIgZGV0YWlscyA9IGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJy5zdG9yZV9kZXRhaWxzJyk7XHJcblx0XHRkZXRhaWxzLmNsYXNzTGlzdC5yZW1vdmUoJ2Nsb3NlMycpO1xyXG5cdH0pO1xyXG59XHJcblxyXG4vKipcclxuICogQGRlc2Mg7J6Q7Iug7J2YIOuyhO2KvCDri6vqs6Ag64uk66W467KE7Yq8IO2ZnOyEse2ZlFxyXG4gKiBAcGFyYW0gb3RoZXJJZFxyXG4gKiBAcGFyYW0gX3RoaXNcclxuICovXHJcbmZ1bmN0aW9uIG15QnV0dG9uSGlkZU90aGVyQnV0dG9uT3BlbihfdGhpcyxvdGhlclF1ZXJ5KXtcclxuXHRcclxuXHRfdGhpcy5jbGFzc0xpc3QucmVtb3ZlKCdvcGVuJyk7XHJcblx0X3RoaXMuY2xhc3NMaXN0LmFkZCgnaGlkZScpO1xyXG5cdFxyXG5cdHZhciBvdGhlciA9IGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3Iob3RoZXJRdWVyeSk7XHJcblx0XHJcblx0b3RoZXIuY2xhc3NMaXN0LnJlbW92ZSgnaGlkZScpO1xyXG5cdG90aGVyLmNsYXNzTGlzdC5hZGQoJ29wZW4nKTtcclxufVxyXG5cclxuLyoqXHJcbiAqIEBkZXNjIHNldCBjb21tZW50XHJcbiAqL1xyXG5mdW5jdGlvbiBzZXRDb21tZW50cyhjb21tZW50cyxjb21tZW50c0xlbmd0aCxjb21tZW50QXZlcmFnZVNjb3JlLHByb2R1Y3RJZCl7XHJcblx0XHJcblx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignLmxpc3Rfc2hvcnRfcmV2aWV3JykuaW5uZXJIVE1MID0gZ2V0SGFuZGxlYmFyVGVtcGxhdGVGcm9tSHRtbCgnI2NvbW1lbnRfdGVtcGxhdGUnLGNvbW1lbnRzKTtcclxuXHRkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcjY29tbWVudF9jb3VudCcpLmlubmVySFRNTCA9IGNvbW1lbnRzTGVuZ3RoO1xyXG5cdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJyNjb21tZW50X2F2ZXJhZ2UnKS5pbm5lckhUTUwgPSBjb21tZW50QXZlcmFnZVNjb3JlO1xyXG5cdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJyNjb21tZW50X2dyYXBoX3N0YXInKS5zdHlsZS53aWR0aCA9IGNvbW1lbnRBdmVyYWdlU2NvcmUgKiAyMCArICclJztcclxuXHRcclxuXHRpZihjb21tZW50c0xlbmd0aCA9PT0gMCl7XHJcblx0XHRkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcuYnRuX3Jldmlld19tb3JlJykucmVtb3ZlKCk7XHJcblx0fSBlbHNlIHtcclxuXHRcdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJy5idG5fcmV2aWV3X21vcmUnKS5ocmVmID0gJy9yZXZpZXcvJytwcm9kdWN0SWQ7XHJcblx0fVxyXG59XHJcblxyXG4vKipcclxuICogQGRlc2MgaGFuZGxlYmFyIGhlbHBlciByZWdpc3Qg7J6R7JeFIFxyXG4gKiBAcmV0dXJuc1xyXG4gKi9cclxuZnVuY3Rpb24gc2V0SGFuZGxlYmFyUmVnaXN0KCl7XHJcblx0XHJcblx0SGFuZGxlYmFycy5yZWdpc3RlckhlbHBlcigndXNlckVtYWlsRW5jcnlwdCcsIGZ1bmN0aW9uKHJlc2VydmF0aW9uRW1haWwpIHtcclxuXHRcdCAgIFxyXG5cdFx0dmFyIHN0YXJ0ID0gMDtcclxuXHQgICAgdmFyIGVuZCA9IDQ7XHJcblx0ICAgIFxyXG5cdFx0dmFyIGVuY3J5cHRlZFVzZXJFbWFpbCA9IHJlc2VydmF0aW9uRW1haWwuc3Vic3RyaW5nKHN0YXJ0LGVuZCkrJyoqKionO1xyXG5cdCAgICByZXR1cm4gbmV3IEhhbmRsZWJhcnMuU2FmZVN0cmluZyhlbmNyeXB0ZWRVc2VyRW1haWwpO1xyXG5cdH0pO1xyXG5cdFxyXG5cdEhhbmRsZWJhcnMucmVnaXN0ZXJIZWxwZXIoJ3Jlc2VydmF0aW9uRGF0ZVlZWVlNTUREJywgZnVuY3Rpb24ocmVzZXJ2YXRpb25EYXRlKSB7XHJcblx0ICAgIFxyXG5cdFx0dmFyIHN0YXJ0ID0gMDtcclxuXHQgICAgdmFyIGVuZCA9IDEwO1xyXG5cdFx0XHJcblx0ICAgIHZhciByZXNlcnZhdGlvbkRhdGVZWVlZTU1ERCA9IHJlc2VydmF0aW9uRGF0ZS5zdWJzdHJpbmcoc3RhcnQsZW5kKS5yZXBsYWNlKC8tL2csJy4nKSsnLic7XHJcblx0ICAgIHJldHVybiBuZXcgSGFuZGxlYmFycy5TYWZlU3RyaW5nKHJlc2VydmF0aW9uRGF0ZVlZWVlNTUREKTtcclxuXHR9KTtcclxufVxyXG5cclxuLyoqXHJcbiAqIEBkZXNjIHByb2R1Y3QgZXZlbnQgc2V0dGluZ1xyXG4gKiBAcmV0dXJuc1xyXG4gKi9cclxuZnVuY3Rpb24gc2V0UHJvZHVjdEV2ZW50KHByb2R1Y3RFdmVudCl7XHJcblx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignLmV2ZW50X2luZm8nKS5pbm5lckhUTUwgPSBnZXRIYW5kbGViYXJUZW1wbGF0ZUZyb21IdG1sKCcjcHJvZHVjdF9ldmVudF90ZW1wbGF0ZScscHJvZHVjdEV2ZW50KTtcclxufVxyXG5cclxuLyoqXHJcbiAqIEBwYXJhbSBwcm9kdWN0Q29udGVudFxyXG4gKiBAcmV0dXJuc1xyXG4gKi9cclxuZnVuY3Rpb24gc2V0UHJvZHVjdENvbnRlbnQocHJvZHVjdENvbnRlbnQpe1xyXG5cdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3JBbGwoJy5wcm9kdWN0X2NvbnRlbnQnKS5mb3JFYWNoKGZ1bmN0aW9uKGVsZW1lbnQpe1xyXG5cdFx0ZWxlbWVudC5pbm5lckhUTUwgPSBwcm9kdWN0Q29udGVudDtcclxuXHR9KTtcclxufVxyXG5cclxuLyoqXHJcbiAqIFxyXG4gKiBAcGFyYW0gZGlzcGxheUluZm9cclxuICogQHBhcmFtIHNhdmVGaWxlTmFtZVxyXG4gKiBAcmV0dXJuc1xyXG4gKi9cclxuZnVuY3Rpb24gc2V0UHJvZHVjdERldGFpbChkaXNwbGF5SW5mbywgc2F2ZUZpbGVOYW1lKXtcclxuXHRcclxuXHRkaXNwbGF5SW5mby5zYXZlRmlsZU5hbWUgPSBzYXZlRmlsZU5hbWU7XHJcblx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignLnByb2R1Y3RfbG9jYXRpb24nKS5pbm5lckhUTUwgPSBnZXRIYW5kbGViYXJUZW1wbGF0ZUZyb21IdG1sKCcjZGV0YWlsX2xvY2F0aW9uX3RlbXBsYXRlJyxkaXNwbGF5SW5mbyk7XHJcbn1cclxuXHJcbi8qKlxyXG4gKiBAZGVzYyBoYW5kbGViYXLroZwgY29tcGlsZe2VtOyEnCBodG1s66GcIOunjOuTpOyWtOyjvOq4sFxyXG4gKiBAcGFyYW0gdGVtcGxhdGVJZFxyXG4gKiBAcGFyYW0gY29tcGlsZVRlbXBsYXRlXHJcbiAqIEBwYXJhbSBjb250ZW50XHJcbiAqIEByZXR1cm5zIGh0bWxcclxuICovXHJcbmZ1bmN0aW9uIGdldEhhbmRsZWJhclRlbXBsYXRlRnJvbUh0bWwodGVtcGxhdGVJZCxjb250ZW50KXtcclxuXHRcclxuXHR2YXIgdGVtcGxhdGUgPSBkb2N1bWVudC5xdWVyeVNlbGVjdG9yKHRlbXBsYXRlSWQpLmlubmVySFRNTDtcclxuXHR2YXIgaGFuZGxlYmFyVGVtcGxhdGUgPSBIYW5kbGViYXJzLmNvbXBpbGUodGVtcGxhdGUpO1xyXG5cdFxyXG5cdHJldHVybiBoYW5kbGViYXJUZW1wbGF0ZShjb250ZW50KTtcclxufVxyXG5cclxuLyoqXHJcbiAqIEBkZXNjIGRldGFpbCBCdXR0b24gRXZlbnQg6rG47Ja07KO86riwXHJcbiAqIEByZXR1cm5zXHJcbiAqL1xyXG5mdW5jdGlvbiBhZGREZXRhaWxCdXR0b25FdmVudCgpe1xyXG5cdFxyXG5cdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJy5pbmZvX3RhYl9sc3QnKS5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsZnVuY3Rpb24oZSl7XHJcblx0XHRcclxuXHRcdHZhciB0YXJnZXQgPSBlLnRhcmdldDtcclxuXHRcdHZhciB0YXJnZXRUYWdOYW1lID0gZS50YXJnZXQudGFnTmFtZS50b0xvd2VyQ2FzZSgpO1xyXG5cdFx0XHJcblx0XHRyZW1vdmVBbmNob3JBY3RpdmVDbGFzcyhlLmN1cnJlbnRUYXJnZXQpO1xyXG5cdFx0XHJcblx0XHR2YXIgbGlUYXJnZXQ7XHJcblx0XHRcclxuXHRcdGlmICh0YXJnZXRUYWdOYW1lID09PSAnbGknKSB7XHJcblx0XHRcdHRhcmdldC5xdWVyeVNlbGVjdG9yKCdhJykuY2xhc3NMaXN0LmFkZCgnYWN0aXZlJyk7XHJcblx0XHRcdGxpVGFyZ2V0ID0gdGFyZ2V0O1xyXG5cdFx0fSBlbHNlIGlmICh0YXJnZXRUYWdOYW1lID09PSAnYScpIHtcclxuXHRcdFx0dGFyZ2V0LmNsYXNzTmFtZSA9ICdhbmNob3IgYWN0aXZlJztcclxuXHRcdFx0bGlUYXJnZXQgPSB0YXJnZXQucGFyZW50Tm9kZTtcclxuXHRcdH0gZWxzZSBpZiAodGFyZ2V0VGFnTmFtZSA9PT0gJ3NwYW4nKSB7XHJcblx0XHRcdHRhcmdldC5wYXJlbnROb2RlLmNsYXNzTmFtZSA9ICdhbmNob3IgYWN0aXZlJztcclxuXHRcdFx0bGlUYXJnZXQgPSB0YXJnZXQucGFyZW50Tm9kZS5wYXJlbnROb2RlO1xyXG5cdFx0fVxyXG5cdFx0XHJcblx0XHRjaGFuZ2VEZXRhaWxIaWRlKGxpVGFyZ2V0KTtcclxuXHR9KTtcclxufVxyXG5cclxuLyoqXHJcbiAqIEBkZXNjIGFuY2hvciDtg5zqt7ggYWN0aXZlIGNsYXNzIOyngOyasOq4sFxyXG4gKiBAcGFyYW0gY3VycmVudFRhcmdldFxyXG4gKi9cclxuZnVuY3Rpb24gcmVtb3ZlQW5jaG9yQWN0aXZlQ2xhc3MoY3VycmVudFRhcmdldCl7XHJcblx0XHJcblx0dmFyIGRldGFpbEFuY2hvclRhZyA9IGN1cnJlbnRUYXJnZXQucXVlcnlTZWxlY3RvckFsbCgnLmFuY2hvcicpO1xyXG5cdFxyXG5cdGRldGFpbEFuY2hvclRhZy5mb3JFYWNoKGZ1bmN0aW9uKGVsZSl7XHJcblx0XHRlbGUuY2xhc3NOYW1lID0gJ2FuY2hvcic7XHJcblx0fSk7XHJcbn1cclxuXHJcbi8qKlxyXG4gKiBAZGVzYyDsg4HshLjsoJXrs7TsmYAg7Jik7Iuc64qU6ri4IGhpZGUgYW5kIHNob3dcclxuICogQHBhcmFtIGxpVGFyZ2V0XHJcbiAqIEByZXR1cm5zXHJcbiAqL1xyXG5mdW5jdGlvbiBjaGFuZ2VEZXRhaWxIaWRlKGxpVGFyZ2V0KSB7XHJcblxyXG5cdGlmIChsaVRhcmdldC5jbGFzc0xpc3QuY29udGFpbnMoJ19kZXRhaWwnKSkge1xyXG5cclxuXHRcdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJy5kZXRhaWxfYXJlYV93cmFwJykuY2xhc3NMaXN0LnJlbW92ZSgnaGlkZScpO1xyXG5cdFx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignLmRldGFpbF9sb2NhdGlvbicpLmNsYXNzTGlzdC5hZGQoJ2hpZGUnKTtcclxuXHR9IGVsc2UgaWYgKGxpVGFyZ2V0LmNsYXNzTGlzdC5jb250YWlucygnX3BhdGgnKSkge1xyXG5cclxuXHRcdGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJy5kZXRhaWxfYXJlYV93cmFwJykuY2xhc3NMaXN0LmFkZCgnaGlkZScpO1xyXG5cdFx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignLmRldGFpbF9sb2NhdGlvbicpLmNsYXNzTGlzdC5yZW1vdmUoJ2hpZGUnKTtcclxuXHR9XHJcbn0iXSwic291cmNlUm9vdCI6IiJ9