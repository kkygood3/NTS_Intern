/**
 * @author 육성렬
 */
var React = require('react');
var ReactDom = require('react-dom');

var MyReservation = require('../components/MyReservation')

document.addEventListener("DOMContentLoaded", function() {

    var requestData = window.__requestData__;
    delete window.__requestData__;
    var dataContainer = document.querySelector("#dataContainer");
    dataContainer.parentNode.removeChild(dataContainer);

    console.log("DOMContentLoaded");
    ReactDom.render( <MyReservation email={requestData.email} reservations={requestData.reservations} size={requestData.size} /> , document.body);
});

