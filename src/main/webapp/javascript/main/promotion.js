function sendGetPromotionsAjax() {
	sendGETAjax("./promotion", makePromotionSlideHTML);
}

function makePromotionSlideHTML(promotions) {
	var html = document.getElementById("promotion_item").innerHTML;
		
	var resultHTML = "";
	promotions.forEach((promotion) => {
		resultHTML += html.replace("{sava_file_name_list}", promotion.save_file_name);
	});

	var ul = document.getElementsByClassName("visual_img")[0];
	ul.innerHTML = resultHTML;
	ul.style.width = (100*promotions.length) + "%";
	readyToSlide = true;
}

function makePromotionSlide() {
	var ul = document.getElementsByClassName("visual_img")[0];
	var left = 0;
	const interval = window.setInterval(()=> {
		if (readyToSlide){
			if (left == -400) {
				left = 0;
				var firstLi = ul.firstElementChild;
				ul.appendChild(firstLi);
				ul.style.left = 0;
			} else {
				ul.style.left = --left + "px";
			}	// 수치는 나중에 다시 계산해야함
		}
	}, 1);
}
