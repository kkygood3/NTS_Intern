var template = {};

function initializeTemplate(){
	template.commentTemplate = document.querySelector("#comment_template").innerHTML;
}

function parseCommentToHtml(comment){
	var parseCommentTemplate = Handlebars.compile(template.commentTemplate);
	
	return parseCommentTemplate(comment);
}