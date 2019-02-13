/**
 * 카테고리 영역 관련 로직 처리
 * @author 시윤
 *
 */

function sendGetCategorissAjax() {
	sendGetAjax("./category", makeCategoryBar);
}

/**
 * 슬라이드 하단 카테고리영역 html생성
 * @param categoris 카테고리 목록
 * 		id 카테고리id
 * 		name 카테고리 이름
 * @returns
 */
function makeCategoryBar(categoris) {
	if (!categoris) {
		return false;
	}
	var html = document.getElementById("category_item").innerHTML;

	var resultHTML = "";
	categoris.forEach((category) => {
	    resultHTML += html.replace("{id}", category.id)
	    				.replace("{name}", category.name);
	});
	document.getElementsByClassName("event_tab_lst")[0].innerHTML += resultHTML;
}
