function sendGetPromotionsAjax() {
	sendGETAjax("./promotion", makePromotionSlideHTML);
}

function makePromotionSlideHTML(promotions) {
	if (!promotions) {
		return false;
	}
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
	var time = 0;
	const interval = window.setInterval(()=> {
		if (readyToSlide){
			if (time == 400) {
				time = 0;
				var firstLi = ul.firstElementChild;
				ul.appendChild(firstLi);
				ul.style.left = 0;
			} else {
				ul.style.left = Math.log(time++/40+1)*-173 + "px";
			}
		}
	}, 1);
}
