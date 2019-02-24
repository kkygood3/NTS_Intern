function addReviewWriteInfoClickEvent() {
	var reviewWriteInfo = document.querySelector(".review_contents.write > .review_write_info");
	var reviewTextarea = document.querySelector(".review_contents.write > .review_textarea");
	reviewWriteInfo.addEventListener("click", function(event){
		reviewWriteInfo.style.display = "none";
		reviewTextarea.focus();
	});
}

function addReviewTextareaBlurEvent() {
	var reviewWriteInfo = document.querySelector(".review_contents.write > .review_write_info");
	var reviewTextarea = document.querySelector(".review_contents.write > .review_textarea");
	reviewTextarea.addEventListener("blur", function(event){
		if (reviewTextarea.value == "") {
			reviewWriteInfo.style.display = "block";
		}
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
