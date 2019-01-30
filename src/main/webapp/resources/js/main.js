document.addEventListener("DOMContentLoaded", function() {
    mainPage.init();
})

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
        productContainers : document.getElementsByClassName("lst_event_box"),
        promotionContainer: document.querySelector(".visual_img")
    },
    /**
     * @function initMoreBtn 더 보기 버튼 초기 설정 함수.
     */
    initMoreBtn: function() {
        var moreBtn = this.elements.moreBtn;
        moreBtn.addEventListener("click", function() {
            var tabContainer = this.elements.tabContainer;
            this.requestProducts(tabContainer.dataset.currentCount, tabContainer.dataset.selected == 0 ? null : tabContainer.dataset.selected);
        }.bind(this))
    },
    /**
     * @function requestProducts 프로덕트 정보 요청 함수.
     * @param {Number} start 시작 index
     * @param {Number} categoryId 요청 카테고리, 전체보기 시 null.
     */
    requestProducts:  function (start, categoryId) {
        var params = {};
        if(start) {
            params.start = start;
        }
        if(categoryId) {
            params.categoryId = categoryId;
        }
        sendGet("/reservation-service/api/products",params, function(response) {
            this.responseProducts(response);
        }.bind(this))
    },
    /**
     * @function requestCategories 카테고리 정보 요청
     */
    requestCategories : function() {
        sendGet("/reservation-service/api/categories", null, function(response){
            this.responseCategories(response);
        }.bind(this))
    },
    /**
     * @function requestPromotions 프로모션 상품 요청 함수
     */
    requestPromotions: function () {
        sendGet("/reservation-service/api/promotions", null, function(response){
            this.responsePromotions(response);
        }.bind(this))
    },
    /**
     * @function responseProducts 프로덕트 정보 요청에 대한 콜백 함수.
     * @param {XMLHttpRequest} response
     */
    responseProducts: function (response) {
        if(response.status == 200){
            var data = JSON.parse(response.responseText);
            this.updateProductList(data.items, data.totalCount);
        }else {
            alert("상품 목록을 불러오는데 실패했습니다.");
        }
    },
    /**
     * @function responseCategories 카테고리 정보 요청에 대한 콜백 함수.
     * @param {XMLHttpRequest} response
     */
    responseCategories: function (response){
        if(response.status == 200){
            var data = JSON.parse(response.responseText);
            this.updateCategories(data.items);
        }else {
            alert("카테고리를 불러오는데 실패했습니다.");
        }
    },
    /**
     * @function responsePromotions 프로모션 정보 요청에 대한 콜백 함수.
     * @param {XMLHttpRequest} response
     */
    responsePromotions: function (response){
        if(response.status == 200){
            var data = JSON.parse(response.responseText);
            this.updatePromtions(data.items);
        }else {
            alert("프로모션 상품들을 불러오는데 실패했습니다.");
        }
    },
    /**
     * @function updateProductList 상품 정보에 대한 상품 리스트 UI 갱신 함수.
     * @param {JSON} products 상품 리스트
     * @param {Number} totalCount 해당 카테고리의 전체 상품 갯수. (products와 갯수 일치 X)
     */
    updateProductList : function (products, totalCount){
        var tabContainer = this.elements.tabContainer;
        tabContainer.dataset.currentTotalCount = totalCount;
        tabContainer.dataset.currentCount = Number(tabContainer.dataset.currentCount) + products.length;
        this.elements.moreBtn.hidden = Number(tabContainer.dataset.currentTotalCount) <= Number(tabContainer.dataset.currentCount);
    
        var productContainers = this.elements.productContainers;
        var template = document.querySelector("#itemList").innerHTML;
        var column = productContainers.length;

        for(var i = 0; i < products.length; i++){
            var product = products[i];
            var params = {
                id: product.displayInfoId,
                description:  product.productDescription,
                image_url: "./" + product.productImageUrl,
                placeName: product.productDescription,
                content: product.productContent,
                description2: product.productDescription
            }
            productContainers[i%column].innerHTML += this.replaceTemlate(template, params)
        }
    },
    /**
     * @function updateCategories 카테고리 정보에 대한 카테고리 UI 갱신 함수.
     * @param {JSON} categories 카테고리 리스트
     */
    updateCategories: function (categories) {
        var template = document.querySelector("#categoryItem").innerHTML;
        var resultTemplate = "";
        var tabContainer = this.elements.tabContainer;
        var sum = 0;
        var params = {};
        for(var i = 0; i < categories.length; i++){
            sum += categories[i].count;
            params = {
                categoryId: categories[i].id,
                count: categories[i].count,
                name: categories[i].name,
                active: ""
            }
            resultTemplate += this.replaceTemlate(template, params)
        }
        params = {
            categoryId: 0,
            count: sum,
            name: "전체리스트",
            active: "active"
        }
        resultTemplate = this.replaceTemlate(template, params) + resultTemplate;
        tabContainer.innerHTML = resultTemplate;
        tabContainer.dataset.selected = 0;
        tabContainer.dataset.currentCount = 0;
    
        var eventListText = document.querySelector(".event_lst_txt > span");
        eventListText.textContent = sum + "개";   
    
        this.addCategoryEventListner();
    },
    /**
     * @function updatePromtions 프로모션 정보에 대한 프로모션 UI 갱신 함수.
     * @param {JSON} promotions 프로모션 리스트
     */
    updatePromtions: function (promotions) {
        var promotionContainer = this.elements.promotionContainer;
        var template = document.querySelector("#promotionItem").innerHTML;
        var resultTemplate = "";
        for(var i = 0; i < promotions.length; i++){
            var promotion = promotions[i];
            var params = {
                image_url: "./" + promotion.productImageUrl,
                index: i
            }
            resultTemplate += this.replaceTemlate(template, params);
        }
        promotionContainer.innerHTML = resultTemplate;
        promotionContainer.dataset.current = 0;
    
        this.preSettingStyleForPromotionAnimation();
        this.values.executionTime = new Date().getTime();
        this.updatePromotionAnimation();
    },
    /**
     * @function preSettingStyleForPromotionAnimation 프로모션 UI 애니메이션 시작 전의 사전 세팅 함수.
     */
    preSettingStyleForPromotionAnimation: function (){
        var childNodes = this.elements.promotionContainer.children;
        childNodes[0].className = "item current_promotion";
        for(var i = 1; i < childNodes.length; i++){
            childNodes[i].className = "item prev_promotion";
        }
    },
    /**
     * @function updatePromotionAnimation 프로모션 UI 애니메이션 구동 함수.
     */
    updatePromotionAnimation: function (){
        var now = new Date().getTime();
        
        if((now - this.values.executionTime) >= this.values.PROMOTION_ANIMATION_TICK){
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
    
        this.values.promotionAnimationRequestId = requestAnimationFrame(function() {
            this.updatePromotionAnimation()
        }.bind(this));
    },
    /**
     * @function addCategoryEventListner 카테고리 탭 별 이벤트 리스너 추가 함수
     */
    addCategoryEventListner: function () {
        var tabContainer = this.elements.tabContainer;
        tabContainer.addEventListener("click", function (event) {
            var liElement;
            var aElement;
            if (event.target.tagName == "A") {
                aElement = event.target;
                liElement = event.target.parentNode;
            }else if(event.target.tagName == "SPAN"){
                aElement = event.target.parentNode;
                liElement = aElement.parentNode;
            }else{
                return;
            }
    
            if(tabContainer.dataset.selected == liElement.dataset.category){
                return;
            }else{
                tabContainer.dataset.selected = liElement.dataset.category;
                var deselectedElement = tabContainer.querySelector(".active");
                deselectedElement.classList.remove("active");
                aElement.classList.add("active");
                var eventListText = document.querySelector(".event_lst_txt > span");
                eventListText.textContent = liElement.dataset.totalCount + "개";
                tabContainer.dataset.currentCount = 0;
    
                var productContainers = this.elements.productContainers;
                for(var i = 0; i < productContainers.length; i++){
                    productContainers[i].innerHTML = "";
                }
                this.requestProducts(0, tabContainer.dataset.selected == 0 ? null : tabContainer.dataset.selected);
            }
        }.bind(this), false);
    },
    /**
     * @function replaceTemlate 템플릿에 파라미터의 값들로 치환해주는 함수
     * @param {String} template 
     * @param {JSON} params 
     * @returns 변환 된 템플릿 String 리턴.
     */
    replaceTemlate: function (template, params){
        for( key in params){
            template = template.replace("{" + key + "}", params[key]);
        }
        return template;
    }
}

window.addEventListener("blur", function(){
    cancelAnimationFrame(this.values.promotionAnimationRequestId);
}.bind(mainPage))

window.addEventListener("focus", function(){
    this.values.promotionAnimationRequestId = requestAnimationFrame(function() {
        this.updatePromotionAnimation()
    }.bind(this));
}.bind(mainPage))