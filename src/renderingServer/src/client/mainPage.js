/**
 * @author 육성렬
 */
var React = require('react');
var ReactDom = require('react-dom');

var Main = require('../components/Main')

document.addEventListener("DOMContentLoaded", function() {

    var requestData = window.__requestData__;
    delete window.__requestData__;
    var dataContainer = document.querySelector("#dataContainer");
    dataContainer.parentNode.removeChild(dataContainer);
    ReactDom.render( <Main email={requestData.userEmail} products={requestData.products} promotions={requestData.promotions} categories={requestData.categories}/> , document.body);
});

