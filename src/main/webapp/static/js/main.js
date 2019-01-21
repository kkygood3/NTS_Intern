
/**
 * 
 * @returns
 */
function changeToNext(){
	
}
/**
 * @desc next버튼을 누를시에 DB 변경
 * @param id
 * @param type
 */
function updateItemToNext(id,type,changingDom) {
	var xhr = new XMLHttpRequest();
	
	xhr.open('POST', '/main', true);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			
			if(type == 'TODO'){
				changingDom.
			}else if(type == 'DOING'){
				
			}
			
		}
	}
	
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	
	var xhrData = "id="+id+"&type="+type;
	xhr.send(xhrData);
}

var dom = document;
var nextButtons = dom.querySelectorAll('.next_btn');

/**
 * Event
 */
nextButtons.forEach(function(btn){
	btn.addEventListener('click',function(){
		
		var li = this.parentNode.parentNode;
		
		var type = li.parentNode.id.toUpperCase();
		var id = li.id;
		
		updateItemToNext(id,type,this);
	});
})
