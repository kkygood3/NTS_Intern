var React = require("react");
var TopBar = require("./common/TopBar");
var util = require("../util");
var Promotion = require("./main/Promotion");

class Main extends React.Component {
  constructor(props) {
    super(props);

  }

  render() {
    try{
      return (
        <div>
            <div id="container">
                <TopBar email={this.props.email}/>
                <hr/>
                <div class="event">
                    <div class="section_visual">
                        <div class="group_visual">
                            <div class="container_visual">
                                <Promotion promotions= {this.props.promotions}/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
      )
    }catch(err){
      console.log(err);
    }
  }
}

module.exports = Main;
