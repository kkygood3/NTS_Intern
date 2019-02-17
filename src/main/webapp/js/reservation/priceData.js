function makePriceData() {
	var priceDatas = [];
	var priceInfos = document.querySelectorAll(".ticket_body .qty");
	for (let priceInfo in priceInfos) {
		var count = priceInfo.getElementsByClassName("count_control_input")[0];
		var price = priceInfo.getElementsByClassName("price")[0];
		var priceData = {};
		priceData.productPriceId = priceInfos.id.replace("product_price_id_", "");
		priceData.price = price;
		priceData.count = count;
		priceDatas.push(priceData);
	}
	var input = document.createElement("input");
	input.name = "price";
	input.value = priceDatas;
}