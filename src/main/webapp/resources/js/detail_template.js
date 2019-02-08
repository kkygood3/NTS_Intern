var template = {};

function initializeTemplate(){
	var commentTemplate = document.querySelector("#comment_template").innerHTML;
	var productDisplayImgTemplate = document.querySelector("#product_display_img_template").innerHTML;
	
	template.parseCommentTemplate = Handlebars.compile(commentTemplate);
	template.parseProductDisplayImgTemplate = Handlebars.compile(productDisplayImgTemplate);
}

function parseCommentToHtml(comment){
	return template.parseCommentTemplate(comment);
}

function parseProductDisplayImgToHtml(imgUrl, description){
	return template.parseProductDisplayImgTemplate({
		productDisplayImageUrl : imgUrl,
		productDescription : description
	});
}