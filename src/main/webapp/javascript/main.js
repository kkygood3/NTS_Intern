function removeTodoCell(id, type) {
    var column = document.getElementById("todo_list_type_"+ type.toLowerCase() + "_column");
    var cells = column.getElementsByClassName("todo_cell");
    for(var i = 0; i < cells.length; i++){
        var cell = cells[i];
        if(cell.dataset.todoId == id && cell.dataset.todoType == type){
            var title = cell.querySelector("h1").innerHTML;
            var content = cell.querySelector("h5").innerHTML;
            column.removeChild(cell);
            return {
                id: id,
                type: type,
                title: title,
                content: content
            }
        }
    }
}
function getNextType(currentType) {
    if(currentType == "TODO"){
        return "DOING"
    }else if(currentType == "DOING"){
        return "DONE"
    }
}
function moveTodoCell(id, type, title, content) {
    var nextType = getNextType(type);
    var column = document.getElementById("todo_list_type_"+ nextType.toLowerCase() + "_column");
    var newCell = document.createElement("li");
    newCell.setAttribute("class", "todo_cell todo_content");
    newCell.setAttribute("data-todo-type", nextType);
    newCell.setAttribute("data-todo-id", id);
    
    var titleElement = document.createElement("h1");
    titleElement.innerHTML = title;

    var contentElement = document.createElement("h5");
    contentElement.innerHTML = content;

    newCell.appendChild(titleElement);
    newCell.appendChild(contentElement);

    if(nextType != "DONE"){
        var nextTypeButton = document.createElement("button");
        nextTypeButton.setAttribute("class", "next_btn");
        nextTypeButton.innerHTML = "&rarr;";
        newCell.appendChild(nextTypeButton);
    }
    addClickListener(newCell);
    column.appendChild(newCell);
}

function addClickListener(todoCell){
	var cellData = {
	        id: todoCell.dataset.todoId,
	        type: todoCell.dataset.todoType
	    };

	    var nextTypeButton = todoCell.querySelector("button");
	    if(nextTypeButton == undefined){
	    	return;
	    }
	    nextTypeButton.addEventListener("click", function() {
	    	var id = this.id;
	    	var type = this.type;
	    	var request = new XMLHttpRequest();
	    	
	    	request.addEventListener("load", function() {
	    		if(this.status == 200){
                    var removeTodoResult = removeTodoCell(id, type);
                    moveTodoCell(removeTodoResult.id, removeTodoResult.type, removeTodoResult.title, removeTodoResult.content);
	    		}else if(this.status == 400){
	    			alert(this.responseText);
	    		}
	    	});
	    	
	    	var data = "id=" + encodeURIComponent(id) + "&type=" + encodeURIComponent(type);
	    	request.open("POST","http://localhost:8080/2019_1st_intern/todotype");
	    	request.setRequestHeader("Content-type","application/x-www-form-urlencoded; charset=utf-8");
	    	request.send(data);
	    }.bind(cellData), false);
};

var todoCells = document.getElementsByClassName("todo_content");

for(var i = 0; i < todoCells.length; i++){
    addClickListener(todoCells[i]);
}