/**
 * 
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
        
        document.querySelector("#form1")

        let xhr = new XMLHttpRequest();
        xhr.open("POST", "/reservation/api/loginform", true);
        xhr.onreadystatechange = function (aEvt) {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    window.location.href = "./myreservation"
                } else {
                    alert("Error fetching");
                }
            }
        };
        xhr.send(new FormData(document.querySelector("#form1")));
    });
}