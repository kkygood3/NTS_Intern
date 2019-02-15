/**
 * 상품금액영역 관련
 * @author 시윤
 *
 */

function sendGetPriceAjax() {
	sendGetAjax("/product/" + displayInfo().productId + "/price", makePriceInfoHTML);
}

/**
 * 프로모션 슬라이드영역 html생성
 * @param promotions 프로모션 이미지 리스트
 */
function makePriceInfoHTML(priceInfos) {
	if (!priceInfos) {
		return false;
	}
	alert(JSON.stringify(priceInfos));
}
