package com.nts.reservation.dto;

public class ReservationInfoPrice {
	private long id;
	private long reservationInfoId;
	private long productPriceId;
	private long count;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	public long getProductPriceId() {
		return productPriceId;
	}
	public void setProductPriceId(long productPriceId) {
		this.productPriceId = productPriceId;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "ReservationInfoPrice [id=" + id + ", reservationInfoId=" + reservationInfoId + ", productPriceId="
			+ productPriceId + ", count=" + count + "]";
	}
	
	
	
}
