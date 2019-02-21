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
	},
	
	elements: {
		ratingContainer : document.querySelector(".rating"),
		ratingValue : document.querySelector(".star_rank"),
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