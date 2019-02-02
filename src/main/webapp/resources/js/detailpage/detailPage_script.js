/**
 *
 */

// library mapping to use forEach
HTMLCollection.prototype.forEach = Array.prototype.forEach;
NodeList.prototype.forEach = Array.prototype.forEach;

this.initSlideAnimation = initSlideAnimation.bind(this);
this.slideAnimation = slideAnimation.bind(this);

var domElements = {
	SLIDE_CONTAINER : document.querySelector("ul.visual_img"),
	SLIDE_CONTAINER_ITEM : document.querySelector("#imageItem").innerHTML
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
    console.log(url.searchParams.get("id"));
    constants.DISPLAY_INFO_ID = url.searchParams.get("id");
    fetchDetailData();
}

function fetchDetailData(){
    xhrGetRequest(urls.DETAIL+constants.DISPLAY_INFO_ID,(respText) => {
        state.DETAIL_DATA = JSON.parse(respText);
        domElements.SLIDE_CONTAINER.innerHTML = "";
        renderImages();
    });
}

function renderImages(){
	let bindTemplate = Handlebars.compile(domElements.SLIDE_CONTAINER_ITEM);
	console.log(state.DETAIL_DATA.productImages);
	state.DETAIL_DATA.productImages.forEach((item) => {
		let newImageItem = parser.parseFromString(bindTemplate(item),"text/html").body.firstChild;
		domElements.SLIDE_CONTAINER.appendChild(newImageItem);
	});
	initSlideAnimation(domElements.SLIDE_CONTAINER,false);
}
