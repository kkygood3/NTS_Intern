$(document).ready(function(){
  $(".bk_more._open").click(function(){
    if($(".bk_more._open").css("display") === "block") {
       $(".bk_more._open").css({ display: "none"});
       $(".bk_more._close").css({ display: "block"});
       $(".store_details").removeClass("close3");
    }
  });
  $(".bk_more._close").click(function(){
	    if($(".bk_more._close").css("display") === "block") {
	       $(".bk_more._close").css({ display: "none"});
	       $(".bk_more._open").css({ display: "block"});
	       $(".store_details").addClass("close3");
	    }
	  });
});

document.addEventListener("DOMContentLoaded", function() {
	let url = window.location.href;
	let path = url.split("/");
	let displayInfoId = path[path.length-1];
	getDetailInfo(displayInfoId);
});

let title = document.querySelector(".display_title")
let image = document.querySelector(".img_thumb");
let cnt = document.querySelector(".count_detail_image");
let content = document.querySelector(".dsc");
let star_rating = document.querySelector(".graph_value");
let rating = document.querySelector(".text_value");
let introduce = document.querySelector(".detail_info_lst").querySelector(".in_dsc");
let cntComment = document.querySelector(".green");

let storeMap = document.querySelector(".store_map");
let storeName = document.querySelector(".store_name");
let addrStreet = document.querySelector(".store_addr_bold");
let addrOld = document.querySelector(".addr_old_detail");
let placeName = document.querySelector(".store_addr.addr_detail");
let telephone = document.querySelector(".store_tel");


let visualImgContainer = document.querySelector(".visual_img.detail_swipe");
let visualImgTemplate = document.querySelector("#visualImgTemplate").innerHTML;
let bindVisualImgs = Handlebars.compile(visualImgTemplate);

let commentsContainer = document.querySelector(".list_short_review");
let commentTemplate = document.querySelector("#commentTemplate").innerHTML;
let bindComments = Handlebars.compile(commentTemplate);
const displayedLength = 4;
Handlebars.registerHelper('anonymize', function(context) {
	return context.substring(0, displayedLength) + "****";
});

function getDetailInfo(displayInfoId) {
	let httpRequest;
	
	if (window.XMLHttpRequest) {
		httpRequest =  new XMLHttpRequest();
		
		httpRequest.onreadystatechange = function() {
			let jsonResponse;
			
			if (httpRequest.readyState === 4 && httpRequest.status === 200) {
				jsonResponse = JSON.parse(httpRequest.responseText);
				
				// 이미지 개수 영역
				cnt.innerHTML = jsonResponse["productImages"].length;
				if(jsonResponse["productImages"].length === 1){
					document.querySelector(".btn_prev").style.display = "none";
					document.querySelector(".btn_nxt").style.display = "none";
				}
				// 상단 이미지 & 타이틀
				title.innerHTML = jsonResponse["displayInfo"].productDescription;
				visualImgContainer.innerHTML = bindVisualImgs(jsonResponse);
				imageSlide();
				// 상단 상품 설명 영역
				content.innerHTML = jsonResponse["displayInfo"].productContent;
				const contentContainerHeight = 73;
				if(content.scrollHeight < contentContainerHeight){
					document.querySelector(".bk_more").style.display = "none";
				}
				// 평점
				star_rating.style.width = (jsonResponse["averageScore"] / 5) * 100 + "%"
				rating.innerHTML = "<span>" + jsonResponse["averageScore"] + "</span>";
				// 댓글 개수
				cntComment.innerHTML = jsonResponse["comments"].length + "건";
				// 댓글
				jsonResponse["comments"].forEach(function(comment){
					comment.score = comment.score.toFixed(1);
				});
				commentsContainer.innerHTML = bindComments(jsonResponse);
				
				const commentsPerPage = 3;
				if(document.querySelector(".list_short_review").querySelectorAll("li").length < commentsPerPage){
					document.querySelector(".btn_review_more").style.display = "none";
				} else {
					document.querySelector(".btn_review_more").href = "../review/" + displayInfoId;
				}
				// 상세설명
				introduce.innerHTML = jsonResponse["displayInfo"].productContent;
				
				//오시는길
				storeMap.src = "../" + jsonResponse["displayInfoImage"].saveFileName;
				storeName.innerHTML = jsonResponse["displayInfo"].productDescription;
				addrStreet.innerHTML = jsonResponse["displayInfo"].placeStreet;
				addrOld.innerHTML = jsonResponse["displayInfo"].placeLot;
				placeName.innerHTML = jsonResponse["displayInfo"].placeName;
				telephone.innerHTML = jsonResponse["displayInfo"].telephone;
			}
		}
		
		httpRequest.open("GET", "../api/products/" + displayInfoId);
		httpRequest.setRequestHeader("Content-type", "charset=utf-8");
		httpRequest.send();
	}
}

let tab = document.querySelector(".info_tab_lst");
let detailInfo = document.querySelector(".detail_area_wrap");
let locationInfo = document.querySelector(".detail_location");
tab.addEventListener("click", function(event){
	let anchorElement;
	let previousActive = document.querySelector(".anchor.active");
	let productContainer = document.querySelectorAll(".lst_event_box");

 	if(event.target.className === "anchor"){
		anchorElement = event.target;
	} else if(event.target.className === "info_tab_text"){
		anchorElement = event.target.parentNode;
	} else {
		return;
	}

 	previousActive.className = "anchor";

 	selectedCategoryId = anchorElement.parentNode.dataset.category;
	anchorElement.className += " active";

	if(anchorElement.innerText === "상세정보"){
		if(!locationInfo.className.includes("hide")){
			locationInfo.className += " hide";
		}
		detailInfo.className = detailInfo.className.replace("hide", "");
	} else if(anchorElement.innerText === "오시는길") {
		if(!detailInfo.className.includes("hide")){
			detailInfo.className += " hide";
		}
		locationInfo.className = detailInfo.className.replace("hide", "");
	}
});

function imageSlide(){
	let visualImageIndex = 0;
	const cntImages = visualImgContainer.querySelectorAll("li").length;
	const imageWidth = 414;
	let prevImage = visualImgContainer.querySelector("li[data-index='" + (cntImages - 1) + "']");
	let nowImage = visualImgContainer.querySelector("li[data-index='" + visualImageIndex + "']");
	let nextImage = visualImgContainer.querySelector("li[data-index='" + (visualImageIndex + 1) + "']");
	document.querySelector(".btn_prev").addEventListener("click", function(){
		if(visualImageIndex === 0){
			prevImage = visualImgContainer.querySelector("li[data-index='" + (cntImages - 1) + "']");
		} else {
			prevImage = visualImgContainer.querySelector("li[data-index='" + (visualImageIndex - 1) + "']");
		}
		
		prevImage.style.left = -imageWidth + "px";
		prevImage.style.visibility = "visible";
		prevImage.className = "item slide_right_in";
		nowImage.style.left = "0";
		nowImage.className = "item slide_right_out";

		visualImageIndex = visualImageIndex - 1;
		if(visualImageIndex < 0){
			visualImageIndex = cntImages - 1; 
		}
		nowImage = visualImgContainer.querySelector("li[data-index='" + visualImageIndex + "']");
		
		document.querySelector(".num").innerHTML = visualImageIndex + 1;
	});
	document.querySelector(".btn_nxt").addEventListener("click", function(){
		nextImage = visualImgContainer.querySelector("li[data-index='" + (visualImageIndex + 1) % cntImages + "']");
		
		nowImage.style.left = "0";
		nowImage.className = "item slide_left_out";
		nextImage.style.left = imageWidth + "px";
		nextImage.style.visibility = "visible";
		nextImage.className = "item slide_left_in";
		
		visualImageIndex = (visualImageIndex + 1) % cntImages;
		nowImage = visualImgContainer.querySelector("li[data-index='" + visualImageIndex + "']");
		
		document.querySelector(".num").innerHTML = visualImageIndex + 1;
	});
}

document.querySelector(".lnk_top").addEventListener("click", function(){
	document.documentElement.scrollTop = 0;
});