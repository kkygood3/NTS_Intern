var submits = document.getElementsByClassName("submit");
var ids = document.getElementsByClassName("id");
var event = [];

for(var i = 0; i < submits.length; i++){
	event[i] = submitIdAndType.bind(undefined, ids[i]);
	submits[i].addEventListener("click", event[i]);
}

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

function submitIdAndType(id) {
	var type = id.nextSibling.nextSibling;
	var data;
	var targetElement;
	
	httpRequest = getXMLHttpRequest();
	
	httpRequest.onreadystatechange = function() {  
	    if (httpRequest.readyState == 4 && httpRequest.status == 200) {
	    	for(var i = 0; i < submits.length; i++){
	    		submits[i].removeEventListener("click", event[i]);
	    	}
	    	id.parentElement.remove();

	    	if(type.value==="TODO"){
	    		targetElement = document.getElementById("doing");
	    		targetElement.insertAdjacentHTML("beforeend", httpRequest.responseText);
	    	}
	    	else if(type.value==="DOING"){
	    		targetElement = document.getElementById("done");
	    		targetElement.insertAdjacentHTML("beforeend", httpRequest.responseText);
	    	}
	    	
	    	for(var i = 0; i < submits.length; i++){
	    		event[i] = submitIdAndType.bind(undefined, ids[i]);
	    		submits[i].addEventListener("click", event[i]);
	    	}
	    }
	}
	
	httpRequest.open("POST","nextStep",true);
	httpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	data = "id=" + id.value + "&type=" + type.value;
	httpRequest.send(data);
}