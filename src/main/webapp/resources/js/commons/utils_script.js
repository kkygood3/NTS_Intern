/**
 * Copyright 2019 NAVER Corp. All rights reserved. Except in the case of
 * internal use for NAVER, unauthorized use of redistribution of this software
 * are strongly prohibited.
 */

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

var slidingAnimation = {
	state : {
		IMAGE_LIST : []
	},
	
	/**
	 * @initSlideAnimation() : required setup for the promo animation, and
	 *                       initialization of animation frame call
	 */
	initSlideAnimation : function (slideContainer, isAuto) {
		constants.SLIDE_CONATINER_WIDTH = slideContainer.offsetWidth;
		state.IMAGE_LIST = slideContainer.getElementsByTagName("li");
		state.prevSlideCount = state.IMAGE_LIST.length - 1;	
		state.currentSlideCount = 0;
		state.nextSlideCount = 1;

		// change the layout with style.left to initialize the animation
		state.IMAGE_LIST.forEach((item) => {
			
			if(item == state.IMAGE_LIST[0]) {
				state.IMAGE_LIST[0].style.left = 0 + "px";
			} else {
				item.style.left = constants.SLIDE_CONATINER_WIDTH + "px";
			}
			item.style.position = "absolute";
		});
		
		/*
		 * timeout because first promo-slide should be displayed before
		 * transition
		 */
		if(isAuto) {
			setTimeout(() => {
				requestAnimationFrame(() => this.slideAnimation(true));
			}, constants.ANIMATION_STOP_DURATION);
		}
	},
	
	/**
	 * @constants.ANIMATION_SPEED : to control the speed or animation, declared
	 *                            as const in global variable
	 * 
	 * @needToStop : this boolean indicates when the element is arrived in the
	 *             right position to be displayed
	 * 
	 * @constants.ANIMATION_STOP_DURATION : in milliseconds, determines the stop
	 *                                    duration of the animation when the
	 *                                    image arrives in the right position ,
	 *                                    declared as const in global variable
	 * 
	 * @promoAnimation() : promotion animation with 2 for loops to control the
	 *                   accuracy of the stop-position
	 * 
	 * @isAuto : parameter to control auto-slide animation, if false, manual
	 */
	slideAnimation : function (isAuto) {
		state.isAnimating = true;
		
		let needToStop = false;
		let currentImage = state.IMAGE_LIST[state.currentSlideCount];
		let nextImage = state.IMAGE_LIST[state.nextSlideCount];
		
		if(parseInt(nextImage.style.left) == -414) {
			nextImage.style.left = constants.SLIDE_CONATINER_WIDTH + "px";
		}
		
		for(let iter = 0; iter < constants.ANIMATION_SPEED; iter++) {
			currentImage.style.left = parseInt(currentImage.style.left) - 1 + "px";
			nextImage.style.left = parseInt(nextImage.style.left) - 1 + "px";
			
			if(parseInt(currentImage.style.left) <= -constants.SLIDE_CONATINER_WIDTH) {
				
				currentImage.style.left = constants.SLIDE_CONATINER_WIDTH + "px";
				
				state.prevSlideCount = state.currentSlideCount;
				state.currentSlideCount = state.nextSlideCount;
				state.nextSlideCount++;
				
				if(state.nextSlideCount == state.IMAGE_LIST.length) {
					state.nextSlideCount = 0;
				}
				needToStop = true;
				break;
			}
		}
		if(needToStop) {
			if(isAuto) {
				setTimeout(() => {
					requestAnimationFrame(() => this.slideAnimation(isAuto));
				}, constants.ANIMATION_STOP_DURATION);
			} else {
				state.isAnimating = false;
			}
		} else {
			requestAnimationFrame(() => this.slideAnimation(isAuto));
		}	
	},

	/**
	 * @constants.ANIMATION_SPEED : to control the speed or animation, declared
	 *                            as const in global variable
	 * 
	 * @needToStop : this boolean indicates when the element is arrived in the
	 *             right position to be displayed
	 * 
	 * @constants.ANIMATION_STOP_DURATION : in milliseconds, determines the stop
	 *                                    duration of the animation when the
	 *                                    image arrives in the right position ,
	 *                                    declared as const in global variable
	 * 
	 * @promoAnimation() : promotion animation with 2 for loops to control the
	 *                   accuracy of the stop-position
	 * 
	 * @isAuto : parameter to control auto-slide animation, if false, manual
	 * 
	 * @slideAnimationReverse(isAuto) diff with slideAnimation => direction of
	 *                                animation and counting system are
	 *                                different, as slideAnimation slides with
	 *                                asc order, Reverse goes desc order
	 */
	slideAnimationReverse : function (isAuto) {
		state.isAnimating = true;
		
		let needToStop = false;
		let prevImage = state.IMAGE_LIST[state.prevSlideCount];
		let currentImage = state.IMAGE_LIST[state.currentSlideCount];
		
		if(parseInt(prevImage.style.left) == 414) {
			prevImage.style.left = -constants.SLIDE_CONATINER_WIDTH + "px";
		}
		
		for(let iter = 0; iter < constants.ANIMATION_SPEED; iter++) {
			currentImage.style.left = parseInt(currentImage.style.left) + 1 + "px";
			prevImage.style.left = parseInt(prevImage.style.left) + 1 + "px";
			
			if(parseInt(currentImage.style.left) >= constants.SLIDE_CONATINER_WIDTH) {
				
				currentImage.style.left = -constants.SLIDE_CONATINER_WIDTH + "px";
				
				state.nextSlideCount = state.currentSlideCount;
				state.currentSlideCount = state.prevSlideCount;
				state.prevSlideCount = state.prevSlideCount - 1;
				
				if(state.prevSlideCount == -1){
					state.prevSlideCount = state.IMAGE_LIST.length - 1;
				}
				
				needToStop = true;
				break;
			}
		}
		if(needToStop) {
			if(isAuto) {
				setTimeout(() => {
					requestAnimationFrame(() => this.slideAnimationReverse(isAuto));
				}, constants.ANIMATION_STOP_DURATION);
			} else {
				state.isAnimating = false;
			}
		} else {
			requestAnimationFrame(() => this.slideAnimationReverse(isAuto));
		}
		
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
