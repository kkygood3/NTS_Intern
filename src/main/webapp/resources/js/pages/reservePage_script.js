/**
 * Copyright 2019 NAVER Corp. All rights reserved. Except in the case of
 * internal use for NAVER, unauthorized use of redistribution of this software
 * are strongly prohibited.
 */

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

window.addEventListener("DOMContentLoaded", function () {
	reservePage.init();
});

var reservePage = {
    domElements: {
        countControlContainer: document.querySelector(".ticket_body"),
        bookButtonWrapper: document.querySelector(".bk_btn_wrap"),
        agreementButton: document.querySelector(".chk_agree"),
        reservationForm: document.querySelector(".form_horizontal")
    },

    constants: {
        DISPLAY_INFO_ID: "",
    },

    urls: {
        DETAIL: "/reservation/api/products/",
    },

    templates: {
        countControlItem: document.querySelector("#countControlItem").innerHTML
    },

    state: {
        detail_data: "",
        pricingType: TICKET_TYPES,
        prices: {},
        priceDataArr : []
    },

    
    init: function () {
        this.constants.DISPLAY_INFO_ID = new URL(window.location.href).searchParams.get("id");
        this.fetchDetailData();
    },

    fetchDetailData: function () {
        let request = new XhrRequest("GET", this.urls.DETAIL + this.constants.DISPLAY_INFO_ID);
        request.setCallback( (respText) => {
        	this.state.detail_data = JSON.parse(respText);
        	this.renderData();
        });
        request.send(null);
    },

    renderData: function () {
        document.querySelector(".preview_txt_tit").innerHTML = this.state.detail_data.displayInfo.productDescription;
        document.querySelector(".title").innerHTML = this.state.detail_data.displayInfo.productDescription;
        document.querySelector(".img_thumb").src = this.state.detail_data.productImages[0].saveFileName;

        let details = document.querySelectorAll(".store_details p");
        details[0].innerHTML = "장소  : " + this.state.detail_data.displayInfo.placeName + "<br>";

        details[1].innerHTML = this.state.detail_data.displayInfo.openingHours;

        details[2].innerHTML = mapPriceData(this.state.detail_data, this.state.prices);

        // add priceController with interpreted data
        arrayToElementRenderer(this.state.detail_data.productPrices, this.domElements.countControlContainer, this.templates.countControlItem);
        this.priceControllerInit();
        this.attachFormValidation();
        this.agreementShowBtnInit();
    },
    
    

    priceControllerInit: function () {
        let controllers = this.domElements.countControlContainer.querySelectorAll(".qty");
        controllers.forEach((item) => {
            var controller = new CountController(item, this.state.prices);
        });
        this.priceControllerWatcherInit();
    },
    
    priceControllerWatcherInit: function () {
        let totalCountBottom = document.querySelector("#totalCount");
        // object watcher attached for updating bottom count
        for (var key in this.state.prices) {
        	this.state.prices[key] = Object.observe(this.state.prices[key], () => {
        		this.inputValidate();
                let totalCount = 0;
                for (var key in this.state.prices) {
                    var value = this.state.prices[key];
                    totalCount += value.count;
                }
                totalCountBottom.innerHTML = totalCount;
                return true;
            })
        }
    },

    agreementShowBtnInit: function () {
        document.querySelectorAll(".btn_agreement").forEach((item) => {
            new EulaButton(item);
        });
    },

    inputValidationErrorMsg: function (criteria) {
        criteria.nextElementSibling.style.visibility = "visible";
        setTimeout(() => {
            criteria.nextElementSibling.style.visibility = "";
        }, 2000);
    },

    attachFormValidation: function () {
        new SubmitButtonWithValidation(this.domElements.bookButtonWrapper.querySelector("button")
        		, this.inputValidationErrorMsg
        		, this.state);
        this.domElements.reservationForm.addEventListener("change", (e) => {
        	this.inputValidate();
        });
    },
    inputValidate : function(){
        let bookButton = this.domElements.bookButtonWrapper;
    	// tickets count check
    	this.state.priceDataArr = []
        for (var key in this.state.prices) {
            if (this.state.prices[key].count > 0) {
            	this.state.priceDataArr.push(this.state.prices[key]);
            }
        }
        
        let formData = new FormData(this.domElements.reservationForm);
        
        if (formData.get("email").length > 0
            && formData.get("rsvname").length > 0
            && formData.get("tel").length > 0
            && formData.get("agreement") =="on"
            && this.state.priceDataArr.length > 0) {
            if (bookButton.classList.contains("disable")) {
                bookButton.classList.remove("disable");
            }
        } else {
            bookButton.classList.add("disable");
        }
    }
}


