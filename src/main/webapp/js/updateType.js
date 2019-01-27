function updateType(id) {
	var todo = document.querySelector("#"+id);
	var type = todo.parentElement.id.split("-")[1];
	
	var xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.addEventListener("load", function() {
		var afterType;
		
		if (type === "TODO") {
			afterType = "DOING";
		} else if (type === "DOING") {
			afterType = "DONE";
		}
		
		removeButton(todo, afterType);
		
		var beforeList = todo.parentElement;
		var afterList = document.querySelector("#ul-" + afterType);
		
		updateList(todo, beforeList, afterList);
		
	});
	xmlHttpRequest.open("PUT", "/todoUpdate?id=" + id.split("-")[2] + "&type=" + type);
	xmlHttpRequest.send();
}

function removeButton(todo, afterType) {
	if (afterType === "DONE") {
		var updateButton = todo.querySelector(".updateButton");
		updateButton.parentNode.removeChild(updateButton);
	}
}

function updateList(todo, beforeList, afterList) {
	if (afterList.getElementsByTagName('li')[1] == undefined) {
		var afterListTitle = afterList.getElementsByTagName('li')[0];
		
		afterList.insertBefore(todo, afterListTitle.nextSibling);
	} else {
		var afterListChild = afterList.getElementsByTagName('li')[1];
		
		while ( afterListChild !== null && afterListChild.getAttribute( 'data-regdate' ) <= todo.getAttribute( 'data-regdate' ) ) {
			afterListChild = afterListChild.nextElementSibling;
		}
		afterList.insertBefore(todo, afterListChild);
	}
}

document.addEventListener("DOMContentLoaded", function(){
	var todoSection = document.querySelector("#content-main");
	
	todoSection.addEventListener("click", function(evt){
		if(evt.target.className === "updateButton"){
			var id = evt.target.parentNode.parentElement.id;
			
			updateType(id);
		}
	});
});