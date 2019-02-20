var React = require("react");

class EmptyReservation extends React.Component {
    constructor(props){
        super(props);
    }
    
    render() {
        return (
            <div class="err" style={{
                display: `${this.props.totalCount == 0 ? "" : "none"}`
            }}> 
                <i class="spr_book ico_info_nolist"></i>
                <h1 class="tit">예약 리스트가 없습니다</h1>
            </div>
        );
    }
}
module.exports = EmptyReservation;
