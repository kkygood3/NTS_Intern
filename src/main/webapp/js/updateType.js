/**
 * 버튼을 누를 시 리스트를 이동시키고, 리스트의 type이 DONE일 경우 버튼을 삭제합니다.
 * 이때 updateList 메서드를 통해 리스트를 이동시키고, removeButton 메서드를 통해 버튼을 삭제합니다.
 */
function updateType(id) {
	var todo = document.querySelector("#"+id);
	var type = todo.parentElement.id.split("-")[1];
	
	var xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.addEventListener("load", function() {
		var nextType;
		
		if (type === "TODO") {
			nextType = "DOING";
		} else if (type === "DOING") {
			nextType = "DONE";
		}
		
		removeButton(todo, nextType);
		
		var todoList = todo.parentElement;
		var nextTodoList = document.querySelector("#ul-" + nextType);
		
		updateList(todo, todoList, nextTodoList);
				
	});
	xmlHttpRequest.open("PUT", "/todoUpdate?id=" + id.split("-")[2] + "&type=" + type);
	xmlHttpRequest.send();
}

/**
 * TodoDto 객체의 type 속성값이 DOING에서 DONE으로 바뀔 경우 이동버튼을 삭제합니다.
 */
function removeButton(todo, nextType) {
	if (nextType === "DONE") {
		var updateButton = todo.querySelector(".updateButton");
		updateButton.parentNode.removeChild(updateButton);
	}
}

/**
 * 버튼을 누르는 이벤트가 발생 시 리스트를 이동시킵니다. (TODO에서 DOING으로, DOING에서 DONE으로)
 * 이때 이동할 목록이 리스트를 가지고 있으면 리스트들의 data-value 값인 날짜를 비교해 빠른 날짜가 위로 오게 삽입합니다.
 */
function updateList(todo, todoList, nextTodoList) {
	if (nextTodoList.getElementsByTagName('li')[1] == undefined) {
		var nextTodoListTitle = nextTodoList.getElementsByTagName('li')[0];
		
		nextTodoList.insertBefore(todo, nextTodoListTitle.nextSibling);
	} else {
		var nextTodoListChild = nextTodoList.getElementsByTagName('li')[1];
		
		while ( nextTodoListChild !== null && nextTodoListChild.getAttribute( 'data-regdate' ) <= todo.getAttribute( 'data-regdate' ) ) {
			nextTodoListChild = nextTodoListChild.nextElementSibling;
		}
		nextTodoList.insertBefore(todo, nextTodoListChild);
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