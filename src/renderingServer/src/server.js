/**
 * @author 육성렬
 */
var express = require("express");
var bodyParser = require("body-parser");

var ReactDOMServer = require("react-dom/server");
var React = require("react");

var MyReservation = require("./components/MyReservation");
var Main = require("./components/Main");
var baseTemplate = require("./baseTemplsate");

var app = express();
app.use(bodyParser.urlencoded())
app.use(bodyParser.json())

app.post("/myReservation", function(req, res) {
    try {
        var markup = ReactDOMServer.renderToString(<MyReservation email={req.body.email}
                                                     reservations={req.body.reservations} size={req.body.size} />);
        var html = baseTemplate.myReservation(markup, JSON.stringify(req.body));
        res.send(html);    
    }catch (e){
        console.log(e);
        res.send("Internal Error", 500);
    }
})

app.post("/main", function(req, res) {
    try {
        var markup = ReactDOMServer.renderToString(<Main email={req.body.userEmail} products={req.body.products} promotions={req.body.promotions} categories={req.body.categories}/>);
        var html = baseTemplate.main(markup, JSON.stringify(req.body));
        res.send(html);
    } catch (e) {
        console.log(e);
        res.send("Internal Error", 500);
    }
})

app.get('*', function(req, res){
    res.send("not found", 404);
});

app.listen(3000, function() { })