var React = require("react");

class Event extends React.Component {
    constructor(props){
        super(props)
    }

  render() {
    return (
        <div class="section_event">
            <div class="event_info_box">
                <div class="event_info_tit">
                    <h4 class="in_tit"> <i class="spr_book ico_evt"></i> <span>이벤트 정보</span> </h4>
                </div>
                <div class="event_info">
                    <div class="in_dsc">[네이버예약 특별할인]<br/>{this.props.eventText}</div>
                </div>
            </div>
        </div>
    );
  }
}
module.exports = Event;