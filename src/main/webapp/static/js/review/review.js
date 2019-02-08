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
/******/ 	__webpack_require__.p = "/static/js/review";
/******/
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = "./comment.js");
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

/***/ "./comment.js":
/*!********************!*\
  !*** ./comment.js ***!
  \********************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

/**
 * @desc 전역변수
 */
var sendAjax = __webpack_require__(/*! ../sendAjax */ "../sendAjax.js");

/**
 * @function setHandlebarRegistHelper()  : handlebar regist Helper 설정
 * @function getHandlebarTemplateFromHtml(templateId,content) : handlebar로 compile해서 html 얻어오기
 */
var handlebarRegistHelper = __webpack_require__(/*! ../handlebarRegistHelper */ "../handlebarRegistHelper.js");

document.addEventListener('DOMContentLoaded', function(){
	getComments();
});

/**
 * @desc comment Setting 
 */
var setComments = {

	/**
	 * @desc comment총 갯수 설정
	 * @param commentLength
	 */
	setProductCount : function(commentLength) {
		document.querySelector('#comment_count').innerText = commentLength;
	},

	/**
	 * @desc comment 평균 점수 셋팅
	 * @param averageScore
	 */
	setCommentAverageScore : function(averageScore) {

		var start = 0;
		var end = 3;

		document.querySelector('#average_score').innerText = String(averageScore).substring(start, end);
	},

	/**
	 * @desc comment 평균 점수 별점 셋팅
	 * @param averageScore
	 */
	setAverageScoreStar : function(averageScore) {

		document.querySelector('#comment_average_star').style.width = averageScore* 20 + '%';
	},
	
	/**
	 * @desc comments 내용 전체 셋팅
	 * @param comments : {comments : comments} 객체
	 */
	setCommentList : function(comments){
		document.querySelector('.list_short_review').innerHTML = handlebarRegistHelper.getHandlebarTemplateFromHtml('#comment_template',comments);
	}
	
};

/**
 * @desc comment 가져오기 가져온 데이터로 셋팅
 */
function getComments(){
	
	var commentSendHeader = {
		method : 'GET',
		uri : '/api/comments/'+getProductId()
	};
	
	sendAjax(commentSendHeader,'',function(commentResponse){
		
		setComments.setProductCount(commentResponse.comments.length);
		setComments.setCommentAverageScore(commentResponse.averageScore);
		setComments.setAverageScoreStar(commentResponse.averageScore);
		
		handlebarRegistHelper.setHandlebarRegistHelper();
		setComments.setCommentList({comments:commentResponse.comments});
	});
}

/**
 * @desc productId 가져오기
 */
function getProductId(){
	return document.querySelector('.section_review_list').dataset.productid;
}


/***/ })

/******/ });
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vd2VicGFjay9ib290c3RyYXAiLCJ3ZWJwYWNrOi8vLy4uL2hhbmRsZWJhclJlZ2lzdEhlbHBlci5qcyIsIndlYnBhY2s6Ly8vLi4vc2VuZEFqYXguanMiLCJ3ZWJwYWNrOi8vLy4vY29tbWVudC5qcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiO0FBQUE7QUFDQTs7QUFFQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7O0FBRUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7OztBQUdBO0FBQ0E7O0FBRUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQSxrREFBMEMsZ0NBQWdDO0FBQzFFO0FBQ0E7O0FBRUE7QUFDQTtBQUNBO0FBQ0EsZ0VBQXdELGtCQUFrQjtBQUMxRTtBQUNBLHlEQUFpRCxjQUFjO0FBQy9EOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQSxpREFBeUMsaUNBQWlDO0FBQzFFLHdIQUFnSCxtQkFBbUIsRUFBRTtBQUNySTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBLG1DQUEyQiwwQkFBMEIsRUFBRTtBQUN2RCx5Q0FBaUMsZUFBZTtBQUNoRDtBQUNBO0FBQ0E7O0FBRUE7QUFDQSw4REFBc0QsK0RBQStEOztBQUVySDtBQUNBOzs7QUFHQTtBQUNBOzs7Ozs7Ozs7Ozs7QUNsRkE7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTs7QUFFQTtBQUNBOztBQUVBO0FBQ0E7QUFDQSxHQUFHOztBQUVIOztBQUVBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBLEdBQUc7QUFDSCxFQUFFOztBQUVGO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7O0FBRUEsdUM7Ozs7Ozs7Ozs7O0FDekNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTs7QUFFQTtBQUNBOztBQUVBOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7O0FBRUEsMEI7Ozs7Ozs7Ozs7O0FDdkJBO0FBQ0E7QUFDQTtBQUNBLGVBQWUsbUJBQU8sQ0FBQyxtQ0FBYTs7QUFFcEM7QUFDQTtBQUNBO0FBQ0E7QUFDQSw0QkFBNEIsbUJBQU8sQ0FBQyw2REFBMEI7O0FBRTlEO0FBQ0E7QUFDQSxDQUFDOztBQUVEO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBLEVBQUU7O0FBRUY7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBOztBQUVBO0FBQ0EsRUFBRTs7QUFFRjtBQUNBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0EsRUFBRTs7QUFFRjtBQUNBO0FBQ0EsdUJBQXVCLG9CQUFvQjtBQUMzQztBQUNBO0FBQ0E7QUFDQTs7QUFFQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTs7QUFFQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQSw4QkFBOEIsa0NBQWtDO0FBQ2hFLEVBQUU7QUFDRjs7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0EiLCJmaWxlIjoicmV2aWV3LmpzIiwic291cmNlc0NvbnRlbnQiOlsiIFx0Ly8gVGhlIG1vZHVsZSBjYWNoZVxuIFx0dmFyIGluc3RhbGxlZE1vZHVsZXMgPSB7fTtcblxuIFx0Ly8gVGhlIHJlcXVpcmUgZnVuY3Rpb25cbiBcdGZ1bmN0aW9uIF9fd2VicGFja19yZXF1aXJlX18obW9kdWxlSWQpIHtcblxuIFx0XHQvLyBDaGVjayBpZiBtb2R1bGUgaXMgaW4gY2FjaGVcbiBcdFx0aWYoaW5zdGFsbGVkTW9kdWxlc1ttb2R1bGVJZF0pIHtcbiBcdFx0XHRyZXR1cm4gaW5zdGFsbGVkTW9kdWxlc1ttb2R1bGVJZF0uZXhwb3J0cztcbiBcdFx0fVxuIFx0XHQvLyBDcmVhdGUgYSBuZXcgbW9kdWxlIChhbmQgcHV0IGl0IGludG8gdGhlIGNhY2hlKVxuIFx0XHR2YXIgbW9kdWxlID0gaW5zdGFsbGVkTW9kdWxlc1ttb2R1bGVJZF0gPSB7XG4gXHRcdFx0aTogbW9kdWxlSWQsXG4gXHRcdFx0bDogZmFsc2UsXG4gXHRcdFx0ZXhwb3J0czoge31cbiBcdFx0fTtcblxuIFx0XHQvLyBFeGVjdXRlIHRoZSBtb2R1bGUgZnVuY3Rpb25cbiBcdFx0bW9kdWxlc1ttb2R1bGVJZF0uY2FsbChtb2R1bGUuZXhwb3J0cywgbW9kdWxlLCBtb2R1bGUuZXhwb3J0cywgX193ZWJwYWNrX3JlcXVpcmVfXyk7XG5cbiBcdFx0Ly8gRmxhZyB0aGUgbW9kdWxlIGFzIGxvYWRlZFxuIFx0XHRtb2R1bGUubCA9IHRydWU7XG5cbiBcdFx0Ly8gUmV0dXJuIHRoZSBleHBvcnRzIG9mIHRoZSBtb2R1bGVcbiBcdFx0cmV0dXJuIG1vZHVsZS5leHBvcnRzO1xuIFx0fVxuXG5cbiBcdC8vIGV4cG9zZSB0aGUgbW9kdWxlcyBvYmplY3QgKF9fd2VicGFja19tb2R1bGVzX18pXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLm0gPSBtb2R1bGVzO1xuXG4gXHQvLyBleHBvc2UgdGhlIG1vZHVsZSBjYWNoZVxuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5jID0gaW5zdGFsbGVkTW9kdWxlcztcblxuIFx0Ly8gZGVmaW5lIGdldHRlciBmdW5jdGlvbiBmb3IgaGFybW9ueSBleHBvcnRzXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLmQgPSBmdW5jdGlvbihleHBvcnRzLCBuYW1lLCBnZXR0ZXIpIHtcbiBcdFx0aWYoIV9fd2VicGFja19yZXF1aXJlX18ubyhleHBvcnRzLCBuYW1lKSkge1xuIFx0XHRcdE9iamVjdC5kZWZpbmVQcm9wZXJ0eShleHBvcnRzLCBuYW1lLCB7IGVudW1lcmFibGU6IHRydWUsIGdldDogZ2V0dGVyIH0pO1xuIFx0XHR9XG4gXHR9O1xuXG4gXHQvLyBkZWZpbmUgX19lc01vZHVsZSBvbiBleHBvcnRzXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLnIgPSBmdW5jdGlvbihleHBvcnRzKSB7XG4gXHRcdGlmKHR5cGVvZiBTeW1ib2wgIT09ICd1bmRlZmluZWQnICYmIFN5bWJvbC50b1N0cmluZ1RhZykge1xuIFx0XHRcdE9iamVjdC5kZWZpbmVQcm9wZXJ0eShleHBvcnRzLCBTeW1ib2wudG9TdHJpbmdUYWcsIHsgdmFsdWU6ICdNb2R1bGUnIH0pO1xuIFx0XHR9XG4gXHRcdE9iamVjdC5kZWZpbmVQcm9wZXJ0eShleHBvcnRzLCAnX19lc01vZHVsZScsIHsgdmFsdWU6IHRydWUgfSk7XG4gXHR9O1xuXG4gXHQvLyBjcmVhdGUgYSBmYWtlIG5hbWVzcGFjZSBvYmplY3RcbiBcdC8vIG1vZGUgJiAxOiB2YWx1ZSBpcyBhIG1vZHVsZSBpZCwgcmVxdWlyZSBpdFxuIFx0Ly8gbW9kZSAmIDI6IG1lcmdlIGFsbCBwcm9wZXJ0aWVzIG9mIHZhbHVlIGludG8gdGhlIG5zXG4gXHQvLyBtb2RlICYgNDogcmV0dXJuIHZhbHVlIHdoZW4gYWxyZWFkeSBucyBvYmplY3RcbiBcdC8vIG1vZGUgJiA4fDE6IGJlaGF2ZSBsaWtlIHJlcXVpcmVcbiBcdF9fd2VicGFja19yZXF1aXJlX18udCA9IGZ1bmN0aW9uKHZhbHVlLCBtb2RlKSB7XG4gXHRcdGlmKG1vZGUgJiAxKSB2YWx1ZSA9IF9fd2VicGFja19yZXF1aXJlX18odmFsdWUpO1xuIFx0XHRpZihtb2RlICYgOCkgcmV0dXJuIHZhbHVlO1xuIFx0XHRpZigobW9kZSAmIDQpICYmIHR5cGVvZiB2YWx1ZSA9PT0gJ29iamVjdCcgJiYgdmFsdWUgJiYgdmFsdWUuX19lc01vZHVsZSkgcmV0dXJuIHZhbHVlO1xuIFx0XHR2YXIgbnMgPSBPYmplY3QuY3JlYXRlKG51bGwpO1xuIFx0XHRfX3dlYnBhY2tfcmVxdWlyZV9fLnIobnMpO1xuIFx0XHRPYmplY3QuZGVmaW5lUHJvcGVydHkobnMsICdkZWZhdWx0JywgeyBlbnVtZXJhYmxlOiB0cnVlLCB2YWx1ZTogdmFsdWUgfSk7XG4gXHRcdGlmKG1vZGUgJiAyICYmIHR5cGVvZiB2YWx1ZSAhPSAnc3RyaW5nJykgZm9yKHZhciBrZXkgaW4gdmFsdWUpIF9fd2VicGFja19yZXF1aXJlX18uZChucywga2V5LCBmdW5jdGlvbihrZXkpIHsgcmV0dXJuIHZhbHVlW2tleV07IH0uYmluZChudWxsLCBrZXkpKTtcbiBcdFx0cmV0dXJuIG5zO1xuIFx0fTtcblxuIFx0Ly8gZ2V0RGVmYXVsdEV4cG9ydCBmdW5jdGlvbiBmb3IgY29tcGF0aWJpbGl0eSB3aXRoIG5vbi1oYXJtb255IG1vZHVsZXNcbiBcdF9fd2VicGFja19yZXF1aXJlX18ubiA9IGZ1bmN0aW9uKG1vZHVsZSkge1xuIFx0XHR2YXIgZ2V0dGVyID0gbW9kdWxlICYmIG1vZHVsZS5fX2VzTW9kdWxlID9cbiBcdFx0XHRmdW5jdGlvbiBnZXREZWZhdWx0KCkgeyByZXR1cm4gbW9kdWxlWydkZWZhdWx0J107IH0gOlxuIFx0XHRcdGZ1bmN0aW9uIGdldE1vZHVsZUV4cG9ydHMoKSB7IHJldHVybiBtb2R1bGU7IH07XG4gXHRcdF9fd2VicGFja19yZXF1aXJlX18uZChnZXR0ZXIsICdhJywgZ2V0dGVyKTtcbiBcdFx0cmV0dXJuIGdldHRlcjtcbiBcdH07XG5cbiBcdC8vIE9iamVjdC5wcm90b3R5cGUuaGFzT3duUHJvcGVydHkuY2FsbFxuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5vID0gZnVuY3Rpb24ob2JqZWN0LCBwcm9wZXJ0eSkgeyByZXR1cm4gT2JqZWN0LnByb3RvdHlwZS5oYXNPd25Qcm9wZXJ0eS5jYWxsKG9iamVjdCwgcHJvcGVydHkpOyB9O1xuXG4gXHQvLyBfX3dlYnBhY2tfcHVibGljX3BhdGhfX1xuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5wID0gXCIvc3RhdGljL2pzL3Jldmlld1wiO1xuXG5cbiBcdC8vIExvYWQgZW50cnkgbW9kdWxlIGFuZCByZXR1cm4gZXhwb3J0c1xuIFx0cmV0dXJuIF9fd2VicGFja19yZXF1aXJlX18oX193ZWJwYWNrX3JlcXVpcmVfXy5zID0gXCIuL2NvbW1lbnQuanNcIik7XG4iLCJ2YXIgaGFuZGxlYmFyUmVnaXN0SGVscGVyID0ge1xyXG5cdFxyXG5cdC8qKlxyXG5cdCAqIEBkZXNjIGhhbmRsZWJhciBoZWxwZXIgcmVnaXN0IOyekeyXhSBcclxuXHQgKiBAcmV0dXJuc1xyXG5cdCAqL1x0XHJcblx0c2V0SGFuZGxlYmFyUmVnaXN0SGVscGVyIDogZnVuY3Rpb24oKSB7XHJcblx0XHRcclxuXHRcdEhhbmRsZWJhcnMucmVnaXN0ZXJIZWxwZXIoJ3VzZXJFbWFpbEVuY3J5cHQnLCBmdW5jdGlvbihyZXNlcnZhdGlvbkVtYWlsKSB7XHJcblx0XHRcdCAgIFxyXG5cdFx0XHR2YXIgc3RhcnQgPSAwO1xyXG5cdFx0ICAgIHZhciBlbmQgPSA0O1xyXG5cdFx0ICAgIFxyXG5cdFx0XHR2YXIgZW5jcnlwdGVkVXNlckVtYWlsID0gcmVzZXJ2YXRpb25FbWFpbC5zdWJzdHJpbmcoc3RhcnQsZW5kKSsnKioqKic7XHJcblx0XHQgICAgcmV0dXJuIG5ldyBIYW5kbGViYXJzLlNhZmVTdHJpbmcoZW5jcnlwdGVkVXNlckVtYWlsKTtcclxuXHRcdH0pO1xyXG5cdFx0XHJcblx0XHRIYW5kbGViYXJzLnJlZ2lzdGVySGVscGVyKCdyZXNlcnZhdGlvbkRhdGVZWVlZTU1ERCcsIGZ1bmN0aW9uKHJlc2VydmF0aW9uRGF0ZSkge1xyXG5cdFx0ICAgIFxyXG5cdFx0XHR2YXIgc3RhcnQgPSAwO1xyXG5cdFx0ICAgIHZhciBlbmQgPSAxMDtcclxuXHRcdFx0XHJcblx0XHQgICAgdmFyIHJlc2VydmF0aW9uRGF0ZVlZWVlNTUREID0gcmVzZXJ2YXRpb25EYXRlLnN1YnN0cmluZyhzdGFydCxlbmQpLnJlcGxhY2UoLy0vZywnLicpKycuJztcclxuXHRcdCAgICByZXR1cm4gbmV3IEhhbmRsZWJhcnMuU2FmZVN0cmluZyhyZXNlcnZhdGlvbkRhdGVZWVlZTU1ERCk7XHJcblx0XHR9KTtcclxuXHR9LFxyXG5cdFxyXG5cdC8qKlxyXG5cdCAqIEBkZXNjIGhhbmRsZWJhcuuhnCBjb21waWxl7ZW07IScIGh0bWwg7Ja77Ja07Jik6riwXHJcblx0ICogQHBhcmFtIHRlbXBsYXRlSWRcclxuXHQgKiBAcGFyYW0gY29udGVudFxyXG5cdCAqIEByZXR1cm5zIGh0bWxcclxuXHQgKi9cclxuXHRnZXRIYW5kbGViYXJUZW1wbGF0ZUZyb21IdG1sOiBmdW5jdGlvbih0ZW1wbGF0ZUlkLGNvbnRlbnQpe1xyXG5cdFx0dmFyIHRlbXBsYXRlID0gZG9jdW1lbnQucXVlcnlTZWxlY3Rvcih0ZW1wbGF0ZUlkKS5pbm5lckhUTUw7XHJcblx0XHR2YXIgaGFuZGxlYmFyVGVtcGxhdGUgPSBIYW5kbGViYXJzLmNvbXBpbGUodGVtcGxhdGUpO1xyXG5cdFx0XHJcblx0XHRyZXR1cm4gaGFuZGxlYmFyVGVtcGxhdGUoY29udGVudCk7XHJcblx0fVxyXG59XHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IGhhbmRsZWJhclJlZ2lzdEhlbHBlcjsiLCIvKipcclxuICogQGRlc2Mgc2VuZEFqYXgg67mE64+Z6riwIOyalOyyrSAoIOqzte2GtSDtlajsiJggKVxyXG4gKiBAcGFyYW0gc2VuZEhlYWRlciAoIG1ldGhvZCwgdXJpKVxyXG4gKiBAcGFyYW0gc2VuZERhdGEgKCDrs7Trgrwg642w7J207YSwIClcclxuICogQHBhcmFtIGNhbGxiYWNrICgg642w7J207YSw66W8IOqwgOyngOqzoCDsmKjrkqQg7IiY7ZaJ7ZWgIGNhbGxiYWNrIO2VqOyImClcclxuICogQHJldHVybnMgeGhyLnJlc3BvbnNlICggSlNPTiDtmJXtg5zroZwgcGFyc2luZyApXHJcbiAqL1xyXG5mdW5jdGlvbiBzZW5kQWpheChzZW5kSGVhZGVyLCBzZW5kRGF0YSwgY2FsbGJhY2spIHtcclxuXHR2YXIgeGhyID0gbmV3IFhNTEh0dHBSZXF1ZXN0KCk7XHJcblxyXG5cdHhoci5vcGVuKHNlbmRIZWFkZXIubWV0aG9kLCBzZW5kSGVhZGVyLnVyaSwgdHJ1ZSk7XHJcblxyXG5cdHhoci5zZXRSZXF1ZXN0SGVhZGVyKFwiQ29udGVudC10eXBlXCIsIFwiYXBwbGljYXRpb24veC13d3ctZm9ybS11cmxlbmNvZGVkXCIpO1xyXG5cdHhoci5zZW5kKHNlbmREYXRhKTtcclxuXHJcblx0eGhyLm9ucmVhZHlzdGF0ZWNoYW5nZSA9IGZ1bmN0aW9uKCkge1xyXG5cclxuXHRcdGlmICh4aHIucmVhZHlTdGF0ZSA9PT0geGhyLkRPTkUgJiYgeGhyLnN0YXR1cyA9PT0gMjAwKSB7XHJcblx0XHRcdGNhbGxiYWNrLmFwcGx5KHRoaXMsIFsgSlNPTi5wYXJzZSh4aHIucmVzcG9uc2UpIF0pO1xyXG5cdFx0fVxyXG5cdH1cclxufVxyXG5cclxubW9kdWxlLmV4cG9ydHMgPSBzZW5kQWpheDsiLCIvKipcclxuICogQGRlc2Mg7KCE7Jet67OA7IiYXHJcbiAqL1xyXG52YXIgc2VuZEFqYXggPSByZXF1aXJlKCcuLi9zZW5kQWpheCcpO1xyXG5cclxuLyoqXHJcbiAqIEBmdW5jdGlvbiBzZXRIYW5kbGViYXJSZWdpc3RIZWxwZXIoKSAgOiBoYW5kbGViYXIgcmVnaXN0IEhlbHBlciDshKTsoJVcclxuICogQGZ1bmN0aW9uIGdldEhhbmRsZWJhclRlbXBsYXRlRnJvbUh0bWwodGVtcGxhdGVJZCxjb250ZW50KSA6IGhhbmRsZWJhcuuhnCBjb21waWxl7ZW07IScIGh0bWwg7Ja77Ja07Jik6riwXHJcbiAqL1xyXG52YXIgaGFuZGxlYmFyUmVnaXN0SGVscGVyID0gcmVxdWlyZSgnLi4vaGFuZGxlYmFyUmVnaXN0SGVscGVyJyk7XHJcblxyXG5kb2N1bWVudC5hZGRFdmVudExpc3RlbmVyKCdET01Db250ZW50TG9hZGVkJywgZnVuY3Rpb24oKXtcclxuXHRnZXRDb21tZW50cygpO1xyXG59KTtcclxuXHJcbi8qKlxyXG4gKiBAZGVzYyBjb21tZW50IFNldHRpbmcgXHJcbiAqL1xyXG52YXIgc2V0Q29tbWVudHMgPSB7XHJcblxyXG5cdC8qKlxyXG5cdCAqIEBkZXNjIGNvbW1lbnTstJ0g6rCv7IiYIOyEpOyglVxyXG5cdCAqIEBwYXJhbSBjb21tZW50TGVuZ3RoXHJcblx0ICovXHJcblx0c2V0UHJvZHVjdENvdW50IDogZnVuY3Rpb24oY29tbWVudExlbmd0aCkge1xyXG5cdFx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignI2NvbW1lbnRfY291bnQnKS5pbm5lclRleHQgPSBjb21tZW50TGVuZ3RoO1xyXG5cdH0sXHJcblxyXG5cdC8qKlxyXG5cdCAqIEBkZXNjIGNvbW1lbnQg7Y+J6regIOygkOyImCDshYvtjIVcclxuXHQgKiBAcGFyYW0gYXZlcmFnZVNjb3JlXHJcblx0ICovXHJcblx0c2V0Q29tbWVudEF2ZXJhZ2VTY29yZSA6IGZ1bmN0aW9uKGF2ZXJhZ2VTY29yZSkge1xyXG5cclxuXHRcdHZhciBzdGFydCA9IDA7XHJcblx0XHR2YXIgZW5kID0gMztcclxuXHJcblx0XHRkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcjYXZlcmFnZV9zY29yZScpLmlubmVyVGV4dCA9IFN0cmluZyhhdmVyYWdlU2NvcmUpLnN1YnN0cmluZyhzdGFydCwgZW5kKTtcclxuXHR9LFxyXG5cclxuXHQvKipcclxuXHQgKiBAZGVzYyBjb21tZW50IO2Pieq3oCDsoJDsiJgg67OE7KCQIOyFi+2MhVxyXG5cdCAqIEBwYXJhbSBhdmVyYWdlU2NvcmVcclxuXHQgKi9cclxuXHRzZXRBdmVyYWdlU2NvcmVTdGFyIDogZnVuY3Rpb24oYXZlcmFnZVNjb3JlKSB7XHJcblxyXG5cdFx0ZG9jdW1lbnQucXVlcnlTZWxlY3RvcignI2NvbW1lbnRfYXZlcmFnZV9zdGFyJykuc3R5bGUud2lkdGggPSBhdmVyYWdlU2NvcmUqIDIwICsgJyUnO1xyXG5cdH0sXHJcblx0XHJcblx0LyoqXHJcblx0ICogQGRlc2MgY29tbWVudHMg64K07JqpIOyghOyytCDshYvtjIVcclxuXHQgKiBAcGFyYW0gY29tbWVudHMgOiB7Y29tbWVudHMgOiBjb21tZW50c30g6rCd7LK0XHJcblx0ICovXHJcblx0c2V0Q29tbWVudExpc3QgOiBmdW5jdGlvbihjb21tZW50cyl7XHJcblx0XHRkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcubGlzdF9zaG9ydF9yZXZpZXcnKS5pbm5lckhUTUwgPSBoYW5kbGViYXJSZWdpc3RIZWxwZXIuZ2V0SGFuZGxlYmFyVGVtcGxhdGVGcm9tSHRtbCgnI2NvbW1lbnRfdGVtcGxhdGUnLGNvbW1lbnRzKTtcclxuXHR9XHJcblx0XHJcbn07XHJcblxyXG4vKipcclxuICogQGRlc2MgY29tbWVudCDqsIDsoLjsmKTquLAg6rCA7KC47JioIOuNsOydtO2EsOuhnCDshYvtjIVcclxuICovXHJcbmZ1bmN0aW9uIGdldENvbW1lbnRzKCl7XHJcblx0XHJcblx0dmFyIGNvbW1lbnRTZW5kSGVhZGVyID0ge1xyXG5cdFx0bWV0aG9kIDogJ0dFVCcsXHJcblx0XHR1cmkgOiAnL2FwaS9jb21tZW50cy8nK2dldFByb2R1Y3RJZCgpXHJcblx0fTtcclxuXHRcclxuXHRzZW5kQWpheChjb21tZW50U2VuZEhlYWRlciwnJyxmdW5jdGlvbihjb21tZW50UmVzcG9uc2Upe1xyXG5cdFx0XHJcblx0XHRzZXRDb21tZW50cy5zZXRQcm9kdWN0Q291bnQoY29tbWVudFJlc3BvbnNlLmNvbW1lbnRzLmxlbmd0aCk7XHJcblx0XHRzZXRDb21tZW50cy5zZXRDb21tZW50QXZlcmFnZVNjb3JlKGNvbW1lbnRSZXNwb25zZS5hdmVyYWdlU2NvcmUpO1xyXG5cdFx0c2V0Q29tbWVudHMuc2V0QXZlcmFnZVNjb3JlU3Rhcihjb21tZW50UmVzcG9uc2UuYXZlcmFnZVNjb3JlKTtcclxuXHRcdFxyXG5cdFx0aGFuZGxlYmFyUmVnaXN0SGVscGVyLnNldEhhbmRsZWJhclJlZ2lzdEhlbHBlcigpO1xyXG5cdFx0c2V0Q29tbWVudHMuc2V0Q29tbWVudExpc3Qoe2NvbW1lbnRzOmNvbW1lbnRSZXNwb25zZS5jb21tZW50c30pO1xyXG5cdH0pO1xyXG59XHJcblxyXG4vKipcclxuICogQGRlc2MgcHJvZHVjdElkIOqwgOyguOyYpOq4sFxyXG4gKi9cclxuZnVuY3Rpb24gZ2V0UHJvZHVjdElkKCl7XHJcblx0cmV0dXJuIGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJy5zZWN0aW9uX3Jldmlld19saXN0JykuZGF0YXNldC5wcm9kdWN0aWQ7XHJcbn1cclxuIl0sInNvdXJjZVJvb3QiOiIifQ==