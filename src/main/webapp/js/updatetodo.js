function changeTodoType(id) {

	var todo = document.querySelector("#todo_item_" + id);
	var type = todo.parentElement.id.split("_")[0];
	var changeType;
	if (type === "todo") {
		changeType = "doing";
	} else if (type === "doing") {
		changeType = "done";
	}

	var xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.addEventListener("load", function() {
		if (this.status == 200) {

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

	xmlHttpRequest.open("PUT", "/todo/" + id + "/" + changeType.toUpperCase());
	xmlHttpRequest.send();

}

function sortInsert(nowList, nextList, todo) {
	var nextListChild = nextList.firstElementChild;
	while (nextListChild !== null
			&& nextListChild.dataset.datetime > todo.dataset.datetime) {
		nextListChild = nextListChild.nextElementSibling;
	}

	nowList.removeChild(todo);
	nextList.insertBefore(todo, nextListChild);
}