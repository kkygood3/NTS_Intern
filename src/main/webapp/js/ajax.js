/**
 * @description : fetch API를 이용해 Request
 */

function dataRequestGET(url, queryString, request){
	return fetch(new Request(url + queryString, request))
		   .then(function(response) { return response.json(); })
}