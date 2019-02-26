/**
 * Copyright 2019 NAVER Corp. All rights reserved. Except in the case of
 * internal use for NAVER, unauthorized use of redistribution of this software
 * are strongly prohibited.
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

class ReviewWritePage {
    private starController : Object;
    private reviewPlaceHolder : HTMLElement;
    private reviewTextArea : HTMLElement;
    private imageFileInput : HTMLElement;
    private imageFilePreviewWrapper : HTMLElement;
    private imagePreviewTemplate : String;
    private imageList : Object[];
    private makeReviewBtn : HTMLElement;

    constructor(){
        this.reviewPlaceHolder = <HTMLElement> document.querySelector(".review_write_info");
        this.reviewTextArea = <HTMLElement> document.querySelector(".review_textarea");
        
        this.imageList = [];
        
        this.makeReviewBtn = <HTMLElement> document.querySelector(".bk_btn");
        
        this.starController = new StarController(<HTMLElement> document.querySelector(".rating"));
        
        new ReviewTextArea(this.reviewPlaceHolder, this.reviewTextArea);
        new FileUploader(this.imageFilePreviewWrapper, this.imagePreviewTemplate, this.imageList);
        new MakeReviewBtn(this.makeReviewBtn, this.starController, this.reviewTextArea, this.imageList);
    }
}

class MakeReviewBtn{
    private button : HTMLElement;

    constructor(button, starController, reviewTextArea ,imageList){
        this.button = button;
        this.button.addEventListener("click", (e) => {
            if(!(/^[ㄱ-ㅎ가-힣a-zA-Z0-9_-]{5,400}$/).test(reviewTextArea.value)){
                alert("한줄평이 너무 짧거나 잘못된 글자입니다");
                return;
            }
            let formData = new FormData();
            formData.append("score", starController.getStarValue());
            formData.append("comment", reviewTextArea.value);
            formData.append("productId", this.button.dataset.productid);
            formData.append("reservationInfoId",this.button.dataset.reservationinfoid);
            imageList.forEach((item)=>{
                formData.append("imageFiles",item);
            })

            let xhr = new XhrRequest("POST", "/reservation/api/comment");
            xhr.setCallback(() => {
                alert("done");
                window.location.href = "/reservation/myreservation"
            }, () => {
                alert("error");
            });
            xhr.setIsAsync(false);
            xhr.multipartSend(formData);
        });
    }
}

class ReviewTextArea{
    constructor(reviewPlaceHolder, reviewTextArea){        
        reviewTextArea.addEventListener("focusout", () => { 
            if(reviewTextArea.value.trim().length==0){
                reviewTextArea.value = "";
                reviewPlaceHolder.style.display = "";
            }
        });
        
        reviewPlaceHolder.addEventListener("click", () => {
            reviewPlaceHolder.style.display = "none";
            reviewTextArea.focus();
        });
    }
}

class FileUploader{
    private imageFilePreviewWrapper : HTMLElement;
    private imagePreviewTemplate : string;
    private imageFileInput : HTMLInputElement;
    private imageList : object[];

    constructor(imageFilePreviewWrapper, imagePreviewTemplate, imageList){
        this.imageFilePreviewWrapper = <HTMLElement> document.querySelector(".lst_thumb");
        this.imagePreviewTemplate = document.querySelector("#previewItem").innerHTML;
        this.imageFileInput = <HTMLInputElement> document.querySelector("#imagesToUpload");
        this.attachProcess(imageList);
    }
    attachProcess(imageList){
        this.imageFileInput.addEventListener("change", (evt) => {
            var images = (<HTMLInputElement> evt.target).files;
            
            for (let i = 0; i < images.length; i++) {
                if(!this.validImageType(images[i])) { 
                    alert("invalid image file type");
                    return;
                }
                imageList.push(images[i]);
            }
            
            while(this.imageFilePreviewWrapper.firstChild){
                this.imageFilePreviewWrapper.removeChild(this.imageFilePreviewWrapper.firstChild);
            }
            
            arrayToElementRenderer(imageList, this.imageFilePreviewWrapper,this.imagePreviewTemplate, null ,(item,index) => {new ImageItem(item,imageList,index)});
        });
    }
    validImageType(image) {
        const result = ([ 'image/jpeg',
                          'image/png',
                          'image/jpg' ].indexOf(image.type) > -1);
        return result;
    }
}

class ImageItem {
    private imageItem : HTMLElement;
    private fileIndex : number; 

    constructor(imageItem, imageList, fileIndex){
        this.imageItem = imageItem;
        this.fileIndex = fileIndex;
        this.imageItem.addEventListener("click", (e) => {
            let target = <HTMLElement> e.target;
            if(target.classList.contains("ico_del")){
                this.removeProcess(imageList);
            }
        });
    }
    removeProcess(imageList){
        imageList.splice(this.fileIndex,1);
        this.imageItem.parentNode.removeChild(this.imageItem);
    }
}

class StarController{
    private starListWrapper : HTMLElement;
    private starList : NodeListOf<Element> | any;
    private starValue : HTMLElement;

    constructor(starListWrapper : HTMLElement){
        this.starListWrapper = starListWrapper;
        this.starList = starListWrapper.querySelectorAll(".rating input");
        this.starValue = <HTMLElement> starListWrapper.querySelector(".star_rank");
        this.starListInit();
    }
    starListInit(){
        this.starListWrapper = <HTMLElement> document.querySelector(".rating");
        
        this.renderStar(0);
        this.starListWrapper.addEventListener("click", (e) => {
            let target :any = e.target;
            if(target.classList.contains("rating_rdo")){
                this.renderStar(target.value);
            }
        });
    }
    
    renderStar(value : Number){
        if(value == Number(this.starValue.innerHTML)){
            value = 0;
        }
        this.starList.forEach((item) => {
            if(item.value > value){
                item.checked=false;
            } else {
                item.checked=true;
            }
        });
        this.starValue.innerHTML = String(value);
    }
    
    getStarValue(){
        return Number(this.starValue.innerText) as number;
    }
}

var reviewWritePage = new ReviewWritePage();
