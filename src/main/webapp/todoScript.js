/**
 * todoScript Author : Jaewon Lee, lee.jaewon@nts-corp.com
 */

/*
 * @init(item_id,nextType) this function searches for buttons in todo element,
 * and adds event-listener to each
 */
function init() {

	var moveButtons = document.querySelectorAll(".content_move_button");

	moveButtons.forEach(function(button) {
		button.addEventListener("click", function() {
			var todo_item = button.parentNode.parentNode;
			var item_id = todo_item.id;
			var currentSection = todo_item.parentNode.id

			/*
			 * initially assume nextType is DOING Change Type if when it is the
			 * case from DOING to DONE
			 */

			var nextType = "DOING";
			if (currentSection === "DOING") {
				nextType = "DONE";
			}

			button.addEventListener("click", update(item_id, nextType));
		});
	});
}

/*
 * @update(item_id,nextType) this function sends XmlHttpRequest(abbr. xhr) to
 * server, and receives http Status for the result. Accordingly, this method
 * will move the todo element to next category Also Removes arrow button when
 * the TODO element reaches DONE Category
 */
function update(item_id, nextType) {
	var todo_info = {};
	todo_info.id = item_id;
	todo_info.type = nextType;

	var json = JSON.stringify(todo_info);

	var xhr = new XMLHttpRequest();
	xhr.open("PUT", "/jaewonlee/updateTodo", true);
	xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");

	xhr.onreadystatechange = function(aEvt) {
		console.log('DONE: ', xhr.status);
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				alert("Successfully Updated");
				var target = document.querySelector("#" + nextType);
				var element = document.getElementById(item_id);
				target.appendChild(element);
				if (nextType == "DONE") {
					element.getElementsByTagName("button")[0].remove();
				}
			} else {
				alert("Error");
			}
		}
	};
	xhr.send(json);
}