// TODO: 시간도 입력받을수있게하거나 함수이름바꾸기
function makeSlide(ul) {
	const interval = window.setInterval(()=> {
		runSlide(ul);
	}, 5000);
}

function runSlide(ul) {
	if (readyToSlide){
		ul.style.transition = "";
		ul.styp
		ul.style.left = 0;
		var firstLi = ul.firstElementChild;
		ul.appendChild(firstLi);

		window.setTimeout(()=> {
			ul.style.transition = "left 2s";
			ul.style.left = "-414px";
		}, 1000);
	}
}
