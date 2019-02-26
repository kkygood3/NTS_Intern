/**
 * @author 육성렬
 */

document.addEventListener("DOMContentLoaded", function() {
    reservationPage.init();
});

var reservationPage = {
    objs: {

        totalTicketCount: null,
        ticketUiList: [],
        inputTextUiList: [],
        agreementTextUiList: [],
        reserverInputTextUi: null,
        telephoneInputTextUi: null,
        emailInputTextUi: null,
        inputAgreementCheckbox: null,
        reservationBtn: null
    },

    init: function() {

        this.initTotalTicketCount();
        this.initTicketUiList();
        this.initInputTextUiList();
        this.initInputCheckbox();
        this.initReservationBtn();
        this.initAgreementTextUiList();
        this.initObserverPattern();
    },

    initTotalTicketCount: function() {
        var container = document.querySelector("#totalCount");
        this.objs.totalTicketCount = new TotalTicketCount(container);
    },

    initTicketUiList: function() {
        var self = this;
        document.querySelectorAll(".count_control").forEach(function(ticketContainer) {

            var ticketObj = new Ticket(ticketContainer);
            self.objs.ticketUiList.push(ticketObj);
        });
    },

    initInputTextUiList: function() {

        var reserverInput = document.querySelector(".input_reserver");
        var nameRegex = regex.USERNAME_REGEX;
        var nameWarningMsg = "잘못된 형식의 이름입니다. (17자 이하)";
        var reserverInputTextUi = new InputTextUi(reserverInput, nameRegex, nameWarningMsg);
        this.objs.inputTextUiList.push(reserverInputTextUi);
        this.objs.reserverInputTextUi = reserverInputTextUi;

        var telephoneInput = document.querySelector(".input_telephone");
        var telephoneRegex = regex.PHONE_NUMBER_REGEX;
        var telephoneWarningMsg = "잘못된 형식의 휴대폰 번호입니다.";
        var telephoneInputTextUi = new InputTextUi(telephoneInput, telephoneRegex, telephoneWarningMsg);
        this.objs.inputTextUiList.push(telephoneInputTextUi);
        this.objs.telephoneInputTextUi = telephoneInputTextUi;

        var emailInput = document.querySelector(".input_email");
        var emailRegex = regex.EMAIL_REGEX;
        var emailWarningMsg = "잘못된 형식의 이메일입니다.";
        var emailInputTextUi = new InputTextUi(emailInput, emailRegex, emailWarningMsg);
        this.objs.inputTextUiList.push(emailInputTextUi);
        this.objs.emailInputTextUi = emailInputTextUi;
    },

    initInputCheckbox: function() {

        var agreementCheckbox = document.querySelector(".chk_agree");
        this.objs.inputAgreementCheckbox = new InputCheckboxUi(agreementCheckbox);
    },
    initReservationBtn: function() {
        var reservationBtnContainer = document.querySelector(".bk_btn_wrap");
        this.objs.reservationBtn = new ReservationBtn(reservationBtnContainer);
    },

    initAgreementTextUiList: function() {

        var agreementTextUis = document.querySelectorAll(".agreement:not(.all)");
        var self = this;
        agreementTextUis.forEach(function(textUi) {

            self.initAgreementTextUi(textUi);
        });
    },

    initAgreementTextUi: function(textUi) {
        
        this.objs.agreementTextUiList.push(textUi);
        var self = this;
        
        textUi.addEventListener("click", function(event) {
            self.onClickAgreementOpenBtn(event.target, textUi);
        })
    },

    onClickAgreementOpenBtn: function(target, textUi) {
        if(textUi.classList.contains("open")) {
            return;
        }
        
        var classList = target.classList;
        if(classList.contains("btn_agreement") || classList.contains("fn")
         || classList.contains("btn_text")) {
            textUi.classList.add("open");
        }
        
    },
    initObserverPattern: function() {

        var self = this;
        this.objs.ticketUiList.forEach(function(ticket) {
            self.objs.totalTicketCount.subscribe(ticket);
        });
        this.objs.inputTextUiList.forEach(function(inputText) {
            self.objs.reservationBtn.subscribe(inputText);
        });

        this.objs.reservationBtn.subscribe(this.objs.totalTicketCount);
        this.objs.reservationBtn.subscribe(this.objs.inputAgreementCheckbox);
    },

    getReservationData: function() {

        var container = document.querySelector(".ct");
        var data = {
            displayInfoId: container.dataset.displayId,
            productId: container.dataset.productId,
            prices: [],
            reservationEmail: this.objs.emailInputTextUi.getInputTextData(),
            reservationName: this.objs.reserverInputTextUi.getInputTextData(),
            reservationTel: this.objs.telephoneInputTextUi.getInputTextData(),
            reservationDate: this.objs.totalTicketCount.getReservationDate()
        };

        var self = this;
        this.objs.ticketUiList.forEach(function(ticket) {
            var ticketData = ticket.getTicketData();
            if (ticketData != null) {
                data.prices.push(ticketData);
            }
        });
        return data;
    }
};

/**
 * @constructor  Ticket Constructor
 */
var Ticket = (function() {
    /**
     * @param {Element} ticketContainer
     */
    function Ticket(ticketContainer) {

        this.container = ticketContainer;
        this.price = convertFormattedNumberToNumber(ticketContainer.dataset.price);
        this.minusBtn = ticketContainer.querySelector(".ico_minus3");
        this.plusBtn = ticketContainer.querySelector(".ico_plus3");
        this.countInput = ticketContainer.querySelector(".count_control_input");
        this.totalPriceTextUi = ticketContainer.querySelector(".total_price");
        this.observerList = [];

        var self = this;
        this.container.addEventListener("click", function(event) {
            self.onClickBtnListener(event.target);
        });
    }

    /**
     * @function onClickBtnListener 플러스 마이너스 버튼에 대한 클릭 리스너.
     * @param {Element} target 클릭되어진 버튼
     */
    Ticket.prototype.onClickBtnListener = function(target) {

        if (target == this.minusBtn) {
            if (this.countInput.value == 0) {
                return;
            }
            this.countInput.value = Number(this.countInput.value) - 1;
        } else if (target == this.plusBtn) {
            this.countInput.value = Number(this.countInput.value) + 1;
        } else {
            return;
        }

        this.updateUiClassName(this.countInput.value);
        var totalPrice = this.price * Number(this.countInput.value);
        this.totalPriceTextUi.innerHTML = convertNumberToFormattedNumber(totalPrice);
        this.notify();
    };

    /**
     * @function updateUiClassName 티켓 수에 따른 UI 변경..
     * @param {Number} count 해당 UI에 현재 티켓 수.
     */
    Ticket.prototype.updateUiClassName = function(count) {

        if (count == 0) {
            this.minusBtn.classList.add("disabled");
            this.countInput.classList.add("disabled");
            this.totalPriceTextUi.parentElement.classList.remove("on_color");
        } else {
            this.minusBtn.classList.remove("disabled");
            this.countInput.classList.remove("disabled");
            this.totalPriceTextUi.parentElement.classList.add("on_color");
        }
    };

    Ticket.prototype.addObserver = function(target) {

        this.observerList.push(target);
    };

    /**
     * @function notify 옵저버들에게 변경 사항 알림.
     */
    Ticket.prototype.notify = function() {
        var self = this;
        this.observerList.forEach(function(observer) {
        
            observer.update(self);
        });
    };

    Ticket.prototype.getTicketCount = function() {

        return Number(this.countInput.value);
    };

    /**
     * @typedef {Object} ReturnParseTicketData
     * @property {number} count
     * @property {number} productPriceId
     */
    /**
     * @function getTicketData 티켓 UI에서 데이터 추출
     * @returns {ReturnParseTicketData}
     */
    Ticket.prototype.getTicketData = function() {
        if (this.countInput.value == 0) {
            return null;
        } else {
            return {
                count: this.countInput.value,
                productPriceId: this.container.dataset.priceId
            };
        }
    };
    return Ticket;
})();

/**
 * @constructor TotalTicketCount Constructor
 */
var TotalTicketCount = (function() {

    /**
     * @param {Element} totalTicketCount
     */
    function TotalTicketCount(totalTicketCount) {
        this.container = totalTicketCount;
        this.subjectList = [];
        this.observerList = [];
    }

    TotalTicketCount.prototype.getReservationDate = function() {
        return Date.parse(this.container.dataset.reservationDate);
    };

    TotalTicketCount.prototype.isValidInputValue = function() {
        return Number(this.container.innerHTML) != 0;
    };

    TotalTicketCount.prototype.subscribe = function(target) {

        this.subjectList.push(target);
        target.addObserver(this);
    };

    TotalTicketCount.prototype.unsubscribe = function(target) {

        this.subjectList = this.subjectList.filter(function(subject) {
            if (target !== subject) {
                return true;
            } else {
                return false;
            }
        });
    };

    /**
     * @function update 구독 대상의 알림에 대한 갱신.
     * @param {Object} target
     */
    TotalTicketCount.prototype.update = function(target) {

        var totalCount = 0;
        this.subjectList.forEach(function(subject) {
            totalCount += subject.getTicketCount();
        });

        this.container.innerHTML = totalCount;
        this.notify();
    };

    TotalTicketCount.prototype.addObserver = function(target) {

        this.observerList.push(target);
    };

    /**
     * @function notify 옵저버들에게 변경 사항 알림.
     */
    TotalTicketCount.prototype.notify = function() {

        var self = this;
        this.observerList.forEach(function(observer) {
            observer.update(self);
        });
    };

    return TotalTicketCount;
})();

/**
 * @constructor
 */
var InputTextUi = (function() {
    /**
     * @param {Element} inputContainer
     * @param {RegExp} regex
     * @param {String} warningMsg
     */
    function InputTextUi(inputContainer, regex, warningMsg) {

        this.container = inputContainer;
        this.regex = regex;
        this.inputTextControl = inputContainer.querySelector("input");
        this.warningMsgTextUi = inputContainer.querySelector(".warning_msg");
        this.warningMsgTextUi.innerHTML = warningMsg;
        this.observerList = [];

        var self = this;
        this.inputTextControl.addEventListener("blur", function(target) {
            self.onBlurInputTextControlListener(target);
        });

        this.warningMsgTextUi.addEventListener("click", function(target) {
            self.onClickWarningMsgTextUiListener(target);
        });
    }

    InputTextUi.prototype.isValidInputValue = function() {

        var text = this.inputTextControl.value;
        return this.regex.test(text);
    };

    InputTextUi.prototype.onClickWarningMsgTextUiListener = function(target) {

        this.inputTextControl.focus();
        this.setVisibleWarningMsg(false);
    };

    InputTextUi.prototype.setVisibleWarningMsg = function(visible) {

        if (visible) {
            this.warningMsgTextUi.style.visibility = "visible";
        } else {
            this.warningMsgTextUi.style.visibility = "hidden";
        }
    };

    InputTextUi.prototype.onBlurInputTextControlListener = function(target) {

        if (!this.isValidInputValue()) {
            this.setVisibleWarningMsg(true);
        }
        this.notify();
    };

    InputTextUi.prototype.getInputTextData = function() {

        return this.inputTextControl.value;
    };

    InputTextUi.prototype.addObserver = function(target) {

        this.observerList.push(target);
    };

    /**
     * @function notify 옵저버들에게 변경 사항 알림.
     */
    InputTextUi.prototype.notify = function() {

        var self = this;
        this.observerList.forEach(function(observer) {
            observer.update(self);
        });
    };

    return InputTextUi;
})();

/**
 * @constructor
 */
var InputCheckboxUi = (function() {
    function InputCheckboxUi(inputContainer) {

        this.container = inputContainer;
        this.observerList = [];

        var self = this;
        this.container.addEventListener("click", function(event) {
            self.onClickCheckboxListener(event.target);
        });
    }

    InputCheckboxUi.prototype.onClickCheckboxListener = function(target) {

        this.notify();
    };

    InputCheckboxUi.prototype.isValidInputValue = function() {

        return this.container.checked;
    };

    InputCheckboxUi.prototype.addObserver = function(target) {

        this.observerList.push(target);
    };

    /**
     * @function notify 옵저버들에게 변경 사항 알림.
     */
    InputCheckboxUi.prototype.notify = function() {

        var self = this;
        this.observerList.forEach(function(observer) {
            observer.update(self);
        });
    };

    return InputCheckboxUi;
})();

/**
 * @constructor
 */
var ReservationBtn = (function() {

    function ReservationBtn(btnContainer) {

        this.container = btnContainer;
        this.subjectList = [];

        var self = this;
        this.container.addEventListener("click", function(event) {
            self.onClickBtnListener(event.target);
        });
    }

    ReservationBtn.prototype.onClickBtnListener = function(target) {

        if (this.container.classList.contains("disable")) {
            return;
        }

        var params = reservationPage.getReservationData();
        httpUtil.sendPostWithJson("/api/reservations", params, this.onRequestReservationPostCallback);
    };

    ReservationBtn.prototype.onRequestReservationPostCallback = function(response) {

        if (response.status == 200) {
            var result = JSON.parse(response.responseText);
            window.location.replace(result.redirectUri);
        } else if (response.status == 500) {
            alert("서버 내부 오류.");
        } else {
            alert("잘못 된 요청");
        }
    };

    ReservationBtn.prototype.subscribe = function(target) {

        this.subjectList.push(target);
        target.addObserver(this);
    };

    ReservationBtn.prototype.unsubscribe = function(target) {

        this.subjectList = this.subjectList.filter(function(subject) {
            if (target !== subject) {
                return true;
            } else {
                return false;
            }
        });
    };

    /**
     * @function update 구독 대상의 알림에 대한 갱신.
     * @param {Object} target
     */
    ReservationBtn.prototype.update = function(target) {

        var isValidAllValue = this.subjectList.reduce(function(prevValue, subject) {
            return subject.isValidInputValue() && prevValue;
        }, true);
        this.setUsableBtn(isValidAllValue);
    };

    ReservationBtn.prototype.setUsableBtn = function(usable) {
        
        if (usable) {
            this.container.classList.remove("disable");
        } else {
            this.container.classList.add("disable");
        }
    };
    return ReservationBtn;
})();
