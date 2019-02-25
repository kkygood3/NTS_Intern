module.exports = {
    
    /**
     * @desc 오늘 날짜 YYYY-MM-DD 형태로 반환
     * @return {String} YYYY-MM-DD
     */
    getTodayYYYYMMDD(){
        const date = new Date();
        return date.getFullYear() + "-" + this._lpadMonth(date.getMonth()+1) + "-" + date.getDate();
    },

    /**
     * @desc 10월 보다 작으면 앞에 숫자 0 붙여줌
     * @param {String} month 
     */
    _lpadMonth(month){
        return month < 10 ? '0' + month : month;
    }
}