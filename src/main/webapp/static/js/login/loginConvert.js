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

	if(sendHeader.method === 'GET'){
		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	} else {
		xhr.setRequestHeader("Content-type","application/json");
	}
	

	xhr.onreadystatechange = function() {
		
		if (xhr.readyState === xhr.DONE && xhr.status === 200) {
			callback.apply(this, [ JSON.parse(xhr.response) ]);
		} else if (xhr.status === 400){
			failCallback();
		}
	}
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
 * 
 */
module.exports = {
	EMAIL_REGEX : /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/g,

    PHONE_NUMBER_REGEX : /^\d{3}-\d{3,4}-\d{4}$/g,
    
    ADD_COMMA_REGEX : /\B(?=(\d{3})+(?!\d))/g,

    NAME_REGEX : /^[가-힣]{2,4}|[a-zA-Z]{2,10}\s[a-zA-Z]{2,10}$/g,
};

/***/ }),

/***/ "../util/validate.js":
/*!***************************!*\
  !*** ../util/validate.js ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {


const regexUtil = __webpack_require__(/*! ./regexUtil */ "../util/regexUtil.js");

module.exports = {  
    
    /**
     * @desc 이메일 유효성 검사
     * @param {String} email 
     */
    validateEmail(email){
        return this._invalidateStringEmpty(email.trim()) && email.match(regexUtil.EMAIL_REGEX);
    },
    
    /**
     * @desc 핸드폰 번호 (-) 포함 유효성 검사
     * @param {String} phoneNumber 
     */
    validatePhoneNumber(phoneNumber){
        return this._invalidateStringEmpty(phoneNumber.trim()) && phoneNumber.match(regexUtil.PHONE_NUMBER_REGEX);
    },

    /**
     * @desc 이름 유효성 검사
     * @param {String} name 
     */
    validateName(name){
        return this._invalidateStringEmpty(name.trim()) && name.match(regexUtil.NAME_REGEX);
    },

    
    /**
     * @desc 빈문자열인지 체크
     * @param {String} str 
     */
    _invalidateStringEmpty(str){
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
const sendAjax = __webpack_require__(/*! ../sendAjax */ "../sendAjax.js");
const validate = __webpack_require__(/*! ../util/validate */ "../util/validate.js");

function ReservationEmail(){
	this.init();
};

ReservationEmail.prototype = {
		
    init(){
        this.addLoginButtonEvent();
    },

    getReservationEmail(){
        return document.querySelector('.reservation_email').value;
    },

    addLoginButtonEvent(){
        document.querySelector('.login_btn').addEventListener('click',()=>{
            let reservationEmail = this.getReservationEmail();
            
            if( validate.validateEmail(this.getReservationEmail())){
                this.sendReservationEmail(reservationEmail);   
            } else {
                alert('이메일 형식이 잘못되었습니다.');
            }
        }); 
    },

    sendReservationEmail(reservationEmail){
    	
    	const reservationEmailSendHeader = {
            method : 'GET',
            uri : '/api/login?reservationEmail='+reservationEmail
        };
    	
        sendAjax(reservationEmailSendHeader,'',()=>{
        
        	location.href='/reservations';   
        },()=>{
            alert('이메일 형식이 잘못되었습니다.');
        });
    }
}

document.addEventListener('DOMContentLoaded', () => {

    const reservationEmail = new ReservationEmail();
});



/***/ })

/******/ });
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vd2VicGFjay9ib290c3RyYXAiLCJ3ZWJwYWNrOi8vLy4uL3NlbmRBamF4LmpzIiwid2VicGFjazovLy8uLi91dGlsL3JlZ2V4VXRpbC5qcyIsIndlYnBhY2s6Ly8vLi4vdXRpbC92YWxpZGF0ZS5qcyIsIndlYnBhY2s6Ly8vLi9sb2dpbi5qcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiO0FBQUE7QUFDQTs7QUFFQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7O0FBRUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7OztBQUdBO0FBQ0E7O0FBRUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQSxrREFBMEMsZ0NBQWdDO0FBQzFFO0FBQ0E7O0FBRUE7QUFDQTtBQUNBO0FBQ0EsZ0VBQXdELGtCQUFrQjtBQUMxRTtBQUNBLHlEQUFpRCxjQUFjO0FBQy9EOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQSxpREFBeUMsaUNBQWlDO0FBQzFFLHdIQUFnSCxtQkFBbUIsRUFBRTtBQUNySTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBLG1DQUEyQiwwQkFBMEIsRUFBRTtBQUN2RCx5Q0FBaUMsZUFBZTtBQUNoRDtBQUNBO0FBQ0E7O0FBRUE7QUFDQSw4REFBc0QsK0RBQStEOztBQUVySDtBQUNBOzs7QUFHQTtBQUNBOzs7Ozs7Ozs7Ozs7QUNsRkE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOztBQUVBOztBQUVBO0FBQ0E7QUFDQSxFQUFFO0FBQ0Y7QUFDQTs7O0FBR0E7O0FBRUE7QUFDQTtBQUNBLEdBQUc7QUFDSDtBQUNBO0FBQ0E7QUFDQTtBQUNBOztBQUVBLDBCOzs7Ozs7Ozs7OztBQzlCQTtBQUNBO0FBQ0E7QUFDQTtBQUNBLGtDQUFrQyx5QkFBeUIsNkJBQTZCLElBQUksUUFBUSxJQUFJLFFBQVEsSUFBSSxRQUFRLElBQUksZ0NBQWdDLEdBQUc7O0FBRW5LLDhCQUE4QixFQUFFLElBQUksSUFBSSxJQUFJLEVBQUU7O0FBRTlDLGdDQUFnQyxFQUFFOztBQUVsQyx5QkFBeUIsSUFBSSxVQUFVLEtBQUssV0FBVyxLQUFLO0FBQzVELEU7Ozs7Ozs7Ozs7OztBQ1ZBLGtCQUFrQixtQkFBTyxDQUFDLHlDQUFhOztBQUV2QyxrQjs7QUFFQTtBQUNBO0FBQ0EsZUFBZSxPQUFPO0FBQ3RCO0FBQ0E7QUFDQTtBQUNBLEtBQUs7O0FBRUw7QUFDQTtBQUNBLGVBQWUsT0FBTztBQUN0QjtBQUNBO0FBQ0E7QUFDQSxLQUFLOztBQUVMO0FBQ0E7QUFDQSxlQUFlLE9BQU87QUFDdEI7QUFDQTtBQUNBO0FBQ0EsS0FBSzs7O0FBR0w7QUFDQTtBQUNBLGVBQWUsT0FBTztBQUN0QjtBQUNBO0FBQ0E7QUFDQTtBQUNBLEU7Ozs7Ozs7Ozs7O0FDckNBO0FBQ0E7QUFDQTtBQUNBLGlCQUFpQixtQkFBTyxDQUFDLG1DQUFhO0FBQ3RDLGlCQUFpQixtQkFBTyxDQUFDLDZDQUFrQjs7QUFFM0M7QUFDQTtBQUNBOztBQUVBOztBQUVBO0FBQ0E7QUFDQSxLQUFLOztBQUVMO0FBQ0E7QUFDQSxLQUFLOztBQUVMO0FBQ0E7QUFDQTs7QUFFQTtBQUNBLDREO0FBQ0EsYUFBYTtBQUNiO0FBQ0E7QUFDQSxTQUFTLEU7QUFDVCxLQUFLOztBQUVMOztBQUVBO0FBQ0E7QUFDQTtBQUNBOztBQUVBOztBQUVBLHVDO0FBQ0EsU0FBUztBQUNUO0FBQ0EsU0FBUztBQUNUO0FBQ0E7O0FBRUE7O0FBRUE7QUFDQSxDQUFDIiwiZmlsZSI6ImxvZ2luQ29udmVydC5qcyIsInNvdXJjZXNDb250ZW50IjpbIiBcdC8vIFRoZSBtb2R1bGUgY2FjaGVcbiBcdHZhciBpbnN0YWxsZWRNb2R1bGVzID0ge307XG5cbiBcdC8vIFRoZSByZXF1aXJlIGZ1bmN0aW9uXG4gXHRmdW5jdGlvbiBfX3dlYnBhY2tfcmVxdWlyZV9fKG1vZHVsZUlkKSB7XG5cbiBcdFx0Ly8gQ2hlY2sgaWYgbW9kdWxlIGlzIGluIGNhY2hlXG4gXHRcdGlmKGluc3RhbGxlZE1vZHVsZXNbbW9kdWxlSWRdKSB7XG4gXHRcdFx0cmV0dXJuIGluc3RhbGxlZE1vZHVsZXNbbW9kdWxlSWRdLmV4cG9ydHM7XG4gXHRcdH1cbiBcdFx0Ly8gQ3JlYXRlIGEgbmV3IG1vZHVsZSAoYW5kIHB1dCBpdCBpbnRvIHRoZSBjYWNoZSlcbiBcdFx0dmFyIG1vZHVsZSA9IGluc3RhbGxlZE1vZHVsZXNbbW9kdWxlSWRdID0ge1xuIFx0XHRcdGk6IG1vZHVsZUlkLFxuIFx0XHRcdGw6IGZhbHNlLFxuIFx0XHRcdGV4cG9ydHM6IHt9XG4gXHRcdH07XG5cbiBcdFx0Ly8gRXhlY3V0ZSB0aGUgbW9kdWxlIGZ1bmN0aW9uXG4gXHRcdG1vZHVsZXNbbW9kdWxlSWRdLmNhbGwobW9kdWxlLmV4cG9ydHMsIG1vZHVsZSwgbW9kdWxlLmV4cG9ydHMsIF9fd2VicGFja19yZXF1aXJlX18pO1xuXG4gXHRcdC8vIEZsYWcgdGhlIG1vZHVsZSBhcyBsb2FkZWRcbiBcdFx0bW9kdWxlLmwgPSB0cnVlO1xuXG4gXHRcdC8vIFJldHVybiB0aGUgZXhwb3J0cyBvZiB0aGUgbW9kdWxlXG4gXHRcdHJldHVybiBtb2R1bGUuZXhwb3J0cztcbiBcdH1cblxuXG4gXHQvLyBleHBvc2UgdGhlIG1vZHVsZXMgb2JqZWN0IChfX3dlYnBhY2tfbW9kdWxlc19fKVxuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5tID0gbW9kdWxlcztcblxuIFx0Ly8gZXhwb3NlIHRoZSBtb2R1bGUgY2FjaGVcbiBcdF9fd2VicGFja19yZXF1aXJlX18uYyA9IGluc3RhbGxlZE1vZHVsZXM7XG5cbiBcdC8vIGRlZmluZSBnZXR0ZXIgZnVuY3Rpb24gZm9yIGhhcm1vbnkgZXhwb3J0c1xuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5kID0gZnVuY3Rpb24oZXhwb3J0cywgbmFtZSwgZ2V0dGVyKSB7XG4gXHRcdGlmKCFfX3dlYnBhY2tfcmVxdWlyZV9fLm8oZXhwb3J0cywgbmFtZSkpIHtcbiBcdFx0XHRPYmplY3QuZGVmaW5lUHJvcGVydHkoZXhwb3J0cywgbmFtZSwgeyBlbnVtZXJhYmxlOiB0cnVlLCBnZXQ6IGdldHRlciB9KTtcbiBcdFx0fVxuIFx0fTtcblxuIFx0Ly8gZGVmaW5lIF9fZXNNb2R1bGUgb24gZXhwb3J0c1xuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5yID0gZnVuY3Rpb24oZXhwb3J0cykge1xuIFx0XHRpZih0eXBlb2YgU3ltYm9sICE9PSAndW5kZWZpbmVkJyAmJiBTeW1ib2wudG9TdHJpbmdUYWcpIHtcbiBcdFx0XHRPYmplY3QuZGVmaW5lUHJvcGVydHkoZXhwb3J0cywgU3ltYm9sLnRvU3RyaW5nVGFnLCB7IHZhbHVlOiAnTW9kdWxlJyB9KTtcbiBcdFx0fVxuIFx0XHRPYmplY3QuZGVmaW5lUHJvcGVydHkoZXhwb3J0cywgJ19fZXNNb2R1bGUnLCB7IHZhbHVlOiB0cnVlIH0pO1xuIFx0fTtcblxuIFx0Ly8gY3JlYXRlIGEgZmFrZSBuYW1lc3BhY2Ugb2JqZWN0XG4gXHQvLyBtb2RlICYgMTogdmFsdWUgaXMgYSBtb2R1bGUgaWQsIHJlcXVpcmUgaXRcbiBcdC8vIG1vZGUgJiAyOiBtZXJnZSBhbGwgcHJvcGVydGllcyBvZiB2YWx1ZSBpbnRvIHRoZSBuc1xuIFx0Ly8gbW9kZSAmIDQ6IHJldHVybiB2YWx1ZSB3aGVuIGFscmVhZHkgbnMgb2JqZWN0XG4gXHQvLyBtb2RlICYgOHwxOiBiZWhhdmUgbGlrZSByZXF1aXJlXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLnQgPSBmdW5jdGlvbih2YWx1ZSwgbW9kZSkge1xuIFx0XHRpZihtb2RlICYgMSkgdmFsdWUgPSBfX3dlYnBhY2tfcmVxdWlyZV9fKHZhbHVlKTtcbiBcdFx0aWYobW9kZSAmIDgpIHJldHVybiB2YWx1ZTtcbiBcdFx0aWYoKG1vZGUgJiA0KSAmJiB0eXBlb2YgdmFsdWUgPT09ICdvYmplY3QnICYmIHZhbHVlICYmIHZhbHVlLl9fZXNNb2R1bGUpIHJldHVybiB2YWx1ZTtcbiBcdFx0dmFyIG5zID0gT2JqZWN0LmNyZWF0ZShudWxsKTtcbiBcdFx0X193ZWJwYWNrX3JlcXVpcmVfXy5yKG5zKTtcbiBcdFx0T2JqZWN0LmRlZmluZVByb3BlcnR5KG5zLCAnZGVmYXVsdCcsIHsgZW51bWVyYWJsZTogdHJ1ZSwgdmFsdWU6IHZhbHVlIH0pO1xuIFx0XHRpZihtb2RlICYgMiAmJiB0eXBlb2YgdmFsdWUgIT0gJ3N0cmluZycpIGZvcih2YXIga2V5IGluIHZhbHVlKSBfX3dlYnBhY2tfcmVxdWlyZV9fLmQobnMsIGtleSwgZnVuY3Rpb24oa2V5KSB7IHJldHVybiB2YWx1ZVtrZXldOyB9LmJpbmQobnVsbCwga2V5KSk7XG4gXHRcdHJldHVybiBucztcbiBcdH07XG5cbiBcdC8vIGdldERlZmF1bHRFeHBvcnQgZnVuY3Rpb24gZm9yIGNvbXBhdGliaWxpdHkgd2l0aCBub24taGFybW9ueSBtb2R1bGVzXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLm4gPSBmdW5jdGlvbihtb2R1bGUpIHtcbiBcdFx0dmFyIGdldHRlciA9IG1vZHVsZSAmJiBtb2R1bGUuX19lc01vZHVsZSA/XG4gXHRcdFx0ZnVuY3Rpb24gZ2V0RGVmYXVsdCgpIHsgcmV0dXJuIG1vZHVsZVsnZGVmYXVsdCddOyB9IDpcbiBcdFx0XHRmdW5jdGlvbiBnZXRNb2R1bGVFeHBvcnRzKCkgeyByZXR1cm4gbW9kdWxlOyB9O1xuIFx0XHRfX3dlYnBhY2tfcmVxdWlyZV9fLmQoZ2V0dGVyLCAnYScsIGdldHRlcik7XG4gXHRcdHJldHVybiBnZXR0ZXI7XG4gXHR9O1xuXG4gXHQvLyBPYmplY3QucHJvdG90eXBlLmhhc093blByb3BlcnR5LmNhbGxcbiBcdF9fd2VicGFja19yZXF1aXJlX18ubyA9IGZ1bmN0aW9uKG9iamVjdCwgcHJvcGVydHkpIHsgcmV0dXJuIE9iamVjdC5wcm90b3R5cGUuaGFzT3duUHJvcGVydHkuY2FsbChvYmplY3QsIHByb3BlcnR5KTsgfTtcblxuIFx0Ly8gX193ZWJwYWNrX3B1YmxpY19wYXRoX19cbiBcdF9fd2VicGFja19yZXF1aXJlX18ucCA9IFwiL3N0YXRpYy9qcy9sb2dpbi9cIjtcblxuXG4gXHQvLyBMb2FkIGVudHJ5IG1vZHVsZSBhbmQgcmV0dXJuIGV4cG9ydHNcbiBcdHJldHVybiBfX3dlYnBhY2tfcmVxdWlyZV9fKF9fd2VicGFja19yZXF1aXJlX18ucyA9IFwiLi9sb2dpbi5qc1wiKTtcbiIsIi8qKlxyXG4gKiBAZGVzYyBzZW5kQWpheCDruYTrj5nquLAg7JqU7LKtICgg6rO17Ya1IO2VqOyImCApXHJcbiAqIEBwYXJhbSBzZW5kSGVhZGVyICggbWV0aG9kLCB1cmkpXHJcbiAqIEBwYXJhbSBzZW5kRGF0YSAoIOuztOuCvCDrjbDsnbTthLAgKVxyXG4gKiBAcGFyYW0gY2FsbGJhY2sgKCDrjbDsnbTthLDrpbwg6rCA7KeA6rOgIOyYqOuSpCDsiJjtlontlaAgY2FsbGJhY2sg7ZWo7IiYKVxyXG4gKiBAcmV0dXJucyB4aHIucmVzcG9uc2UgKCBKU09OIO2Yle2DnOuhnCBwYXJzaW5nIClcclxuICovXHJcbmZ1bmN0aW9uIHNlbmRBamF4KHNlbmRIZWFkZXIsIHNlbmREYXRhLCBjYWxsYmFjaywgZmFpbENhbGxiYWNrKSB7XHJcblx0dmFyIHhociA9IG5ldyBYTUxIdHRwUmVxdWVzdCgpO1xyXG5cclxuXHR4aHIub3BlbihzZW5kSGVhZGVyLm1ldGhvZCwgc2VuZEhlYWRlci51cmksIGZhbHNlKTtcclxuXHJcblx0aWYoc2VuZEhlYWRlci5tZXRob2QgPT09ICdHRVQnKXtcclxuXHRcdHhoci5zZXRSZXF1ZXN0SGVhZGVyKFwiQ29udGVudC10eXBlXCIsIFwiYXBwbGljYXRpb24veC13d3ctZm9ybS11cmxlbmNvZGVkXCIpO1xyXG5cdH0gZWxzZSB7XHJcblx0XHR4aHIuc2V0UmVxdWVzdEhlYWRlcihcIkNvbnRlbnQtdHlwZVwiLFwiYXBwbGljYXRpb24vanNvblwiKTtcclxuXHR9XHJcblx0XHJcblxyXG5cdHhoci5vbnJlYWR5c3RhdGVjaGFuZ2UgPSBmdW5jdGlvbigpIHtcclxuXHRcdFxyXG5cdFx0aWYgKHhoci5yZWFkeVN0YXRlID09PSB4aHIuRE9ORSAmJiB4aHIuc3RhdHVzID09PSAyMDApIHtcclxuXHRcdFx0Y2FsbGJhY2suYXBwbHkodGhpcywgWyBKU09OLnBhcnNlKHhoci5yZXNwb25zZSkgXSk7XHJcblx0XHR9IGVsc2UgaWYgKHhoci5zdGF0dXMgPT09IDQwMCl7XHJcblx0XHRcdGZhaWxDYWxsYmFjaygpO1xyXG5cdFx0fVxyXG5cdH1cclxuXHR4aHIuc2VuZChzZW5kRGF0YSk7XHJcbn1cclxuXHJcbm1vZHVsZS5leHBvcnRzID0gc2VuZEFqYXg7IiwiLyoqXHJcbiAqIFxyXG4gKi9cclxubW9kdWxlLmV4cG9ydHMgPSB7XHJcblx0RU1BSUxfUkVHRVggOiAvXigoW148PigpXFxbXFxdXFxcXC4sOzpcXHNAXCJdKyhcXC5bXjw+KClcXFtcXF1cXFxcLiw7Olxcc0BcIl0rKSopfChcIi4rXCIpKUAoKFxcW1swLTldezEsM31cXC5bMC05XXsxLDN9XFwuWzAtOV17MSwzfVxcLlswLTldezEsM31dKXwoKFthLXpBLVpcXC0wLTldK1xcLikrW2EtekEtWl17Mix9KSkkL2csXHJcblxyXG4gICAgUEhPTkVfTlVNQkVSX1JFR0VYIDogL15cXGR7M30tXFxkezMsNH0tXFxkezR9JC9nLFxyXG4gICAgXHJcbiAgICBBRERfQ09NTUFfUkVHRVggOiAvXFxCKD89KFxcZHszfSkrKD8hXFxkKSkvZyxcclxuXHJcbiAgICBOQU1FX1JFR0VYIDogL15b6rCALe2eo117Miw0fXxbYS16QS1aXXsyLDEwfVxcc1thLXpBLVpdezIsMTB9JC9nLFxyXG59OyIsIlxyXG5jb25zdCByZWdleFV0aWwgPSByZXF1aXJlKCcuL3JlZ2V4VXRpbCcpO1xyXG5cclxubW9kdWxlLmV4cG9ydHMgPSB7ICBcclxuICAgIFxyXG4gICAgLyoqXHJcbiAgICAgKiBAZGVzYyDsnbTrqZTsnbwg7Jyg7Zqo7ISxIOqygOyCrFxyXG4gICAgICogQHBhcmFtIHtTdHJpbmd9IGVtYWlsIFxyXG4gICAgICovXHJcbiAgICB2YWxpZGF0ZUVtYWlsKGVtYWlsKXtcclxuICAgICAgICByZXR1cm4gdGhpcy5faW52YWxpZGF0ZVN0cmluZ0VtcHR5KGVtYWlsLnRyaW0oKSkgJiYgZW1haWwubWF0Y2gocmVnZXhVdGlsLkVNQUlMX1JFR0VYKTtcclxuICAgIH0sXHJcbiAgICBcclxuICAgIC8qKlxyXG4gICAgICogQGRlc2Mg7ZW465Oc7Y+wIOuyiO2YuCAoLSkg7Y+s7ZWoIOycoO2aqOyEsSDqsoDsgqxcclxuICAgICAqIEBwYXJhbSB7U3RyaW5nfSBwaG9uZU51bWJlciBcclxuICAgICAqL1xyXG4gICAgdmFsaWRhdGVQaG9uZU51bWJlcihwaG9uZU51bWJlcil7XHJcbiAgICAgICAgcmV0dXJuIHRoaXMuX2ludmFsaWRhdGVTdHJpbmdFbXB0eShwaG9uZU51bWJlci50cmltKCkpICYmIHBob25lTnVtYmVyLm1hdGNoKHJlZ2V4VXRpbC5QSE9ORV9OVU1CRVJfUkVHRVgpO1xyXG4gICAgfSxcclxuXHJcbiAgICAvKipcclxuICAgICAqIEBkZXNjIOydtOumhCDsnKDtmqjshLEg6rKA7IKsXHJcbiAgICAgKiBAcGFyYW0ge1N0cmluZ30gbmFtZSBcclxuICAgICAqL1xyXG4gICAgdmFsaWRhdGVOYW1lKG5hbWUpe1xyXG4gICAgICAgIHJldHVybiB0aGlzLl9pbnZhbGlkYXRlU3RyaW5nRW1wdHkobmFtZS50cmltKCkpICYmIG5hbWUubWF0Y2gocmVnZXhVdGlsLk5BTUVfUkVHRVgpO1xyXG4gICAgfSxcclxuXHJcbiAgICBcclxuICAgIC8qKlxyXG4gICAgICogQGRlc2Mg67mI66y47J6Q7Je07J247KeAIOyytO2BrFxyXG4gICAgICogQHBhcmFtIHtTdHJpbmd9IHN0ciBcclxuICAgICAqL1xyXG4gICAgX2ludmFsaWRhdGVTdHJpbmdFbXB0eShzdHIpe1xyXG4gICAgICAgIHJldHVybiBzdHIgIT09ICcnO1xyXG4gICAgfVxyXG59OyIsIi8qKlxyXG4gKiBAZGVzYyDsoITsl60g67OA7IiYXHJcbiAqL1xyXG5jb25zdCBzZW5kQWpheCA9IHJlcXVpcmUoJy4uL3NlbmRBamF4Jyk7XHJcbmNvbnN0IHZhbGlkYXRlID0gcmVxdWlyZSgnLi4vdXRpbC92YWxpZGF0ZScpO1xyXG5cclxuZnVuY3Rpb24gUmVzZXJ2YXRpb25FbWFpbCgpe1xyXG5cdHRoaXMuaW5pdCgpO1xyXG59O1xyXG5cclxuUmVzZXJ2YXRpb25FbWFpbC5wcm90b3R5cGUgPSB7XHJcblx0XHRcclxuICAgIGluaXQoKXtcclxuICAgICAgICB0aGlzLmFkZExvZ2luQnV0dG9uRXZlbnQoKTtcclxuICAgIH0sXHJcblxyXG4gICAgZ2V0UmVzZXJ2YXRpb25FbWFpbCgpe1xyXG4gICAgICAgIHJldHVybiBkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcucmVzZXJ2YXRpb25fZW1haWwnKS52YWx1ZTtcclxuICAgIH0sXHJcblxyXG4gICAgYWRkTG9naW5CdXR0b25FdmVudCgpe1xyXG4gICAgICAgIGRvY3VtZW50LnF1ZXJ5U2VsZWN0b3IoJy5sb2dpbl9idG4nKS5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsKCk9PntcclxuICAgICAgICAgICAgbGV0IHJlc2VydmF0aW9uRW1haWwgPSB0aGlzLmdldFJlc2VydmF0aW9uRW1haWwoKTtcclxuICAgICAgICAgICAgXHJcbiAgICAgICAgICAgIGlmKCB2YWxpZGF0ZS52YWxpZGF0ZUVtYWlsKHRoaXMuZ2V0UmVzZXJ2YXRpb25FbWFpbCgpKSl7XHJcbiAgICAgICAgICAgICAgICB0aGlzLnNlbmRSZXNlcnZhdGlvbkVtYWlsKHJlc2VydmF0aW9uRW1haWwpOyAgIFxyXG4gICAgICAgICAgICB9IGVsc2Uge1xyXG4gICAgICAgICAgICAgICAgYWxlcnQoJ+ydtOuplOydvCDtmJXsi53snbQg7J6Y66q765CY7JeI7Iq164uI64ukLicpO1xyXG4gICAgICAgICAgICB9XHJcbiAgICAgICAgfSk7IFxyXG4gICAgfSxcclxuXHJcbiAgICBzZW5kUmVzZXJ2YXRpb25FbWFpbChyZXNlcnZhdGlvbkVtYWlsKXtcclxuICAgIFx0XHJcbiAgICBcdGNvbnN0IHJlc2VydmF0aW9uRW1haWxTZW5kSGVhZGVyID0ge1xyXG4gICAgICAgICAgICBtZXRob2QgOiAnR0VUJyxcclxuICAgICAgICAgICAgdXJpIDogJy9hcGkvbG9naW4/cmVzZXJ2YXRpb25FbWFpbD0nK3Jlc2VydmF0aW9uRW1haWxcclxuICAgICAgICB9O1xyXG4gICAgXHRcclxuICAgICAgICBzZW5kQWpheChyZXNlcnZhdGlvbkVtYWlsU2VuZEhlYWRlciwnJywoKT0+e1xyXG4gICAgICAgIFxyXG4gICAgICAgIFx0bG9jYXRpb24uaHJlZj0nL3Jlc2VydmF0aW9ucyc7ICAgXHJcbiAgICAgICAgfSwoKT0+e1xyXG4gICAgICAgICAgICBhbGVydCgn7J2066mU7J28IO2YleyLneydtCDsnpjrqrvrkJjsl4jsirXri4jri6QuJyk7XHJcbiAgICAgICAgfSk7XHJcbiAgICB9XHJcbn1cclxuXHJcbmRvY3VtZW50LmFkZEV2ZW50TGlzdGVuZXIoJ0RPTUNvbnRlbnRMb2FkZWQnLCAoKSA9PiB7XHJcblxyXG4gICAgY29uc3QgcmVzZXJ2YXRpb25FbWFpbCA9IG5ldyBSZXNlcnZhdGlvbkVtYWlsKCk7XHJcbn0pO1xyXG5cclxuIl0sInNvdXJjZVJvb3QiOiIifQ==