<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="utf-8">
	<meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
	<title>네이버 예약</title>
	<link href="/css/style.css" rel="stylesheet">
</head>

<body>
	<div id="container">
		<div class="header fade">
			<header class="header_tit">
				<h1 class="logo">
					<a href="/main" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
					<a href="/main" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
				</h1>
				  <a href="#" class="btn_my"> <span title="예약확인">예약확인</span> </a>
			</header>
		</div>
		<div class="ct">
			<div class="ct_wrap">
				<div class="top_title review_header">
					<a href="/myreservation" class="btn_back" title="이전 화면으로 이동"> <i class="fn fn-backward1"></i> </a>
					<h2><span class="title">${productDescription}</span></h2>
				</div>
				<!-- 리뷰 별점 -->
				<div class="write_act">
					<p class="title_star">별점과 이용경험을 남겨주세요.</p>
					<div class="review_rating rating_point">
						<div class="rating">
							<!-- [D] 해당 별점이 선택될 때 그 점수 이하의 input엘리먼트에 checked 클래스 추가 -->
							<input name="rating2" value="1" class="rating_rdo" title="1점">
							<span class="span"></span>
							<input name="rating3" value="2" class="rating_rdo" title="2점">
							<span class="span"></span>
							<input name="rating4" value="3" class="rating_rdo" title="3점" >
							<span class="span"></span>
							<input name="rating5" value="4" class="rating_rdo" title="4점">
							<span class="span"></span>
							<input name="rating6" value="5" class="rating_rdo" title="5점">
							<span class="span"></span>
							<!-- [D] 0점일 때 gray_star 추기 -->
							<span class="star_rank gray_star">0</span>
						</div>
					</div>
				</div>
				<!-- //리뷰 별점 -->

				<!-- 리뷰 입력 -->
				<div class="review_contents write">
					<!-- [D] review_write_info 클릭 시 자신을 숨기고 review_textarea 에 focus를 보낸다. -->
					<a class="review_write_info">
						<span class="middot">
							실 사용자의 리뷰는 상품명의 더 나은 서비스 제공과 다른 사용자들의 선택에 큰 도움이 됩니다.
						</span><br>
						<span class="middot">
							소중한 리뷰에 대한 감사로 네이버페이 포인트 500원을 적립해드립니다.
						</span>
						<span class="left_space">(단, 리뷰 포인트는 ID 당 1일 최대 5건까지 지급됩니다.)</span>
					</a>
					<textarea cols="30" rows="10" maxlength="400" class="review_textarea"></textarea>
				</div>
				<!-- //리뷰 입력 -->

				<!-- 리뷰 작성 푸터 -->
				<div class="review_write_footer_wrap">
					<div class="review_write_footer">
						<label class="btn_upload" for="reviewImageFileOpenInput">
							<i class="fn fn-image1" aria-hidden="true"></i>
							<span class="text_add_photo">사진 추가</span>
							<img
						</label>
						<input type="file" class="hidden_input" id="reviewImageFileOpenInput">
						<div class="guide_review">
							<span>0</span>/400
							<span>(최소5자이상)</span>
						</div>
					</div>

					<!-- 리뷰 포토 -->
					<div class="review_photos review_photos_write">
						<div class="item_preview_thumbs">
							<ul class="lst_thumb">
								<li class="item" style="display: none;">
									<a class="anchor">
										<span class="spr_book ico_del">삭제</span>
									</a>
									<img src="" width="100" alt="" class="item_thumb">
									<!-- <span class="img_border"></span> -->
								</li>
							</ul>
						</div>
					</div>
					<!-- //리뷰 포토 -->

				</div>
				<!-- //리뷰 작성 푸터 -->

				<!-- 리뷰 등록 -->
				<div class="box_bk_btn">
					<button class="bk_btn"><span class="btn_txt">리뷰 등록</span></button>
				</div>
				<!-- //리뷰 등록 -->
			</div>
		</div>
	</div>
	<footer>
		<div class="gototop">
			<a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span> </a>
		</div>
		<div id="footer" class="footer">
			<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
			<span class="copyright">© NAVER Corp.</span>
		</div>
	</footer>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script>
		var reservationId = parseInt(window.location.pathname.split("/")[2]);
		var productId = parseInt(new URL(window.location.href).searchParams.get("productId"));

		// 별점매기기 기능
		function StarRating(body) {
			this.body = body;
			this.value = 0;
			this.registerEvents();
		}

		StarRating.prototype = {
			registerEvents: function () {
				this.body.addEventListener("click", function(evt) {
					if (evt.target.tagName !== "INPUT") {
						return;
					}
					this.value = evt.target.value;
					this.showRating();
				}.bind(this));
			},
			showRating: function () {
				this.body.querySelectorAll("input").forEach(function (element) {
					if (this.value >= element.value) {
						element.classList.add("checked");
					} else {
						element.classList.remove("checked");
					}
				}.bind(this));
				document.querySelector(".star_rank").innerText = this.value;
				document.querySelector(".star_rank").classList.remove("gray_star");
			}
		}

		var ratingBody = document.querySelector(".rating");
		var starRating = new StarRating(ratingBody);


		// 상품평 글자수 표시 및 가이드라인 숨김 이벤트 기능
		function CommentTextArea(body) {
			this.body = body;
			this.text = "";
			this.registerEvents();
		}

		CommentTextArea.prototype = {
			registerEvents: function () {
				// 텍스트 영역 클릭시 설명문 숨김 및 텍스트영역 포커스
				document.querySelector(".review_write_info").addEventListener("click", function(evt) {
					document.querySelector(".review_write_info").style.display = "none";
					this.body.focus();
				}.bind(this));
				// 입력된 글자 text필드변수에 저장 및 글자수 표시
				this.body.addEventListener("input", function(evt) {
					this.text = this.body.value;
					document.querySelector(".guide_review span").innerText = this.text.length;
				}.bind(this));
			}
		}

		var commentBody = document.querySelector(".review_textarea");
		var commentTextArea = new CommentTextArea(commentBody);

		document.querySelector(".review_textarea");

		// 이미지 input 이벤트리스너 등록
		const elImage = document.querySelector("#reviewImageFileOpenInput");
		elImage.addEventListener("change", function(evt) {
			const image = evt.target.files[0];
			if(!validImageType(image)) {
				alert("지원하지 않는 이미지 타입 확장자입니다.")
				return;
			}
			// 썸네일 노출
			document.querySelector(".lst_thumb .item").style.display = "block";
			const elThumbImage = document.querySelector(".item_thumb");
			elThumbImage.src = window.URL.createObjectURL(image);
		})

		// 이미지 확장자 검사
		function validImageType(image) {
			const result = ([ 'image/jpeg',
							  'image/png',
							  'image/jpg' ].indexOf(image.type) > -1);
			return result;
		}

		// 이미지 선택 취소
		document.querySelector(".spr_book.ico_del").addEventListener("click", function(evt) {
			// 썸네일 구역 숨김
			document.querySelector(".lst_thumb .item").style.display = "none";
			document.querySelector(".item_thumb").src = "";
			// 이미지 input value 초기화
			elImage.value = "";
		})

		// 리뷰 등록
		document.querySelector(".bk_btn").addEventListener("click", function(evt) {
			if (!starRating.value) {
				alert("별점을 선택해주세요");
				return;
			}
			if (commentTextArea.text.length < 5 || commentTextArea.text.length > 400) {
				alert("상품평이 너무 길거나 짧습니다.");
				return;
			}

			// 폼 전송 데이터 준비
			var formData = new FormData();
			formData.append("score", starRating.value);
			formData.append("comment", commentTextArea.text);
			if (elImage.files[0]) {
				formData.append("image", elImage.files[0]);
			}

			var url = "/api/reservations/" + reservationId + "/comments?productId=" + productId;
			ajaxFile(url, formData);

		});

		function ajaxFile(url, data) {
			$.ajax({
				type: "POST",
				enctype: 'multipart/form-data',
				url: url,
				data: data,
				processData: false,
				contentType: false,
				cache: false,
				timeout: 600000,
				success: function (data) {
					if (confirm("등록 성공! 나의예약페이지로 이동하시겠습니까?")) {
						location.href="/myreservation";
					}
				},
				error: function (e) {
					alert("등록 실패!");
					console.log("ERROR : ", e);
				}
			});
		}
	</script>
</body>
</html>
