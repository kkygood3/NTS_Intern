/**
 * 약관 더보기 클릭이벤트
 */
function addagreementClickEvent() {
	var agreements = document.getElementsByClassName("agreement");

	for (let agreement of agreements) {
		agreement.addEventListener("click", function(event){
			if (!event.target.closest("a")) {
				return;
			}
			for (let className of event.currentTarget.classList) {
				if (className == "open") {
					event.currentTarget.classList.remove("open");
					return;
				}
			}
			event.currentTarget.classList.add("open");
		});
	}
}