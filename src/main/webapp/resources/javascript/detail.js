
function ajax(perform, method, url) {
	let oReq = new XMLHttpRequest();
	oReq.addEventListener('load', e=>{
		let data = e.target.response;
		perform(data);
	});
	oReq.responseType = 'json';
	oReq.open(method, url);
	oReq.send();
}

document.addEventListener('DOMContentLoaded', ()=>{
	ajax(test, 'GET', '/api/products/1');
});

function test(data){
	console.log(data);
}