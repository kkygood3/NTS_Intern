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

let title = document.querySelector(".visual_txt_tit")
let image = document.querySelector(".img_thumb");
let cnt = document.querySelector(".count_detail_image");
let content = document.querySelector(".dsc");
let star_rating = document.querySelector(".graph_value");
let rating = document.querySelector(".text_value");
let introduce = document.querySelector(".detail_info_lst").querySelector(".in_dsc");
let cntComment = document.querySelector(".green");
let commentsContainer = document.querySelector(".list_short_review");

var template = document.querySelector("#commentTemplate").innerText;
var bindTemplate = Handlebars.compile(template);

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
				title.innerHTML = "<span>" + jsonResponse["displayInfo"].productDescription + "</span>";
				jsonResponse["productImages"].forEach(function(productImage){
					image.src = "../" + productImage.saveFileName;
				});
				// 상단 상품 설명 영역
				content.innerHTML = jsonResponse["displayInfo"].productContent;
				// 평점
				star_rating.style.width = (jsonResponse["averageScore"] / 5) * 100 + "%"
				rating.innerHTML = "<span>" + jsonResponse["averageScore"] + "</span>";
				// 댓글 개수
				cntComment.innerHTML = jsonResponse["comments"].length + "건";
				// 댓글
				jsonResponse["comments"].forEach(function(comment){
					comment.score = comment.score.toFixed(1);
				});
				commentsContainer.innerHTML = bindTemplate(jsonResponse);
				// 상세설명
				introduce.innerHTML = jsonResponse["displayInfo"].productContent;
			}
		}
		
		httpRequest.open("GET", "../api/products/" + displayInfoId);
		httpRequest.setRequestHeader("Content-type", "charset=utf-8");
		httpRequest.send();
	}
}

let tab = document.querySelector(".info_tab_lst");
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
}); 