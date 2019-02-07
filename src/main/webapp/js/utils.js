/**
 * @description : 수신된 item과 html mapping
 */
function replaceTemplateHTML(item, html){
	for(attribute in item){
		html = html.split('${'+attribute+'}').join(item[attribute]);
	}
	return  html;
}