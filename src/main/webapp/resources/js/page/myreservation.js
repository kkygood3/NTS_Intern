document.addEventListener("DOMContentLoaded", () => {
	
	template.setting();
	template.setUseDateFormat(0, 10);
	
	template.addNewFunction("isReserved", (reservationStatus, options) =>{
		if(reservationStatus === "Reserved"){
			return options.fn(this);
		}
		else{
			return options.inverse(this);
		}
	});
	
	template.addNewFunction("isUsed", (reservationStatus, options) =>{
		if(reservationStatus === "Used"){
			return options.fn(this);
		}
		else{
			return options.inverse(this);
		}
	});
	
	ajaxGet("/api/reservation-histories", printReservationHistory);
	
	
});

function printReservationHistory(data){
	var reservationHistoryList = new AllReservationHistoryList(JSON.parse(data).reservationHistoryList);
	
	reservationHistoryList.print();
}

function ReservationHistory(reservationHistory){
	this.reservationId = reservationHistory.reservationId;
	this.element = template.parseReservationHistoryToElement(reservationHistory);
	
	if(reservationHistory.reservationStatus === "Reserved"){
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
			historyEmpty.className += " hide";
		}
		else{
			historyEmpty.className = historyEmpty.className.replace(" hide" ,"");
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
			listElement.className = listElement.className.replace(" hide", "");;
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
		ajaxPut("/api/reservation/" + this.reservationId + "/cancel", () =>{
			window.location.reload();
		});
	});
}();