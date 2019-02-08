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
	
	IMAGE_LIST = slideContainer.getElementsByTagName("li");
	SLIDE_CONATINER_WIDTH = slideContainer.offsetWidth;
	
	isAuto = true;
	ANIMATION_SPEED = 1;
	ANIMATION_STOP_DURATION = 1000;
	
	prevSlideCount = IMAGE_LIST.length - 1;	
	currentSlideCount = 0;
	nextSlideCount = 1;
	isAnimating = false;
}

/**
 * @initSlideAnimation() : required setup for the promo animation, and
 *                       initialization of animation frame call
 */
SlidingAnimation.prototype.init = function(_animationSpeed, _stopDuration, _isAuto) {
	isAuto = _isAuto;
	ANIMATION_SPEED = _animationSpeed;
	ANIMATION_STOP_DURATION = _stopDuration;
	
	IMAGE_LIST.forEach((item) => {
		if(item == IMAGE_LIST[0]) {
			IMAGE_LIST[0].style.left = 0 + "px";
		} else {
			item.style.left = SLIDE_CONATINER_WIDTH + "px";
		}
		item.style.position = "absolute";
	});
	
	/*
	 * timeout because first promo-slide should be displayed before transition
	 */
	if(isAuto) {
		setTimeout(() => {
			requestAnimationFrame(() => this.slide(true));
		}, ANIMATION_STOP_DURATION);
	}
},

/**
 * @constants.ANIMATION_SPEED : to control the speed or animation, declared as
 *                            const in global variable
 * 
 * @needToStop : this boolean indicates when the element is arrived in the right
 *             position to be displayed
 * 
 * @constants.ANIMATION_STOP_DURATION : in milliseconds, determines the stop
 *                                    duration of the animation when the image
 *                                    arrives in the right position , declared
 *                                    as const in global variable
 * 
 * @promoAnimation() : promotion animation with 2 for loops to control the
 *                   accuracy of the stop-position
 * 
 * @isAuto : parameter to control auto-slide animation, if false, manual
 */
SlidingAnimation.prototype.slide = function(isAuto, isResizing, maxHeight, minHeight) {
	this.isAnimating = true;
	
	let needToStop = false;
	let currentImage = IMAGE_LIST[currentSlideCount];
	let nextImage = IMAGE_LIST[nextSlideCount];
		
	if(isResizing){
		this.resizeImageContainer(nextImage, maxHeight, minHeight);
	}
	
	if(parseInt(nextImage.style.left) == -414) {
		nextImage.style.left = SLIDE_CONATINER_WIDTH + "px";
	}
	
	for(let iter = 0; iter < ANIMATION_SPEED; iter++) {
		currentImage.style.left = parseInt(currentImage.style.left) - 1 + "px";
		nextImage.style.left = parseInt(nextImage.style.left) - 1 + "px";
		
		if(parseInt(currentImage.style.left) <= -SLIDE_CONATINER_WIDTH) {
			
			currentImage.style.left = SLIDE_CONATINER_WIDTH + "px";
			
			prevSlideCount = currentSlideCount;
			currentSlideCount = nextSlideCount;
			nextSlideCount++;
			
			if(nextSlideCount == IMAGE_LIST.length) {
				nextSlideCount = 0;
			}
			needToStop = true;
			break;
		}
	}
	if(needToStop) {
		if(isAuto) {
			setTimeout(() => {
				requestAnimationFrame(() => this.slide(isAuto));
			}, ANIMATION_STOP_DURATION);
		} else {
			console.log("done")
			this.isAnimating = false;
		}
	} else {
		requestAnimationFrame(() => this.slide(isAuto));
	}	
},

/**
 * @constants.ANIMATION_SPEED : to control the speed or animation, declared as
 *                            const in global variable
 * 
 * @needToStop : this boolean indicates when the element is arrived in the right
 *             position to be displayed
 * 
 * @constants.ANIMATION_STOP_DURATION : in milliseconds, determines the stop
 *                                    duration of the animation when the image
 *                                    arrives in the right position , declared
 *                                    as const in global variable
 * 
 * @promoAnimation() : promotion animation with 2 for loops to control the
 *                   accuracy of the stop-position
 * 
 * @isAuto : parameter to control auto-slide animation, if false, manual
 * 
 * @slideAnimationReverse(isAuto) diff with slideAnimation => direction of
 *                                animation and counting system are different,
 *                                as slideAnimation slides with asc order,
 *                                Reverse goes desc order
 */
SlidingAnimation.prototype.slideReverse = function(isAuto, isResizing, maxHeight, minHeight) {
	this.isAnimating = true;
	
	let needToStop = false;
	let prevImage = IMAGE_LIST[prevSlideCount];
	let currentImage = IMAGE_LIST[currentSlideCount];
	
	if(isResizing){
		this.resizeImageContainer(prevImage, maxHeight, minHeight);
	}
	
	if(parseInt(prevImage.style.left) == 414) {
		prevImage.style.left = -SLIDE_CONATINER_WIDTH + "px";
	}
	
	for(let iter = 0; iter < ANIMATION_SPEED; iter++) {
		currentImage.style.left = parseInt(currentImage.style.left) + 1 + "px";
		prevImage.style.left = parseInt(prevImage.style.left) + 1 + "px";
		
		if(parseInt(currentImage.style.left) >= SLIDE_CONATINER_WIDTH) {
			
			currentImage.style.left = -SLIDE_CONATINER_WIDTH + "px";
			
			nextSlideCount = currentSlideCount;
			currentSlideCount = prevSlideCount;
			prevSlideCount = prevSlideCount - 1;
			
			if(prevSlideCount == -1){
				prevSlideCount = IMAGE_LIST.length - 1;
			}
			
			needToStop = true;
			break;
		}
	}
	if(needToStop) {
		if(isAuto) {
			setTimeout(() => {
				requestAnimationFrame(() => this.slideReverse(isAuto));
			}, ANIMATION_STOP_DURATION);
		} else {
			this.isAnimating = false;
		}
	} else {
		requestAnimationFrame(() =>this.slideReverse(isAuto));
	}
}

/**
 * @resizeImageContainer(countTarget) : countTarget represents the next target
 *                                    image index in state.IMAGE_LIST, and set
 *                                    the height of the image container with
 *                                    height obtained from the slide
 */
SlidingAnimation.prototype.resizeImageContainer= function(target, maxHeight, minHeight){
	console.log(target)
	console.log(slideWrapper)
	if(target.clientHeight > 414) {
		slideWrapper.style.height = "414px";
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
	Handlebars.registerHelper("emailMask", (item) => {
		return item + "****";
	});
	Handlebars.registerHelper("scoreToDouble", (item) => {
		return item.toFixed(1);
	});
	Handlebars.registerHelper("isImagePresent", (item) => {
		return item.length>0?"":"no_img";
	});
	Handlebars.registerHelper("imageUrl", (item)=> {
		return item[0].saveFileName;
	});
	
	let newCommentItems = 
		parser.parseFromString(bindTemplate({data : commentsList}), "text/html")
					.querySelectorAll("li");
	
	newCommentItems.forEach((item) => {
		target.append(item);
	});	
}
