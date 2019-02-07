package com.nts.dto.comment;

/**
 * @author 전연빈
 */
public class Comment {
	private String comment;						// 상품평
	private int commentId;						// 상품평 Id
	private CommentImage commentImage;			// 상품평 이미지 
	private String createDate;					// 생성일
	private String modifyDate;					// 수정일
	private int productId;						// 상품 Id
	private String reservationDate;				// 예약일
	private String reservationEmail;			// 예약자 이메일
	private int reservationInfoId;				// 예약 Id
	private String reservationName;				// 예약자 명
	private String reservationTelephone;		// 예약자 전화번호
	private double score;						// 평점
	private boolean hasImageFile;				// 이미지 파일 여부

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public CommentImage getCommentImage() {
		return commentImage;
	}

	public void setCommentImage(CommentImage commentImage) {
		this.commentImage = commentImage;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public boolean hasImageFile() {
		return hasImageFile;
	}

	public void setHasImageFile(boolean hasImageFile) {
		this.hasImageFile = hasImageFile;
	}
}
