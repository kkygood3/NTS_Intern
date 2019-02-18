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
        
        let data = {resrv_email : new FormData(document.querySelector("#form1")).get("resrv_email")}
        console.log(JSON.stringify(data));
        console.log(data)
        xhrRequest("POST"
        		, "/reservation/api/login"
        		, new FormData(document.querySelector("#form1")).get("resrv_email")
        		, () => {
        				window.location.href = "./myreservation"
        			}
        		, true);
    });
}