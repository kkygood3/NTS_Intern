
/**
 * @function sendGet 서버에 Get 메서드 요청 함수.
 * @param {String} path 요청 URL
 * @param {JSON} params  
 * @param {Function} onCallback 콜백 함수.
 */
function sendGet (path, params, onCallback) {
    var data = "";
    if(params){
        data = Object.keys(params).map(function(key){
            return key + "=" + encodeURIComponent(params[key]);
        }).join("&");
    }
    var request = new XMLHttpRequest();
    request.addEventListener("load", function(event){
        onCallback(event.target);
    });

    var url = path + (data.length == 0 ? "" : "?") + data;
    request.open("GET", url);
    request.setRequestHeader("Content-type","charset=utf-8");
    request.send();
}