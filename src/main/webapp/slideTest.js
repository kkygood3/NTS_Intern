document.addEventListener("DOMContentLoaded", function() {
	var ul = document.getElementsByClassName("visual_img")[0];
	var left = 0;
	const interval = window.setInterval(()=> {
			if (left == 500) {
				left = 0;
				var firstLi = ul.firstElementChild;
				ul.appendChild(firstLi);
				ul.style.left = 0;
			} else {
				ul.style.left = Math.log(++left)*-15.78 + "px";
			}	// 수치는 나중에 다시 계산해야함
	},100);
});
