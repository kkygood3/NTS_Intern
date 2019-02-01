/**
 * 
 */
document.addEventListener("DOMContentLoaded", function() {
	var id = getDisplayInfoIdFromUrl();
	
	sendGet("/reservation-service/api/products/"+id, {}, function(response){
		console.log(response);
	}.bind(this));
})