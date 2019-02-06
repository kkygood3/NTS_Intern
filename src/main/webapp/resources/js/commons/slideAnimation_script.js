/**
 * Copyright 2019 NAVER Corp. All rights reserved. Except in the case of
 * internal use for NAVER, unauthorized use of redistribution of this software
 * are strongly prohibited.
 */

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

var constants = {
	SLIDE_CONATINER_WIDTH : "",
	ANIMATION_SPEED : 4,
	ANIMATION_STOP_DURATION : 1000,
};

var state = {
	nextSlideCount : 1,
	currentSlideCount : 0,
	prevSlideCount : -1,
	isAnimating : false,
	IMAGE_LIST : []
};

/**
 * @initPromoAnimation() : required setup for the promo animation, and
 *                       initialization of animation frame call
 */

function initSlideAnimation(slideContainer, isAuto) {
	constants.SLIDE_CONATINER_WIDTH = slideContainer.offsetWidth;

	state.IMAGE_LIST = slideContainer.getElementsByTagName("li");
	state.prevSlideCount = state.IMAGE_LIST.length-1;	
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
	
	/* timeout because first promo-slide should be displayed before
		transition*/
	if(isAuto) {
		setTimeout(() => {
			requestAnimationFrame(() => slideAnimation(true));
		}, constants.ANIMATION_STOP_DURATION);
	}
}

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
 */
function slideAnimation(isAuto) {
	state.isAnimating = true;
	
	let needToStop = false;
	let currentImage = state.IMAGE_LIST[state.currentSlideCount];
	let nextImage = state.IMAGE_LIST[state.nextSlideCount];
	
	if(parseInt(nextImage.style.left) == -414){
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
	if(needToStop){
		if(isAuto) {
			setTimeout(() => {
				requestAnimationFrame(() => slideAnimation(isAuto));
			}, constants.ANIMATION_STOP_DURATION);
		} else {
			state.isAnimating = false;
		}
	}
	else {
		requestAnimationFrame(() => slideAnimation(isAuto));
	}	
}

function slideAnimationReverse(isAuto) {
	state.isAnimating = true;
	
	let needToStop = false;
	let prevImage = state.IMAGE_LIST[state.prevSlideCount];
	let currentImage = state.IMAGE_LIST[state.currentSlideCount];
	
	if(parseInt(prevImage.style.left) == 414){
		prevImage.style.left = -constants.SLIDE_CONATINER_WIDTH + "px";
	}
	
	for(let iter = 0; iter < constants.ANIMATION_SPEED; iter++) {
		currentImage.style.left = parseInt(currentImage.style.left) + 1 + "px";
		prevImage.style.left = parseInt(prevImage.style.left) + 1 + "px";
		
		if(parseInt(currentImage.style.left) >= constants.SLIDE_CONATINER_WIDTH) {
			
			currentImage.style.left = -constants.SLIDE_CONATINER_WIDTH + "px";
			
			state.nextSlideCount = state.currentSlideCount;
			state.currentSlideCount = state.prevSlideCount;
			state.prevSlideCount = state.prevSlideCount -1;
			
			if(state.prevSlideCount == -1){
				state.prevSlideCount = state.IMAGE_LIST.length-1;
			}
			
			needToStop = true;
			break;
		}
	}
	if(needToStop){
		if(isAuto) {
			setTimeout(() => {
				requestAnimationFrame(() => slideAnimationReverse(isAuto));
			}, constants.ANIMATION_STOP_DURATION);
		} else {
			state.isAnimating = false;
		}
	}
	else {
		requestAnimationFrame(() => slideAnimationReverse(isAuto));
	}
	
}
