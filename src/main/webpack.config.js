
const path = require('path');

module.exports = {
  context: path.resolve(__dirname, 'webapp/static/js/'),
  entry: {
    home: './main.js',
  },
  output: {
    path: path.resolve(__dirname, 'webapp/static/js'),
    filename: 'home.js',
    publicPath: '/static',
  }
};