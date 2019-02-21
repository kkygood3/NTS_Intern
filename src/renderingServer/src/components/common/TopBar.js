var React = require("react");

class TopBar extends React.Component {
  render() {
    return (
			<div class="header">
				<header class="header_tit">
					<h1 class="logo">
						<a href="./" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
						<a href="./" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
					</h1>
					<a href="#" class="btn_my"> <span title="내예약" class="viewReservation">{`${this.props.email == null ? '예약 확인' : this.props.email}`}</span> </a>
				</header>
			</div>
    );
  }
}

module.exports = TopBar;


