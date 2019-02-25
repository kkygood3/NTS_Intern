class ReviewContents{
    constructor(){
        this.MAX_TEXT_LENGTH = 400;
        this.reviewContentsElement = document.querySelector(".review_contents");
        this.reviewTextArea = this.reviewContentsElement.querySelector(".review_textarea");
        this.reviewWriteInfo = this.reviewContentsElement.querySelector(".review_write_info");
        this.addReviewWriteInfoClickEvent();
        this.addContentsTextAreaKeyUpEvent();
    }

    /**
     * @desc review write click event
     */
    addReviewWriteInfoClickEvent(){
        this.reviewWriteInfo.addEventListener("click", event => {
            this.reviewWriteInfo.classList.add("hide");
            this.reviewTextArea.focus();
        });
    }

    /**
     * @desc review contents textarea keyup event
     */
    addContentsTextAreaKeyUpEvent(){
        const wordCount = document.querySelector("#word_count");
        this.reviewTextArea.addEventListener("keyup", event => {

            const reviewText = this.reviewTextArea.value;
            
            if(reviewText.length > this.MAX_TEXT_LENGTH){
                
                event.preventDefault();
                alert("글자수를 400자로 맞춰주세요");
                
                this.reviewTextArea.value = reviewText.substring(0,this.MAX_TEXT_LENGTH);
                wordCount.innerText = this.MAX_TEXT_LENGTH;
                this.reviewTextArea.focus();
            } else {

                wordCount.innerText = reviewText.length;
            }
        });
    }

}

export default ReviewContents