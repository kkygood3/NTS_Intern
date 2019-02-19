/**
 * @desc 전역 변수
 */
const sendAjax = require('../sendAjax');
const validate = require('../util/validate');

/**
 * @desc reservationEmail class
 */
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

    /**
     * @desc 로그인 하기 버튼 이벤트
     */
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

    /**
     * @desc reservationEmail 서버에 세션에 저장 
     * @param {String} reservationEmail 
     */
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

