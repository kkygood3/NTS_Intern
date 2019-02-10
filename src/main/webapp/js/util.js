// null or undefined or ""
function isEmpty(value) {
	return (value == null || value.length === 0);
}

function isNumber(val) {
	return val === +val;
}

/*
 * XMLHttpRequest를 생성하고 반환해주며
 * 미리 헤더값과 transfer error 일때 발생되는 이벤트를 등록해줍니다.
 */
function getXMLHttpRequest(url) {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
	xhr.responseType = "json";

	xhr.addEventListener("error", function(e) {
		alert("An error occurred while transferring the file.");
	});
	return xhr;
}

function replaceAll(str, searchStr, replaceStr) {
	return str.split(searchStr).join(replaceStr);
}

// data오브젝트의 키값들을 templateHTML에 매핑해 키에해당하는 값으로 변환해줍니다.
function parseTemplateToHTML(template, data) {
	var keys = Object.keys(data);
	for ( var i in keys) {
		template = replaceAll(template, "{" + keys[i] + "}", data[keys[i]]);
	}
	return template;
}