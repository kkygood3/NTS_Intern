/**
 * 
 */

function handleBarRenderer(data, target, item){
	let bindTemplate = Handlebars.compile(item);
	let commentsList = data;
	Handlebars.registerHelper('productName', function() {
		return state.detail_data.displayInfo.productDescription;
	});
	Handlebars.registerHelper('date', function(item) {
		return item.split(" ")[0];
	});
	let newCommentItems = 
		parser.parseFromString(
				bindTemplate({data : commentsList}),"text/html")
					.querySelectorAll("li");
	
	newCommentItems.forEach((item) => {
		target.append(item);
	});	
}