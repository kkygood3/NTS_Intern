function changeTodoType(id, type) {
	var xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.addEventListener("load", function() {
		if(this.status == 200){
			var todoItem = document.querySelector("#todo_list_" + id);
			var list = todoItem.parentElement;
			var nextList = list.
			list.removeChild(todoItem);
			
		}
	});
	
	xmlHttpRequest.open("PUT", "/todo/" + id + "/" + type);
	xmlHttpRequest.send();
	
}