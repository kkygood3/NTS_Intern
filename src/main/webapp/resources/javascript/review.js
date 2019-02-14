/**
 * 
 */

document.addEventListener('DOMContentLoaded', ()=>{
	let displayInfoId = document.querySelector('.short_review_area').dataset.id;
	let url = '/api/comment/' + displayInfoId;
	ajax(init, 'GET', url);
});

function init(data){
	loadScore(data);
	loadComments(data);
}

function loadComments(data){
	let commentTemplate = document.querySelector("#comment_template").innerText;
	let bindTemplate = Handlebars.compile(commentTemplate);
	
	if(data == null) {
		return;
	}

	let resultHtml = data.comments.reduce((prev, next)=>{
		return prev + bindTemplate(next);
	}, "");

	let commentUl = document.querySelector('#comment_ul');
	commentUl.innerHTML = resultHtml;
}

function loadScore(data){
	let gradeTemplate = document.querySelector('#score_template').innerText;
	let bindTemplate = Handlebars.compile(gradeTemplate);

	let resultHtml = bindTemplate(data);
	let gradeDiv = document.querySelector('#score');
	gradeDiv.innerHTML = resultHtml;
	
	let scoreValue = document.querySelector('#avgerage_score');
	let starWidth = document.querySelector('.graph_value');
	let percentageScore = (parseFloat(scoreValue.innerText) / 5 ) * 100;

	starWidth.style.width = percentageScore + '%';
}