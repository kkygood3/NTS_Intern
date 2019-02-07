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

function makePromotionSlide(numOfPromotionImgs) {
	const interval = window.setInterval(()=> {
		runSlide();
	}, 5000);
}

function runSlide() {
	if (readyToSlide){
		var ul = document.getElementsByClassName("visual_img")[0];
		ul.className = "visual_img";
		ul.style.left = 0;
		var firstLi = ul.firstElementChild;
		ul.appendChild(firstLi);

		window.setTimeout(()=> {
			ul.className = "visual_img transition";
			ul.style.left = "-414px";
		}, 1000);
	}
}
