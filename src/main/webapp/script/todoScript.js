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

	var todoItem = document.querySelectorAll(".todoItem");

	todoItem.forEach((item) => {
		var id = item.dataset.id;
		var section = item.parentNode.id;
		var button = item.querySelector("button.content_move_button");
		if(button!=null){
			button.addEventListener("click", ()=>update(id,section));
		}
		
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
function update(item_id, currentSection) {
	var todo_info = {};
	todo_info.id = item_id;
	todo_info.type = currentSection;

	var json = JSON.stringify(todo_info);

	var xhr = new XMLHttpRequest();
	xhr.open("PUT", "/jaewonlee/updateTodo", true);
	xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
	
	 xhr.onreadystatechange = function(aEvt) {
		 if (xhr.readyState === XMLHttpRequest.DONE) {
			 if (xhr.status === 200) {
				 
				alert("Successfully Updated");
				var nextType = "DOING";
				if (currentSection == "DOING") {
					nextType = "DONE"
				}
			
				/**
				 * if there is any element that has higher id which means
				 * generated after the element we are dealing with. Therefore,
				 * if there is one, we are inserting before the element. if
				 * findHtmlInsertSpot = -1, simply append in the section
				 */
				var element = document.querySelector("[data-id='"+item_id+"']");
				var target = findHtmlInsertSpot(nextType, item_id);
				if (target == -1) {
					document.querySelector("#" + nextType).appendChild(element);
				} else {
					document.querySelector("#" + nextType).insertBefore(element,
							document.querySelector("[data-id='"+target+"']"));
				}
			
				if (currentSection == "DOING") {
					element.getElementsByTagName("button")[0].remove();
				}
			 } else {
				 alert("Error");
			 }
		 }
	 };
	 xhr.send(json);
	
	
}

/**
 * 
 * @findHtmlInsertSpot(type,currentId)
 * @returns proper id in section to insert the element according to the id of
 *          element. If -1, there is no id found
 */
function findHtmlInsertSpot(type, currentId) {
	var htmlList = document.querySelectorAll("#" + type + ">li");
	for ( var index =0; index<htmlList.length;index++) {
		if (htmlList[index].dataset.id > currentId) {
			return htmlList[index].dataset.id;
			break;
		}
	}
	return -1;
	
}
