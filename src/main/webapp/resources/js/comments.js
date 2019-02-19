document.addEventListener("DOMContentLoaded", () => {
	template.setting();
	template.setUseDateFormat(0, 10);
	
	var displayInfoId = document.querySelector("#display_info").dataset.displayInfoId;
	ajax("GET", "/api/products/" + displayInfoId + "/comments", "", printComment);
	
});

function printComment(data){
	const SCORE_TO_PERCENT = 20;
	var commentListInfo = JSON.parse(data).commentListInfo;
	var commentItems = commentListInfo.commentList;
	var commentCount = commentListInfo.totalCount;
	var commentAvgScore = commentListInfo.averageScore;
	
	document.querySelector("#comment_count").innerHTML = commentCount;
	document.querySelector("#comment_avg_score").innerHTML = commentAvgScore.toFixed(1);
	document.querySelector("#star_score").style.width = commentAvgScore * SCORE_TO_PERCENT + "%";
	
	var commentList = document.querySelector("#comment_list");
	commentItems.forEach((commentItem) =>{
		commentList.innerHTML += template.parseCommentToHtml(commentItem);
	});
}