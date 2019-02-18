document.addEventListener('DOMContentLoaded', function() {
	requestAjax(loadDisplayInfoCallback, 'api/products/' + getUrlParameter('displayInfoId'));
});

function loadDisplayInfoCallback(responseData) {
	// 코멘트
	loadComments(responseData);
	
	// 펼쳐보기 및 닫기
	loadShowMoreButton();
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

function loadComments(responseData){
	displayInfo = responseData.displayInfo;
	displayComment = responseData.comments;
	
	let commentCount = responseData.comments.length;
	let averageScore = responseData.averageScore;
	
	// 코멘트 템플릿
	let commentTemplate = document.querySelector('#commentItemTemplate').innerText;
	let bindCommentTemplate = Handlebars.compile(commentTemplate);
	
	let commentContainer = document.querySelector('ul.list_short_review');
	
	for(let i = 0 ; i < 3 && i < commentCount; i++){
		commentContainer.innerHTML += bindCommentTemplate(displayComment[i]);	
	}
	
	// 별점 그래프 및 코멘트 평균 점수
	document.querySelector('em.graph_value').style.width = (averageScore * 20) + '%';
	document.querySelector('.text_value>span').innerText = averageScore.toFixed(1);
	
	// 코멘트 개수
	document.querySelector('span.join_count>em.green').innerText = commentCount+'건';
	
	// 코멘트 더보기 버튼 생성하기
}

function loadShowMoreButton(){
	let unfoldBtn = document.querySelector('a._open');	
	let foldBtn = document.querySelector('a._close');
	let foldingText = document.querySelector('div.store_details');
	let textArea = document.querySelector('p.dsc');

	if(textArea.scrollHeight > textArea.clientHeight){
		document.querySelector('div.section_store_details').addEventListener('click',function(evt){
			evt.preventDefault();
			let clickedTag  = evt.target;
			
			if(clickedTag.tagName == 'SPAN' || clickedTag.tagName == 'I'){
				clickedTag = clickedTag.parentElement;
			} 
			
			if(clickedTag.className === 'bk_more _open'){
				
				foldingText.classList.remove('close3');
				
				unfoldBtn.style.display = 'none';
				foldBtn.style.display = '';
				
			}else if(clickedTag.className === 'bk_more _close'){
				
				foldingText.classList.add('close3');
				
				unfoldBtn.style.display = '';
				foldBtn.style.display = 'none';
				
			}
		});
	} else {
		unfoldBtn.style.display = 'none';
	}
}