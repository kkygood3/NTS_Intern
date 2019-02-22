var React = require("react");

class ImageSlider extends React.Component {

  render() {
    return (
        <div class="group_visual">
            <div>
                <div class="container_visual" style={{width: '414px'}}>
                    <ul class="visual_img detail_swipe" >

                    </ul>
                </div>
                <div class="prev">
                    <div class="prev_inn">
                        <a href="#" class="btn_prev" title="이전">
                            <i class="spr_book2 ico_arr6_lt off"></i>
                        </a>
                    </div>
                </div>
                <div class="nxt">
                    <div class="nxt_inn">
                        <a href="#" class="btn_nxt" title="다음">
                            <i class="spr_book2 ico_arr6_rt"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    );
  }
}
module.exports = ImageSlider;