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

window.addEventListener('DOMContentLoaded', function() {
	reservePage.init();
});

var reservePage = {
	domElements : {

	},

	constants : {
		DISPLAY_INFO_ID : "",
	},

	urls : {
		DETAIL : "/reservation/api/products/",
	},

	state : {
		detail_data : "",
	},

	parser : new DOMParser(),

	init : function() {
		domElements = this.domElements;
		urls = this.urls;
		constants = this.constants;
		state = this.state;
		parser = this.parser;
	},

}
