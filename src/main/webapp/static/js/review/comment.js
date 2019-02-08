/**
 * @desc 전역변수
 */
var sendAjax = require('../sendAjax');

/**
 * @function setHandlebarRegistHelper()  : handlebar regist Helper 설정
 * @function getHandlebarTemplateFromHtml(templateId,content) : handlebar로 compile해서 html 얻어오기
 */
var handlebarRegistHelper = require('../handlebarRegistHelper');

document.addEventListener('DOMContentLoaded', function(){
	getComments();
});

/**
 * @desc comment Setting 
 */
var setComments = {

	/**
	 * @desc comment총 갯수 설정
	 * @param commentLength
	 */
	setProductCount : function(commentLength) {
		document.querySelector('#comment_count').innerText = commentLength;
	},

	/**
	 * @desc comment 평균 점수 셋팅
	 * @param averageScore
	 */
	setCommentAverageScore : function(averageScore) {

		var start = 0;
		var end = 3;

		document.querySelector('#average_score').innerText = String(averageScore).substring(start, end);
	},

	/**
	 * @desc comment 평균 점수 별점 셋팅
	 * @param averageScore
	 */
	setAverageScoreStar : function(averageScore) {

		document.querySelector('#comment_average_star').style.width = averageScore* 20 + '%';
	},
	
	/**
	 * @desc comments 내용 전체 셋팅
	 * @param comments : {comments : comments} 객체
	 */
	setCommentList : function(comments){
		document.querySelector('.list_short_review').innerHTML = handlebarRegistHelper.getHandlebarTemplateFromHtml('#comment_template',comments);
	}
	
};

/**
 * @desc comment 가져오기 가져온 데이터로 셋팅
 */
function getComments(){
	
	var commentSendHeader = {
		method : 'GET',
		uri : '/api/comments/'+getProductId()
	};
	
	sendAjax(commentSendHeader,'',function(commentResponse){
		
		setComments.setProductCount(commentResponse.comments.length);
		setComments.setCommentAverageScore(commentResponse.averageScore);
		setComments.setAverageScoreStar(commentResponse.averageScore);
		
		handlebarRegistHelper.setHandlebarRegistHelper();
		setComments.setCommentList({comments:commentResponse.comments});
	});
}

/**
 * @desc productId 가져오기
 */
function getProductId(){
	return document.querySelector('.section_review_list').dataset.productid;
}
