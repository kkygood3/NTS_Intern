<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko" class="no-js" >
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <style type="text/css">@charset "UTF-8";[ng\:cloak],[ng-cloak],[data-ng-cloak],[x-ng-cloak],.ng-cloak,.x-ng-cloak,.ng-hide:not(.ng-hide-animate){display:none !important;}ng\:form{display:block;}.ng-animate-shim{visibility:hidden;}.ng-anchor{position:absolute;}</style>
      <base href=".">
      <meta http-equiv="X-UA-Compatible" content="IE=Edge">
      <meta http-equiv="cache-control" content="no-cache">
      <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT">
      <meta http-equiv="pragma" content="no-cache">
      <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
      <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
      <meta name="format-detection" content="telephone=no, address=no, email=no">
      <title translate="CM-NBOOKING">네이버 예약</title>
      <link rel="stylesheet" href="../css/bookinglogin.css">
   </head>
   <body class="biz  ko" >
      <app>
         <toast-alert class="top_info hide booking_alert">
            <p class="top_info_txt"> <i class="fn fn-info1" aria-hidden="true"></i> <span class="_toast_alert_text"></span> </p>
            <a href="./mainpage.html" class="top_info_close"  data-tst_alt_close="0"> <i class="fn fn-close" aria-hidden="true"></i> <span class="sr_only" translate="CM-CLOSE">닫기</span> </a> 
         </toast-alert>
         <!---->
         <div class="_view_content" ui-view="content" id="container" >
            <booked-confirm>
               <div class="booking_login">
                  <h1 class="login_header"> <a href="./mainpage.html"  class="nbooking_logo spr_bi txt_logo" > <span translate="CM-NBOOKING">네이버 예약</span> </a> </h1>
                  <!---->
                  <div >
                     <form name="confirm_form" class="ng-pristine ng-valid" id="form1" method="post" action="/login">
                        <h2 class="login_header_sub border_bottom"> <span translate="CM-NON_MEMBER_BK_CONFIRMATION">비회원 예약확인</span> </h2>
                        <div class="login_form"> 
                           <label class="label_form" for="resrv_id" translate="CM-BOOKING_NUMBER">예약자 이메일 입력</label> 
                           <input type="text" class="login_input ng-pristine ng-untouched ng-valid ng-empty" id="resrv_id" name="email" aria-invalid="false" placeholder="crong@naver.com" title="예매자이메일">  </div>
                        <button type="submit" form="form1" class="login_btn confirm" > <span translate="CM-MY_BOOKING_CHECK">내 예약 확인</span> </button>
                     </form>
                  </div>
                  <!----> <!----> 
               </div>
            </booked-confirm>
         </div>
         <footer>
		<%@ include file="/WEB-INF/views/footer.jsp"%>
		</footer>
      </app>
   </body>
</html>
