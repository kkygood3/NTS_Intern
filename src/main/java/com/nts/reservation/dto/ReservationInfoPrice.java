package com.nts.reservation.dto;

public class ReservationInfoPrice {
	private Long id;
	private Long productId;
	private Long displayInfoId;
	private Long count;

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

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "ReservationInfoPrice [id=" + id + ", productId=" + productId + ", displayInfoId=" + displayInfoId
			+ ", count=" + count + "]";
	}
}
