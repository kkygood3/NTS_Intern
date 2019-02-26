let loginEmail;
let ticketContainers = document.querySelectorAll('li.card');
let countAreas = document.querySelectorAll('.link_summary_board>.figure');
let moreBtns = document.querySelectorAll('.more>.btn');
let nextStart = [0, 0, 0];
let asyncCount = 0;
let isInitialize = false;

const RESERVATION_TYPE = ['CONFIRM','COMPLETE','CANCEL'];
const PAGING_LIMIT = 3;

function loadReservationInfoCallback(response){
	let reservationList = response.reservations;
	let reservationInfoType = response.reservationInfoType;
	let count = response.count;
	if(count > 0){
		countAreas[getTypeIdx(reservationInfoType) + 1].innerText = count;
		templateReservation(reservationList, reservationInfoType);
	}
	
	asyncCount++
	if(!isInitialize && asyncCount >= RESERVATION_TYPE.length) {
		isInitialize = true; 
		initTicketCancelEvents(ticketContainers);
		initTicketCancelPopupEvent();
		initBtnLayouts();
		initMoreBtnClickEvent();
	}
	
	checkTicketCount();
}

function requestAjax(callback, url, method) {
	var ajaxReq = new XMLHttpRequest();
	ajaxReq.callback = callback;
	ajaxReq.addEventListener('load', function(evt) {
		this.callback(evt.target.response);
	});
	ajaxReq.open(method, url);
	ajaxReq.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
	ajaxReq.responseType = 'json';
	ajaxReq.send()
}

function getTypeIdx(type){
	let targetIdx;
	if(type === 'CONFIRM'){
		targetIdx = 0;
	} else if(type === 'COMPLETE') {
		targetIdx = 1;
	} else {
		targetIdx = 2;
	}
	return targetIdx;
}

function getBtnRequestParam(type){
	let targetIdx = getTypeIdx(type);
	let param = 'reservationInfoType=' + type + '&start=' + nextStart[targetIdx] + '&limit=' + PAGING_LIMIT;
	nextStart[targetIdx] += 3;
	return param;
}

function convertPrintType(reservationList){
	reservationList.forEach(item =>{
		item.reservationDate = item.reservationDate.split(' ')[0].replace(/-/g,'.');
		item.price = item.price.toString().replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
	});
}

function loadReservationItem(type){
	requestAjax(loadReservationInfoCallback, 'api/lookUpReservation?'+getBtnRequestParam(type),'GET');
}

function initBtnLayouts(){
	document.querySelectorAll('a.link_summary_board').forEach(item=>item.style.cursor='default');
	
	document.querySelectorAll('a.link_booking_details').forEach(item=>{
		item.style.cursor='default';
		item.addEventListener('click',(evt)=>evt.preventDefault());
	});
}

function checkTicketCount(){
	let sumOfCount = 0;
	for(let i = 0 ; i < RESERVATION_TYPE.length; i++){
		let emptyMsgArea = ticketContainers[i + 1].querySelector('.err');
		let itemCount = parseInt(countAreas[i + 1].innerText);
		sumOfCount += itemCount;
		
		if(itemCount > 0){
			emptyMsgArea.style.display = 'none';
		} else {
			emptyMsgArea.style.display = '';
		}
		
		if(itemCount <= nextStart[i]){
			moreBtns[i].style.display = 'none';
		} else {
			moreBtns[i].style.display = '';
		}
	}
	
	countAreas[0].innerText = sumOfCount;
}

function initMoreBtnClickEvent(){
	for(let i = 0 ; i < RESERVATION_TYPE.length; i++){
		moreBtns[i].addEventListener('click',function(evt){
			let idx = parseInt(evt.target.getAttribute('idx'));
			loadReservationItem(RESERVATION_TYPE[idx]);
		});
	}
}

function cancelResultCallback(response){
	if(response.result === 'cancelSuccess'){
		// 응답 OK일때 상단 Count변경
		countAreas[1].innerText = parseInt(countAreas[1].innerText)-1;
		countAreas[3].innerText = parseInt(countAreas[3].innerText)+1;
		nextStart[2]++;
		checkTicketCount();
	} else {
		alert('예약을 취소할 수 없습니다.');
		location.reload();
	}
}

function initTicketCancelEvents(ticketContainers){
	let cancelPopup = document.querySelector('.popup_booking_wrapper');
	let cancelTitleArea = cancelPopup.querySelector('.pop_tit>span');
	let cancelDate = cancelPopup.querySelector('.pop_tit>.sm');
	
	ticketContainers[1].addEventListener('click',function(evt){
		let clickedBtn = evt.target;
		
		let isClickedChild = (clickedBtn.tagName == 'SPAN')
		if (isClickedChild){
			clickedBtn = clickedBtn.parentElement;
		}
		
		if (clickedBtn.classList.contains('btn')){
			let descriptionAreas = clickedBtn.offsetParent.querySelectorAll('ul.detail .item_dsc');
			cancelDate.innerText = descriptionAreas[0].innerText;
			cancelTitleArea.innerText = descriptionAreas[1].innerText;
			cancelPopup.style.display = '';
			cancelPopup.setAttribute('reservationInfoId',clickedBtn.getAttribute('reservationInfoId'));
			currentSelect = clickedBtn;
		}
	});
}

function initTicketCancelPopupEvent(){
	let cancelPopup = document.querySelector('.popup_booking_wrapper');
	let cancelContainer = document.querySelectorAll('li.card')[3]; 
	
	cancelPopup.addEventListener('click',popupClickEvent);
	
	function popupClickEvent(evt){
		evt.preventDefault();
		let clickedPopupBtn = evt.target;
		if(clickedPopupBtn.tagName == 'SPAN'){
			clickedPopupBtn = clickedPopupBtn.parentElement;
		}
		
		let isAcceptClicked = (clickedPopupBtn.classList.contains('btn_bottom') && clickedPopupBtn.innerText === '예')

		if(isAcceptClicked) {
			cancelContainer.appendChild(currentSelect.offsetParent);
			currentSelect.parentElement.remove();			
			requestAjax(cancelResultCallback, 'api/cancelReservation/'+cancelPopup.getAttribute('reservationInfoId'),'PUT');
		}
		
		let isCloseClicked = (clickedPopupBtn.classList.contains('ico_cls') || (clickedPopupBtn.classList.contains('btn_bottom') && clickedPopupBtn.innerText === '아니오'));
		
		if(isAcceptClicked||isCloseClicked){
			cancelPopup.style.display = 'none';
		}
		checkTicketCount();
	}
}

function initReservationInfo(){
	RESERVATION_TYPE.forEach(type=>loadReservationItem(type));
}

function templateReservation(reservationList, reservationInfoType){
	convertPrintType(reservationList);
	
	let reservationTemplate = document.querySelector('#reservationTemplate').innerText;
	let bindCommentTemplate = Handlebars.compile(reservationTemplate);
	
	let targetIdx = getTypeIdx(reservationInfoType);
	reservationList.forEach(reservation=>{
		ticketContainers[targetIdx + 1].innerHTML += bindCommentTemplate(reservation);
		let cancelBtn = ticketContainers[targetIdx + 1].lastElementChild.querySelector('.btn');
		if(targetIdx == 1){
			cancelBtn.innerText = '예매자 리뷰 남기기';
		} else if(targetIdx == 2){
			cancelBtn.parentElement.remove();
		}
	});
}
