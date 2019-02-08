<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="utf-8">
	<meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
	<title>네이버 예약</title>
	<link href="css/style.css" rel="stylesheet">
</head>

<body>
	<div id="container">
		<div class="header">
			<header class="header_tit">
				<h1 class="logo">
					<a href="https://m.naver.com/" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
					<a href="./myreservation.html" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
				</h1>
				<a href="./bookinglogin.html" class="btn_my"> <span class="viewReservation" title="예약확인">예약확인</span> </a>
			</header>
		</div>
		<hr>
		<div class="event">
			<div class="section_visual">
				<div class="group_visual">
					<div class="container_visual">
						<div class="prev_e" style="display:none;">
							<div class="prev_inn">
								<a href="#" class="btn_pre_e" title="이전"> <i class="spr_book_event spr_event_pre">이전</i> </a>
							</div>
						</div>
						<div class="nxt_e" style="display:none;">
							<div class="nxt_inn">
								<a href="#" class="btn_nxt_e" title="다음"> <i class="spr_book_event spr_event_nxt">다음</i> </a>
							</div>
						</div>
						<div>
							<div class="container_visual">
								<!-- [D] 이전,다음 버튼을 클릭할때마다 캐러셀 형태로 순환 됨 -->
								<ul class="visual_img">
								
								</ul>
							</div>
							<span class="nxt_fix" style="display:none;"></span>
						</div>
					</div>
				</div>
			</div>
			<div class="section_event_tab">
				<ul class="event_tab_lst tab_lst_min">
					<li class="item" data-category="0">
						<a class="anchor active"> <span>전체리스트</span> </a>
					</li>
				</ul>
			</div>
			<div class="section_event_lst">
				<p class="event_lst_txt">바로 예매 가능한 행사가 <span class="pink"></span> 있습니다</p>
				<div class="wrap_event_box">
					<!-- [D] lst_event_box 가 2컬럼으로 좌우로 나뉨, 더보기를 클릭할때마다 좌우 ul에 li가 추가됨 -->
					<ul class="lst_event_box"></ul>
					<ul class="lst_event_box"></ul>
					<!-- 더보기 -->
					<div class="more">
						<button class="btn"><span>더보기</span></button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer>
		<div class="gototop">
			<a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span> </a>
		</div>
		<div class="footer">
			<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
			<span class="copyright">© NAVER Corp.</span>
		</div>
	</footer>


	<script type="rv-template" id="promotionItem">
	<li class="item" style="background-image: url({promotionImageUrl});">
		<a href="detail?id={productId}"> <span class="img_btm_border"></span> <span class="img_right_border"></span> <span class="img_bg_gra"></span>
			<div class="event_txt">
				<h4 class="event_txt_tit"></h4>
				<p class="event_txt_adr"></p>
				<p class="event_txt_dsc"></p>
			</div>
		</a>
	</li>
	</script>

	<script type="rv-template" id="productItem">
		<li class="item">
			<a href="detail?id={productId}" class="item_book">
				<div class="item_preview">
					<img alt="{description}" class="img_thumb" src="{productImageUrl}">
					<span class="img_border"></span>
				</div>
				<div class="event_txt">
					<h4 class="event_txt_tit"> <span>{description}</span> <small class="sm">{placeName}</small> </h4>
					<p class="event_txt_dsc">{content}</p>
				</div>
			</a>
		</li>
	</script>

	<script type="rv-template" id="categoryItem">
		<li class="item" data-category="{id}">
			<a class="anchor"> <span>{name}</span> </a>
		</li>
	</script>

	<script>
		var currentStart = 0;
		var currentCategory = 0;
		
		/**
		* 메인 페이지에서 호출할 /api의 Ajax Reqeust를 전담.
		* @param	callback	페이지 로드후에 실행할 callback function
		* @param	url	api/ 뒤에 붙일 url
		*/
		function requestAjax(callback, url){
			var ajaxReq = new XMLHttpRequest();
			ajaxReq.callback = callback;
			ajaxReq.addEventListener('load', function(evt) {
				this.callback(evt.target.response);
			});
			
			ajaxReq.open('GET', 'api/'+url);
			ajaxReq.responseType = 'json';
			ajaxReq.send()
		}
		
		
		function loadCategoriesCallback(responseData){
			var categoryList = responseData.categoryList;
			
			var template = document.querySelector('script#categoryItem').innerHTML;
			var resultHtml = '';
			for(var i = 0 ; i < categoryList.length; i++){
				resultHtml += template
									.replace('{name}',categoryList[i].name)
									.replace('{id}',categoryList[i].id);
			}
			
			document.querySelector('ul.event_tab_lst').innerHTML += resultHtml;
		}
		

		//페이지 상단에 움직이는 배너를 설정하는 함수 
		function setPromotionMove() {
			var bannerList = document.querySelectorAll('.visual_img>.item');
			var leftDistance = 0;
			var itemSize = bannerList.length;
			var curIdx = 0;
			
			setInterval(moveStep, 4000);
			

			//4초에 한번 배너를 다음 페이지로 넘기는 역할을 하는 함수
			function moveStep(){
				
				//모든 배너 이미지를 매 주기마다 -100% 이동
				leftDistance -= 100;

				for (var i = 0; i < bannerList.length; i++) {
					bannerList[i].style.left = leftDistance + '%';
				}

				curIdx++;

				//마지막장의 다음장은 미리 첫번째 이미지로 설정
				//if(마지막장을 넘어 갔을때)
				if (curIdx >= itemSize - 1) {
					
					//마지막 +1에 위치한 첫번째 이미지를 출력하는 도중에, 모든 이미지를 왼쪽으로 이동
					setTimeout(resetPromotionPos, 1100);
					
					//마지막장에서 첫장으로 반복하기위해 복구하는 함수
					//모든 이미지의 transitionDuration을 0s로 초기화 -> 모든 이미지를 다시 시작점으로 이동 -> 다시 transitionDuration을 1s로 초기화
					function resetPromotionPos() {
						
						for (var i = 0; i < bannerList.length; i++) {
							bannerList[i].style.transitionDuration = '0s';
						}

						for (var i = 0; i < bannerList.length; i++) {
							bannerList[i].style.left = '0';
						}
						
						setTimeout(reattachTransition, 100);
						
						function reattachTransition() {
							for (var i = 0; i < bannerList.length; i++) {
								bannerList[i].style.transitionDuration = '1s';
							}
							curIdx = 0;
							leftDistance = 0;
						}
						
					}
					
				}
				
			}
		}

		function loadPromotionsCallback(responseData) {
			var itemCount = responseData.totalCount;
			var promotionList = responseData.promotionList;

			var template = document.querySelector('script#promotionItem').innerHTML;
			var resultHtml = '';
			for (var i = 0; i < promotionList.length; i++) {
				resultHtml += template
									.replace('{promotionImageUrl}', promotionList[i].productImageUrl)
									.replace('{promotionImageUrl}', promotionList[i].productImageUrl)
									.replace('{productId}', promotionList[i].productId);
			}

			if (promotionList.length > 0) {
				resultHtml += template
									.replace('{promotionImageUrl}', promotionList[0].productImageUrl)
									.replace('{promotionImageUrl}', promotionList[0].productImageUrl)
									.replace('{productId}', promotionList[0].productId);
			}

			document.querySelector('ul.visual_img').innerHTML = resultHtml;

			setPromotionMove();
		}

		function loadProductsCallback(responseData) {
			currentStart += 4;

			var itemCount = responseData.totalCount;
			var productList = responseData.productList;

			var template = document.querySelector('script#productItem').innerText;
			var resultHtml = new Array(2);
			resultHtml[0] = '';
			resultHtml[1] = '';
			for (var i = 0; i < productList.length; i++) {
				resultHtml[i % 2] += template
												.replace('{productImageUrl}', productList[i].productImageUrl)
												.replace('{description}', productList[i].productDescription)
												.replace('{description}', productList[i].productDescription)
												.replace('{id}', productList[i].displayInfoId)
												.replace('{placeName}', productList[i].placeName)
												.replace('{content}', productList[i].productContent)
												.replace('{productId}', productList[i].productId);
			}
			var containers = document.querySelectorAll('.lst_event_box');
			containers[0].innerHTML += resultHtml[0];
			containers[1].innerHTML += resultHtml[1];

			var moreProductBtn = document.querySelector('.btn');
			if (itemCount <= currentStart) {
				moreProductBtn.style.display = 'none';
			} else {
				moreProductBtn.style.display = 'initial';
			}

			document.querySelector('.event_lst_txt>span').innerText = itemCount + '개';
		}

		function mapProductParameters(categoryId, start) {
			return 'products?categoryId=' + categoryId + '&start=' + start;
		}

		function setTabClickEvent() {
			document.querySelector('ul.event_tab_lst').addEventListener('click',function(btnEvent) {
				var selectedTab = event.target;
				
				if (selectedTab.tagName === 'SPAN') {
					selectedTab = selectedTab.parentElement;
				}

				if (selectedTab.tagName === 'A') {
					var categoryId = selectedTab.parentElement.getAttribute('data-category');
					
					//현재 탭과 다른 탭을 클릭했을 때 
					if (categoryId != currentCategory) {
						currentCategory = categoryId;
						currentStart = 0;

						document.querySelectorAll('a.anchor').forEach(element=>element.classList.remove('active'));
						selectedTab.classList.add('active');

						var containers = document.querySelectorAll('.lst_event_box');
						containers[0].innerHTML = '';
						containers[1].innerHTML = '';

						requestAjax(loadProductsCallback,mapProductParameters(currentCategory,currentStart));
					}
				}
			});
		}

		document.addEventListener('DOMContentLoaded', function() {
			//페이지 첫 로딩시 할 일

			//1. 카테고리 목록 가져오기
			requestAjax(loadCategoriesCallback, 'categories');

			//2. 상품 목록 가져오기
			requestAjax(loadProductsCallback, mapProductParameters(0, 0));

			//3. promotion 가져오기
			requestAjax(loadPromotionsCallback, 'promotions');

			//4. 더보기 버튼 event 등록
			document.querySelector('.btn').addEventListener('click',evt=>requestAjax(loadProductsCallback, mapProductParameters(currentCategory, currentStart)));

			//5. 탭 변경 event 등록
			setTabClickEvent();
		});
	</script>
</body>

</html>
