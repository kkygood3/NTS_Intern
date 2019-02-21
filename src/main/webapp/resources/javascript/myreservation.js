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
	addCancelEvent();
	addEmptySign();
});


function addCancelEvent(){
	// 취소하기 button
	document.querySelectorAll('.cancel_btn').forEach(btn=>{
		btn.addEventListener('click', event=>showPopup(event));
	});
	
	document.querySelector('#cancel_accept').addEventListener('click', event=>{
		ajax(moveToCancelBox, 'PUT', '/api/reservations/' + myReservation.selectedId, 'Fail to Cancel Reservation!');
	});
	
	// yes / no button
	document.querySelectorAll('.btn_bottom').forEach(btn=>{
		btn.addEventListener('click', event=>{
			myReservation.popup.style.display = 'none';
		});
	});
	
	// close button
	document.querySelector('.popup_btn_close').addEventListener('click', event=>{
		myReservation.popup.style.display = 'none';
	});
}


function showPopup(event){
	myReservation.popup.style.display = 'block';
	myReservation.selectedId = event.target.dataset.id;
	
	let title = event.target.parentElement.parentElement.children[1].innerText;
	let date = event.target.parentElement.parentElement.children[2].firstElementChild.children[1].innerText;
	
	document.querySelector('.pop_tit').firstElementChild.innerText = title;
	document.querySelector('.sm').innerText = date;
}

function moveToCancelBox(data){
	myReservation.counts[1].innerText--;
	myReservation.counts[3].innerText++;

	// element 이동
	let elementToMove = document.querySelector('#reservation_' + data.reservationInfoId);
	myReservation.canceled.insertBefore(elementToMove, null);
	
	// 취소 버튼 삭제
	elementToMove.firstElementChild.firstElementChild.children[1].firstElementChild.children[4].remove();

	addEmptySign();
}

function addEmptySign(){
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