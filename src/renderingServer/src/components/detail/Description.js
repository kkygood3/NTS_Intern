var React = require("react");

class Description extends React.Component {
    constructor(props){
        super(props)
        this.state = {
            isOpen: false
        }
        this.onClickBtn = this.onClickBtn.bind(this);
    }

    onClickBtn() {
        this.setState({
            isOpen: !this.state.isOpen
        })
    }

  render() {
    return (
        <div class="section_store_details">
            <div class={`store_details ${this.state.isOpen ? "": "close3"}`}>
                <p class="dsc">{this.props.description}</p>
            </div>
            <a class="bk_more _open" style={{display: this.state.isOpen ? "none":""}} onClick={function() {
                this.onClickBtn();
            }.bind(this)}> <span class="bk_more_txt">펼쳐보기</span> <i class="fn fn-down2"></i> </a>
            <a class="bk_more _close" style={{display: this.state.isOpen ? "":"none"}}  onClick={function() {
                this.onClickBtn();
            }.bind(this)}> <span class="bk_more_txt">접기</span> <i class="fn fn-up2"></i> </a>
        </div>
    );
  }
}
module.exports = Description;    