function ajax(perform, method, url, errorMessage) {
	let oReq = new XMLHttpRequest();
	oReq.addEventListener('load', e=>{
		let data = e.target.response;
		let status = e.target.status;
		
		if (status == 200){
			perform(data);
		}else if(status == 400){
			alert(errorMessage);
			window.history.back();
		}else if(status == 403){
			alert('허용되지 않은 접근 입니다.');
		}else{
			location.href = '/error';
		}
	});
	oReq.responseType = 'json';
	oReq.open(method, url);
	oReq.send();
}