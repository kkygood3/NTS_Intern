document.addEventListener("DOMContentLoaded", function() {
    mainPage.init();
})

var mainPage = {
    init: function() {
        this.requestProducts();
        this.requestCategories();
        this.requestPromotions();
        this.initMoreBtn();
    },
    values: {
        executionTime: 0,
        promotionAnimationRequestId: 0
    },
    elements: {
        tabContainer: document.querySelector(".event_tab_lst"),
        moreBtn: document.querySelector(".btn"),
        productContainers : document.getElementsByClassName("lst_event_box"),
        promotionContainer: document.querySelector(".visual_img")
    },
    initMoreBtn: function() {
        var moreBtn = this.elements.moreBtn;
        moreBtn.addEventListener("click", function() {
            var tabContainer = this.elements.tabContainer;
            this.requestProducts(tabContainer.dataset.currentCount, tabContainer.dataset.selected == 0 ? null : tabContainer.dataset.selected);
        }.bind(this))
    },
    requestProducts:  function (start, categoryId) {
        var params = {};
        if(start) {
            params.start = start;
        }
        if(categoryId) {
            params.categoryId = categoryId;
        }
        console.log("test 2");
        console.log(this);
        this.httpMethod.sendGet("/reservation-service/api/products",params, function(response) {
            this.responseProduct(response);
        }.bind(this))
    },
    requestCategories : function() {
        console.log("test");
        console.log(this);
        this.httpMethod.sendGet("/reservation-service/api/categories", null, function(response){
            this.responseCategories(response);
        }.bind(this))
    },
    requestPromotions: function () {
        this.httpMethod.sendGet("/reservation-service/api/promotions", null, function(response){
            this.responsePromotions(response);
        }.bind(this))
    },
    responseProduct: function (response) {
        console.log("check point 00");
        console.log(this);
        if(response.status == 200){
            var data = JSON.parse(response.responseText);
            this.updateProductList(data.items, data.totalCount);
        }else {
            alert("상품 목록을 불러오는데 실패했습니다.");
        }
    },
    responseCategories: function (response){
        console.log("check point 01");
        console.log(this);
        if(response.status == 200){
            var data = JSON.parse(response.responseText);
            this.updateCategories(data.items);
        }else {
            alert("카테고리를 불러오는데 실패했습니다.");
        }
    },
    responsePromotions: function (response){
        if(response.status == 200){
            var data = JSON.parse(response.responseText);
            this.updatePromtions(data.items);
        }else {
            alert("프로모션 상품들을 불러오는데 실패했습니다.");
        }
    },
    updateProductList : function (products, totalCount){
        var tabContainer = this.elements.tabContainer;
        tabContainer.dataset.currentTotalCount = totalCount;
        tabContainer.dataset.currentCount = Number(tabContainer.dataset.currentCount) + products.length;
        this.elements.moreBtn.hidden = Number(tabContainer.dataset.currentTotalCount) <= Number(tabContainer.dataset.currentCount);
    
        var productContainers = this.elements.productContainers;
        var template = document.querySelector("#itemList").innerHTML;
        var resultTemplate = ["", ""];
        for(var i = 0; i < products.length; i++){
            var product = products[i];
            resultTemplate[i%2] += template.replace("{id}", product.displayInfoId).replace("{description}", product.productDescription)
                                        .replace("{image_url}", "./" + product.productImageUrl).replace("{placeName}", product.placeName)
                                        .replace("{content}", product.productContent).replace("{description}", product.productDescription);
        }
        for(var i = 0; i < productContainers.length; i++){
            productContainers[i].innerHTML += resultTemplate[i];
        }
    },
    updateCategories: function (categories) {
        var template = document.querySelector("#categoryItem").innerHTML;
        var resultTemplate = "";
        var sum = 0;
        for(var i = 0; i < categories.length; i++){
            sum += categories[i].count;
            resultTemplate += template.replace("{categoryId}", categories[i].id).replace("{count}", categories[i].count)
                                    .replace("{name}", categories[i].name).replace("{active}", "");
        }
        resultTemplate = template.replace("{categoryId}", 0).replace("{count}", sum)
                                    .replace("{name}", "전체리스트").replace("{active}", "active") + resultTemplate;
        this.elements.tabContainer.innerHTML = resultTemplate;
        this.elements.tabContainer.dataset.selected = 0;
        this.elements.tabContainer.dataset.currentCount = 0;
    
        var eventListText = document.querySelector(".event_lst_txt > span");
        eventListText.textContent = sum + "개";   
    
        this.addCategoryEventListner();
    },
    updatePromtions: function (promotions) {
        var promotionContainer = this.elements.promotionContainer;
        var template = document.querySelector("#promotionItem").innerHTML;
        var resultTemplate = "";
        for(var i = 0; i < promotions.length; i++){
            var promotion = promotions[i];
            resultTemplate += template.replace("{image_url}", "./" + promotion.productImageUrl)
                                        .replace("{index}", i);
        }
        promotionContainer.innerHTML = resultTemplate;
        
        promotionContainer.dataset.current = 0;
    
        this.settingPromotionStyle();
        this.values.executionTime = new Date().getTime();
        this.updatePromotionAnimation();
    },
    settingPromotionStyle: function (){
        var childNodes = this.elements.promotionContainer.children;
        childNodes[0].className = "item current_promotion";
        for(var i = 1; i < childNodes.length; i++){
            childNodes[i].className = "item prev_promotion";
        }
    },
    updatePromotionAnimation: function (){
        var now = new Date().getTime();
        
        if((now - this.values.executionTime) >= 3000){
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
    httpMethod: {
        sendGet: function (path, params, onCallback) {
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
    },
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
                console.log("check point 02")
                console.log(this);
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
    }
}

window.addEventListener("blur", function(){
    console.log("check point blur")
    console.log(this)
    console.log(this.promotionAnimationRequestId);
    cancelAnimationFrame(this.values.promotionAnimationRequestId);
}.bind(mainPage))

window.addEventListener("focus", function(){
    console.log("check point focus")
    console.log(this)
    console.log(this.promotionAnimationRequestId);
    this.values.promotionAnimationRequestId = requestAnimationFrame(function() {
        this.updatePromotionAnimation()
    }.bind(this));
}.bind(mainPage))