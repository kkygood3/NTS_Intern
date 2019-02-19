var template = {
	setting : function(){
		var templateList = document.querySelectorAll(".template");
		
		templateList.forEach((item)=>{
			var toHtmlFunctionName = "parse" + item.dataset.parseName +"ToHtml";
			var toElementFunctionName = "parse" + item.dataset.parseName +"ToElement";
			var templateHtml = item.innerHTML;
			
			this[toHtmlFunctionName] = Handlebars.compile(templateHtml);
			this[toElementFunctionName] = (item) =>{
				var html = this[toHtmlFunctionName](item);
				return this._htmlToElement(html);
			}
		});
	},
	
	_htmlToElement : function(html){
		var dummyElement = document.createElement("div");
		dummyElement.innerHTML = html;
		return dummyElement.firstElementChild;
	},

	setUseDateFormat : function(startIndex, length){
		Handlebars.registerHelper("formattingDate", (date) =>{
			return date.substr(startIndex, length);
		});
	},
	
	addNewFunction : function(name, func){
		Handlebars.registerHelper(name, func);
	},
}