function cancelButtonDown(reservationInfoId, reservationType){
	//if(reservationType === "SCHEDULED"){
		//reservationCancelRequest(reservationInfoId)
		
		var reservationScheduled = reservationInfoObject.reservationInfoScheduled;
		for(var i=0, len=reservationScheduled.length; i<len; i++){
			console.log(reservationInfoId)
			if(reservationScheduled[i].reservation.reservationInfoId === reservationInfoId){
				reservationInfoObject.appendElement(reservationScheduled[i], "CANCELED");
				reservationInfoObject.removeElement(i, reservationType);
				cleanUL();
				
				if(document.querySelector("#reservation_list_button_scheduled").className.includes("on")){
					reservationInfoObject.render("SCHEDULED");
				} else{
					reservationInfoObject.render();
				}
				break;
			}
		}
	//	}
	//} else{
	//	
	//}
}

function reservationCancelRequest(reservationInfoId){
	var request = {
			method:"PUT",
			contentType:"application/x-www-form-urlencoded",
			queryString:""
	}
	var requestUri = "/api/reservations/" + reservationInfoId;
	sendRequest(request, requestUri);
}



function cleanUL(event){
	document.querySelector("#reservation_ul").innerHTML = "";
	if(event){
		document.querySelectorAll(".link_summary_board").forEach((v)=>{
			v.className = "link_summary_board";
		});
		
		var listButton = event.target;
		if(event.target.tagName != "A"){
			listButton = event.target.parentElement;
		}
		listButton.className += " on";
	}
}