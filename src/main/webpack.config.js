const path = require('path');

module.exports = {
  context: path.resolve(__dirname, 'webapp/static/js/detail'),
  entry: {
	detailConvert: './detail.js',
  },
  output: {
    path: path.resolve(__dirname, 'webapp/static/js/detail'),
    filename: 'detailConvert.js',
    publicPath: '/static/js/',
  }
};