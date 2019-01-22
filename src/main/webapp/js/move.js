function changeTodoType(id) {

	var todo = document.querySelector("#todo_item_" + id);
	var changeType = todo.dataset.nextType;
	var xmlHttpRequest = new XMLHttpRequest();
	
	xmlHttpRequest.addEventListener("load", function() {
		if(this.status == 200){
			
			if(changeType === 'doing'){
				todo.dataset.nextType = 'done'
			}else if(changeType === 'done'){
				todo.dataset.nextType = '';
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