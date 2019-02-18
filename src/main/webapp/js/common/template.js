function getBindTemplate(templateId) {
	var template = document.getElementById(templateId).innerText;
	return Handlebars.compile(template); 
}

function makeHtmlFromListData(datas, bindTemplate) {
	var html = ""
	commentDisplayInfos.forEach((commentDisplayInfo) => {
		html += bindTemplate(commentDisplayInfo);
	});
	return html;
}
