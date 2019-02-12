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

window.addEventListener('DOMContentLoaded', function(){
	detailPage.init();
});

var detailPage = {
	domElements : {
		slideWrapper : document.querySelector(".container_visual"),
		slideContainer : document.querySelector(".visual_img"),
		slideLeft : document.querySelector(".group_visual div div.prev"),
		slideRight : document.querySelector(".group_visual div div.nxt"),
		productContent : document.querySelector(".store_details p.dsc"),
		eventContainer : document.querySelector(".section_event"),
		averageScoreStars : document.querySelector(".graph_value"),
		averageScoreText : document.querySelector(".text_value span"),
		unfold : document.querySelector("._open"),
		fold : document.querySelector("._close"),
		figurePagination : document.querySelector(".figure_pagination"),
		reviewArea : document.querySelector(".list_short_review"),
		reviewCount : document.querySelector(".green"),
		infoTabUl : document.querySelector(".info_tab_lst"),
		infoTabLi : document.querySelectorAll(".info_tab_lst li"),
		bottomDescription : document.querySelector(".detail_info_group"),
		bottomPath : document.querySelector(".detail_location"),
		scrollToTop : document.querySelector(".gototop")
	},
	
	templates : {
		reviewItem : document.querySelector("#commentItem").innerHTML,
		slideContainerItem : document.querySelector("#imageItem").innerHTML,
	},
	
	constants : {
	    DISPLAY_INFO_ID : "",
	},
	
	urls : {
	    DETAIL : "/reservation/api/products/",
	},
	
	animation : "",
	
	state : {
	  detail_data : "",
	},
	
	parser : new DOMParser(),
	
	init : function() {
		domElements = this.domElements;
		urls = this.urls;
		constants = this.constants;
		state = this.state;
		templates = this.templates;
		parser = this.parser;
		
		initTab = this.initTab;
		fetchDetailData = this.fetchDetailData;
		renderComments = this.renderComments;
		renderEventSection = this.renderEventSection;
		renderDescription = this.renderDescription;
		renderBottomData = this.renderBottomData;
		renderImages = this.renderImages;
		resizeImageContainer = this.resizeImageContainer;
		
	    constants.DISPLAY_INFO_ID = new URL(window.location.href).searchParams.get("id");
	    fetchDetailData();
	    initTab();
	},

	initTab : function(){
		let detailTab = document.querySelector(".detail_area_wrap");
		let pathTab = document.querySelector(".detail_location");
		
		domElements.infoTabUl.addEventListener("click", (e) => {
			let currentScroll = document.documentElement.scrollTop;
			if(e.target == domElements.infoTabUl) {
				return;
			}
			
			domElements.infoTabLi.forEach((item) => {
				let iter = item.firstElementChild;
				if(iter.classList.contains("active")) {
					iter.classList.remove("active");
				}
			});
			let tab = e.target.closest("li");
			tab.firstElementChild.classList.add("active");
			
			if(tab.classList.contains("_detail")){
				detailTab.classList.remove("hide");
				pathTab.classList.add("hide");
			}
			
			else if(tab.classList.contains("_path")){
				pathTab.classList.remove("hide");
				detailTab.classList.add("hide");
			}
			document.documentElement.scrollTop = document.body.scrollTop = currentScroll;
		});
		domElements.scrollToTop.addEventListener("click", (e) => {
			document.documentElement.scrollTop = document.body.scrollTop = 0;
		});
		
	},

	fetchDetailData : function(){
	    xhrGetRequest(urls.DETAIL + constants.DISPLAY_INFO_ID,(respText) => {
	    	state.detail_data = JSON.parse(respText);

	    	renderDescription();
	        
	        renderBottomData();
	        
	        renderEventSection();
	        
	        renderImages();
	        
	        renderComments();
	    });
	},

	/**
	 * @renderComments() : calling render function to render 3 comments as
	 *                   specification mentioned, and re-establishment of more
	 *                   comments button to id;
	 */
	renderComments : function(){
	    document.querySelector(".btn_review_more").href = "./review?id=" + constants.DISPLAY_INFO_ID;
		domElements.reviewCount.innerHTML = state.detail_data.comments.length + "건";
	    arrayToLiRenderer(state.detail_data.comments.slice(0, 3), domElements.reviewArea, templates.reviewItem);
	},

	/**
	 * @renderEventSection() : if there is no event specified, remove the
	 *                       corresponding section, else put event information
	 *                       inside the section
	 */
	renderEventSection : function(){
		if(state.detail_data.displayInfo.productEvent == "") {
	    	domElements.eventContainer.innerHTML = "";
	    } else {
	    	domElements.eventContainer.querySelector(".dsc").innerHTML 
	    		= state.detail_data.displayInfo.productEvent;
	    }
	},

	/**
	 * @renderDescription() this puts description in the section right below the
	 *                      images carousel detect scroll height, and client
	 *                      height to check whether the string has more than 3
	 *                      lines. If there is less than 3 lines, hide the
	 *                      buttons.
	 */
	renderDescription : function(){
		domElements.productContent.innerHTML = state.detail_data.displayInfo.productContent;
	    if(domElements.productContent.clientHeight == domElements.productContent.scrollHeight) {
	    	domElements.unfold.style.display = "none";
	    	domElements.fold.style.display = "none";
	    } else {
	    	let descArea = domElements.productContent.closest("div");
	    	domElements.unfold.addEventListener("click" , (e) => {
	        	if(descArea.classList.contains("close3")) {
	        		descArea.classList.remove("close3");
	        	}
	        	domElements.unfold.style.display = "none";
	        	domElements.fold.style.display = "block";
	        	});
	        
	        domElements.fold.addEventListener("click", (e) => {
	            if(!descArea.classList.contains("close3")) {
	            	descArea.classList.add("close3");
	            }
	            domElements.unfold.style.display = "block";
	            domElements.fold.style.display = "none";
	        });
	    }
	},

	/**
	 * @renderBottomData() : put information into corresponding section
	 */
	renderBottomData : function(){
		 domElements.bottomDescription.querySelector(".in_dsc").innerHTML 
		 	= state.detail_data.displayInfo.productContent;
		 
		 domElements.bottomPath.querySelector(".store_map").src 
		 	= state.detail_data.displayInfoImage.saveFileName;
		 
		 domElements.bottomPath.querySelector(".store_name").innerHTML 
		 	= state.detail_data.displayInfo.productDescription;
		 
		 domElements.bottomPath.querySelector(".store_addr_bold").innerHTML 
		 	= state.detail_data.displayInfo.placeStreet;
		 
		 domElements.bottomPath.querySelector(".addr_old_detail").innerHTML 
		 	= state.detail_data.displayInfo.placeLot;
		 
		 domElements.bottomPath.querySelector(".addr_detail").innerHTML 
		 	= state.detail_data.displayInfo.placeName;
		 
		 domElements.bottomPath.querySelector(".store_tel").innerHTML 
		 	= state.detail_data.displayInfo.telephone;
		 
		 domElements.averageScoreStars.style.width 
		 	= state.detail_data.averageScore / 5 * 100 + "%";
		 
	     domElements.averageScoreText.innerHTML 
	     	= state.detail_data.averageScore;
	},

	/**
	 * @renderImages() : render and attach functionality to product images part
	 */
	renderImages : function(){
	    domElements.slideContainer.innerHTML = "";
	    
		let imageList = state.detail_data.productImages;
		
		domElements.figurePagination.querySelector(".off span").innerHTML 
			= imageList.length;
		
		arrayToLiRenderer(imageList, domElements.slideContainer, templates.slideContainerItem)
		animation = new SlidingAnimation(domElements.slideContainer);
		animation.init(false);
				
		if(imageList.length > 1) {
			domElements.slideLeft.addEventListener("click", (e) => {
				if(!animation.isAnimating) {
					animation.slide(false, true);
				}
			});
			
			domElements.slideRight.addEventListener("click", (e) => {
				if(!animation.isAnimating) {
					animation.slideReverse(false, true);
				}
			});
		} else {
			domElements.slideLeft.style.visibility = "hidden";
			domElements.slideRight.style.visibility = "hidden";
		}	
		document.querySelector(".visual_txt_tit span").innerHTML 
			= state.detail_data.displayInfo.productDescription;
	}
}
