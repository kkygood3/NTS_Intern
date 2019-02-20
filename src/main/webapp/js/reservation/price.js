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
	
	var bindTemplate = getBindTemplate("ticket_item");
	
	Handlebars.registerHelper("orgPrice", function(price, discountRate) {
	      return Math.ceil(price / discountRate * 100); 
	});
	
	var innerHtml = makeHtmlFromListData(priceInfos, bindTemplate);

	var ul = document.getElementsByClassName("ticket_body")[0];
	ul.innerHTML += innerHtml;
	
	makePriceDescription(priceInfos);
	setPricePreviewText(priceInfos);
}

function setPricePreviewText(priceInfos) {
	var minPrice = getMinPrice(priceInfos);
	var preview = document.querySelectorAll(".preview_txt .preview_txt_dsc");
	preview[0].innerText = "₩" + minPrice + "~";
}

function getMinPrice(priceInfos) {
	var minPrice = priceInfos[0].price;
	priceInfos.forEach((price) => {
		if (price.price < minPrice) {
			minPrice = price.price;
		}
	});
	return minPrice;
}

function makePriceDescription(priceInfos) {
	var description = priceInfos[0].priceTypeName + " " + priceInfos[0].price + "원";
	priceInfos.slice(1).forEach((priceInfo) => {
		description += " / " + priceInfo.priceTypeName + " " + priceInfo.price + "원";
	});
	document.querySelectorAll(".store_details .dsc")[2].innerText = description;
}
