document.addEventListener("DOMContentLoaded", function() {
	let url = window.location.href;
	let path = url.split("/");
	let displayInfoId = path[path.length-1];
	getDetailInfo(displayInfoId);
});

let image = document.querySelector(".img_thumb");
let cnt = document.querySelector(".count_detail_image");

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
				// 상단 이미지
				jsonResponse["productImages"].forEach(function(productImage){
					image.src = "../" + productImage.saveFileName;
				});
			}
		}
		
		httpRequest.open("GET", "../api/products/" + displayInfoId);
		httpRequest.setRequestHeader("Content-type", "charset=utf-8");
		httpRequest.send();
	}
}