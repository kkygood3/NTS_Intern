/**
 * Copyright 2019 NAVER Corp. All rights reserved. Except in the case of
 * internal use for NAVER, unauthorized use of redistribution of this software
 * are strongly prohibited.
 *
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */
var ReviewWritePage = /** @class */ (function () {
    function ReviewWritePage() {
        this.reviewPlaceHolder = document.querySelector(".review_write_info");
        this.reviewTextArea = document.querySelector(".review_textarea");
        this.imageList = [];
        this.makeReviewBtn = document.querySelector(".bk_btn");
        this.starController = new StarController(document.querySelector(".rating"));
        new ReviewTextArea(this.reviewPlaceHolder, this.reviewTextArea);
        new FileUploader(this.imageFilePreviewWrapper, this.imagePreviewTemplate, this.imageList);
        new MakeReviewBtn(this.makeReviewBtn, this.starController, this.reviewTextArea, this.imageList);
    }
    return ReviewWritePage;
}());
var MakeReviewBtn = /** @class */ (function () {
    function MakeReviewBtn(button, starController, reviewTextArea, imageList) {
        var _this = this;
        this.button = button;
        this.button.addEventListener("click", function (e) {
            if (!(/^[ㄱ-ㅎ가-힣a-zA-Z0-9_-]{5,400}$/).test(reviewTextArea.value)) {
                alert("한줄평이 너무 짧거나 잘못된 글자입니다");
                return;
            }
            var formData = new FormData();
            formData.append("score", starController.getStarValue());
            formData.append("comment", reviewTextArea.value);
            formData.append("productId", _this.button.dataset.productid);
            formData.append("reservationInfoId", _this.button.dataset.reservationinfoid);
            imageList.forEach(function (item) {
                formData.append("imageFiles", item);
            });
            var xhr = new XhrRequest("POST", "/reservation/api/comment");
            xhr.setCallback(function () {
                alert("done");
                window.location.href = "/reservation/myreservation";
            }, function () {
                alert("error");
            });
            xhr.setIsAsync(false);
            xhr.multipartSend(formData);
        });
    }
    return MakeReviewBtn;
}());
var ReviewTextArea = /** @class */ (function () {
    function ReviewTextArea(reviewPlaceHolder, reviewTextArea) {
        reviewTextArea.addEventListener("focusout", function () {
            if (reviewTextArea.value.trim().length == 0) {
                reviewTextArea.value = "";
                reviewPlaceHolder.style.display = "";
            }
        });
        reviewPlaceHolder.addEventListener("click", function () {
            reviewPlaceHolder.style.display = "none";
            reviewTextArea.focus();
        });
    }
    return ReviewTextArea;
}());
var FileUploader = /** @class */ (function () {
    function FileUploader(imageFilePreviewWrapper, imagePreviewTemplate, imageList) {
        this.imageFilePreviewWrapper = document.querySelector(".lst_thumb");
        this.imagePreviewTemplate = document.querySelector("#previewItem").innerHTML;
        this.imageFileInput = document.querySelector("#imagesToUpload");
        this.attachProcess(imageList);
    }
    FileUploader.prototype.attachProcess = function (imageList) {
        var _this = this;
        this.imageFileInput.addEventListener("change", function (evt) {
            var images = evt.target.files;
            for (var i = 0; i < images.length; i++) {
                if (!_this.validImageType(images[i])) {
                    console.warn("invalid image file type");
                    return;
                }
            }
            for (var i = 0; i < images.length; i++) {
                if (!_this.validImageType(images[i])) {
                    console.warn("invalid image file type");
                    return;
                }
                imageList.push(images[i]);
            }
            console.log(imageList);
            while (_this.imageFilePreviewWrapper.firstChild) {
                _this.imageFilePreviewWrapper.removeChild(_this.imageFilePreviewWrapper.firstChild);
            }
            arrayToElementRenderer(imageList, _this.imageFilePreviewWrapper, _this.imagePreviewTemplate, null, function (item, index) { new ImageItem(item, imageList, index); });
        });
    };
    FileUploader.prototype.validImageType = function (image) {
        var result = (['image/jpeg',
            'image/png',
            'image/jpg'].indexOf(image.type) > -1);
        return result;
    };
    return FileUploader;
}());
var ImageItem = /** @class */ (function () {
    function ImageItem(imageItem, imageList, fileIndex) {
        var _this = this;
        this.imageItem = imageItem;
        this.fileIndex = fileIndex;
        this.imageItem.addEventListener("click", function (e) {
            var target = e.target;
            if (target.classList.contains("ico_del")) {
                _this.removeProcess(imageList);
            }
        });
    }
    ImageItem.prototype.removeProcess = function (imageList) {
        console.log(imageList);
        imageList.splice(this.fileIndex, 1);
        console.log(imageList);
        this.imageItem.parentNode.removeChild(this.imageItem);
    };
    return ImageItem;
}());
var StarController = /** @class */ (function () {
    function StarController(starListWrapper) {
        this.starListWrapper = starListWrapper;
        this.starList = starListWrapper.querySelectorAll(".rating input");
        this.starValue = starListWrapper.querySelector(".star_rank");
        this.starListInit();
    }
    StarController.prototype.starListInit = function () {
        var _this = this;
        this.starListWrapper = document.querySelector(".rating");
        this.renderStar(0);
        this.starListWrapper.addEventListener("click", function (e) {
            var target = e.target;
            if (target.classList.contains("rating_rdo")) {
                _this.renderStar(target.value);
            }
        });
    };
    StarController.prototype.renderStar = function (value) {
        if (value == Number(this.starValue.innerHTML)) {
            value = 0;
        }
        this.starList.forEach(function (item) {
            if (item.value > value) {
                item.checked = false;
            }
            else {
                item.checked = true;
            }
        });
        this.starValue.innerHTML = String(value);
    };
    StarController.prototype.getStarValue = function () {
        console.log(Number(this.starValue.innerText));
        return Number(this.starValue.innerText);
    };
    return StarController;
}());
var reviewWritePage = new ReviewWritePage();
