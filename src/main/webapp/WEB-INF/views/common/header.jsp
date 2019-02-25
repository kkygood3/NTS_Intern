<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<body>
	<div class="header fade">
		<header class="header_tit">
			<h1 class="logo">
				<a href="https://m.naver.com/" class="lnk_logo" title="네이버"><span class="spr_bi ico_n_logo">네이버</span></a>
				<a href="/" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span></a>
			</h1>
			<c:if test="${ email ne null }">
				<a href="/myreservation" class="btn_my"> <span class="viewReservation" title="예약확인">${ email }</span></a>
			</c:if>
			<c:if test="${ email eq null }">
				<a href="/login" class="btn_my"> <span class="viewReservation" title="예약확인">예약확인</span></a>
			</c:if>
		</header>
	</div>
</body>
</html>