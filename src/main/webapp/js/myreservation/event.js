function addCancelButtonClickEvent() {
	var area = document.querySelector(".card.confirmed");
	area.addEventListener("click", function(event){
		var cancelButton = event.target;
		if (cancelButton.tagName != "BUTTON") {
			return;
		}
		var cardBody = cancelButton.closest(".card_body");
		var reservationId = cardBody.querySelector(".booking_number").innerText.replace("No.", "");
		$.ajax({
			method: "PUT",
			url: "/reservation_info/" + reservationId,
			success: function(data){
				alert(data);
            }
		});
	});
}
