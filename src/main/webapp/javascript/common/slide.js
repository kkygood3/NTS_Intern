// TODO: 함수를 두개만들지 if문으로 처리할지...
function makeSlide(ul) {
	const interval = window.setInterval(()=> {
		if (readyToSlide){
			slideLeft(ul);
		}
	}, 5000);
}

function slideLeft(ul) {
	//위치를 -414로 만든다(트랜지션)
	//제일뒤로 li를 하나 옮긴다
	//위치를 0으로 만든다
	addTransitionAndmoveUl(ul, -414);
	window.setTimeout(()=> {
		removeTransitionAndmoveUl(ul, 0);
		moveLiFirstToLast(ul);
	}, 1000);
}

function slideRight(ul) {
	removeTransitionAndmoveUl(ul, -414);
	moveLiLastToFirst(ul);
	window.setTimeout(()=> {
		addTransitionAndmoveUl(ul, 0);
	}, 1);
}

function moveLiFirstToLast(ul) {
	var firstLi = ul.firstElementChild;
	ul.appendChild(firstLi);

}
function moveLiLastToFirst(ul) {
	var lastLi = ul.lastElementChild;
	ul.insertBefore(lastLi, ul.firstElementChild);
}
function removeTransitionAndmoveUl(ul, position) {
	ul.classList.remove("transition");
	ul.style.left = position + "px";
}
function addTransitionAndmoveUl(ul, position) {
	ul.classList.add("transition");
	ul.style.left = position + "px";
}
