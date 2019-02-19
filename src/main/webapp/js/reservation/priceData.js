function makePriceData() {
	var priceDatas = [];
	var priceInfos = document.querySelectorAll(".ticket_body .qty");
	for (var i = 0, len = priceInfos.length; i < len; i++) {
		var count = priceInfos[i].getElementsByClassName("count_control_input")[0].value;
		var price = priceInfos[i].getElementsByClassName("price")[0].innerText;
		var priceData = {};
		priceData.productPriceId = priceInfos[i].id.replace("product_price_id_", "");
		priceData.count = count;
		if(count > 0) {
			priceDatas.push(priceData);
		}
	}
	return priceDatas;
}
