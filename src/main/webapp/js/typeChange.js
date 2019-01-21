var todos = document.querySelector('.todos');
todos.addEventListener('click', function(event) {
	if(event.target.textContent==="→"){
		submitIdAndType(event.target.parentNode);
	}
});

var httpRequest = null;
function getXMLHttpRequest() {
	if (window.ActiveXObject) {
		try {
			return new ActiveXObject("Msxml2.XMLHTTP");
		} catch(e) {
			try {
				return new ActiveXObject("Microsoft.XMLHTTP");
			} catch(e1) {
				return null;
			}
		}
	}
	else if (window.XMLHttpRequest) {
		return new XMLHttpRequest();
	}
	else {
		return null;
	}
}

function submitIdAndType(todo) {
	var id = todo.dataset.todo_id;
	var type = todo.dataset.todo_type;
	var title = todo.getElementsByClassName("title")[0].textContent;
	var name = todo.getElementsByClassName("name")[0].textContent;
	var sequence = todo.getElementsByClassName("sequence")[0].textContent;
	var regdate = todo.getElementsByClassName("regdate")[0].textContent;
	
	var data;
	var targetElement;
	
	httpRequest = getXMLHttpRequest();
	
	httpRequest.onreadystatechange = function() {  
	    if (httpRequest.readyState == 4 && httpRequest.status == 200) {
	    	todo.remove();

	    	if(type==="TODO"){
	    		moveTodo(id, "DOING", title, regdate, name, sequence);
	    	}
	    	else if(type==="DOING"){
	    		moveTodo(id, "DONE", title, regdate, name, sequence);
	    	}
	    }
	}
	
	httpRequest.open("POST","nextStep",true);
	httpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	data = "id=" + id + "&type=" + type;
	httpRequest.send(data);
}

function moveTodo(id, type, title, regdate, name, sequence){
	var targetElement = document.getElementById(type.toLowerCase());
	
	var movedTodo = document.createElement("li");
	movedTodo.setAttribute("data-todo_id", id);
	movedTodo.setAttribute("data-todo_type", type);
	
	var titleElement = document.createElement("p");
	titleElement.setAttribute("class", "title");
	titleElement.innerHTML = title;

	var regdateElement = document.createElement("p");
	regdateElement.setAttribute("class", "regdate");
	regdateElement.innerHTML = regdate;

	var nameElement = document.createElement("p");
	nameElement.setAttribute("class", "name");
	nameElement.innerHTML = name;
	
	var sequenceElement = document.createElement("p");
	sequenceElement.setAttribute("class", "sequence");
	sequenceElement.innerHTML = sequence;
	
	movedTodo.append(titleElement);
	movedTodo.append(regdateElement);
	movedTodo.append(nameElement);
	movedTodo.append(sequenceElement);
	
	if(type==="DOING"){
		var nextElement = document.createElement("button");
		nextElement.setAttribute("class", "go_next");
		nextElement.innerHTML = "→";
		
		movedTodo.append(nextElement);
	}
	
	targetElement.append(movedTodo);
}