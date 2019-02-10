// TODO: 함수를 두개만들지 if문으로 처리할지...
function makeSlide(ul, dir="left") {
	if (dir != "left" && dir != "right") {
		return;
	}
	const interval = window.setInterval(()=> {
		if (readyToSlide){
			runSlide(ul, dir);
		}
	}, 5000);
}

function runSlide(ul, dir) {
	if (dir != "left" && dir != "right") {
		return;
	}

	moveUl(ul, dir);

	window.setTimeout(()=> {
		moveLi(ul, dir);
	}, 2000);
}

function moveLi(ul, dir) {
	ul.classList.remove("transition");
	ul.style.left = 0;
	if (dir == "left") {
		var firstLi = ul.firstElementChild;
		ul.appendChild(firstLi);
	} else {
		var lastLi = ul.lastElementChild;
		ul.insertBefore(lastLi, ul.firstElementChild);
	}
}

function moveUl(ul, dir) {
	ul.classList.add("transition");
	if (dir == "left") {
		ul.style.left = "-414px";
	} else {

		ul.style.left = "414px";
	}
}
