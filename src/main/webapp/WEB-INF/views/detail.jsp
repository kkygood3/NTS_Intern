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
						<p class="dsc"></p>
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
									<em class="graph_value" style="width: 84%;"></em>
								</span> 
								<strong class="text_value">
									<span></span> 
									<em class="total">5.0</em>
								</strong>
								<span class="join_count"> <em class="green"></em> 등록</span>
							</div>
							<!-- [D] 상품평 넣는 구역 -->
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
										<p class="in_dsc"></p>
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
							<a href="#" class="store_location" title="지도웹으로 연결">
								<img class="store_map img_thumb" alt="map">
								<span class="img_border"></span>
								<span class="btn_map">
									<i class="spr_book2 ico_mapview"></i>
								</span>
							</a>
							<h3 class="store_name">{{productDescription}}</h3>
							<div class="store_info">
								<div class="store_addr_wrap">
									<span class="fn fn-pin2"></span>
									<p class="store_addr store_addr_bold">{{placeLot}}</p>
									<p class="store_addr">
										<span class="addr_old">지번</span>
										<span class="addr_old_detail">{{placeStreet}}</span>
									</p>
									<p class="store_addr addr_detail">{{placeName}}</p>
								</div>
								<div class="lst_store_info_wrap">
									<ul class="lst_store_info">
										<li class="item">
											<span class="item_lt">
												<i class="fn fn-call2"></i>
												<span class="sr_only">전화번호</span>
											</span> 
											<span class="item_rt">
												<a class="store_tel">{{telephone}}</a>
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
	<script type="text/javascript" src="/js/comment.js"></script>
	<script>
		var detail = {
			// DIV Elements
			productImagesDiv : document.querySelector('.visual_img.detail_swipe'),
			currentNumDiv : document.querySelector(".num"),
			lastNumDiv : document.querySelector(".num.off"),
			productContentDiv : document.querySelector(".section_store_details"),
			// init
			init : function(displayInfoId) {
				this.loadDisplayInfoResponse(displayInfoId);
			},
			loadDisplayInfoResponse : function(displayInfoId) {
				if (!isNumber(displayInfoId)) {
					alert("잘못된 파라미터임니다");
					window.history.back();
					return;
				}
				var url = "/api/products/" + displayInfoId;
				ajax(this.initDisplayInfo.bind(this), url);
			},
			initDisplayInfo : function(response) {
				this.setTitleDOM(response);
				this.setProductContentDOM(response);
				this.setDetailInformationDOM(response);
				productId = response.displayInfo.productId;
				comment.init(productId, 0, 3);
			},
			// 타이틀 구역 설정
			setTitleDOM : function(response) {
				// 이미지 페이징 번호값 지정
				this.currentNumDiv.innerText = 1;
				this.lastNumDiv.innerText = "/ " + response.productImages.length;
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
				this.productImagesDiv.innerHTML = resultHTML;
				this.initSlide(); 
			},
			initSlide : function() {
				var prevDiv = document.querySelector('.prev');
				var nextDiv = document.querySelector('.nxt');
				if (this.productImagesDiv.childElementCount < 2) {
					prevDiv.style.display = "none";
					nextDiv.style.display = "none";
				} else {
					this.productImagesDiv.innerHTML += this.productImagesDiv.innerHTML;
					this.productImagesDiv.style.transform = "translateX(-200%)";
					var left = 0;
					var translateX = -200;
					prevDiv.addEventListener("click", function(evt) {
						if (evt.target.tagName === "A" || evt.target.tagName === "I") {
							left += 100;
							translateX -= 100;
							this.doSlide(left, translateX);
						}
					}.bind(this));
					nextDiv.addEventListener("click", function(evt) {
						if (evt.target.tagName === "A" || evt.target.tagName === "I") {
							left -= 100;
							translateX += 100;
							this.doSlide(left, translateX);
						}
					}.bind(this));
				}
			},
			doSlide : function(left, translateX) {
				this.productImagesDiv.style.left = left + "%";
				if (translateX % 200 == 0) {
					this.productImagesDiv.style.transform = "translateX(" + translateX + "%)";
				}
				if (this.currentNumDiv.innerText === "1") {
					this.currentNumDiv.innerText = 2;
				} else {
					this.currentNumDiv.innerText = 1;	
				}
			},
			// 상품 설명 설정
			setProductContentDOM : function(response) {
				this.productContentDiv.querySelector(".dsc").innerText = response.displayInfo.productContent;
				this.productContentDiv.addEventListener("click", function(evt) {
					if (evt.target.className === "bk_more _open") {
						this.productContentDiv.querySelector(".bk_more._open").style.display = "none";
						this.productContentDiv.querySelector(".bk_more._close").style.display = "block";
						this.productContentDiv.querySelector(".store_details").classList.remove("close3");
					} else if (evt.target.className === "bk_more _close") {
						this.productContentDiv.querySelector(".bk_more._open").style.display = "block";
						this.productContentDiv.querySelector(".bk_more._close").style.display = "none";
						this.productContentDiv.querySelector(".store_details").classList.add("close3");
					}
				}.bind(this));
			},
			// 상세정보 구역 설정
			setDetailInformationDOM : function(response) {
				// 상세정보 
				document.querySelector(".detail_info_lst .in_dsc").innerText = response.displayInfo.productContent;
				// 오시는길
				document.querySelector(".store_map.img_thumb").src = response.displayInfoImage.saveFileName;
				document.querySelector(".store_name").innerText = response.displayInfo.productDescription;
				document.querySelector(".store_addr.store_addr_bold").innerText = response.displayInfo.placeLot;
				document.querySelector(".addr_old_detail").innerText = response.displayInfo.placeStreet;
				document.querySelector(".store_addr.addr_detail").innerText = response.displayInfo.placeName;
				document.querySelector(".store_tel").innerText = response.displayInfo.telephone;
				// 탭UI 이벤트 등록
				var infoTablistDiv = document.querySelector(".info_tab_lst");
				infoTablistDiv.addEventListener("click", function(evt) {
					if (evt.target.tagName === "SPAN") {
						this.toggleDetailInfoTab(evt.target.parentElement);
					} else if (evt.target.classList.contains("anchor")) {
						this.toggleDetailInfoTab(evt.target);
					}
				}.bind(this));
				infoTablistDiv.querySelector("._detail").classList.contains("item2")
			},
			// 상세정보/오시는길 Tab UI
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
			}
		}
		var displayInfoId = parseInt(new URL(window.location.href).searchParams.get("id"));
		var productId;
		detail.init(displayInfoId);

		function goReviewPage() {
			location.href="review?productId=" + productId;
		}
	</script>
</body>
</html>
