/**
 * @author 육성렬
 */
document.addEventListener("DOMContentLoaded", function() {
	myReservationPage.init();
});

var myReservationPage = {
	values : {
		userEmail : document.querySelector("#container").dataset.userEmail,
		filteredData : {},
		subjectList : [],
		path : "/api/reservations",
		reservationSize : 0
	},

	objs : {
		header : null,
		okReservationList : null,
		cancelReservaionList : null,
		finishReservationList : null,
		popup : null
	},

	init : function() {
		this.requestMyReservations();
		this.initHeader();
		this.initReservationList();
		this.initPopup();
	},

	initHeader : function() {
		var headerContainer = document.querySelector(".my_summary");
		this.objs.header = new Header(headerContainer);
	},

	initReservationList : function() {
		var okReservationContainer = document.querySelector(".confirmed");
		this.objs.okReservationList = new ReservationList(
				okReservationContainer);
		this.objs.okReservationList.setHideBtn(false);
		this.objs.okReservationList.setBtnMsg("취소");
		this.subscribe(this.objs.okReservationList);

		var cancelReservationContainer = document.querySelector(".used.cancel");
		this.objs.cancelReservaionList = new ReservationList(
				cancelReservationContainer);
		this.objs.cancelReservaionList.setHideBtn(true);
		this.subscribe(this.objs.cancelReservaionList);

		var finishReservationContainer = document.querySelector(".used");
		this.objs.finishReservationList = new ReservationList(
				finishReservationContainer);
		this.objs.finishReservationList.setHideBtn(false);
		this.objs.finishReservationList.setBtnMsg("예매자 리뷰 남기기");
		this.subscribe(this.objs.finishReservationList);
	},

	initPopup : function() {
		this.objs.popup = new Popup(document
				.querySelector(".popup_booking_wrapper"));
		this.subscribe(this.objs.popup);
	},

	requestMyReservations : function() {
		var path = this.values.path;
		var params = {
			reservationEmail : this.values.userEmail
		};
		var options = {
			params : params
		}
		var self = this;
		httpUtil.sendGet(path, options, function(response) {
			self.requestMyReservationsCallback(response);
		});
	},

	requestCancelMyReservation : function(reservationId) {
		var path = this.values.path;
		var reservationId = reservationId;

		var self = this;
		httpUtil.sendPut(path + "/" + reservationId, function(response) {
			self.requestCancelMyReservationCallback(response);
		});
	},

	requestMyReservationsCallback : function(response) {

		if (response.status === 200) {
			var data = JSON.parse(response.responseText);
			var filteredData = this.filterReservations(data.reservations);
			this.values.filteredData = filteredData;
			this.values.reservationSize = data.size;
			this.updateMyReservationPage(filteredData,
					this.values.reservationSize);
		} else {
			alert("예약 목록을 불러오는 데 실패했습니다.");
		}
	},

	requestCancelMyReservationCallback : function(response) {

		if (response.status == 200) {
			var reservationId = Number(response.responseText);
			var okReservations = this.values.filteredData.okReservations;
			var targetReservation = {};

			for (var i = 0; i < okReservations.length; i++) {
				if (okReservations[i].reservationInfoId === reservationId) {
					targetReservation = okReservations[i];
					okReservations.splice(i, 1);
					break;
				}
			}

			this.values.filteredData.cancelReservaions.push(targetReservation);
			this.updateMyReservationPage(this.values.filteredData,
					this.values.reservationSize);
		} else {
			alert("예약을 취소하는 데 실패했습니다.");
		}
	},

	updateMyReservationPage : function(filteredData, size) {

		this.updateHeader(filteredData, size);
		if (size == 0) {
			this.updateEmptyReservation(true);
		} else {
			this.updateEmptyReservation(false);
			this.updateReservationList(filteredData);
		}
	},

	updateEmptyReservation : function(isEmpty) {
		var reservationList = document.querySelector(".wrap_mylist");
		var emptyContainer = document.querySelector(".err");
		reservationList.style.display = isEmpty ? "none" : "";
		emptyContainer.style.display = isEmpty ? "" : "none";
	},

	updateHeader : function(filteredData, size) {
		var totalCount = size;
		var okCount = filteredData.okReservations.length;
		var cancelCount = filteredData.cancelReservaions.length;
		var finishCount = filteredData.finishReservations.length;
		this.objs.header
				.updateUi(totalCount, okCount, finishCount, cancelCount);
	},

	updateReservationList : function(filteredData) {
		this.objs.okReservationList.setItems(filteredData.okReservations);
		this.objs.okReservationList.updateUi();

		this.objs.cancelReservaionList.setItems(filteredData.cancelReservaions);
		this.objs.cancelReservaionList.updateUi();

		this.objs.finishReservationList
				.setItems(filteredData.finishReservations);
		this.objs.finishReservationList.updateUi();
	},

	/**
	 * @function filterReservationList 받은 예약 목록을 필터링 한다.
	 * @param {JSON[]}
	 *            reservations
	 * @return {Object} 예매되있는 예약목록은 okReservations 배열 취소된 목록은 cancelReservations
	 *         배열 관람 완료된 목록은 finishReservations 배열
	 */
	filterReservations : function(reservations) {
		var initParam = {
			okReservations : [],
			cancelReservaions : [],
			finishReservations : []
		};
		var now = Date.now();

		var filteredReservations = reservations.reduce(function(prevValue,
				reservation) {
			if (reservation.cancelYn === true) {
				prevValue.cancelReservaions.push(reservation);
			} else if (reservation.reservationDate < now) {
				prevValue.finishReservations.push(reservation);
			} else {
				prevValue.okReservations.push(reservation);
			}
			return prevValue;
		}, initParam);

		return filteredReservations;
	},

	subscribe : function(target) {
		this.values.subjectList.push(target);
		target.addObserver(this);
	},

	unsubscribe : function(target) {
		this.values.subjectList = this.subjectList.filter(function(subject) {
			if (target !== subject) {
				return true;
			} else {
				return false;
			}
		});
	},

	/**
	 * @function update 구독 대상의 알림에 대한 갱신.
	 * @param {Object}
	 *            target
	 */
	update : function(target) {

		// 확인 된 예약 목록에서 값이 변경되었다고 요청하는 경우.
		if (target.container.classList.contains("confirmed")) {
			this.objs.popup.showPopup(target.clickedReservationId,
					target.clickedReservationTitle);

		} else if (target.container.classList.contains("popup_booking_wrapper")) {
			var reservationId = target.reservationId;
			this.requestCancelMyReservation(reservationId);
		} else if (target.container.classList.contains("used")) {
			var reservaionId = target.clickedReservationId;
			window.location.href = "/comment/"
					+ reservaionId;
		}
	}
};
var Header = (function() {

	/**
	 * @function Header header Contructor
	 * @param {Element}
	 *            headerContainer
	 */
	function Header(headerContainer) {
		this.container = headerContainer;
	}

	Header.prototype.updateUi = function(totalCount, okCount, finishCount,
			cancelCount) {
		var spanList = this.container.querySelectorAll(".figure");
		for (var i = 0; i < spanList.length; i++) {
			spanList[i].innerHTML = arguments[i];
		}
	};

	return Header;
})();

var ReservationList = (function() {

	/**
	 * @function ReservationList Constructor
	 * @param {Element}
	 *            container
	 */
	function ReservationList(container) {
		this.container = container;
		this.isHideBtn = false;
		this.btnMsg = "";
		this.items = [];
		this.observerList = [];
		this.clickedReservationId = -1;
	}

	ReservationList.prototype.setHideBtn = function(hide) {
		this.isHideBtn = hide;
	};

	ReservationList.prototype.setBtnMsg = function(msg) {
		this.btnMsg = msg;
	};

	ReservationList.prototype.setItems = function(items) {
		this.items = items;
	};

	ReservationList.prototype.addObserver = function(target) {
		this.observerList.push(target);
	};

	/**
	 * @function notify 옵저버들에게 변경 사항 알림.
	 */
	ReservationList.prototype.notify = function() {
		var self = this;
		this.observerList.forEach(function(observer) {
			observer.update(self);
		});
	};

	ReservationList.prototype.updateUi = function() {
		if (this.items.length == 0) {
			this.container.style.display = "none";
			return;
		}
		this.removeChildren();

		var bindTemplate = getTargetTemplate("#reservationItem");
		var resultHtml = "";

		var self = this;
		this.container.style.display = "";

		this.items.forEach(function(item) {
			var param = {
				reservationId : item.reservationInfoId,
				btnMsg : self.btnMsg,
				isHideBtn : self.isHideBtn,
				price : convertNumberToFormattedNumber(item.totalPrice),
				placeName : item.displayInfo.placeName,
				productName : item.displayInfo.productDescription,
				placeStreet : item.displayInfo.placeStreet,
				reservationDate : toDateString(item.reservationDate)
			};
			resultHtml += bindTemplate(param).trim();
		});
		this.container.innerHTML += resultHtml;
		this.addItemBtnClickListener();
	};

	ReservationList.prototype.addItemBtnClickListener = function() {
		var childNodes = this.container.querySelectorAll(".card_item");
		var self = this;
		childNodes.forEach(function(child) {
			child.addEventListener("click",function(event) {
				if (event.target.classList.contains("btn")
						|| event.target.parentNode.classList.contains("btn")) {
					self.clickedReservationId = event.currentTarget.dataset.reservationId;
					self.clickedReservationTitle = event.currentTarget.querySelector(".tit").innerHTML;
					self.notify();
				}
			});
		});
	};

	ReservationList.prototype.removeChildren = function() {
		var childNodes = this.container.querySelectorAll(".card_item");
		var self = this;
		childNodes.forEach(function(child) {
			self.container.removeChild(child);
		});
	};

	return ReservationList;
})();

var Popup = (function() {

	function Popup(container) {
		this.container = container;
		this.observerList = [];
		this.reservationId = -1;

		this.titleTextUi = this.container.querySelector(".pop_tit > span");
		this.cancelBtn = this.container.querySelector(".btn_bottom");
		this.okBtn = this.container.querySelector(".btn_green");
		this.closeBtn = this.container.querySelector(".popup_btn_close");

		var self = this;
		this.cancelBtn.addEventListener("click", function(event) {
			self.closePopup();
		});

		this.closeBtn.addEventListener("click", function(event) {
			self.closePopup();
		});

		this.okBtn.addEventListener("click", function(event) {
			self.notify();
		});
	}

	Popup.prototype.showPopup = function(reservationId, title) {
		this.reservationId = reservationId;
		this.titleTextUi.innerHTML = title;
		this.container.style.display = "block";
	};

	Popup.prototype.closePopup = function() {
		this.container.style.display = "none";
	};

	Popup.prototype.addObserver = function(target) {
		this.observerList.push(target);
	};

	/**
	 * @function notify 옵저버들에게 변경 사항 알림.
	 */
	Popup.prototype.notify = function() {
		var self = this;
		this.observerList.forEach(function(observer) {
			observer.update(self);
			self.closePopup();
		});
	};
	return Popup;
})();
