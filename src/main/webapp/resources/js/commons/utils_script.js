/**
 * Copyright 2019 NAVER Corp. All rights reserved. Except in the case of
 * internal use for NAVER, unauthorized use of redistribution of this software
 * are strongly prohibited.
 */

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

function SlidingAnimation(_slideContainer){
	this.slideContainer = _slideContainer;
	this.slideWrapper = this.slideContainer.closest("div");
	
	this.imageList = this.slideContainer.getElementsByTagName("li");
	this.SLIDE_CONATINER_WIDTH = this.slideContainer.offsetWidth;	
}

/**
 * @initSlideAnimation() : required setup for the promo animation, and
 *                       initialization of animation frame call
 */
SlidingAnimation.prototype.init = function(params) {	
	this.prevSlideCount = this.imageList.length - 1;	
	this.currentSlideCount = 0;
	this.nextSlideCount = 1;
	this.isAnimating = false;
	
	this.maxHeight = params && params.maxHeight ? params.maxHeight : this.slideWrapper.clientHeight;
	this.minHeight = params && params.minHeight ? params.minHeight : 100;
	this.isAutoStart = false;
	this.animationSpeed = params && params.animationSpeed ? params.animationSpeed : 10;
	this.animationStopDuration = params && params.animationStopDuration ? params.animationStopDuration : 1000;
		
	this.imageList.forEach((item) => {
		if(item == this.imageList[0]) {
			this.imageList[0].style.left = 0 + "px";
		} else {
			item.style.left = this.SLIDE_CONATINER_WIDTH + "px";
		}
		item.style.position = "absolute";
	});
},

SlidingAnimation.prototype.startAutoAnimation = function(){
	this.isAutoStart = true;
	if(this.isAutoStart) {
		setTimeout(() => {
			requestAnimationFrame(() => this.slide(true, false, false));
		}, this.animationStopDuration);
	}
}

SlidingAnimation.prototype.changeTiming = function(_animationSpeed, _stopDuration){
	this.animationSpeed = _animationSpeed;
	this.animationStopDuration = _stopDuration;
}

SlidingAnimation.prototype.resizeMinMax = function(_minHeight, _maxHeight){
	this.maxHeight = _maxHeight;
	this.minHeight = _minHeight;
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
 * 
 * @isReverse : as the meaning of the words, implies if the animation is in
 *            reversed direction
 */
SlidingAnimation.prototype.slide = function(isAutoStart, isReverse, isResizing) {
	this.isAnimating = true;
	
	let needToStop = false;
	
	let prevImage = this.imageList[this.prevSlideCount];
	let currentImage = this.imageList[this.currentSlideCount];
	let nextImage = this.imageList[this.nextSlideCount];
		
	if(isResizing){
		this.resizeImageContainer(nextImage);
	}
	
	if(isReverse && parseInt(prevImage.style.left) == this.SLIDE_CONATINER_WIDTH) {
		prevImage.style.left = -this.SLIDE_CONATINER_WIDTH + "px";
	}
	
	if(!isReverse && parseInt(nextImage.style.left) == -this.SLIDE_CONATINER_WIDTH) {
		nextImage.style.left = this.SLIDE_CONATINER_WIDTH + "px";
	}
	
	for(let iter = 0; iter < this.animationSpeed; iter++) {
		if(isReverse){
			currentImage.style.left = parseInt(currentImage.style.left) + 1 + "px";
			prevImage.style.left = parseInt(prevImage.style.left) + 1 + "px";
		} else {
			currentImage.style.left = parseInt(currentImage.style.left) - 1 + "px";
			nextImage.style.left = parseInt(nextImage.style.left) - 1 + "px";
		}
		
		if((parseInt(currentImage.style.left) <= -this.SLIDE_CONATINER_WIDTH && !isReverse)
			|| (parseInt(currentImage.style.left) >= this.SLIDE_CONATINER_WIDTH && isReverse) ) {
			
			currentImage.style.left = this.SLIDE_CONATINER_WIDTH + "px";
			
			if(isReverse){
				this.nextSlideCount += this.imageList.length-1;
				this.currentSlideCount += this.imageList.length-1;
				this.prevSlideCount += this.imageList.length-1;
			} else {
				this.prevSlideCount ++;
				this.currentSlideCount ++;
				this.nextSlideCount++;
			}
			
			this.prevSlideCount %= this.imageList.length;
			this.currentSlideCount %= this.imageList.length;
			this.nextSlideCount %= this.imageList.length;
			
			needToStop = true;
			break;
		}
	}
	if(needToStop) {
		if(this.isAutoStart) {
			setTimeout(() => {
				requestAnimationFrame(() => this.slide(isAutoStart, isReverse, isResizing));
			}, this.animationStopDuration);
		} else {
			this.isAnimating = false;
		}
	} else {
		requestAnimationFrame(() => this.slide(isAutoStart, isReverse, isResizing));
	}	
},


/**
 * @resizeImageContainer(countTarget) : countTarget represents the next target
 *                                    image index in state.imageList, and set
 *                                    the height of the image container with
 *                                    height obtained from the slide
 */
SlidingAnimation.prototype.resizeImageContainer= function(target, maxHeight, minHeight){
	if(target.clientHeight > this.SLIDE_CONATINER_WIDTH) {
		this.slideWrapper.style.height = this.SLIDE_CONATINER_WIDTH+"px";
	} else {
		this.slideWrapper.style.height = target.clientHeight + "px";
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
 * @xhrPostRequest() : pre-defined XmlHttpRequest Get method since get method
 *                   will be used frequently
 */
function xhrPostRequest(url, callback) {
	let xhr = new XMLHttpRequest();
	xhr.open("POST", url, true);
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
function arrayToElementRenderer(data, target, item) {
	if(!data.length){
		return;
	}
	
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
	let parsedItems = parser.parseFromString(bindTemplate({data : commentsList}), "text/html");
	let elementClassName = parsedItems.querySelector("body").firstElementChild.className;
	let newCommentItems = parsedItems.querySelectorAll("."+elementClassName);

	newCommentItems.forEach((item) => {
		target.append(item);
	});	
}

function scrollToTopAttacher(target){
	target.addEventListener("click", (e) => {
		document.documentElement.scrollTop = document.body.scrollTop = 0;
	});
}

