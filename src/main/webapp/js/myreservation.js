var loginEmail;
var ticketContainers = document.querySelectorAll('li.card');
var countAreas = document.querySelectorAll('.link_summary_board>.figure');
var moreBtns = document.querySelectorAll('.more>.btn');
var nextStart = [0, 0, 0];
var asyncCount = 0;
var isInitialize = false;

const RESERVATION_TYPE = ['CONFIRM','COMPLETE','CANCEL'];
const PAGING_LIMIT = 3;

/**
 * 전역변수를 Idx로 일괄적으로 관리하기 위해 사용 
 * @param type
 * @returns CONFIRM 0, COMPLETE 1, CANCEL 2
 */
function getTypeIdx(type){
	var targetIdx;
	if(type === 'CONFIRM'){
		targetIdx = 0;
	} else if(type === 'COMPLETE') {
		targetIdx = 1;
	} else {
		targetIdx = 2;
	}
	return targetIdx;
}

/**
 * 버튼 이벤트로 실행할 ajax request의 패러미터를 반환하고
 * Paging처리에 사용하는 nextStart를 조정
 * @param type
 */
function getBtnRequestParam(type){
	var targetIdx = getTypeIdx(type);
	var param = 'reservationType=' + type + '&start=' + nextStart[targetIdx] + '&pagingLimit=' + PAGING_LIMIT;
	nextStart[targetIdx] += 3;
	return param;
}

/**
 * MyReservation 아이템의 속성을 페이지의 출력 형태로 변환
 * @param reservationList
 * @returns
 */
function convertPrintType(reservationList){
	reservationList.forEach(item =>{
		item.reservationDate = item.reservationDate.split(' ')[0].replace(/-/g,'.');
		item.price = item.price.toString().replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
	});
}

function loadReservationItem(type){
	requestAjax(loadReservationInfoCallback, 'api/reservations?'+getBtnRequestParam(type),'GET');
}

function initBtnLayouts(){
	ticketContainers[2].querySelectorAll('.btn').forEach(item => item.innerText = '예매자 리뷰 남기기');
	ticketContainers[3].querySelectorAll('.booking_cancel').forEach(item => item.remove());
	
	// 상단 네비게이션 a tag
	document.querySelectorAll('a.link_summary_board').forEach(item=>item.style.cursor='default');
	
	// myreservation item a tag
	document.querySelectorAll('a.link_booking_details').forEach(item=>{
		item.style.cursor='default';
		item.addEventListener('click',(evt)=>evt.preventDefault());
	});
}

/**
 * 호출 시점에 티켓수가
 * 0개 라면 예약 목록이 없다고 알려주며
 * 더보기 버튼으로 가져올 게 없을 때 숨김
 */
function checkTicketCount(){
	var sumOfCount = 0;
	for(var i = 0 ; i < RESERVATION_TYPE.length; i++){
		var emptyMsgArea = ticketContainers[i + 1].querySelector('.err');
		var itemCount = parseInt(countAreas[i + 1].innerText);
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

/**
 * 더보기 버튼을 눌렀을 때의 클릭 이벤트를 등록
 */
function initMoreBtnClickEvent(){
	for(var i = 0 ; i < RESERVATION_TYPE.length; i++){
		moreBtns[i].addEventListener('click',function(evt){
			var idx = parseInt(evt.target.getAttribute('idx'));
			loadReservationItem(RESERVATION_TYPE[idx]);
		});
	}
}

/**
 * 취소요청이 서버에 반영되었는지 확인.
 * @param response -
 *            취소 요청의 응답. 서버에서 OK나 FAIL로 응답한다.
 */
function cancelResultCallback(response){
	if(!response || response.result != 'OK'){
		alert('예약을 취소할 수 없습니다.');
		location.reload();
	} else {
		// 응답 OK일때 상단 Count변경
		countAreas[1].innerText = parseInt(countAreas[1].innerText)-1;
		countAreas[3].innerText = parseInt(countAreas[3].innerText)+1;
		checkTicketCount();
	}
}

/**
 * 티켓 아이템중 예약 확정 카테고리의 버튼 이벤트 처리 취소 버튼을 누르면 팝업창을 띄우고 팝업 이벤트를 등록한다.
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
		
		if (clickedBtn.classList.contains('btn')){
			var descriptionAreas = clickedBtn.offsetParent.querySelectorAll('ul.detail .item_dsc');
			cancelDate.innerText = descriptionAreas[0].innerText;
			cancelTitleArea.innerText = descriptionAreas[1].innerText;
			cancelPopup.style.display = '';
			cancelPopup.setAttribute('reservationInfoId',clickedBtn.getAttribute('reservationInfoId'));
			currentSelect = clickedBtn;
		}
			
		checkTicketCount();
	});
}

function initTicketCancelPopupEvent(){
	var cancelPopup = document.querySelector('.popup_booking_wrapper');
	var cancelContainer = document.querySelectorAll('li.card')[3]; 
	
	cancelPopup.addEventListener('click',popupClickEvent);
	
	function popupClickEvent(evt){
		evt.preventDefault();
		var clickedPopupBtn = evt.target;
		if(clickedPopupBtn.tagName == 'SPAN'){
			clickedPopupBtn = clickedPopupBtn.parentElement;
		}
		
		var isAcceptClicked = (clickedPopupBtn.classList.contains('btn_bottom') && clickedPopupBtn.innerText === '예')
		
		/**
		 * '예' 버튼을 눌렀다면 예약 확정 카테고리에서 예약 취소 카테고리로 옮기고 서버에 ajax로 취소 요청
		 */
		if(isAcceptClicked) {
			cancelContainer.appendChild(currentSelect.offsetParent);
			currentSelect.parentElement.remove();			
			requestAjax(cancelResultCallback, 'api/reservations/'+cancelPopup.getAttribute('reservationInfoId'),'PUT');
		}
		
		var isCloseClicked = (clickedPopupBtn.classList.contains('ico_cls') || (clickedPopupBtn.classList.contains('btn_bottom') && clickedPopupBtn.innerText === '아니오'));
		
		if(isAcceptClicked||isCloseClicked){
			cancelPopup.style.display = 'none';
		}
	}
}

function initReservationInfo(){
	RESERVATION_TYPE.forEach(type=>loadReservationItem(type));
}

/**
 * ajax response를 패러미터로 받는다. myreservation 페이지 로드시 필요한 정보를 화면에 출력한다.
 */
function loadReservationInfoCallback(response){
	var reservationList = response.myReservationResponse.reservationList;
	var reservationType = response.myReservationResponse.reservationType;
	var count = response.myReservationResponse.count;
	if(count > 0){
		convertPrintType(reservationList);
		
		var reservationTemplate = document.querySelector('#reservationTemplate').innerText;
		var bindCommentTemplate = Handlebars.compile(reservationTemplate);
		
		var targetIdx = getTypeIdx(reservationType);
		countAreas[targetIdx + 1].innerText = count;
		reservationList.forEach(item=>ticketContainers[targetIdx + 1].innerHTML += bindCommentTemplate(item));
	}
	
	/**
	 * 아이템이 모두 Load 되었을 때 기준으로 initailize
	 */
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