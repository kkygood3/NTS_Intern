/**
 * todoScript implementation 
 * Author : Jaewon Lee, lee.jaewon@nts-corp.com
 */

/**
 * @init() This function searches for buttons in todo element, and adds
 *         event-listener to each. Collects variable by traversing html file
 *         structure to parent node.
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

/**
 * @update(item_id,nextType)
 * 
 * This function sends XmlHttpRequest(abbr. xhr) to server, and receives http
 * Status for the result. Accordingly, this method will move the todo element to
 * next category. Also Removes arrow button when the Todo element reaches DONE
 * Category
 */
function update(item_id, nextType) {
	var id_extracted = item_id.split("_")[1];
	var todo_info = {};
	todo_info.id = id_extracted;
	todo_info.type = nextType;

	var json = JSON.stringify(todo_info);

	var xhr = new XMLHttpRequest();
	xhr.open("PUT", "/jaewonlee/updateTodo", true);
	xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");

	xhr.onreadystatechange = function(aEvt) {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				alert("Successfully Updated" + id_extracted);

				var target = document.querySelector("#" + nextType);
				var element = document.getElementById("todo_" + id_extracted);
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