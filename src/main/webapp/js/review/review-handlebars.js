
Handlebars.registerHelper("print_comments_all", (displayInfo)=> {
	var commentListTemplate = document.querySelector("#review_list").innerText;
	var bindCommentTemplate = Handlebars.compile(commentListTemplate);
	
	var commentListHTML = "";
	
	for(var index=0, len = displayInfo.comments.length; index<len; index++){
		commentListHTML += bindCommentTemplate(displayInfo.comments[index]);
	}
	return (commentListHTML.split("${description}").join(displayInfo.displayInfo.description));
});

Handlebars.registerHelper("percentage_score",(score)=>{
	return score*20;
});