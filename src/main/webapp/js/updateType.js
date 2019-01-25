function updateType(id) {
	var todo = document.querySelector("#"+id);
	var type = todo.parentElement.id.split("-")[1];
	//console.log(type);
	
	var xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.addEventListener("load", function() {
		//console.log(this.responseText);
		
		var afterType;
		if (type === "TODO") {
			afterType = "DOING";
		} else if (type === "DOING") {
			afterType = "DONE";
		}

		if (type === "DOING") {
			var updateButton = todo.querySelector(".updateButton");
			todo.removeChild(updateButton);
		}
		
		var beforeList = todo.parentElement;
		var afterList = document.querySelector("#ul-" + afterType);
		updateList(todo, beforeList, afterList);
		
	});
	console.log(type);
	xmlHttpRequest.open("PUT", "/todoUpdate?id=" + id.split("-")[2] + "&type=" + type);
	xmlHttpRequest.send();
}

document.addEventListener("DOMContentLoaded", function(){
	var todoSection = document.querySelector("#section-content-main");
	//alert("DEBUG : " + todoSection)
	todoSection.addEventListener("click", function(evt){
		//alert("DEBUG : Click")
		if(evt.target.className === "updateButton"){
			var id = evt.target.parentNode.parentElement.id;
			
			/*alert("[updateType 테스트] id : " + evt.target.parentNode.parentElement.id);
			alert("[updateType 테스트] [0] : " + evt.target.parentElement.id.split("-")[0])
			alert("[updateType 테스트] [1] : " + evt.target.parentElement.id.split("-")[1])
			alert("[updateType 테스트] [2] : " + evt.target.parentElement.id.split("-")[2])*/

			//alert(id)
			updateType(id);
		}
	});
});

function updateList(todo, nowList, nextList) {
/*	console.log("todo : " + todo);
	console.log("beforeList : " + beforeList);
	console.log("afterList : " + afterList);*/
}
