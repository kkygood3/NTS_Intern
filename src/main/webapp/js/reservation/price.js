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

	var bindTemplate = getBindTemplate("ticket_item");
	var innerHtml = makeHtmlFromListData(priceInfos, bindTemplate);

	var ul = document.getElementsByClassName("ticket_body")[0];
	ul.innerHTML += innerHtml;
}

// TODO: comment_data에서도 공통으로 쓰고있음
function getBindTemplate(templateId) {
	var template = document.getElementById(templateId).innerText;
	return Handlebars.compile(template); 
}

function makeHtmlFromListData(commentDisplayInfos, bindTemplate) {
	var html = ""
	commentDisplayInfos.forEach((commentDisplayInfo) => {
		html += bindTemplate(commentDisplayInfo);
	});
	return html;
}
