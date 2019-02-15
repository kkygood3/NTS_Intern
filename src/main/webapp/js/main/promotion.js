/**
 * 페이지 상단 프로모션 슬라이드영역 관련
 * @author 시윤
 *
 */

function sendGetPromotionsAjax() {
	sendGetAjax("./promotion", makePromotionSlideHTML);
}

/**
 * 프로모션 슬라이드영역 html생성
 * @param promotions 프로모션 이미지 리스트
 */
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
	ul.style.width = (100 * promotions.length) + "%";
	readyToSlide = true;
}

function makePromotionSlide() {
	var ul = document.getElementsByClassName("visual_img")[0];
	makeInfiniteSlide(ul);
}
