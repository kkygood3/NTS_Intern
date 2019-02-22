var React = require("react");

class ProductItem extends React.Component {

    constructor(props){
        super(props)
    }

    render() {
        return (
            <li class="item">
            	<a href={`/react/displayInfo/${this.props.product.displayInfoId}`} class="item_book">
                	<div class="item_preview">
                    	<img alt={this.props.product.productDescription} class="img_thumb" src={`/api/download/img?imageName=${this.props.product.productImageUrl}`}/>
                    	<span class="img_border"></span>
                	</div>
                	<div class="event_txt">
                   		<h4 class="event_txt_tit"> <span>{this.props.product.productDescription}</span> <small class="sm">{this.props.product.placeName}</small> </h4>
                   	 	<p class="event_txt_dsc">{this.props.product.productContent}</p>
                	</div>
            	</a>
        	</li>
        );
    }
}

module.exports = ProductItem;