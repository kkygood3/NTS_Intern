/**
 * ajax response를 패러미터로 받는다.
 * myreservation 페이지 로드시 필요한 정보를 화면에 출력한다. 
 */
function loadReservationInfoCallback(response){
	var reservationList = response.myReservationResponse;
	
	if(reservationList.length > 0){
		// 예약정보 Template
		var reservationTemplate = document.querySelector('#reservationTemplate').innerText;
		var bindCommentTemplate = Handlebars.compile(reservationTemplate);
		
		var ticketContainers = document.querySelectorAll('li.card');
		var confirmContainer = ticketContainers[1];
		var completeContainer = ticketContainers[2];
		var cancelContainer = ticketContainers[3];
		
		for(var i = 0 ; i < reservationList.length; i++){
			var targetReservation = reservationList[i];
			targetReservation.reservationDate = targetReservation.reservationDate.split(' ')[0].replace(/-/g,'.');
			targetReservation.price = targetReservation.price.toString().replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
			targetReservation.index = i+1;
			
			var reservationItem = bindCommentTemplate(targetReservation);
			
			/**
			 * 1) 0 보다 크면 cancel으로 이동.
			 * 2) 0 보다 작거나 같다면 ->
			 *  - 날짜가 오늘 이전이라면 conplete으로
			 *  - 날짜가 오늘 이후라면 confirm으로
			 */
			if(targetReservation.cancelFlag > 0){
				cancelContainer.innerHTML += reservationItem;
			} else {
				var today = new Date();
				var targetDate = new Date(targetReservation.displayDate)
				
				// 오늘보다 이전이라면
				if(targetDate < today) {
					completeContainer.innerHTML += reservationItem;
				} else {
					confirmContainer.innerHTML += reservationItem;
				}
			}
		}
		
		checkTicketCount();
		initTicketBtnClickEvents();
		initUnclickableBtn();
	} else {
		/**
		 * 해당 Email의 예약 정보가 하나도 없을 때
		 * 예약 정보가 없다는 메세지 출력
		 */
		var listWrap = document.querySelector('.wrap_mylist');
		var emptyMsgWrap = document.querySelector('.err');
		listWrap.remove();
		emptyMsgWrap.style.display = '';
	}
}
/**
 * 클릭 이벤트가 없는 영역의 마우스 커서를 Default로 고정
 */
function initUnclickableBtn(){
	// 상단 네비게이션 a tag
	document.querySelectorAll('a.link_summary_board').forEach(item=>item.style.cursor='default');
	
	// myreservation item a tag
	document.querySelectorAll('a.link_booking_details').forEach(item=>{
		item.style.cursor='default';
		item.addEventListener('click',(evt)=>evt.preventDefault());
	});
	
}

/**
 * 호출 시점의 Ticket정보를 확인하여
 * 상단 네비게이션, 티켓 Category 타이틀에 반영하는 함수
 */
function checkTicketCount(){
	var ticketContainers = document.querySelectorAll('li.card');
	var reservationCountAreas = document.querySelectorAll('.link_summary_board>.figure');
	var sumOfCount = 0;
	
	for(var i = 1 ; i < 4; i++){
		var countOfChild = (ticketContainers[i].querySelectorAll('.card_item').length);
		reservationCountAreas[i].innerText = countOfChild;
		sumOfCount += countOfChild;
		
		if(countOfChild > 0){
			ticketContainers[i].style.display = '';
		} else {
			ticketContainers[i].style.display = 'none';
		}
	}
	reservationCountAreas[0].innerText = sumOfCount;
}

/**
 * 취소요청이 서버에 반영되었는지 확인.
 * @param response - 취소 요청의 응답. 서버에서 OK나 FAIL로 응답한다.
 */
function cancelResultCallback(response){
	if(!response || response.result != 'OK'){
		alert('예약을 취소할 수 없습니다.');
		location.reload();
	}
}

/**
 * 티켓 아이템 클릭 이벤트 처리.
 * 취소, 취소 팝업 버튼, 코멘트 클릭 이벤트를 처리한다.
 */
function initTicketBtnClickEvents(){
	var ticketContainers = document.querySelectorAll('li.card');
	
	initTicketCancelEvents(ticketContainers);
	ticketContainers[2].querySelectorAll('.btn').forEach(item => item.innerText = '예매자 리뷰 남기기');	
	ticketContainers[3].querySelectorAll('.booking_cancel').forEach(item => item.remove());
	checkTicketCount();
}

/**
 * 티켓 아이템중 예약 확정 카테고리의 버튼 이벤트 처리
 * 취소 버튼을 누르면 팝업창을 띄우고 팝업 이벤트를 등록한다.
 */ 
function initTicketCancelEvents(ticketContainers){
	var cancelPopup = document.querySelector('.popup_booking_wrapper');
	var cancelTitleArea = cancelPopup.querySelector('.pop_tit>span');
	var cancelDate = cancelPopup.querySelector('.pop_tit>.sm');
	
	ticketContainers[1].addEventListener('click',function(evt){
		var clickedBtn = evt.target;
		
		var isClickedChild = (clickedBtn.tagName == 'SPAN')
		if (isClickedChild){
			clickedBtn = clickedBtn.parentElement;
		}
		
		var isBtnClicked = (clickedBtn.classList.contains('btn'));
		
		if (isBtnClicked){
			var descriptionAreas = clickedBtn.offsetParent.querySelectorAll('ul.detail .item_dsc');
			cancelDate.innerText = descriptionAreas[0].innerText;
			cancelTitleArea.innerText = descriptionAreas[1].innerText;
			cancelPopup.style.display = '';
			
			cancelPopup.removeEventListener('click',popupClickEvent);
			cancelPopup.addEventListener('click',popupClickEvent);
			
			function popupClickEvent(evt){
				evt.preventDefault();
				var clickedPopupBtn = evt.target;
				if(clickedPopupBtn.tagName == 'SPAN'){
					clickedPopupBtn = clickedPopupBtn.parentElement;
				}
				
				var isAcceptClicked = (clickedPopupBtn.classList.contains('btn_bottom') && clickedPopupBtn.innerText === '예')
				/**
				 * '예' 버튼을 눌렀다면 예약 확정 카테고리에서 예약 취소 카테고리로 옮기고
				 * 서버에 ajax로 취소 요청을 보낸다.
				 */
				if(isAcceptClicked) {
					ticketContainers[3].appendChild(clickedBtn.offsetParent);
					clickedBtn.parentElement.remove();
					requestAjax(cancelResultCallback, 'api/reservations/'+clickedBtn.getAttribute('reservationInfoId'),'PUT');
				}
				
				var isCloseClicked = (clickedPopupBtn.classList.contains('ico_cls') 
						|| (clickedPopupBtn.classList.contains('btn_bottom') && clickedPopupBtn.innerText === '아니오'));
				
				if(isAcceptClicked||isCloseClicked){
					cancelPopup.style.display = 'none';
				}
			}
		}
			
		checkTicketCount();
	});
}