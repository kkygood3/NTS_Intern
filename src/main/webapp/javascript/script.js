/**
 * change_type() : 타입 변경 함수(Ajax) 
 * div(article) 이동 함수
 * @author yongjoon.Park
 */
const TODO = "TODO";
const DOING = "DOING";
const DONE = "DONE";


function change_type(){
	let parent_element = event.srcElement.parentElement;	// button의 parent
	let id = parent_element.id;
	let type = parent_element.children[2].value;
	
	// Ajax
	let httpRequest = new XMLHttpRequest();
	httpRequest.addEventListener("load", function(){
		let result = this.responseText;
		
		if(result == "success"){
			move_next(type, parent_element);
		}else{
			alert("ERROR");
		}
		
	});
	httpRequest.open("PUT", "/changeType?id=" + id + "&type=" + type);
	httpRequest.send();
}

function move_next(type, parent_element){
	if(type == TODO){
		let doing_aside = document.getElementById("doing");
		let doing_aside_childNodes_length = doing_aside.childNodes.length;
		
		// change type
		parent_element.children[2].value = DOING
		
		// move next aside
		if(doing_aside_childNodes_length > 0){
			doing_aside.insertBefore(parent_element, doing_aside.childNodes[doing_aside_childNodes_length-1].nextSibling);
		}else{
			doing_aside.insertBefore(parent_element, doing_aside.children[0]);
		}
		
	}else if(type == DOING){
		let done_aside = document.getElementById("done");
		let done_aside_childNodes_length = done_aside.childNodes.length;
		
		// change type
		parent_element.children[2].value = DONE
		
		// remove button
		parent_element.removeChild(parent_element.childNodes[7]);
		
		// move next aside
		if(done_aside_childNodes_length > 0){
			done_aside.insertBefore(parent_element, done_aside.childNodes[done_aside_childNodes_length - 1].nextSibling);
		}else{
			done_aside.insertBefore(parent_element, done_aside.children[0]);
		}
	}
}