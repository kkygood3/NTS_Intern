var React = require("react");

class ImageSliderItem extends React.Component {

  render() {
    return (
        <li class={`item product_image ${this.props.slideStyleName}`} style={{width: '414px'}}> 
            <img alt={this.props.name} class="img_thumb" src={`/api/download/img?imageName=${this.props.productImage.saveFileName}`}/> <span class="img_bg"></span>
          <div class="visual_txt">
            <div class="visual_txt_inn">
              <h2 class="visual_txt_tit"> <span>{this.props.name}</span> </h2>
            </div>
          </div>
        </li>
    );
  }
}
module.exports = ImageSliderItem;