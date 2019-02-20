function getBindTemplate(templateId) {
	var template = document.getElementById(templateId).innerText;
	return Handlebars.compile(template); 
}

function makeHtmlFromListData(datas, bindTemplate) {
	var html = ""
		datas.forEach((data) => {
		html += bindTemplate(data);
	});
	return html;
}
