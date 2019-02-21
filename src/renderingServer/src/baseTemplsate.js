module.exports = {
    main: function(markup, data) {
        return `<!DOCTYPE html>
        <html lang="ko">
            <head>
                <meta charset="utf-8">
                <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
                <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
                <title>네이버 예약</title>
                <link href="/reservation-service/css/style.css" rel="stylesheet">
                <link href="/reservation-service/css/animation.css" rel="stylesheet">
                <script id="dataContainer">window.__requestData__ = ${data}</script>
            </head>
            
            <body>
                ${markup}
                <script src="/reservation-service/js/react/mainPage.js"></script>
            </body>
        </html>`;
    },
    myReservation: function(markup, data) {
        return `<!DOCTYPE html>
        <html lang="ko">
            <head>
                <meta charset="utf-8">
                <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
                <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
                <title>네이버 예약</title>
                <link href="./css/style.css" rel="stylesheet">
                <script id="dataContainer">window.__requestData__ = ${data}</script>
            </head>
            
            <body>
                ${markup}
                <script src="./js/myReservationPage.js"></script>
            </body>
        </html>`;
    }
}