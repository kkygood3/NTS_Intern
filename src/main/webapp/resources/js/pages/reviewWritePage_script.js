var ReviewWritePage = /** @class */ (function () {
    function ReviewWritePage() {
        this.reservationData = JSON.parse(document.querySelector("#_fetched_data").innerHTML);
        this.starController = new StarController(document.querySelector(".rating"));
        this.initReviewTextArea();
        new FileUploader(this.imageFilePreviewWrapper, this.imagePreviewTemplate, this.imageList);
    }
    ReviewWritePage.prototype.initReviewTextArea = function () {
        var _this = this;
        this.reviewPlaceHolder = document.querySelector(".review_write_info");
        this.reviewTextArea = document.querySelector(".review_textarea");
        this.reviewPlaceHolder.addEventListener("click", function () {
            _this.reviewPlaceHolder.style.display = "none";
            _this.reviewTextArea.focus();
        });
    };
    return ReviewWritePage;
}());
var FileUploader = /** @class */ (function () {
    function FileUploader(imageFilePreviewWrapper, imagePreviewTemplate, imageList) {
        this.imageList = imageList;
        this.imageFilePreviewWrapper = document.querySelector(".lst_thumb");
        this.imagePreviewTemplate = document.querySelector("#previewItem").innerHTML;
        this.imageFileInput = document.querySelector("#imagesToUpload");
        this.imageList = [];
        this.attachProcess();
    }
    FileUploader.prototype.attachProcess = function () {
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
                _this.imageList.push(images[i]);
            }
            arrayToElementRenderer(_this.imageList, _this.imageFilePreviewWrapper, _this.imagePreviewTemplate, null, function (item) { new ImageItem(item, _this.imageList); });
            _this.remove();
        });
    };
    FileUploader.prototype.validImageType = function (image) {
        var result = (['image/jpeg',
            'image/png',
            'image/jpg'].indexOf(image.type) > -1);
        return result;
    };
    FileUploader.prototype.remove = function () {
        console.log(this.imageList);
    };
    return FileUploader;
}());
var ImageItem = /** @class */ (function () {
    function ImageItem(imageItem, imageList) {
        this.imageList = imageList;
    }
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
        return Number(this.starValue.innerHTML);
    };
    return StarController;
}());
var reviewWritePage = new ReviewWritePage();
