document.addEventListener("DOMContentLoaded", function() {
	reviewWritePage.getReviewWritePage();
});

var reviewWritePage = {
	getReviewWritePage: function(){
		var star = new StarRating(this.elements.ratingContainer);
		var callback = function(event){
			this.elements.ratingValue.classList.remove("gray_star");
			this.elements.ratingValue.innerHTML = event.target.value;
		}.bind(this);
		star.setStarRating("rating_rdo", callback);
		
		this.compileHendlebars.getThumbnailSrc();
		this.reviewImage.showThumbnail();

		this.setEvent.focusOnReviewTextarea();
		this.setEvent.addEventToReviewTextarea();
		this.setEvent.addSubmitReviewToBtnWrite();
	},
	
	constants: {
		MAX_LENGTH : 400,
		DEFAULT_HEIGHT : 214
	},
	
	elements: {
		ratingContainer : document.querySelector(".rating"),
		ratingValue : document.querySelector(".star_rank"),
		
		reviewWriteInfo : document.querySelector(".review_write_info"),
		reviewTextarea : document.querySelector(".review_textarea"),
		btnWrite : document.querySelector(".bk_btn"),
		reviewLength : document.querySelector("#review_length"),
		
		thumbnailImg : document.querySelector(".item_thumb"),
		reviewImageFileInput : document.querySelector("#reviewImageFileOpenInput"),
		thumbnailContainer : document.querySelector(".lst_thumb")
	},
	
	template: {
		thumbnailTemplate : document.querySelector("#thumbnailList").innerHTML
	},
	
	compileHendlebars: {
		bindTemplate : function(template){
			return Handlebars.compile(template);
		},
		
		getThumbnailSrc : function(){
			Handlebars.registerHelper('getSrc', function(image) {
			    return window.URL.createObjectURL(image);
			});
		}
	},
	
	ajaxSender : new AjaxSender(),
	
	getAjaxOption : function(){
		var options = {
			data : this.reviewWritePage.getData(),
			callback : function(httpRequest){
				window.location = httpRequest.responseURL;
			}
		}
		
		return options;
	}.bind(this),
	
	getData : function(){
		var data = new FormData();

 		data.append("rating", this.reviewWritePage.elements.ratingValue.innerHTML);
		data.append("review", this.reviewWritePage.elements.reviewTextarea.value);

 		this.reviewWritePage.reviewImage.fileList.forEach(function(file){
			data.append("file", file);
		})

 		return data;
	}.bind(this),
	
	reviewImage : {
		fileList : [],
		
		showThumbnail : function(){
			var bindThumbnail = this.reviewWritePage.compileHendlebars.bindTemplate(this.reviewWritePage.template.thumbnailTemplate);
			
			this.reviewWritePage.elements.reviewImageFileInput.addEventListener("change", function(event){
				this.reviewWritePage.elements.thumbnailContainer.innerHTML += bindThumbnail(event.target.files);
				this.reviewWritePage.reviewImage.updateFileList(this.reviewWritePage.elements.reviewImageFileInput.files);
				
				this.reviewWritePage.elements.reviewImageFileInput.value = "";
			}.bind(this));

			this.reviewWritePage.reviewImage.setRemoveToBtnThumbnailDelete();
		}.bind(this),
		
		setRemoveToBtnThumbnailDelete : function(){
			this.reviewWritePage.elements.thumbnailContainer.addEventListener("click", function(event){
				var thumbnailList = Array.from(this.reviewWritePage.elements.thumbnailContainer.children);
				var thumbnail = event.target.parentNode.parentNode;
				var fileIndex = thumbnailList.indexOf(thumbnail);

				event.preventDefault();
				
				if(event.target.className === "spr_book ico_del"){
					this.reviewWritePage.elements.thumbnailContainer.removeChild(thumbnail);
					
					this.reviewWritePage.reviewImage.removeFileFromFileList(fileIndex);
				}
			}.bind(this));
		}.bind(this),
		
		updateFileList : function(fileList){
			var fileBuffer=[];
			Array.prototype.push.apply(fileBuffer, fileList);
			
			fileBuffer.forEach(function(file){
				this.fileList.push(file);
			}.bind(this));
		},
		
		removeFileFromFileList : function(fileIndex){
			var newFileList = []
			
			this.reviewWritePage.reviewImage.fileList.forEach(function(file){
				if(this.reviewWritePage.reviewImage.fileList.indexOf(file) !== fileIndex){
					newFileList.push(file);
				}
			});

			this.reviewWritePage.reviewImage.fileList = newFileList;
		}.bind(this),
	},
	
	setEvent: {
		focusOnReviewTextarea : function(){
			this.reviewWritePage.elements.reviewWriteInfo.addEventListener("click", function(event){
				event.preventDefault();
				
				this.reviewWritePage.elements.reviewWriteInfo.style.display = "none";
				this.reviewWritePage.elements.reviewTextarea.focus();
			}.bind(this));
		}.bind(this),
		
		addEventToReviewTextarea : function(){
			this.reviewWritePage.elements.reviewTextarea.addEventListener("input", function(event){
				if(event.target.value.length === 0){
					this.reviewWritePage.elements.reviewWriteInfo.style.display = "";
					this.reviewWritePage.elements.btnWrite.disabled = "true";
				} else {
					this.reviewWritePage.elements.reviewWriteInfo.style.display = "none";
					this.reviewWritePage.elements.btnWrite.removeAttribute("disabled");
				}
				
				if(event.target.value.length > this.reviewWritePage.constants.MAX_LENGTH){
					event.target.value = event.target.value.substring(0, this.reviewWritePage.constants.MAX_LENGTH);
				}
				
				if(event.target.scrollHeight > this.reviewWritePage.constants.DEFAULT_HEIGHT){
					event.target.style.height = event.target.scrollHeight + "px";
				}
				
				this.reviewWritePage.elements.reviewLength.innerHTML = event.target.value.length;
			}.bind(this));
		}.bind(this),
		
		addSubmitReviewToBtnWrite : function(){
			this.reviewWritePage.elements.btnWrite.addEventListener("click", function(){
				this.reviewWritePage.ajaxSender.sendPost("/reservation/upload", this.reviewWritePage.getAjaxOption());
			}.bind(this))
		}.bind(this)
	}
}

function StarRating(ratingContainer){
	this.ratingContainer = ratingContainer;
}
StarRating.prototype.setStarRating = function(starClassName, callback){
	this.ratingContainer.addEventListener("click", function(event){
		var stars = document.querySelectorAll("." + starClassName);
		if(event.target.classList.contains(starClassName)){
			if(typeof(callback) === "function" && callback !== undefined && callback !== null){
				callback(event);
			}
			
			stars.forEach(function(star){
				star.checked = true;
				if(star.value > event.target.value){
					star.checked = false;
				}
			});
		}
	});
}