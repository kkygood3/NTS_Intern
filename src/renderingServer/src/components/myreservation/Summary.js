var React = require("react");

class TopBar extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <div class="my_summary">
                <ul class="summary_board">
                    <li class="item">
                        <a href="#" class="link_summary_board on"> <i class="spr_book2 ico_book2"></i> <em class="tit">전체</em> <span class="figure">{this.props.totalCount}</span> </a>
                    </li>
                    <li class="item">
                        <a href="#" class="link_summary_board"> <i class="spr_book2 ico_book_ss"></i> <em class="tit">이용예정</em> <span class="figure">{this.props.okReservationCount}</span> </a>
                    </li>
                    <li class="item">
                        <a href="#" class="link_summary_board"> <i class="spr_book2 ico_check"></i> <em class="tit">이용완료</em> <span class="figure">{this.props.finishReservationCount}</span> </a>
                    </li>
                    <li class="item">
                        <a href="#" class="link_summary_board"> <i class="spr_book2 ico_back"></i> <em class="tit">취소·환불</em> <span class="figure">{this.props.cancelReservationCount}</span> </a>
                    </li>
                </ul>
            </div>
        );
    }
}

module.exports = TopBar;
