document.addEventListener("DOMContentLoaded", function() {
	let url = window.location.href;
	let path = url.split("/");
	let displayInfoId = path[path.length-1];
	getDetailInfo(displayInfoId);
});

let title = document.querySelector(".title")
let star_rating = document.querySelector(".graph_value");
let rating = document.querySelector(".text_value");
let cntComment = document.querySelector(".green");
let commentsContainer = document.querySelector(".list_short_review");

let template = document.querySelector("#commentTemplate").innerText;
let bindTemplate = Handlebars.compile(template);
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
				
				title.innerHTML = jsonResponse["displayInfo"].productDescription;
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
			}
		}
		
		httpRequest.open("GET", "../api/products/" + displayInfoId);
		httpRequest.setRequestHeader("Content-type", "charset=utf-8");
		httpRequest.send();
	}
}

document.querySelector(".btn_back").addEventListener("click", function(){
	history.back();
});

document.querySelector(".lnk_top").addEventListener("click", function(){
	document.documentElement.scrollTop = 0;
});