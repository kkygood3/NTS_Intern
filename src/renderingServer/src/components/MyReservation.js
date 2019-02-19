var React = require("react");
var Popup = require("./myreservation/Popup");
var CopyrightFooter = require("./common/CopyrightFooter");
var TopBar = require("./common/TopBar");
var Summary = require("./myreservation/Summary");
var EmptyReservation = require("./myreservation/EmptyReservation")
var MyReservationList = require("./myreservation/MyReservationList")

var util = require("../util");

class MyReservation extends React.Component {
  constructor(props) {
    super(props);

    var filteredReservations = this.filterReservations(props.reservations);
    this.state = {
      email: props.email,
      size: props.size,
      okReservations: filteredReservations.okReservations,
      cancelReservations: filteredReservations.cancelReservations,
      finishReservations: filteredReservations.finishReservations,
      clickedReservationId: -1,
      clickedReservationTitle: "",
      isHidePopup: true
    }

    this.onClickCancelReservationBtn = this.onClickCancelReservationBtn.bind(this);
    this.onClickPopupCloseBtn = this.onClickPopupCloseBtn.bind(this);
    this.onClickPopupOkBtn = this.onClickPopupOkBtn.bind(this);

  }
  filterReservations() {
    var reservations = this.props.reservations;
    var initParam = {
      okReservations: [],
      cancelReservations: [],
      finishReservations: []
    };

    var now = Date.now();
    var filteredReservations = reservations.reduce(function(prevValue, reservation) {

        if (reservation.cancelYn === true) {
            prevValue.cancelReservations.push(reservation);
        } else if (reservation.reservationDate < now) {
            prevValue.finishReservations.push(reservation);
        } else {
            prevValue.okReservations.push(reservation);
        }
        return prevValue;
    }, initParam);

    return filteredReservations;
  }

  onClickCancelReservationBtn (event, title) {
    if (!event.target.classList.contains("btn") && !event.target.parentNode.classList.contains("btn")) {
      return;
    }

    var reservationId = event.currentTarget.dataset.reservationId;
    this.setState({
      clickedReservationId: reservationId,
      clickedReservationTitle: title,
      isHidePopup: false
    });
  }

  onClickPopupCloseBtn (event) {
    this.setState({
      isHidePopup: true
    })
  }

  onClickPopupOkBtn (event) {
    var path = "/reservation-service/api/reservations";
    var reservationId = this.state.clickedReservationId;
    this.setState({
      isHidePopup: true
    })

    var self = this;
    util.sendPutWithPathVariable(path, reservationId, function(response) {
      self.onRequestCancelReservationCallback(response);
    });
  }

  onRequestCancelReservationCallback (response) {
    if (response.status == 200) {
      var reservationId = Number(response.responseText);
      var okReservations = Array.from(this.state.okReservations);
      var cancelReservations = Array.from(this.state.cancelReservations);
      var targetReservation = {};

      for (var i = 0; i < okReservations.length; i++) {
        if (okReservations[i].reservationInfoId == reservationId) {
            targetReservation = okReservations[i];
            okReservations.splice(i, 1);
            break;
        }
      }
      cancelReservations.push(targetReservation);
      
      this.setState({
        okReservations: okReservations,
        cancelReservations: cancelReservations
      });
    } else {
        alert("예약을 취소하는 데 실패했습니다.");
    }
  }

  render() {
    try{
      return (
        <div>
          <div id="container">
              <TopBar email={this.props.email}/>
              <hr/>
              <div class="ct">
                <div class="section_my">
                  <Summary totalCount = {this.state.size}
                    okReservationCount={this.state.okReservations.length}
                    finishReservationCount={this.state.finishReservations.length}
                    cancelReservationCount={this.state.cancelReservations.length}/>
                  <MyReservationList
                    totalCount = {this.state.size}
                    okReservations={this.state.okReservations}
                    finishReservations={this.state.finishReservations}
                    cancelReservations={this.state.cancelReservations}
                    onClickCancelReservationBtn= {this.onClickCancelReservationBtn}  
                  />
                  <EmptyReservation totalCount={this.state.size}/>
                </div>
              </div>
              <hr/>
          </div>
          <CopyrightFooter />
          <Popup isHidePopup={this.state.isHidePopup} onClickCloseBtn= { this.onClickPopupCloseBtn } 
            onClickOkBtn= {this.onClickPopupOkBtn } title= { this.state.clickedReservationTitle} />
        </div>
        
      )
    }catch(err){
      console.log(err);
    }
  }
}

module.exports = MyReservation;
