var React = require("react");
var CategoryItem = require("./CategoryItem");

class Categories extends React.Component {

    constructor(props){
        super(props)
    }

    render() {
        return (
            <div class="section_event_tab">
                <ul class="event_tab_lst tab_lst_min" >
                    <CategoryItem id={0} categoryName={"전체리스트"}
                     selected={this.props.currentIndex == 0}
                     onClickCategory={this.props.onClickCategory}
                     />
                    {this.props.categories.items.map(function(item, index) {
                        return <CategoryItem id={item.id} 
                            categoryName={item.name} 
                            selected={this.props.currentIndex == index + 1}
                            onClickCategory={this.props.onClickCategory}
                            />;
                    }.bind(this))}
                </ul>
            </div>
        );
    }
}

module.exports = Categories;