const TODO = "TODO";
const DOING = "DOING";
const DONE = "DONE";

/**
 * 버튼의 DOM위치를 기반으로 id, type을 알아내어 DB와 연동(AJAX) 후 moveNext() 함수 실행
 */
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

/**
 * 입력받은 type을 다음 상태의 type으로 변경 후 return
 * ex)todo -> doing, doing -> done
 * @param 현재 상태의 type
 * @returns 다음 상태의 type
 */
function changeType(type) {
	if (type === TODO) {
		type = DOING;
	} else {
		type = DONE;
	}
	return type;
}

/**
 * 1개의 todo 다음 상태의 todo list로 이동
 * @author yongjoon.Park
 */
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

/**
 * form에 빈 값이 있는지 확인하는 function
 * @returns 1개라도 빈 값이 있다면 false를 반환
 */

function isValueEmpty() {
	let title = document.getElementById("title").value.trim();
	let name = document.getElementById("name").value.trim();
	let sequence = document.querySelector('input[name="sequence"]:checked').value;

	if (!title || !name || !sequence) {
		alert("양식을 확인하십시오.")
		return false;
	}
	return true;
}