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
							<!-- [D] 출력중인 이미지 인덱스 번호 -->
							<span class="num"></span>
							<!-- [D] 총이미지 개수 -->
							<span class="num off"></span>
						</div>
					</div>
					<div class="group_visual" style="cursor: default;">
						<div>
							<div class="container_visual" style="width: 414px;">
								<!-- 타이틀 이미지 넣는 구역 -->
								<ul class="visual_img detail_swipe"></ul>
							</div>
							<div class="prev">
								<div class="prev_inn">
									<a class="btn_prev" title="이전"> <!-- [D] 첫 이미지 이면 off 클래스 추가 -->
										<i class="spr_book2 ico_arr6_lt off"></i>
									</a>
								</div>
							</div>
							<div class="nxt">
								<div class="nxt_inn">
									<a class="btn_nxt" title="다음"> <i
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
						<p class="dsc">${displayInfo.productContent}</p>
					</div>
					<!-- [D] 토글 상황에 따라 bk_more에 display:none 추가 -->
					<a class="bk_more _open"> <span class="bk_more_txt">펼쳐보기</span>
						<i class="fn fn-down2"></i>
					</a> <a class="bk_more _close" style="display: none;"> <span
						class="bk_more_txt">접기</span> <i class="fn fn-up2"></i>
					</a>
				</div>
				<!-- [D] 이벤트 정보 영역  -->
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
				<!-- [D] 예매하기 버튼 영역  -->
				<div class="section_btn">
					<button type="button" class="bk_btn">
						<i class="fn fn-nbooking-calender2"></i> <span>예매하기</span>
					</button>
				</div>
				<!-- [D] 상품평 영역  -->
				<div class="section_review_list">
					<div class="review_box">
						<h3 class="title_h3">예매자 한줄평</h3>
						<div class="short_review_area">
							<div class="grade_area">
								<!-- [D] 별점 graph_value는 퍼센트 환산하여 width 값을 넣어줌 -->
								<span class="graph_mask">
									<em class="graph_value" style="width: ${commentResponse.averageScore / 5 * 100}%;"></em>
								</span>
								<strong class="text_value">
									<span>${commentResponse.averageScore}</span>
									<em class="total">5.0</em>
								</strong>
								<span class="join_count"> <em class="green">${commentResponse.totalCount} 건</em> 등록</span>
							</div>
							<!-- [D] 상품평 넣는 구역 -->
							<ul class="list_short_review">
								<c:forEach var="comment" items="${commentResponse.comments}">
									<li class="list_item">
										<div>
											<div class="review_area">
												<c:if test="${comment.saveFileName != null}">
													<div class="thumb_area">
														<a href="#" class="thumb" title="이미지 크게 보기">
															<img width="90" height="90" class="img_vertical_top" src="/${comment.saveFileName}" alt="리뷰이미지">
														</a>
														<span class="img_count" style="display: none;">1</span>
													</div>
												</c:if>
												<h4 class="resoc_name"></h4>
												<p class="review">${comment.comment}</p>
											</div>
											<div class="info_area">
												<div class="review_info">
													<span class="grade">${comment.score}</span>
													<span class="name">${comment.reservationName}</span>
													<span class="date">${comment.reservationDate} 방문</span>
												</div>
											</div>
										</div>
									</li>
								</c:forEach>
							</ul>
						</div>
						<p class="guide">
							<i class="spr_book2 ico_bell"></i>
							<span>네이버 예약을 통해 실제 방문한 이용자가 남긴 평가입니다.</span>
						</p>
					</div>
					<c:if test="${commentResponse.totalCount > 4}">
						<a class="btn_review_more" onclick="detail.goCommentPage();">
							<span>예매자 한줄평 더보기</span>
							<i class="fn fn-forward1"></i>
						</a>
					</c:if>
				</div>
				<!-- [D] 상세정보 / 오시는길 영역 -->
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
								<ul class="detail_info_group">
									<li class="detail_info_lst">
										<strong class="in_tit">[소개]</strong>
										<p class="in_dsc">${displayInfo.productContent}</p>
									</li>
									<li class="detail_info_lst">
										<strong class="in_tit">[공지사항]</strong>
										<ul class="in_img_group">
											<li class="in_img_lst">
												<img alt="" class="img_thumb"
													src="https://ssl.phinf.net/naverbooking/20170131_238/14858250829398Pnx6_JPEG/%B0%F8%C1%F6%BB%E7%C7%D7.jpg?type=a1000">
											</li>
										</ul>
									</li>
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
							<a class="store_location" title="지도웹으로 연결">
								<img class="store_map img_thumb" alt="map" src="/${displayInfo.saveFileName}">
								<span class="img_border"></span>
								<span class="btn_map">
									<i class="spr_book2 ico_mapview"></i>
								</span>
							</a>
							<h3 class="store_name">${displayInfo.productDescription}</h3>
							<div class="store_info">
								<div class="store_addr_wrap">
									<span class="fn fn-pin2"></span>
									<p class="store_addr store_addr_bold">${displayInfo.placeLot}</p>
									<p class="store_addr">
										<span class="addr_old">지번</span>
										<span class="addr_old_detail">${displayInfo.placeStreet}</span>
									</p>
									<p class="store_addr addr_detail">${displayInfo.placeName}</p>
								</div>
								<div class="lst_store_info_wrap">
									<ul class="lst_store_info">
										<li class="item">
											<span class="item_lt">
												<i class="fn fn-call2"></i>
												<span class="sr_only">전화번호</span>
											</span>
											<span class="item_rt">
												<a class="store_tel">${displayInfo.telephone}</a>
											</span>
										</li>
									</ul>
								</div>
							</div>
							<!-- [D] 모바일 브라우저에서 접근 시 column2 추가와 btn_navigation 요소 추가 -->
							<div class="bottom_common_path column2">
								<a class="btn_path">
									<i class="fn fn-path-find2"></i><span>길찾기</span>
								</a>
								<a class="btn_navigation before">
									<i class="fn fn-navigation2"></i><span>내비게이션</span>
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
			<a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span></a>
		</div>
		<div class="footer">
			<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
			<span class="copyright">© NAVER Corp.</span>
		</div>
	</footer>
	<div id="photoviwer"></div>
	<script type="text/template" id="template-product-image">
		<li class="item" style="width: 414px; height: 414px;">
			<img alt="" class="img_thumb" src="/{{saveFileName}}">
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
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.1.0/handlebars.min.js"></script>
	<script type="text/javascript" src="/js/util.js"></script>
	<script>
		var productId = parseInt(window.location.pathname.split("/")[2]);
		var displayInfoId = parseInt(new URL(window.location.href).searchParams.get("displayInfoId"));
		const PRODUCT_IMAGE_LIMIT = 2;

		var detail = {
			// DIV Elements
			productImagesDiv : document.querySelector('.visual_img.detail_swipe'),
			// init
			init : function() {
				this.loadProductImagesResponse(this.initTitleImages.bind(this), PRODUCT_IMAGE_LIMIT);
				this.registMoreCotentEvent();
				this.registDetailInfoTabUI();
			},
			// ajax request ProductImages
			loadProductImagesResponse : function(callback, limit) {
				if (!isNumber(productId)) {
					alert("잘못된 productId임니다 프로덕트 이미지를 불러올수 없습니다.");
					return;
				}
				var url = "/api/products/" + productId + "/images?limit=" + limit;
				ajax(callback, url);
			},
			// 타이틀 이미지 구역 설정
			initTitleImages : function(response) {
				document.querySelector(".num").innerText = 1;
				document.querySelector(".num.off").innerText = "/ " + response.productImages.length;

				var template = document.querySelector("#template-product-image").innerText;
				var bindTemplate = Handlebars.compile(template);
				var resultHTML = "";
				for (var i in response.productImages) {
					var data = {
						saveFileName : response.productImages[i].saveFileName,
						productDescription : response.productDescription
					}
					resultHTML += bindTemplate(data);
				}
				this.productImagesDiv.innerHTML = resultHTML;
				this.initSlide();
			},
			// 슬라이드 애니메이션 설정
			initSlide : function() {
				var prevButton = document.querySelector('.btn_prev');
				var nextButton = document.querySelector('.btn_nxt');
				if (this.productImagesDiv.childElementCount <= 1) {
					prevButton.style.display = "none";
					nextButton.style.display = "none";
					return;
				}

				this.productImagesDiv.innerHTML += this.productImagesDiv.innerHTML;
				var left = 0;
				var translateX = -200;

				this.productImagesDiv.style.transform = "translateX(" + translateX + "%)";
				var titleDiv = document.querySelector('.group_visual');
				titleDiv.addEventListener("click", function(evt) {
					if (evt.target !== prevButton && evt.target !== nextButton) {
						return;
					}
					// 완전히 이동된후에 작동
					if (this.productImagesDiv.offsetLeft % 414) {
						return;
					}
					if (evt.target === prevButton) {
						left += 100;
						translateX -= 100;
					} else {
						left -= 100;
						translateX += 100
					}
					this.doSlide(left, translateX);
				}.bind(this));
			},
			// left값을 기준으로 이미지를 슬라이드 무한슬라이드를 위해 이미지가 두번 이동될때마다 translateX를 조정
			doSlide : function(left, translateX) {
				this.productImagesDiv.style.left = left + "%";
				if (translateX % 200 == 0) {
					this.productImagesDiv.style.transform = "translateX(" + translateX + "%)";
				}
				var productImagesIndexDiv = document.querySelector(".num");
				if (productImagesIndexDiv.innerText === "1") {
					productImagesIndexDiv.innerText = 2;
				} else {
					productImagesIndexDiv.innerText = 1;
				}
			},
			// 상품 설명 더보기 이벤트 등록
			registMoreCotentEvent : function(response) {
				var productContentDiv = document.querySelector(".section_store_details");
				var textArea = productContentDiv.querySelector(".dsc");
				// 텍스트 영역이 충분한경우 버튼을 숨기고 이벤트를 등록하지않음
				if (textArea.scrollHeight <= textArea.clientHeight) {
					productContentDiv.querySelector(".bk_more._open").style.display = "none"
					return;
				}
				productContentDiv.addEventListener("click", function(evt) {
					var className = evt.target.className;
					if (evt.target.parentElement.tagName === "A") {
						className = evt.target.parentElement.className;
					}
					if (className === "bk_more _open") {
						this.openMoreContent(productContentDiv)
					} else if (className === "bk_more _close") {
						this.closeMoreContent(productContentDiv)
					}
				}.bind(this));
			},
			// 더 보기 open
			openMoreContent : function(contentDiv) {
				contentDiv.querySelector(".bk_more._open").style.display = "none";
				contentDiv.querySelector(".bk_more._close").style.display = "block";
				contentDiv.querySelector(".store_details").classList.remove("close3");
			},
			// 더 보기 close
			closeMoreContent : function(contentDiv) {
				contentDiv.querySelector(".bk_more._open").style.display = "block";
				contentDiv.querySelector(".bk_more._close").style.display = "none";
				contentDiv.querySelector(".store_details").classList.add("close3");
			},
			// 상세정보/오시는길 Tab UI 등록
			registDetailInfoTabUI : function() {
				var DetailinfoTablistDiv = document.querySelector(".info_tab_lst");
				DetailinfoTablistDiv.addEventListener("click", function(evt) {
					if (evt.target.tagName === "SPAN") {
						this.toggleDetailInfoTab(evt.target.parentElement);
					} else if (evt.target.classList.contains("anchor")) {
						this.toggleDetailInfoTab(evt.target);
					}
				}.bind(this));
			},
			// 상세정보, 오시는길 TabUI Toggle
			toggleDetailInfoTab : function(target) {
				document.querySelector(".anchor.active").classList.remove("active");
				target.classList.add("active");
				if (target.parentElement.innerText === "상세정보") {
					document.querySelector(".detail_area_wrap").classList.remove("hide");
					document.querySelector(".detail_location").classList.add("hide");
				} else {
					document.querySelector(".detail_area_wrap").classList.add("hide");
					document.querySelector(".detail_location").classList.remove("hide");
				}
			},
			goCommentPage : function() {
				location.href="comment?displayInfoId=" + displayInfoId;
			}
		}

		document.addEventListener("DOMContentLoaded", function(event) {
			detail.init();
		});
	</script>
</body>
</html>
