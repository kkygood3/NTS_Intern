function ajax(perform, method, url) {
	let oReq = new XMLHttpRequest();
	oReq.addEventListener('load', e=>{
		let data = e.target.response;
		let status = e.target.status;
		
		if (status == 200){
			perform(data);
		}else if(status == 400){
			// TODO 예외 처리
		}else{
			location.href = '/error';
		}
	});
	oReq.responseType = 'json';
	oReq.open(method, url);
	oReq.send();
}