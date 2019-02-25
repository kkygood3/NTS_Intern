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

function addFileInputChangeEvent() {
	var fileInput = document.querySelector("#reviewImageFileOpenInput");
	fileInput.addEventListener("change", (event) => {
		const image = event.target.files[0];
		if(!validImageType(image)) { 
			console.warn("invalide image file type");
			return;
		}
        const elImage = document.querySelector(".item_preview_thumbs .item_thumb");
        const li = document.querySelector(".item_preview_thumbs .item");
        elImage.src = window.URL.createObjectURL(image);
        li.style.display = "";
	});
}

function validImageType(image) {
	return ([ "image/jpeg",
			  "image/png",
			  "image/jpg" ].indexOf(image.type) > -1);
}

function addFileDeleteButtonClickEvent() {
	var fileInput = document.querySelector("#reviewImageFileOpenInput");
	const fileDeleteButton = document.querySelector(".spr_book.ico_del");
	fileDeleteButton.addEventListener("click", (event) => {
        const li = document.querySelector(".item_preview_thumbs .item");
        li.style.display = "none";
        fileInput.value = "";
	});
}
