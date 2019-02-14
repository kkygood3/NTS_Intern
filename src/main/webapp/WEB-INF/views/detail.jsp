<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
<title>네이버 예약</title>
<link href="/resources/css/style.css" rel="stylesheet">
<style>
.container_visual {
	height: 414px;
}

.detail_swipe {
	padding: 0px;
	margin: 0px;
	transition: left 0.1s;
}
</style>
</head>

<body>
	<div id="container">
		<div class="header fade">
			<header class="header_tit">
				<h1 class="logo">
					<a href="./mainpage.html" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span>
					</a> <a href="./mainpage.html" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span>
					</a>
				</h1>
				<a href="#" class="btn_my"> <span title="예약확인">예약확인</span>
				</a>
			</header>
		</div>
		<div class="ct main">
			<div>
				<div class="section_visual">
					<header>
						<h1 class="logo">
							<a href="./mainpage.html" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span>
							</a> <a href="./mainpage.html" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span>
							</a>
						</h1>
						<a href="./myreservation.html" class="btn_my"> <span class="viewReservation" title="예약확인">예약확인</span>
						</a>
					</header>
					
					
					<!-- IF EXIST ETC IMAGE * 2 -->
					<div class="pagination">
						<div class="bg_pagination"></div>
						<div class="figure_pagination">
							<span class="num">1</span> <span class="num off">/ <span>2</span></span>
						</div>
					</div>
					

					
					
					<!-- TITLE -->
					<div class="group_visual">
						<div>
							<div class="container_visual" style="width: 414px;">
								<ul id="product_information" class="visual_img detail_swipe">
									<!-- PRODUCT TITLE & IMAGE -->
								</ul>
							</div>
							
							<!-- IF EXIST ETC IMAGE * 2 -->
							<div class="prev">
								<div class="prev_inn">
									<a class="btn_prev" title="이전"> <!-- [D] 첫 이미지 이면 off 클래스 추가 --> <i class="spr_book2 ico_arr6_lt off"></i>
									</a>
								</div>
							</div>
							<div class="nxt">
								<div class="nxt_inn">
									<a class="btn_nxt" title="다음"> <i class="spr_book2 ico_arr6_rt"></i>
									</a>
								</div>
							</div>
							
						</div>
					</div>
					
					<div id="information" class="group_btn_goto" style="display: none;">
					<!-- DETAIL INFOMATION -->
					</div>
					
				</div>
				
				<div class="section_store_details">
				<!-- CONTENT + '펼치기' -->
					<div id="content" class="store_details close3">
					</div>
					<a class="bk_more _open"> 
						<span class="bk_more_txt">펼쳐보기</span> <i class="fn fn-down2"></i>
					</a> 
					<a class="bk_more _close" style="display: none;">
						<span class="bk_more_txt">접기</span> <i class="fn fn-up2"></i>
					</a>
				</div>
				
				<div class="section_event">
					<div class="event_info_box">
						<div class="event_info_tit">
							<h4 class="in_tit">
								<i class="spr_book ico_evt"></i> <span>이벤트 정보</span>
							</h4>
						</div>
						<div class="event_info">
							<div class="in_dsc">
								[네이버예약 특별할인]<br>R석 50%, S석 60% 할인
							</div>
						</div>
					</div>
				</div>
				<div class="section_btn">
					<button id="reservation" type="button" class="bk_btn" data-id="${displayInfoId}">
						<i class="fn fn-nbooking-calender2"></i> <span>예매하기</span>
					</button>
				</div>
				<div class="section_review_list">
					<div class="review_box">
						<h3 class="title_h3">예매자 한줄평</h3>
						<div class="short_review_area">
						
							<!-- STAR SCORE -->
							<div id="score" class="grade_area">
							</div>
							
							<!-- LIMIT COMMENT -->
							<ul id="comment_ul" class="list_short_review">
							</ul>
							
						</div>
						<p class="guide">
							<i class="spr_book2 ico_bell"></i> <span>네이버 예약을 통해 실제 방문한 이용자가 남긴 평가입니다.</span>
						</p>
					</div>
					
					
					
					<!-- IF COMMENT > 3 -->
					<a class="btn_review_more" href="/review?displayInfoId=${ displayInfoId }"> <span>예매자 한줄평 더보기</span> <i class="fn fn-forward1"></i>	</a>
					
					
					
					
				</div>
				
				
				
				
				<!-- ACITVE ANCHOR FUNCTION -->
				<div class="section_info_tab">
					<!-- [D] tab 선택 시 anchor에 active 추가 -->
					<ul class="info_tab_lst">
						<li class="item active _detail">
							<a class="anchor active"> <span>상세정보</span></a>
						</li>
						<li class="item _path">
							<a class="anchor"> <span>오시는길</span></a>
						</li>
					</ul>
					<!-- [D] 상세정보 외 다른 탭 선택 시 detail_area_wrap에 hide 추가 -->
					<div class="detail_area_wrap">
						<div class="detail_area">
							<div class="detail_info">
								<h3 class="blind">상세정보</h3>
								
								
								<ul id="detail_content" class="detail_info_group">
								<!-- DETAIL INFO (DESCRIPTION, IMG?) -->
								</ul>
								
								
								
							</div>
						</div>
					</div>
					
					
					
					<div class="detail_location hide">
						<div id="place_information" class="box_store_info no_topline">
						<!-- [D] 오시는길 외 다른 탭 선택 시 detail_location에 hide 추가 -->
						</div>
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
			<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
			<span class="copyright">© NAVER Corp.</span>
		</div>
	</footer>
	<div id="photoviwer"></div>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="/resources/javascript/ajax.js"></script>
	<script src="/resources/javascript/detail.js"></script>
	
	
	<script id="product_information_template" type="text/template">
		<li id="product_information" class="item" style="width: 414px;">
			{{#each productImages}}
				<img alt="" class="img_thumb" src="/resources/img/{{saveFileName}}">
			{{/each}}
			<span class="img_bg"></span>
			<div class="visual_txt">
				<div class="visual_txt_inn">
					<h2 class="visual_txt_tit">
						<span>{{displayInfo.productDescription}}</span>
					</h2>
				<p class="visual_txt_dsc">qweqweqwe?</p>
				</div>
			</div>
		</li>
		
	</script>
	
	<script id="information_template" type="text/template">
		<a class="btn_goto_home" title="홈페이지" href="{{displayInfo.homepage}}" target="siteUrl">{{displayInfo.homepage}}<i class="fn fn-home1"></i>
		</a> <a class="btn_goto_tel" title="전화" href="#">{{displayInfo.telephone}}<i class="fn fn-call1"></i>
		</a> <a class="btn_goto_mail" title="이메일" href="#">{{displayInfo.email}}<i class="fn fn-mail1"></i>
		</a> <a href="#" class="btn_goto_path" title="길찾기"> <i class="fn fn-path-find1"></i>
		</a> <a href="#" class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
	</script>
	
	<script id="content_template" type="text/template">
		<p class="dsc">{{displayInfo.productContent}}</p>
	</script>
	
	<script id="detail_content_template" type="text/template">
		<li class="detail_info_lst"><strong class="in_tit">[소개]</strong>
			<p class="in_dsc">{{displayInfo.productContent}}</p>
		</li>
		<li class="detail_info_lst"><strong class="in_tit">[공지사항]</strong>
			<ul class="in_img_group">
				<li class="in_img_lst">
					<img alt="" class="img_thumb" src="https://ssl.phinf.net/naverbooking/20170131_238/14858250829398Pnx6_JPEG/%B0%F8%C1%F6%BB%E7%C7%D7.jpg?type=a1000">
				</li>
			</ul>
		</li>
	</script>
	
	<script id="place_information_template" type="text/template">
		<a href="#" class="store_location" title="지도웹으로 연결">
			<img class="store_map img_thumb" alt="map" src="/resources/img/{{displayInfoImage.saveFileName}}">
			<span class="img_border"></span>
			<span class="btn_map"><i class="spr_book2 ico_mapview"></i></span>
		</a>
		<h3 class="store_name">{{displayInfo.placeName}}</h3>
		<div class="store_info">
			<div class="store_addr_wrap">
				<span class="fn fn-pin2"></span>
				<p class="store_addr store_addr_bold">{{displayInfo.placeStreet}}</p>
				<p class="store_addr">
					<span class="addr_old">지번</span> <span class="addr_old_detail">{{displayInfo.placeLot}}</span>
				</p>
				<p class="store_addr addr_detail">{{displayInfo.placeName}}</p>
			</div>
			<div class="lst_store_info_wrap">
				<ul class="lst_store_info">
					<li class="item"><span class="item_lt"> <i class="fn fn-call2"></i> <span class="sr_only">전화번호</span>
					</span> <span class="item_rt"> <a href="tel:{{displayInfo.telephone}}" class="store_tel">{{displayInfo.telephone}}</a></span></li>
				</ul>
			</div>
		</div>
		
		<!-- [D] 모바일 브라우저에서 접근 시 column2 추가와 btn_navigation 요소 추가 -->
		<div class="bottom_common_path column2">
			<a href="#" class="btn_path"> <i class="fn fn-path-find2"></i> <span>길찾기</span>
			</a> <a href="#" class="btn_navigation before"> <i class="fn fn-navigation2"></i> <span>내비게이션</span>
			</a>
		</div>
	</script>
	
	<script id="comment_template" type="text/template">
	<li class="list_item">
	<div>
		{{#if commentImages}}
		<div class="review_area">
			<div class="thumb_area">
	
				{{#each commentImages}} <a href="#" class="thumb" title="이미지 크게 보기"> <img width="90" height="90" class="img_vertical_top" src="/resources/img/{{saveFileName}}" alt="리뷰이미지">
				</a> <span class="img_count">{{commentImages.length}}</span> {{/each}}
	
			</div>
			<h4 class="resoc_name">제목은 어디서??</h4>
			<p class="review"></p>
		</div>
		{{else}}
		<div class="review_area no_img">
			<h4 class="resoc_name">제목은 어디서??</h4>
			<p class="review">{{comment}}</p>
		</div>
		{{/if}}
	
		<div class="info_area">
			<div class="review_info">
				<span class="grade">{{score}}</span> <span class="name">{{reservationEmail}}</span> <span class="date">{{reservationDate}} 방문</span>
			</div>
		</div>
	</div>
	</li>
	</script>



<script id="score_template" type="text/template">
	<span class="graph_mask"> <em class="graph_value" style="width: 0;"></em></span>
	<strong class="text_value"> <span id="avgerage_score">{{avgScore}}</span> <em class="total">5.0</em>
	</strong> <span class="join_count"><em class="green">{{comments.length}}건</em> 등록</span>
</script>

<script id="etc_image_template" type="text/template">
	<li id="product_information" class="item" style="width: 414px;">
		<img alt="" class="img_thumb" src="/resources/img/img/{{fileName}}">
	</li>
</script>
</body>
</html>
