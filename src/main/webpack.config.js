const path = require('path');

const reservationConfig = {
  context: path.resolve(__dirname, 'webapp/static/js/reservation'),
  entry: {
	  reservationConvert: './reservation.js',
  },
  output: {
    path: path.resolve(__dirname, 'webapp/static/js/reservation'),
    filename: '[name].js',
    publicPath: '/static/js/reservation/',
  },
  module: {
    rules: [
      {
        test: /\.m?js$/,
        exclude: /(node_modules|bower_components)/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['@babel/preset-env']
          }
        }
      }
    ]
  }
};

const reserveConfig = {
  context: path.resolve(__dirname, 'webapp/static/js/reserve'),
  entry: {
	  reserveConvert: './reserve.js',
  },
  output: {
    path: path.resolve(__dirname, 'webapp/static/js/reserve'),
    filename: '[name].js',
    publicPath: '/static/js/reserve/',
  },

  module: {
    rules: [
      {
        test: /\.m?js$/,
        exclude: /(node_modules|bower_components)/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['@babel/preset-env']
          }
        }
      }
    ]
  }
};

const loginConfig = {
  context: path.resolve(__dirname, 'webapp/static/js/login'),
  entry: {
	  loginConvert: './login.js',
  },
  output: {
    path: path.resolve(__dirname, 'webapp/static/js/login'),
    filename: '[name].js',
    publicPath: '/static/js/login/',
  },
  module: {
    rules: [
      {
        test: /\.m?js$/,
        exclude: /(node_modules|bower_components)/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['@babel/preset-env']
          }
        }
      }
    ]
  }
};

const detailConfig = {
  context: path.resolve(__dirname, 'webapp/static/js/detail'),
  entry: {
	  detailConvert: './detail.js',
  },
  output: {
    path: path.resolve(__dirname, 'webapp/static/js/detail'),
    filename: '[name].js',
    publicPath: '/static/js/detail/',
  },
  module: {
    rules: [
      {
        test: /\.m?js$/,
        exclude: /(node_modules|bower_components)/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['@babel/preset-env']
          }
        }
      }
    ]
  }
};

const reviewWriteConfig = {
  context: path.resolve(__dirname, 'webapp/static/js/review/write'),
  entry: {
	  reviewWriteConvert: './reviewWrite.js',
  },
  output: {
    path: path.resolve(__dirname, 'webapp/static/js/review/write'),
    filename: '[name].js',
    publicPath: '/static/js/review/write/',
  },
  module: {
    rules: [
      {
        test: /\.m?js$/,
        exclude: /(node_modules|bower_components)/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['@babel/preset-env']
          }
        }
      }
    ]
  }
};

const mainConfig = {
  context: path.resolve(__dirname, 'webapp/static/js'),
  entry: {
	  home: './main.js',
  },
  output: {
    path: path.resolve(__dirname, 'webapp/static/js'),
    filename: '[name].js',
    publicPath: '/static/js',
  },
  module: {
    rules: [
      {
        test: /\.m?js$/,
        exclude: /(node_modules|bower_components)/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['@babel/preset-env']
          }
        }
      }
    ]
  }
}

module.exports = [reservationConfig, reserveConfig, loginConfig, detailConfig, reviewWriteConfig, mainConfig];