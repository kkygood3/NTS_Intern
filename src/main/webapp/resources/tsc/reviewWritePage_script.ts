class ReviewWritePage {
    private reservationData : object;
    private starController : object;
    private reviewPlaceHolder : HTMLElement;
    private reviewTextArea : HTMLElement;
    private imageFileInput : HTMLElement;
    private imageFilePreviewWrapper : HTMLElement;
    private imagePreviewTemplate : String;
    private imageList : object;
    constructor(){
        this.reservationData = JSON.parse(document.querySelector("#_fetched_data").innerHTML);
        this.starController = new StarController(<HTMLElement> document.querySelector(".rating"));
        this.initReviewTextArea();
        new FileUploader(this.imageFilePreviewWrapper, this.imagePreviewTemplate, this.imageList);
    }
    initReviewTextArea(){
        this.reviewPlaceHolder = <HTMLElement> document.querySelector(".review_write_info");
        this.reviewTextArea = <HTMLElement> document.querySelector(".review_textarea");
        this.reviewPlaceHolder.addEventListener("click", () => {
            this.reviewPlaceHolder.style.display = "none";
            this.reviewTextArea.focus();
        });
    }
}

class FileUploader{
    private imageFilePreviewWrapper : HTMLElement;
    private imagePreviewTemplate : string;
    private imageFileInput : HTMLInputElement;
    private imageList : object[];
    constructor(imageFilePreviewWrapper, imagePreviewTemplate, imageList){
        this.imageList = imageList;
        this.imageFilePreviewWrapper = <HTMLElement> document.querySelector(".lst_thumb");
        this.imagePreviewTemplate = document.querySelector("#previewItem").innerHTML;
        this.imageFileInput = <HTMLInputElement> document.querySelector("#imagesToUpload");
        this.imageList = [];
        this.attachProcess();
    }
    attachProcess(){
        this.imageFileInput.addEventListener("change", (evt) => {
            var images = (<HTMLInputElement> evt.target).files;
            for (let i = 0; i < images.length; i++) {
                if(!this.validImageType(images[i])) { 
                    console.warn("invalid image file type");
                    return;
                }
            }
            
            for (let i = 0; i < images.length; i++) {
                if(!this.validImageType(images[i])) { 
                    console.warn("invalid image file type");
                    return;
                }
                this.imageList.push(images[i]);
            }
            arrayToElementRenderer(this.imageList, this.imageFilePreviewWrapper,this.imagePreviewTemplate, null ,(item) => {new ImageItem(item,this.imageList)});
            this.remove();
        });
    }
    validImageType(image) {
        const result = ([ 'image/jpeg',
                          'image/png',
                          'image/jpg' ].indexOf(image.type) > -1);
        return result;
    }
    remove(){
        console.log(this.imageList);
    }
}

class ImageItem {
    private imageItem : object;
    private imageList : object[];
    constructor(imageItem, imageList){
        this.imageList = imageList;
    }
}

class StarController{
    private starListWrapper : HTMLElement | any;
    private starList : NodeListOf<Element> | any;
    private starValue : HTMLElement | any;
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
        return Number(this.starValue.innerHTML);
    }
}

var reviewWritePage = new ReviewWritePage();
