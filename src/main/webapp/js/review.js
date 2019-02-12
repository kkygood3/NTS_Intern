var PERCENT_COEF = 20;

function requestAjax(callback, url) {
	var ajaxReq = new XMLHttpRequest();
	ajaxReq.callback = callback;
	ajaxReq.addEventListener('load', function(evt) {
		this.callback(evt.target.response);
	});

	ajaxReq.open('GET', url);
	ajaxReq.responseType = 'json';
	ajaxReq.send()
}

function convertDateFormat(date){
	var originDate = date.split(' ')[0];
	var originDateSplited = originDate.split('-');
	var resultDate = originDateSplited[0];
	
	if(originDateSplited[1].charAt(0) === '0'){
		originDateSplited[1] = originDateSplited[1].charAt(1); 
	}
	
	resultDate += '.'+originDateSplited[1];
	
	if(originDateSplited[2].charAt(0) === '0'){
		originDateSplited[2] = originDateSplited[2].charAt(1); 
	}
	
	resultDate += '.'+originDateSplited[2];
	
	return resultDate;
}

function loadDisplayInfoCallback(responseData) {
	var displayInfoResponse = responseData.detailDisplay;
	
	var averageScore = displayInfoResponse.averageScore.toFixed(1);
	var comments = displayInfoResponse.comments;
	
	//Comment Template
	var commentTemplate = document.querySelector('#commentItemTemplate').innerText;
	var bindCommentTemplate = Handlebars.compile(commentTemplate);
	
	var commentContainer = document.querySelector('ul.list_short_review');
	for(var i = 0 ; i < comments.length; i++){
		comments[i].reservationDate = convertDateFormat(comments[i].reservationDate); 
		comments[i].productDescription = displayInfoResponse.displayInfo.productDescription;
		commentContainer.innerHTML += bindCommentTemplate(comments[i]);	
	} 
	
	//맨 위 화면의 title
	document.querySelector('a.title').innerText = displayInfoResponse.displayInfo.productDescription;
	
	//별점 그래프, 숫자 조정
	document.querySelector('em.graph_value').style.width = (averageScore * PERCENT_COEF) + '%';
	document.querySelector('.text_value>span').innerText = averageScore;
	
	//우측 상단의 Comment 갯수
	document.querySelector('span.join_count>em.green').innerText = comments.length+'건';
} 

document.addEventListener('DOMContentLoaded', function() {
	//페이지 첫 로딩시 할 일
	
	var id = location.href.split('?')[1].split('=')[1];
	requestAjax(loadDisplayInfoCallback, 'api/products/' + id);
});