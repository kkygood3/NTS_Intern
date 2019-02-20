var React = require("react");

class Popup extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div class="popup_booking_wrapper" style
                            ={{display:`${this.props.isHidePopup ? "none" : "block"}`}}>
                <div class="dimm_dark" style={{display:'block'}}></div>
                <div class="popup_booking refund">
                    <h1 class="pop_tit">
                        <span>{this.props.title}</span>
                    </h1>
                    <div class="nomember_alert">
                        <p>취소하시겠습니까?</p>
                    </div>
                    <div class="pop_bottom_btnarea">
                        <div class="btn_gray">
                            <a href="#" class="btn_bottom"
                             onClick={this.props.onClickCloseBtn}><span>아니오</span></a>
                        </div>
                        <div class="btn_green">
                            <a href="#" class="btn_bottom"
                             onClick={this.props.onClickOkBtn}><span>예</span></a>
                        </div>
                    </div>
                    <a href="#" class="popup_btn_close" title="close"
                         onClick={this.props.onClickCloseBtn}>
                        <i class="spr_book2 ico_cls"></i>
                    </a>
                </div>
            </div>
        );
    }
}

module.exports = Popup;
