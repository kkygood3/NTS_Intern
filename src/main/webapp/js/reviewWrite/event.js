const COMMENT_MIN_LEN = 5;
const COMMENT_MAX_LEN = 400;

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
		if (isValidComment()) {
			enableSubmitButton();
		} else {
			disableSubmitButton();
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
		if (isValidComment()) {
			enableSubmitButton();
		} else {
			disableSubmitButton();
		}
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

function addSubmitButtonClickEvent() {
	const submitButton = document.querySelector(".box_bk_btn .bk_btn");
	submitButton.addEventListener("click", (event) => {
        const score = document.querySelector(".rating .star_rank").innerText;
        const comment = document.querySelector(".review_contents .review_textarea");
        
        if (isValidComment()) {
        	var form = document.querySelector("form");
        	var image = document.querySelector("#reviewImageFileOpenInput");
        	var scoreInput = document.querySelector(".score_form_input");
        	
        	scoreInput.value = score;
        	form.append(comment);
        	form.append(image);
        	
        	form.submit();
        }
	});	
}

function isValidComment() {
    const score = document.querySelector(".rating .star_rank").innerText;
    const comment = document.querySelector(".review_contents .review_textarea").value;
	return isValidScore(score) & isValidContent(comment);
}

function disableSubmitButton() {
	var submitButton = document.querySelector("div.box_bk_btn");
	submitButton.classList.add("disable");
}

function enableSubmitButton() {
	var submitButton = document.querySelector("div.box_bk_btn");
	submitButton.classList.remove("disable");
}

function isValidScore(score) {
	score *= 1;
	if (score == parseInt(score) && score > 0 && score <= 5) {
		return true;
	}
	return false;
}

function isValidContent(comment) {
	return !(/^\s*$/.test(comment)) && comment.length >= COMMENT_MIN_LEN && comment.length <= COMMENT_MAX_LEN;
}

function addContentKeyupAndKeydownEvent() {
    const content = document.querySelector(".review_contents .review_textarea");
    const contentLen = document.querySelector(".guide_review span");
    content.addEventListener("keyup", (event) => {
    	adjustCommentLength(content, contentLen);
	});
    content.addEventListener("keydown", (event) => {
    	adjustCommentLength(content, contentLen);
	});	
}

function adjustCommentLength(content, contentLen) {
	if (content.value.length > COMMENT_MAX_LEN) {
		content.value = content.value.substring(0, COMMENT_MAX_LEN);
	}
	contentLen.innerText = content.value.length;
	if (isValidComment()) {
		enableSubmitButton();
	} else {
		disableSubmitButton();
	}
}
