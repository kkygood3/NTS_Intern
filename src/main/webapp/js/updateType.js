/**
 * 버튼을 누를 시 리스트를 이동시키고, 리스트의 type이 DONE일 경우 버튼을 삭제합니다.
 * 이때 updateList 메서드를 통해 리스트를 이동시키고, removeButton 메서드를 통해 버튼을 삭제합니다.
 */
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

/**
 * TodoDto 객체의 type 속성값이 DOING에서 DONE으로 바뀔 경우 이동버튼을 삭제합니다.
 */
function removeButton(todo, afterType) {
	if (afterType === "DONE") {
		var updateButton = todo.querySelector(".updateButton");
		updateButton.parentNode.removeChild(updateButton);
	}
}

/**
 * 버튼을 누르는 이벤트가 발생 시 리스트를 이동시킵니다. (TODO에서 DOING으로, DOING에서 DONE으로)
 * 이때 이동할 목록이 리스트를 가지고 있으면 리스트들의 data-value 값인 날짜를 비교해 빠른 날짜가 위로 오게 삽입합니다.
 */
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