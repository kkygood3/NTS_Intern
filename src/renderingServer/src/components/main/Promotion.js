var React = require("react");
var PromotionItem = require("./PromotionItem");

const MOVE_TIME = 1000;
const WAIT_TIME = 3000;

class Promotion extends React.Component {
    constructor(props) {
        super(props);
        this.executionTime = 0;
        this.move = false;
        this.sliderCurrent = 0;
        this.state = {
            current: 0,
            slideStyleNames: ["slideCurrent", "slideReady"],
            slideItemIndex: [0, 1]
        }
    }
    componentDidMount() {
        this.executionTime = new Date().getTime();

        var self = this;
        requestAnimationFrame(function() {
            self.updateAnimation();
        });
    }
    
    getNextIndex() {
         var current = this.state.current;
         var totalCount = this.props.promotions.totalCount;
         var next = current - 1 < 0 ? totalCount + (current - 1) : current - 1;
         return next;
    }

    getPrevIndex() {
        var current = this.state.current;
        var totalCount = this.props.promotions.totalCount;
        var prev = current + 1 > totalCount ? current + 1 - totalCount : current + 1;
        return prev;
    }

    updateAnimation() {

        var now = new Date().getTime();
        var tickTime = this.move ? MOVE_TIME : WAIT_TIME
        
        if(now - this.executionTime >= tickTime){
            this.move = !this.move;
            if(this.move) {
                this.moveSlide()
            }else {
                this.waitSlide()
            }
            
            this.executionTime = new Date().getTime();
        }
        var self = this;
        requestAnimationFrame(function() {
            self.updateAnimation();
        });
    }

    moveSlide() {
        if(this.sliderCurrent == 0) {
            this.sliderCurrent = 1;
            this.setState({
                slideStyleNames: ["slideCurrent slideOut", "slideReady slideIn"]
            })
        }else {
            this.sliderCurrent = 0;
            this.setState({
                slideStyleNames: ["slideReady slideIn", "slideCurrent slideOut"]
            })
        }
    }
    waitSlide() {
        var newPromotionIndex = Array.from(this.state.slideItemIndex);
        var slideStyleNames = [];
        if(this.sliderCurrent == 0) {
            newPromotionIndex[1] = (newPromotionIndex[0] + 1) % this.props.promotions.totalCount;
            slideStyleNames = ["slideCurrent", "slideReady"];
            
        }else {
            newPromotionIndex[0] = (newPromotionIndex[1] + 1) % this.props.promotions.totalCount;
            slideStyleNames = ["slideReady", "slideCurrent"];
        }
        this.setState({
            slideStyleNames: slideStyleNames,
            slideItemIndex: newPromotionIndex
        })
    }
    
    render() {
        return (
            <div>
                <div class="container_visual">
                    <ul class="visual_img" >
                        <PromotionItem promotion={this.props.promotions.items[this.state.slideItemIndex[0]]} styleName={this.state.slideStyleNames[0]}/>
                        <PromotionItem promotion={this.props.promotions.items[this.state.slideItemIndex[1]]} styleName={this.state.slideStyleNames[1]} />
                    </ul>
                </div>
            </div>
        );
    }
}

module.exports = Promotion;
