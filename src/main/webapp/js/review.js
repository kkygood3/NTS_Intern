function loadDisplayInfoCallback(responseData) {
	var reviewResponse = responseData.comments;
	var displayInfomation = reviewResponse[0];
	var commentCount = displayInfomation.commentCount;

	var averageScore = displayInfomation.averageScore;

	// Comment Template
	var commentTemplate = document.querySelector('#commentItemTemplate').innerText;
	var bindCommentTemplate = Handlebars.compile(commentTemplate);

	var commentContainer = document.querySelector('ul.list_short_review');
	for (var i = 0; i < commentCount; i++) {
		commentContainer.innerHTML += bindCommentTemplate(reviewResponse[i]);
	} 
	
	// 맨 위 화면의 title
	document.querySelector('a.title').innerText = displayInfomation.productDescription;
	
	//별점 그래프, 숫자 조정
	document.querySelector('em.graph_value').style.width = (averageScore * 20) + '%';
	document.querySelector('.text_value>span').innerText = averageScore;
	
	//우측 상단의 Comment 갯수
	document.querySelector('span.join_count>em.green').innerText = commentCount+'건';
	
	//뒤로가기 버튼 클릭 이벤트
	document.querySelector('.btn_back').setAttribute('href','detail?id='+displayInfomation.displayInfoId);
} 

document.addEventListener('DOMContentLoaded', function() {
	//페이지 첫 로딩시 할 일
	requestAjax(loadDisplayInfoCallback, 'api/products/' + getUrlParameter('id') + '/review');
});