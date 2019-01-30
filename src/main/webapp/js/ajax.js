/**
 * @description : fetch API를 이용해 Request
 */
function dataRequest(sendData,requestInit,url){
	var productRequest = new Request(url+sendData,requestInit);
	var productData;
	
	return fetch(productRequest)
		   .then(function(response) { return response.json(); })
}