function updateType(id) {
	var todo = document.querySelector("#"+id);
	var type = todo.parentElement.id.split("-")[1];
	//console.log(type);
	
	var xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.addEventListener("load", function() {
		//console.log(this.responseText);
		
		var afterType;
		if (type === "TODO") {
			afterType = "DOING";
		} else if (type === "DOING") {
			afterType = "DONE";
		}

		if (type === "DOING") {
			var updateButton = todo.querySelector(".updateButton");
			todo.remove(updateButton);
		}
		
		var beforeList = todo.parentElement;
		var afterList = document.querySelector("#ul-" + afterType);
		updateList(todo, beforeList, afterList);
		
	});
	//console.log(type);
	xmlHttpRequest.open("PUT", "/todoUpdate?id=" + id.split("-")[2] + "&type=" + type);
	xmlHttpRequest.send();
}

document.addEventListener("DOMContentLoaded", function(){
	var todoSection = document.querySelector("#section-content-main");
	//alert("DEBUG : " + todoSection)
	todoSection.addEventListener("click", function(evt){
		//alert("DEBUG : Click")
		if(evt.target.className === "updateButton"){
			var id = evt.target.parentNode.parentElement.id;
			
			/*alert("[updateType 테스트] id : " + evt.target.parentNode.parentElement.id);
			alert("[updateType 테스트] [0] : " + evt.target.parentElement.id.split("-")[0])
			alert("[updateType 테스트] [1] : " + evt.target.parentElement.id.split("-")[1])
			alert("[updateType 테스트] [2] : " + evt.target.parentElement.id.split("-")[2])*/

			//alert(id)
			updateType(id);
		}
	});
});

function updateList(todo, beforeList, afterList) {
	var afterListChild = afterList.getElementsByTagName('li')[1];
/*	alert(todo.getAttribute( 'data-regdate' ));
	alert(todo.firstElementChild.getAttribute( 'data-regdate' ));
	alert(afterList.getAttribute( 'data-regdate' ));
	alert(afterListChild.getAttribute( 'data-regdate' ));*/
	
/*	if (todo.id !== null) console.log("1. todo.id : " + todo.id); // li-content-35
	if (todo.firstElementChild !== null) console.log("2. todo.firstElementChild.id : " + todo.firstElementChild.id); // undefined
	if (afterList !== null) console.log("3. afterList.id : " + afterList.id); // ul-DOING
	if (afterListChild !== null) console.log("4. afterListChild.id : " + afterListChild.id); // li-content-33
	
	if (todo.getAttribute( 'data-regdate' ) !== null) console.log("todo.getAttribute( 'data-regdate' ) : " + todo.getAttribute( 'data-regdate' ));
	if (afterListChild.getAttribute( 'data-regdate' ) !== null) console.log("afterListChild.getAttribute( 'data-regdate' ) : " + afterListChild.getAttribute( 'data-regdate' ));
	*/
	
	//if (afterListChild == undefined) alert("undefined");
/*	while ( afterListChild !== null && afterListChild.getAttribute( 'data-regdate' ) < todo.getAttribute( 'data-regdate' ) ) {
		afterListChild = afterListChild.nextElementSibling;
	}
	
	beforeList.removeChild(todo);
	afterList.insertBefore(todo, afterListChild);*/
	
	if (afterListChild == undefined) {
	} else {
		while ( afterListChild !== null && afterListChild.getAttribute( 'data-regdate' ) < todo.getAttribute( 'data-regdate' ) ) {
			afterListChild = afterListChild.nextElementSibling;
		}
	}
	console.log("todo.id : " + todo.id);
	beforeList.removeChild(todo);
	afterList.insertBefore(todo, afterListChild);
}
