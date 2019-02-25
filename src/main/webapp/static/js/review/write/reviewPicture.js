
const handlebarsFunction = require("../../handlebarsFunction");

class ReviewPicture{

    constructor(){
        this.reviewPicElement = document.querySelector("#reviewImageFileOpenInput");
        this.fileList = [];
        this.addReviewPictureChangeEvent();
    }

    /**
     * @desc review picture change event
     */
    addReviewPictureChangeEvent(){
        const thumbnailUl = document.querySelector(".lst_thumb");

        this.reviewPicElement.addEventListener("change", event => {
            
            if(this.fileList.length == 0){
                const image = event.target.files[0];
                
                if(this._invalidateImageType(image.type)){
                    alert("형식에 맞지 않은 파일입니다. image/jpeg, image/png, image/jpg 파일만 가능합니다.");
                } else {
                    thumbnailUl.innerHTML += handlebarsFunction.getHandlebarTemplateFromHtml('#review_picture_template',{imageUri: window.URL.createObjectURL(image)});
                    this.fileList.push(image);
                    this._addRemoveFileClickEvent(thumbnailUl);        
                }
            }
        });
    }

    /**
     * @desc image type validation
     * @param {String} imageType 
     */
    _invalidateImageType(imageType) {
        return ([ 'image/jpeg','image/png','image/jpg' ].indexOf(imageType) < 0);
    }

    /**
     * @desc file 삭제 event
     * @param {DOMElement} thumbnailUl 
     */
    _addRemoveFileClickEvent(thumbnailUl){
        document.querySelector(".ico_del").addEventListener("click", event => {
            thumbnailUl.querySelector(".item").remove();
            this.fileList.pop();
        });
    }
}

export default ReviewPicture