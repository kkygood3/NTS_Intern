function Rating() {
	this.score = 0;
	this.stars = document.querySelectorAll(".review_rating.rating_point .rating_rdo");
	this.starRank = document.querySelector(".rating .star_rank");
}

Rating.prototype.setScore = function(score) {
	this.stars[score - 1].checked = false;
	for (var i = score - 1; i >= 0; i--) {
		this.stars[i].classList.add("checked");
	}
	for (var i = score; i < 5; i++) {
		this.stars[i].classList.remove("checked");
	}
	this.starRank.innerText = score;
	if (score > 0) {
		this.starRank.classList.remove("gray_star");
	}
}
