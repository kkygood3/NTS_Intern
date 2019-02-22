var React = require("react");

class ImageSliderItem extends React.Component {

  render() {
    return (
        <li class="item product_image current_pos" style={{width: '414px'}}> 
            <img alt="퀀틴 블레이크" class="img_thumb" src="/api/download/img?imageName=img/2_ma_4.png"/> <span class="img_bg"></span>
			<div class="visual_txt">
				<div class="visual_txt_inn">
					<h2 class="visual_txt_tit"> <span>퀀틴 블레이크</span> </h2>
				</div>
			</div>
		</li>
    );
  }
}
module.exports = ImageSliderItem;