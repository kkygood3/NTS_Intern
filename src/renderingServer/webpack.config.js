const webpack = require("webpack");
const path = require('path')

const clientConfig = {
    entry: {
        myReservationPage: "./src/client/myReservationPage"
    },
    output: {
        path: path.join(__dirname + "/../main/webapp/resources/js"),
        filename: "[name].js"
    },
    module: {
        rules: [
            {
                test: /js$/,
                exclude: /(node_modules)/,
                loader: "babel-loader",
                query: { presets: ["@babel/preset-react", "@babel/preset-env"] }
            }
        ]
    }
}

const serverConfig = {
    entry: {
        server: "./src/server.js"
    },
    target: "node",
    output: {
        path: __dirname,
        filename: "[name].js"
    },
    module: {
        rules: [
            {
                test: /js$/,
                exclude: /(node_modules)/,
                loader: "babel-loader",
                query: { presets: ["@babel/preset-react", "@babel/preset-env"] }
            }
        ]
    }
}

module.exports = [serverConfig, clientConfig];