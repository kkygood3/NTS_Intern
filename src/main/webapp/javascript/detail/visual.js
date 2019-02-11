function sendGetEtcImageAjax() {
	sendGetAjax("./file_info?product_id="+ displayInfo.productId + "&type=et", setVisualArea);
}

function setVisualArea(etcImage) {
	var template = document.getElementById("visual_area").innerText;
	var sectionVisual = document.getElementsByClassName("section_visual")[0];
	
	template = template.replace("{image_length}", etcImage.length > 0 ? 2 : 1);
	var bindTemplate = Handlebars.compile(template); 
	etcImage = preprocessEtcImage(etcImage);
	
	sectionVisual.innerHTML += bindTemplate(etcImage);
	adjustUlSize(etcImage);
}

function preprocessEtcImage(etcImage) {
	if (etcImage.length > 0) {
		return {"etcImage" : etcImage[0]};
	} else {
		return null;
	}
}

function adjustUlSize(etcImage) {
	if (etcImage) {
		var ul = document.getElementsByClassName("visual_img");
		ul[0].style.width="200%";
	}
}
