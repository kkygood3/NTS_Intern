/**
 * 
 */

let myReservation = {
	popup : document.querySelector('.popup_booking_wrapper'),
	selectedId : 0,
	counts : document.querySelectorAll('.figure'),
	confirmed : document.querySelector('#confirmed'),
	used : document.querySelector('#used'),
	canceled : document.querySelector('#canceled'),
	emptySign : document.querySelector('#empty_sign')
}

document.addEventListener('DOMContentLoaded', ()=>{
	document.querySelector('.header').classList.remove('fade');
	new ReservationCancel();
});


function ReservationCancel(){
	this.init();
	this.addEmptySign();
}
ReservationCancel.prototype = {
	init : function(){
		this.addCancelEvent();
		this.cancelYnEvent();
	},
	addCancelEvent : function(){
		// 취소하기 button
		document.querySelectorAll('.cancel_btn').forEach(btn=>{
			btn.addEventListener('click', event=>this.showPopup(event));
		});
		document.querySelector('#cancel_accept').addEventListener('click', event=>{
			ajax(this.moveToCancelBox.bind(this), 'PUT', '/api/reservations/' + myReservation.selectedId, 'Fail to Cancel Reservation!');
		});
		
	},
	cancelYnEvent : function(){
		document.querySelectorAll('.btn_bottom').forEach(btn=>{
			btn.addEventListener('click', event=>{
				this.hidePopup();
			});
		});
		document.querySelector('.popup_btn_close').addEventListener('click', ()=>{
			this.hidePopup();
		});
	},
	showPopup : function(event){
		myReservation.popup.style.display = 'block';
		myReservation.selectedId = event.target.dataset.id;
		
		let title = document.querySelectorAll('.item_' + event.target.dataset.id)[0].innerText;;
		let date = document.querySelectorAll('.item_' + event.target.dataset.id)[1].innerText;;

		document.querySelector('.pop_tit').firstElementChild.innerText = title;
		document.querySelector('.sm').innerText = date;
	},
	hidePopup : function(){
		myReservation.popup.style.display = 'none';
	},
	moveToCancelBox : function(){
		myReservation.counts[1].innerText--;
		myReservation.counts[3].innerText++;

		// element 이동
		let elementToMove = document.querySelector('#reservation_' + myReservation.selectedId);
		myReservation.canceled.insertBefore(elementToMove, null);
		
		// 취소 버튼 삭제
		elementToMove.firstElementChild.firstElementChild.children[1].firstElementChild.children[4].remove();

		this.addEmptySign();
	},
	addEmptySign : function(){
		document.querySelectorAll('.err').forEach(element=>{element.remove()});
		
		if(myReservation.confirmed.childElementCount == 1){
			myReservation.confirmed.innerHTML += myReservation.emptySign.innerHTML;
		}
		
		if(myReservation.used.childElementCount == 1){
			myReservation.used.innerHTML += myReservation.emptySign.innerHTML;
		}
		
		if(myReservation.canceled.childElementCount == 1){
			myReservation.canceled.innerHTML += myReservation.emptySign.innerHTML;
		}
	}
}
