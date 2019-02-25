const sendFileAjax = require("../../util/sendFileAjax");

class ReviewRegist {

    /**
     * @param {DOMElement} starRank 
     * @param {DOMElement} reviewTextArea
     * @param {Array} reviewPictureList
     */
    constructor(starRank,reviewTextArea,reviewPictureList){
        
        this.MIN_LENGTH = 5;
        this.MAX_LENGTH = 400;
        this.starRank = starRank;
        this.reviewTextArea = reviewTextArea;
        this.reviewPictureList = reviewPictureList;
        this.reviewButton = document.querySelector(".review_regist_button");
        this.addReviewRegistButtonClick();
    }

    /**
     * @desc review regist button click event
     */
    addReviewRegistButtonClick(){
        this.reviewButton.addEventListener("click", event => {

            if(this._validateRegistReview()){
                const reservationCommentHeader = {
                    method : "POST",
                    uri : "/api/reservations/"+ document.querySelector(".ct").dataset.reservationinfoid +"/comments" 
                }
                sendFileAjax(reservationCommentHeader,this._makeRequest()
                , successResponse => {
                    alert("리뷰가 성공적으로 삽입 되었습니다.");
                    window.location.href = "/reservations";   
                }, failResponse => {
                    alert("형식이 잘못됬거나, 맞지 않은 정보가 들어가 있습니다.");
                });
            }
        });
    }

    /**
     * @desc review validation check
     */
    _validateRegistReview(){
        const reviewText = this.reviewTextArea.value;
        if(reviewText.length < this.MIN_LENGTH && this.MAX_LENGTH){
            alert("리뷰 작성 내용은 5자 이상 400자 미만으로 작성해주세요");
            this.reviewTextArea.focus();
            return false;
        }
        return true;
    }

    /**
     * @desc data 전송시 formdata 생성
     * @return formData 
     */
    _makeRequest(){
        const formData = new FormData();
        formData.append("score",this.starRank.innerText );
        formData.append("comment", this.reviewTextArea.value);
        formData.append("productId",document.querySelector(".ct").dataset.productid);        
        
        this.reviewPictureList.forEach( picture => {
            formData.append("attachedImage", picture);
        });
        console.log(formData.get("attachedImage"));
        
        return formData;
    }
}

export default ReviewRegist