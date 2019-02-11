var currentStart = 0;
var currentCategory = 0;

// Rest API로 서버로부터 해당 url의 json데이터를 가져옴
function requestAjax(callback, url) {
    var ajaxReq = new XMLHttpRequest();
    ajaxReq.callback = callback;
    ajaxReq.addEventListener('load', evt => {
        callback(evt.target.response)
    });
    ajaxReq.open('GET', 'api/' + url);
    ajaxReq.responseType = 'json';
    ajaxReq.send();
}

function loadProductsCallback(responseData) {

    currentStart += 4;

    var totalCount = responseData.totalCount;
    var items = responseData.items;

    var template = document.querySelector('#itemList').innerHTML;
    var containers = document.querySelectorAll('.lst_event_box');

    for (var i = 0, len = items.length; i < len; ++i) {
        containers[i % 2].innerHTML += template
                                                .replace('{productImageUrl}', items[i].productImageUrl)
                                                .replace(/{description}/g, items[i].productDescription)
                                                .replace('{id}', items[i].productId)
                                                .replace('{placeName}', items[i].placeName)
                                                .replace('{content}', items[i].productContent);
    }

    var moreViewbtn = document.querySelector('.btn');

    // 더이상 보여줄 컨텐츠가 없을 땐 더보기 버튼 비활성화
    if (currentStart >= totalCount) {
        moreViewbtn.style.display = 'none';
    } else {
        moreViewbtn.style.display = 'initial';
    }

    document.querySelector('.pink').innerText = totalCount + '개';
}

function setPromotionSlideAnimation() {
    var promotionList = document.querySelectorAll('.visual_img > .item');
    var leftDistance = 0;
    var promotionLength = promotionList.length;
    var curIdx = 0;

    // 4초간격으로 슬라이딩
    setInterval(moveStep, 4000);

    function moveStep() {

        leftDistance -= 100;

        for (var i = 0; i < promotionLength; ++i) {
            promotionList[i].style.left = leftDistance + '%';
        }

        curIdx++;

        // 마지막 슬라이드 옮기고 초기화
        if (curIdx >= promotionLength) {

            setTimeout(initPromotionPos, 0);

            function initPromotionPos() {
                for (var i = 0; i < promotionLength; i++) {
                    promotionList[i].style.transitionDuration = '0s';
                    promotionList[i].style.left = '0';
                }
                for (var i = 0; i < promotionLength; i++) {
                    promotionList[i].style.transitionDuration = '1s';
                }

                curIdx = 0;
                leftDistance = 0;
            }
        }

    }
}

function setTabButton() {
    document.querySelector('.tab_lst_min').addEventListener('click', (btnEvent) => {
        var selectedTab = event.target;

        if (selectedTab.tagName === 'SPAN') {
            selectedTab = selectedTab.parentElement;
        }

        if (selectedTab.tagName === 'A') {
            var categoryId = selectedTab.parentElement.getAttribute('data-category');

            // 다른 탭 클릭 이벤트 발생 시에만 동작
            if (categoryId != currentCategory) {
                currentCategory = categoryId;
                currentStart = 0;

                document.querySelectorAll('a.anchor').forEach(element => element.classList.remove('active'));
                selectedTab.classList.add('active');

                document.querySelectorAll('.lst_event_box').forEach(element => element.innerHTML = '');
                requestAjax(loadProductsCallback, mapProductParameters(currentCategory, currentStart));

            }
        }
    });

}

function loadPromotionsCallback(responseData) {
    var items = responseData.items;

    var template = document.querySelector('#promotionItem').innerHTML;
    var containers = document.querySelector('.visual_img');

    for (var i = 0, len = items.length; i < len; ++i) {
        containers.innerHTML += template
            .replace(/{productImageUrl}/g, items[i].productImageUrl);
    }

    setPromotionSlideAnimation();
}

function mapProductParameters(categoryId, start) {
    return 'products?categoryId=' + categoryId + '&start=' + start;
}

document.addEventListener('DOMContentLoaded', function () {
    // 초기 설정

    // 기본 Products 가져오기
    requestAjax(loadProductsCallback, mapProductParameters(0, 0));

    // 더보기 버튼 클릭시 비동기로 추가적인 Products 가져오기
    document.querySelector('.btn').addEventListener('click', () => {
        requestAjax(loadProductsCallback, mapProductParameters(currentCategory, currentStart))
    });

    // 기본 Promotion 무한 슬라이딩 동작
    requestAjax(loadPromotionsCallback, 'promotions');

    // 카테고리 버튼 이벤트 등록
    setTabButton();
});

