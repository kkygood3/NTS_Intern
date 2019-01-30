<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>네이버 예약</title>
<link href="./css/style.css" rel="stylesheet">
<link href="./css/main_slide.css" rel="stylesheet">
<script type= "text/javascript" src= "./javascript/main/main.js" ></script>
</head>
<body>
    <div id="container">
        <div class="header">
            <header class="header_tit">
                <h1 class="logo">
                    <span class="spr_bi ico_n_logo">네이버</span>
                    <span class="spr_bi ico_bk_logo">예약</span>
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
                <!-- content -->
                </ul>
            </div>
            <div class="section_event_lst">
                <p class="event_lst_txt">바로 예매 가능한 행사가 <span class="pink">10개</span> 있습니다</p>
                <div class="wrap_event_box">
                    <!-- [D] lst_event_box 가 2컬럼으로 좌우로 나뉨, 더보기를 클릭할때마다 좌우 ul에 li가 추가됨 -->
                    <ul class="lst_event_box">
                    </ul>
                    <ul class="lst_event_box">
                    </ul>
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


    <script type="rv-template" id="promotion_item">
    <li class="item" style="background-image: url(./{sava_file_name_list});">
    </li>
    </script>

    <script type="rv-template" id="product_item">
	<li class="item">
		<div class="item_preview">
			<img alt="{description}" class="img_thumb" src="./{save_file_name}">
		</div>
		<div class="event_txt">
			<h4 class="event_txt_tit"> <span>{description}</span> <small class="sm">{place_name}</small> </h4>
			<p class="event_txt_dsc">{content}</p>
		</div>
	</li>
    </script>
    <script type="rv-template" id="category_item">
    <li class="item" data-category="{id}">
		<a class="anchor active"> <span>{name}</span> </a>
	</li>
	</script>
</body>
</html>