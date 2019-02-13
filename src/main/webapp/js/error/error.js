/**
 * 에러페이지 로직 처리
 * @author 시윤
 *
 */

document.addEventListener("DOMContentLoaded", function() {
	addGotoMainButtonClickEvent();
});

function addGotoMainButtonClickEvent() {
	var gotoMainButton = document.getElementsByClassName("goto_main")[0];
	gotoMainButton.addEventListener("click", function(event){
		location.href="/";
	});
}
