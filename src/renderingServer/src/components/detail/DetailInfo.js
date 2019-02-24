var React = require("react");

class DetailInfo extends React.Component {
    constructor(props){
        super(props)
    }

  render() {
    return (
        <div class={`detail_area_wrap ${this.props.type=="detail" ? "" : "hide"}`}>
            <div class="detail_area">
            <div class="detail_info">
                    <h3 class="blind">상세정보</h3>
                    <ul class="detail_info_group">
                        <li class="detail_info_lst">
                            <strong class="in_tit">[소개]</strong>
                            <p class="in_dsc">{this.props.description}</p>
                        </li>
                        <li class="detail_info_lst"> <strong class="in_tit">[공지사항]</strong>
                            <ul class="in_img_group">
                                <li class="in_img_lst"> <img alt="" class="img_thumb" src="https://ssl.phinf.net/naverbooking/20170131_238/14858250829398Pnx6_JPEG/%B0%F8%C1%F6%BB%E7%C7%D7.jpg?type=a1000"/> </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    );
  }
}
module.exports = DetailInfo;