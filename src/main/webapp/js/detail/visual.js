/**
 * 페이지 상단 비주얼영역 관련
 * @author 시윤
 *
 */

function sendGetEtcImageAjax() {
	sendGetAjax("/api/product/" + displayInfo().productId + "/file_info/et", setVisualArea);
}

/**
 * 상단 비주얼영역 handlebar로 html 생성
 * 이미지가 있으면 한개만 노출, 없으면 아무것도 안함
 * @param etcImage 이미지 이름
 */
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
