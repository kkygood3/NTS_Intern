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
			
			targetReservation.price = targetReservation.price.toString().replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,')
			targetReservation.index = i+1;
			var reservationItem = bindCommentTemplate(targetReservation);
			
			/**
			 * 0 보다 크면 cancel으로 이동. 0 이라면 -> 날짜가 오늘보다 이전이면 conplete으로 -> 날짜가 오늘
			 * 이후라면 confirm으로
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
		setBtnClickEvents();
		initUnclickableBtn();
	} else {
		var listWrap = document.querySelector('.wrap_mylist');
		var emptyMsgWrap = document.querySelector('.err');
		listWrap.remove();
		emptyMsgWrap.style.display = '';
	}
}

function initUnclickableBtn(){
	// 상단 네비게이션 a tag
	document.querySelectorAll('a.link_summary_board').forEach(item=>item.style.cursor='default');
	
	// myreservation item a tag
	document.querySelectorAll('a.link_booking_details').forEach(item=>{
		item.style.cursor='default';
		item.addEventListener('click',(evt)=>evt.preventDefault());
	});
	
}

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

function cancelResultCallback(response){
	if(!response || response.result != 'OK'){
		alert('예약을 취소할 수 없습니다.');
		location.reload();
	}
}
	
function setBtnClickEvents(){
	var ticketContainers = document.querySelectorAll('li.card')
	
	
	ticketContainers[1].addEventListener('click',function(evt){
	    var clickedBtn = evt.target;
	    
	    var isClickedChild = (clickedBtn.tagName == 'SPAN')
	    if (isClickedChild){
	    	clickedBtn = clickedBtn.parentElement;
	    }
	    
	    var isBtnClicked = (clickedBtn.classList.contains('btn'));
	    if (isBtnClicked){
	    	ticketContainers[3].appendChild(clickedBtn.offsetParent);
	    	clickedBtn.parentElement.remove();
	    	// 취소 처리 ajax
	    	requestAjax(cancelResultCallback, 'api/reservations/'+clickedBtn.getAttribute('reservationInfoId'),'PUT');
	    }
	    	
	    checkTicketCount();
	});
	
	ticketContainers[2].querySelectorAll('.btn').forEach(item => item.innerText = '예매자 리뷰 남기기');
	ticketContainers[2].addEventListener('click',function(evt){
	    var clickedBtn = evt.target;
	    
	    var isClickedChild = (clickedBtn.tagName == 'SPAN')
	    if (isClickedChild){
	    	clickedBtn = clickedBtn.parentElement;
	    }
	    
	    var isBtnClicked = (clickedBtn.classList.contains('btn'));
	    if (isBtnClicked){
	    	// PJT6 : 예매자 리뷰 남기기 Event 처리
	    }
	    checkTicketCount();
	});
	
	ticketContainers[3].querySelectorAll('.booking_cancel').forEach(item => item.remove());
	checkTicketCount();
}