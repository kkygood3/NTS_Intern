window.onload = function() {
	setInterval(function() {showToday()}, 1000);
}

function showToday() {
	document.getElementById("today").innerHTML = formatDate(new Date());
}

function formatDate(date) {
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	var h = date.getHours();
	var i = date.getMinutes();
	return "현재시간 : " + y +'/' + m + '/' + d + ' ' + h + ':' + i;
}