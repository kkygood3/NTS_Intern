package com.nts.reservation.dto;

import java.util.Date;

public class ReservationUserComment {
	private Long id;
	private Long productId;
	private Long displayInfoId;
	private int score;
	private String text;
	private Date createDate;
	private Date modifyDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(Long displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "ReservationUserComment [id=" + id + ", productId=" + productId + ", displayInfoId=" + displayInfoId
			+ ", score=" + score + ", text=" + text + ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}

}
