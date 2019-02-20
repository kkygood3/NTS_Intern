var React = require("react");
var MyReservationItem = require("./MyReservationItem");

class MyReservationListSection extends React.Component {
    constructor(props){
        super(props);
    }
    containerClassNames () {
        var classList = this.props.containerClassList
        var classNames = classList.reduce(function (prevValue, className) {
            prevValue += className + " ";
            return prevValue;
        }, "").trim();

        return classNames;
    }
    
    render() {
        return (
            <li class={`card ${this.containerClassNames()}`}>
                <div class="link_booking_details">
                    <div class="card_header">
                        <div class="left"></div>
                        <div class="middle">
                            <i class={`spr_book2 ${this.props.headerIconClass}`}></i>
                            <span class="tit">{this.props.headerText}</span>
                        </div>
                        <div class="right"></div>
                    </div>
                </div>
                {
                    this.props.reservations.map( function(reservation) {
                        return <MyReservationItem 
                                    reservation = {reservation}
                                    btnMsg = {this.props.btnMsg}
                                    onClickReservationBtn = {this.props.onClickReservationBtn}
                                />
                }.bind(this))}
            </li>
        );
    }
}

module.exports = MyReservationListSection;
