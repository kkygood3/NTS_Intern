<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- saved from url=(0042)https://m.booking.naver.com/booked/confirm -->
<html lang="ko" class="no-js" >
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <style type="text/css">@charset "UTF-8";[ng\:cloak],[ng-cloak],[data-ng-cloak],[x-ng-cloak],.ng-cloak,.x-ng-cloak,.ng-hide:not(.ng-hide-animate){display:none !important;}ng\:form{display:block;}.ng-animate-shim{visibility:hidden;}.ng-anchor{position:absolute;}</style>
      <!--<base href="/">-->
      <title translate="CM-NBOOKING">네이버 예약</title>
      <link rel="stylesheet" href="/css/bookinglogin.css">
   </head>
   <body class="biz  ko" >
      <!-- 
      	Failed URL: ${url} 
    	Exception: ${exception.message}
    	<c:forEach items="${exception.stackTrace}" var="ste">
    		${ste}
   		</c:forEach>
      -->
      <app>
         <toast-alert class="top_info hide booking_alert">
            <p class="top_info_txt"> <i class="fn fn-info1" aria-hidden="true"></i> <span class="_toast_alert_text"></span> </p>
            <a href="/" class="top_info_close"  data-tst_alt_close="0"> <i class="fn fn-close" aria-hidden="true"></i> <span class="sr_only" translate="CM-CLOSE">닫기</span> </a> 
         </toast-alert>
         <!---->
         <div class="_view_content" ui-view="content" id="container" >
	         <div class="booking_login">
	            <h1 class="login_header"> <a href="/"  class="nbooking_logo spr_bi txt_logo" > <span translate="CM-NBOOKING">네이버 예약</span> </a> </h1>
	            <c:choose>
		            <c:when test="${errorMsg == null}">
		            	<p class="warning_msg" >요청한 페이지를 찾을 수 없습니다.</p>
		            </c:when>
		            <c:otherwise>
		            	<p class="warning_msg" >${errorMsg}</p>
		            </c:otherwise>
	            </c:choose>
	            <div class="link_container"><a href="/" class="link_to_main"><span>메인으로</span></a></div>
	         </div>
         </div>
         <footer  aria-hidden="false">
            <div id="footer" class="footer"  >
               <p class="dsc_footer" translate="CM-FOOTER_DESC">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
               <dl class="box_vcard">
                  <dt class="tit_dt"> <a href="https://m.booking.naver.com/#" class="lnk_naver"   title="펼쳐보기"> <span translate="CM-NAVER_INC">(주)네이버 사업자정보</span> <i class="fn fn-down2" aria-hidden="true" ></i> </a> </dt>
                  <!----> 
               </dl>
               <ul class="lst_link">
                  <li class="item"> <a class="anchor" ui-sref="policy" href="https://m.booking.naver.com/policy"> <span translate="CM-TERMS">이용약관</span> </a> </li>
                  <li class="item"> <a class="anchor" href="https://nid.naver.com/mobile/user/help/termAgree.nhn?m=viewTermAgree3"> <strong class="policy_em" translate="CM-PRIVACY_POLICY">개인정보처리방침</strong> </a> </li>
                  <li class="item"> <a href="https://m.booking.naver.com/#" class="anchor" > <span translate="CM-CUSTOMER_CENTER_NBOOKING">네이버 예약 고객센터</span> </a> </li>
               </ul>
               <span class="copyright" translate="CM-COPYRIGHT_NAVER">© NAVER Corp.</span> 
            </div>
            <!----> <!----> 
         </footer>
         <dialog-alert>
            <!----> 
         </dialog-alert>
         <translater-modal style="display: none;">
            <div class="sel_box_on">
               <div class="dimm_dark"  role="button" tabindex="0"></div>
               <div class="select_layer">
                  <div class="select_tit">
                     <h4 class="header_h4"> <span>언어선택</span> </h4>
                     <a href="https://m.booking.naver.com/#"  class="close"  title="MBK-selectBox-CLOSE"> <i class="fn fn-close" aria-hidden="true"></i> </a> 
                  </div>
                  <ul class="lst_select">
                     <li class="item" > <a href="https://m.booking.naver.com/#" class="anchor" > <span>한국어</span> </a> </li>
                     <li class="item" > <a href="https://m.booking.naver.com/#" class="anchor" > <span>English</span> </a> </li>
                  </ul>
               </div>
            </div>
         </translater-modal>
      </app>
   </body>
</html>