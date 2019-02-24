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
	var rating = new Rating();
	reviewRating.addEventListener("click", function(event){
		if (event.target.tagName != "INPUT") {
			return;
		}
		rating.setScore(event.target.value);
	});
}
