/**
 * 슬라이드 처리
 * @author 시윤
 *
 */

/**
 * ul안의 li들 무한슬라이드
 * @param ul
 */
function makeInfiniteSlide(ul) {
	const interval = window.setInterval(()=> {
		if (readyToSlide){
			slideLeft(ul);
		}
	}, 5000);
}

/**
 * ul안의 li들 왼쪽으로 한번 슬라이드
 * @param ul
 */
function slideLeft(ul) {
	addTransitionAndmoveUl(ul, -414);
	window.setTimeout(()=> {
		removeTransitionAndmoveUl(ul, 0);
		moveLiFirstToLast(ul);
	}, 1000);
}

/**
 * ul안의 li들 오른쪽으로 한번 슬라이드
 * @param ul
 */
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
