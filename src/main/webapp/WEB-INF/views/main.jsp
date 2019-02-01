<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="description"
	content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
<title>네이버 예약</title>
<link href="./css/style.css" rel="stylesheet">
</head>
<body>
	<div id="container">
		<div class="header">
			<header class="header_tit">
				<h1 class="logo">
					<a href="#" class="lnk_logo" title="네이버">
					<span class="spr_bi ico_n_logo">네이버</span>
					</a>
					<a href="#" class="lnk_logo" title="예약">
						<span class="spr_bi ico_bk_logo">예약</span>
					</a>
				</h1>
				<a href="#" class="btn_my"> <span
					class="viewReservation" title="예약확인">예약확인</span>
				</a>
			</header>
		</div>
		<hr>
		<div class="event">
			<!-- 프로모션 구역 -->
			<div class="section_visual">
				<div class="group_visual">
					<div class="container_visual">
						<div class="slide_images"></div>
					</div>
				</div>
			</div>
			<!-- 카테고리 tab 구역 -->
			<div class="section_event_tab">
				<ul class="event_tab_lst tab_lst_min">
					<li class="item" data-category="0">
						<a class="anchor active">
							<span>전체리스트</span>
						</a>
					</li>
				</ul>
			</div>
			<!-- 카테고리 내용 -->
			<div class="section_event_lst">
				<p class="event_lst_txt">
					바로 예매 가능한 행사가 <span class="pink"></span> 있습니다
				</p>
				<!-- 상품 목록 -->
				<div class="wrap_event_box">
					<!-- [D] lst_event_box 가 2컬럼으로 좌우로 나뉨, 더보기를 클릭할때마다 좌우 ul에 li가 추가됨 -->
					<ul class="lst_event_box"></ul>
					<ul class="lst_event_box"></ul>
					<!-- 더보기 -->
					<div class="more">
						<button class="btn" onclick="moreProducts();">
							<span>더보기</span>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer>
		<div class="gototop">
			<a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span>
			</a>
		</div>
		<div class="footer">
			<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및
				환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
			<span class="copyright">© NAVER Corp.</span>
		</div>
	</footer>


	<script type="text/template" id="template-promotion-image">
		<img data-id="{id}" data-product-id="{productId}" src="{productImageUrl}"/>
	</script>

	<script type="text/template" id="template-category-ui-list">
		<li class="item" data-category="{id}" data-count="{count}">
			<a class="anchor">
				<span>{name}</span>
			</a>
		</li>
	</script>

	<script type="text/template" id="template-product-list">
		<li class="item" data-display-info-id={displayInfoId}, data-product-id={productId}>
			<a href="detail.html" class="item_book">
			<div class="item_preview">
				<img alt="{productDescription}" class="img_thumb" src="{productImageUrl}">
				<span class="img_border"></span>
			</div>
			<div class="event_txt">
				<h4 class="event_txt_tit">
					<span>{productDescription}</span>
					<small class="sm">{placeName}</small>
				</h4>
				<p class="event_txt_dsc">
					{productContent}
				</p>
			</div>
			</a>
		</li>
	</script>
	<script type="text/javascript" src="/js/util.js"></script>
	<script>
		/*
		 * ========================================
		 *             UTIL Fucntion
		 * ========================================
		*/ 

		// null or undefined or ""
		function isEmpty(value){
			return (value == null || value.length === 0);
		}

		/*
		 * XMLHttpRequest를 생성하고 반환해주며
		 * 미리 헤더값과 transfer error 일때 발생되는 이벤트를 등록해줍니다.
		*/
		function getXMLHttpRequest(url) {
			var xhr = new XMLHttpRequest();
			xhr.open("GET", url);
			xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
			xhr.responseType = "json";

			xhr.addEventListener("error", function(e) {
				alert("An error occurred while transferring the file.");
			});
			return xhr;
		}

		function replaceAll(str, searchStr, replaceStr) {
			  return str.split(searchStr).join(replaceStr);
		}

		// data오브젝트의 키값들을 templateHTML에 매핑해 키에해당하는 값으로 변환해줍니다.
		function parseTemplateToHTML(template, data) {
			var keys = Object.keys(data);
			for (var i in keys) {
				template = replaceAll(template, "{"+ keys[i] + "}", data[keys[i]]);
			}
			return template;
		}


		/*
		 * ========================================
		 *                Main JS
		 * ========================================
		*/ 

		// 상수
		const CATEGORY_TYPE_ALL = 0;
		
		// DOM
		var slideImages = document.querySelector(".slide_images"); // 프로모션의 슬라이드 영역
		var categoryTabList = document.querySelector(".tab_lst_min"); // 카테고리 탭 영역
		var productListBox = document.querySelector(".wrap_event_box"); // 상품들을 보여주는 영역


		/*
		 * DOM 로딩후 한번만 실행되는 초기화 함수로
		 * ajax를 통해 서버로부터 데이터를 가져와 각 DOM에 알맞게 넣어줍니다.
		*/
		function init() {
			setDOMPromotions();
			setDOMCategories();
			setDOMProducts(CATEGORY_TYPE_ALL, 0); // args : categoryId, startNumber
		}

		// 프로모션정보를 서버로부터 불러와 프로모션 영역에 넣어줍니다.
		function setDOMPromotions() {
			var url = "./api/promotions";
			var xhr = getXMLHttpRequest(url);
			xhr.send();

			xhr.addEventListener("load", function(e) {
				var promotions = e.target.response.promotions;
				var template = document.querySelector("#template-promotion-image").innerHTML;

				var resultHTML = promotions.reduce(function(prev, promotion) {
					return prev + parseTemplateToHTML(template, promotion)
				}, "");
				// 무한 슬라이딩을 위해 프로모션 첫 이미지를 끝에 추가로 삽입
				resultHTML += parseTemplateToHTML(template, promotions[0]); 

				slideImages.innerHTML = resultHTML;
				doAutoSlideShowPromotions();
			});
		}

		// 카테고리정보를 서버로부터 불러와 카테고리 영역에 넣어줍니다.
		function setDOMCategories() {
			var url = "./api/categories";
			var xhr = getXMLHttpRequest(url);
			xhr.send();

			xhr.addEventListener("load", function(e) {
				var categories = e.target.response.categories;
				var template = document.querySelector("#template-category-ui-list").innerHTML;
				var totalCount = 0;

				var resultHTML = categories.reduce(function(prev, category) {
					totalCount += category.count;
					return prev + parseTemplateToHTML(template, category)
				}, "");

				setCategoryCount(totalCount);
				// 모든 카테고리의 개수를 전체리스트탭 속성에 저장합니다.
				categoryTabList.firstElementChild.setAttribute("data-count", totalCount);
				categoryTabList.innerHTML += resultHTML;
				registTabUIEvent();
			});
		}

		// 상품정보를 서버로부터 불러와 상품영역에 넣어줍니다.
		function setDOMProducts(categoryId, start) {
			var leftList = productListBox.getElementsByTagName("ul")[0];
			var rightList = productListBox.getElementsByTagName("ul")[1];
			leftList.innerHTML = "";
			rightList.innerHTML = "";

			enableMoreButton();
			addDOMProducts(categoryId, start);
		}
		
		function getProductsApiUrl(categoryId, start) {
			var url;
			if (categoryId === CATEGORY_TYPE_ALL) {
				url = "./api/products?start=" + start;
			} else {
				url = "./api/categories/" + categoryId + "/products?start=" + start;
			}
			return url;
		}

		// 상품정보를 서버로부터 불러와 상품영역에 추가해서 넣어줍니다.
		function addDOMProducts(categoryId, start) {
			if (isEmpty(categoryId) || isEmpty(start)) {
				alert("잘못된 입력값입니다.");
				return;
			}

			var url = getProductsApiUrl(categoryId, start);
			var xhr = getXMLHttpRequest(url);
			xhr.send();

			xhr.addEventListener("load", function(e) {
				var products = e.target.response.products;
				var template = document.querySelector("#template-product-list").innerHTML;
				// 더이상 보여줄 데이터가 없는경우 더보기UI disable
				if (products.length < 4) {
					disableMoreButton();
				}
				
				var leftList = productListBox.getElementsByTagName("ul")[0];
				var rightList = productListBox.getElementsByTagName("ul")[1];
				for (var i in products) {
					var resultHTML = parseTemplateToHTML(template, products[i]);
					// 템플링된 하나의 상품 HTML를 번갈아가며 왼쪽리스트와 오른쪽리스트에  넣어줍니다.
					if (i % 2) {
						rightList.innerHTML += resultHTML
					} else {
						leftList.innerHTML += resultHTML;
					}
				}
			});
		}

		// 프로모션 이미지들을 자동으로 슬라딩해줍니다.
		function doAutoSlideShowPromotions() {
			var imgCount = slideImages.childElementCount;
			var i = 1;
			setInterval(function() {
				if (i == imgCount) {
					slideImages.style.transitionProperty = "none"
					slideImages.style.transform = "translateX(0px)";
					i = 1;
				} else {
					slideImages.style.transitionProperty = "all"
					slideImages.style.transform = "translateX(" + i * -414 + "px)";
					i += 1;
				}
			}, 2000);
		}

		// 전체 카테고리 상품 개수 표시
		function setCategoryCount(count) {
			var pink = document.querySelector(".pink");
			pink.innerText = count + "개";
		}

		// tabUI 영역에 이벤트리스너를 등록해줍니다.
		function registTabUIEvent() {
			categoryTabList.addEventListener("click", function(evt) {
				if (evt.target.tagName === "SPAN") {
					clickTabUI(evt.target.parentNode.parentNode);
				}
			});
		}

		// 카테고리 클릭시 해당 카레고리의 프로덕트를 가져와 DOM에 설정
		function clickTabUI(categoryTab) {
			var els = categoryTabList.querySelectorAll("a");
			for (var i=0; i<els.length; i++) {
				els[i].setAttribute("class", "anchor");
			}
			categoryTab.querySelector("a").setAttribute("class", "anchor active");
			var count = categoryTab.getAttribute("data-count");
			setCategoryCount(count);
			var categoryId = categoryTab.getAttribute("data-category");
			setDOMProducts(parseInt(categoryId), 0);
		}

		// more 버튼 활성화 
		function enableMoreButton() {
			var button = document.querySelector(".more .btn");
			button.disabled = false;
		}

		// more 버튼 비활성화 
		function disableMoreButton() {
			var button = document.querySelector(".more .btn");
			button.disabled = true;
		}

		// 프로덕트 더보기
		function moreProducts() {
			var el = document.querySelector(".active");
			var categoryId = el.parentNode.getAttribute("data-category");
			var listCount = productListBox.getElementsByTagName("li").length;
			addDOMProducts(parseInt(categoryId), listCount);
		}

		init();
	</script>
</body>
</html>