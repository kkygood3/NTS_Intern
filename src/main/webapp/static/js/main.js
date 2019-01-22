/**
 * @desc 다음요소로 전달 (날짜 정렬)
 * @params changingElement (바뀔 요소)
 * @parmas nextElementUl (changingElement가 이동할 요소 ul)
 * @returns
 */
function changeToNext(changingElement, nextElementUl) {

	var changingLi = changingElement.li;
	var nextElements = document.querySelectorAll(nextElementUl + ' li');
	var changeElementRegDate = changingLi.getAttribute('data-date');

	// 날짜를 비교해서 들어갈 위치 찾은후 삽입
	for (var i = 0, nextElementsLength = nextElements.length; i < nextElementsLength; i++) {
		var nextElementRegDate = nextElements[i].getAttribute('data-date');

		if (nextElementRegDate < changeElementRegDate) {
			changingLi.parentNode.removeChild(changingLi);

			document.querySelector(nextElementUl).insertBefore(changingLi,nextElements[i]);
			return;
		}
	}

	document.querySelector(nextElementUl).appendChild(changingLi);
}

/**
 * @desc next버튼을 누를시에 DB 변경
 * @param changingElement (바뀔 요소 -> {id,type,li} )
 */
function updateItemToNext(changingElement) {
	var xhr = new XMLHttpRequest();

	xhr.open('POST', '/changeTodoType', true);
	xhr.onreadystatechange = function() {

		if (xhr.readyState === 4 && xhr.status === 200
				&& xhr.response === 'success') {
			if (changingElement.type == 'TODO') {
				changeToNext(changingElement, '#doing');
			} else if (changingElement.type == 'DOING') {
				changingElement.li.querySelector('.next_btn').remove();
				changeToNext(changingElement, '#done');
			}
		}
	}

	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

	var xhrData = "id=" + changingElement.id + "&type=" + changingElement.type;
	xhr.send(xhrData);
}

/**
 * @desc next btn 이벤트리스너
 * @returns
 */	
function nextBtnEvent() {
	var nextButtons = document.querySelectorAll('.next_btn');

	nextButtons.forEach(function(btn) {
		btn.addEventListener('click', function() {

			var li = this.parentNode.parentNode;
			var type = li.parentNode.id.toUpperCase(); // TODO , DOING , DONE
			var id = li.getAttribute('data-id');

			var changingElement = {	
				id : id,
				type : type,
				li : li,
			};
			
			updateItemToNext(changingElement);
		});
	});
}

document.addEventListener("DOMContentLoaded", function(){
	nextBtnEvent();
});
