var React = require("react");

class DetailInfo extends React.Component {
    constructor(props){
        super(props)
    }

  render() {
    return (
        <div class={`detail_location ${this.props.type=="detail" ? "hide" : ""}`}>
            <div class="box_store_info no_topline">
                <a href="#" class="store_location" title="지도웹으로 연결">
                    <img class="store_map img_thumb" alt="map" src={`/api/download/img?imageName=${this.props.displayInfoImage.saveFileName}`}/>
                    <span class="img_border"></span>
                    <span class="btn_map"><i class="spr_book2 ico_mapview"></i></span>
                </a>
                <div class="store_info">
                    <div class="store_addr_wrap">
                        <span class="fn fn-pin2"></span>
                        <p class="store_addr store_addr_bold">{this.props.displayInfo.placeStreet}</p>
                        <p class="store_addr">
                            <span class="addr_old">지번</span>
                            <span class="addr_old_detail">{this.props.displayInfo.placeLot}</span>
                        </p>
                        <p class="store_addr addr_detail">{this.props.displayInfo.placeName}</p>
                    </div>
                    <div class="lst_store_info_wrap">
                        <ul class="lst_store_info">
                            <li class="item"> <span class="item_lt"> <i class="fn fn-call2"></i> <span class="sr_only">전화번호</span> </span> <span class="item_rt"> <a href="tel:02-548-0597" class="store_tel">{this.props.displayInfo.telephone}</a></span> </li>
                        </ul>
                    </div>
                </div>
                <div class="bottom_common_path column2">
                    <a href="#" class="btn_path"> <i class="fn fn-path-find2"></i> <span>길찾기</span> </a>
                    <a hewf="#" class="btn_navigation before"> <i class="fn fn-navigation2"></i> <span>내비게이션</span> </a>
                </div>
            </div>
        </div>
    );
  }
}
module.exports = DetailInfo;