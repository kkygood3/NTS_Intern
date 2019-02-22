/**
 * @author 육성렬
 */
var React = require('react');
var ReactDom = require('react-dom');

var Detail = require('../components/Detail');

document.addEventListener("DOMContentLoaded", function() {

    var requestData = window.__requestData__;
    delete window.__requestData__;

    console.log(requestData);
    console.log("////");
    console.log(Detail);
    var dataContainer = document.querySelector("#dataContainer");
    dataContainer.parentNode.removeChild(dataContainer);
    ReactDom.render( <Detail email={requestData.userEmail} productImages={requestData.productImages}/> , document.body);
});
