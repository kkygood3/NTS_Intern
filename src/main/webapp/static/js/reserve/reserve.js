/**
 * @desc 전역 변수
 */
const sendAjax = require('../sendAjax');
const addCommaUtil = require('../util/addCommaUtil');
const handlebarsFunction = require('../handlebarsFunction');
const validate = require('../util/validate');
const dateUtil = require('../util/dateUtil');

const globalVariables = {
    reservationButton : document.querySelector("#reservation_button"),
    displayInfoReseponse : {},
    validateReserveInformation : {},
}

/**
 * @desc displayInfo class
 */
function DisplayInfo(){
    this.displayInfoId = this.getDisplayInfoId();
    this.init();
}

DisplayInfo.prototype = {
    init(){
        this.getDisplayInfo();
    },

    /**
     * @desc displayInfoId 가져오기
     */
    getDisplayInfoId(){
    	return document.querySelector("#display_info_id_div").dataset.displayinfoid;
    },

    /**
     * @desc displayInfo 정보 가져오기
     */
    getDisplayInfo(){

        const displayInfoHeader = {
            method: "GET",
            uri: "/api/products/"+this.displayInfoId
        };
        
        sendAjax(displayInfoHeader,'', displayInfoResponse => {
            
            globalVariables.displayInfoReseponse = displayInfoResponse;
            
            this._setTitle(displayInfoResponse.displayInfo.productDescription);
            this._setThumnailImage('/static/'+ displayInfoResponse.productImages[0].saveFileName);
            this._setProductPrices(displayInfoResponse.productPrices.reverse());
            this._setProductPricesTicket(displayInfoResponse.productPrices);
            this._setPlaceLot(displayInfoResponse.displayInfo.placeLot);
        })
    },
    
    /**
     * @desc 썸네일 이미지 셋팅
     * @param {String} thumnailImage 
     */
    _setThumnailImage(thumnailImage){
    	document.querySelector('#img_th').src = thumnailImage;
    },
    
    /**
     * @desc productPrice 셋팅
     * @param {Array} productPrices 
     */
    _setProductPrices(productPrices){
    	
    	let productPriceContent = '';
    	
    	productPrices.forEach( productPrice => {
    		productPriceContent += productPrice.priceTypeName + '석 : ' + addCommaUtil.getCommaToNumberString(productPrice.price.toString()) + ' 원 <br>';
    	});
    	
    	document.querySelector('#product_prices').innerHTML = productPriceContent;
    },

    /**
     * @desc productPrice 정보들 티켓 셋팅
     * @param {Array} productPrices 
     */
    _setProductPricesTicket(productPrices){

        const ticketBody = document.querySelector(".ticket_body");

        productPrices.forEach( productPrice => {

            productPrice.price = addCommaUtil.getCommaToNumberString(productPrice.price.toString());
            ticketBody.innerHTML += handlebarsFunction.getHandlebarTemplateFromHtml("#product_ticket_template",{productPrice: productPrice});
        });

        new TicketButton();
    },

    /**
     * @desc place 장소 셋팅
     * @param {String} placeLot 
     */
    _setPlaceLot(placeLot){
        document.querySelector("#place_lot").innerHTML = placeLot;
    },

    /**
     * @desc title 셋팅
     * @param {String} title 
     */
    _setTitle(title){
        document.querySelector(".title").innerText = title;
    }
    
};

/**
 * @desc TicketButton 관련 class
 */
function TicketButton(){
    this.totalTicketCount = 0;
    this.init();
};

TicketButton.prototype = {
    init(){
        this.addTicketMinusButtonEvent();
        this.addTicketPlusButtonEvent();
    },

    /**
     * @desc ticket 플러스 버튼 이벤트
     */
    addTicketPlusButtonEvent(){

        const plusCount = 1;
        document.querySelectorAll(".plus_button").forEach(plusButton => {
            plusButton.addEventListener("click" , event => {

                let plusButtonElement = event.currentTarget;
                let countElement = plusButtonElement.previousSibling.previousSibling;
                
                this._modifyTicketCount(countElement, plusCount);
                this._setTicketTotalCount(plusCount);
                
                countElement.previousSibling.previousSibling.classList.remove("disabled");
                countElement.classList.remove("disabled");

                let ticketParentElement = plusButton.parentNode.parentNode.parentNode;
                this._setTotalPrice(ticketParentElement,countElement.value);
                changeReservationButton();
            });
        });
    },

    /**
     * @desc ticket 마이너스 버튼 
     */
    addTicketMinusButtonEvent(){

        const minusCount = -1;
        document.querySelectorAll(".minus_button").forEach(minusButton => {
            minusButton.addEventListener("click" , event => {
                
                let minusButtonElement = event.currentTarget;
                let countElement = minusButtonElement.nextSibling.nextSibling;
                
                if(countElement.value > 0){
                    this._modifyTicketCount(countElement, minusCount);
                    this._setTicketTotalCount(minusCount);
                }

                if(countElement.value == 0 ){
                    minusButtonElement.classList.add("disabled");
                    countElement.classList.add("disabled");
                }

                let ticketParentElement = minusButton.parentNode.parentNode.parentNode;
                this._setTotalPrice(ticketParentElement,countElement.value);
                changeReservationButton();
            });
        });
    },

    /**
     * @desc Ticket 갯수 수정
     * @param {DOMElement} countElement 
     * @param {String} countNumber 
     */
    _modifyTicketCount(countElement, countNumber){
        countElement.value = Number(countElement.value,10)+countNumber;
    },

    /**
     * @desc ticket 당 총 금액 계산 및 셋팅
     * @param {DOMElement} ticketParentElement 
     * @param {Number} count 
     */
    _setTotalPrice(ticketParentElement,count){

        const price = ticketParentElement.querySelector(".price").innerText.replace(/,/g,'');
        ticketParentElement.querySelector('.total_price').innerText = addCommaUtil.getCommaToNumberString((count * price).toString());
    },

    /**
     * @desc ticket당 총 개수 계산 및 셋팅
     * @param {Number} count 
     */
    _setTicketTotalCount(count){
        this.totalTicketCount += count;
        document.querySelector("#total_count").innerText = this.totalTicketCount;
    }
};

/**
 * @desc 예약정보 유효성 검사
 */
function ValidateReserveInformation(){
    this.reserve = {
        userName : "",
        phoneNumber : "",
        email : "",
        totalCount : 0,
        agreeChecked : false,
    }
}

ValidateReserveInformation.prototype = {
    
    getReserveData(){
        return this.reserve;
    },

    validateReserveUserName(){
        this.reserve.userName = document.querySelector("#name").value;
        return validate.validateName(this.reserve.userName);
    },

    validateReservePhoneNumber(){
        this.reserve.phoneNumber = document.querySelector("#tel").value;
        return validate.validatePhoneNumber(this.reserve.phoneNumber);
    },

    validateReserveEmail(){
        this.reserve.email = document.querySelector("#email").value;
        return validate.validateEmail(this.reserve.email);
    },
    
    validateReserveTotalCount(){
        this.reserve.totalCount = Number(document.querySelector("#total_count").innerText,10);
        return this.reserve.totalCount > 0;
    },

    validateCheckedAgree(){
        this.reserve.agreeChecked = document.querySelector("#chk3").checked;
        return this.reserve.agreeChecked;
    },

    validateAll(){
        return this.validateReserveUserName() && this.validateReservePhoneNumber() && this.validateReserveEmail() &&this.validateReserveTotalCount() && this.validateCheckedAgree();  
    }
}
/**
 * @desc 예약하기 관련 class
 */
function ReservateProduct(){
    this.init();
}

ReservateProduct.prototype = {
    init(){
        this.addReservationButtonEvent();
        this.addReserveInputKeyPressEvent();
        this.addAgreeCheckBoxKeyDownEvent();
        this.addAgreementButtonClickEvent();
    },

    /**
     * @desc 예약 하기 버튼 이벤트
     */
    addReservationButtonEvent(){
        document.querySelector("#reservation_button").addEventListener("click", event => {

            if(!event.currentTarget.parentNode.classList.contains('disable') 
                && globalVariables.validateReserveInformation.validateAll()){

                const reservationSendHeader = {
                    method : "POST",
                    uri : "/api/reservations"
                };

                sendAjax(reservationSendHeader,JSON.stringify(this._makeSendData()),()=>{

                    alert("예매가 성공적으로 완료 되었습니다.");
                    window.location.href = "/main";
                });
            }
        });
    },

    /**
     * @desc 이름, 전화번호, 이메일 관련 인풋박스 keypress 유효성 검사
     */
    addReserveInputKeyPressEvent(){

        ['#name','#tel','#email'].forEach( inputId => {
            document.querySelector(inputId).addEventListener("keyup", event =>{
                changeReservationButton();
            });
        });
    },

    /**
     * @desc 동의하기 체크박스 keydown 이벤트 유효성 검사
     */
    addAgreeCheckBoxKeyDownEvent(){
        document.querySelector(".chk_agree").addEventListener("change", event => {
            changeReservationButton();
        });
    },

    /**
     * @desc 동의하기 펼쳐보기 이벤트
     */
    addAgreementButtonClickEvent(){
        document.querySelectorAll(".btn_agreement").forEach( agreementButton =>{
            agreementButton.addEventListener("click", event => {
                
                const agreementButtonElement = event.currentTarget;
                const agreementButtonText = agreementButtonElement.querySelector(".btn_text");
                const agreementIcon = agreementButtonElement.querySelector(".fn");

                if(agreementButtonElement.parentNode.classList.contains("open")){

                    agreementButtonElement.parentNode.classList.remove("open");
                    agreementButtonText.innerText = "보기";
                    agreementIcon.classList.remove("fn-up2");
                    agreementIcon.classList.add("fn-down2");

                } else {

                    agreementButtonElement.parentNode.classList.add("open");
                    agreementButtonText.innerText = "접기";
                    agreementIcon.classList.remove("fn-down2");
                    agreementIcon.classList.add("fn-up2");
                    
                }
            });
        });
    },

    /**
     * @desc 전송할 데이터 만들기
     */
    _makeSendData(){
        const displayInfo = globalVariables.displayInfoReseponse;
        const reserveUserInfo = globalVariables.validateReserveInformation.getReserveData();
        const sendData = {
            "displayInfoId" : DisplayInfo.prototype.getDisplayInfoId(),
            "productId" : displayInfo.displayInfo.productId,
            "reservationEmail" : reserveUserInfo.email,
            "reservationName" : reserveUserInfo.userName,
            "reservationTelephone" : reserveUserInfo.phoneNumber,
            "reservationYearMonthDay" : dateUtil.getTodayYYYYMMDD(),
            "prices" : [] 
        };

        const qty = document.querySelectorAll(".qty");

        qty.forEach( (value, index) => {
            let count = value.querySelector(".count_control_input").value;
            let priceData = {
                "count" : count,
                "productPriceId" : displayInfo.productPrices[index].productPriceId,
            };

            if(count > 0) {
                sendData["prices"].push(priceData);
            }
        });

        return sendData;
    }
};

/**
 * @desc 예약하기 버튼 변화
 */
function changeReservationButton(){

    globalVariables.reservationButton.parentNode.classList.remove('disable');
    
    if(globalVariables.validateReserveInformation.validateAll() != true){
        globalVariables.reservationButton.parentNode.classList.add('disable');
    } 
}

document.addEventListener("DOMContentLoaded" , () => {
    const displayInfo = new DisplayInfo();
    const reservateProduct = new ReservateProduct();

    globalVariables.validateReserveInformation = new ValidateReserveInformation();
});