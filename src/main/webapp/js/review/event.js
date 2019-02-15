/**
 * review 페이지 이벤트 처리
 * @author 시윤
 *
 */

/**
 * 더보기 버튼 클릭이벤트
 * 3개씩 리뷰 더 가져옴
 */
function addMoreButtonClickEvent() {
	var moreButton = document.querySelector(".more > .btn");
	moreButton.addEventListener("click", function(event){
		sendGetCommentsAjax(calcLoadedComments());
	});
}

