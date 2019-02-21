var React = require("react");
var PromotionItem = require("./PromotionItem");
var { CSSTransition, TransitionGroup } = require("react-transition-group");

class Promotion extends React.Component {
    constructor(props) {
        super(props);
        console.log(props);
        this.state = {
            current: 0,
            start: false
        }
    }
    componentDidMount() {
        this.setState({
            start: true
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
    render() {
        return (
            <div>
                <div class="container_visual">
                    <ul class="visual_img" current={this.state.current}>
                        {this.props.promotions.items.map(function(promotion, index) {
                            var styleClass = "";
                            if(index == this.state.current) {
                                styleClass = "currentSlider";
                            }else if(index == this.getNextIndex()) {
                                styleClass = "nextSlider";
                            }else if(index == this.getPrevIndex()) {
                                styleClass = "prevSlider";
                            }else {
                                styleClass = "waitSlider";
                            }
                            return (<CSSTransition
                                        classNames={styleClass}
                                        timeout={1000}
                                        onEntered={function() {
                                            console.log("entered!")
                                        }}
                                        onEnter={function() {
                                            console.log("enter!");
                                        }}
                                        onEntering={
                                            function() {
                                                console.log("entering!")
                                            }
                                        }
                                        onExit={
                                            function() {
                                                console.log("exit!")
                                            }
                                        }
                                        onExited={
                                            function() {
                                                console.log("exited!")
                                            }
                                        }
                                        onExiting= {
                                            function() {
                                                console.log("Exiting!!");
                                            }
                                        }
                                        >
                                        <PromotionItem promotion={promotion} index={index} styleName={styleClass} />
                                    </CSSTransition>);
                            
                        }.bind(this))}
                    </ul>
                </div>
            </div>
        );
    }
}

module.exports = Promotion;
