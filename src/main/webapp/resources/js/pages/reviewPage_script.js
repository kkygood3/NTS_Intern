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
    reviewPage.init();
});

var reviewPage = {
    domElements: {
        averageScoreStars: document.querySelector(".graph_value"),
        averageScoreText: document.querySelector(".text_value span"),
        reviewArea: document.querySelector(".list_short_review"),
        reviewCount: document.querySelector(".green"),
        scrollToTop: document.querySelector(".gototop")
    },

    templates: {
        reviewItem: document.querySelector("#commentItem").innerHTML,
    },

    urls: {
        DETAIL: "/reservation/api/products/"
    },

    constants: {
        DISPLAY_INFO_ID: "",
    },

    state: {
        detail_data: "",
        tab_state: 1
    },

    parser: new DOMParser(),

    init: function () {
        domElements = this.domElements;
        urls = this.urls;
        constants = this.constants;
        state = this.state;
        templates = this.templates;
        parser = this.parser;

        // parse initial id value from url;
        constants.DISPLAY_INFO_ID = new URL(window.location.href).searchParams.get("id");
        this.fetchDetailData();

        scrollToTopAttacher(domElements.scrollToTop)
    },

    fetchDetailData: function () {
        xhrRequest("GET"
            , urls.DETAIL + constants.DISPLAY_INFO_ID
            , null
            , (respText) => {
                state.detail_data = JSON.parse(respText);
                this.renderInformation();
            }, true);
    },

    /**
     * @renderInformation() : put data into corresponding section
     */
    renderInformation: function () {
        domElements.averageScoreStars.style.width = state.detail_data.averageScore / 5 * 100 + "%";
        domElements.averageScoreText.innerHTML = state.detail_data.averageScore;
        domElements.reviewCount.innerHTML = state.detail_data.comments.length + "건";
        arrayToElementRenderer(state.detail_data.comments, domElements.reviewArea, templates.reviewItem);
    }
}
