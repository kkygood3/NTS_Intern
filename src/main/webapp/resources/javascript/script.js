
document.addEventListener('DOMContentLoaded', function(){
	
	basicSettings();
	document.getElementById('moreButton').addEventListener('click', e=>moreButtonListener(e));
	categoryClickEvent();
	
});

let currentCategory = 0;
let isClickedAnotherCategory = false;
const LIMIT = 4;
const GET = 'GET';

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


function basicSettings(){
	ajax(addProductTemplate, GET, "/products?categoryId=0");
	ajax(getPromotions, GET, "/promotions");
	ajax(showCategories, GET, "/categories");
}

// slide CSS
function slidePromotion(count){
	let promotionSlide = document.getElementById('slide');
	let slideWidth = promotionSlide.offsetWidth;
	let promotionCount = count;
	let duration = promotionCount * 2;
	
	let slideCss = document.createElement('style');
	slideCss.type = 'text/css';
	
	cssScript = '100% {right: 0px;}';
	for(let i = 0; i < promotionCount; i++){
		cssScript += `${(i / promotionCount) * 100}% {right: ${slideWidth * i}px}`;
	}
	slideCss.innerHTML = `.visual_img { padding: 0px;
		margin: 0px;
		list-style: none;
		height: 177px;
		display: flex;
		overflow: hidden; }
	
	@keyframes slide{${cssScript}}

	.visual_img li img {
		width: ${slideWidth}px;
		position: relative;
		animation-name: slide;
		animation-duration: ${duration}s;
		animation-iteration-count: infinite;
	}`;
	document.body.appendChild(slideCss);
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
 * @desc 더보기 버트 클릭 시 ajax통신을 통해 product를 받아옴
 * @param e
 * @returns
 */
function moreButtonListener(e){
	let buttonValue = parseInt(e.target.dataset.page);
	let categoryCount = parseInt(document.getElementById('categoryCount').innerText);
	
	let categoryId = currentCategory;
	let url = `/products?categoryId=${categoryId}&start=${buttonValue}`;
	let param;
	
	ajax(addProductTemplate, GET, url);
	
	if(buttonValue < categoryCount - LIMIT){
		buttonValue += LIMIT;
		e.target.dataset.page = buttonValue; 
	}else{
		removeMoreButton();
	}
}

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
					.replace(/{description}/gi, product.productDescription)
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
 * category 클릭 시 발생하는 event 
 * @returns
 */
function categoryClickEvent(){
	document.getElementById('category').addEventListener('click', e=>{
		let clickedTag = e.target.tagName;
		
		let clickedCategory;
		if(clickedTag == 'A'){
			removeActiveCategory();
			e.target.className = 'anchor active';
			clickedCategory = e.target.parentElement.dataset.category;
		}else if(clickedTag == 'SPAN'){
			removeActiveCategory();
			e.target.parentNode.className = 'anchor active';
			clickedCategory= e.target.parentElement.parentElement.dataset.category;
		}else if(clickedTag == 'LI'){
			removeActiveCategory();
			e.target.firstChild.className = 'anchor active';
			clickedCategory = e.target.dataset.category;
		}
		/*else if(clickedTag == 'UL'){
			return;
		}*/
		isClickedAnotherCategory = true;
		currentCategory = clickedCategory;
		let url = `/products?categoryId=${clickedCategory}`;
		ajax(addProductTemplate, GET, url);
	});
}
/**
 * category의 활성화(anchor) class 제거
 * @returns
 */
function removeActiveCategory(){
	Array.from(document.getElementsByClassName('active')).forEach(tag=>{
		tag.className = 'anchor';
	});
}

/****************************************/
/***************promotion****************/
/****************************************/
function getPromotions(data){
	let promotionsHtml;
	let promotions = data.items;
	promotions.forEach(promotion=>{
		promotionsHtml += promotionTemplate.replace('{productImageUrl}', promotion.productImageUrl);
	});
	document.getElementById('slide').innerHTML += promotionsHtml;
	
	slidePromotion(promotions.length);
}