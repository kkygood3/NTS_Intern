function loadDisplayInfoCallback(responseData) {
	// 모든 코멘트 보여주기
	loadAllComments(responseData);
}

function requestAjax(callback, url){
	let ajaxReq = new XMLHttpRequest();
	ajaxReq.callback = callback;
	ajaxReq.addEventListener('load', function(evt) {
		this.callback(evt.target.response);
	});
	
	ajaxReq.open('GET', url);
	ajaxReq.responseType = 'json';
	ajaxReq.send()
}

function getUrlParameter(name) {
	let params = location.href.split('?')[1].split('&');
	for (let i = 0; i < params.length; i++) {
		let paramSplited = params[i].split('=');
		let paramName = paramSplited[0];
		let paramValue = paramSplited[1];

		if (paramName === name) {
			return paramValue;
		}
	}
}

function loadAllComments(responseData){
	let reviewResponse = responseData.detailCommentList;
	let displayInfomation = reviewResponse[0];
	
	let commentCount = responseData.commentCount;
	let averageScore = responseData.averageScore.toFixed(1);

	// 코멘트 템플릿
	let commentTemplate = document.querySelector('#commentItemTemplate').innerText;
	let bindCommentTemplate = Handlebars.compile(commentTemplate);

	let commentContainer = document.querySelector('ul.list_short_review');
	for (let i = 0; i < commentCount; i++) {
		reviewResponse[i].reservationEmail = reviewResponse[i].reservationEmail.substring(0,4) + "****";
		commentContainer.innerHTML += bindCommentTemplate(reviewResponse[i]);
	}
	
	// 타이틀 제목
	document.querySelector('a.title').innerText = displayInfomation.productDescription;
	
	// 별점 그래프 및 코멘트 평균 점수
	document.querySelector('em.graph_value').style.width = (averageScore * 20) + '%';
	document.querySelector('.text_value>span').innerText = averageScore;
	
	// 코멘트 개수
	document.querySelector('span.join_count>em.green').innerText = commentCount+'건';
	
	// 뒤로가기 버튼 클릭 이벤트
	document.querySelector('.btn_back').setAttribute('href','detailProduct?displayInfoId='+displayInfomation.displayInfoId);
	
	// 펼치기 버튼 가리기
	document.querySelector('div.more').style.display = 'none';
}

document.addEventListener('DOMContentLoaded', function() {
	requestAjax(loadDisplayInfoCallback, 'api/products/' + getUrlParameter('displayInfoId') + '/detailComment');
});