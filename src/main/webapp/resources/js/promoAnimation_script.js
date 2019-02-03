/**
 * Copyright 2019 NAVER Corp. All rights reserved. Except in the case of
 * internal use for NAVER, unauthorized use of redistribution of this software
 * are strongly prohibited.
 */

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

var constants = {
	PROMO_CONATINER_WIDTH : "",
	ANIMATION_SPEED : 4,
	ANIMATION_STOP_DURATION : 1000,
};

var state = {
	prevPromoCount : 0,
	currentPromoCount : 1,
	prevPromoImage : "",
	currentPromoImage : "",
	IMAGE_LIST : []
};

/**
 * @initPromoAnimation() : required setup for the promo animation, and
 *                       initialization of animation frame call
 */

function initPromoAnimation() {
	state.IMAGE_LIST = domElements.PROMO_CONTAINER.getElementsByTagName("li");
	constants.PROMO_CONATINER_WIDTH = domElements.PROMO_CONTAINER.offsetWidth;

	state.prevPromoCount = 0;
	state.currentPromoCount = 1;
	// change the layout with style.left to initialize the animation
	state.IMAGE_LIST.forEach((item) => {
		if(item == state.IMAGE_LIST[0]){
			state.IMAGE_LIST[0].style.left = 0 + "px";
		} else {
			item.style.left = constants.PROMO_CONATINER_WIDTH + "px";
		}
		item.style.position = "absolute";
		
	});
	
	// timeout because first promo-slide should be displayed before
	// transition
	setTimeout(() => {
		state.prevPromoImage = state.IMAGE_LIST[state.prevPromoCount];
		state.currentPromoImage = state.IMAGE_LIST[state.currentPromoCount];
		requestAnimationFrame(promoAnimation);
	},constants.ANIMATION_STOP_DURATION);
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
function promoAnimation() {
	let needToStop = false;
	let prevImage = state.prevPromoImage;
	let currentImage = state.currentPromoImage;
	
	for(let iter = 0; iter < constants.ANIMATION_SPEED; iter++) {
		prevImage.style.left = parseInt(prevImage.style.left) - 1 + "px";
		currentImage.style.left = parseInt(currentImage.style.left) - 1 + "px";
		
		if(parseInt(prevImage.style.left) <= -1 * constants.PROMO_CONATINER_WIDTH) {
			prevImage.style.left = constants.PROMO_CONATINER_WIDTH + "px";
			state.prevPromoCount = state.currentPromoCount;
			state.currentPromoCount++;
			if(state.currentPromoCount == state.IMAGE_LIST.length) {
				state.currentPromoCount = 0;
			}
			needToStop = true;
			break;
		}
	}	
	
	if(needToStop) {
		setTimeout(() => {
			state.prevPromoImage = state.IMAGE_LIST[state.prevPromoCount];
			state.currentPromoImage = state.IMAGE_LIST[state.currentPromoCount];
			requestAnimationFrame(promoAnimation);
		},constants.ANIMATION_STOP_DURATION);
	} else {
		requestAnimationFrame(promoAnimation);
	}
}
