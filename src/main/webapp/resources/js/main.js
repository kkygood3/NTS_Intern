/**
 * @author 육성렬
 */

document.addEventListener("DOMContentLoaded", function() {
    mainPage.init();
});

var mainPage = {

    /**
     * @function init 페이지 Content 로드 완료시 초기 설정 함수.
     */
    init: function() {
        this.requestProducts();
        this.requestCategories();
        this.requestPromotions();
        this.initMoreBtn();
    },

    values: {
        /**
         * @val {number} 프로모션 애니메이션 tick을 위한 값
         */
        executionTime: 0,
        /**
         * @val {number} 프로모션 애니메이션 requestAnimationFrame 시의 아이디값
         */
        promotionAnimationRequestId: 0,
        PROMOTION_ANIMATION_TICK: 3000
    },

    elements: {
        tabContainer: document.querySelector(".event_tab_lst"),
        moreBtn: document.querySelector(".btn"),
        productContainers: document.querySelectorAll(".lst_event_box"),
        promotionContainer: document.querySelector(".visual_img")
    },

    /**
     * @function initMoreBtn 더 보기 버튼 초기 설정 함수.
     */
    initMoreBtn: function() {
        
        var moreBtn = this.elements.moreBtn;
        var self = this;
        moreBtn.addEventListener( "click", function() {
            var tabContainer = self.elements.tabContainer;
            this.requestProducts(
                tabContainer.dataset.currentCount,
                tabContainer.dataset.selectedTabIndex == 0 ? null : tabContainer.dataset.selectedTabIndex
            );
        });
    },

    /**
     * @function requestProducts 프로덕트 정보 요청 함수.
     * @param {Number}
     *            start 시작 index
     * @param {Number}
     *            categoryId 요청 카테고리, 전체보기 시 null.
     */
    requestProducts: function(start, categoryId) {
        var params = {};
        if (start) {
            params.start = start;
        }
        if (categoryId) {
            params.categoryId = categoryId;
        }
        
        var options = {
            params: params
        }

        var self = this;
        httpUtil.sendGet( "/api/products", options, function(response) {
            self.requestProductsCallback(response);
        });
    },

    /**
     * @function requestCategories 카테고리 정보 요청
     */
    requestCategories: function() {

        var self = this;
        httpUtil.sendGet( "/api/categories", {}, function(response) {
            self.requestCategoriesCallback(response);
        });
    },

    /**
     * @function requestPromotions 프로모션 상품 요청 함수
     */
    requestPromotions: function() {

        var self = this;
        httpUtil.sendGet( "/api/promotions", {}, function(response) {
            self.requestPromotionsCallback(response);
        });
    },

    /**
     * @function responseProducts 프로덕트 정보 요청에 대한 콜백 함수.
     * @param {XMLHttpRequest}
     *            response
     */
    requestProductsCallback: function(response) {
        if (response.status == 200) {
            var data = JSON.parse(response.responseText);
            this.updateProductList(data.items, data.totalCount);
        } else {
            alert("상품 목록을 불러오는데 실패했습니다.");
        }
    },

    /**
     * @function responseCategories 카테고리 정보 요청에 대한 콜백 함수.
     * @param {XMLHttpRequest}
     *            response
     */
    requestCategoriesCallback: function(response) {
        if (response.status == 200) {
            var data = JSON.parse(response.responseText);
            this.updateCategories(data.items);
        } else {
            alert("카테고리를 불러오는데 실패했습니다.");
        }
    },

    /**
     * @function responsePromotions 프로모션 정보 요청에 대한 콜백 함수.
     * @param {XMLHttpRequest}
     *            response
     */
    requestPromotionsCallback: function(response) {
        if (response.status == 200) {
            var data = JSON.parse(response.responseText);
            this.updatePromtions(data.items);
        } else {
            alert("프로모션 상품들을 불러오는데 실패했습니다.");
        }
    },

    /**
     * @function updateProductList 상품 정보에 대한 상품 리스트 UI 갱신 함수.
     * @param {JSON}
     *            products 상품 리스트
     * @param {Number}
     *            totalCount 해당 카테고리의 전체 상품 갯수. (products와 갯수 일치 X)
     */
    updateProductList: function(products, totalCount) {

        var tabContainer = this.elements.tabContainer;
        tabContainer.dataset.currentTotalCount = totalCount;
        tabContainer.dataset.currentCount = Number(tabContainer.dataset.currentCount) + products.length;
        this.elements.moreBtn.hidden =
            Number(tabContainer.dataset.currentTotalCount) <= Number(tabContainer.dataset.currentCount);

        var productContainers = this.elements.productContainers;
        var template = document.querySelector("#itemList").innerHTML;
        var column = productContainers.length;

        for (var i = 0; i < products.length; i++) {
            var product = products[i];
            var params = {
                id: product.displayInfoId,
                description: product.productDescription,
                image_url: "/api/download/img?imageName=" + product.productImageUrl,
                placeName: product.placeName,
                content: product.productContent,
                description2: product.productDescription
            };
            productContainers[i % column].innerHTML += this.replaceTemplate(template, params);
        }
    },

    /**
     * @function updateCategories 카테고리 정보에 대한 카테고리 UI 갱신 함수.
     * @param {JSON[]}
     *            categories 카테고리 리스트
     */
    updateCategories: function(categories) {
        var template = document.querySelector("#categoryItem").innerHTML;
        var resultHtml = "";
        var tabContainer = this.elements.tabContainer;
        var sum = 0;
        var params = {};

        categories.forEach(
            function(category) {
                sum += category.count;
                params = {
                    categoryId: category.id,
                    count: category.count,
                    name: category.name,
                    active: ""
                };
                resultHtml += this.replaceTemplate(template, params);
            }.bind(this)
        );

        params = {
            categoryId: 0,
            count: sum,
            name: "전체리스트",
            active: "active"
        };

        resultHtml = this.replaceTemplate(template, params) + resultHtml;
        tabContainer.innerHTML = resultHtml;
        tabContainer.dataset.selectedTabIndex = 0;
        tabContainer.dataset.currentCount = 0;

        var eventListText = document.querySelector(".event_lst_txt > span");
        eventListText.textContent = sum + "개";

        this.addCategoryEventListner();
    },

    /**
     * @function updatePromtions 프로모션 정보에 대한 프로모션 UI 갱신 함수.
     * @param {JSON[]}
     *            promotions 프로모션 리스트
     */
    updatePromtions: function(promotions) {

        var promotionContainer = this.elements.promotionContainer;
        var template = document.querySelector("#promotionItem").innerHTML;
        var resultHtml = "";

        var self = this;
        promotions.forEach( function(promotion, index) {

            var params = {
                image_url: "/api/download/img?imageName=" + promotion.productImageUrl,
                index: index,
                place: promotion.placeName,
                description: promotion.productDescription
            };

            resultHtml += self.replaceTemplate(template, params);
        });

        promotionContainer.innerHTML = resultHtml;
        promotionContainer.dataset.current = 0;

        this.setupClassNameForPromotionAnimation();
        this.values.executionTime = new Date().getTime();
        this.updatePromotionAnimation();
    },

    /**
     * @function setupClassNameForPromotionAnimation 프로모션 UI 애니메이션 시작 전의 사전 세팅
     *           함수.
     */
    setupClassNameForPromotionAnimation: function() {

        var childNodes = this.elements.promotionContainer.children;
        for (var i = 1; i < childNodes.length; i++) {
            childNodes[i].className = "item prev_promotion";
        }
        childNodes[0].className = "item current_promotion";
    },

    /**
     * @function updatePromotionAnimation 프로모션 UI 애니메이션 구동 함수.
     */
    updatePromotionAnimation: function() {
        var now = new Date().getTime();

        if (now - this.values.executionTime >= this.values.PROMOTION_ANIMATION_TICK) {
            var promotionContainer = this.elements.promotionContainer;
            var childNodes = promotionContainer.children;
            var length = childNodes.length;
            var current = Number(promotionContainer.dataset.current);
            var next = (current + 1) % childNodes.length;
            var prev = (current - 1 + childNodes.length) % childNodes.length;

            childNodes[current].className = "item next_promotion";
            childNodes[next].className = "item current_promotion";
            childNodes[prev].className = "item prev_promotion";

            promotionContainer.dataset.current = next;

            this.values.executionTime = new Date().getTime();
        }

        var self = this;
        this.values.promotionAnimationRequestId = requestAnimationFrame( function() {
            self.updatePromotionAnimation();
        });
    },

    /**
     * @function addCategoryEventListner 카테고리 탭 별 이벤트 리스너 추가 함수
     */
    addCategoryEventListner: function() {

        var tabContainer = this.elements.tabContainer;
        var self = this;

        tabContainer.addEventListener( "click", function(event) {
                self.onClickTabContainer(event, tabContainer);
            }, false);
    },

    /**
     * @function onClickTabContainer 카테고리 탭 클릭에 대한 콜백함수
     * @param {MouseEvent} event
     * @param {Element} tabContainer
     */
    onClickTabContainer: function(event, tabContainer) {

        var liElement;
        var aElement;

        if (event.target.tagName == "A") {
            aElement = event.target;
            liElement = event.target.parentNode;
        } else if (event.target.tagName == "SPAN") {
            aElement = event.target.parentNode;
            liElement = aElement.parentNode;
        } else {
            return;
        }

        if (tabContainer.dataset.selectedTabIndex == liElement.dataset.category) {
            return;
        } else {
            tabContainer.dataset.selectedTabIndex = liElement.dataset.category;
            var deselectedElement = tabContainer.querySelector(".active");
            deselectedElement.classList.remove("active");
            aElement.classList.add("active");
            var eventListText = document.querySelector(".event_lst_txt > span");
            eventListText.textContent = liElement.dataset.totalCount + "개";
            tabContainer.dataset.currentCount = 0;

            var productContainers = this.elements.productContainers;
            productContainers.forEach(function(container) {
                container.innerHTML = "";
            });
            this.requestProducts( 0,
                tabContainer.dataset.selectedTabIndex == 0 ? null : tabContainer.dataset.selectedTabIndex
            );
        }
    },
    /**
     * @function replaceTemplate 템플릿에 파라미터의 값들로 치환해주는 함수
     * @param {String}
     *            template
     * @param {JSON}
     *            params
     * @returns 변환 된 템플릿 String 리턴.
     */
    replaceTemplate: function(template, params) {
        for (key in params) {
            template = template.replace("{" + key + "}", params[key]);
        }
        return template;
    }
};

window.addEventListener(
    "blur",
    function() {
        cancelAnimationFrame(this.values.promotionAnimationRequestId);
    }.bind(mainPage)
);

window.addEventListener(
    "focus",
    function() {
        this.values.promotionAnimationRequestId = requestAnimationFrame(
            function() {
                this.updatePromotionAnimation();
            }.bind(this)
        );
    }.bind(mainPage)
);
