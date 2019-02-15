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
	<link href="/css/style.css" rel="stylesheet">
	<link href="/css/detail.css" rel="stylesheet">
	<link href="/css/slide.css" rel="stylesheet">
</head>

<body>
	<div id="container">
		<div class="header fade">
		<%@ include file="/WEB-INF/views/header.jsp"%>
		</div>
		<div class="ct main">
			<div>
				<div class="section_visual">
					<header>
						<h1 class="logo">
							<a href="/" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
							<a href="/" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
						</h1>
						<a href="/reservation" class="btn_my"> <span class="viewReservation" title="예약확인">예약확인</span> </a>
						<div class="img_bg">
								<h2 class="visual_txt_tit"><span>${pageInfo.description}</span></h2>
						</div>
						
					</header>

					<!-- content -->
				</div>
				<div class="section_store_details">
					<!-- [D] 펼쳐보기 클릭 시 store_details에 close3 제거 -->
					<div class="store_details close3">
						<p class="dsc">${pageInfo.content}</p>
					</div>
					<!-- [D] 토글 상황에 따라 bk_more에 display:none 추가 -->
					<a class="bk_more _open"> <span class="bk_more_txt">펼쳐보기</span> <i class="fn fn-down2"></i> </a>
					<a class="bk_more _close" style="display: none;"> <span class="bk_more_txt">접기</span> <i class="fn fn-up2"></i> </a>
				</div>
				<div class="section_event">
					<div class="event_info_box">
						<div class="event_info_tit">
							<h4 class="in_tit"> <i class="spr_book ico_evt"></i> <span>이벤트 정보</span> </h4>
						</div>
						<div class="event_info">
							<div class="in_dsc">[네이버예약 특별할인]<br>R석 50%, S석 60% 할인</div>
						</div>
					</div>
				</div>
				<div class="section_btn"> <button type="button" class="bk_btn"> <i class="fn fn-nbooking-calender2"></i> <span>예매하기</span> </button> </div>
				<div class="section_review_list">
					<div class="review_box">
						<h3 class="title_h3">예매자 한줄평</h3>
						<div class="short_review_area">
							<div class="grade_area">
								<!-- [D] 별점 graph_value는 퍼센트 환산하여 width 값을 넣어줌 -->
								<span class="graph_mask"><em class="graph_value" style="width:${pageInfo.averageScore * 20}%;"></em></span>
								<strong class="text_value"><span>${pageInfo.averageScore}</span> <em class="total">5.0</em></strong>
								<span class="join_count"><em class="green">${pageInfo.commentCount}건</em> 등록</span>
							</div>
							<ul class="list_short_review">
							<!-- content -->
							</ul>
						</div>
						<p class="guide"> <i class="spr_book2 ico_bell"></i> <span>네이버 예약을 통해 실제 방문한 이용자가 남긴 평가입니다.</span> </p>
					</div>
					<a class="btn_review_more" href="./${displayInfoId}/review"> <span>예매자 한줄평 더보기</span> <i class="fn fn-forward1"></i> </a>
				</div>
				<div class="section_info_tab">
					<!-- [D] tab 선택 시 anchor에 active 추가 -->
					<ul class="info_tab_lst">
						<li class="item active _detail">
							<a class="anchor active"> <span>상세정보</span> </a>
						</li>
						<li class="item _path">
							<a class="anchor"> <span>오시는길</span> </a>
						</li>
					</ul>
					<!-- [D] 상세정보 외 다른 탭 선택 시 detail_area_wrap에 hide 추가 -->
					<div class="detail_area_wrap">
						<div class="detail_area">
						<div class="detail_info">
								<h3 class="blind">상세정보</h3>
								<ul class="detail_info_group">
									<li class="detail_info_lst">
										<strong class="in_tit">[소개]</strong>
										<p class="in_dsc">${pageInfo.content}</p>
									</li>
									<li class="detail_info_lst"> <strong class="in_tit">[공지사항]</strong>
										<ul class="in_img_group">
											<li class="in_img_lst"> <img alt="" class="img_thumb" src="https://ssl.phinf.net/naverbooking/20170131_238/14858250829398Pnx6_JPEG/%B0%F8%C1%F6%BB%E7%C7%D7.jpg?type=a1000"> </li>
										</ul>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- [D] 오시는길 외 다른 탭 선택 시 detail_location에 hide 추가 -->
					<div class="detail_location hide">
						<div class="box_store_info no_topline">
							<a href="#" class="store_location" title="지도웹으로 연결">
								<img class="store_map img_thumb" alt="map" src="/${pageInfo.mapFile}">
								<span class="img_border"></span>
								<span class="btn_map"><i class="spr_book2 ico_mapview"></i></span>
							</a>
							<h3 class="store_name">${pageInfo.description}</h3>
							<div class="store_info">
								<div class="store_addr_wrap">
									<span class="fn fn-pin2"></span>
									<p class="store_addr store_addr_bold">${pageInfo.placeStreet}</p>
									<p class="store_addr">
										<span class="addr_old">지번</span>
										<span class="addr_old_detail">${pageInfo.placeLot}</span>
									</p>
									<p class="store_addr addr_detail">${pageInfo.placeName}</p>
								</div>
								<div class="lst_store_info_wrap">
									<ul class="lst_store_info">
										<li class="item"> <span class="item_lt"> <i class="fn fn-call2"></i> <span class="sr_only">${pageInfo.tel}</span> </span> <span class="item_rt"> <a href="tel:02-548-0597" class="store_tel">${pageInfo.tel}</a></span> </li>
									</ul>
								</div>
							</div>
							<!-- [D] 모바일 브라우저에서 접근 시 column2 추가와 btn_navigation 요소 추가 -->
							<div class="bottom_common_path column2">
								<a href="#" class="btn_path"> <i class="fn fn-path-find2"></i> <span>길찾기</span> </a>
								<a href="#" class="btn_navigation before"> <i class="fn fn-navigation2"></i> <span>내비게이션</span> </a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/footer.jsp"%>
	<div id="photoviwer"></div>
	
	<%@ include file="/template/comment_item.jsp"%>
	<%@ include file="/template/detail.jsp"%>
	
	
	<script type="text/javascript">
		function displayInfo() {
			return {
				"productId" : "${pageInfo.productId}",
				"displayInfoId" : "${displayInfoId}",
				"commentCount" : "${pageInfo.commentCount}"
			};
		}
	</script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.1.0/handlebars.min.js"></script>
	<script type= "text/javascript" src= "/js/common/error.js" ></script>
	<script type= "text/javascript" src= "/js/common/sendAjax.js" ></script>
	<script type= "text/javascript" src= "/js/slide/slide.js" ></script>
	<script type= "text/javascript" src= "/js/comment_data/comment.js" ></script>
	<script type= "text/javascript" src= "/js/detail/button_display.js" ></script>
	<script type= "text/javascript" src= "/js/detail/visual.js" ></script>
	<script type= "text/javascript" src= "/js/detail/event.js" ></script>
	<script type= "text/javascript" src= "/js/detail/detail.js" ></script>
</body>
</html>
