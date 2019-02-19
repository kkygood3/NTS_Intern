var React = require("react");
var Util = require("../../util")

class MyReservationItem extends React.Component {
    constructor(props){
        super(props);
    }
    
    render() {
        return (
			<article class="card_item" data-reservation-id={this.props.reservation.reservationInfoId}
				 onClick={this.props.onClickReservationBtn != undefined
					 ? function(event) { 
						 this.props.onClickReservationBtn(event, this.props.reservation.displayInfo.productDescription)
						}.bind(this)
					: function() {
						return;
					}}>
				<a href="#" class="link_booking_details">
				<div class="card_body">
					<div class="left"></div>
					<div class="middle">
						<div class="card_detail">
							<em class="booking_number">No.0000000</em>
							<h4 class="tit">{this.props.reservation.displayInfo.productDescription}</h4>
								<ul class="detail">
									<li class="item">
										<span class="item_tit">일정</span>
										<em class="item_dsc">
											{Util.toDateString(this.props.reservation.reservationDate)}
										</em>
									</li>
									<li class="item">
										<span class="item_tit">내역</span>
										<em class="item_dsc">
											내역이 없습니다.
										</em>
									</li>
									<li class="item">
										<span class="item_tit">장소</span>
										<em class="item_dsc">
											{this.props.reservation.displayInfo.placeName}

										</em>
									</li>
									<li class="item">
										<span class="item_tit">주소</span>
										<em class="item_dsc">
											{this.props.reservation.displayInfo.placeStreet}
										</em>
									</li>
								</ul>
								<div class="price_summary">
									<span class="price_tit">결제 예정금액</span>
									<em class="price_amount">
										<span>{Util.convertNumberToFormattedNumber(this.props.reservation.totalPrice)}</span>
										<span class="unit">원</span>
									</em>
								</div>
								<div class="booking_cancel">
									{
										this.props.btnMsg == undefined ? "" : (
										<button class="btn">
											<span>
												{this.props.btnMsg}
											</span>
										</button> )
									}
								</div>
							</div>
						</div>
						<div class="right"></div>
					</div>
					<div class="card_footer">
						<div class="left"></div>
						<div class="middle"></div>
						<div class="right"></div>
					</div>
				</a>
			</article>
        );
    }
}

module.exports = MyReservationItem;
