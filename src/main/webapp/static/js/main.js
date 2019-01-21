
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
function updateItemToNext(id,type,changingElement) {
	var xhr = new XMLHttpRequest();
	
	xhr.open('POST', '/changeTodoType', true);
	xhr.onreadystatechange = function() {
		
		if (xhr.readyState == 4 && xhr.status == 200) {
			if(type == 'TODO'){
				
				var doingElements = document.querySelectorAll('#doing li');
				var changeElementRegDate = changingElement.getAttribute('data-date');
				var isChanged = false;
				
				doingElements.forEach(function(element){
					var regDate = element.getAttribute('data-date');
					
					console.log('regDate : '+regDate);
					console.log('changedDate :' + changeElementRegDate);
					if(regDate < changeElementRegDate){
						// 여기서 추가
						console.log(changingElement.parentElement);
						changingElement.parentElement.removeChild(changingElement);
						alert('이전껄로');
						isChanged = true;
						return true;
					}
				});
				// 여기서 추가
				//if(isChanged == false) doingElements.appendChild();
				
			}else if(type == 'DOING'){
				alert("DOING TO DONE");
			}
			
		}
		
	}
	
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	
	var xhrData = "id="+id+"&type="+type;
	xhr.send(xhrData);
}


/**
 * @desc next btn 이벤트리스너 
 * @returns
 */
function nextBtnEvent(){
	var nextButtons = document.querySelectorAll('.next_btn');
	
	nextButtons.forEach(function(btn){
		btn.addEventListener('click',function(){
			
			var li = this.parentNode.parentNode;
			
			
			var type = li.parentNode.id.toUpperCase();
			var id = li.getAttribute('data-id');
			updateItemToNext(id,type,li);
		});
	});
}

nextBtnEvent();

