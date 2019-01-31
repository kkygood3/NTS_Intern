<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <li class="item" style="background-image: url(img/{productImageUrl});">
        <a href="img/{productImageUrl}"> <span class="img_btm_border"></span> <span class="img_right_border"></span> <span class="img_bg_gra"></span>
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
            <a href="detail.html?id={id}" class="item_book">
                <div class="item_preview">
                    <img alt="{description}" class="img_thumb" src="img/{productImageUrl}">
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
	
		function categoryLoading(){
			var ajaxReq = new XMLHttpRequest();
			
			ajaxReq.addEventListener('load', function(evt) {
				var responseJson = evt.target.response;
				var items = responseJson['items'];
				
				var template = document.querySelector("script#categoryItem").innerHTML;
				var resultHtml = "";
				for(var i = 0 ; i < items.length; i++){
					resultHtml += template.replace('{name}',items[i].name)
										.replace('{id}',items[i].id);
				}
				
				document.querySelector('ul.event_tab_lst').innerHTML += resultHtml;
			});
	
			ajaxReq.open('GET', 'api/categories');
			ajaxReq.responseType = 'json';
			ajaxReq.send()
		}
		
		function promotionMoveSet(){
			var items = document.querySelectorAll(".visual_img>.item");
			var leftDistance = 0;
			var itemSize = items.length;
			var curIdx = 0;
			
			setInterval(function(){
					leftDistance -= 100;
	
					for (var i = 0; i < items.length; i++) {
						items[i].style.left = leftDistance + '%';
					}
	
					curIdx++;
	
					if (curIdx >= itemSize - 1) {
						setTimeout(function() {
							for (var i = 0; i < items.length; i++) {
								items[i].style.transitionDuration = '0s';
							}
	
							for (var i = 0; i < items.length; i++) {
								items[i].style.left = '0';
							}
							setTimeout(function() {
								for (var i = 0; i < items.length; i++) {
									items[i].style.transitionDuration = '1s';
								}
								curIdx = 0;
								leftDistance = 0;
							}, 100);
						}, 1100);
					}
				
			},4000);
		}
		
		function promotionLoading(){
			var ajaxReq = new XMLHttpRequest();
	
			ajaxReq.addEventListener('load', function(evt) {
				var responseJson = evt.target.response;
				var itemCount = responseJson['totalCount'];
				var items = responseJson['items'];
				
				var template = document.querySelector("script#promotionItem").innerHTML;
				var resultHtml = "";
				for(var i = 0 ; i < itemCount; i++){
					resultHtml += template.replace('{productImageUrl}',items[i].productImageUrl);
				}
				
				if(items.length > 0)
					resultHtml += template.replace('{productImageUrl}',items[0].productImageUrl)
				
				document.querySelector('ul.visual_img').innerHTML = resultHtml;
								
				promotionMoveSet();
			});
	
			ajaxReq.open('GET', 'api/promotions');
			ajaxReq.responseType = 'json';
			ajaxReq.send()
		}
	
		function productLoading(categoryId, start, flag){
			var ajaxReq = new XMLHttpRequest();
			
			ajaxReq.addEventListener('load', function(evt) {
				var responseJson = evt.target.response;
				var itemCount = responseJson['totalCount'];
				var items = responseJson['items'];
				
				var template = document.querySelector("script#productItem").innerText;
				var resultHtml = new Array(2);
				resultHtml[0] = "";
				resultHtml[1] = "";
				var lrCounter = 0;
				for(var i = 0 ; i < items.length; i++){
					resultHtml[lrCounter] += template
									.replace('{productImageUrl}',items[i].productImageUrl)
									.replace("{description}",items[i].productDescription)
									.replace("{description}",items[i].productDescription)
									.replace('{id}',items[i].displayInfoId)
									.replace('{placeName}',items[i].placeName)
									.replace('{content}',items[i].productContent);
					lrCounter++;
					if(lrCounter == 2){
						lrCounter = 0;
					}
				}
				var containers = document.querySelectorAll('.lst_event_box');
				containers[0].innerHTML += resultHtml[0];
				containers[1].innerHTML += resultHtml[1];

				var moreProductBtn = document.querySelector('.btn');
				if(itemCount <= currentStart){
					moreProductBtn.style.display = 'none';
				} else {
					moreProductBtn.style.display = 'initial';
				}
				
				if(flag == 1){
					//최초 로딩 시 전체 Product수를 페이지 상단에 표시
					document.querySelector('.event_lst_txt>span').innerText = itemCount+'개';
				}
			});
			
			ajaxReq.open('GET', 'api/products?categoryId='+categoryId+'&start='+start);
			ajaxReq.responseType = 'json';
			ajaxReq.send();
			currentStart += 4;
		}
		
		function navTabEventSet(){
			document.querySelector('ul.event_tab_lst').addEventListener('click', function(btnEvent){
				var selectedTab = event.target;
				if(selectedTab.tagName === 'SPAN')
					selectedTab = selectedTab.parentElement;
				
				if(selectedTab.tagName === 'A'){
					var categoryId = selectedTab.parentElement.getAttribute('data-category');
					//다른 탭을 누른게 맞다면
					if(categoryId != currentCategory){
						currentCategory = categoryId;
						currentStart = 0;
						
						document.querySelectorAll('a.anchor').forEach(function(element){
							element.classList.remove('active');
						});
						selectedTab.classList.add('active');
						
						var containers = document.querySelectorAll('.lst_event_box');
						containers[0].innerHTML = "";
						containers[1].innerHTML = "";
						
						productLoading(currentCategory,currentStart,0);
					}
				}
			});
		}
		
		document.addEventListener("DOMContentLoaded", function() {
			//페이지 첫 로딩시 할 일
			
			//1. 카테고리 목록 가져오기
			categoryLoading();
			
			//2. 상품 목록 가져오기
			productLoading(0,0,1);
			
			//3. promotion 가져오기
			promotionLoading();
			
			//4. 더보기 버튼 event 등록
			document.querySelector('.btn').addEventListener('click', evt=>productLoading(currentCategory, currentStart, 0));
			
			//5. 탭 변경 event 등록
			navTabEventSet();
		});
	</script>
</body>

</html>
