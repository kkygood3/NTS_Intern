function changeTodoType(id) {

	var todo = document.querySelector("#todo_item_" + id);
	var type = todo.parentElement.id.split("_")[0];
	var changeType;
	if(type === "todo"){
		changeType = "doing";
	}else if(type === "doing"){
		changeType = "done";
	}
	
	var xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.addEventListener("load", function() {
		if(this.status == 200){
			
			if(changeType === "done"){
				var moveBtn = todo.querySelector(".move");
				todo.removeChild(moveBtn);
			}
			var preList = todo.parentElement;
			var nextList = document.querySelector("#" + changeType + "_list");
			var child = nextList.firstElementChild;
			while(child !== null && child.dataset.datetime > todo.dataset.datetime){
				child = child.nextElementSibling;
			}
			console.log(child);
			preList.removeChild(todo);
			nextList.insertBefore(todo, child);
			
		}
	});
	
	xmlHttpRequest.open("PUT", "/todo/" + id + "/" + changeType.toUpperCase());
	xmlHttpRequest.send();
	
}