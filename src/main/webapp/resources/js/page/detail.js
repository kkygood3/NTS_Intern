document.addEventListener("DOMContentLoaded", () => {
	
	template.setting();
	template.setUseDateFormat(0,10);

	var displayInfoId = document.querySelector("#display_info").dataset.displayInfoId;
	
	var ajax = new Ajax();
	ajax.get("/api/products/"+displayInfoId, printProductDisplay);
	
	addEventClickProductContentFoldExpand();
	addEventClickInfoTabList();
	
});

function printProductDisplay(responseObj){
	
	var productDisplay = responseObj.productDisplay;
	var productDisplayImgUrlItems = productDisplay.productDisplayImageUrlList;
	var commentListInfo = responseObj.commentListInfo;
	
	document.querySelector("#product_content").innerHTML = productDisplay.productContent;
	document.querySelector("#product_introduce").innerHTML = productDisplay.productContent;
	
	printProductDisplayImg(productDisplayImgUrlItems, productDisplay.productDescription);
	printComment(commentListInfo);
	printLocationDetail(productDisplay);
	
}

function printComment(commentListInfo){
	const SCORE_TO_PERCENT = 20;
	
	var commentItems = commentListInfo.commentList;
	var commentCount = commentListInfo.totalCount;
	var commentAvgScore = commentListInfo.averageScore;
	
	document.querySelector("#comment_count").innerHTML = commentCount;
	document.querySelector("#comment_avg_score").innerHTML = commentAvgScore.toFixed(1);
	document.querySelector("#star_score").style.width = commentAvgScore * SCORE_TO_PERCENT + "%";
	
	var commentList = document.querySelector("#comment_list");
	commentItems.forEach((commentItem) =>{
		commentList.innerHTML += template.parseCommentToHtml(commentItem);
	});
}

function printProductDisplayImg(productDisplayImgUrlItems, productDescription){
	var productDisplayImgList = document.querySelector("#product_display_img_list");
	
	productDisplayImgUrlItems.forEach((imgUrlItem) =>{
		productDisplayImgList.innerHTML += template.parseProductDisplayImgToHtml({
			productDisplayImageUrl : imgUrlItem,
			productDescription : productDescription
		});
	});

	setSlideBtn(productDisplayImgList, productDisplayImgUrlItems);
}

function setSlideBtn(productDisplayImgList, productDisplayImgUrlItems){
	var slide = new Slide(productDisplayImgList);
	
	var btnLeftSlide = document.querySelector("#display_img_left_slide");
	var btnRightSlide = document.querySelector("#display_img_right_slide");
	var displayImgPage = document.querySelector("#display_img_page");
	
	if(productDisplayImgUrlItems.length === 1){
		btnLeftSlide.className += " hide";
		btnRightSlide.className += " hide";
		displayImgPage.className += " hide";
		return;
	}
	
	var displayImgIdx = displayImgPage.querySelector("#display_img_idx");
	var displayImgTotalCount = displayImgPage.querySelector("#display_img_total_count");
	
	displayImgTotalCount.innerText = productDisplayImgUrlItems.length;
	
	btnLeftSlide.addEventListener("click", () =>{
		slide.left();
		displayImgIdx.innerText = slide.index + 1;
	});
	
	btnRightSlide.addEventListener("click", () =>{
		slide.right();
		displayImgIdx.innerText = slide.index + 1;
	});
}

function printLocationDetail(productDisplay){
	document.querySelector("#location_display_img").src = "/resources/" +productDisplay.displayInfoImageUrl;
	document.querySelector("#location_product_description").textContent = productDisplay.productDescription;
	document.querySelector("#location_place_street").textContent = productDisplay.placeStreet;
	document.querySelector("#location_place_lot").textContent = productDisplay.placeLot;
	document.querySelector("#location_place_name").textContent = productDisplay.placeName;
	document.querySelector("#location_telephone").textContent = productDisplay.telephone;
}

function addEventClickProductContentFoldExpand(){
	var btnExpand = document.querySelector("#btn_expand");
	var btnFold = document.querySelector("#btn_fold");
	var productContentArea = document.querySelector("#product_content").parentElement;
	
	btnExpand.addEventListener("click", () =>{
		btnExpand.className = "bk_more _open hide";
		btnFold.className = "bk_more _close";
		productContentArea.className = "store_details";
	});
	
	btnFold.addEventListener("click", () =>{
		btnExpand.className = "bk_more _open";
		btnFold.className = "bk_more _close hide";
		productContentArea.className = "store_details close3";
	});
}

function addEventClickInfoTabList(){
	var infoTabList = document.querySelector("#info_tab_list");
	
	infoTabList.addEventListener("click", (evt) =>{
		var paths = evt.path;
		
		paths.some((path) => {
	        if(path.tagName === "A" && path.className === "anchor"){
				document.querySelector("#detail_area").className = "detail_area_wrap";
				document.querySelector("#location_area").className = "detail_location";
	            infoTabList.querySelector(".anchor.active").className = "anchor";
	            path.className = "anchor active";
				
	            var areaId = path.dataset.areaId;
	            document.querySelector("#"+areaId).className += " show";
	            
	            return true;
	        	}
	        return false;
	    });
	});
}