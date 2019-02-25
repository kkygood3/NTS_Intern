/**
 * Copyright 2019 NAVER Corp. All rights reserved. Except in the case of
 * internal use for NAVER, unauthorized use of redistribution of this software
 * are strongly prohibited.
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

window.addEventListener("DOMContentLoaded", function () {
    this.init();
});

function init() {
    this.submitButton = document.querySelector(".login_btn");

    this.submitButton.addEventListener("click", (e) => {
        e.preventDefault();
        let email = new FormData(document.querySelector("#form1")).get("resrv_email");

        let emailValid = (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/).test(email);
        if (!emailValid) {
            alert("Wrong email type");
            return;
        }
        console.log(email);
        let formData = new FormData();
        formData.append("email", email)
        
        let request = new XhrRequest("POST", "/reservation/api/login");
        request.setCallback(() => {
            window.location.href = "/reservation/myreservation"
        });
        request.setIsAsync(false);
        request.multipartSend(formData);
    });
}