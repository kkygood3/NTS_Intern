function setPreviewText() {
	const dayName = ["일", "월", "화", "수", "목", "금", "토"];
	var period = generateReservationDate();
	var preview = document.querySelectorAll(".preview_txt .preview_txt_dsc");
	preview[1].innerText = period.start.toLocaleDateString() + "(" + dayName[period.start.getDay()]
		+ ") ~ " + period.end.toLocaleDateString() + "(" + dayName[period.end.getDay()]
		+ "), 잔여티켓 " + Math.ceil(Math.random() * 10000) + "매";
}

function generateReservationDate() {
	var fiveDays = 432000000;	// 5(d) * 24(h) * 60 (m) * 60(s) * 1000(ms) = 432000000
	var now = new Date();
	var start = new Date(now.getTime() - fiveDays);
	var end = new Date(now.getTime() + fiveDays);
	return {
		"start" : start,
		"end" : end
	};
}