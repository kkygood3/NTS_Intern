<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

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
    </style>
</head>

<body>
    <div id="container">
        <div class="header fade">
            <header class="header_tit">
                <h1 class="logo">
                    <a class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
                    <a class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
                </h1>
                <a class="btn_my"> <span title="예약확인">예약확인</span> </a>
            </header>
        </div>
        <div class="ct main">
            <div>
                <div class="section_visual">
                    <header>
                        <h1 class="logo">
                            <a class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
                            <a class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
                        </h1>
                        <a class="btn_my"> <span class="viewReservation" title="예약확인">예약확인</span> </a>
                    </header>
                    <div class="pagination">
                        <div class="bg_pagination"></div>
                        <div class="figure_pagination">
                            <span class="num">1</span>
                            <span class="num">/ <span>2</span></span>
                        </div>
                    </div>
                    <div class="group_visual">
                        <div>
                            <div class="container_visual" style="width: 414px;">
                                <ul id="product_display_img_list" class="visual_img detail_swipe">
                                </ul>
                            </div>
                            
                            <div class="prev">
                                <div class="prev_inn">
                                    <a id="display_img_left_slide" class="btn_prev" title="이전">
                                        <!-- [D] 첫 이미지 이면 off 클래스 추가 -->
                                        <i class="spr_book2 ico_arr6_lt"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="nxt">
                                <div class="nxt_inn">
                                    <a id="display_img_right_slide" class="btn_nxt" title="다음">
                                        <i class="spr_book2 ico_arr6_rt"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="group_btn_goto"  style="display: none;">
                        <a class="btn_goto_home" title="홈페이지" href="#" target="siteUrl"> <i class="fn fn-home1"></i> </a>
                        <a class="btn_goto_tel" title="전화" href="#"> <i class="fn fn-call1"></i> </a>
						<a class="btn_goto_mail" title="이메일" href="#"> <i class="fn fn-mail1"></i> </a>
                        <a href="#" class="btn_goto_path" title="길찾기"> <i class="fn fn-path-find1"></i> </a>
                        <a href="#" class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
                    </div>
                </div>
                <div class="section_store_details">
                    <!-- [D] 펼쳐보기 클릭 시 store_details에 close3 제거 -->
                    <div class="store_details close3">
                        <p id="product_content" class="dsc">
                        </p>
                    </div>
                    <a id="btn_expand" class="bk_more _open"> <span class="bk_more_txt">펼쳐보기</span> <i class="fn fn-down2"></i> </a>
                    <a id="btn_fold" class="bk_more _close hide"> <span class="bk_more_txt">접기</span> <i class="fn fn-up2"></i> </a>
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
                                <span class="graph_mask"> <em id="star_score" class="graph_value" style="width: 0%;"></em> </span>
                                <strong class="text_value"> <span id="comment_avg_score">0.0</span> <em class="total">5.0</em> </strong>
                                <span class="join_count"><em class="green"><span id="comment_count">0</span>건</em> 등록</span>
                            </div>
                            <ul id="comment_list" class="list_short_review">
                            </ul>
                        </div>
                        <p class="guide"> <i class="spr_book2 ico_bell"></i> <span>네이버 예약을 통해 실제 방문한 이용자가 남긴 평가입니다.</span> </p>
                    </div>
                    <a class="btn_review_more" href="/product-display/comments?displayInfoId=${displayInfoId}"> <span>예매자 한줄평 더보기</span> <i class="fn fn-forward1"></i> </a>
                </div>
                <div class="section_info_tab">
                    <!-- [D] tab 선택 시 anchor에 active 추가 -->
                    <ul id="info_tab_list" class="info_tab_lst">
                        <li class="item active _detail">
                            <a class="anchor active" data-area-id="detail_area"> <span>상세정보</span> </a>
                        </li>
                        <li class="item _path">
                            <a class="anchor" data-area-id="location_area"> <span>오시는길</span> </a>
                        </li>
                    </ul>
                    <!-- [D] 상세정보 탭 선택 시 class에 show 추가 -->
                    <div id="detail_area" class="detail_area_wrap show">
                        <div class="detail_area">
                        <div class="detail_info">
                                <h3 class="blind">상세정보</h3>
                                <ul class="detail_info_group">
                                    <li class="detail_info_lst">
                                        <strong class="in_tit">[소개]</strong>
                                        <p id="product_introduce" class="in_dsc"></p>
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
                    <!-- [D] 오시는길 탭 선택 시 class에 show 추가 -->
                    <div id="location_area" class="detail_location">
                        <div class="box_store_info no_topline">
                            <a class="store_location" title="지도웹으로 연결">
                                <img id="location_display_img" class="store_map img_thumb" alt="map" src="">
                                <span class="img_border"></span>
                                <span class="btn_map"><i class="spr_book2 ico_mapview"></i></span>
                            </a>
                            <h3 id="location_product_description" class="store_name"></h3>
                            <div class="store_info">
                                <div class="store_addr_wrap">
                                    <span class="fn fn-pin2"></span>
                                    <p id="location_place_street" class="store_addr store_addr_bold"></p>
                                    <p class="store_addr">
                                        <span class="addr_old">지번</span>
                                        <span id="location_place_lot"class="addr_old_detail"></span>
                                    </p>
                                    <p id="location_place_name" class="store_addr addr_detail"></p>
                                </div>
                                <div class="lst_store_info_wrap">
                                    <ul class="lst_store_info">
                                        <li class="item"> 
                                        	<span class="item_lt"> 
                                        		<i class="fn fn-call2"></i> 
                                        		<span class="sr_only">전화번호</span> 
                                        	</span> 
                                        	<span class="item_rt"> 
                                        		<span id="location_telephone" class="store_tel"></span>
                                        	</span> 
                                        </li>
                                    </ul>
                                </div>
                            </div>
							<!-- [D] 모바일 브라우저에서 접근 시 column2 추가와 btn_navigation 요소 추가 -->
                            <div class="bottom_common_path column2">
                                <a class="btn_path"> <i class="fn fn-path-find2"></i> <span>길찾기</span> </a>
								<a class="btn_navigation before"> <i class="fn fn-navigation2"></i> <span>내비게이션</span> </a>
                            </div>
                        </div>
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
    <div id="photoviwer"></div>
    <script type="text/template" id="comment_template">
		<li class="list_item">
			<div>
				<div class="review_area {{#unless commentImageUrl}}no_img{{/unless}}">
					{{#if commentImageUrl}}
					<div class="thumb_area">
						<a class="thumb" title="이미지 크게 보기">
							<img width="90" height="90" class="img_vertical_top" src="/resources/{{commentImageUrl}}" alt="리뷰이미지">
						</a>
						<span class="img_count" style="display:none;">1</span>
					</div>
					{{/if}}
					<h4 class="resoc_name"></h4>
					<p class="review">{{comment}}</p>
				</div>
				<div class="info_area">
					<div class="review_info"> 
						<span class="grade">{{score}}.0</span>
						<span class="name">{{reservationEmail}}****</span>
						<span class="date">{{formattingDate reservationDate}} 방문</span>
					</div>
				</div>
			</div>
		</li>
	</script>
	<script type="text/template" id="product_display_img_template">
		<li class="item" style="width: 414px;"> <img alt="" class="img_thumb" src="/resources/{{productDisplayImageUrl}}"> <span class="img_bg"></span>
			<div class="visual_txt">
				<div class="visual_txt_inn">
					<h2 class="visual_txt_tit"> <span>{{productDescription}}</span> </h2>
					<p class="visual_txt_dsc"></p>
				</div>
			</div>
		</li>
	</script>
	
	<script type="text/javascript" src="/resources/js/handlebars-v4.0.12.js"></script>
	<script type="text/javascript" src="/resources/js/detail_template.js"></script>
	<script type="text/javascript" src="/resources/js/common.js"></script>
	<script>
	document.addEventListener("DOMContentLoaded", () => {
		initializeTemplate();
		
		ajax("GET", "/api/products/${displayInfoId}", "", printProductDisplay);
		
		addEventClickProductContentFoldExpand();
		addEventClickInfoTabList();
		addEventClickProductDisplayImgSlide();
	});
	
	function printProductDisplay(evt){
		var response = evt.currentTarget.response;
		var productDisplayInfo = JSON.parse(response);
		
		var productDisplay = productDisplayInfo.productDisplay;
		var productDisplayImgUrlItems = productDisplay.productDisplayImageUrlList;
		var commentListInfo = productDisplayInfo.commentListInfo;
		
		document.querySelector("#product_content").innerHTML = productDisplay.productContent;
		document.querySelector("#product_introduce").innerHTML = productDisplay.productContent;
		
		printProductDisplayImg(productDisplayImgUrlItems, productDisplay.productDescription);
		printComment(commentListInfo);
		printLocationDetail(productDisplay);
		

	}
	
	function printComment(commentListInfo){
		const SCORE_TO_PERCENT = 20;
		var commentItems = commentListInfo.commentList;
		var commentCount = commentListInfo.commentMetaData.totalCount;
		var commentAvgScore = commentListInfo.commentMetaData.averageScore;
		
		document.querySelector("#comment_count").innerHTML = commentCount;
		document.querySelector("#comment_avg_score").innerHTML = commentAvgScore.toFixed(1);
		document.querySelector("#star_score").style.width = commentAvgScore * SCORE_TO_PERCENT + "%";
		
		var commentList = document.querySelector("#comment_list");
		commentItems.forEach((commentItem) =>{
			commentList.innerHTML += parseCommentToHtml(commentItem);
		});
	}
	
	function printProductDisplayImg(productDisplayImgUrlItems, productDescription){
		var productDisplayImgList = document.querySelector("#product_display_img_list");
		productDisplayImgUrlItems.forEach((imgUrlItem) =>{
			productDisplayImgList.innerHTML += parseProductDisplayImgToHtml(imgUrlItem, productDescription);
		});
	}
	
	function printLocationDetail(productDisplay){
		document.querySelector("#location_display_img").src = "/resources/" +productDisplay.displayInfoImageUrl;
		document.querySelector("#location_product_description").textContent = productDisplay.productDescription;
		document.querySelector("#location_place_street").textContent = productDisplay.placeStreet;
		document.querySelector("#location_place_lot").textContent = productDisplay.placeLot;
		document.querySelector("#location_place_name").textContent = productDisplay.placeName;
		document.querySelector("#location_telephone").textContent = productDisplay.telephone;
	}
	
	var slide = {
		PAGE : 100,
		direction : {
			LEFT : 0,
			RIGHT : -1
		},
		action : (target, direction) =>{
			target.className += " slide";
			target.style.transform = "translateX(" + slide.PAGE * direction +"%)";
		},
		transitionend : () => {
			slide.callback();
		},
		
	}
	function addEventClickProductDisplayImgSlide(){
		var productDisplayImgList = document.querySelector("#product_display_img_list");
		var btnLeftSlide = document.querySelector("#display_img_left_slide");
		var btnRightSlide = document.querySelector("#display_img_right_slide");
		var originalClass = productDisplayImgList.className;
		

		productDisplayImgList.addEventListener("transitionend", slide.transitionend);
		
		btnLeftSlide.addEventListener("click", () =>{
			slide.callback = readjustImgList.bind(null, productDisplayImgList, originalClass, -100);
			slide.action(productDisplayImgList, slide.direction.LEFT);
		});
		
		btnRightSlide.addEventListener("click", () =>{
			slide.callback = readjustImgList.bind(null, productDisplayImgList, originalClass, 0);
			slide.action(productDisplayImgList, slide.direction.RIGHT);
		});
	}
	
	function readjustImgList(productDisplayImgList, originalClass, v){
		productDisplayImgList.className = originalClass;
		productDisplayImgList.style.transform = "translate("+v+"%)";
		var img = productDisplayImgList.removeChild(productDisplayImgList.firstElementChild)
		productDisplayImgList.innerHTML = productDisplayImgList.innerHTML + img.outerHTML;
	}
	
	function addEventClickProductContentFoldExpand(){
		var btnExpand = document.querySelector("#btn_expand");
		var btnFold = document.querySelector("#btn_fold");
		var productContentArea = document.querySelector("#product_content").parentElement;
		
		btn_expand.addEventListener("click", () =>{
			btnExpand.className = "bk_more _open hide";
			btnFold.className = "bk_more _close";
			productContentArea.className = "store_details";
		});
		
		btn_fold.addEventListener("click", () =>{
			btnExpand.className = "bk_more _open";
			btnFold.className = "bk_more _close hide";
			productContentArea.className = "store_details close3";
		});
	}
	
	function addEventClickInfoTabList(){
		var infoTabList = document.querySelector("#info_tab_list");
		
		infoTabList.addEventListener("click", (evt) =>{
			var paths = evt.path;
			
			paths.some((path) => {
		        if(path.tagName === "A" && path.className === "anchor"){
					document.querySelector("#detail_area").className = "detail_area_wrap";
					document.querySelector("#location_area").className = "detail_location";
		            infoTabList.querySelector(".anchor.active").className = "anchor";
		            path.className = "anchor active";
					
		            var areaId = path.dataset.areaId;
		            document.querySelector("#"+areaId).className += " show";
		            
		            return true;
		        	}
		        return false;
		    });
		});
	}
	
	</script>
</body>


</html>
