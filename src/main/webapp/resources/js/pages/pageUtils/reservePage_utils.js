/**
 * Copyright 2019 NAVER Corp. All rights reserved. Except in the case of
 * internal use for NAVER, unauthorized use of redistribution of this software
 * are strongly prohibited.
 */

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

function SubmitButtonWithValidation(item) {
    item.addEventListener("click", (e) => {
        e.preventDefault();
        /*
		 * form check, getting name, email, tel from form directly, since this
		 * params "item" is equal to submit button.
		 */
        let nameValid = (/[가-힣a-zA-Z]+$/).test(rsvname.value);
        let emailValid = (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/).test(email.value);
        let telValid = (/^[\+]?[(]?[0-9]{2,3}[)]?[-\s\.]?[0-9]{3,4}[-\s\.]?[0-9]{4}$/im).test(tel.value);

        if (!nameValid) {
            inputValidationErrorMsg(rsvname);
            return;
        } else if (!telValid) {
            inputValidationErrorMsg(tel);
            return;
        } else if (!emailValid) {
            inputValidationErrorMsg(email);
            return;
        }

        // agreement check
        if (!domElements.agreementButton.checked) {
            alert("Please check agreement");
            return;
        }

        // tickets count check
        let priceDataArr = [];
        for (var key in state.prices) {
            if (state.prices.hasOwnProperty(key)) {
                if (state.prices[key].count > 0) {
                    priceDataArr.push(state.prices[key]);
                }
            }
        }
        if (priceDataArr.length == 0) {
            alert("No tickets Added to reserve");
            return;
        }

        sendReservation(priceDataArr);
    });
}

function sendReservation(priceDataArr) {
    var formData = new FormData(domElements.reservationForm);

    var d = new Date();
    let dataToSend = {
        displayInfoId: state.detail_data.displayInfo.displayInfoId
        , prices: priceDataArr
        , productId: state.detail_data.displayInfo.productId
        , reservationEmail: formData.get("email").toString()
        , reservationName: formData.get("rsvname").toString()
        , reservationTelephone: formData.get("tel").toString()
        , reservationYearMonthDay: d.getFullYear() + "/" + (d.getMonth() + 1) + "/" + d.getDate()
    }

    xhrRequest("POST"
        , "/reservation/api/reservations"
        , JSON.stringify(dataToSend)
        , () => {
            alert("SUCCESS");
            window.location.href = "/reservation";
        }, false);
}

function FormWatcher() {
    domElements.reservationForm.addEventListener("change", (e) => {
        formWatching();
    });
    domElements.agreementButton.addEventListener("change", (e) => {
        formWatching();
    });

}

function formWatching() {
    let bookButton = domElements.bookButtonWrapper;
    let formData = new FormData(domElements.reservationForm);
    if (formData.get("email").length > 0
        && formData.get("rsvname").length > 0
        && formData.get("tel").length > 0
        && domElements.agreementButton.checked) {
        if (bookButton.classList.contains("disable")) {
            bookButton.classList.remove("disable");
        }
    } else {
        bookButton.classList.add("disable");
    }
}

function EulaButton(item) {
    item.addEventListener("click", (e) => {
        let container = item.parentElement;
        if (container.classList.contains("open")) {
            container.classList.remove("open");
            item.querySelector(".btn_text").innerText = "보기";
            item.querySelector(".fn").classList.replace("fn-up2", "fn-down2");
        } else {
            container.classList.add("open");
            item.querySelector(".btn_text").innerText = "접기";
            item.querySelector(".fn").classList.replace("fn-down2", "fn-up2");
        }
    });
}


function CountController(item, _prices) {
    this.addButton = item.querySelector("a[title='더하기']");
    this.reduceButton = item.querySelector("a[title='빼기']");
    prices = _prices

    this.addButton.addEventListener("click", (e) => {
        this.increment();
    });

    this.reduceButton.addEventListener("click", (e) => {
        this.decrement();
    });
}

CountController.prototype.increment = function () {
    let wrapper = this.addButton.closest(".qty");
    let id = this.addButton.closest(".qty").dataset.id;
    let product = prices[id];
    debugger;
    product.count++;

    let qtyArea = wrapper.querySelector(".count_control_input");
    qtyArea.value = product.count;
    if (qtyArea.classList.contains("disabled")) {
        qtyArea.classList.remove("disabled");
    }
    if (this.reduceButton.classList.contains("disabled")) {
        this.reduceButton.classList.remove("disabled");
    }

    let totalPriceArea = wrapper.querySelector(".total_price");
    totalPriceArea.innerHTML = product.price * product.count;
    totalPriceArea.parentElement.style.color = "black";
}

CountController.prototype.decrement = function () {
    let wrapper = this.addButton.closest(".qty");
    let id = this.addButton.closest(".qty").dataset.id;
    let product = prices[id];

    if (product.count == 0) {
        return;
    }
    product.count--;

    let qtyArea = wrapper.querySelector(".count_control_input");
    qtyArea.value = product.count;
    if (product.count == 0) {
        if (!qtyArea.classList.contains("disabled")) {
            qtyArea.classList.add("disabled");
        }
        if (!this.reduceButton.classList.contains("disabled")) {
            this.reduceButton.classList.add("disabled");
        }
    }

    let totalPriceArea = wrapper.querySelector(".total_price");
    totalPriceArea.innerHTML = product.price * product.count;
    if (product.count == 0) {
        totalPriceArea.parentElement.style.color = "";
    }
}

CountController.prototype.updateTotalCountBottom = function () {
    let totalCount = 0;
    for (var key in state.prices) {
        var value = state.prices[key];
        totalCount += value.count;
    }
    this.totalCountBottom.innerHTML = totalCount;
}
