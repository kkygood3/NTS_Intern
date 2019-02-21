document.addEventListener("DOMContentLoaded", function() {
	reviewWritePage.getReviewWritePage();
});

var reviewWritePage = {
	getReviewWritePage: function(){
		var star = new StarRating(this.elements.ratingContainer);
		var callback = function(event){
			this.elements.ratingValue.classList.remove("gray_star");
			this.elements.ratingValue.innerHTML = event.target.value;
		}.bind(this);
		star.setStarRating("rating_rdo", callback);
		
		this.setEvent.focusOnReviewTextarea();
		this.setEvent.addEventToReviewTextarea();
	},
	
	constants: {
		MAX_LENGTH : 400,
		DEFAULT_HEIGHT : 214
	},
	
	elements: {
		ratingContainer : document.querySelector(".rating"),
		ratingValue : document.querySelector(".star_rank"),
		

		reviewWriteInfo : document.querySelector(".review_write_info"),
		reviewTextarea : document.querySelector(".review_textarea"),
		btnWrite : document.querySelector(".bk_btn"),
		reviewLength : document.querySelector("#review_length")
	},
	
	setEvent: {
		focusOnReviewTextarea : function(){
			this.reviewWritePage.elements.reviewWriteInfo.addEventListener("click", function(event){
				event.preventDefault();
				
				this.reviewWritePage.elements.reviewWriteInfo.style.display = "none";
				this.reviewWritePage.elements.reviewTextarea.focus();
			}.bind(this));
		}.bind(this),
		
		addEventToReviewTextarea : function(){
			this.reviewWritePage.elements.reviewTextarea.addEventListener("input", function(event){
				if(event.target.value.length === 0){
					this.reviewWritePage.elements.reviewWriteInfo.style.display = "";
					this.reviewWritePage.elements.btnWrite.disabled = "true";
				} else {
					this.reviewWritePage.elements.reviewWriteInfo.style.display = "none";
					this.reviewWritePage.elements.btnWrite.removeAttribute("disabled");
				}
				
				if(event.target.value.length > this.reviewWritePage.constants.MAX_LENGTH){
					event.target.value = event.target.value.substring(0, this.reviewWritePage.constants.MAX_LENGTH);
				}
				
				if(event.target.scrollHeight > this.reviewWritePage.constants.DEFAULT_HEIGHT){
					event.target.style.height = event.target.scrollHeight + "px";
				}
				
				this.reviewWritePage.elements.reviewLength.innerHTML = event.target.value.length;
			}.bind(this));
		}.bind(this),
	}
}

function StarRating(ratingContainer){
	this.ratingContainer = ratingContainer;
}
StarRating.prototype.setStarRating = function(starClassName, callback){
	this.ratingContainer.addEventListener("click", function(event){
		var stars = document.querySelectorAll("." + starClassName);
		if(event.target.classList.contains(starClassName)){
			if(typeof(callback) === "function" && callback !== undefined && callback !== null){
				callback(event);
			}
			
			stars.forEach(function(star){
				star.checked = true;
				if(star.value > event.target.value){
					star.checked = false;
				}
			});
		}
	});
}