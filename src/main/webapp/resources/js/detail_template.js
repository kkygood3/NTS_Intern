var template = {};

function initializeTemplate(){
	var commentTemplate = document.querySelector("#comment_template")
	var productDisplayImgTemplate = document.querySelector("#product_display_img_template");
	
	Handlebars.registerHelper('formattingDate', (date) =>{
		return date.substr(0,10);
	});
	
	template.parseComment = Handlebars.compile(commentTemplate.innerHTML);
	template.parseProductDisplayImg = Handlebars.compile(productDisplayImgTemplate.innerHTML);
	
	commentTemplate.remove();
	productDisplayImgTemplate.remove();
}

function parseCommentToHtml(comment){
	return template.parseComment(comment);
}

function parseProductDisplayImgToHtml(imgUrl, description){
	return template.parseProductDisplayImg({
		productDisplayImageUrl : imgUrl,
		productDescription : description
	});
}