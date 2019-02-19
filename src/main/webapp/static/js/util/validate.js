
const regexUtil = require('./regexUtil');

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