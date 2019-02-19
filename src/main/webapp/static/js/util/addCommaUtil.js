/**
 * 
 */
const regexUtil = require('./regexUtil');

module.exports = {
    getCommaToNumberString(numberString){
        return numberString.replace(regexUtil.ADD_COMMA_REGEX,",");
    },
}