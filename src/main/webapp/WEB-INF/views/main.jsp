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
<link href="./css/reservation.css" rel="stylesheet">
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
						<div class="visual_img">
							<div class="slide_images">
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 카테고리 tab 구역 -->
			<div class="section_event_tab">
				<ul class="event_tab_lst tab_lst_min">
					<li class="item">
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
						<button class="btn">
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

	<script>
		// empty, null, undefined 체크 
		function isEmpty(x) {
			return (!x || 0 === x.length);
		}

		function init() {
			setPromotions();
			setCategories();
			setProducts()
			
		}

		<!-- 프로모션정보를 서버로부터 불러와 해당dom에 추가한다 -->
		function setPromotions() {
			var xhr = new XMLHttpRequest();
			var url = "./api/promotions";

			xhr.open("GET", url);
			xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
			xhr.send();

			xhr.addEventListener("load", function(e) {
				var items = JSON.parse(e.target.response).items;
				var html = document.querySelector("#template-promotion-image").innerHTML;
				var resultHTML = "";
				for (var i in items) {
					resultHTML += html.replace("{id}", items[i].id)
							.replace("{productId}", items[i].productId)
							.replace("{productImageUrl}", items[i].productImageUrl);
				}
				resultHTML += html.replace("{id}", items[0].id)
						.replace("{productId}", items[0].productId)
						.replace("{productImageUrl}", items[0].productImageUrl);

				document.querySelector(".slide_images").innerHTML = resultHTML;
				doSlidePromotion();
			});

			xhr.addEventListener("error", function(e) {
				alert("An error occurred while transferring the file.");
			});
		}

		<!-- 프로모션 이미지들을 슬라딩해준다 -->
		function doSlidePromotion() {
			var slideImages = document.querySelector(".slide_images");
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

		<!-- 카테고리정보를 서버로부터 불러와 해당dom에 추가한다 -->
		function setCategories() {
			var xhr = new XMLHttpRequest();
			var url = "./api/categories";

			xhr.open("GET", url);
			xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
			xhr.send();

			xhr.addEventListener("load", function(e) {
				var items = JSON.parse(e.target.response).items;
				var html = document.querySelector("#template-category-ui-list").innerHTML;
				var resultHTML = "";
				var totalCount = 0;
				for (var i in items) {
					resultHTML += html.replace("{id}", items[i].id)
							.replace("{name}", items[i].name)
							.replace("{count}", items[i].count);
					totalCount += items[i].count;
				}
				var categoryTabList = document.querySelector(".tab_lst_min");
				categoryTabList.innerHTML += resultHTML;
				setTabUI();
			});

			xhr.addEventListener("error", function(e) {
				alert("An error occurred while transferring the file.");
			});
		}
		
		function setTabUI() {
			var categoryTabList = document.querySelector(".tab_lst_min");
			categoryTabList.addEventListener("click", function(evt) {
				if (evt.target.tagName === "SPAN") {
					var els = categoryTabList.querySelectorAll("a");
					for (var i=0; i<els.length; i++) {
						els[i].setAttribute("class", "anchor");
					}
					evt.target.parentNode.setAttribute("class", "anchor active");
					var categoryId = evt.target.parentNode.parentNode.getAttribute("data-category");
					setProducts(categoryId);
				}
			});
		}

		<!-- 상품정보를 서버로부터 불러와 해당dom에 추가한다 -->
		function setProducts(categoryId) {
			var xhr = new XMLHttpRequest();
			var url = "./api/products";

			var params = "";
			if (!isEmpty(categoryId)) {
				params = "?categoryId=" + categoryId; 
			}

			xhr.open("GET", url + params);
			xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
			xhr.send();

			xhr.addEventListener("load", function(e) {
				var data = JSON.parse(e.target.response);
				var items = data.items;
				var totalCount = data.totalCount;
				var html = document.querySelector("#template-product-list").innerHTML;
				var resultHTML = "";
				
				var productListBox = document.querySelector(".wrap_event_box");
				var leftList = productListBox.getElementsByTagName("ul")[0];
				var rightList = productListBox.getElementsByTagName("ul")[1];
				leftList.innerHTML = "";
				rightList.innerHTML = "";
				
				for (var i in items) {
					var resultHTML = html.replace("{displayInfoId}", items[i].displayInfoId)
						.replace("{productId}", items[i].productId)
						.replace("{productDescription}", items[i].productDescription)
						.replace("{productDescription}", items[i].productDescription)
						.replace("{placeName}", items[i].placeName)
						.replace("{productContent}", items[i].productContent)
						.replace("{productImageUrl}", items[i].productImageUrl);
					if (i % 2) {
						rightList.innerHTML += resultHTML
					} else {
						leftList.innerHTML += resultHTML;
					}
				}
				setCategoryCount(totalCount);
			});

			xhr.addEventListener("error", function(e) {
				alert("An error occurred while transferring the file.");
			});
		}
		

		<!-- 전체 카테고리 상품 개수 표시 -->
		function setCategoryCount(count) {
			var pink = document.querySelector(".pink");
			pink.innerText = count + "개";
		}

		init();
	</script>
</body>
</html>