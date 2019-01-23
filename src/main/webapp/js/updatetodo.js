function changeTodoType(id) {

	var todo = document.querySelector("#todo_item_" + id);
	var type = todo.parentElement.id.split("_")[0];

	var xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.addEventListener("load", function() {
		if (this.status == 200) {

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
			
		} else if (this.status == 404) {

		}
	});
	console.log(type);
	xmlHttpRequest.open("PUT", "/todo/" + id + "/" + type.toUpperCase());
	xmlHttpRequest.send();

}

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