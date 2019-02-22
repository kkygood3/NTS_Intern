var React = require("react");
var Header = require("./detail/Header");
var Pagination = require("./detail/Pagination");
var ImageSlider = require("./detail/ImageSlider");

class Detail extends React.Component {

    constructor(props){
        super(props)

        this.state = {
            currentImageIndex: 0
        }
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
                                <ImageSlider />
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        );
    }
}

module.exports = Detail;