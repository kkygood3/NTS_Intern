/**
 * @desc 전역 변수
 */
const regexUtil = require('./regexUtil');

module.exports = {
    /**
     * @desc number String 된 값을 넘겨주면 세자리수마다 , 찍어주기
     * @param {String} numberString 
     */
    getCommaToNumberString(numberString){
        return numberString.replace(regexUtil.ADD_COMMA_REGEX,",");
    },
}