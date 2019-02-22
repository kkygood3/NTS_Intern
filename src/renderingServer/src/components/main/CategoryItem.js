var React = require("react");

class CategoryItem extends React.Component {

    constructor(props){
        super(props)
    }

    render() {
        return (
            <li class="item" onClick={function(event) {
                if(this.props.selected) {
                    return;
                }
                this.props.onClickCategory(event, this.props.id);
            }.bind(this)}>
				<a class={`anchor ${this.props.selected ? "active": ""}`}>
					<span>{this.props.categoryName}</span>
				</a>
			</li>
        );
    }
}

module.exports = CategoryItem;