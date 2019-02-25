document.addEventListener("DOMContentLoaded", () => {
	
	template.setting();
	template.setUseDateFormat(0, 10);
	
	template.addNewFunction("isReserved", function(reservationStatus, options){
		if(reservationStatus === "RESERVED"){
			return options.fn(this);
		}
		else{
			return options.inverse();
		}
	});
	
	template.addNewFunction("isUsed", function(reservationStatus, options){
		if(reservationStatus === "USED"){
			return options.fn(this);
		}
		else{
			return options.inverse();
		}
	});
	
	var ajax = new Ajax();
	ajax.get("/api/reservation-histories", printReservationHistory);
	
	
});

function printReservationHistory(responseObj){
	var reservationHistoryList = new AllReservationHistoryList(responseObj.reservationHistoryList);
	
	reservationHistoryList.print();
}

function ReservationHistory(reservationHistory){
	this.reservationId = reservationHistory.reservationId;
	this.element = template.parseReservationHistoryToElement(reservationHistory);
	
	if(reservationHistory.reservationStatus === "RESERVED"){
		var cancelBtn = this.element.querySelector("#cancel");
		
		cancelBtn.addEventListener("click", () =>{
			cancelPopup.open(reservationHistory);
		});
	}
}

function AllReservationHistoryList(reservationHistoryList){
	this.count = reservationHistoryList.length;
	this.classifyList(reservationHistoryList);
}

AllReservationHistoryList.prototype = {
	classifyList : function(reservationHistoryList){
		this.classifiedLists = {};
		reservationHistoryList.forEach((reservationHistory) => {
			if(!this.classifiedLists.hasOwnProperty(reservationHistory.reservationStatus)){
				this.classifiedLists[reservationHistory.reservationStatus] = 
					new ClassifiedReservationHistoryList(reservationHistory.reservationStatus);
			}
			this.classifiedLists[reservationHistory.reservationStatus].push(reservationHistory);
		});
	},

	print : function(){
		var countElement = document.querySelector("#all_count");
		countElement.innerText = this.count;
		this.isHistoryEmpty();
		
		for(reservationStatus in this.classifiedLists){
			this.classifiedLists[reservationStatus].print();
		}
	},
	
	isHistoryEmpty : function(){
		var historyEmpty = document.querySelector("#history_empty"); 
		if(this.count > 0){
			historyEmpty.classList.add("hide");
		}
		else{
			historyEmpty.classList.remove("hide");
		}
	}
}

function ClassifiedReservationHistoryList(reservationStatus){
	this.reservationStatus = reservationStatus;
	this.list = [];
}

ClassifiedReservationHistoryList.prototype = {
	push : function(reservationHistory){
		this.list.push(new ReservationHistory(reservationHistory));
	},

	print : function(){
		var countElement = document.querySelector("#" + this.reservationStatus.toLowerCase() + "_count");
		countElement.innerText = this.list.length;
		
		var listElement = document.querySelector("#" + this.reservationStatus.toLowerCase() + "_reservation_history_list");
		
		if(this.list.length > 0){
			listElement.classList.remove("hide");
		}
		
		this.list.forEach((reservationHistory) => {
			listElement.appendChild(reservationHistory.element);
		});
	}
}

var cancelPopup = new function(){
	this.element = document.querySelector("#cancel_popup");
	this.btnYes = this.element.querySelector("#btn_cancel_yes");
	this.btnClose = this.element.querySelector("#btn_cancel_close");
	this.btnNo = this.element.querySelector("#btn_cancel_no");

	this.open = function(reservationHistory){
		var title = this.element.querySelector("#cancel_title");
		var date = this.element.querySelector("#cancel_date");
		
		this.reservationId = reservationHistory.reservationId;
		title.innerText = reservationHistory.productDescription;
		date.innerText = reservationHistory.reservationDate.substr(0,10);
		
		this.element.style.display = "block";
	};
	
	this.close = function(){
		this.element.style.display = "none";
	};
	
	this.btnClose.addEventListener("click", this.close.bind(this));
	this.btnNo.addEventListener("click", this.close.bind(this));
	this.btnYes.addEventListener("click", () =>{
		var ajax = new Ajax();
		ajax.put("/api/reservation/" + this.reservationId + "/cancel", () =>{
			alert("취소 완료");
			window.location.reload();
		});
	});
}();