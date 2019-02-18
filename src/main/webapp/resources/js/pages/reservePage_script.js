/**
 * Copyright 2019 NAVER Corp. All rights reserved. Except in the case of
 * internal use for NAVER, unauthorized use of redistribution of this software
 * are strongly prohibited.
 */

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

// library mapping to use forEach
HTMLCollection.prototype.forEach = Array.prototype.forEach;
NodeList.prototype.forEach = Array.prototype.forEach;


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
        prices: {}
    },

    parser: new DOMParser(),

    init: function () {
        domElements = this.domElements;
        urls = this.urls;
        constants = this.constants;
        state = this.state;
        templates = this.templates;
        parser = this.parser;

        fetchDetailData = this.fetchDetailData;
        renderData = this.renderData;
        priceControllerInit = this.priceControllerInit;
        updateTotalCountBottom = this.updateTotalCountBottom;
        inputValidationAttachment = this.inputValidationAttachment;
        agreementShowBtnInit = this.agreementShowBtnInit;
        inputValidationErrorMsg = this.inputValidationErrorMsg;

        constants.DISPLAY_INFO_ID = new URL(window.location.href).searchParams.get("id");
        fetchDetailData();
    },

    fetchDetailData: function () {
    	xhrRequest("GET"
    			, urls.DETAIL + constants.DISPLAY_INFO_ID
    			, null
    			, (respText) => {
    	            state.detail_data = JSON.parse(respText);
    	            renderData();
    	        }, true);
    },

    renderData: function () {
        document.querySelector(".preview_txt_tit").innerHTML = state.detail_data.displayInfo.productDescription;
        document.querySelector(".title").innerHTML = state.detail_data.displayInfo.productDescription;
        document.querySelector(".img_thumb").src = state.detail_data.productImages[0].saveFileName;

        let details = document.querySelectorAll(".store_details p");
        details[0].innerHTML = "장소  : " + state.detail_data.displayInfo.placeName + "<br>";

        details[1].innerHTML = state.detail_data.displayInfo.openingHours;

        details[2].innerHTML = convertPriceArrToHtmlStr(state.detail_data);

        // add priceController with interpreted data
        arrayToElementRenderer(state.detail_data.productPrices, domElements.countControlContainer, templates.countControlItem);
        priceControllerInit();
        inputValidationAttachment();
        agreementShowBtnInit();
    },

    priceControllerInit: function () {
        let controllers = domElements.countControlContainer.querySelectorAll(".qty");
        controllers.forEach((item) => {
            var controller = new CountController(item);
        });
    },

    agreementShowBtnInit: function () {
        document.querySelectorAll(".btn_agreement").forEach((item) => {
            new EulaButton(item);
        });
    },

    inputValidationErrorMsg: function (criteria) {
        criteria.nextElementSibling.style.visibility = "visible";
        setTimeout(() => {
            criteria.nextElementSibling.style.visibility = "hidden";
        }, 2000);
    },

    inputValidationAttachment: function () {
        new SubmitButtonWithValidation(domElements.bookButtonWrapper.querySelector("button"));

        new FormWatcher();
    },
}


