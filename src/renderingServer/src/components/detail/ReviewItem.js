var React = require("react");
var util = require("../../util");

class ReviewItem extends React.Component {
    constructor(props){
        super(props)
    }

  render() {
    return (
        <li class="list_item">
			<div>
				<div class="review_area">
                    {
                        this.props.comment.commentImages.length > 0 ? 
                            (
                                <div class="thumb_area">
                                    <a href={`/api/download/comment/image/${this.props.comment.commentImages[0].imageId}`} title="이미지 크게 보기"> 
                                        <img width="90" height="90" class="img_vertical_top" src={`/api/download/comment/image/${this.props.comment.commentImages[0].imageId}`} alt="리뷰이미지"/> 
                                    </a>
                                </div>
                            ) : ""
                    }
                    					
                    <h4 class="resoc_name"></h4>
					<p class="review">{this.props.comment.comment}</p>
				</div>
				<div class="info_area">
					<div class="review_info"> 
                        <span class="grade">{this.props.comment.score.toFixed(1)}</span> 
                        <span class="name">{this.props.comment.reservationName}</span> 
                        <span class="date">{util.toDateString(this.props.comment.createDate)}방문</span> 
                    </div>
				</div>
			</div>
		</li>
    );
  }
}
module.exports = ReviewItem;