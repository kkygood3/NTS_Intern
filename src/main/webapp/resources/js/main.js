document.addEventListener("DOMContentLoaded", function() {
    requestCategories();
    requestProducts();
    requestPromotions();
    initMoreBtn();
})
var promotionAnimationRequestId;
window.addEventListener("blur", function(){
    cancelAnimationFrame(promotionAnimationRequestId);
})
window.addEventListener("focus", function(){
    requestAnimationFrame(updatePromotionAnimation);
})
function sendGet(path, params, onCallback) {
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

function requestCategories() {
    sendGet("/reservation-service/api/categories", null, function(response){
        if(response.status == 200){
            var data = JSON.parse(response.responseText);
            updateCategories(data.items);
        }else {
            alert("카테고리를 불러오는데 실패했습니다.");
        }
    })  
}

function updateCategories(categories) {
    var container = document.querySelector(".event_tab_lst");
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
    container.innerHTML = resultTemplate;
    container.dataset.selected = 0;
    container.dataset.currentCount = 0;

    var eventListText = document.querySelector(".event_lst_txt > span");
    eventListText.textContent = sum + "개";   

    addCategoryEventListner();
}

function addCategoryEventListner() {
    var container = document.querySelector(".event_tab_lst");
    container.addEventListener("click", function (event) {
        var liElement;
        var aElement;
        if (event.target.tagName == "A") {
            aElement = event.target;
            liElement = event.target.parentNode;
        }else if(event.target.tagName == "SPAN"){
            var aElement = event.target.parentNode;
            var liElement = aElement.parentNode;
        }else{
            return;
        }

        if(container.dataset.selected == liElement.dataset.category){
            return;
        }else{
            container.dataset.selected = liElement.dataset.category;
            var deselectedElement = container.querySelector(".active");
            deselectedElement.classList.remove("active");
            aElement.classList.add("active");
            var eventListText = document.querySelector(".event_lst_txt > span");
            eventListText.textContent = liElement.dataset.totalCount + "개";
            container.dataset.currentCount = 0;

            var productContainers = document.getElementsByClassName("lst_event_box");
            for(var i = 0; i < productContainers.length; i++){
                productContainers[i].innerHTML = "";
            }
            requestProducts(0, container.dataset.selected == 0 ? null : container.dataset.selected);
        }
    }, false);
}
function requestProducts(start, categoryId) {
    var params = {};
    if(start) {
        params.start = start;
    }
    if(categoryId) {
        params.categoryId = categoryId;
    }
    sendGet("/reservation-service/api/products",params, function(response) {
        if(response.status == 200){
            var data = JSON.parse(response.responseText);
            updateProductList(data.items, data.totalCount);
        }else {
            alert("상품 목록을 불러오는데 실패했습니다.");
        }
    })
}
function updateProductList(products, totalCount){
    var tabContainer = document.querySelector(".event_tab_lst");
    tabContainer.dataset.currentTotalCount = totalCount;
    tabContainer.dataset.currentCount = Number(tabContainer.dataset.currentCount) + products.length;
    var moreBtn = document.querySelector(".btn");
    moreBtn.hidden = Number(tabContainer.dataset.currentTotalCount) <= Number(tabContainer.dataset.currentCount);

    var containers = document.getElementsByClassName("lst_event_box");
    var template = document.querySelector("#itemList").innerHTML;
    var resultTemplate = ["", ""];
    for(var i = 0; i < products.length; i++){
        var product = products[i];
        resultTemplate[i%2] += template.replace("{id}", product.displayInfoId).replace("{description}", product.productDescription)
                                    .replace("{image_url}", "./" + product.productImageUrl).replace("{placeName}", product.placeName)
                                    .replace("{content}", product.productContent).replace("{description}", product.productDescription);
    }
    for(var i = 0; i < containers.length; i++){
        containers[i].innerHTML += resultTemplate[i];
    }
}


function requestPromotions() {
    sendGet("/reservation-service/api/promotions", null, function(response){
        if(response.status == 200){
            var data = JSON.parse(response.responseText);
            updatePromtions(data.items);
        }else {
            alert("프로모션 상품들을 불러오는데 실패했습니다.");
        }
    })
}

function updatePromtions(promotions) {
    var promotionContainer = document.querySelector(".visual_img");
    var template = document.querySelector("#promotionItem").innerHTML;
    var resultTemplate = "";
    for(var i = 0; i < promotions.length; i++){
        var promotion = promotions[i];
        resultTemplate += template.replace("{image_url}", "./" + promotion.productImageUrl)
                                    .replace("{index}", i);
    }
    promotionContainer.innerHTML = resultTemplate;
    
    promotionContainer.dataset.current = 0;

    initPromotionStyle(promotionContainer);
    executionTime = new Date().getTime();
    updatePromotionAnimation()
}
function initPromotionStyle(container){
    var childNodes = container.children;
    childNodes[0].className = "item current_promotion";
    for(var i = 1; i < childNodes.length; i++){
        childNodes[i].className = "item prev_promotion";
    }
}

var executionTime;
function updatePromotionAnimation(){
    var now = new Date().getTime();
    
    if((now - executionTime) >= 3000){
        var container = document.querySelector(".visual_img");
        var childNodes = container.children;
        var length = childNodes.length;
        var current = Number(container.dataset.current);
        var next = (current + 1) % childNodes.length;
        var prev = (current - 1 + childNodes.length) % childNodes.length;
        
        childNodes[current].className = "item next_promotion";
        childNodes[next].className = "item current_promotion";
        childNodes[prev].className = "item prev_promotion";

        container.dataset.current = next;

        executionTime = new Date().getTime();
    }

    promotionAnimationRequestId = requestAnimationFrame(updatePromotionAnimation);
}
function initMoreBtn() {
    var moreBtn = document.querySelector(".btn");
    moreBtn.addEventListener("click", function() {
        var container = document.querySelector(".event_tab_lst");
        requestProducts(container.dataset.currentCount, container.dataset.selected == 0 ? null : container.dataset.selected);
    })
}
