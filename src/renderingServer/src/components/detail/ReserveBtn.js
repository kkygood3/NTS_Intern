var React = require("react");

class ReserveBtn extends React.Component {

  render() {
    return (
        <div class="section_btn"> 
          <button type="button" class="bk_btn" onClick={function(){
            window.location.href="/displayInfo/reservation/" + this.props.displayId;
          }.bind(this)}> 
            <i class="fn fn-nbooking-calender2"></i> 
            <span>예매하기</span> 
          </button> 
        </div>
    );
  }
}
module.exports = ReserveBtn;