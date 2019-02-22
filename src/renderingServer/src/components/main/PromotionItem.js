var React = require("react");


class PromotionItem extends React.Component {
    constructor(props) {
        super(props);
        
        this.state = {
            current: 0
        }

    }
    render() {
        return (
            <li class={`item ${this.props.styleName}`} style={
                {
                    backgroundImage: `url(/api/download/img?imageName=${this.props.promotion.productImageUrl})`
                 }
             } data-index={this.props.index}>
        		<a href="#">  <span class="img_btm_border"></span> <span class="img_right_border"></span> <span class="img_bg_gra"></span>
            		<div class="event_txt">
                		<h4 class="event_txt_tit">{this.props.promotion.productDescription}</h4>
                		<p class="event_txt_adr">{this.props.promotion.placeName}</p>
                		<p class="event_txt_dsc"></p>
            		</div>
        		</a>
    		</li>
        );
    }
}

module.exports = PromotionItem;