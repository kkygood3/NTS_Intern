/**
 * Copyright 2019 NAVER Corp. All rights reserved. Except in the case of
 * internal use for NAVER, unauthorized use of redistribution of this software
 * are strongly prohibited.
 */

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

function ReservationCard(_reservation, reservationData, _popup) {
    this.reservation = _reservation
    this.cancelButton = _reservation.querySelector(".btn");
    this.cancelButtonWrapper = _reservation.querySelector(".booking_cancel");
    this.ReservationId = _reservation.dataset.id;
    this.reservationDesc = "";
    this.reservationDate = "";
    
    sectionUsed = document.querySelector(".card.used");
    sectionCanceled = document.querySelector(".card.used.cancel");
    popup = _popup;

    // search for corresponding data;
    for (var key in reservationData) {
        if (reservationData[key].id == this.ReservationId) {
            this.reservationDesc = reservationData[key].displayInfo.productDescription;
            this.reservationDate = reservationData[key].reservationDate.split(" ")[0];
            break;
        }
    }
    if (new Date(this.reservationDate) < Date.now()) {
        sectionUsed.appendChild(this.reservation);
    	this.cancelButton.innerText = "한줄평 남기기"
    	this.cancelButton.addEventListener("click", () => {
            window.location.href = "/reservation/myreservation/reviewWrite/"+this.ReservationId;
        });
    } else if (this.reservation.dataset.cancelFlag == "true") {
        sectionCanceled.appendChild(this.reservation);
        this.cancelButtonWrapper.style.display = "none";
    }  else {
        this.cancelButton.addEventListener("click", () => {
            this.cancellationPopup();
        });
    }
}

/**
 * @cancellationPopup() : render popup asking user to confirm the cancellation
 *                      process, depends on event, this method wiill proceed to
 *                      actual cancellation of reservation
 */
ReservationCard.prototype.cancellationPopup = function () {
    var event = (e) => {
        if (e.target.classList.contains("_close_control")) {
            popup.style.display = "none";
            popup.removeEventListener("click", event);
        } else if (e.target.classList.contains("_process_control")) {
            this.cancelReservation();
        }
    };

    popup.querySelector(".pop_tit span").innerHTML = this.reservationDesc;
    popup.querySelector(".pop_tit .sm").innerHTML = this.reservationDate;
    popup.addEventListener("click", event);
    popup.style.display = "block";
}

/**
 * @cancelReservation() : this method is the actual method that cancels
 *                      reservation. This method will only get called by
 *                      cancellationPopup event listener
 */
ReservationCard.prototype.cancelReservation= function () {
    let request = new XhrRequest("PUT", "/reservation/api/reservations/" + this.ReservationId); 
    request.setCallback(() => {
        alert("\"" + this.reservationDesc + "\"에 대한 예약이 취소되었습니다.");
        popup.style.display = "none";
        popup.removeEventListener("click", event);
        sectionCanceled.appendChild(this.reservation);
        this.cancelButtonWrapper.style.display = "none";
    });
    request.send();
    
}
