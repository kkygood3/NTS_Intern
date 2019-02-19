/**
 * @desc 전역 변수
 */
const sendAjax = require('../sendAjax');
const validate = require('../util/validate');

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

