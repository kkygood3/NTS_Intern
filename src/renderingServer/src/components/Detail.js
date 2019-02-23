var React = require("react");
var Header = require("./detail/Header");
var Pagination = require("./detail/Pagination");
var ImageSlider = require("./detail/ImageSlider");
var Description = require("./detail/Description");
var Event = require("./detail/Event");
var ReserveBtn = require("./detail/ReserveBtn");
var ReviewList = require("./detail/ReviewList");
var Info = require("./detail/Info");

class Detail extends React.Component {

    constructor(props){
        super(props)

        this.state = {
            currentImageIndex: 0
        }
        this.changeCurrentImageIndex = this.changeCurrentImageIndex.bind(this);
    }

    getEventText() {
        var eventText = this.props.productPrices.reduce(function(prevValue, price) {
            if (price.discountRate != 0) {
                return prevValue + price.priceTypeName + "석 " + price.discountRate + "%, ";
            }
        }, "");

        if (eventText != undefined) {
            eventText = eventText.substr(0, eventText.length - 2) + " 할인";
        }

        return eventText;
    }
    changeCurrentImageIndex(index) {
        this.setState({
            currentImageIndex: index
        })
    }

    render() {
        return (
            <div>
                <div id="container">
                    <div class="ct main">
                        <div>
                            <div class="section_visual">
                                <Header email={this.props.email} />
                                <Pagination currentImageIndex={this.state.currentImageIndex + 1} totalImageCount={this.props.productImages.length}/>
                                <ImageSlider name={this.props.displayInfo.productDescription} 
                                    productImages={this.props.productImages}
                                    changeCurrentImageIndex={this.changeCurrentImageIndex}/>
                                <Description description={this.props.displayInfo.productContent}/>
                                {this.getEventText() == undefined ? "" : <Event eventText={this.getEventText()}/>}
                                <ReserveBtn displayId={this.props.displayInfo.displayInfoId}/>
                                {this.props.comments.length > 0 ? <ReviewList displayId={this.props.displayInfo.displayInfoId} 
                                                                        comments={this.props.comments} 
                                                                        averageScore={this.props.averageScore}/> : ""}
                                <Info displayInfo={this.props.displayInfo} displayInfoImage={this.props.displayInfoImage}/>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        );
    }
}

module.exports = Detail;