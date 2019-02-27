<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네이버 예약</title>
	<link href="/reservation/resources/css/style.css" rel="stylesheet">
	<link rel="shortcut icon" href="/reservation/resources/img/favicon.ico">
</head>
<body>
	<div id="container" style="min-height: 100px;">
		<div class="header">
			<header class="header_tit">
				<h1 class="logo">
					<a href="https://m.naver.com/" class="lnk_logo" title="네이버"> <span
						class="spr_bi ico_n_logo">네이버</span>
					</a> <a href="/reservation/" class="lnk_logo" title="예약"> <span
						class="spr_bi ico_bk_logo">예약</span>
					</a>
				</h1>
				<a href="/reservation/myreservation" class="btn_my"> <span
					class="viewReservation" title="예약확인">
						<%
							if (session.getAttribute("email") == null) {
						%>예약확인<%
							} else {
						%> <%=session.getAttribute("email")%>
						<%
							}
						%>
				</span>
				</a>
			</header>
		</div>
	</div>
	<div>
		<h1 style="text-align: center; min-height: 100px;">${requestScope['javax.servlet.error.status_code']}입니다 ㅠㅠ</h1>
		<h1 style="text-align: center; min-height: 100px;">원인 : ${requestScope['javax.servlet.error.message']}</h1>
	</div>

	<footer>
		<div class="footer">
				
			
		</div>
		<div class="gototop">
		
			<a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span>
			</a>
		</div>
		<div class="footer">
			<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및
				환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
			<span class="copyright">© NAVER Corp.</span>
		</div>
		<%
			if (session.getAttribute("email") != null) {
		%>
		<div class="logout">
			<a href="#" onClick ="logout()"> <span class="lnk_top_text">로그아웃</span>
			</a>
		</div>
		<%}%> 
		
	</footer>
</body>
</html>