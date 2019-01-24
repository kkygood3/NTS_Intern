
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

/**
 * todoScript.js implementation 
 * Author : Jaewon Lee, lee.jaewon@nts-corp.com
 */

/**
 * This function searches for buttons in todo element, and adds event-listener
 * to each. Collects variable by traversing html file structure to parent node.
 * 
 * @init()
 */
var init = () => {

	var todoItem = document.querySelectorAll(".todoItem");

	todoItem.forEach((item) => {
		var id = item.dataset.id;
		var button = item.querySelector("button.content_move_button");
		if(button!=null){
			button.addEventListener("click", ()=>{
				/**
				 * item.parentNode.dataset.section is the target section; this
				 * is used as a variable, since declaration of the variable of
				 * this element will make element to stick with current variable
				 */
				update(id,item.parentNode.dataset.section) 
			});
		}
		
	});
}

/**
 * This function sends XmlHttpRequest(abbr. xhr) to server, and receives http
 * Status for the result. Accordingly, this method will move the todo element to
 * next category. Also Removes arrow button when the Todo element reaches DONE
 * Category
 * 
 * @update(item_id,nextType)
 */
var update = (itemId, currentSection) => {
	var todoInfo = {};
	todoInfo.id = itemId;
	todoInfo.type = currentSection;
	
	var json = JSON.stringify(todoInfo);
	
	var xhr = new XMLHttpRequest();
	xhr.open("PUT", "/jaewonlee/updatetodo", true);
	xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
	
	xhr.onreadystatechange = function(aEvt) {
		 if (xhr.readyState === XMLHttpRequest.DONE) {
			 if (xhr.status === 200) {
				 
				alert("Successfully Updated");
				
				var nextType = "DOING";
				console.log(currentSection);
				if (currentSection == "DOING") {
					nextType = "DONE"
				}
				
				/**
				 * if there is any element that has higher id which means
				 * generated after the element we are dealing with. Therefore,
				 * if there is one, we are inserting before the element. if
				 * findHtmlInsertSpot = -1, simply append in the section var
				 * toBeMoved = html Dom Element Node, which refers the item to
				 * be moved to left var targetItemId = html Dom Element Node,
				 * which refers the item that has to be under the Node toBeMoved
				 * var targetSection = refers html DOME Element Node in ul tag
				 * of 3 sections, todo, doing, done.
				 */
				var toBeMoved = document.querySelector("[data-id = '"+itemId+"']");
				var targetItemId = findHtmlInsertSpot(nextType, itemId);
				var targetSection = document.querySelector("ul[data-section ='"+nextType+"']");
				
				if (targetSection == -1) {
					targetSection.appendChild(toBeMoved);
				} else {
					targetSection.insertBefore(toBeMoved,
							document.querySelector("[data-id = '"+targetItemId+"']"));
				}
				
				// if todo item is moving to DONE, remove button
				if (currentSection == "DOING") {
					toBeMoved.getElementsByTagName("button")[0].remove();
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
var findHtmlInsertSpot = (type, currentId) => {
	var htmlList = document.querySelectorAll("ul[data-section ='"+type+"'] > li");
	console.log(htmlList);
	for (var index =0; index<htmlList.length; index++) {
		if (htmlList[index].dataset.id > currentId) {
			return htmlList[index].dataset.id;
		}
	}
	return -1;
	
}
