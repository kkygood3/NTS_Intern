var React = require("react");

var util = require("../util");

class Main extends React.Component {
  constructor(props) {
    super(props);

    var filteredReservations = this.filterReservations(props.reservations);
    this.state = {

    }

    this.onClickCancelReservationBtn = this.onClickCancelReservationBtn.bind(this);
    this.onClickPopupCloseBtn = this.onClickPopupCloseBtn.bind(this);
    this.onClickPopupOkBtn = this.onClickPopupOkBtn.bind(this);

  }

  render() {
    try{
      return (
        <div>
          
        </div>
        
      )
    }catch(err){
      console.log(err);
    }
  }
}

module.exports = Main;
