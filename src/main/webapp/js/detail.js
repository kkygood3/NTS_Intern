document.addEventListener("DOMContentLoaded", function() {
	detailPage.getDetailPage(detailPage.displayInfoId);
});

var detailPage = {
	getDetailPage: function(displayInfoId){
		this.compileHendlebars.compareDiscountRateToZero();
		this.compileHendlebars.noticeDiscountRate();
		
		ajaxSend("GET", "/reservation/api/products/" + displayInfoId, this.displayContents, "charset=utf-8");
		
		this.setEvent.bkBtnEvent();
		this.setEvent.scrollTop();
	},
	
	displayContents: function(data){
		this.detailPage.displayMainInfo(data);
		this.detailPage.displayDiscountInfo(data);
		this.detailPage.displayComments(data);
		this.detailPage.displayDetailInfo(data);
	}.bind(this),
		
	displayInfoId : window.location.href.match(/detail\/\d+/)[0].split("/")[1],
	
	constants: {
		CNT_MAIN_IMAGE : 2,
		CONTENT_CONTAINER_HEIGHT : 73,
		SLIDE_IMG_WIDTH : 414,
		CNT_COMMENTS_AT_MAIN_PAGE : 3
	},

	elements: {
		displayTitle : document.querySelector(".display_title"),
		cnt : document.querySelector(".count_detail_image"),
		content : document.querySelector(".dsc"),
		star_rating : document.querySelector(".graph_value"),
		num_rating : document.querySelector(".text_value"),
		introduce : document.querySelector(".detail_info_lst").querySelector(".in_dsc"),
		cntComment : document.querySelector(".green"),
		
		btnPrev : document.querySelector(".btn_prev"),
		btnNxt : document.querySelector(".btn_nxt"),
		
		btnOpen : document.querySelector(".bk_more._open"),
		btnClose : document.querySelector(".bk_more._close"),
		
		btnBooking : document.querySelector(".bk_btn"),
		btnMoreReview : document.querySelector(".btn_review_more"),
		
		tabUi : document.querySelector(".info_tab_lst"),
		detailInfo : document.querySelector(".detail_area_wrap"),
		locationInfo : document.querySelector(".detail_location"),

		storeMap : document.querySelector(".store_map"),
		storeName : document.querySelector(".store_name"),
		addrStreet : document.querySelector(".store_addr_bold"),
		addrOld : document.querySelector(".addr_old_detail"),
		placeName : document.querySelector(".store_addr.addr_detail"),
		telephone : document.querySelector(".store_tel"),
		
		btnTop : document.querySelector(".lnk_top")
	},
	
	container: {
		contentContainer : document.querySelector(".store_details"),
		visualImgContainer : document.querySelector(".visual_img.detail_swipe"),
		discountContainer : document.querySelector(".in_dsc"),
		commentsContainer : document.querySelector(".list_short_review")
	},
	
	template: {
		visualImgTemplate : document.querySelector("#visualImgTemplate").innerHTML,
		discountTemplate : document.querySelector("#discountTemplate").innerHTML,
		commentTemplate : document.querySelector("#commentTemplate").innerHTML
	},
	
	displayMainInfo: function(jsonResponse){
		var bindVisualImgs = this.compileHendlebars.bindTemplate(this.template.visualImgTemplate);
		
		if(jsonResponse["productImages"].length > 1){
			this.elements.cnt.innerHTML = this.constants.CNT_MAIN_IMAGE;
		}
		if(jsonResponse["productImages"].length === 1){
			this.elements.btnPrev.style.display = "none";
			this.elements.btnNxt.style.display = "none";
		}
		
		this.elements.displayTitle.innerHTML = jsonResponse["displayInfo"].productDescription;
		this.container.visualImgContainer.innerHTML = bindVisualImgs(jsonResponse);
		this.setEvent.carousel();
		
		this.elements.content.innerHTML = jsonResponse["displayInfo"].productContent;
		if(this.elements.content.scrollHeight < this.constants.CONTENT_CONTAINER_HEIGHT){
			this.elements.btnOpen.style.display = "none";
		}
		
		this.setEvent.openClose();
	},
	
	displayDiscountInfo: function(jsonResponse){
		var bindDiscounts = this.compileHendlebars.bindTemplate(this.template.discountTemplate);

 		this.container.discountContainer.innerHTML = bindDiscounts(jsonResponse);
		if(this.container.discountContainer.querySelector(".discountInfo").innerText.length === 0){
			this.container.discountContainer.parentNode.parentNode.style.display = "none";
		}
	},
	
	displayComments: function(jsonResponse){
		var bindComments = this.compileHendlebars.bindTemplate(this.template.commentTemplate);
		
		this.elements.star_rating.style.width = (jsonResponse["averageScore"] / 5) * 100 + "%"
		this.elements.num_rating.innerHTML = "<span>" + jsonResponse["averageScore"] + "</span>";

		this.elements.cntComment.innerHTML = jsonResponse["comments"].length + "건";

		jsonResponse["comments"].forEach(function(comment){
			comment.score = comment.score.toFixed(1);
		});
		
		this.container.commentsContainer.innerHTML = bindComments(jsonResponse);
		if(this.container.commentsContainer.querySelectorAll("li").length < this.constants.CNT_COMMENTS_AT_MAIN_PAGE){
			this.elements.btnMoreReview.style.display = "none";
		} else {
			this.elements.btnMoreReview.href = this.displayInfoId + "/reviews";
		}
	},
	
	displayDetailInfo: function(jsonResponse){
		this.elements.introduce.innerHTML = jsonResponse["displayInfo"].productContent;
		
		this.elements.storeMap.src = "/reservation/" + jsonResponse["displayInfoImage"].saveFileName;
		this.elements.storeName.innerHTML = jsonResponse["displayInfo"].productDescription;
		this.elements.addrStreet.innerHTML = jsonResponse["displayInfo"].placeStreet;
		this.elements.addrOld.innerHTML = jsonResponse["displayInfo"].placeLot;
		this.elements.placeName.innerHTML = jsonResponse["displayInfo"].placeName;
		this.elements.telephone.innerHTML = jsonResponse["displayInfo"].telephone;
		
		this.setEvent.tabEvent();
	},
	
	compileHendlebars: {
		bindTemplate : function(template){
			return Handlebars.compile(template);
		},
		
		priceType : {
			A : "성인",
			Y : "청소년",
			B : "유아",
			D : "장애인",
			C : "지역주민",
			E : "어얼리버드",
			V : "VIP",
			R : "R석",
			S : "S석"
		},
		
		noticeDiscountRate : function(){
			Handlebars.registerHelper('noticeDiscountRate', function(typeName, discountRate) {
				if(this.priceType[typeName] !== undefined && this.priceType[typeName] !== null) {
				    return this.priceType[typeName] + " " + discountRate + "%";
				}
			}.bind(this));
		},

 		compareDiscountRateToZero: function(){
			Handlebars.registerHelper('ifNotZero', function(value, options) {
				if(value !== 0) {
				    return options.fn(this);
				} else {
					return options.inverse(this);
				}
			});
		}
	},
	
	setEvent: {
		openClose : function(){
			this.detailPage.elements.btnOpen.addEventListener("click", function(){
				this.detailPage.elements.btnOpen.style.display = "none";
				this.detailPage.elements.btnClose.style.display = "block";
				this.detailPage.container.contentContainer.classList.remove("close3");
			}.bind(this));
			this.detailPage.elements.btnClose.addEventListener("click", function(){
				this.detailPage.elements.btnClose.style.display = "none";
				this.detailPage.elements.btnOpen.style.display = "block";
				this.detailPage.container.contentContainer.classList.add("close3");
			}.bind(this));
		}.bind(this),
		
		scrollTop: function(){
			addScrollTopEvent(this.detailPage.elements.btnTop);
		}.bind(this),
		
		tabEvent: function(){
			this.detailPage.elements.tabUi.addEventListener("click", function(event){
					this.detailPage.setEvent.changeTab(event);
			}.bind(this));
		}.bind(this),
		
		carousel: function(){
			this.detailPage.elements.btnPrev.addEventListener("click", this.detailPage.imageSlide.slideRight);
			this.detailPage.elements.btnNxt.addEventListener("click", this.detailPage.imageSlide.slideLeft);
		}.bind(this),
		
		bkBtnEvent: function(){
			this.detailPage.elements.btnBooking.addEventListener("click", function(){
				location.href = this.detailPage.displayInfoId + "/reserve";
			}.bind(this));
		}.bind(this),
		
		changeTab: function(event){
			var anchorElement;
			var selectedCategoryId;
			var previousActive = document.querySelector(".anchor.active");
			
		 	if(event.target.classList.contains("anchor")){
				anchorElement = event.target;
			} else if(event.target.classList.contains("info_tab_text")){
				anchorElement = event.target.parentNode;
			} else {
				return;
			}

		 	previousActive.classList.remove("active");

		 	selectedCategoryId = anchorElement.parentNode.dataset.category;
			anchorElement.classList.add("active");

			if(anchorElement.classList.contains("_detail")){
				if(!this.detailPage.elements.locationInfo.classList.contains("hide")){
					this.detailPage.elements.locationInfo.classList.add("hide");
				}
				this.detailPage.elements.detailInfo.classList.remove("hide");
			} else if(anchorElement.classList.contains("_path")) {
				if(!this.detailPage.elements.detailInfo.classList.contains("hide")){
					this.detailPage.elements.detailInfo.classList.add("hide");
				}
				this.detailPage.elements.locationInfo.classList.remove("hide");
			}
		}.bind(this),
	},
	
	imageSlide: {
		visualImageIndex : 0,
		
		setCntImage: function(){
			return this.detailPage.container.visualImgContainer.querySelectorAll("li").length;
		}.bind(this),
		setImage: function(index){
			return this.detailPage.container.visualImgContainer.querySelector("li[data-index='" + index + "']");
		}.bind(this),
		
		slideRight: function(){
			var slideImageIndex = this.detailPage.imageSlide.visualImageIndex;
			var cntImages = this.detailPage.constants.CNT_MAIN_IMAGE;
			var prevImage;
			var nowImage = this.detailPage.imageSlide.setImage(slideImageIndex);
			
			if(slideImageIndex === 0){
				prevImage = this.detailPage.imageSlide.setImage(cntImages - 1);
			} else {
				prevImage = this.detailPage.imageSlide.setImage(slideImageIndex - 1);
			}
			
			prevImage.style.left = -this.detailPage.constants.SLIDE_IMG_WIDTH + "px";
			prevImage.style.visibility = "visible";
			prevImage.className = "item slide_right_in";
			nowImage.style.left = "0";
			nowImage.className = "item slide_right_out";

			this.detailPage.imageSlide.visualImageIndex = slideImageIndex - 1;
			if(this.detailPage.imageSlide.visualImageIndex < 0){
				this.detailPage.imageSlide.visualImageIndex = cntImages - 1; 
			}

			this.detailPage.imageSlide.updateIndex();
		}.bind(this),
		
		slideLeft: function(){
			var slideImageIndex = this.detailPage.imageSlide.visualImageIndex;
			var cntImages = this.detailPage.constants.CNT_MAIN_IMAGE;
			var nowImage = this.detailPage.imageSlide.setImage(this.detailPage.imageSlide.visualImageIndex);
			var nextImage = this.detailPage.container.visualImgContainer.querySelector("li[data-index='" + (slideImageIndex + 1) % cntImages + "']");
			
			nowImage.style.left = "0";
			nowImage.className = "item slide_left_out";
			nextImage.style.left = this.detailPage.constants.SLIDE_IMG_WIDTH + "px";
			nextImage.style.visibility = "visible";
			nextImage.className = "item slide_left_in";
			
			this.detailPage.imageSlide.visualImageIndex = (slideImageIndex + 1) % cntImages;
			
			this.detailPage.imageSlide.updateIndex();
		}.bind(this),
		
		updateIndex: function(){
			document.querySelector(".num").innerHTML = this.detailPage.imageSlide.visualImageIndex + 1;
		}.bind(this)
	}
}