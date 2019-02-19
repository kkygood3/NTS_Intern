var template = {
	setting : function(){
		var templateList = document.querySelectorAll(".template");
		
		templateList.forEach((item)=>{
			var functionName = "parse" + item.dataset.parseName +"ToHtml";
			var templateHtml = item.innerHTML;
			
			this[functionName] = Handlebars.compile(templateHtml);
		})
	},

	setUseDateFormat : function(startIndex, length){
		Handlebars.registerHelper('formattingDate', (date) =>{
			return date.substr(startIndex, length);
		});
	},
}