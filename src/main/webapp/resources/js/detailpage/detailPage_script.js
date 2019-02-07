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

this.initSlideAnimation = initSlideAnimation.bind(this);
this.slideAnimation = slideAnimation.bind(this);
this.slideAnimationReverse = slideAnimationReverse.bind(this);
this.arrayToLiRenderer = arrayToLiRenderer.bind(this);

const domElements = {
	SLIDE_WRAPPER : document.querySelector("div.container_visual"),
	SLIDE_CONTAINER : document.querySelector("ul.visual_img"),
	SLIDE_CONTAINER_ITEM : document.querySelector("#imageItem").innerHTML,
	SLIDE_LEFT : document.querySelector("div.group_visual div div.prev"),
	SLIDE_RIGHT : document.querySelector("div.group_visual div div.nxt"),
	PRODUCT_CONTENT : document.querySelector("div.store_details p.dsc"),
	EVENT_CONTAINER : document.querySelector("div.section_event"),
	AVERAGE_SCORE_STARS : document.querySelector("em.graph_value"),
	AVERAGE_SCORE_TEXT : document.querySelector("strong.text_value span"),
	UNFOLD : document.querySelector("a._open"),
	FOLD : document.querySelector("a._close"),
	FIGURE_PAGINATION : document.querySelector("div.figure_pagination"),
	REVIEW_AREA : document.querySelector("ul.list_short_review"),
	REVIEW_ITEM : document.querySelector("#commentItem").innerHTML,
	REVIEW_COUNT : document.querySelector("em.green"),
	INFO_TAB_UL : document.querySelector("ul.info_tab_lst"),
	INFO_TAB_LI : document.querySelectorAll("ul.info_tab_lst li"),
	BOTTOM_DESCRIPTION : document.querySelector("ul.detail_info_group"),
	BOTTOM_PATH : document.querySelector("div.detail_location")
};

var urls = {
    DETAIL : "/reservation/api/products/",
};

var constants = {
    DISPLAY_INFO_ID : "",
    SLIDE_CONATINER_WIDTH : "",
	ANIMATION_SPEED : 4
};

var state = {
  detail_data : "",
  tab_state : 1
}

var parser = new DOMParser();

function init() {
    // parse initial id value from url;
    var url_string = window.location.href;
    var url = new URL(url_string);
    constants.DISPLAY_INFO_ID = url.searchParams.get("id");
    fetchDetailData();
    initTab();
}

function initTab(){
	domElements.INFO_TAB_UL.addEventListener("click", (e) => {
		let currentScroll = document.documentElement.scrollTop;
		if(e.target == domElements.INFO_TAB_UL) {
			return;
		}
		
		domElements.INFO_TAB_LI.forEach((item) => {
			let iter = item.firstElementChild;
			if(iter.classList.contains("active")) {
				iter.classList.remove("active");
			}
		});
		let tab = e.target.closest("li");
		tab.firstElementChild.classList.add("active");
		
		if(tab.classList.contains("_detail")){
			document.querySelector("div.detail_area_wrap").classList.remove("hide");
			document.querySelector("div.detail_location").classList.add("hide");
		}
		else if(tab.classList.contains("_path")){
			document.querySelector("div.detail_location").classList.remove("hide");
			document.querySelector("div.detail_area_wrap").classList.add("hide");
		}
		document.documentElement.scrollTop = document.body.scrollTop = currentScroll;
	});
}

function fetchDetailData(){
    xhrGetRequest(urls.DETAIL + constants.DISPLAY_INFO_ID,(respText) => {
    	state.detail_data = JSON.parse(respText);
    	// redefinition of slide speed since this may be different by pages
    	constants.ANIMATION_SPEED = 10;
        
        deployDescription();
        
        deployBottomData();
        
        deployEventSection();
        
        deployImages();
        
        deployComments();
    });
}

/**
 * @deployComments() : calling render function to render 3 comments as
 *                   specification mentioned, and re-establishment of more
 *                   comments button to id;
 */
function deployComments(){
    document.querySelector("a.btn_review_more").href="./review?id=" + constants.DISPLAY_INFO_ID;
	domElements.REVIEW_COUNT.innerHTML = state.detail_data.comments.length + "건";
    arrayToLiRenderer(state.detail_data.comments.slice(0, 3), domElements.REVIEW_AREA, domElements.REVIEW_ITEM);
}

/**
 * @deployEventSection() : if there is no event specified, remove the
 *                       corresponding section, else put event information
 *                       inside the section
 */
function deployEventSection(){
	if(state.detail_data.displayInfo.productEvent == "") {
    	domElements.EVENT_CONTAINER.innerHTML = "";
    } else {
    	domElements.EVENT_CONTAINER.querySelector("div_dsc").innerHTML 
    		= state.detail_data.displayInfo.productEvent;
    }
}

/**
 * @deployDescription() this puts description in the section right below the
 *                      images carousel detect scroll height, and client height
 *                      to check whether the string has more than 3 lines. If
 *                      there is less than 3 lines, hide the buttons.
 */
function deployDescription(){
	domElements.PRODUCT_CONTENT.innerHTML = state.detail_data.displayInfo.productContent;
    if(domElements.PRODUCT_CONTENT.clientHeight == domElements.PRODUCT_CONTENT.scrollHeight) {
    	domElements.UNFOLD.style.display = "none";
    	domElements.FOLD.style.display = "none";
    } else{
    	domElements.UNFOLD.addEventListener("click" , (e) => {
        	if(domElements.PRODUCT_CONTENT.closest("div").classList.contains("close3")) {
        		domElements.PRODUCT_CONTENT.closest("div").classList.remove("close3");
        	}
        	domElements.UNFOLD.style.display = "none";
        	domElements.FOLD.style.display = "block";
        	});
        
        domElements.FOLD.addEventListener("click", (e) => {
            if(!domElements.PRODUCT_CONTENT.closest("div").classList.contains("close3")) {
                domElements.PRODUCT_CONTENT.closest("div").classList.add("close3");
            }
            domElements.UNFOLD.style.display = "block";
            domElements.FOLD.style.display = "none";
        });
    }
}

/**
 * @deployBottomData() : put information into corresponding section
 */
function deployBottomData(){
	 domElements.BOTTOM_DESCRIPTION.querySelector("p.in_dsc").innerHTML 
	 	= state.detail_data.displayInfo.productContent;
	 
	 domElements.BOTTOM_PATH.querySelector("img.store_map").src 
	 	= state.detail_data.displayInfoImage.saveFileName;
	 
	 domElements.BOTTOM_PATH.querySelector("h3.store_name").innerHTML 
	 	= state.detail_data.displayInfo.productDescription;
	 
	 domElements.BOTTOM_PATH.querySelector("p.store_addr_bold").innerHTML 
	 	= state.detail_data.displayInfo.placeStreet;
	 
	 domElements.BOTTOM_PATH.querySelector("span.addr_old_detail").innerHTML 
	 	= state.detail_data.displayInfo.placeLot;
	 
	 domElements.BOTTOM_PATH.querySelector("p.addr_detail").innerHTML 
	 	= state.detail_data.displayInfo.placeName;
	 
	 domElements.BOTTOM_PATH.querySelector("a.store_tel").innerHTML 
	 	= state.detail_data.displayInfo.telephone;
	 
	 domElements.AVERAGE_SCORE_STARS.style.width = state.detail_data.averageScore / 5 * 100 + "%";
	 
     domElements.AVERAGE_SCORE_TEXT.innerHTML = state.detail_data.averageScore;
}

/**
 * @deployImages() : render and attach functionality to product images part
 */
function deployImages(){
    domElements.SLIDE_CONTAINER.innerHTML = "";
    
	let imageList = state.detail_data.productImages;
	domElements.FIGURE_PAGINATION.querySelector("span.off span").innerHTML 
		= imageList.length;
	
	arrayToLiRenderer(imageList, domElements.SLIDE_CONTAINER, domElements.SLIDE_CONTAINER_ITEM)

	initSlideAnimation(domElements.SLIDE_CONTAINER, false);
	
	if(imageList.length > 1) {
		domElements.SLIDE_LEFT.addEventListener("click", (e) => {
			if(!state.isAnimating) {
				slideAnimation(false);
				resizeImageContainer(state.nextSlideCount);
			}
		});
		
		domElements.SLIDE_RIGHT.addEventListener("click", (e) => {
			if(!state.isAnimating) {
				slideAnimationReverse(false);
				resizeImageContainer(state.prevSlideCount);
			}
		});
	} else {
		domElements.SLIDE_LEFT.style.visibility = "hidden";
		domElements.SLIDE_RIGHT.style.visibility = "hidden";
	}	
	document.querySelector("h2.visual_txt_tit span").innerHTML 
		= state.detail_data.displayInfo.productDescription;
}

/**
 * @resizeImageContainer(countTarget) : countTarget represents the next target
 *                                    image index in state.IMAGE_LIST, and set
 *                                    the height of the image container with
 *                                    height obtained from the slide
 */
function resizeImageContainer(countTarget){
	if(state.IMAGE_LIST[countTarget].clientHeight > 414) {
		domElements.SLIDE_WRAPPER.style.height = "414px";
	} else {
		domElements.SLIDE_WRAPPER.style.height = state.IMAGE_LIST[countTarget].clientHeight + "px";
	}
}
