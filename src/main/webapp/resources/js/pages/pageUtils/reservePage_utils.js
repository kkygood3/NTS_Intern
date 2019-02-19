/**
 * Copyright 2019 NAVER Corp. All rights reserved. Except in the case of
 * internal use for NAVER, unauthorized use of redistribution of this software
 * are strongly prohibited.
 */

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

function SubmitButtonWithValidation(item, errorHandler, state) {
    item.addEventListener("click", (e) => {
        e.preventDefault();

        if (item.closest(".bk_btn_wrap").classList.contains("disable")) {
            alert("fill in the form correctly");
            return;
        }

        /*
		 * form check, getting name, email, tel from form directly, since this
		 * params "item" is equal to submit button.
		 */
        let nameValid = (/[가-힣a-zA-Z]+$/).test(rsvname.value);
        let emailValid = (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/).test(email.value);
        let telValid = (/^[\+]?[(]?[0-9]{2,3}[)]?[-\s\.]?[0-9]{3,4}[-\s\.]?[0-9]{4}$/im).test(tel.value);
        // tickets count check
        let priceDataArr = []
        for (var key in state.prices) {
            if (state.prices[key].count > 0) {
                priceDataArr.push(state.prices[key]);
            }
        }
        if (priceDataArr.length == 0) {
            alert("please add at least 1 ticket to reserve");
            return;
        } else if (!nameValid) {
            errorHandler(rsvname);
            return;
        } else if (!telValid) {
            errorHandler(tel);
            return;
        } else if (!emailValid) {
            errorHandler(email);
            return;
        }


        sendReservation(priceDataArr,
            {
                name: rsvname.value
                , email: email.value
                , tel: tel.value
                , displayInfoId: state.detail_data.displayInfo.displayInfoId
                , productId: state.detail_data.displayInfo.productId
            });
    });
}

function sendReservation(priceDataArr, params) {
    var d = new Date();
    let dataToSend = {
        displayInfoId: params.displayInfoId
        ,
        prices: priceDataArr
        ,
        productId: params.productId
        ,
        reservationEmail: params.email
        ,
        reservationName: params.name
        ,
        reservationTelephone: params.tel
        ,
        reservationYearMonthDay: d.getFullYear() + "/" + (d.getMonth() + 1) + "/" + (d.getDate() + Math.floor(Math.random() * 5))
    }

    let request = new XhrRequest("POST", "/reservation/api/reservations");
    request.setCallback(() => {
        alert("SUCCESS");
        window.location.href = "/reservation";
    });
    request.setIsAsync(false);
    request.send(JSON.stringify(dataToSend))
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
        this.control(true);
    });

    this.reduceButton.addEventListener("click", (e) => {
        this.control(false);
    });
}

CountController.prototype.control = function (isIncrement) {

    let wrapper = this.addButton.closest(".qty");
    let id = this.addButton.closest(".qty").dataset.id;
    let product = prices[id];
    if (isIncrement) {
        product.count++;
    } else {
        if (product.count == 0) {
            return;
        }
        product.count--;
    }

    let qtyArea = wrapper.querySelector(".count_control_input");
    qtyArea.value = product.count;
    let totalPriceArea = wrapper.querySelector(".total_price");

    if (product.count == 0) {
        if (!qtyArea.classList.contains("disabled")) {
            qtyArea.classList.add("disabled");
        }
        if (!this.reduceButton.classList.contains("disabled")) {
            this.reduceButton.classList.add("disabled");
        }
        totalPriceArea.parentElement.style.color = "";
    } else {
        if (qtyArea.classList.contains("disabled")) {
            qtyArea.classList.remove("disabled");
        }
        if (this.reduceButton.classList.contains("disabled")) {
            this.reduceButton.classList.remove("disabled");
        }
        totalPriceArea.parentElement.style.color = "black";
    }

    totalPriceArea.innerHTML = product.price * product.count;
}

