/**
 * Copyright 2019 NAVER Corp. All rights reserved. Except in the case of
 * internal use for NAVER, unauthorized use of redistribution of this software
 * are strongly prohibited.
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */


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

    init: function () {
        // parse initial id value from url;
    	this.constants.DISPLAY_INFO_ID = new URL(window.location.href).searchParams.get("id");
        this.fetchDetailData();

        scrollToTopAttacher(this.domElements.scrollToTop)
    },

    fetchDetailData: function () {
    	let request = new XhrRequest("GET", this.urls.DETAIL + this.constants.DISPLAY_INFO_ID);
    	request.setCallback((respText) => {
    		this.state.detail_data = JSON.parse(respText);
    		console.log(this.state.detail_data);
    		this.renderInformation();
    	});
    	request.send();
    },

    /**
	 * @renderInformation() : put data into corresponding section
	 */
    renderInformation: function () {
    	this.domElements.averageScoreStars.style.width = this.state.detail_data.averageScore / 5 * 100 + "%";
    	this.domElements.averageScoreText.innerHTML = this.state.detail_data.averageScore;
    	this.domElements.reviewCount.innerHTML = this.state.detail_data.comments.length + "건";
        arrayToElementRenderer(this.state.detail_data.comments, this.domElements.reviewArea, this.templates.reviewItem, 
        	{
        		productName : this.state.detail_data.displayInfo.productDescription
        	}
        );
    }
}
