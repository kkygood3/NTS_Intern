/**
 *
 */

// library mapping to use forEach
HTMLCollection.prototype.forEach = Array.prototype.forEach;
NodeList.prototype.forEach = Array.prototype.forEach;

this.initSlideAnimation = initSlideAnimation.bind(this);
this.slideAnimation = slideAnimation.bind(this);
this.slideAnimationReverse = slideAnimationReverse.bind(this);

var domElements = {
	SLIDE_CONTAINER : document.querySelector("ul.visual_img"),
	SLIDE_CONTAINER_ITEM : document.querySelector("#imageItem").innerHTML,
	SLIDE_LEFT : document.querySelector("div.group_visual div div.prev"),
	SLIDE_RIGHT : document.querySelector("div.group_visual div div.nxt"),
	PRODUCT_CONTENT : document.querySelector("div.store_details p.dsc"),
	EVENT_CONTAINER : document.querySelector("div.section_event"),
	AVERAGE_SCORE_STARS : document.querySelector("em.graph_value"),
	AVERAGE_SCORE_TEXT : document.querySelector("strong.text_value span"),
	UNFOLD : document.querySelector("a._open"),
	FOLD : document. querySelector("a._close")
};

var urls = {
    DETAIL : "/reservation/api/products/",
};

var constants = {
    DISPLAY_INFO_ID : "",
    SLIDE_CONATINER_WIDTH : "",
	ANIMATION_SPEED : 4,
};

var state = {
  DETAIL_DATA : ""
}

var parser = new DOMParser();

function init() {
    // parse initial id value from url;
    var url_string = window.location.href;
    var url = new URL(url_string);
    constants.DISPLAY_INFO_ID = url.searchParams.get("id");
    fetchDetailData();
}

function fetchDetailData(){
    xhrGetRequest(urls.DETAIL+constants.DISPLAY_INFO_ID,(respText) => {
    	// redefinition of slide speed since this may be different by pages
    	this.constants.ANIMATION_SPEED = 10;
        state.DETAIL_DATA = JSON.parse(respText);
        console.log(state.DETAIL_DATA);
        
        domElements.SLIDE_CONTAINER.innerHTML = "";
        domElements.PRODUCT_CONTENT.innerHTML = state.DETAIL_DATA.displayInfo.productContent;
        renderImages();
        
        if(state.DETAIL_DATA.displayInfo.productEvent == "") {
        	domElements.EVENT_CONTAINER.innerHTML = "";
        } else {
        	domElements.EVENT_CONTAINER.querySelector("div_dsc").innerHTML = state.DETAIL_DATA.displayInfo.productEvent;
        }
        
        domElements.AVERAGE_SCORE_STARS.style.width = state.DETAIL_DATA.averageScore/5 *100 + "%";
        domElements.AVERAGE_SCORE_TEXT.innerHTML = 	state.DETAIL_DATA.averageScore;
    });
}

function renderImages(){
	let bindTemplate = Handlebars.compile(domElements.SLIDE_CONTAINER_ITEM);
	let imageList = state.DETAIL_DATA.productImages;
	imageList.forEach((item) => {
		let newImageItem = parser.parseFromString(bindTemplate(item),"text/html").body.firstChild;
		domElements.SLIDE_CONTAINER.appendChild(newImageItem);
	});
	initSlideAnimation(domElements.SLIDE_CONTAINER,false);
	if(imageList.length>1) {
		domElements.SLIDE_LEFT.addEventListener("click" , (e) => {
			if(!state.isAnimating) {
				slideAnimation(false);
			}
		});
		
		domElements.SLIDE_RIGHT.addEventListener("click" , (e) => {
			if(!state.isAnimating) {
				slideAnimationReverse(false);
			}
		});
		
	} else {
		domElements.SLIDE_LEFT.style.visibility = "hidden";
		domElements.SLIDE_RIGHT.style.visibility = "hidden";
	}
}
