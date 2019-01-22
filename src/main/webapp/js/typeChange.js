var todos = document.querySelector('.todos');
todos.addEventListener('click', function(event) {
	if(event.target.className==="go_next"){
		updateType(event.target.parentNode);
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

function updateType(todo) {
	var id = todo.dataset.todo_id;
	var type = todo.dataset.todo_type;
	var types = ["TODO", "DOING", "DONE"];
	var title = todo.getElementsByClassName("title")[0].textContent;
	var name = todo.getElementsByClassName("name")[0].textContent;
	var sequence = todo.getElementsByClassName("sequence")[0].textContent;
	var regdate = todo.getElementsByClassName("regdate")[0].textContent;
	
	var data;
	var targetElement;
	
	httpRequest = getXMLHttpRequest();
	
	if(httpRequest!=null){
		httpRequest.onreadystatechange = function() {  
		    if (httpRequest.readyState == 4 && httpRequest.status == 200) {
		    	var changedType = types[types.indexOf(type)+1];
		    	var targetElement = document.getElementById(changedType.toLowerCase());
		    	
		    	targetElement.appendChild(todo);
		    	todo.dataset.todo_type = changedType;
		    	
		    	if(changedType==="DONE"){
		    		todo.removeChild(todo.getElementsByClassName("go_next")[0]);
		    	}
		    }
		}
		
		httpRequest.open("POST","nextStep",true);
		httpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		data = "id=" + id + "&type=" + type;
		httpRequest.send(data);
	}
}