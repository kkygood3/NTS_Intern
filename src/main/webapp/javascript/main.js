var todoListContainer = document.querySelector(".todo_list_container");
todoListContainer.addEventListener("click", function (event) {
    if (event.target.className == "next_btn") {
        requestNextType(event.target.parentNode);
    }
}, false);

function requestNextType(node) {
    var id = node.dataset.todoId;
    var type = node.dataset.todoType;
    var request = new XMLHttpRequest();
	    	
    request.addEventListener("load", function() {
        if(this.status == 200){
            updateTodoCell(node);
        }else if(this.status == 400){
            alert(this.responseText);
        }else if(this.status == 500){
        	alert(this.responseText);
        }
    });
    var data = "id=" + encodeURIComponent(id) + "&type=" + encodeURIComponent(type);
    request.open("POST","/2019_1st_intern/todo-type");
    request.setRequestHeader("Content-type","application/x-www-form-urlencoded; charset=utf-8");
    request.send(data);
}

function updateTodoCell(node) {
    var nextType = getNextType(node.dataset.todoType);
    node.dataset.todoType = nextType;
    var column = document.getElementById("todo_list_type_"+ nextType.toLowerCase() + "_column");
    column.appendChild(node);

    if (node.dataset.todoType == "DONE") {
        var button = node.querySelector("button");
        button.hidden = true;
    }
}
function getNextType(currentType) {
    if (currentType == "TODO") {
        return "DOING";
    } else if(currentType == "DOING") {
        return "DONE";
    }else {
    	return "DONE";
    }
}