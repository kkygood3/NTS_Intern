/**
 * @author 육성렬
 */

document.addEventListener("DOMContentLoaded", function() {
    bookingLoginPage.init();
});

var bookingLoginPage = {
    init: function() {
        var self = this;
        this.elements.loginBtn.addEventListener("click", function(event) {
            self.onClickLoginBtn(event);
        })
    },
    elements: {
        loginBtn: document.querySelector(".login_btn"),
        emailInput: document.querySelector("#resrv_id")
    },
    onClickLoginBtn: function(event) {
    	event.preventDefault();
    	
    	var self = this;
        var options = {
            params: {
                userEmail: self.elements.emailInput.value
            }
        }
        httpUtil.sendGet( "/api/login", options, function(response) {
            self.requestLoginCallback(response);
        });
    },
    requestLoginCallback(response){
        if(response.status === 200){
            window.location.href = JSON.parse(response.responseText).redirectUri;
        } else if(response.status == 400){
            alert("잘못 된 요청을 보냈습니다.")
        } else {
            alert("서버 내부 에러.")
        }
    }
}