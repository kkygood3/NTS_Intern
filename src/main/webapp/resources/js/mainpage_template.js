const promotionTemplate = `
    <li class="item" style="background-image: url(/resources/{productImageUrl});">
        <a href="#"> 
			<span class="img_btm_border"></span> 
			<span class="img_right_border"></span>
			<span class="img_bg_gra"></span>
            <div class="event_txt">
                <h4 class="event_txt_tit"></h4>
                <p class="event_txt_adr"></p>
                <p class="event_txt_dsc"></p>
            </div>
        </a>
    </li>
    `;

const categoryTemplate = `
    <li class="item" data-category="{id}">
	    <a class="anchor"><span>{name}</span></a>
	</li>
	`;

const productTemplate = `
    <li class="item">
        <a href="/product-display?displayInfoId={displayInfoId}" class="item_book">
            <div class="item_preview">
                <img alt="{productDescription}" class="img_thumb" src="/resources/{productImageUrl}">
                <span class="img_border"></span>
            </div>
            <div class="event_txt">
                <h4 class="event_txt_tit">
					<span>{productDescription}</span>
					<small class="sm">{placeName}</small>
				</h4>
                <p class="event_txt_dsc">{productContent}</p>
            </div>
        </a>
    </li>
    `;

function parsePromotionToHtml(promotionItem){
	var promotionItemHTML = promotionTemplate
		.replace("{productImageUrl}", promotionItem.productImageUrl);
	return promotionItemHTML;
}

function parseCategoryToHtml(categoryItem){
	var categoryItemHTML = categoryTemplate
		.replace("{id}", categoryItem.id)
		.replace("{name}", categoryItem.name);
	return categoryItemHTML;
}

function parseProductToHtml(productItem){
	var productItemHTML = productTemplate
		.replace("{displayInfoId}", productItem.displayInfoId)
		.replace("{productDescription}", productItem.productDescription)
		.replace("{productImageUrl}", productItem.productImageUrl)
		.replace("{productDescription}", productItem.productDescription)
		.replace("{placeName}", productItem.placeName)
		.replace("{productContent}", productItem.productContent);
	return productItemHTML;
}