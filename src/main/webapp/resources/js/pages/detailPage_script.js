/**
 * Copyright 2019 NAVER Corp. All rights reserved. Except in the case of
 * internal use for NAVER, unauthorized use of redistribution of this software
 * are strongly prohibited.
 */

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

window.addEventListener('DOMContentLoaded', function () {
    detailPage.init();
});

var detailPage = {
    domElements: {
        slideWrapper: document.querySelector(".container_visual"),
        slideContainer: document.querySelector(".visual_img"),
        slideLeft: document.querySelector(".group_visual div div.prev"),
        slideRight: document.querySelector(".group_visual div div.nxt"),
        productContent: document.querySelector(".store_details p.dsc"),
        eventContainer: document.querySelector(".section_event"),
        averageScoreStars: document.querySelector(".graph_value"),
        averageScoreText: document.querySelector(".text_value span"),
        unfold: document.querySelector("._open"),
        fold: document.querySelector("._close"),
        figurePagination: document.querySelector(".figure_pagination"),
        reviewArea: document.querySelector(".list_short_review"),
        reviewCount: document.querySelector(".green"),
        infoTabUl: document.querySelector(".info_tab_lst"),
        infoTabLi: document.querySelectorAll(".info_tab_lst li"),
        bottomDescription: document.querySelector(".detail_info_group"),
        bottomPath: document.querySelector(".detail_location"),
        scrollToTop: document.querySelector(".gototop")
    },

    templates: {
        reviewItem: document.querySelector("#commentItem").innerHTML,
        slideContainerItem: document.querySelector("#imageItem").innerHTML,
    },

    constants: {
        DISPLAY_INFO_ID: "",
    },

    urls: {
        DETAIL: "/reservation/api/products/",
    },

    animation: "",

    state: {
        detail_data: "",
    },

    init: function () {
    	this.constants.DISPLAY_INFO_ID = new URL(window.location.href).searchParams.get("id");

    	this.fetchDetailData();
    	this.initButtons();
    },

    initButtons: function () {
        let detailTab = document.querySelector(".detail_area_wrap");
        let pathTab = document.querySelector(".detail_location");

        document.querySelector(".bk_btn").addEventListener("click", (e) => {
            window.location.href = "./reserve?id=" + this.constants.DISPLAY_INFO_ID;
        });

        this.domElements.infoTabUl.addEventListener("click", (e) => {
            let currentScroll = document.documentElement.scrollTop;
            if (e.target == this.domElements.infoTabUl) {
                return;
            }

            this.domElements.infoTabLi.forEach((item) => {
                let iter = item.firstElementChild;
                if (iter.classList.contains("active")) {
                    iter.classList.remove("active");
                }
            });
            let tab = e.target.closest("li");
            tab.firstElementChild.classList.add("active");

            if (tab.classList.contains("_detail")) {
                detailTab.classList.remove("hide");
                pathTab.classList.add("hide");
            } else if (tab.classList.contains("_path")) {
                pathTab.classList.remove("hide");
                detailTab.classList.add("hide");
            }
            document.documentElement.scrollTop = document.body.scrollTop = currentScroll;
        });
        scrollToTopAttacher(this.domElements.scrollToTop);
    },

    fetchDetailData: function () {
        let request = new XhrRequest("GET", this.urls.DETAIL + this.constants.DISPLAY_INFO_ID);
        request.setCallback((respText) => {
        	this.state.detail_data = JSON.parse(respText);

            this.renderDescription();

            this.renderBottomData();

            this.renderEventSection();

            this.renderImages();

            this.renderComments();
        });
        request.send();
    },

    /**
	 * @renderComments() : calling render function to render 3 comments as
	 *                   specification mentioned, and re-establishment of more
	 *                   comments button to id;
	 */
    renderComments: function () {
        document.querySelector(".btn_review_more").href = "./review?id=" + this.constants.DISPLAY_INFO_ID;
        this.domElements.reviewCount.innerHTML = this.state.detail_data.comments.length + "건";
        arrayToElementRenderer(this.state.detail_data.comments.slice(0, 3), this.domElements.reviewArea, this.templates.reviewItem);
    },

    /**
	 * @renderEventSection() : if there is no event specified, remove the
	 *                       corresponding section, else put event information
	 *                       inside the section
	 */
    renderEventSection: function () {
        if (this.state.detail_data.displayInfo.productEvent == "") {
        	this.domElements.eventContainer.innerHTML = "";
        } else {
        	this.domElements.eventContainer.querySelector(".dsc").innerHTML
                = this.state.detail_data.displayInfo.productEvent + "<br>" + mapPriceData(this.state.detail_data);
        }
    },

    /**
	 * @renderDescription() this puts description in the section right below the
	 *                      images carousel detect scroll height, and client
	 *                      height to check whether the string has more than 3
	 *                      lines. If there is less than 3 lines, hide the
	 *                      buttons.
	 */
    renderDescription: function () {
    	this.domElements.productContent.innerHTML = this.state.detail_data.displayInfo.productContent;
        if (this.domElements.productContent.clientHeight == this.domElements.productContent.scrollHeight) {
        	this.domElements.unfold.style.display = "none";
        	this.domElements.fold.style.display = "none";
        } else {
            let descArea = this.domElements.productContent.closest("div");
            this.domElements.unfold.addEventListener("click", (e) => {
                if (descArea.classList.contains("close3")) {
                    descArea.classList.remove("close3");
                }
                this.domElements.unfold.style.display = "none";
                this.domElements.fold.style.display = "";
            });

            this.domElements.fold.addEventListener("click", (e) => {
                if (!descArea.classList.contains("close3")) {
                    descArea.classList.add("close3");
                }
                this.domElements.unfold.style.display = "";
                this.domElements.fold.style.display = "none";
            });
        }
    },

    /**
	 * @renderBottomData() : put information into corresponding section
	 */
    renderBottomData: function () {
    	this.domElements.bottomDescription.querySelector(".in_dsc").innerHTML
            = this.state.detail_data.displayInfo.productContent;

    	this.domElements.bottomPath.querySelector(".store_map").src
            = this.state.detail_data.displayInfoImage.saveFileName;

    	this.domElements.bottomPath.querySelector(".store_name").innerHTML
            = this.state.detail_data.displayInfo.productDescription;

    	this.domElements.bottomPath.querySelector(".store_addr_bold").innerHTML
            = this.state.detail_data.displayInfo.placeStreet;

    	this.domElements.bottomPath.querySelector(".addr_old_detail").innerHTML
            = this.state.detail_data.displayInfo.placeLot;

    	this.domElements.bottomPath.querySelector(".addr_detail").innerHTML
            = this.state.detail_data.displayInfo.placeName;

        this.domElements.bottomPath.querySelector(".store_tel").innerHTML
            = this.state.detail_data.displayInfo.telephone;

        this.domElements.averageScoreStars.style.width
            = this.state.detail_data.averageScore / 5 * 100 + "%";

        this.domElements.averageScoreText.innerHTML
            = this.state.detail_data.averageScore;
    },

    /**
	 * @renderImages() : render and attach functionality to product images part
	 */
    renderImages: function () {
    	this.domElements.slideContainer.innerHTML = "";

        let imageList = this.state.detail_data.productImages;

        this.domElements.figurePagination.querySelector(".off span").innerHTML
            = imageList.length;

        arrayToElementRenderer(imageList, this.domElements.slideContainer, this.templates.slideContainerItem)
        animation = new SlidingAnimation(this.domElements.slideContainer);
        animation.init();

        if (imageList.length > 1) {
        	this.domElements.slideLeft.addEventListener("click", (e) => {
                if (!animation.isAnimating) {
                    animation.slide(
            		{
                    	isAutoStart : false, 
                    	isReverse : false,
                    	isResizing : true
                    });
                }
            });

        	this.domElements.slideRight.addEventListener("click", (e) => {
                if (!animation.isAnimating) {
                	animation.slide(
            		{
                    	isAutoStart : false, 
                    	isReverse : true,
                    	isResizing : true
                    });
                }
            });
        } else {
        	this.domElements.slideLeft.style.visibility = "hidden";
        	this.domElements.slideRight.style.visibility = "hidden";
        }
        document.querySelector(".visual_txt_tit span").innerHTML
            = this.state.detail_data.displayInfo.productDescription;
    }
}
