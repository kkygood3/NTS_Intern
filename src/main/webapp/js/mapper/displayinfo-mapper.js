function getContents(displayInfo){
	
	var contents = {
			
			displayInfo: displayInfo.displayInfo,
			
			init:function(){
				
				this.render();
				this.bindEvents();
				
			},
			
			render:function(){
				
				var contentParagraph = document.querySelector("#product_content_paragraph");
				var bind = Handlebars.compile(contentParagraph.innerText);
				contentParagraph.innerText = bind(this.displayInfo);
			},
			
			bindEvents:function(displayInfo){
				document.querySelector("#more_button_open").addEventListener("click", ()=>{
					moreOpenButtonDown(this.displayInfo);
					});
				document.querySelector("#more_button_close").addEventListener("click", ()=>{
					moreCloseButtonDown(this.displayInfo);
					});
			}
	}
	
	return contents;
}

function getProductImages(displayInfo){
	
	var productImages = {
			
			displayInfo: displayInfo,
			
			init:function(){
				
				this.render();
				
			},
			
			render:function(){
				
				document.querySelector("#product_image_count").innerText = 1;
				document.querySelector("#total_product_image_count").innerText = this.displayInfo.productImages.length;
				
				var backgroundImageUl = document.querySelector("#background_image_ul");
				var backgorundImageTemplate = document.querySelector("#background_images").innerText;
				
				var bindTemplate = Handlebars.compile(backgorundImageTemplate);
				var bindedHTML = bindTemplate(this.displayInfo).split("${description}").join(displayInfo.displayInfo.description);
				backgroundImageUl.innerHTML = bindedHTML;
				
				
				if(this.displayInfo.productImages.length > 1){
					
					this.bindEvents();
					
				} else{
					
					document.querySelector("#prev_button").setAttribute("class", "prev hide");
					document.querySelector("#next_button").setAttribute("class", "nxt hide");
					
				}
			},
			
			bindEvents:function(){
				
				var backGroundImageUl = document.querySelector("#background_image_ul");
				backGroundImageUl.innerHTML = backGroundImageUl.lastElementChild.outerHTML + backGroundImageUl.innerHTML;
				backGroundImageUl.style.right = "414px";
				document.querySelector("#prev_button").addEventListener("click", prevButtonDown);
				document.querySelector("#next_button").addEventListener("click", nextButtonDown);
				
			}
	}
	
	return productImages;
}

function getComments(displayInfo, maxCommentNum = -1){
	
	var comments = {
			
			displayInfo: displayInfo,
			
			init:function(){
				
				this.render(maxCommentNum);
				
			},
			
			render:function(maxCommentNum){
				
				if(maxCommentNum > 0){
					
					if(this.displayInfo.comments.length < maxCommentNum){
						
						document.querySelector("#review_more_button").setAttribute("class", "btn_review_more hide");
					
					} else{
						
						document.querySelector("#review_more_button").setAttribute("href","review?id="+this.displayInfo.displayInfo.id);
					
					}
				}
				
				this.displayInfo.comments.forEach((comment)=>{
					comment.reservationEmail = comment.reservationEmail.split("@")[0].substr(0, 4) + "****";
					comment.reservationDate = comment.reservationDate.split(" ")[0].split("-").join(".");
				});
				
				var commentUl = document.querySelector("#short_review");
				var commentAreaTemplate = document.querySelector("#review_area_template").innerText;

				var bindTemplate = Handlebars.compile(commentAreaTemplate);
				var bindedHTML = bindTemplate(this.displayInfo);
				commentUl.innerHTML = bindedHTML;
			}
	}

	return comments;
}

function getDetailInfo(displayInfo){
	
	var detailInfo = {
			
			displayInfo: displayInfo,
			
			init:function(){
				
				this.render();
				this.bindEvents();
				
			},
			
			render:function(){
				
				var detailInfoUl = document.querySelector("#detail_info_wrap");
				var detailInfoTemplate = document.querySelector("#detail_info_template").innerText;
				
				var bindDetailInfoTemplate = Handlebars.compile(detailInfoTemplate);
				var bindedDetailInfoHTML = bindDetailInfoTemplate(this.displayInfo.displayInfo);
				detailInfoUl.innerHTML = bindedDetailInfoHTML;
				
			},
			
			bindEvents:function(){
				
				document.querySelector("#detail_info").addEventListener("click", detailButtonDown);
				
			}
	}
	
	return detailInfo;
}

function getPath(displayInfo){
	
	var path = {
			
			displayInfo: displayInfo,
			
			init:function(){
				
				this.render();
				this.bindEvents();
				
			},
			
			render:function(){
				
				var pathUl = document.querySelector("#path_wrap");
				var pathTemplate = document.querySelector("#path_template").innerText;
				
				var bindPathTemplate = Handlebars.compile(pathTemplate);
				var bindedPathHTML = bindPathTemplate(this.displayInfo.displayInfo);
				pathUl.innerHTML = bindedPathHTML;
				
			},
			
			bindEvents:function(){
				
				document.querySelector("#path").addEventListener("click", pathButtonDown);
				
			}
	}
	
	return path;
}	
