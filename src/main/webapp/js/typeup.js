var btns = document.getElementsByTagName('button');

for (var i = 0; i < btns.length; ++i) {
	btns[i].addEventListener('click', clickEvent);
}

function updateRequest(btn, type) {
	var oReq = new XMLHttpRequest();
	oReq.addEventListener("load", function(e) {
		if (e.target.response != "success") {
			alert('update error');
		}
	});
	oReq.open("GET", "update?id=" + btn.getAttribute("name") + "&type=" + type);
	oReq.send();
}

function clickEvent(event) {
	var btn = event.target;
	var type = btn.parentElement.parentElement.parentElement
			.getElementsByClassName('title')[0].innerText;
	var movedCard = btn.parentElement.parentElement;	
	
	if (type === 'TODO') {
		// TODO -> DOING
		var addDoing = document.getElementById('zone-doing');
		addDoing.appendChild(movedCard);
	} else {
		// DOING -> DONE
		var addDone = document.getElementById('zone-done');
		var par = btn.parentElement;
		par.removeChild(btn);

		addDone.appendChild(movedCard);

	}
	
	updateRequest(btn, type);

}