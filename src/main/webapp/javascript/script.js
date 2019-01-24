const TODO = "TODO";
const DOING = "DOING";
const DONE = "DONE";

function buttonToAjax() {

	let buttonParent = event.srcElement.parentElement; // button의 parent
	let id = buttonParent.id;
	let type = buttonParent.children[2].value;

	let httpRequest = new XMLHttpRequest();
	httpRequest.addEventListener("load", function() {
		let result = this.responseText;

		if (result == "success") {
			moveNext(type, buttonParent);
		} else {
			alert("ERROR");
		}

	});
	httpRequest.open("PUT", "/changeType?id=" + id + "&type=" + type);
	httpRequest.send();
}

function changeType(type) {
	if (type === TODO) {
		type = DOING;
	} else {
		type = DONE;
	}
	return type;
}

function moveNext(type, buttonParent) {

	let nextType = changeType(type);
	let nextAside = document.getElementById(nextType.toLowerCase());
	let nextAsideChildNodesLength = nextAside.childNodes.length;

	buttonParent.children[2].value = nextType;

	if (nextType === DONE) {
		// remove button
		buttonParent.removeChild(buttonParent.childNodes[7]);
	}

	if (nextAsideChildNodesLength > 0) {
		nextAside.insertBefore(buttonParent, nextAside.childNodes[nextAsideChildNodesLength - 1].nextSibling);
	} else {
		nextAside.insertBefore(buttonParent, nextAside.children[0]);
	}

}