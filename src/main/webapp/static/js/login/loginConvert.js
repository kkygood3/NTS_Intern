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
/******/ 	__webpack_require__.p = "/static/js/login/";
/******/
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = "./login.js");
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

/***/ }),

/***/ "../util/regexUtil.js":
/*!****************************!*\
  !*** ../util/regexUtil.js ***!
  \****************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * @desc 정규 표현식
 */
module.exports = {
  EMAIL_REGEX: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/g,
  PHONE_NUMBER_REGEX: /^\d{3}-\d{3,4}-\d{4}$/g,
  ADD_COMMA_REGEX: /\B(?=(\d{3})+(?!\d))/g,
  NAME_REGEX: /^[가-힣]{2,4}|[a-zA-Z]{2,10}\s[a-zA-Z]{2,10}$/g
};

/***/ }),

/***/ "../util/validate.js":
/*!***************************!*\
  !*** ../util/validate.js ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var regexUtil = __webpack_require__(/*! ./regexUtil */ "../util/regexUtil.js");

module.exports = {
  /**
   * @desc 이메일 유효성 검사
   * @param {String} email 
   */
  validateEmail: function validateEmail(email) {
    return this._invalidateStringEmpty(email.trim()) && email.match(regexUtil.EMAIL_REGEX);
  },

  /**
   * @desc 핸드폰 번호 (-) 포함 유효성 검사
   * @param {String} phoneNumber 
   */
  validatePhoneNumber: function validatePhoneNumber(phoneNumber) {
    return this._invalidateStringEmpty(phoneNumber.trim()) && phoneNumber.match(regexUtil.PHONE_NUMBER_REGEX);
  },

  /**
   * @desc 이름 유효성 검사
   * @param {String} name 
   */
  validateName: function validateName(name) {
    return this._invalidateStringEmpty(name.trim()) && name.match(regexUtil.NAME_REGEX);
  },

  /**
   * @desc 빈문자열인지 체크
   * @param {String} str 
   */
  _invalidateStringEmpty: function _invalidateStringEmpty(str) {
    return str !== '';
  }
};

/***/ }),

/***/ "./login.js":
/*!******************!*\
  !*** ./login.js ***!
  \******************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

/**
 * @desc 전역 변수
 */
var sendAjax = __webpack_require__(/*! ../sendAjax */ "../sendAjax.js");

var validate = __webpack_require__(/*! ../util/validate */ "../util/validate.js");
/**
 * @desc reservationEmail class
 */


function ReservationEmail() {
  this.init();
}

;
ReservationEmail.prototype = {
  init: function init() {
    this.addLoginButtonEvent();
  },
  getReservationEmail: function getReservationEmail() {
    return document.querySelector('.reservation_email').value;
  },

  /**
   * @desc 로그인 하기 버튼 이벤트
   */
  addLoginButtonEvent: function addLoginButtonEvent() {
    var _this = this;

    document.querySelector('.login_btn').addEventListener('click', function () {
      var reservationEmail = _this.getReservationEmail();

      if (validate.validateEmail(_this.getReservationEmail())) {
        _this.sendReservationEmail(reservationEmail);
      } else {
        alert('이메일 형식이 잘못되었습니다.');
      }
    });
  },

  /**
   * @desc reservationEmail 서버에 세션에 저장 
   * @param {String} reservationEmail 
   */
  sendReservationEmail: function sendReservationEmail(reservationEmail) {
    var reservationEmailSendHeader = {
      method: 'GET',
      uri: '/api/login?reservationEmail=' + reservationEmail
    };
    sendAjax(reservationEmailSendHeader, '', function () {
      location.href = '/reservations';
    }, function () {
      alert('이메일 형식이 잘못되었습니다.');
    });
  }
};
document.addEventListener('DOMContentLoaded', function () {
  var reservationEmail = new ReservationEmail();
});

/***/ })

/******/ });
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vd2VicGFjay9ib290c3RyYXAiLCJ3ZWJwYWNrOi8vLy4uL3NlbmRBamF4LmpzIiwid2VicGFjazovLy8uLi91dGlsL3JlZ2V4VXRpbC5qcyIsIndlYnBhY2s6Ly8vLi4vdXRpbC92YWxpZGF0ZS5qcyIsIndlYnBhY2s6Ly8vLi9sb2dpbi5qcyJdLCJuYW1lcyI6WyJzZW5kQWpheCIsInNlbmRIZWFkZXIiLCJzZW5kRGF0YSIsImNhbGxiYWNrIiwiZmFpbENhbGxiYWNrIiwieGhyIiwiWE1MSHR0cFJlcXVlc3QiLCJvcGVuIiwibWV0aG9kIiwidXJpIiwic2V0UmVxdWVzdEhlYWRlciIsIm9ucmVhZHlzdGF0ZWNoYW5nZSIsInJlYWR5U3RhdGUiLCJET05FIiwic3RhdHVzIiwiYXBwbHkiLCJKU09OIiwicGFyc2UiLCJyZXNwb25zZSIsInNlbmQiLCJtb2R1bGUiLCJleHBvcnRzIiwiRU1BSUxfUkVHRVgiLCJQSE9ORV9OVU1CRVJfUkVHRVgiLCJBRERfQ09NTUFfUkVHRVgiLCJOQU1FX1JFR0VYIiwicmVnZXhVdGlsIiwicmVxdWlyZSIsInZhbGlkYXRlRW1haWwiLCJlbWFpbCIsIl9pbnZhbGlkYXRlU3RyaW5nRW1wdHkiLCJ0cmltIiwibWF0Y2giLCJ2YWxpZGF0ZVBob25lTnVtYmVyIiwicGhvbmVOdW1iZXIiLCJ2YWxpZGF0ZU5hbWUiLCJuYW1lIiwic3RyIiwidmFsaWRhdGUiLCJSZXNlcnZhdGlvbkVtYWlsIiwiaW5pdCIsInByb3RvdHlwZSIsImFkZExvZ2luQnV0dG9uRXZlbnQiLCJnZXRSZXNlcnZhdGlvbkVtYWlsIiwiZG9jdW1lbnQiLCJxdWVyeVNlbGVjdG9yIiwidmFsdWUiLCJhZGRFdmVudExpc3RlbmVyIiwicmVzZXJ2YXRpb25FbWFpbCIsInNlbmRSZXNlcnZhdGlvbkVtYWlsIiwiYWxlcnQiLCJyZXNlcnZhdGlvbkVtYWlsU2VuZEhlYWRlciIsImxvY2F0aW9uIiwiaHJlZiJdLCJtYXBwaW5ncyI6IjtBQUFBO0FB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Q0E2QkpELGdCQTdCSSxFQTZCYTtBQUVyQyxRQUFNRywwQkFBMEIsR0FBRztBQUM1QjNDLFlBQU0sRUFBRyxLQURtQjtBQUU1QkMsU0FBRyxFQUFHLGlDQUErQnVDO0FBRlQsS0FBbkM7QUFLR2hELFlBQVEsQ0FBQ21ELDBCQUFELEVBQTRCLEVBQTVCLEVBQStCLFlBQUk7QUFFMUNDLGNBQVEsQ0FBQ0MsSUFBVCxHQUFjLGVBQWQ7QUFDQSxLQUhPLEVBR04sWUFBSTtBQUNGSCxXQUFLLENBQUMsa0JBQUQsQ0FBTDtBQUNILEtBTE8sQ0FBUjtBQU1IO0FBMUN3QixDQUE3QjtBQTZDQU4sUUFBUSxDQUFDRyxnQkFBVCxDQUEwQixrQkFBMUIsRUFBOEMsWUFBTTtBQUVoRCxNQUFNQyxnQkFBZ0IsR0FBRyxJQUFJVCxnQkFBSixFQUF6QjtBQUNILENBSEQsRSIsImZpbGUiOiJsb2dpbkNvbnZlcnQuanMiLCJzb3VyY2VzQ29udGVudCI6WyIgXHQvLyBUaGUgbW9kdWxlIGNhY2hlXG4gXHR2YXIgaW5zdGFsbGVkTW9kdWxlcyA9IHt9O1xuXG4gXHQvLyBUaGUgcmVxdWlyZSBmdW5jdGlvblxuIFx0ZnVuY3Rpb24gX193ZWJwYWNrX3JlcXVpcmVfXyhtb2R1bGVJZCkge1xuXG4gXHRcdC8vIENoZWNrIGlmIG1vZHVsZSBpcyBpbiBjYWNoZVxuIFx0XHRpZihpbnN0YWxsZWRNb2R1bGVzW21vZHVsZUlkXSkge1xuIFx0XHRcdHJldHVybiBpbnN0YWxsZWRNb2R1bGVzW21vZHVsZUlkXS5leHBvcnRzO1xuIFx0XHR9XG4gXHRcdC8vIENyZWF0ZSBhIG5ldyBtb2R1bGUgKGFuZCBwdXQgaXQgaW50byB0aGUgY2FjaGUpXG4gXHRcdHZhciBtb2R1bGUgPSBpbnN0YWxsZWRNb2R1bGVzW21vZHVsZUlkXSA9IHtcbiBcdFx0XHRpOiBtb2R1bGVJZCxcbiBcdFx0XHRsOiBmYWxzZSxcbiBcdFx0XHRleHBvcnRzOiB7fVxuIFx0XHR9O1xuXG4gXHRcdC8vIEV4ZWN1dGUgdGhlIG1vZHVsZSBmdW5jdGlvblxuIFx0XHRtb2R1bGVzW21vZHVsZUlkXS5jYWxsKG1vZHVsZS5leHBvcnRzLCBtb2R1bGUsIG1vZHVsZS5leHBvcnRzLCBfX3dlYnBhY2tfcmVxdWlyZV9fKTtcblxuIFx0XHQvLyBGbGFnIHRoZSBtb2R1bGUgYXMgbG9hZGVkXG4gXHRcdG1vZHVsZS5sID0gdHJ1ZTtcblxuIFx0XHQvLyBSZXR1cm4gdGhlIGV4cG9ydHMgb2YgdGhlIG1vZHVsZVxuIFx0XHRyZXR1cm4gbW9kdWxlLmV4cG9ydHM7XG4gXHR9XG5cblxuIFx0Ly8gZXhwb3NlIHRoZSBtb2R1bGVzIG9iamVjdCAoX193ZWJwYWNrX21vZHVsZXNfXylcbiBcdF9fd2VicGFja19yZXF1aXJlX18ubSA9IG1vZHVsZXM7XG5cbiBcdC8vIGV4cG9zZSB0aGUgbW9kdWxlIGNhY2hlXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLmMgPSBpbnN0YWxsZWRNb2R1bGVzO1xuXG4gXHQvLyBkZWZpbmUgZ2V0dGVyIGZ1bmN0aW9uIGZvciBoYXJtb255IGV4cG9ydHNcbiBcdF9fd2VicGFja19yZXF1aXJlX18uZCA9IGZ1bmN0aW9uKGV4cG9ydHMsIG5hbWUsIGdldHRlcikge1xuIFx0XHRpZighX193ZWJwYWNrX3JlcXVpcmVfXy5vKGV4cG9ydHMsIG5hbWUpKSB7XG4gXHRcdFx0T2JqZWN0LmRlZmluZVByb3BlcnR5KGV4cG9ydHMsIG5hbWUsIHsgZW51bWVyYWJsZTogdHJ1ZSwgZ2V0OiBnZXR0ZXIgfSk7XG4gXHRcdH1cbiBcdH07XG5cbiBcdC8vIGRlZmluZSBfX2VzTW9kdWxlIG9uIGV4cG9ydHNcbiBcdF9fd2VicGFja19yZXF1aXJlX18uciA9IGZ1bmN0aW9uKGV4cG9ydHMpIHtcbiBcdFx0aWYodHlwZW9mIFN5bWJvbCAhPT0gJ3VuZGVmaW5lZCcgJiYgU3ltYm9sLnRvU3RyaW5nVGFnKSB7XG4gXHRcdFx0T2JqZWN0LmRlZmluZVByb3BlcnR5KGV4cG9ydHMsIFN5bWJvbC50b1N0cmluZ1RhZywgeyB2YWx1ZTogJ01vZHVsZScgfSk7XG4gXHRcdH1cbiBcdFx0T2JqZWN0LmRlZmluZVByb3BlcnR5KGV4cG9ydHMsICdfX2VzTW9kdWxlJywgeyB2YWx1ZTogdHJ1ZSB9KTtcbiBcdH07XG5cbiBcdC8vIGNyZWF0ZSBhIGZha2UgbmFtZXNwYWNlIG9iamVjdFxuIFx0Ly8gbW9kZSAmIDE6IHZhbHVlIGlzIGEgbW9kdWxlIGlkLCByZXF1aXJlIGl0XG4gXHQvLyBtb2RlICYgMjogbWVyZ2UgYWxsIHByb3BlcnRpZXMgb2YgdmFsdWUgaW50byB0aGUgbnNcbiBcdC8vIG1vZGUgJiA0OiByZXR1cm4gdmFsdWUgd2hlbiBhbHJlYWR5IG5zIG9iamVjdFxuIFx0Ly8gbW9kZSAmIDh8MTogYmVoYXZlIGxpa2UgcmVxdWlyZVxuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy50ID0gZnVuY3Rpb24odmFsdWUsIG1vZGUpIHtcbiBcdFx0aWYobW9kZSAmIDEpIHZhbHVlID0gX193ZWJwYWNrX3JlcXVpcmVfXyh2YWx1ZSk7XG4gXHRcdGlmKG1vZGUgJiA4KSByZXR1cm4gdmFsdWU7XG4gXHRcdGlmKChtb2RlICYgNCkgJiYgdHlwZW9mIHZhbHVlID09PSAnb2JqZWN0JyAmJiB2YWx1ZSAmJiB2YWx1ZS5fX2VzTW9kdWxlKSByZXR1cm4gdmFsdWU7XG4gXHRcdHZhciBucyA9IE9iamVjdC5jcmVhdGUobnVsbCk7XG4gXHRcdF9fd2VicGFja19yZXF1aXJlX18ucihucyk7XG4gXHRcdE9iamVjdC5kZWZpbmVQcm9wZXJ0eShucywgJ2RlZmF1bHQnLCB7IGVudW1lcmFibGU6IHRydWUsIHZhbHVlOiB2YWx1ZSB9KTtcbiBcdFx0aWYobW9kZSAmIDIgJiYgdHlwZW9mIHZhbHVlICE9ICdzdHJpbmcnKSBmb3IodmFyIGtleSBpbiB2YWx1ZSkgX193ZWJwYWNrX3JlcXVpcmVfXy5kKG5zLCBrZXksIGZ1bmN0aW9uKGtleSkgeyByZXR1cm4gdmFsdWVba2V5XTsgfS5iaW5kKG51bGwsIGtleSkpO1xuIFx0XHRyZXR1cm4gbnM7XG4gXHR9O1xuXG4gXHQvLyBnZXREZWZhdWx0RXhwb3J0IGZ1bmN0aW9uIGZvciBjb21wYXRpYmlsaXR5IHdpdGggbm9uLWhhcm1vbnkgbW9kdWxlc1xuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5uID0gZnVuY3Rpb24obW9kdWxlKSB7XG4gXHRcdHZhciBnZXR0ZXIgPSBtb2R1bGUgJiYgbW9kdWxlLl9fZXNNb2R1bGUgP1xuIFx0XHRcdGZ1bmN0aW9uIGdldERlZmF1bHQoKSB7IHJldHVybiBtb2R1bGVbJ2RlZmF1bHQnXTsgfSA6XG4gXHRcdFx0ZnVuY3Rpb24gZ2V0TW9kdWxlRXhwb3J0cygpIHsgcmV0dXJuIG1vZHVsZTsgfTtcbiBcdFx0X193ZWJwYWNrX3JlcXVpcmVfXy5kKGdldHRlciwgJ2EnLCBnZXR0ZXIpO1xuIFx0XHRyZXR1cm4gZ2V0dGVyO1xuIFx0fTtcblxuIFx0Ly8gT2JqZWN0LnByb3RvdHlwZS5oYXNPd25Qcm9wZXJ0eS5jYWxsXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLm8gPSBmdW5jdGlvbihvYmplY3QsIHByb3BlcnR5KSB7IHJldHVybiBPYmplY3QucHJvdG90eXBlLmhhc093blByb3BlcnR5LmNhbGwob2JqZWN0LCBwcm9wZXJ0eSk7IH07XG5cbiBcdC8vIF9fd2VicGFja19wdWJsaWNfcGF0aF9fXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLnAgPSBcIi9zdGF0aWMvanMvbG9naW4vXCI7XG5cblxuIFx0Ly8gTG9hZCBlbnRyeSBtb2R1bGUgYW5kIHJldHVybiBleHBvcnRzXG4gXHRyZXR1cm4gX193ZWJwYWNrX3JlcXVpcmVfXyhfX3dlYnBhY2tfcmVxdWlyZV9fLnMgPSBcIi4vbG9naW4uanNcIik7XG4iLCIvKipcclxuICogQGRlc2Mgc2VuZEFqYXgg67mE64+Z6riwIOyalOyyrSAoIOqzte2GtSDtlajsiJggKVxyXG4gKiBAcGFyYW0gc2VuZEhlYWRlciAoIG1ldGhvZCwgdXJpKVxyXG4gKiBAcGFyYW0gc2VuZERhdGEgKCDrs7Trgrwg642w7J207YSwIClcclxuICogQHBhcmFtIGNhbGxiYWNrICgg642w7J207YSw66W8IOqwgOyngOqzoCDsmKjrkqQg7IiY7ZaJ7ZWgIGNhbGxiYWNrIO2VqOyImClcclxuICogQHJldHVybnMgeGhyLnJlc3BvbnNlICggSlNPTiDtmJXtg5zroZwgcGFyc2luZyApXHJcbiAqL1xyXG5mdW5jdGlvbiBzZW5kQWpheChzZW5kSGVhZGVyLCBzZW5kRGF0YSwgY2FsbGJhY2ssIGZhaWxDYWxsYmFjaykge1xyXG5cdHZhciB4aHIgPSBuZXcgWE1MSHR0cFJlcXVlc3QoKTtcclxuXHJcblx0eGhyLm9wZW4oc2VuZEhlYWRlci5tZXRob2QsIHNlbmRIZWFkZXIudXJpLCBmYWxzZSk7XHJcblxyXG5cdGlmKHNlbmRIZWFkZXIubWV0aG9kID09PSAnR0VUJyl7XHJcblx0XHR4aHIuc2V0UmVxdWVzdEhlYWRlcihcIkNvbnRlbnQtdHlwZVwiLCBcImFwcGxpY2F0aW9uL3gtd3d3LWZvcm0tdXJsZW5jb2RlZFwiKTtcclxuXHR9IGVsc2Uge1xyXG5cdFx0eGhyLnNldFJlcXVlc3RIZWFkZXIoXCJDb250ZW50LXR5cGVcIixcImFwcGxpY2F0aW9uL2pzb25cIik7XHJcblx0fVxyXG5cdFxyXG5cclxuXHR4aHIub25yZWFkeXN0YXRlY2hhbmdlID0gZnVuY3Rpb24oKSB7XHJcblx0XHRcclxuXHRcdGlmICh4aHIucmVhZHlTdGF0ZSA9PT0geGhyLkRPTkUgJiYgeGhyLnN0YXR1cyA9PT0gMjAwKSB7XHJcblx0XHRcdGNhbGxiYWNrLmFwcGx5KHRoaXMsIFsgSlNPTi5wYXJzZSh4aHIucmVzcG9uc2UpIF0pO1xyXG5cdFx0fSBlbHNlIGlmICh4aHIuc3RhdHVzID09PSA0MDApe1xyXG5cdFx0XHRmYWlsQ2FsbGJhY2soKTtcclxuXHRcdH1cclxuXHR9XHJcblx0eGhyLnNlbmQoc2VuZERhdGEpO1xyXG59XHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IHNlbmRBamF4OyIsIi8qKlxyXG4gKiBAZGVzYyDsoJXqt5wg7ZGc7ZiE7IudXHJcbiAqL1xyXG5tb2R1bGUuZXhwb3J0cyA9IHtcclxuXHRFTUFJTF9SRUdFWCA6IC9eKChbXjw+KClcXFtcXF1cXFxcLiw7Olxcc0BcIl0rKFxcLltePD4oKVxcW1xcXVxcXFwuLDs6XFxzQFwiXSspKil8KFwiLitcIikpQCgoXFxbWzAtOV17MSwzfVxcLlswLTldezEsM31cXC5bMC05XXsxLDN9XFwuWzAtOV17MSwzfV0pfCgoW2EtekEtWlxcLTAtOV0rXFwuKStbYS16QS1aXXsyLH0pKSQvZyxcclxuXHJcbiAgICBQSE9ORV9OVU1CRVJfUkVHRVggOiAvXlxcZHszfS1cXGR7Myw0fS1cXGR7NH0kL2csXHJcbiAgICBcclxuICAgIEFERF9DT01NQV9SRUdFWCA6IC9cXEIoPz0oXFxkezN9KSsoPyFcXGQpKS9nLFxyXG5cclxuICAgIE5BTUVfUkVHRVggOiAvXlvqsIAt7Z6jXXsyLDR9fFthLXpBLVpdezIsMTB9XFxzW2EtekEtWl17MiwxMH0kL2csXHJcbn07IiwiXHJcbmNvbnN0IHJlZ2V4VXRpbCA9IHJlcXVpcmUoJy4vcmVnZXhVdGlsJyk7XHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IHsgIFxyXG4gICAgXHJcbiAgICAvKipcclxuICAgICAqIEBkZXNjIOydtOuplOydvCDsnKDtmqjshLEg6rKA7IKsXHJcbiAgICAgKiBAcGFyYW0ge1N0cmluZ30gZW1haWwgXHJcbiAgICAgKi9cclxuICAgIHZhbGlkYXRlRW1haWwoZW1haWwpe1xyXG4gICAgICAgIHJldHVybiB0aGlzLl9pbnZhbGlkYXRlU3RyaW5nRW1wdHkoZW1haWwudHJpbSgpKSAmJiBlbWFpbC5tYXRjaChyZWdleFV0aWwuRU1BSUxfUkVHRVgpO1xyXG4gICAgfSxcclxuICAgIFxyXG4gICAgLyoqXHJcbiAgICAgKiBAZGVzYyDtlbjrk5ztj7Ag67KI7Zi4ICgtKSDtj6ztlagg7Jyg7Zqo7ISxIOqygOyCrFxyXG4gICAgICogQHBhcmFtIHtTdHJpbmd9IHBob25lTnVtYmVyIFxyXG4gICAgICovXHJcbiAgICB2YWxpZGF0ZVBob25lTnVtYmVyKHBob25lTnVtYmVyKXtcclxuICAgICAgICByZXR1cm4gdGhpcy5faW52YWxpZGF0ZVN0cmluZ0VtcHR5KHBob25lTnVtYmVyLnRyaW0oKSkgJiYgcGhvbmVOdW1iZXIubWF0Y2gocmVnZXhVdGlsLlBIT05FX05VTUJFUl9SRUdFWCk7XHJcbiAgICB9LFxyXG5cclxuICAgIC8qKlxyXG4gICAgICogQGRlc2Mg7J2066aEIOycoO2aqOyEsSDqsoDsgqxcclxuICAgICAqIEBwYXJhbSB7U3RyaW5nfSBuYW1lIFxyXG4gICAgICovXHJcbiAgICB2YWxpZGF0ZU5hbWUobmFtZSl7XHJcbiAgICAgICAgcmV0dXJuIHRoaXMuX2ludmFsaWRhdGVTdHJpbmdFbXB0eShuYW1lLnRyaW0oKSkgJiYgbmFtZS5tYXRjaChyZWdleFV0aWwuTkFNRV9SRUdFWCk7XHJcbiAgICB9LFxyXG5cclxuICAgIFxyXG4gICAgLyoqXHJcbiAgICAgKiBAZGVzYyDruYjrrLjsnpDsl7Tsnbjsp4Ag7LK07YGsXHJcbiAgICAgKiBAcGFyYW0ge1N0cmluZ30gc3RyIFxyXG4gICAgICovXHJcbiAgICBfaW52YWxpZGF0ZVN0cmluZ0VtcHR5KHN0cil7XHJcbiAgICAgICAgcmV0dXJuIHN0ciAhPT0gJyc7XHJcbiAgICB9XHJcbn07IiwiLyoqXHJcbiAqIEBkZXNjIOyghOyXrSDrs4DsiJhcclxuICovXHJcbmNvbnN0IHNlbmRBamF4ID0gcmVxdWlyZSgnLi4vc2VuZEFqYXgnKTtcclxuY29uc3QgdmFsaWRhdGUgPSByZXF1aXJlKCcuLi91dGlsL3ZhbGlkYXRlJyk7XHJcblxyXG4vKipcclxuICogQGRlc2MgcmVzZXJ2YXRpb25FbWFpbCBjbGFzc1xyXG4gKi9cclxuZnVuY3Rpb24gUmVzZXJ2YXRpb25FbWFpbCgpe1xyXG5cdHRoaXMuaW5pdCgpO1xyXG59O1xyXG5cclxuUmVzZXJ2YXRpb25FbWFpbC5wcm90b3R5cGUgPSB7XHJcblx0XHRcclxuICAgIGluaXQoKXtcclxuICAgICAgICB0aGlzLmFkZExvZ2luQnV0dG9uRXZlbnQoKTtcclxuICAgIH0sXHJcblxyXG4gICAgZ2V0UmVzZXJ2YXRpb25FbWFpbCgpe1xyXG4gICAgICAgIHJldHVybiBkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcucmVzZXJ2YXRpb25fZW1haWwnKS52YWx1ZTtcclxuICAgIH0sXHJcblxyXG4gICAgLyoqXHJcbiAgICAgKiBAZGVzYyDroZzqt7jsnbgg7ZWY6riwIOuyhO2KvCDsnbTrsqTtirhcclxuICAgICAqL1xyXG4gICAgYWRkTG9naW5CdXR0b25FdmVudCgpe1xyXG4gICAgICAgIGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJy5sb2dpbl9idG4nKS5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsKCk9PntcclxuICAgICAgICAgICAgbGV0IHJlc2VydmF0aW9uRW1haWwgPSB0aGlzLmdldFJlc2VydmF0aW9uRW1haWwoKTtcclxuICAgICAgICAgICAgXHJcbiAgICAgICAgICAgIGlmKCB2YWxpZGF0ZS52YWxpZGF0ZUVtYWlsKHRoaXMuZ2V0UmVzZXJ2YXRpb25FbWFpbCgpKSl7XHJcbiAgICAgICAgICAgICAgICB0aGlzLnNlbmRSZXNlcnZhdGlvbkVtYWlsKHJlc2VydmF0aW9uRW1haWwpOyAgIFxyXG4gICAgICAgICAgICB9IGVsc2Uge1xyXG4gICAgICAgICAgICAgICAgYWxlcnQoJ+ydtOuplOydvCDtmJXsi53snbQg7J6Y66q765CY7JeI7Iq164uI64ukLicpO1xyXG4gICAgICAgICAgICB9XHJcbiAgICAgICAgfSk7IFxyXG4gICAgfSxcclxuXHJcbiAgICAvKipcclxuICAgICAqIEBkZXNjIHJlc2VydmF0aW9uRW1haWwg7ISc67KE7JeQIOyEuOyFmOyXkCDsoIDsnqUgXHJcbiAgICAgKiBAcGFyYW0ge1N0cmluZ30gcmVzZXJ2YXRpb25FbWFpbCBcclxuICAgICAqL1xyXG4gICAgc2VuZFJlc2VydmF0aW9uRW1haWwocmVzZXJ2YXRpb25FbWFpbCl7XHJcbiAgICBcdFxyXG4gICAgXHRjb25zdCByZXNlcnZhdGlvbkVtYWlsU2VuZEhlYWRlciA9IHtcclxuICAgICAgICAgICAgbWV0aG9kIDogJ0dFVCcsXHJcbiAgICAgICAgICAgIHVyaSA6ICcvYXBpL2xvZ2luP3Jlc2VydmF0aW9uRW1haWw9JytyZXNlcnZhdGlvbkVtYWlsXHJcbiAgICAgICAgfTtcclxuICAgIFx0XHJcbiAgICAgICAgc2VuZEFqYXgocmVzZXJ2YXRpb25FbWFpbFNlbmRIZWFkZXIsJycsKCk9PntcclxuICAgICAgICBcclxuICAgICAgICBcdGxvY2F0aW9uLmhyZWY9Jy9yZXNlcnZhdGlvbnMnOyAgIFxyXG4gICAgICAgIH0sKCk9PntcclxuICAgICAgICAgICAgYWxlcnQoJ+ydtOuplOydvCDtmJXsi53snbQg7J6Y66q765CY7JeI7Iq164uI64ukLicpO1xyXG4gICAgICAgIH0pO1xyXG4gICAgfVxyXG59XHJcblxyXG5kb2N1bWVudC5hZGRFdmVudExpc3RlbmVyKCdET01Db250ZW50TG9hZGVkJywgKCkgPT4ge1xyXG5cclxuICAgIGNvbnN0IHJlc2VydmF0aW9uRW1haWwgPSBuZXcgUmVzZXJ2YXRpb25FbWFpbCgpO1xyXG59KTtcclxuXHJcbiJdLCJzb3VyY2VSb290IjoiIn0=