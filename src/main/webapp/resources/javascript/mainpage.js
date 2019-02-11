
document.addEventListener('DOMContentLoaded', function(){
	
	basicSettings();
	document.getElementById('moreButton').addEventListener('click', e=>moreButtonListener(e));
	categoryClickEvent();
	
});

let currentCategory = 0;
let isClickedAnotherCategory = false;
const LIMIT = 4;
const GET = 'GET';

/**
 * DOM content가 load된 후 실행될 초기 설정
 */
function basicSettings(){
	ajax(addProductTemplate, GET, "/api/products?categoryId=0");
	ajax(getPromotions, GET, "/api/promotions");
	ajax(showCategories, GET, "/api/categories");
}

/**
 * ajax 통신
 * @param perform load시 실행될 함수
 * @param method 호출 방식 (GET, POST, PUT, DELETE, ...)
 * @param url 호출할 api url
 */
function ajax(perform, method, url) {
	let oReq = new XMLHttpRequest();
	oReq.addEventListener('load', (e)=>{
		let data = JSON.parse(e.currentTarget.responseText);
		perform(data);
	});

	oReq.open(method, url);
	oReq.send();
}
/****************************************/
/*****************product****************/
/****************************************/
/**
 * 더보기 버튼 클릭 시 ajax통신을 통해 product를 받아옴
 */
function moreButtonListener(e){
	let buttonValue = parseInt(e.target.dataset.page);
	let categoryCount = parseInt(document.getElementById('categoryCount').innerText);
	
	let categoryId = currentCategory;
	let url = `/api/products?categoryId=${categoryId}&start=${buttonValue}`;
	
	ajax(addProductTemplate, GET, url);
	
	if(buttonValue < categoryCount - LIMIT){
		buttonValue += LIMIT;
		e.target.dataset.page = buttonValue; 
	}else{
		removeMoreButton();
	}
}

/**
 * template(HTML)에 product정보를 넣은 후 정해진 위치의 DOM에 추가 
 * @param data AJAX로부터 받아온 product data(JSON)
 */
function addProductTemplate(data){
	if(isClickedAnotherCategory){
		document.getElementById('productBox1').innerHTML = "";
		document.getElementById('productBox2').innerHTML = "";
		document.getElementById('moreButton').dataset.page = LIMIT;
		isClickedAnotherCategory = false;
		document.getElementById('moreButton').hidden = false;
	}

	let products = data.items;
	products.forEach((product, index)=>{

		let resultHtml = productTemplate.replace('{id}', product.displayInfoId)
					.replace('{placeName}', product.placeName)
					.replace('{content}', product.productContent)
					.replace(/{description}/g, product.productDescription)
					.replace('{productImageUrl}', product.productImageUrl);

		if(index % 2 == 0){
			document.getElementById('productBox1').innerHTML += resultHtml;
		}else{
			document.getElementById('productBox2').innerHTML += resultHtml;
		}
	});
	
	let categoryCount = data.totalCount;
	document.getElementById("categoryCount").textContent = categoryCount;
}

function removeMoreButton(){
	document.getElementById('moreButton').hidden = true;
}

/****************************************/
/***************category*****************/
/****************************************/
/**
 * template(HTML)에 category정보를 넣은 후 정해진 위치의 DOM에 추가 
 * @param data AJAX로부터 받아온 category data(JSON)
 */
function showCategories(data){
	let items = data.items;
	items.forEach(function(category){
		let categoryId = category.id;
		let categoryName = category.name;
		let script = categoryTemplate.replace('{categoryId}', categoryId)
									.replace('{categoryName}', categoryName);
		document.getElementById('category').innerHTML += script;
	});
}

/**
 * category 클릭 시 발생하는 event (Active a tag, change current category, load product list)
 */
function categoryClickEvent(){
	document.getElementById('category').addEventListener('click', e=>{
		let clickedTag = e.target.tagName;

		let clickedCategory;
		if(clickedTag == 'UL'){
			return;
		}else if(clickedTag == 'LI'){
			removeActiveCategory();
			e.target.lastElementChild.className = 'anchor active';
			clickedCategory = e.target.dataset.category;
		}else if(clickedTag == 'A'){
			removeActiveCategory();
			e.target.className = 'anchor active';
			clickedCategory = e.target.parentElement.dataset.category;
		}else if(clickedTag == 'SPAN'){
			removeActiveCategory();
			e.target.parentNode.className = 'anchor active';
			clickedCategory= e.target.parentElement.parentElement.dataset.category;
		}
		isClickedAnotherCategory = true;
		currentCategory = clickedCategory;
		let url = `/api/products?categoryId=${clickedCategory}`;
		ajax(addProductTemplate, GET, url);
	});
}
/**
 * category의 활성화(anchor) class 제거
 */
function removeActiveCategory(){
	Array.from(document.getElementsByClassName('active')).forEach(tag=>{
		tag.className = 'anchor';
	});
}

/****************************************/
/***************promotion****************/
/****************************************/
/**
 * template(HTML)에 promotion정보를 넣은 후 정해진 위치의 DOM에 추가 
 * @param data AJAX로부터 받아온 promotion data(JSON)
 */
function getPromotions(data){
	let promotionsHtml = "";
	let promotions = data.items;
	promotions.forEach(promotion=>{
		promotionsHtml += promotionTemplate.replace('{productImageUrl}', promotion.productImageUrl);
	});
	document.getElementById('slide').innerHTML += promotionsHtml;
	
	promotionSlide(promotions.length);
}

/**
 * promotion이 carousel형태로 slide 
 * @param promotionCount slide형태로 출력될 promotion의 개수
 */
function promotionSlide(promotionCount){
	let slideUl = document.querySelector('#slide');
	const duration = 2 * 1000;

	for(let i = 0; i <= promotionCount; i++){
		let temp = i * -100;
		setTimeout(()=>{
			if(i != promotionCount){
				slideUl.style.transform = `translateX(${temp}%)`;
			}else{
				slideUl.style.transform = 'translateX(0%)';
				promotionSlide(promotionCount);
			}
		}, i * duration);
	}
}



/****************************************/
/****************template****************/
/****************************************/
const productTemplate = `<li class="item">
	<a href="detail.html?id={id}" class="item_book">
		<div class="item_preview">
			<img alt="{description}" class="img_thumb" src="/resources/img/product/{productImageUrl}">
			<span class="img_border"></span>
		</div>
		<div class="event_txt">
			<h4 class="event_txt_tit"> <span>{description}</span> <small class="sm">{placeName}</small> </h4>
			<p class="event_txt_dsc">{content}</p>
		</div>
	</a>
</li>`;

const categoryTemplate = `<li class="item" data-category="{categoryId}">
					<a class="anchor"><span>{categoryName}</span></a>
				</li>`;

const promotionTemplate = `<li><img src="/resources/img/product/{productImageUrl}"/></li>`;