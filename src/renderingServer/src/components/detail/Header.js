var React = require("react");

class Header extends React.Component {

  render() {
    return (
        <header>
            <h1 class="logo">
                <a href="/react" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
                <a href="/react" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
            </h1>
            <a href="/react/myReservation" class="btn_my"> 
                <span class="viewReservation" title="예약확인">
                    {`${this.props.email == null ? '예약 확인' : this.props.email}`}
                </span>
            </a>
        </header>
    );
  }
}
module.exports = Header;