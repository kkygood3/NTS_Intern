function displayInfoRequest(callBack, displayInfoId){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "/api/products/" + displayInfoId, true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

	xhr.onreadystatechange = () => {
		if(xhr.readyState==4 && xhr.status==200){
			callBack(JSON.parse(xhr.responseText));
		}
	}
	
	xhr.send();
}


function detailsInit(displayInfo){
	Handlebars.registerHelper("convert_to_product_title", ()=>{
		return displayInfo.displayInfo.description;
	});
	var maxCommentNum = 3;
	
	appendComments(displayInfo, maxCommentNum);
	insertProductImages(displayInfo);
	insertContent(displayInfo);
	insertDetailInfo(displayInfo);
	insertPath(displayInfo);
	addMoreButtonListener(displayInfo);
	addDetailButtonListener();
}

function insertProductImages(displayInfo){
	
	document.querySelector("#product_image_count").innerText = 1;
	document.querySelector("#total_product_image_count").innerText = displayInfo.productImages.length;
	
	var backgroundImageUl = document.querySelector("#background_image_ul");
	var backgorundImageTemplate = document.querySelector("#background_images").innerText;
	
	var bindTemplate = Handlebars.compile(backgorundImageTemplate);
	var bindedHTML = bindTemplate(displayInfo);
	backgroundImageUl.innerHTML = bindedHTML;
	
	if(displayInfo.productImages.length > 1){
		setBackgroundImageMove();
	} else{
		document.querySelector("#prev_button").setAttribute("class", "prev hide");
		document.querySelector("#next_button").setAttribute("class", "nxt hide");
	}
}

function insertContent(displayInfo){
	Handlebars.registerHelper("product_content", (content)=>{
		return content.substr(0, 100) + "...";
	});
	
	var contentParagraph = document.querySelector("#product_content_paragraph");
	var bind = Handlebars.compile(contentParagraph.innerText);
	contentParagraph.innerText = bind(displayInfo.displayInfo);
}

function appendComments(displayInfo, maxCommentNum = -1){	
	if(maxCommentNum > 0){
		if(displayInfo.comments.length < maxCommentNum){
			document.querySelector("#review_more_button").setAttribute("class", "btn_review_more hide");
		} else{
			document.querySelector("#review_more_button").setAttribute("href","review?id="+displayInfo.displayInfo.id);
		}
	}
	
	displayInfo.comments.forEach((comment)=>{
		comment.commentImages = JSON.parse(comment.commentImages);
		comment.reservationEmail = comment.reservationEmail.split("@")[0].substr(0, 4) + "****";
		comment.reservationDate = comment.reservationDate.split(" ")[0].split("-").join(".");
	});
	
	Handlebars.registerHelper("print_comments_limit_3", (comments)=> {
		var len = comments.length > maxCommentNum ? maxCommentNum : comments.length;

		var commentListTemplate = document.querySelector("#review_list").innerText;
		var bindCommentTemplate = Handlebars.compile(commentListTemplate);
		
		var commentListHTML = "";
		
		for(var index=0; index<len; index++){
			commentListHTML += bindCommentTemplate(comments[index]);
		}
		return commentListHTML;
	});
	
	Handlebars.registerHelper("print_comments_all", (comments)=> {
		var commentListTemplate = document.querySelector("#review_list").innerText;
		var bindCommentTemplate = Handlebars.compile(commentListTemplate);
		
		var commentListHTML = "";
		
		for(var index=0, len = comments.length; index<len; index++){
			commentListHTML += bindCommentTemplate(comments[index]);
		}
		return commentListHTML;
	});
	
	Handlebars.registerHelper("percentage_score",(score)=>{
		return score*20;
	});
	
	var commentUl = document.querySelector("#short_review");
	var commentAreaTemplate = document.querySelector("#review_area_template").innerText;

	var bindTemplate = Handlebars.compile(commentAreaTemplate);
	
	var bindedHTML = bindTemplate(displayInfo);
	commentUl.innerHTML = bindedHTML;
}

function insertDetailInfo(displayInfo){
	var detailInfoUl = document.querySelector("#detail_info_wrap");
	var detailInfoTemplate = document.querySelector("#detail_info_template").innerText;
	
	var bindDetailInfoTemplate = Handlebars.compile(detailInfoTemplate);
	var bindedDetailInfoHTML = bindDetailInfoTemplate(displayInfo.displayInfo);
	detailInfoUl.innerHTML = bindedDetailInfoHTML;
}

function insertPath(displayInfo){
	var pathUl = document.querySelector("#path_wrap");
	var pathTemplate = document.querySelector("#path_template").innerText;
	
	var bindPathTemplate = Handlebars.compile(pathTemplate);
	var bindedPathHTML = bindPathTemplate(displayInfo.displayInfo);
	pathUl.innerHTML = bindedPathHTML;
}

function setBackgroundImageMove(){
	var backGroundImageUl = document.querySelector("#background_image_ul");
	backGroundImageUl.innerHTML = backGroundImageUl.lastElementChild.outerHTML + backGroundImageUl.innerHTML;
	backGroundImageUl.style.transition = "right 0.5s";
	backGroundImageUl.style.right = "414px";
	document.querySelector("#prev_button").addEventListener("click", ()=>{
		prevButtonDown();
	});
	document.querySelector("#next_button").addEventListener("click", ()=>{
		nextButtonDown();
	});
}

function prevButtonDown(){
	var backGroundImageUl = document.querySelector("#background_image_ul");
	backGroundImageUl.style.transition = "right 0.1s";
	backGroundImageUl.style.right = "0px";
	
	setTimeout(()=>{
		backGroundImageUl.style.transition = "right 0s";
		moveListElement("begin", backGroundImageUl);
		backGroundImageUl.style.right = "414px";
	},100);
	
	var imageCount = document.querySelector("#product_image_count");
	imageCount.innerText = (parseInt(imageCount.innerText) % 2) + 1;
}

function nextButtonDown(){
	var backGroundImageUl = document.querySelector("#background_image_ul");
	backGroundImageUl.style.transition = "right 0.1s";
	backGroundImageUl.style.right = "828px";
	
	setTimeout(()=>{
		backGroundImageUl.style.transition = "right 0s";
		moveListElement("end", backGroundImageUl);
		backGroundImageUl.style.right = "414px";
	},100);
	
	var imageCount = document.querySelector("#product_image_count");
	imageCount.innerText = (parseInt(imageCount.innerText) % 2) + 1;
}

function moveListElement(where, ul){
	if(where === "begin"){
		ul.removeChild(ul.lastElementChild);
		var firstElement = ul.lastElementChild.outerHTML;
		ul.innerHTML = firstElement + ul.innerHTML;
		
	} else if(where === "end"){
		ul.removeChild(ul.firstElementChild);
		var lastElement = ul.firstElementChild.outerHTML;
		ul.innerHTML = ul.innerHTML + lastElement;
	}
	return ul;
}

function addDetailButtonListener(){
	document.querySelector("#detail_info").addEventListener("click", ()=>{
		detailButtonDown();
	});
	document.querySelector("#path").addEventListener("click", ()=>{
		pathButtonDown();
	});
}

function detailButtonDown(){
	document.querySelector("#detail_info").setAttribute("class","anchor active");
	document.querySelector("#detail_info_wrap").setAttribute("class","detail_area_wrap");
	
	document.querySelector("#path").setAttribute("class","anchor");
	document.querySelector("#path_wrap").setAttribute("class","detail_location hide");
}

function pathButtonDown(){
	document.querySelector("#detail_info").setAttribute("class","anchor");
	document.querySelector("#detail_info_wrap").setAttribute("class","detail_area_wrap hide");
	
	document.querySelector("#path").setAttribute("class","anchor active");
	document.querySelector("#path_wrap").setAttribute("class","detail_location");
}

function addMoreButtonListener(displayInfo){
	document.querySelector("#more_button_open").addEventListener("click", ()=>{
		moreOpenButtonDown(displayInfo.displayInfo);
	});
	document.querySelector("#more_button_close").addEventListener("click", ()=>{
		moreCloseButtonDown(displayInfo.displayInfo);
	});
}

function moreOpenButtonDown(displayInfo){
	document.querySelector("#more_button_open").setAttribute("style","display: none");
	document.querySelector("#more_button_close").removeAttribute("style");
	document.querySelector("#product_content_div").setAttribute("class", "store_details");
	document.querySelector("#product_content_paragraph").innerText = displayInfo.content;
}

function moreCloseButtonDown(displayInfo){
	document.querySelector("#more_button_open").removeAttribute("style");
	document.querySelector("#more_button_close").setAttribute("style","display: none");
	document.querySelector("#product_content_div").setAttribute("class", "store_details close3");
	document.querySelector("#product_content_paragraph").innerText = displayInfo.content.substr(0, 100) + "...";
}


	