/**
 * Copyright 2019 NAVER Corp. All rights reserved. Except in the case of
 * internal use for NAVER, unauthorized use of redistribution of this software
 * are strongly prohibited.
 */

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */


const TICKET_TYPES = {
    A: {
        name: "성인",
        requirement: "(만 19~64세)"
    },
    Y: {
        name: "청소년",
        requirement: "(만 13~18세)"
    },
    B: {
        name: "유아",
        requirement: "(만 4~12세)"
    },
    S: {
        name: "셋트",
        requirement: "(만 19~64세)"
    },
    D: {
        name: "장애인",
    },
    C: {
        name: "지역주민",
    },
    E: {
        name: "얼리버드",
    },
};

const HALL_TYPES = {
    V: {
        name: "VIP",
    },
    R: {
        name: "R석",
    },
    S: {
        name: "S석",
    },
    A: {
        name: "A석",
    },
    B: {
        name: "B석",
    },
    D: {
        name: "평일",
    },
};

/**
 * 
 * @param data :
 *            crucial info to be rendered
 * @param pricingType :
 *            crucial info, contains corresponding map as json type
 * @param prices =
 * @NULLABLE, this is the target where price information is mapped as JSON
 *            object
 * @returns
 */
function mapPriceData(data, prices) {
	pricingType = TICKET_TYPES;
    // checkType
    data.productPrices.forEach((item) => {
        if (item.priceTypeName === "V" || item.priceTypeName === "R") {
            pricingType = HALL_TYPES;
        }
    });
    /*
	 * accumulate strings and render information, init control data by pushing
	 * JSON object to prices array
	 */
    let priceString = "";
    data.productPrices.forEach((item) => {
    	if(prices){
    		prices[item.productPriceId] = {price: item.price, count: 0, productPriceId: item.productPriceId};
    	}
        let currentType = pricingType[item.priceTypeName];
        let currentString = "";
        for (var key in currentType) {
            if (currentType.hasOwnProperty(key)) {
                currentString += currentType[key];
                if (key === "name") {
                    item.name = currentType[key];
                }
            }
        }
        currentString += " " + item.price + "원 할인율 " + item.discountRate + "%<br>";
        priceString += currentString;
    });
    return priceString;
}

function SlidingAnimation(_slideContainer) {
    this.slideContainer = _slideContainer;
    this.slideWrapper = this.slideContainer.closest("div");

    this.imageList = this.slideContainer.getElementsByTagName("li");
    this.SLIDE_CONATINER_WIDTH = this.slideContainer.offsetWidth;
}

/**
 * @SlidingAnimation.init() : required setup for the promo animation, and
 *                          initialization of animation frame call
 */
SlidingAnimation.prototype.init = function (optionals) {
    this.prevSlideCount = this.imageList.length - 1;
    this.currentSlideCount = 0;
    this.nextSlideCount = 1;
    this.isAnimating = false;
    this.isAutoStart = false;

    this.maxHeight = this.slideWrapper.clientHeight;
    this.minHeight = 100;
    this.animationSpeed = 10;
    this.animationStopDuration = 1000;

    if (optionals) {
        this.maxHeight = optionals.maxHeight ? optionals.maxHeight : this.slideWrapper.clientHeight;
        this.minHeight = optionals.minHeight ? optionals.minHeight : 100;
        this.animationSpeed = optionals.animationSpeed ? optionals.animationSpeed : 10;
        this.animationStopDuration = optionals.animationStopDuration ? optionals.animationStopDuration : 1000;
    }
    this.imageList.forEach((item) => {
        if (item == this.imageList[0]) {
            this.imageList[0].style.left = 0 + "px";
        } else {
            item.style.left = this.SLIDE_CONATINER_WIDTH + "px";
        }
        item.style.position = "absolute";
    });
},

SlidingAnimation.prototype.startAutoAnimation = function () {
    this.isAutoStart = true;
    setTimeout(() => {
        requestAnimationFrame(() => this.slide(
        		{
                	isAutoStart : true, 
                	isReverse : false,
                	isResizing : false
                }));
    }, this.animationStopDuration);
}

SlidingAnimation.prototype.changeTiming = function (_animationSpeed, _stopDuration) {
    this.animationSpeed = _animationSpeed;
    this.animationStopDuration = _stopDuration;
}

SlidingAnimation.prototype.resizeMinMax = function (_minHeight, _maxHeight) {
    this.maxHeight = _maxHeight;
    this.minHeight = _minHeight;
}

/**
 * @constants.animationSpeed : to control the speed or animation, declared as
 *                           const in global variable
 * 
 * @constants.animationStopDuration : in milliseconds, determines the stop
 *                                  duration of the animation when the image
 *                                  arrives in the right position , declared as
 *                                  const in global variable
 * 
 * @needToStop : this boolean indicates when the element is arrived in the right
 *             position to be displayed
 * 
 * 
 * 
 * @params * required
 * 
 * @params.isAutoStart : parameter to control auto-slide animation, if false,
 *                     manual
 * 
 * @params.isReverse : as the meaning of the words, implies if the animation is
 *                   in reversed direction
 * @params.isResizing : as the meaning of the words, implies if the slide
 *                    wrapper should be resized
 */
SlidingAnimation.prototype.slide = function (params) {
    this.isAnimating = true;

    let needToStop = false;

    let prevImage = this.imageList[this.prevSlideCount];
    let currentImage = this.imageList[this.currentSlideCount];
    let nextImage = this.imageList[this.nextSlideCount];
    
    if (params.isResizing) {
        this.resizeImageContainer(nextImage);
    }

    if (params.isReverse && parseInt(prevImage.style.left) == this.SLIDE_CONATINER_WIDTH) {
        prevImage.style.left = -this.SLIDE_CONATINER_WIDTH + "px";
    }

    if (!params.isReverse && parseInt(nextImage.style.left) == -this.SLIDE_CONATINER_WIDTH) {
        nextImage.style.left = this.SLIDE_CONATINER_WIDTH + "px";
    }

    for (let iter = 0; iter < this.animationSpeed; iter++) {
        if (params.isReverse) {
            currentImage.style.left = parseInt(currentImage.style.left) + 1 + "px";
            prevImage.style.left = parseInt(prevImage.style.left) + 1 + "px";
        } else {
            currentImage.style.left = parseInt(currentImage.style.left) - 1 + "px";
            nextImage.style.left = parseInt(nextImage.style.left) - 1 + "px";
        }

        if ((parseInt(currentImage.style.left) <= -this.SLIDE_CONATINER_WIDTH && !params.isReverse)
            || (parseInt(currentImage.style.left) >= this.SLIDE_CONATINER_WIDTH && params.isReverse)) {

            currentImage.style.left = this.SLIDE_CONATINER_WIDTH + "px";

            if (params.isReverse) {
                this.nextSlideCount += this.imageList.length - 1;
                this.currentSlideCount += this.imageList.length - 1;
                this.prevSlideCount += this.imageList.length - 1;
            } else {
                this.prevSlideCount++;
                this.currentSlideCount++;
                this.nextSlideCount++;
            }

            this.prevSlideCount %= this.imageList.length;
            this.currentSlideCount %= this.imageList.length;
            this.nextSlideCount %= this.imageList.length;

            needToStop = true;
            break;
        }
    }
    if (needToStop) {
        if (this.isAutoStart) {
            setTimeout(() => {
                requestAnimationFrame(() => this.slide(params));
            }, this.animationStopDuration);
        } else {
            this.isAnimating = false;
        }
    } else {
        requestAnimationFrame(() => this.slide(params));
    }
},


/**
 * @resizeImageContainer(countTarget) : countTarget represents the next target
 *                                    image index in state.imageList, and set
 *                                    the height of the image container with
 *                                    height obtained from the slide
 */
SlidingAnimation.prototype.resizeImageContainer = function (target, maxHeight, minHeight) {
    if (target.clientHeight > this.SLIDE_CONATINER_WIDTH) {
        this.slideWrapper.style.height = this.SLIDE_CONATINER_WIDTH + "px";
    } else {
        this.slideWrapper.style.height = target.clientHeight + "px";
    }
}


/**
 * @XhrRequest() : pre-defined XmlHttpRequest in prototype
 * @param method :
 *            implies REST method way,
 */
function XhrRequest(_method, _url) {
    this.xhr = new XMLHttpRequest();
    this.method = _method;
    this.url = _url;
    this.isAsync = true;
}

XhrRequest.prototype.setCallback = function (_callback){
	this.xhr.onreadystatechange = function (aEvt) {
        if (this.readyState === XMLHttpRequest.DONE) {
            if (this.status === 200) {
                _callback(this.responseText);
            }
        }
    };
}

XhrRequest.prototype.setIsAsync = function(_isAsync){
	isAsync = _isAsync;
}

XhrRequest.prototype.send = function(data){
	this.xhr.open(this.method, this.url, this.isAsync);
    this.xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
	this.xhr.send(data);
}


/**
 * @data: data to be rendered, and mapped with handlebar, in array type.
 * 
 * @target: dom element where the rendered item should be attached.
 * 
 * @item : html template in string type
 */
function arrayToElementRenderer(data, target, item, opt) {
    if (!data.length) {
        return;
    }

    let bindTemplate = Handlebars.compile(item);
    let list = data;
    
    if(opt){
    	for(let key in opt){
    		Handlebars.registerHelper(key, () => {
    	        return opt[key];
    	    });
    	}
    }
    
    Handlebars.registerHelper("date", (item) => {
        return item.year + "년 " + item.monthValue + "월 " + item.dayOfMonth + "일 ";
    });
    Handlebars.registerHelper("scoreToDouble", (item) => {
        return item.toFixed(1);
    });
    Handlebars.registerHelper("isImagePresent", (item) => {
        return item.length > 0 ? "" : "no_img";
    });
    Handlebars.registerHelper("commentImageUrl", (item) => {
        return item[0].saveFileName;
    });
    Handlebars.registerHelper("rsvDateSplit", (item) => {
        return item.split(" ")[0];
    });
    Handlebars.registerHelper("rsvIdFix", (item) => {
        let width = 8;
        item = item + '';
        return item.length >= width ? n : new Array(width - item.length + 1).join('0') + item;
    });
    let parser = new DOMParser();
    let parsedItems = parser.parseFromString(bindTemplate({data: list}), "text/html");
    let elementClassName = parsedItems.querySelector("body").firstElementChild.className;
    let newCommentItems = parsedItems.querySelectorAll("." + elementClassName);
    newCommentItems.forEach((item) => {
        target.append(item);
    });
}

function logout(){
	document.querySelector(".logout").addEventListener("click", () => {
		let request = new XhrRequest("POST", "./api/logout");
		request.setCallback(() =>{alert("Logout"), window.location.href = "./"});
		request.send();
	});
}

function scrollToTopAttacher() {
	document.querySelectorAll(".gototop").forEach((item) => {
		item.addEventListener("click", (e) => {
	        document.documentElement.scrollTop = document.body.scrollTop = 0;
	    });
	});
}
