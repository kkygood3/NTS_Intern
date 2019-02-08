document.addEventListener("DOMContentLoaded", function() {
	detailPage.getDetailPage(detailPage.displayInfoId);
});

var detailPage = {
	getDetailPage: function(displayInfoId){
		this.compileHendlebars.anonymizeUserId(this.constants.displayedIdLength);
		
		let httpRequest;
		
		if (window.XMLHttpRequest) {
			httpRequest =  new XMLHttpRequest();
			
			httpRequest.onreadystatechange = function() {
				let jsonResponse;
				
				if (httpRequest.readyState === 4 && httpRequest.status === 200) {
					jsonResponse = JSON.parse(httpRequest.responseText);
					
					this.displayMainInfo(jsonResponse);
					this.displayComments(jsonResponse);
					this.displayDetailInfo(jsonResponse);
				}
			}.bind(this)
			
			httpRequest.open("GET", "../api/products/" + displayInfoId);
			httpRequest.setRequestHeader("Content-type", "charset=utf-8");
			httpRequest.send();
		}
		
		this.setEvent.scrollTop();
	},
		
	displayInfoId : window.location.href.split("/").slice(-1)[0],
	
	constants: {
		cntMainImage : 2,
		displayedIdLength : 4,
		contentContainerHeight : 73,
		slideImageWidth : 414,
		commentsPerPage : 3
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
		contentContainer : document.querySelector(".store_details"),
		
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

		visualImgContainer : document.querySelector(".visual_img.detail_swipe"),
		commentsContainer : document.querySelector(".list_short_review"),
		
		btnTop : document.querySelector(".lnk_top"),
	},
	
	template: {
		visualImgTemplate : document.querySelector("#visualImgTemplate").innerHTML,
		commentTemplate : document.querySelector("#commentTemplate").innerHTML
	},
	
	displayMainInfo: function(jsonResponse){
		var bindVisualImgs = this.compileHendlebars.bindTemplate(this.template.visualImgTemplate);
		
		if(jsonResponse["productImages"].length > 1){
			this.elements.cnt.innerHTML = this.constants.cntMainImage;
		}
		if(jsonResponse["productImages"].length === 1){
			this.elements.btnPrev.style.display = "none";
			this.elements.btnNxt.style.display = "none";
		}
		
		this.elements.displayTitle.innerHTML = jsonResponse["displayInfo"].productDescription;
		this.elements.visualImgContainer.innerHTML = bindVisualImgs(jsonResponse);
		this.setEvent.carousel();
		
		this.elements.content.innerHTML = jsonResponse["displayInfo"].productContent;
		if(this.elements.content.scrollHeight < this.constants.contentContainerHeight){
			this.elements.btnOpen.style.display = "none";
		}
		
		this.setEvent.openClose();
	},
	
	displayComments: function(jsonResponse){
		var bindComments = this.compileHendlebars.bindTemplate(this.template.commentTemplate);
		
		this.elements.star_rating.style.width = (jsonResponse["averageScore"] / 5) * 100 + "%"
		this.elements.num_rating.innerHTML = "<span>" + jsonResponse["averageScore"] + "</span>";

		this.elements.cntComment.innerHTML = jsonResponse["comments"].length + "건";

		jsonResponse["comments"].forEach(function(comment){
			comment.score = comment.score.toFixed(1);
		});
		
		this.elements.commentsContainer.innerHTML = bindComments(jsonResponse);
		if(this.elements.commentsContainer.querySelectorAll("li").length < this.constants.commentsPerPage){
			this.elements.btnMoreReview.style.display = "none";
		} else {
			this.elements.btnMoreReview.href = "../review/" + this.displayInfoId;
		}
	},
	
	displayDetailInfo: function(jsonResponse){
		this.elements.introduce.innerHTML = jsonResponse["displayInfo"].productContent;
		
		this.elements.storeMap.src = "../" + jsonResponse["displayInfoImage"].saveFileName;
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
		
		anonymizeUserId: function(displayedIdLength){
			Handlebars.registerHelper('anonymize', function(context) {
				return context.substring(0, displayedIdLength) + "****";
			});
		}
	},
	
	setEvent: {
		openClose : function(){
			this.detailPage.elements.btnOpen.addEventListener("click", function(){
				this.detailPage.elements.btnOpen.style.display = "none";
				this.detailPage.elements.btnClose.style.display = "block";
				this.detailPage.elements.contentContainer.classList.remove("close3");
			}.bind(this));
			this.detailPage.elements.btnClose.addEventListener("click", function(){
				this.detailPage.elements.btnClose.style.display = "none";
				this.detailPage.elements.btnOpen.style.display = "block";
				this.detailPage.elements.contentContainer.classList.add("close3");
			}.bind(this));
		}.bind(this),
		
		scrollTop: function(){
			this.detailPage.elements.btnTop.addEventListener("click", function(){
				document.documentElement.scrollTop = 0;
			});
		}.bind(this),
		
		tabEvent: function(){
			this.detailPage.elements.tabUi.addEventListener("click", function(event){
				var anchorElement;
				var selectedCategoryId;
				var previousActive = document.querySelector(".anchor.active");
	
			 	if(event.target.className === "anchor"){
					anchorElement = event.target;
				} else if(event.target.className === "info_tab_text"){
					anchorElement = event.target.parentNode;
				} else {
					return;
				}
	
			 	previousActive.className = "anchor";
	
			 	selectedCategoryId = anchorElement.parentNode.dataset.category;
				anchorElement.className = "anchor active";
	
				if(anchorElement.innerText === "상세정보"){
					if(!this.detailPage.elements.locationInfo.className.includes("hide")){
						this.detailPage.elements.locationInfo.className = "detail_area_wrap hide";
					}
					this.detailPage.elements.detailInfo.className = this.detailPage.elements.detailInfo.className.replace("hide", "");
				} else if(anchorElement.innerText === "오시는길") {
					if(!this.detailPage.elements.detailInfo.className.includes("hide")){
						this.detailPage.elements.detailInfo.className = "detail_location hide";
					}
					this.detailPage.elements.locationInfo.className = this.detailPage.elements.locationInfo.className.replace("hide", "");
				}
			}.bind(this));
		}.bind(this),
		
		carousel: function(){
			this.detailPage.elements.btnPrev.addEventListener("click", this.detailPage.imageSlide.slideRight);
			this.detailPage.elements.btnNxt.addEventListener("click", this.detailPage.imageSlide.slideLeft);
		}.bind(this)
	},
	
	imageSlide: {
		visualImageIndex : 0,
		
		setCntImage: function(){
			return this.detailPage.elements.visualImgContainer.querySelectorAll("li").length;
		}.bind(this),
		setImage: function(index){
			return this.detailPage.elements.visualImgContainer.querySelector("li[data-index='" + index + "']");
		}.bind(this),
		
		slideRight: function(){
			var slideImageIndex = this.detailPage.imageSlide.visualImageIndex;
			var cntImages = this.detailPage.constants.cntMainImage;
			var prevImage;
			var nowImage = this.detailPage.imageSlide.setImage(slideImageIndex);
			
			if(slideImageIndex === 0){
				prevImage = this.detailPage.imageSlide.setImage(cntImages - 1);
			} else {
				prevImage = this.detailPage.imageSlide.setImage(slideImageIndex - 1);
			}
			
			prevImage.style.left = -this.detailPage.constants.slideImageWidth + "px";
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
			var cntImages = this.detailPage.constants.cntMainImage;
			var nowImage = this.detailPage.imageSlide.setImage(this.detailPage.imageSlide.visualImageIndex);
			var nextImage = this.detailPage.elements.visualImgContainer.querySelector("li[data-index='" + (slideImageIndex + 1) % cntImages + "']");
			
			nowImage.style.left = "0";
			nowImage.className = "item slide_left_out";
			nextImage.style.left = this.detailPage.constants.slideImageWidth + "px";
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