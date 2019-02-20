// null or undefined or ""
function isEmpty(value) {
	return (value == null || value.length === 0);
}

function isNumber(val) {
	return val === +val;
}

/**
 * XMLHttpRequest를 생성하고 서버로부터 request후 응답결과를 매개변수로 callback함수를 실행
 */
function ajax(callback, url, method, data) {
	var xhr = new XMLHttpRequest();
	if (!method) {
		method = "GET";
	}
	xhr.open(method, url);
	xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
	xhr.responseType = "json";

	xhr.addEventListener("load", function(e) {
		var response = e.target.response;

		callback(response);
	});
	xhr.addEventListener("error", function(e) {
		alert("An error occurred while transferring the file.");
	});

	if (data) {
		xhr.send(data);
	} else {
		xhr.send();
	}
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
