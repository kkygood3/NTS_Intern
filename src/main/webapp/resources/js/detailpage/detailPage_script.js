/**
 * 
 */

// library mapping to use forEach
HTMLCollection.prototype.forEach = Array.prototype.forEach;
NodeList.prototype.forEach = Array.prototype.forEach;

var urls = {
    DETAIL : "/reservation/api/products/",
};

var constants = {
    DISPLAY_INFO_ID : ""
}

function init() {
    // parse initial id value from url;
    var url_string = window.location.href;
    var url = new URL(url_string);
    constants.DISPLAY_INFO_ID = url.searchParams.get("id");
    fetchDetailData();
}

function fetchDetailData(){
    xhrGetRequest(urls.DETAIL+constants.DISPLAY_INFO_ID,(respText) => {
        let detailData = JSON.parse(respText);
        console.log(detailData)
    });
}