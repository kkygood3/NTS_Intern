/**
 * @author 육성렬
 */

document.addEventListener("DOMContentLoaded", function() {
    addCommentPage.init();
});

var addCommentPage = {
    elements: {
        addCommentBtn: document.querySelector(".bk_btn")
    },
    objs: {
        textareaObj: null,
        ratingObj: null,
        thumbnailImageListObj: null
    },
    init: function() {
        this.initTextareaUi();
        this.initRatingUi();
        this.initThumbnailImageList();
        this.initAddCommentBtn();
    },
    initTextareaUi: function() {
        var container = document.querySelector(".review_contents ");
        var textLengthUi = document.querySelector(".guide_review > span");
        this.objs.textareaObj = new InputTextareaUi(container, textLengthUi);
    },
    initRatingUi: function() {
        var container = document.querySelector(".rating");
        this.objs.ratingObj = new RatingUi(container);
    },
    initThumbnailImageList: function() {
        var container = document.querySelector(".review_photos");
        var inputImageBtn = document.querySelector("#reviewImageFileOpenInput");
        this.objs.thumbnailImageListObj = new ThumbnailImageList(container, inputImageBtn);
    },
    initAddCommentBtn: function() {
        var addCommentBtn = this.elements.addCommentBtn;
        var self = this;
        addCommentBtn.addEventListener("click", function(event) {
            self.onClickAddCommentBtn(event);
        })
    },
    requestAddComment: function(formData) {
        var path = "/reservation-service/api/reservations/"
                         + document.querySelector("#container").dataset.reservationId + "/comments";

        var self = this;
        httpUtil.sendPostWithMultiPartForm(path, formData, function(response) {
            self.requestAddCommentCallback(response)
        })
    },
    requestAddCommentCallback: function(response) {

        if (response.status === 200) {
            var result = JSON.parse(response.responseText);
            window.location.replace(result.redirectUri);
        } else {
            alert("코멘트 등록에 실패했습니다.");
        }
    },
    onClickAddCommentBtn: function(event) {
        var formData = this.getCommentFormdData();
        if(!this.isValidCommentData(formData)){
            return;
        }
        this.requestAddComment(formData);
    },
    getCommentFormdData: function() {

        var formData = new FormData();
        var attachedImages = this.objs.thumbnailImageListObj.getAllImageData();
        attachedImages.forEach(function(image) {
            formData.append("attachedImages", image);
        })
        formData.append("comment", this.objs.textareaObj.getTextData());
        formData.append("score", this.objs.ratingObj.getScoreData());
        formData.append("productId", document.querySelector("#container").dataset.productId);
        
        return formData;
    },
    isValidCommentData: function(formData) {
        if(!regex.WORD_MAX_400_REGEX.test(formData.get("comment"))) {
            alert("코멘트가 잘못 입력되었습니다. (5자 이상 400자 이하)");
            return false;
        }

        return true;
    }
}

var RatingUi = (function() {
    function RatingUi (container) {
        this.container = container;
        this.ratingScoreUi = this.container.querySelector(".star_rank");
        this.ratingCheckboxs = this.container.querySelectorAll("input");

        this.addEventListeners();
    }
    
    RatingUi.prototype.getScoreData = function() {
        return Number(this.ratingScoreUi.innerHTML);
    }

    RatingUi.prototype.addEventListeners = function() {
        this.ratingCheckboxs.forEach(function(checkbox) {
            checkbox.addEventListener("click", function(event) {
                this.onClickRatingCheckbox(event);
            }.bind(this));
        }.bind(this));
    }

    RatingUi.prototype.onClickRatingCheckbox = function(event) {
        event.preventDefault();

        this.uncheckedAllCheckbox();
        var score = event.target.value;
        for(var i = 0; i < score; i++){
            this.ratingCheckboxs[i].classList.add("checked");
        }
        this.ratingScoreUi.innerHTML = score;
    }

    RatingUi.prototype.uncheckedAllCheckbox = function() {
        this.ratingCheckboxs.forEach(function(checkbox) {
            checkbox.classList.remove("checked");
        })
    }

    return RatingUi;
})();

var InputTextareaUi = (function() {
    function InputTextareaUi (container, textLengthUi) {
        this.container = container
        this.placeholderView = this.container.querySelector(".review_write_info");
        this.inputTextarea = this.container.querySelector(".review_textarea");
        this.textLengthUi = textLengthUi;

        this.addEventListeners();
    }
    
    InputTextareaUi.prototype.getTextData = function() {
        return this.inputTextarea.value;
    }

    InputTextareaUi.prototype.addEventListeners = function() {
        
        var self = this;
        this.placeholderView.addEventListener("click", function(event) {
            self.onClickPlaceholderView(event);
        });

        this.inputTextarea.addEventListener("input", function(event) {
            self.onInputTextarea(event);
        });

    }

    InputTextareaUi.prototype.onInputTextarea = function(event) {
        if(this.inputTextarea.value.length > 400 ) {
            this.inputTextarea.value = this.inputTextarea.value.substring(0, 400);
        }
        this.textLengthUi.innerHTML = this.inputTextarea.value.length;
    }

    InputTextareaUi.prototype.onClickPlaceholderView = function(event) {

        this.setVisiblePlaceholderView(false);
        this.inputTextarea.focus();
    }

    InputTextareaUi.prototype.setVisiblePlaceholderView = function(visible) {
        if (visible) {
            this.placeholderView.style.display = "";
        }else {
            this.placeholderView.style.display = "none";
        }
    }

    return InputTextareaUi;
})();

var ThumbnailImageList = (function() {
    function ThumbnailImageList(container, inputImageBtn) {
        
        this.container = container;
        this.inputImageBtn = inputImageBtn;
        this.imageList = [];
        this.fileList = [];
        this.thumbnailTemlate = getTargetTemplate("#commentImageItem");
        this.thumnailContainer = this.container.querySelector(".lst_thumb");
        var self = this;
        this.inputImageBtn.addEventListener("change", function(event) {
            self.onChangeInputImage(event);
        })
    }

    ThumbnailImageList.prototype.getAllImageData = function() {
        return this.fileList;
    }

    ThumbnailImageList.prototype.onChangeInputImage = function(event) {
        
        var fileList = event.target.files;
        for(var i = 0; i < fileList.length; i++){
            if(!this.isValidImageType(fileList[i])){
                alert("이미지 파일이 아닌 파일이 있습니다.")
                return;
            }
        }
        this.addThumbnailImages(fileList);
        this.inputImageBtn.value = null;
    }

    ThumbnailImageList.prototype.addThumbnailImages = function(fileList) {
        var parser = new DOMParser();
        for(var i = 0; i < fileList.length; i++){
            this.fileList.push(fileList[i]);
            var param = {
                filePath: window.URL.createObjectURL(fileList[i]),
                fileName: fileList[i].name
            }
            var thumbElement = parser.parseFromString(this.thumbnailTemlate(param).trim(), "text/html").body.firstChild;
            this.imageList.push(thumbElement);
            this.thumnailContainer.appendChild(thumbElement);
            this.addThumbClickEventListener(thumbElement);
        }
    }

    ThumbnailImageList.prototype.addThumbClickEventListener = function (thumb) {
        var self = this;
        thumb.addEventListener("click", function (event) {

            var targetClassList = event.target.classList;
            if (!targetClassList.contains("anchor") && !targetClassList.contains("spr_book")) {
                return;
            }
            var targetIndex = -1;
            self.imageList = self.imageList.filter(function(image, index) {
                if(image === thumb){
                    targetIndex = index;
                    return false;
                }
                return true;
            });

            if(targetIndex >= 0) {
                self.fileList = self.fileList.splice(targetIndex, 1);
            }
            self.thumnailContainer.removeChild(thumb);
        })
    }

    ThumbnailImageList.prototype.isValidImageType = function(image) {
        return regex.IMAGE_CONTENT_TYPE_REGEX.test(image.type);
    }

    return ThumbnailImageList;
})();