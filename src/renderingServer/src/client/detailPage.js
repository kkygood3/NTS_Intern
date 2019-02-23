/**
 * @author 육성렬
 */
var React = require('react');
var ReactDom = require('react-dom');

var Detail = require('../components/Detail');

document.addEventListener("DOMContentLoaded", function() {

    var requestData = window.__requestData__;
    delete window.__requestData__;

    var dataContainer = document.querySelector("#dataContainer");
    dataContainer.parentNode.removeChild(dataContainer);
    ReactDom.render( <Detail email={requestData.userEmail}
                         productImages={requestData.productImages}
                         displayInfo={requestData.displayInfo}
                         productPrices={requestData.productPrices}
                         comments={requestData.comments}
                         averageScore={requestData.averageScore}
                         displayInfoImage={requestData.displayInfoImage}
                         /> , document.body);
});
