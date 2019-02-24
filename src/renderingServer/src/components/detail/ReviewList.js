var React = require("react");
var ReviewItem = require("./ReviewItem");

class ReviewList extends React.Component {
    constructor(props){
        super(props)
    }
    getAverageScorePercent() {
        return (this.props.averageScore / 5) * 100;
    }
  render() {
    return (
        <div class="section_review_list">
            <div class="review_box">
                <h3 class="title_h3">예매자 한줄평</h3>
                <div class="short_review_area">
                    <div class="grade_area">
                        <span class="graph_mask"> <em class="graph_value" style={{width: this.getAverageScorePercent() + "%"}}></em> </span>
                        <strong class="text_value"> <span>{this.props.averageScore.toFixed(1)}</span> <em class="total">5.0</em> </strong>
                        <span class="join_count"><em class="green">{this.props.comments.length}건</em> 등록</span>
                    </div>
                    <ul class="list_short_review">
                        {this.props.comments.map(function(comment, index) {
                            if(index < 3) {
                                return <ReviewItem comment={comment}/>
                            }
                        })}
                    </ul>
                </div>
                <p class="guide"> <i class="spr_book2 ico_bell"></i> <span>네이버 예약을 통해 실제 방문한 이용자가 남긴 평가입니다.</span> </p>
            </div>
            {this.props.comments.length <= 3 ? "" 
                : <a class="btn_review_more" href={`/displayInfo/${this.props.displayId}/review`}> <span>예매자 한줄평 더보기</span> <i class="fn fn-forward1"></i> </a>}
           
        </div>
    );
  }
}
module.exports = ReviewList;