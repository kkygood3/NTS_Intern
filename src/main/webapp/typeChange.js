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
	var data;
	var targetElement;
	
	httpRequest = getXMLHttpRequest();
	
	httpRequest.onreadystatechange = function() {  
	    if (httpRequest.readyState == 4 && httpRequest.status == 200) {
	    	todo.remove();

	    	if(type==="TODO"){
	    		targetElement = document.getElementById("doing");
	    		targetElement.insertAdjacentHTML("beforeend", httpRequest.responseText);
	    	}
	    	else if(type==="DOING"){
	    		targetElement = document.getElementById("done");
	    		targetElement.insertAdjacentHTML("beforeend", httpRequest.responseText);
	    	}
	    }
	}
	
	httpRequest.open("POST","nextStep",true);
	httpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	data = "id=" + id + "&type=" + type;
	httpRequest.send(data);
}