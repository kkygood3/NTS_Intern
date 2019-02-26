document.addEventListener("DOMContentLoaded", () => {
	template.setting();
	
	var review = new Review();
});
function Review(){
	var reviewWriteInfoElement = document.querySelector("#review_write_info");
	var reviewWriteElement = document.querySelector("#review_write");
	var reviewImageOpenElement = document.querySelector("#review_image_open");
	var reviewImageListElement = document.querySelector("#review_image_list");
	var ratingElement = document.querySelector("#rating");
	var btnSubmit = document.querySelector("#btn_submit");
	
	this.reservationId = document.querySelector("#reservation_id").dataset.reservationId;
	this.rating = new Rating(ratingElement);
	this.reviewWrite = new ReviewWrite(reviewWriteElement);
	this.imageList = new ImageList(reviewImageOpenElement, reviewImageListElement);
	
	reviewWriteInfoElement.addEventListener("focus", () =>{
		reviewWriteInfoElement.classList.add("hide");
		reviewWriteElement.focus();
	});
	
	reviewWriteElement.addEventListener("focusout", ()=>{
		if(reviewWriteElement.value.length === 0){
			reviewWriteInfoElement.classList.remove("hide");
		}
	})
	
	btnSubmit.addEventListener("click", () =>{
		
		if(this.reviewWrite.isNotValid()){
			alert("comment is not valid");
			return;
		}
		var reservationId = this.reservationId;
		var score = this.rating.score;
		var comment = this.reviewWrite.getComment();

		var formData = new FormData();
		formData.append("reservationId", reservationId);
		formData.append("score", score);
		formData.append("comment", comment);
		this.imageList.appendToFormData(formData);
		
		
		var ajax = new Ajax();
		ajax.post("/api/comment", formData, () =>{
			alert("작성 완료.");
			location.href="/my-reservation";
		});
	});
	
}

function ReviewWrite(reviewWriteElement){
	this.reviewWriteElement = reviewWriteElement;
	
	this.reviewWriteElement.addEventListener("keydown", this.updateWordCount.bind(this));
	this.reviewWriteElement.addEventListener("keyup", this.updateWordCount.bind(this));
}

ReviewWrite.prototype = {
	updateWordCount : function(){
		var wordCountElement = document.querySelector("#word_count");
		wordCountElement.innerText = this.reviewWriteElement.value.length;
	},
	
	getComment : function(){
		return this.reviewWriteElement.value;
	},
	
	isNotValid : function(){
		const commentRegex = /.{5,400}/;
		var comment = this.getComment();
		
		if(comment === "" || comment.match(commentRegex) === null
				|| comment.match(commentRegex)[0] !== comment){
			return true;
		}
		return false;
	}
}

function ImageList(fileInput, viewList){
	this.imageList = [];
	this.viewList = viewList;
	this.index = 0;
	
	fileInput.addEventListener("change", (evt) =>{
		var files = evt.target.files;
		this.addAll(files);
		
		fileInput.value = "";
	});
	
	this.viewList.addEventListener("click", (evt) =>{
		var paths = evt.path;
	    
	    paths.some((path) => {
	        if(path.tagName === "A" && path.className.includes("delete")){
	        	var imageElement = path.parentElement;
	        	var index = imageElement.dataset.index;
	        	this.imageList[index].cancel = true;
	        	viewList.removeChild(imageElement);
            	return true;
        	}
	        return false;
	    });
	})
}

ImageList.prototype = {
	addAll : function(files){
		Array.from(files).forEach((file) =>{
			if(this.isImage(file)){
				var image = new Image(file, this.index);
				this.index++;
				this.imageList.push(image);
				this.viewList.appendChild(image.imageElement);
			}
		});
	},
	
	isImage : function(file){
		const result = ([ 'image/jpeg',
			  'image/png',
			  'image/jpg' ].indexOf(file.type) > -1);
		return result;
	},
	
	appendToFormData : function(formdata){
		this.imageList.forEach((image)=>{
			if(!image.cancel){
				formdata.append("images", image.imageFile);	
			}
		});
	}
}

function Image(imageFile, index){
	this.imageFile = imageFile;
	this.cancel = false;
	
	var imgUrl = window.URL.createObjectURL(this.imageFile);
	this.imageElement = template.parseImageViewToElement({"imgUrl" : imgUrl, "index" : index});
}