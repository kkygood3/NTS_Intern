function addReviewWriteInfoClickEvent() {
	var reviewWriteInfo = document.querySelector(".review_contents.write > .review_write_info");
	reviewWriteInfo.addEventListener("click", function(event){
		reviewWriteInfo.style.display = "none";
		var reviewTextarea = document.querySelector(".review_contents.write > .review_textarea");
		reviewTextarea.focus();
	});
}


function addRatingClickEvent() {
	var reviewRating = document.querySelector(".review_rating.rating_point");
	var starRank = document.querySelector(".rating .star_rank");
	reviewRating.addEventListener("click", function(event){
		if (event.target.tagName != "INPUT") {
			return;
		}
		var stars = reviewRating.querySelectorAll(".rating_rdo");
		var rating = event.target.value;
		stars[rating - 1].checked = false;
		for (var i = rating - 2; i >= 0; i--) {
			stars[i].classList.add("checked");
		}
		for (var i = rating; i < 5; i++) {
			stars[i].classList.remove("checked");
		}
		if (rating == starRank.innerText) {
			stars[--rating].classList.remove("checked");
		} else {
			stars[rating - 1].classList.add("checked");
		}
		starRank.innerText = rating;
		if (rating == 0) {
			starRank.classList.add("gray_star");
		} else {
			starRank.classList.remove("gray_star");
		}
	});
}