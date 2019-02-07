/**
 * @author 육성렬
 */

document.addEventListener("DOMContentLoaded", function() {
	reviewPage.init();
})

var reviewPage = {
	init: function() {
		this.requestDisplayInfo();
	},
	values: {

	},
	elements: {
		headerContainer: document.querySelector(".review_header"),
		reviewContainer: document.querySelector(".review_box")
	},
	/**
	 * @function requestDisplayInfo 서버에 displayInfo 정보 요청
	 */
	requestDisplayInfo: function() {
		var id = getDisplayInfoIdFromUrl();
		
		sendGet("/reservation-service/api/products/"+id, {}, function(response){
			this.requestDisplayInfoCallback(response);
		}.bind(this));
	},
	/**
	 * @function responseDisplayInfo displayInfo 요청에 대한 처리.
	 * @param {XMLHttpRequest}
	 *            response
	 */
	requestDisplayInfoCallback: function(response) {
		if(response.status == 200){
			var data = JSON.parse(response.responseText);
			const { averageScore, comments, displayInfo, displayInfoImage, productImages, productPrices } = data;
			this.updateHeader(displayInfo.placeName);
			this.updateReviewContainer(comments, averageScore, displayInfo.productDescription);
			
        }else if(response.status == 500) {
            alert("서버 내부 오류 : 상세 정보 로딩에 실패하였습니다.");
        }else {
			alert("잘못된 요청입니다.");
		}
	},
	/**
	 * @function updateHeader 헤더의 UI를 갱신시켜준다.
	 * @param {String}
	 *            placeName
	 */
	updateHeader: function(placeName){
		var headerTitleTextUi = this.elements.headerContainer.querySelector(".title");
		headerTitleTextUi.textContent = placeName;
	},
	/**
	 * @function updateReviewContainer 코멘트 리스트를 갱신시켜준다.
	 * @param {JSON[]}
	 *            comments
	 */
	updateReviewContainer: function(comments, averageScore, productTitle){
		var reviewContainer = this.elements.reviewContainer;
		this.updateReviewContainerHeader(averageScore, comments.length);
		this.updateCommentList(comments, reviewContainer.querySelector(".list_short_review"), productTitle)
	},

	/**
	 * @function updateCommentHeader 리뷰 콘테이너의 헤더 UI를 갱신한다.
	 * @param {Number}
	 *            averageScore
	 * @param {Number}
	 *            commentCount
	 */
	updateReviewContainerHeader: function(averageScore, commentCount){
		var reviewContainer = this.elements.reviewContainer;
		var scoreTextUi = reviewContainer.querySelector(".text_value > span");
		scoreTextUi.textContent = averageScore.toFixed(1);

		var scoreGraph = reviewContainer.querySelector(".graph_value");
		var graphValuePercent = Math.round((averageScore / 5) * 100);
		scoreGraph.style.width = graphValuePercent + "%";
		
		var commentCountTextUi = reviewContainer.querySelector(".green");
		commentCountTextUi.innerHTML = commentCount + "건";
	},

	/**
	 * @function updateCommentList commentList를 갱신한다.
	 * @param {JSON[]}
	 *            comments
	 * @param {Element}
	 *            commentListContainer
	 * @param {String}
	 *            productTitle
	 */
	updateCommentList: function(comments, commentListContainer, productTitle){
		var resultHtml = comments.reduce(function(prevValue, comment) {
			var commentElement = "";
			if(comment.commentImages.length > 0){
				commentElement += this.getCommentElement(comment, productTitle);
			}else{
				commentElement += this.getNoImageCommentElement(comment, productTitle);
			}
			return prevValue + commentElement;
		}.bind(this), "");
		commentListContainer.innerHTML = resultHtml;
	},
	/**
	 * @function getNoImageCommentElement 이미지가 없는 코멘트 템플릿 리턴
	 * @param {JSON}
	 *            comment
	 * @param {String}
	 *            productTitle
	 */
	getNoImageCommentElement: function(comment, productTitle) {
		var bindTemplate = getTargetTemplate("#noImageComment");
		var data = {
			productName: productTitle,
			comment: comment.comment,
			score: comment.score.toFixed(1),
			name: comment.reservationName,
			commentDate: toDateString(comment.reservationDate) + "방문"
		};
		return bindTemplate(data).trim();
	},
	/**
	 * @function getCommentElement 이미지가 있는 코멘트 템플릿 리턴
	 * @param {JSON}
	 *            comment
	 * @param {String}
	 *            productTitle
	 */
	getCommentElement: function(comment, productTitle) {
		var bindTemplate = getTargetTemplate("#imageComment");
		var data = {
			productName: productTitle,
			comment: comment.comment,
			score: comment.score.toFixed(1),
			name: comment.reservationName,
			commentDate: toDateString(comment.reservationDate) + "방문",
			imageUrl: "../../" + comment.commentImages[0].saveFileName,
			imageCount: comment.commentImages.length
		};
		return bindTemplate(data).trim();
	}
};