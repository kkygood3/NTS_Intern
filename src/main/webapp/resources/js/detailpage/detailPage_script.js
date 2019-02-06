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
	FOLD : document.querySelector("a._close"),
	FIGURE_PAGINATION : document.querySelector("div.figure_pagination"),
	REVIEW_AREA : document.querySelector("ul.list_short_review"),
	REVIEW_ITEM : document.querySelector("#commentItem").innerHTML,
	REVIEW_COUNT : document.querySelector("em.green")
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
        state.detail_data = JSON.parse(respText);
        
        domElements.PRODUCT_CONTENT.innerHTML = state.detail_data.displayInfo.productContent;
        
        domElements.UNFOLD.addEventListener("click" , (e) => {
            if(domElements.PRODUCT_CONTENT.closest("div").classList.contains("close3")) {
                domElements.PRODUCT_CONTENT.closest("div").classList.remove("close3");
            }
            domElements.UNFOLD.style.display = "none";
            domElements.FOLD.style.display = "block";
        });
        
        domElements.FOLD.addEventListener("click" , (e) => {
            if(!domElements.PRODUCT_CONTENT.closest("div").classList.contains("close3")) {
                domElements.PRODUCT_CONTENT.closest("div").classList.add("close3");
            }
            domElements.UNFOLD.style.display = "block";
            domElements.FOLD.style.display = "none";
        });
        
        domElements.SLIDE_CONTAINER.innerHTML = "";
        renderImages();
        console.log(state.detail_data);
        
        domElements.REVIEW_COUNT.innerHTML = state.detail_data.comments.length + "건";
        renderThreeComments();
        //Event Description attachment
        if(state.detail_data.displayInfo.productEvent == "") {
        	domElements.EVENT_CONTAINER.innerHTML = "";
        } else {
        	domElements.EVENT_CONTAINER.querySelector("div_dsc").innerHTML = state.detail_data.displayInfo.productEvent;
        }
        
        domElements.AVERAGE_SCORE_STARS.style.width = state.detail_data.averageScore/5 *100 + "%";
        domElements.AVERAGE_SCORE_TEXT.innerHTML = 	state.detail_data.averageScore;
    });
}

function renderImages(){
	let bindTemplate = Handlebars.compile(domElements.SLIDE_CONTAINER_ITEM);
	let imageList = state.detail_data.productImages;
	domElements.FIGURE_PAGINATION.querySelector("span.off span").innerHTML = imageList.length;
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

function renderThreeComments(){
    let bindTemplate = Handlebars.compile(domElements.REVIEW_ITEM);
    let commentsThreeList = state.detail_data.comments.slice(0,3);
    let newCommentItems = parser.parseFromString(bindTemplate({data : commentsThreeList}),"text/html").querySelectorAll("li");
    
    newCommentItems.forEach((item) => {
        domElements.REVIEW_AREA.append(item);
    });
}
