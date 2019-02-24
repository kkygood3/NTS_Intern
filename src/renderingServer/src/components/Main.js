var React = require("react");
var TopBar = require("./common/TopBar");
var util = require("../util");
var Promotion = require("./main/Promotion");
var Categories = require("./main/Categories");
var ProductItem = require("./main/ProductItem");
var MoreBtn = require("./main/MoreBtn");
var CopyrightFooter = require("./common/CopyrightFooter");

class Main extends React.Component {
  constructor(props) {
    super(props);

    var products = JSON.parse(JSON.stringify(this.props.products));

    this.state = {
      currentCategory: 0,
      products: products.items,
      currentCategoryTotalCount: products.totalCount
    }
    
    this.onClickCategory = this.onClickCategory.bind(this);
    this.onClickMoreBtn = this.onClickMoreBtn.bind(this);
  }
  componentDidMount() {
    
  }
  onClickCategory(event, categoryId) {
    //setState request 뒤로 미루자.

    var self = this;
    this.setState({
      currentCategory: categoryId,
      products: []
    }, function() {
      self.requestProducts(0, categoryId == 0 ? null : categoryId);
    })
  }

  onClickMoreBtn(event) {
    this.requestProducts(this.state.products.length
      , this.state.currentCategory == 0 ? null : this.state.currentCategory);
  }

  requestProducts(start, categoryId) {
      var params = {};
      if (start) {
          params.start = start;
      }
      if (categoryId) {
          params.categoryId = categoryId;
      }
      
      var options = {
          params: params
      }

      var self = this;
      util.sendGet( "/api/products", options, function(response) {
          self.requestProductsCallback(response);
      });
  }

  requestProductsCallback(response) {
    if (response.status == 200) {
      var data = JSON.parse(response.responseText);
      var products = Array.from(this.state.products);
      var currentCategoryTotalCount = data.totalCount;
      var newProducts = data.items;
      this.setState({
        currentCategoryTotalCount: currentCategoryTotalCount,
        products: products.concat(newProducts)
      })
    } else {
        alert("상품 목록을 불러오는데 실패했습니다.");
    }
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
                    <Categories categories={this.props.categories} 
                     currentIndex={this.state.currentCategory}
                     onClickCategory={this.onClickCategory}
                     />
                     <div class="section_event_lst">
                        <p class="event_lst_txt">
                          바로 예매 가능한 행사가 <span class="pink">{this.state.currentCategoryTotalCount}개</span> 있습니다
                        </p>
                        <div class="wrap_event_box">
                            <ul class="lst_event_box">
                              {this.state.products.map( function(product, index) {
                                if(index%2 == 1){
                                  return;
                                }
                                return <ProductItem product={product} />;
                              })}
                            </ul>
                            <ul class="lst_event_box">
                              { this.state.products.map( function(product, index) {
                                if(index%2 == 0){
                                  return;
                                }

                                return <ProductItem product={product} />;
                              })}
                            </ul>
                            {
                              this.state.currentCategoryTotalCount > this.state.products.length ? 
                                <MoreBtn onClickMoreBtn={this.onClickMoreBtn}/>: ""
                            }
                            
                        </div>
                     </div>
                </div>
            </div>
            <CopyrightFooter />
        </div>
        
      )
    }catch(err){
      console.log(err);
    }
  }
}

module.exports = Main;
