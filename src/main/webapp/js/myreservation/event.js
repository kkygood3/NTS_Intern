function addCancelButtonClickEvent() {
	var area = document.querySelector(".card.confirmed");
	area.addEventListener("click", function(event){
		var cancelButton = event.target;
		if (cancelButton.tagName != "BUTTON") {
			return;
		}
		var cardItem = cancelButton.closest(".card_item");
		var reservationId = cardItem.querySelector(".booking_number").innerText.replace("No.", "");
		$.ajax({
			method: "PUT",
			url: "/reservation_info/" + reservationId,
			success: function(data){
				alert(data);
				var canceled = document.querySelector(".card.cancel");
				canceled.appendChild(cardItem);
				cardItem.querySelector("button").remove();
            }
		});
	});
}
