<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description"
	content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
<title>네이버 예약</title>
<link href="/css/style.css" rel="stylesheet">
</head>
<body>
	<div id="container">
		<div class="header">
			<header class="header_tit">
				<h1 class="logo">
					<a href="https://m.naver.com/" class="lnk_logo" title="네이버"> <span
						class="spr_bi ico_n_logo">네이버</span>
					</a> <a href="./myreservation.html" class="lnk_logo" title="예약"> <span
						class="spr_bi ico_bk_logo">예약</span>
					</a>
				</h1>
				<a href="./bookinglogin.html" class="btn_my"> <span
					class="viewReservation" title="예약확인">예약확인</span>
				</a>
			</header>
		</div>
		<hr>
		<div class="event">
			<div class="section_visual">
				<div class="group_visual">
					<div class="container_visual">
						<div class="prev_e" style="display: none;">
							<div class="prev_inn">
								<a href="#" class="btn_pre_e" title="이전"> <i
									class="spr_book_event spr_event_pre">이전</i>
								</a>
							</div>
						</div>
						<div class="nxt_e" style="display: none;">
							<div class="nxt_inn">
								<a href="#" class="btn_nxt_e" title="다음"> <i
									class="spr_book_event spr_event_nxt">다음</i>
								</a>
							</div>
						</div>
						<div>
							<div class="container_visual">
								<!-- [D] 이전,다음 버튼을 클릭할때마다 캐러셀 형태로 순환 됨 -->
								<%
									//TODO slide image, link
								%>
								<ul class="visual_img">
									<c:forEach var="promotion" items="${ promotionList }">
										<li><img src="/img/product/${ promotion.productImageUrl }"/></li>
									</c:forEach>
								</ul>
							</div>
							<span class="nxt_fix" style="display: none;"></span>
						</div>
					</div>
				</div>
			</div>
			<div class="section_event_tab">
				<ul class="event_tab_lst tab_lst_min">
					<li class="item" data-category="0"><a class="anchor active">
							<span>전체리스트</span>
					</a></li>
					<c:forEach var="category" items="${ categoryList }">
						<li class="item" data-category="${ category.id }">
							<a class="anchor"><span>${ category.name }</span></a>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="section_event_lst">
				<p class="event_lst_txt">
					바로 예매 가능한 행사가 <span id="categoryCount" class="pink"> ${ productCount } </span> 있습니다
				</p>
				<div class="wrap_event_box">
					<!-- [D] lst_event_box 가 2컬럼으로 좌우로 나뉨, 더보기를 클릭할때마다 좌우 ul에 li가 추가됨 -->
					<ul id="productBox1" class="lst_event_box">
					<c:forEach var="product" items="${ productList }" varStatus="status">
						<c:if test="${ status.index % 2 == 0 }">
						<li class="item">
							<a href="#" class="item_book">
								<div class="item_preview">
									<img alt="${ product.productDescription }" class="img_thumb"
										src="img/product/${ product.productImageUrl }">
									<span class="img_border"></span>
								</div>
								<div class="event_txt">
									<h4 class="event_txt_tit">
										<span>${ product.productDescription }</span>
										<small class="sm">${ product.placeName }</small>
									</h4>
									<p class="event_txt_dsc">${ product.productContent }</p>
								</div>
							</a>
						</li>
						</c:if>
					</c:forEach>
					</ul>
					<ul id="productBox2" class="lst_event_box">

					<c:forEach var="product" items="${ productList }" varStatus="status">
						<c:if test="${ status.index % 2 != 0 }">
						<li class="item">
							<a href="#" class="item_book">
								<div class="item_preview">
									<img alt="${ product.productDescription }" class="img_thumb"
										src="img/product/${ product.productImageUrl }">
									<span class="img_border"></span>
								</div>
								<div class="event_txt">
									<h4 class="event_txt_tit">
										<span>${ product.productDescription }</span>
										<small class="sm">${ product.placeName }</small>
									</h4>
									<p class="event_txt_dsc">${ product.productContent }</p>
								</div>
							</a>
						</li>
						</c:if>
					</c:forEach>

					</ul>
					<!-- 더보기 -->
					<div class="more">
						<button id="moreButton" class="btn" value="4">
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

	<script type="rv-template" id="itemList">
		<li class="item">
			<a href="detail.html?id={id}" class="item_book">
				<div class="item_preview">
					<img alt="{description}" class="img_thumb" src="img/product/{productImageUrl}">
					<span class="img_border"></span>
				</div>
				<div class="event_txt">
					<h4 class="event_txt_tit"> <span>{description}</span> <small class="sm">{placeName}</small> </h4>
					<p class="event_txt_dsc">{content}</p>
				</div>
			</a>
		</li>
	</script>

	<script>
		let categoryCount = parseInt(document.querySelector("#categoryCount").innerText);
		const LIMIT = 4;
		
		let more = document.querySelector("#moreButton");
		more.addEventListener("click", function(e){
			let buttonValue = parseInt(e.target.value);
			
			ajax(buttonValue);
			
			if(buttonValue < categoryCount){
				buttonValue += LIMIT;
				e.target.value = buttonValue; 
			}else{
				removeMoreButton();
			}
		});

		function ajax(start) {
			let oReq = new XMLHttpRequest();
			let categoryId = 0;
			oReq.addEventListener("load", function() {
				let data = JSON.parse(this.responseText);
				replacingScript(data.products);
			});

			oReq.open("GET", "http://localhost:8080/products?categoryId="+ categoryId + "&start=" + start);
			oReq.send();

		}

		function replacingScript(products){
			products.forEach(function(product, index){
				let script = document.querySelector("#itemList").innerHTML;
				let resultHtml = script.replace('{id}', product.displayInfoId)
							.replace('{placeName}', product.placeName)
							.replace("{content}", product.productContent)
							.replace(/{description}/gi, product.productDescription)
							.replace("{productImageUrl}", product.productImageUrl);
				
				if(index % 2 == 0){
					document.querySelector('#productBox1').innerHTML += resultHtml;
				}else{
					document.querySelector('#productBox2').innerHTML += resultHtml;
				}
			});
		}
		
		function removeMoreButton(){
			document.querySelector("#moreButton").hidden = true;
		}
		
		
		// slide
		document.addEventListener("DOMContentLoaded", function(){
			let promotionSlide = document.querySelector(".visual_img");
			let slideWidth = promotionSlide.offsetWidth;
			let promotionCount = promotionSlide.childElementCount;
			let duration = promotionCount;
			
			let slideCss = document.createElement("style");
			slideCss.type = "text/css";
			
			cssScript = "100% {right: 0px;}";
			for(let i = 0; i < promotionCount; i++){
				cssScript += (i / promotionCount) * 100 + "% {right: " + slideWidth * i + "px}\n";
			}
			slideCss.innerHTML = `.visual_img { padding: 0px;
				margin: 0px;
				list-style: none;
				height: 177px;
				display: flex;
				overflow: hidden; }` + 
			
			`@keyframes slide{` + cssScript + `}

			.visual_img li img {
				width: `+ slideWidth + `px;
				position: relative;
				animation-name: slide;
				animation-duration: `+ duration + `s;
				animation-iteration-count: infinite;
			}`;
			document.body.appendChild(slideCss);
		});
		
	</script>
</body>
</html>
