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

window.addEventListener("DOMContentLoaded", function() {
	reviewPage.init();
});

var reviewPage = {
		domElements : {
			AVERAGE_SCORE_STARS : document.querySelector(".graph_value"),
			AVERAGE_SCORE_TEXT : document.querySelector(".text_value span"),
			REVIEW_AREA : document.querySelector(".list_short_review"),
			REVIEW_ITEM : document.querySelector("#commentItem").innerHTML,
			REVIEW_COUNT : document.querySelector(".green")
		},
		
		urls : {
		    DETAIL : "/reservation/api/products/"
		},
		
		constants : {
		    DISPLAY_INFO_ID : "",
		},
		
		state : {
		  detail_data : "",
		  tab_state : 1
		},
		
		parser : new DOMParser(),
		
		init : function() {
			domElements = this.domElements;
			urls = this.urls;
			constants = this.constants;
			state = this.state;
			parser = this.parser;
			
		    // parse initial id value from url;
		    var url_string = window.location.href;
		    var url = new URL(url_string);
		    constants.DISPLAY_INFO_ID = url.searchParams.get("id");
		    this.fetchDetailData();
		},
		
		fetchDetailData : function(){
		    xhrGetRequest(urls.DETAIL+constants.DISPLAY_INFO_ID,(respText) => {
		    	state.detail_data = JSON.parse(respText); 
		    	console.log(state.detail_data);
		        this.deployInformation();
		    });
		},
		
		/**
		 * @deployInformation() : put data into corresponding section
		 */
		deployInformation : function(){
		   	domElements.AVERAGE_SCORE_STARS.style.width = state.detail_data.averageScore / 5 *100 + "%";
		    domElements.AVERAGE_SCORE_TEXT.innerHTML = 	state.detail_data.averageScore;
			domElements.REVIEW_COUNT.innerHTML = state.detail_data.comments.length + "건";
		    arrayToLiRenderer(state.detail_data.comments, domElements.REVIEW_AREA, domElements.REVIEW_ITEM);
		}
}
