var todos = document.querySelector('.todos');
todos.addEventListener('click', function(event) {
	if(event.target.className==="go_next"){
		updateType(event.target.parentNode);
	}
});

function updateType(todo) {
	var id = todo.dataset.todoId;
	var type = todo.dataset.todoType;
	var types = ["TODO", "DOING", "DONE"];
	
	if (window.XMLHttpRequest) {
		var httpRequest =  new XMLHttpRequest();
		
		httpRequest.onreadystatechange = function() {  
		    if (httpRequest.readyState == 4 && httpRequest.status == 200) {
		    	var changedType = types[types.indexOf(type)+1];
		    	var targetElement = document.getElementById(changedType.toLowerCase());
		    	
		    	targetElement.appendChild(todo);
		    	todo.dataset.todoType = changedType;
		    	
		    	if(changedType==="DONE"){
		    		todo.removeChild(todo.getElementsByClassName("go_next")[0]);
		    	}
		    }
		}
		
		httpRequest.open("POST","nextStep",true);
		httpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		var data = "id=" + id + "&type=" + type;
		httpRequest.send(data);
	}
}