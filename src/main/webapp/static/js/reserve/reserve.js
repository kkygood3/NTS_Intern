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

function DisplayInfo(){
    this.displayInfoId = this.getDisplayInfoId();
    this.init();
}

DisplayInfo.prototype = {
    init(){
        this.getDisplayInfo();
    },

    getDisplayInfoId(){
    	return document.querySelector("#display_info_id_div").dataset.displayinfoid;
    },

    getDisplayInfo(){

        const displayInfoHeader = {
            method: "GET",
            uri: "/api/products/"+this.displayInfoId
        };
        
        sendAjax(displayInfoHeader,'', displayInfoResponse => {
            
            globalVariables.displayInfoReseponse = displayInfoResponse;
            
            this.setTitle(displayInfoResponse.displayInfo.productDescription);
            this.setThumnailImage('/static/'+ displayInfoResponse.productImages[0].saveFileName);
            this.setProductPrices(displayInfoResponse.productPrices.reverse());
            this.setProductPricesTicket(displayInfoResponse.productPrices);
            this.setPlaceLot(displayInfoResponse.displayInfo.placeLot);
        })
    },
    
    setThumnailImage(thumnailImage){
    	document.querySelector('#img_th').src = thumnailImage;
    },
    
    setProductPrices(productPrices){
    	
    	let productPriceContent = '';
    	
    	productPrices.forEach( productPrice => {
    		productPriceContent += productPrice.priceTypeName + '석 : ' + addCommaUtil.getCommaToNumberString(productPrice.price.toString()) + ' 원 <br>';
    	});
    	
    	document.querySelector('#product_prices').innerHTML = productPriceContent;
    },

    setProductPricesTicket(productPrices){

        const ticketBody = document.querySelector(".ticket_body");

        productPrices.forEach( productPrice => {

            productPrice.price = addCommaUtil.getCommaToNumberString(productPrice.price.toString());
            ticketBody.innerHTML += handlebarsFunction.getHandlebarTemplateFromHtml("#product_ticket_template",{productPrice: productPrice});
        });

        new TicketButton();
    },

    setPlaceLot(placeLot){
        document.querySelector("#place_lot").innerHTML = placeLot;
    },

    setTitle(title){
        document.querySelector(".title").innerText = title;
    }
    
};

function TicketButton(){
    this.totalTicketCount = 0;
    this.init();
};

TicketButton.prototype = {
    init(){
        this.addTicketMinusButtonEvent();
        this.addTicketPlusButtonEvent();
    },

    addTicketPlusButtonEvent(){

        const plusCount = 1;
        document.querySelectorAll(".plus_button").forEach(plusButton => {
            plusButton.addEventListener("click" , event => {

                let plusButtonElement = event.currentTarget;
                let countElement = plusButtonElement.previousSibling.previousSibling;
                
                this.modifyTicketCount(countElement, plusCount);
                this.setTicketTotalCount(plusCount);
                
                countElement.previousSibling.previousSibling.classList.remove("disabled");
                countElement.classList.remove("disabled");

                let ticketParentElement = plusButton.parentNode.parentNode.parentNode;
                this.setTotalPrice(ticketParentElement,countElement.value);
                changeReservationButton();
            });
        });
    },

    addTicketMinusButtonEvent(){

        const minusCount = -1;
        document.querySelectorAll(".minus_button").forEach(minusButton => {
            minusButton.addEventListener("click" , event => {
                
                let minusButtonElement = event.currentTarget;
                let countElement = minusButtonElement.nextSibling.nextSibling;
                
                if(countElement.value > 0){
                    this.modifyTicketCount(countElement, minusCount);
                    this.setTicketTotalCount(minusCount);
                }

                if(countElement.value == 0 ){
                    minusButtonElement.classList.add("disabled");
                    countElement.classList.add("disabled");
                }

                let ticketParentElement = minusButton.parentNode.parentNode.parentNode;
                this.setTotalPrice(ticketParentElement,countElement.value);
                changeReservationButton();
            });
        });
    },

    modifyTicketCount(countElement, countNumber){
        countElement.value = Number(countElement.value,10)+countNumber;
    },

    setTotalPrice(ticketParentElement,count){

        const price = ticketParentElement.querySelector(".price").innerText.replace(/,/g,'');
        ticketParentElement.querySelector('.total_price').innerText = addCommaUtil.getCommaToNumberString((count * price).toString());
    },

    setTicketTotalCount(count){
        this.totalTicketCount += count;
        document.querySelector("#total_count").innerText = this.totalTicketCount;
    }
};

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

    addReservationButtonEvent(){
        document.querySelector("#reservation_button").addEventListener("click", event => {

            if(!event.currentTarget.parentNode.classList.contains('disable') 
                && globalVariables.validateReserveInformation.validateAll()){

                const reservationSendHeader = {
                    method : "POST",
                    uri : "/api/reservations"
                };

                sendAjax(reservationSendHeader,JSON.stringify(this.makeSendData()),()=>{

                    alert("예매가 성공적으로 완료 되었습니다.");
                    window.location.href = "/main";
                });
            }
        });
    },

    addReserveInputKeyPressEvent(){

        ['#name','#tel','#email'].forEach( inputId => {
            document.querySelector(inputId).addEventListener("keyup", event =>{
                changeReservationButton();
            });
        });
    },

    addAgreeCheckBoxKeyDownEvent(){
        document.querySelector(".chk_agree").addEventListener("change", event => {
            changeReservationButton();
        });
    },

    addAgreementButtonClickEvent(){
        document.querySelectorAll(".btn_agreement").forEach( agreementButton =>{
            agreementButton.addEventListener("click", event => {
                event.currentTarget.parentNode.classList.add("open");
            });
        });
    },

    makeSendData(){
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