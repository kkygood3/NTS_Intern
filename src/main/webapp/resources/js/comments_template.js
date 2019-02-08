var template = {};

function initializeTemplate(){
	var commentTemplate = document.querySelector("#comment_template")
	
	Handlebars.registerHelper('formattingDate', (date) =>{
		return date.substr(0,10);
	});
	
	template.parseComment = Handlebars.compile(commentTemplate.innerHTML);
	
	commentTemplate.remove();
}

function parseCommentToHtml(comment){
	return template.parseComment(comment);
}
