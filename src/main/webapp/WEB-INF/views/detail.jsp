<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="description"
	content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
<title>네이버 예약</title>
<link href="/css/style.css" rel="stylesheet">
<style>
.container_visual {
	height: 414px;
}
</style>
</head>

<body>
	<div id="container">
		<div class="header fade">
			<header class="header_tit">
				<h1 class="logo">
					<a href="/main" class="lnk_logo" title="네이버">
						<span class="spr_bi ico_n_logo">네이버</span>
					</a>
					<a href="/main" class="lnk_logo" title="예약">
						<span class="spr_bi ico_bk_logo">예약</span>
					</a>
				</h1>
				<a href="/main" class="btn_my"> <span title="예약확인">예약확인</span></a>
			</header>
		</div>
		<div class="ct main">
			<div>
				<!-- 타이틀 영역  -->
				<div class="section_visual">
					<!-- 타이틀 헤더 -->
					<header>
						<h1 class="logo">
							<a href="/main" class="lnk_logo" title="네이버">
								<span class="spr_bi ico_n_logo">네이버</span>
							</a>
							<a href="/main" class="lnk_logo" title="예약"> <span
								class="spr_bi ico_bk_logo">예약</span>
							</a>
						</h1>
						<a href="/main" class="btn_my"> 
							<span class="viewReservation" title="예약확인">예약확인</span>
						</a>
					</header>
					<!-- 타이틀 이미지 사진번호 -->
					<div class="pagination">
						<div class="bg_pagination"></div>
						<div class="figure_pagination">
							<!-- 현재사진 번호 -->
							<span class="num"></span>
							<!-- 총사진 개수 -->
							<span class="num off"></span>
						</div>
					</div>
					<div class="group_visual">
						<div>
							<div class="container_visual" style="width: 414px;">
								<!-- 타이틀 이미지 넣는 구역 -->
								<ul class="visual_img detail_swipe"></ul>
							</div>
							<div class="prev">
								<div class="prev_inn">
									<a href="#" class="btn_prev" title="이전"> <!-- [D] 첫 이미지 이면 off 클래스 추가 -->
										<i class="spr_book2 ico_arr6_lt off"></i>
									</a>
								</div>
							</div>
							<div class="nxt">
								<div class="nxt_inn">
									<a href="#" class="btn_nxt" title="다음"> <i
										class="spr_book2 ico_arr6_rt"></i>
									</a>
								</div>
							</div>
						</div>
					</div>
					<div class="group_btn_goto" style="display: none;">
						<a class="btn_goto_home" title="홈페이지" href="#" target="siteUrl">
							<i class="fn fn-home1"></i>
						</a> <a class="btn_goto_tel" title="전화" href="#"> <i
							class="fn fn-call1"></i>
						</a> <a class="btn_goto_mail" title="이메일" href="#"> <i
							class="fn fn-mail1"></i>
						</a> <a href="#" class="btn_goto_path" title="길찾기"> <i
							class="fn fn-path-find1"></i>
						</a> <a href="#" class="fn fn-share1 naver-splugin btn_goto_share"
							title="공유하기"></a>
					</div>
				</div>
				<!-- 상품 설명 영역  -->
				<div class="section_store_details">
					<!-- [D] 펼쳐보기 클릭 시 store_details에 close3 제거 -->
					<div class="store_details close3">
						<p class="dsc"></p>
					</div>
					<!-- [D] 토글 상황에 따라 bk_more에 display:none 추가 -->
					<a href="#" class="bk_more _open"> <span class="bk_more_txt">펼쳐보기</span>
						<i class="fn fn-down2"></i>
					</a> <a href="#" class="bk_more _close" style="display: none;"> <span
						class="bk_more_txt">접기</span> <i class="fn fn-up2"></i>
					</a>
				</div>
				<!-- 이벤트 정보 영역  -->
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
				<!-- 예매하기 버튼 영역  -->
				<div class="section_btn">
					<button type="button" class="bk_btn">
						<i class="fn fn-nbooking-calender2"></i> <span>예매하기</span>
					</button>
				</div>
				<!-- 상품평 영역  -->
				<div class="section_review_list">
					<div class="review_box">
						<h3 class="title_h3">예매자 한줄평</h3>
						<div class="short_review_area">
							<div class="grade_area">
								<!-- [D] 별점 graph_value는 퍼센트 환산하여 width 값을 넣어줌 -->
								<span class="graph_mask">
									<em class="graph_value" style="width: 84%;"></em>
								</span> 
								<strong class="text_value">
									<span></span> 
									<em class="total">5.0</em>
								</strong>
								<span class="join_count"> <em class="green"></em> 등록</span>
							</div>
							<!-- 상품평 넣는 구역 -->
							<ul class="list_short_review"></ul>
						</div>
						<p class="guide">
							<i class="spr_book2 ico_bell"></i>
							<span>네이버 예약을 통해 실제 방문한 이용자가 남긴 평가입니다.</span>
						</p>
					</div>
					<a class="btn_review_more" onclick="goReviewPage();">
						<span>예매자 한줄평 더보기</span>
						<i class="fn fn-forward1"></i>
					</a>
				</div>
				<!-- 상세정보 / 오시는길 영역 -->
				<div class="section_info_tab">
					<!-- [D] tab 선택 시 anchor에 active 추가 -->
					<ul class="info_tab_lst">
						<li class="item active _detail"><a href="#"
							class="anchor active"> <span>상세정보</span>
						</a></li>
						<li class="item _path"><a href="#" class="anchor"> <span>오시는길</span>
						</a></li>
					</ul>
					<!-- [D] 상세정보 외 다른 탭 선택 시 detail_area_wrap에 hide 추가 -->
					<div class="detail_area_wrap">
						<div class="detail_area">
							<div class="detail_info">
								<h3 class="blind">상세정보</h3>
								<ul class="detail_info_group">
									<li class="detail_info_lst"><strong class="in_tit">[소개]</strong>
										<p class="in_dsc"></p></li>
									<li class="detail_info_lst"><strong class="in_tit">[공지사항]</strong>
										<ul class="in_img_group">
											<li class="in_img_lst"><img alt="" class="img_thumb"
												src="https://ssl.phinf.net/naverbooking/20170131_238/14858250829398Pnx6_JPEG/%B0%F8%C1%F6%BB%E7%C7%D7.jpg?type=a1000">
											</li>
										</ul></li>
									<!-- <li class="detail_info_lst"> <strong class="in_tit">[공연정보]</strong>
                                        <ul class="in_img_group">
                                            <li class="in_img_lst"> <img alt="" class="img_thumb" src="https://ssl.phinf.net/naverbooking/20170131_255/1485825099482NmYMe_JPEG/%B0%F8%BF%AC%C1%A4%BA%B8.jpg?type=a1000"> </li>
                                        </ul>
                                    </li> -->
								</ul>
							</div>
						</div>
					</div>
					<!-- [D] 오시는길 외 다른 탭 선택 시 detail_location에 hide 추가 -->
					<div class="detail_location hide">
						<div class="box_store_info no_topline">
							<a href="#" class="store_location" title="지도웹으로 연결"> <img
								class="store_map img_thumb" alt="map"
								src="https://simg.pstatic.net/static.map/image?version=1.1&amp;crs=EPSG:4326&amp;baselayer=bl_vc_bg&amp;exception=xml&amp;scale=2&amp;caller=mw_smart_booking&amp;overlayers=ol_vc_an&amp;center=127.0011948,37.5717079&amp;markers=type,default2,127.0011948,37.5717079&amp;level=11&amp;w=340&amp;h=150">
								<span class="img_border"></span> <span class="btn_map"><i
									class="spr_book2 ico_mapview"></i></span>
							</a>
							<h3 class="store_name">엔에이치엔티켓링크(주)</h3>
							<div class="store_info">
								<div class="store_addr_wrap">
									<span class="fn fn-pin2"></span>
									<p class="store_addr store_addr_bold">서울특별시 종로구 종로33길 15</p>
									<p class="store_addr">
										<span class="addr_old">지번</span> <span class="addr_old_detail">서울특별시
											종로구 연지동 270 </span>
									</p>
									<p class="store_addr addr_detail">두산아트센터 연강홀</p>
								</div>
								<div class="lst_store_info_wrap">
									<ul class="lst_store_info">
										<li class="item"><span class="item_lt"> <i
												class="fn fn-call2"></i> <span class="sr_only">전화번호</span>
										</span> <span class="item_rt"> <a href="tel:02-548-0597"
												class="store_tel">02-548-0597</a></span></li>
									</ul>
								</div>
							</div>
							<!-- [D] 모바일 브라우저에서 접근 시 column2 추가와 btn_navigation 요소 추가 -->
							<div class="bottom_common_path column2">
								<a href="#" class="btn_path"> <i class="fn fn-path-find2"></i>
									<span>길찾기</span>
								</a> <a hewf="#" class="btn_navigation before"> <i
									class="fn fn-navigation2"></i> <span>내비게이션</span>
								</a>
							</div>
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
			<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및
				환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
			<span class="copyright">© NAVER Corp.</span>
		</div>
	</footer>
	<div id="photoviwer"></div>
	<script type="text/template" id="template-product-image">
		<li class="item" style="width: 414px;">
			<img alt="" class="img_thumb" src="{{saveFileName}}">
			<span class="img_bg"></span>
			<div class="visual_txt">
				<div class="visual_txt_inn">
					<h2 class="visual_txt_tit">
						<span>{{productDescription}}</span>
					</h2>
					<p class="visual_txt_dsc"></p>
				</div>
			</div>
		</li>
	</script>
	<script type="text/template" id="template-comment">
		<li class="list_item">
			<div>
				<div class="review_area">
					{{#if commentImage}}
						<div class="thumb_area">
							<a href="#" class="thumb" title="이미지 크게 보기"> 
								<img width="90" height="90" class="img_vertical_top" src="{{commentImage.saveFileName}}" alt="리뷰이미지">
							</a> 
							<span class="img_count" style="display: none;">1</span>
						</div>
					{{/if}}
					<h4 class="resoc_name"></h4>
					<p class="review">{{comment}}</p>
				</div>
				<div class="info_area">
					<div class="review_info">
						<span class="grade">{{score}}</span>
						<span class="name">{{reservationName}}</span>
						<span class="date">{{reservationDate}} 방문</span>
					</div>
				</div>
			</div>
		</li>
	</script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.1.0/handlebars.min.js"></script>
	<script type="text/javascript" src="/js/util.js"></script>
	<script type="text/javascript" src="/js/ajax.js"></script>
	<script>
		var displayInfoId = parseInt(new URL(window.location.href).searchParams.get("id"));
		var productId;

		function goReviewPage() {
			location.href="review?productId=" + productId;
		}
		var detail = {
			init : function() {
				displayInfoResponse.loadDisplayInfoResponse(this.initDisplayInfo.bind(this), displayInfoId);
			},
			initDisplayInfo : function(response) {
				this.setTitleDOM(response);
				this.setProductContentDOM(response);
				this.setDetailInformationDOM(response);
				productId = response.displayInfo.productId;
				commentResponse.loadCommentResponse(this.initComment.bind(this), productId, 0, 3);
			},
			initComment : function(response) {
				this.setCountDOM(response);
				this.setAvgScoreDOM(response);
				this.setCommentDOM(response);
			},
			
			// 타이틀 구역 설정
			setTitleDOM : function(response) {
				// 이미지 페이징 번호값 지정
				var currentNumDiv = document.querySelector(".num");
				var lastNumDiv = document.querySelector(".num.off");
				currentNumDiv.innerText = 1;
				lastNumDiv.innerText = "/ " + response.productImages.length;

				// 상품 이미지 리스트 추가
				var template = document.querySelector("#template-product-image").innerText;
				var bindTemplate = Handlebars.compile(template);
				var resultHTML = "";
				for (var i in response.productImages) {
					var data = {
						saveFileName: response.productImages[i].saveFileName,
						productDescription: response.displayInfo.productDescription
					}
					resultHTML += bindTemplate(data);
				}
				var productImagesDiv = document.querySelector('.visual_img.detail_swipe');
				productImagesDiv.innerHTML = resultHTML;
			},
			// 상품 설명 설정
			setProductContentDOM : function(response) {
				var productContentTextDiv = document.querySelector(".store_details .dsc");
				productContentTextDiv.innerText = response.displayInfo.productContent;
			},
			// TODO: 상세정보 구역 구현
			setDetailInformationDOM : function(response) {
			},
			
			// 상품평 총 개수 설정
			setCountDOM : function(response) {
				var countTextDiv = document.querySelector(".join_count .green");
				countTextDiv.innerText = response.totalCount + " 건";
			},
			// 상품평 평점 설정
			setAvgScoreDOM : function(response) {
				var scoreTextDiv = document.querySelector(".text_value span");
				scoreTextDiv.innerText = response.averageScore.toFixed(1);
				var maxScore = parseFloat(document.querySelector(".text_value .total").innerText);
				document.querySelector(".graph_value").style.width = response.averageScore
						/ maxScore * 100 + "%";
			},
			// 상품평 추가
			setCommentDOM : function(response) {
				var template = document.querySelector("#template-comment").innerText;
				var bindTemplate = Handlebars.compile(template);
				var resultHTML = response.comments.reduce(function(prev, comment) {
					return prev + bindTemplate(comment);
				}, "");
				var commentsDiv = document.querySelector(".list_short_review");
				commentsDiv.innerHTML = resultHTML;

				if (response.totalCount <= 3) {
					this.hiddenMoreButton();
				}
			},
			hiddenMoreButton : function() {
				document.querySelector(".btn_review_more").style.display = "none";
			}
		}
		
		detail.init();
	</script>
</body>
</html>
