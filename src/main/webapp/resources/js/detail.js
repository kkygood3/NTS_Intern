/**
 * @author 육성렬
 */

document.addEventListener("DOMContentLoaded", function() {
    detailPage.init();
});

var detailPage = {
    values: {
        MAX_NUMBER_SHOW_COMMENT: 3,
        id: document.querySelector("#container").dataset.displayId
    },

    elements: {
        imageContainer: document.querySelector(".detail_swipe"),
        imageCurrentIndexTextUi: document.querySelector(".figure_pagination > .num"),
        imageMaxIndexTextUi: document.querySelector(".figure_pagination > .off > span"),
        prevBtn: document.querySelector(".prev_inn"),
        nextBtn: document.querySelector(".nxt_inn"),
        contentOpenMoreBtn: document.querySelector("._open"),
        contentCloseMoreBtn: document.querySelector("._close"),
        contentContainer: document.querySelector(".store_details"),
        eventTextUi: document.querySelector(".event_info > .in_dsc"),
        eventContainer: document.querySelector(".section_event"),
        detailTabBtn: document.querySelector("._detail > a"),
        contactTabBtn: document.querySelector("._path > a"),
        detailContainer: document.querySelector(".detail_area_wrap"),
        contactContainer: document.querySelector(".detail_location"),
        commentsContainer: document.querySelector(".section_review_list"),
        reservationBtn: document.querySelector(".section_btn > button")
    },

    init: function() {
        this.requestDisplayInfo();
        this.initReservationBtn();
    },

    /**
     * @function requestDisplayInfo 서버에 displayInfo 정보 요청
     */
    requestDisplayInfo: function() {
        var id = this.values.id;

        var self = this;
        sendGet( "/reservation-service/api/products/" + id, {}, {}, function(response) {
            self.requestDisplayInfoCallback(response);
        });
    },

    /**
     * @function responseDisplayInfo displayInfo 요청에 대한 처리.
     * @param {XMLHttpRequest} response
     */
    requestDisplayInfoCallback: function(response) {

        if (response.status == 200) {
            var data = JSON.parse(response.responseText);
            const { averageScore, comments, displayInfo, displayInfoImage, productImages, productPrices } = data;
            this.updateImageSlide(productImages, displayInfo.productDescription);
            this.updateImageSlideBtn(productImages);
            this.updateContentContainer(displayInfo.productContent);
            this.updateEventTextUi(productPrices);
            this.updateBottomSection(displayInfo, displayInfoImage);
            this.updateCommentsContainer(averageScore, comments);
            this.addEventListeners();
        } else if (response.status == 500) {
            alert("서버 내부 오류 : 상세 정보 로딩에 실패하였습니다.");
        } else {
            alert("잘못된 요청입니다.");
        }
    },

    /**
     * @function updateImageSlide 이미지들을 이용한 이미지 슬라이더 UI 업데이트
     * @param {JSON[]} images
     */
    updateImageSlide: function(images, description) {
        var bindTemplate = getTargetTemplate("#imageItem");
        var convertImages = images.reduce(function(prevValue, image) {
            var data = {
                imageUrl: "../" + image.saveFileName,
                description: description
            };
            return prevValue + bindTemplate(data).trim();
        }, "");

        var imageContainer = this.elements.imageContainer;
        imageContainer.innerHTML = convertImages;
        imageContainer.dataset.currentIndex = 0;
        imageContainer.dataset.maxIndex = images.length;

        var items = imageContainer.querySelectorAll(".item");
        items[0].classList.add("current_pos");
        for (var i = 1; i < items.length; i++) {
            items[i].classList.add("right_pos");
        }
        this.elements.imageCurrentIndexTextUi.innerHTML = Number(imageContainer.dataset.currentIndex) + 1;
        this.elements.imageMaxIndexTextUi.innerHTML = imageContainer.dataset.maxIndex;
    },

    /**
     * @function updateImageSlideBtn 이미지 슬라이더 양옆 버튼 UI 업데이트.
     * @param {JSON[]} images
     */
    updateImageSlideBtn: function(images) {
        var prevBtn = this.elements.prevBtn;
        var nextBtn = this.elements.nextBtn;

        if (images.length == 1) {
            prevBtn.style.display = "none";
            nextBtn.style.display = "none";
            return;
        }
    },

    /**
     * @function updateContentContainer 컨텐트 컨테이너 UI 업데이트
     * @param {String} contents
     */
    updateContentContainer: function(contents) {
        var contentContainer = this.elements.contentContainer;
        contentContainer.querySelector("p").innerHTML = contents;
    },

    /**
     * @function updateEventTextUi 이벤트 표기 부분 UI 업데이트
     * @param {JSON[]} priceInfos
     */
    updateEventTextUi: function(priceInfos) {
        var eventText = priceInfos.reduce(function(prevValue, priceInfo) {
            if (priceInfo.discountRate != 0) {
                return prevValue + priceInfo.priceTypeName + "석 " + priceInfo.discountRate + "%, ";
            }
        }, "");

        if (eventText != undefined) {
            eventText = eventText.substr(0, eventText.length - 2) + " 할인";
            this.elements.eventTextUi.innerHTML += eventText;
        } else {
            this.elements.eventContainer.hidden = true;
        }
    },

    /**
     * @function updateBottomSection 하단 영역 UI 업데이트
     * @param {JSON} displayInfo
     * @param {JSON} displayInfoImage
     */
    updateBottomSection: function(displayInfo, displayInfoImage) {
        var detailContainer = this.elements.detailContainer;
        var contactContainer = this.elements.contactContainer;
        this.bindDataToBottomSection(detailContainer, contactContainer, displayInfo, displayInfoImage);
    },

    /**
     * @function updateCommentsContainer 댓글 컨테이너 UI 업데이트
     * @param {Number} averageScore
     * @param {JSON[]} comments
     */
    updateCommentsContainer: function(averageScore, comments) {
        var commentsContainer = this.elements.commentsContainer;

        var averageScoreTextUi = commentsContainer.querySelector(".text_value > span");
        averageScoreTextUi.innerHTML = averageScore.toFixed(1);

        var scoreGraph = commentsContainer.querySelector(".graph_value");
        var graphValuePercent = Math.round((averageScore / 5) * 100);
        scoreGraph.style.width = graphValuePercent + "%";

        var countCommentsTextUi = commentsContainer.querySelector(".green");
        countCommentsTextUi.innerHTML = comments.length + "건";

        var commentListContainer = commentsContainer.querySelector(".list_short_review");
        commentListContainer.innerHTML = this.getCommentListHtml(comments);

        var moreCommentBtn = commentsContainer.querySelector(".btn_review_more");
        if (comments.length <= 3) {
            moreCommentBtn.className += " hide";
            return;
        }
        moreCommentBtn.setAttribute("href", "./" + this.values.id + "/review");
    },

    /**
     * @function initReservationBtn 예매하기 버튼 설정
     */
    initReservationBtn: function() {
        var self = this;
        this.elements.reservationBtn.addEventListener("click", function() {
            window.location.href = "./reservation/" + self.values.id;
        });
    },

    /**
     * @function onClickSlideBtn 이미지 슬라이더 버튼 클릭에 대한 콜백함수
     * @param {String} direction
     */
    onClickSlideBtn: function(direction) {
        var imageContainer = this.elements.imageContainer;
        var images = document.querySelectorAll(".product_image");
        var currentIndex = Number(imageContainer.dataset.currentIndex);
        var moveToNextStyle = "current_pos";
        var { nextIndex, nextStyle, moveToCurrentStyle } = this.getIndexAndStyleForNext(
            direction,
            currentIndex,
            images.length
        );

        this.changeSlideBtnClass(images[nextIndex], [nextStyle]);
        setTimeout(
            function() {
                var addToTransition = "add_transition";
                this.moveToNextSlideImage(
                    images[currentIndex],
                    [moveToCurrentStyle, addToTransition],
                    images[nextIndex],
                    [moveToNextStyle, addToTransition],
                    nextIndex
                );
            }.bind(this),
            100
        );
    },

    /**
     * @typedef {Object} ReturnIndexAndStyleForNext
     * @property {number} nextIndex
     * @property {String} nextStyle
     * @property {String} moveToCurrentStyle
     */
    /**
     * @function getIndexAndStyleForNext 이미지 슬라이드의 방향에 따른 NextIndex 및 스타일 클래스 명들을 리턴
     * @param {String} direction "right" Or "left" 값만 넣어야함.
     * @param {Number} currentIndex
     * @param {Number} imagesCount
     * @return {ReturnIndexAndStyleForNext}
     */
    getIndexAndStyleForNext(direction, currentIndex, imagesCount) {
        var nextIndex = 0;
        var nextStyle = "";
        var moveToCurrentStyle = "";
        if (direction == "right") {
            nextIndex = (currentIndex + 1) % imagesCount;
            nextStyle = "left_pos";
            moveToCurrentStyle = "right_pos";
        } else {
            nextIndex = currentIndex - 1 < 0 ? currentIndex - 1 + imagesCount : currentIndex - 1;
            nextStyle = "right_pos";
            moveToCurrentStyle = "left_pos";
        }
        return {
            nextIndex: nextIndex,
            nextStyle: nextStyle,
            moveToCurrentStyle: moveToCurrentStyle
        };
    },

    /**
     * @function moveToNextSlideImage 이미지를 슬라이드를 작동 시키고
     * 									라벨 값을 갱신한다.
     * @param {Element} currentImage
     * @param {String[]} currentImageClassNames
     * @param {Element} nextImage
     * @param {String[]} nextImageClassNames
     * @param {Element} imageContainer
     */
    moveToNextSlideImage: function(currentImage, currentImageClassNames, nextImage, nextImageClassNames, nextIndex) {
        this.changeSlideBtnClass(currentImage, currentImageClassNames);
        this.changeSlideBtnClass(nextImage, nextImageClassNames);
        this.elements.imageCurrentIndexTextUi.innerHTML = nextIndex + 1;
        this.elements.imageContainer.dataset.currentIndex = nextIndex;
    },

    /**
     * @function onClickBottomTabBtn 하단 탭 클릭 이벤트에 대한 처리를 해줌.
     * @param {Element} selectedTabBtn
     * @param {Element} deselectedTabBtn
     * @param {Element} selectedContainer
     * @param {Element} deselectedContainer
     */
    onClickBottomTabBtn: function(selectedTabBtn, deselectedTabBtn, selectedContainer, deselectedContainer) {
        if (selectedTabBtn.classList.contains("active")) {
            return;
        } else {
            selectedContainer.classList.remove("hide");
            deselectedContainer.classList.add("hide");
            selectedTabBtn.classList.add("active");
            deselectedTabBtn.classList.remove("active");
        }
    },

    /**
     * @function registerCommentImagesLogicForHelper commentImages 부분의 템플릿 제작을 위한 헬퍼등록 메소드
     * @param {JSON[]} commentImage
     */
    registerCommentImagesLogicForHelper: function(commentImages) {
        if (commentImages.length == 0) {
            return;
        }
        var bindTemplate = getTargetTemplate("#commentImageItem");
        var data = {
            imageUrl: "../" + commentImages[0].saveFileName
        };
        return bindTemplate(data).trim();
    },

    /**
     *
     * @param {Element} image
     * @param {String[]} classNames
     */
    changeSlideBtnClass: function(image, classNames) {
        image.className =
            "item product_image " +
            classNames.reduce(function(prevValue, className) {
                return prevValue + className + " ";
            }, "");
    },

    /**
     * @function bindDataToBottomSection 하단 섹션에 받은 데이터 UI에 바인딩.
     * @param {Element} detailContainer 상세정보 부분
     * @param {Element} contactContainer 오시는길 부분
     * @param {JSON} displayInfo
     * @param {JSON} displayInfoImage
     */
    bindDataToBottomSection: function(detailContainer, contactContainer, displayInfo, displayInfoImage) {
        var contentTextUi = detailContainer.querySelector(".in_dsc");
        contentTextUi.innerHTML = displayInfo.productContent;
        var mapImg = contactContainer.querySelector(".store_map");
        mapImg.setAttribute("src", "../" + displayInfoImage.saveFileName);
        var streetAddress = contactContainer.querySelector(".store_addr_bold");
        streetAddress.innerHTML = displayInfo.placeStreet;
        var oldAddress = contactContainer.querySelector(".addr_old_detail");
        oldAddress.innerHTML = displayInfo.placeLot;
        var placeName = contactContainer.querySelector(".addr_detail");
        placeName.innerHTML = displayInfo.placeName;

        if (displayInfo.telephone == "") {
            var telConatiner = document.querySelector(".lst_store_info_wrap");
            telConatiner.className += " hide";
        } else {
            var telTextUi = document.querySelector(".store_tel");
            telTextUi.innerHTML = displayInfo.telephone;
        }
    },

    /**
     * @function getCommentListHtml comments에 대한 html 값을 얻는다.
     * @param {JSON[]} comments
     * @return {String}
     */
    getCommentListHtml: function(comments) {
        var resultHtml = "";
        var bindTemplate = getTargetTemplate("#commentItem");

        var self = this;
        Handlebars.registerHelper( "commentImage", function(commentImages) {
            return self.registerCommentImagesLogicForHelper(commentImages);
        });

        for (var i = 0; i < this.values.MAX_NUMBER_SHOW_COMMENT; i++) {
            if (comments.length < i + 1) {
                break;
            }
            var comment = comments[i];
            var data = {
                coment: comment.comment,
                score: comment.score.toFixed(1),
                name: comment.reservationName,
                date: toDateString(comment.reservationDate) + "방문",
                commentImages: comment.commentImages
            };
            resultHtml += bindTemplate(data).trim();
        }
        return resultHtml;
    },

    /**
     * @function addEventListeners page 내 이벤트 리스너들 추가.
     */
    addEventListeners: function() {
        this.addSlideImageBtnEventListener();
        this.addShowMoreBtnEventListener();
        this.addBottomTabEventListener();
    },

    /**
     * @function addSlideImageBtnEventListener
     */
    addSlideImageBtnEventListener: function() {
        var prevBtn = this.elements.prevBtn;
        var nextBtn = this.elements.nextBtn;
        if (prevBtn.style.display !== "none" && nextBtn.style.display !== "none") {

            var self = this;
            prevBtn.addEventListener("click", function(event) {
                self.onClickSlideBtn("left");
            });
            nextBtn.addEventListener("click", function(event) {
                self.onClickSlideBtn("right");
            });
        }
    },

    /**
     * @function addShowMoreBtnEventListener
     */
    addShowMoreBtnEventListener: function() {
        var contentContainer = this.elements.contentContainer;
        var openMoreBtn = this.elements.contentOpenMoreBtn;
        var closeMoreBtn = this.elements.contentCloseMoreBtn;

        openMoreBtn.addEventListener("click", function() {
            contentContainer.classList.remove("close3");
            openMoreBtn.style.display = "none";
            closeMoreBtn.style.display = "";
        });
        closeMoreBtn.addEventListener("click", function() {
            contentContainer.classList.add("close3");
            openMoreBtn.style.display = "";
            closeMoreBtn.style.display = "none";
        });
    },

    addBottomTabEventListener: function() {
        var detailTabBtn = this.elements.detailTabBtn;
        var contactTabBtn = this.elements.contactTabBtn;
        var detailContainer = this.elements.detailContainer;
        var contactContainer = this.elements.contactContainer;

        var self = this;
        detailTabBtn.addEventListener("click", function() {
            self.onClickBottomTabBtn(detailTabBtn, contactTabBtn, detailContainer, contactContainer);
        });
        contactTabBtn.addEventListener("click", function() {
            self.onClickBottomTabBtn(contactTabBtn, detailTabBtn, contactContainer, detailContainer);
        });
    }
};
