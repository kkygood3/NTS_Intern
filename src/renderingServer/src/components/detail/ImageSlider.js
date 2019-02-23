var React = require("react");
var ImageSliderItem = require("./ImageSliderItem");

class ImageSlider extends React.Component {
    constructor(props){
        super(props);
        this.sliderCurrent = 0; //어느 이미지가 현재 이미지인지를 보여주는 flag. 이미지 두개로 왔다갔다 하므로 .
        this.state = {
            slideStyleNames: ["slideCurrent", "slideReady"],
            slideItemIndex: [0, 1]
        }
        this.onClickBtn = this.onClickBtn.bind(this);
    }
    onClickBtn(direct) {
        this.moveSlide(direct);
    }
    
    moveSlide(direct) {
        var slideItemIndex = this.changeIndex(direct);
        var slideStyleNames = this.changeSlideStyleNames(direct);
        this.setState({
            slideItemIndex: slideItemIndex,
            slideStyleNames: slideStyleNames
        }, function() {
            this.props.changeCurrentImageIndex(
                this.state.slideItemIndex[this.sliderCurrent]
            );
        }.bind(this));

    }
    changeIndex(direct) {
        var current =this.state.slideItemIndex[this.sliderCurrent];
        var length = this.props.productImages.length;
        var next = 0;
        var slideItemIndex = Array.from(this.state.slideItemIndex);

        if(direct == "left") {
            next = (current - 1 + length) % length; 
        }else {
            next = (current + 1) % length;
        }

        if(this.sliderCurrent == 0){
            slideItemIndex[1] = next;
        }else {
            slideItemIndex[0] = next;
        }

        return slideItemIndex;
    }
    changeSlideStyleNames(direct) {
        var slideStyleNames = [];
        if(direct == "left") {
            if(this.sliderCurrent == 0) {
                this.sliderCurrent = 1;
                slideStyleNames = ["slideLeftOut", "slideLeftIn"];
            }else {
                this.sliderCurrent = 0;
                slideStyleNames = ["slideLeftIn", "slideLeftOut"];
            }
        }else {
            if(this.sliderCurrent == 0) {
                this.sliderCurrent = 1;
                slideStyleNames = ["slideRightOut", "slideRightIn"];
            }else {
                this.sliderCurrent = 0;
                slideStyleNames = ["slideRightIn", "slideRightOut"];
            }
        }
        return slideStyleNames;
    }

    render() {
        return (
            <div class="group_visual">
                <div>
                    <div class="container_visual" style={{width: '414px', height: '414px'}}>
                        <ul class="visual_img detail_swipe" >
                            <ImageSliderItem productImage={this.props.productImages[this.state.slideItemIndex[0]]} 
                                name={this.props.name} 
                                slideStyleName={this.state.slideStyleNames[0]}/>
                                {
                                    this.props.productImages.length > 1 ?
                                    <ImageSliderItem productImage={this.props.productImages[this.state.slideItemIndex[1]]} 
                                        name={this.props.name} 
                                        slideStyleName={this.state.slideStyleNames[1]}/> : ""
                                }
                            
                        </ul>
                    </div>
                    {   
                        this.props.productImages.length > 1 ?
                        (<div class="prev">
                            <div class="prev_inn" onClick={function() {
                                this.onClickBtn("left")
                            }.bind(this)}>
                                <a href="#" class="btn_prev" title="이전">
                                    <i class="spr_book2 ico_arr6_lt"></i>
                                </a>
                            </div>
                        </div>) : "" 
                    }
                    {
                        this.props.productImages.length > 1 ?
                        (<div class="nxt">
                            <div class="nxt_inn" onClick={function() {
                                this.onClickBtn("right")
                            }.bind(this)}>
                                <a href="#" class="btn_nxt" title="다음">
                                    <i class="spr_book2 ico_arr6_rt"></i>
                                </a>
                            </div>
                        </div>) : ""
                    }
                </div>
            </div>
        );
    }
}
module.exports = ImageSlider;