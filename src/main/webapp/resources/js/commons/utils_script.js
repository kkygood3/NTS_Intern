/**
 * Copyright 2019 NAVER Corp. All rights reserved. Except in the case of
 * internal use for NAVER, unauthorized use of redistribution of this software
 * are strongly prohibited.
 */

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

function SlidingAnimation(_slideContainer){
	slideContainer = _slideContainer;
	slideWrapper = slideContainer.closest("div");
	
	imageList = slideContainer.getElementsByTagName("li");
	SLIDE_CONATINER_WIDTH = slideContainer.offsetWidth;	
}

/**
 * @initSlideAnimation() : required setup for the promo animation, and
 *                       initialization of animation frame call
 */
SlidingAnimation.prototype.init = function(params) {	
	prevSlideCount = imageList.length - 1;	
	currentSlideCount = 0;
	nextSlideCount = 1;
	isAnimating = false;
	
	maxHeight = params && params.maxHeight ? params.maxHeight : slideWrapper.clientHeight;
	minHeight = params && params.minHeight ? params.minHeight : 100;
	isAutoStart = false;
	animationSpeed = params && params.animationSpeed ? params.animationSpeed : 10;
	animationStopDuration = params && params.animationStopDuration ? params.animationStopDuration : 1000;
		
	imageList.forEach((item) => {
		if(item == imageList[0]) {
			imageList[0].style.left = 0 + "px";
		} else {
			item.style.left = SLIDE_CONATINER_WIDTH + "px";
		}
		item.style.position = "absolute";
	});
},

SlidingAnimation.prototype.startAutoAnimation = function(){
	isAutoStart = true;
	if(isAutoStart) {
		setTimeout(() => {
			requestAnimationFrame(() => this.slide(true));
		}, animationStopDuration);
	}
}

SlidingAnimation.prototype.changeTiming = function(_animationSpeed, _stopDuration){
	animationSpeed = _animationSpeed;
	animationStopDuration = _stopDuration;
}

SlidingAnimation.prototype.resizeMinMax = function(_minHeight, _maxHeight){
	maxHeight = _maxHeight;
	minHeight = _minHeight;
}
/**
 * @constants.animationSpeed : to control the speed or animation, declared as
 *                           const in global variable
 * 
 * @needToStop : this boolean indicates when the element is arrived in the right
 *             position to be displayed
 * 
 * @constants.animationStopDuration : in milliseconds, determines the stop
 *                                  duration of the animation when the image
 *                                  arrives in the right position , declared as
 *                                  const in global variable
 * 
 * @promoAnimation() : promotion animation with 2 for loops to control the
 *                   accuracy of the stop-position
 * 
 * @isAutoStart : parameter to control auto-slide animation, if false, manual
 */
SlidingAnimation.prototype.slide = function(isAutoStart, isResizing) {
	this.isAnimating = true;
	
	let needToStop = false;
	let currentImage = imageList[currentSlideCount];
	let nextImage = imageList[nextSlideCount];
		
	if(isResizing){
		this.resizeImageContainer(nextImage);
	}
	
	if(parseInt(nextImage.style.left) == -SLIDE_CONATINER_WIDTH) {
		nextImage.style.left = SLIDE_CONATINER_WIDTH + "px";
	}
	
	for(let iter = 0; iter < animationSpeed; iter++) {
		currentImage.style.left = parseInt(currentImage.style.left) - 1 + "px";
		nextImage.style.left = parseInt(nextImage.style.left) - 1 + "px";
		
		if(parseInt(currentImage.style.left) <= -SLIDE_CONATINER_WIDTH) {
			
			currentImage.style.left = SLIDE_CONATINER_WIDTH + "px";
			
			prevSlideCount = currentSlideCount;
			currentSlideCount = nextSlideCount;
			nextSlideCount++;
			
			if(nextSlideCount == imageList.length) {
				nextSlideCount = 0;
			}
			needToStop = true;
			break;
		}
	}
	if(needToStop) {
		if(isAutoStart) {
			setTimeout(() => {
				requestAnimationFrame(() => this.slide(isAutoStart));
			}, animationStopDuration);
		} else {
			this.isAnimating = false;
		}
	} else {
		requestAnimationFrame(() => this.slide(isAutoStart));
	}	
},

/**
 * @constants.animationSpeed : to control the speed or animation, declared as
 *                           const in global variable
 * 
 * @needToStop : this boolean indicates when the element is arrived in the right
 *             position to be displayed
 * 
 * @constants.animationStopDuration : in milliseconds, determines the stop
 *                                  duration of the animation when the image
 *                                  arrives in the right position , declared as
 *                                  const in global variable
 * 
 * @promoAnimation() : promotion animation with 2 for loops to control the
 *                   accuracy of the stop-position
 * 
 * @isAutoStart : parameter to control auto-slide animation, if false, manual
 * 
 * @slideAnimationReverse(isAutoStart) diff with slideAnimation => direction of
 *                                     animation and counting system are
 *                                     different, as slideAnimation slides with
 *                                     asc order, Reverse goes desc order
 */
SlidingAnimation.prototype.slideReverse = function(isAutoStart, isResizing) {
	this.isAnimating = true;
	
	let needToStop = false;
	let prevImage = imageList[prevSlideCount];
	let currentImage = imageList[currentSlideCount];
	
	if(isResizing){
		this.resizeImageContainer(prevImage);
	}
	
	if(parseInt(prevImage.style.left) == SLIDE_CONATINER_WIDTH) {
		prevImage.style.left = -SLIDE_CONATINER_WIDTH + "px";
	}
	
	for(let iter = 0; iter < animationSpeed; iter++) {
		currentImage.style.left = parseInt(currentImage.style.left) + 1 + "px";
		prevImage.style.left = parseInt(prevImage.style.left) + 1 + "px";
		
		if(parseInt(currentImage.style.left) >= SLIDE_CONATINER_WIDTH) {
			
			currentImage.style.left = -SLIDE_CONATINER_WIDTH + "px";
			
			nextSlideCount = currentSlideCount;
			currentSlideCount = prevSlideCount;
			prevSlideCount = prevSlideCount - 1;
			
			if(prevSlideCount == -1){
				prevSlideCount = imageList.length - 1;
			}
			
			needToStop = true;
			break;
		}
	}
	if(needToStop) {
		if(isAutoStart) {
			setTimeout(() => {
				requestAnimationFrame(() => this.slideReverse(isAutoStart));
			}, animationStopDuration);
		} else {
			this.isAnimating = false;
		}
	} else {
		requestAnimationFrame(() =>this.slideReverse(isAutoStart));
	}
}

/**
 * @resizeImageContainer(countTarget) : countTarget represents the next target
 *                                    image index in state.imageList, and set
 *                                    the height of the image container with
 *                                    height obtained from the slide
 */
SlidingAnimation.prototype.resizeImageContainer= function(target, maxHeight, minHeight){
	if(target.clientHeight > SLIDE_CONATINER_WIDTH) {
		slideWrapper.style.height = SLIDE_CONATINER_WIDTH+"px";
	} else {
		slideWrapper.style.height = target.clientHeight + "px";
	}
}

/**
 * @xhrGetRequest() : pre-defined XmlHttpRequest Get method since get method
 *                  will be used frequently
 */
function xhrGetRequest(url, callback) {
	let xhr = new XMLHttpRequest();
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
	xhr.onreadystatechange = function(aEvt) {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				callback(xhr.responseText);
			} else {
				alert("Error fetching");
			}
		}
	};
	xhr.send();
}

/**
 * @data: data to be rendered, and mapped with handlebar, in array type.
 * 
 * @target: dom element where the rendered item should be attached.
 * 
 * @item : html template in string type
 */
function arrayToLiRenderer(data, target, item) {
	let bindTemplate = Handlebars.compile(item);
	let commentsList = data;
	
	Handlebars.registerHelper("productName", () => {
		return state.detail_data.displayInfo.productDescription;
	});
	Handlebars.registerHelper("date", (item) => {
		return item.split(" ")[0];
	});
	Handlebars.registerHelper("scoreToDouble", (item) => {
		return item.toFixed(1);
	});
	Handlebars.registerHelper("isImagePresent", (item) => {
		return item.length>0 ? "" : "no_img";
	});
	Handlebars.registerHelper("commentImageUrl", (item)=> {
		return item[0].saveFileName;
	});
	
	let newCommentItems = 
		parser.parseFromString(bindTemplate({data : commentsList}), "text/html")
					.querySelectorAll("li");
	
	newCommentItems.forEach((item) => {
		target.append(item);
	});	
}
function scrollToTopAttacher(target){
	target.addEventListener("click", (e) => {
		document.documentElement.scrollTop = document.body.scrollTop = 0;
	});
}

