function changeTodoType(id) {
	const HTTP_STATUS_OK = 200;
	const HTTP_STATUS_NOT_FOUND;
	var todo = document.querySelector("#todo_item_" + id);
	var type = todo.parentElement.id.split("_")[0];

	var xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.addEventListener("load", function() {
		if (this.status === HTTP_STATUS_OK) {

			var changeType;
			if (type === "todo") {
				changeType = "doing";
			} else if (type === "doing") {
				changeType = "done";
			}
			
			if (changeType === "done") {
				var moveBtn = todo.querySelector(".move_btn");
				todo.removeChild(moveBtn);
			}
			var nowList = todo.parentElement;
			var nextList = document.querySelector("#" + changeType + "_list");
			
			sortInsert(nowList, nextList, todo);
			
		} else if (this.status === HTTP_STATUS_NOT_FOUND) {
			alert("type change fail");
		}
	});
	console.log(type);
	xmlHttpRequest.open("PUT", "/todo/" + id + "/" + type.toUpperCase());
	xmlHttpRequest.send();

}

/*
 * list를 날짜기준으로 출력하므로, 이동시 맨마지막에 넣으면 새로고침했을때의 화면과 다를 수 있기때문에 사용자 UX를 고려하여, 정렬해서 삽입하도록 변경
 */
function sortInsert(nowList, nextList, todo) {
	var nextListChild = nextList.firstElementChild;
	while (nextListChild !== null
			&& nextListChild.dataset.datetime < todo.dataset.datetime) {
		nextListChild = nextListChild.nextElementSibling;
	}

	nowList.removeChild(todo);
	nextList.insertBefore(todo, nextListChild);
}

document.addEventListener("DOMContentLoaded", function(){
	var todoSection = document.querySelector("#todo_section");
	todoSection.addEventListener("click", function(evt){
		if(evt.target.className === "move_btn"){
			var id = evt.target.parentElement.id.split("_")[2];
			changeTodoType(id);
		}
	});
});