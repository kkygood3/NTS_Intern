function checkEmailValidate() {
    document.querySelector('.ng-pristine').addEventListener("submit", function (evt) {
        evt.preventDefault();
        var emailValue = document.querySelector("[name='reservationEmail']").value;
        var emailVaild = (/^[\w+_]\w+@\w+\.\w+$/).test(emailValue);

        if (!emailVaild) {
            alert("올바르지 않은 이메일 형식입니다.");
        } else {
            this.submit();
        }
    });
}

// DOMContentLoaded 초기 설정
document.addEventListener('DOMContentLoaded', function () {
    checkEmailValidate();
});