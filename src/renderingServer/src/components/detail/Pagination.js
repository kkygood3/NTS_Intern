var React = require("react");

class Pagination extends React.Component {

  render() {
    return (
        <div class="pagination">
            <div class="bg_pagination"></div>
            <div class="figure_pagination">
                <span class="num">{this.props.currentImageIndex}</span>
                <span class="num off">/ <span>{this.props.totalImageCount}</span></span>
            </div>
        </div>
    );
  }
}
module.exports = Pagination;