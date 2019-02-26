var React = require("react");
var DetailInfo = require("./DetailInfo");
var ContactInfo = require("./ContactInfo");

class Info extends React.Component {
    constructor(props){
        super(props)
        this.state = {
            type: "detail" 
        }

        this.type = ["detail", "contact"];
    }

    onClickTab() {
        this.setState({
            type: this.state.type == this.type[0] ? this.type[1] : this.type[0]
        })
    }

    render() {
        return (
            <div class="section_info_tab">
                    <ul class="info_tab_lst">
                        <li class="item active _detail">
                            <a class={`anchor ${this.state.type == this.type[0] ? "active" : ""}`}  onClick={function() {
                                if(this.state.type == this.type[0]){
                                    return;
                                }
                                this.onClickTab();
                            }.bind(this)}> <span>상세정보</span> </a>
                        </li>
                        <li class="item _path">
                            <a class={`anchor ${this.state.type == this.type[1] ? "active" : ""}`}  onClick={function() {
                                if(this.state.type == this.type[1]){
                                    return;
                                }
                                this.onClickTab();
                            }.bind(this)}> <span>오시는길</span> </a>
                        </li>
                    </ul>
                    <DetailInfo type={this.state.type} description={this.props.displayInfo.productContent}/>
                    <ContactInfo type={this.state.type} displayInfo={this.props.displayInfo} displayInfoImage={this.props.displayInfoImage}/>
            </div>
        );
    }
}
module.exports = Info;