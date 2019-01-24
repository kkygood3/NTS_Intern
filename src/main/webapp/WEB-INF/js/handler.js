function addEvents() {
	var buttons = document.querySelectorAll("button");
	
	for (var i = 0; i < buttons.length; i++) {
		buttons[i].addEventListener("click", sendRequest);
	}
	
}

function sendRequest(event) {
	var id = event.target.parentElement.getAttribute("id");
	var type = event.target.parentElement.parentElement.getAttribute("id").toUpperCase().toString();

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/todo-type", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

	xhr.onreadystatechange = function() {
		if (xhr.response === "success") {
			shift(event, type)
		}
	}

	xhr.send("id=" + id + "&type=" + type);
}

function shift(event, type) {
	var ul;
	var li;
	var clickedElement = event.target;
	
	if (type === "TODO") {
		ul = "#doing"
		clickedElement = clickedElement.parentElement;
		
	} else if (type === "DOING") {
		ul = "#done"
		var temp = clickedElement.parentElement;
		temp.removeChild(clickedElement);
		clickedElement = temp;
	}
	
	li = document.querySelectorAll(ul + " .todo_list_block");
	ul = document.querySelector(ul);
	
	for (var i = 1, len = li.length; i < len; i++) {
		if (clickedElement.getAttribute("id") < li[i].getAttribute("id")) {
			ul.insertBefore(clickedElement, li[i]);
			return;
		}
	}

	ul.appendChild(clickedElement);
}

addEvents();