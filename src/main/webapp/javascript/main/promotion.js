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
		resultHTML += html.replace("{sava_file_name_list}", promotion.saveFileName);
	});

	var ul = document.getElementsByClassName("visual_img")[0];
	ul.innerHTML = resultHTML;
	ul.style.width = (100*promotions.length) + "%";
	readyToSlide = true;
}

function makePromotionSlide() {
	var ul = document.getElementsByClassName("visual_img")[0];
	makeSlide(ul);
}
