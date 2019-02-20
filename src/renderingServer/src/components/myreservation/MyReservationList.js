var React = require("react");
var MyReservationListSection = require("./MyReservationListSection");

class MyReservationList extends React.Component {
    constructor(props){
        super(props);
    }

    render() {
        return (
            <div class="wrap_mylist" style={{display : `${this.props.totalCount == 0 ? "none": ""}`}}>
                <ul class="list_cards">
                    <MyReservationListSection containerClassList={["confirmed"]}
                        headerIconClass={"ico_check2"}
                        headerText={"예약 확정"} reservations={this.props.okReservations} btnMsg={"취소"} onClickReservationBtn= {this.props.onClickCancelReservationBtn}/>
                    <MyReservationListSection containerClassList={["used"]}
                    headerIconClass={"ico_check2"}
                    headerText={"이용 완료"}  reservations={this.props.finishReservations} btnMsg={"예매자 리뷰 남기기"}/>
                    <MyReservationListSection containerClassList={["used", "cancel"]}
                    headerIconClass={"ico_cancel"}
                    headerText={"취소된 예약"}  reservations={this.props.cancelReservations}/>
                </ul>
            </div>
        );
    }
}

module.exports = MyReservationList;
