var React = require("react");

class MoreBtn extends React.Component {

    constructor(props){
        super(props)
    }

    render() {
        return (
            <div class="more">
                <button class="btn" onClick={function(event) {
                    this.props.onClickMoreBtn(event);
                }.bind(this)}>
                    <span>더보기</span>
                </button>
            </div>
        );
    }
}

module.exports = MoreBtn;