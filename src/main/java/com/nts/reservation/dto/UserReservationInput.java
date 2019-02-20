package com.nts.reservation.dto;

import java.util.Date;
import java.util.List;

public class UserReservationInput {
	private long productId;
	private String name;
	private String tel;
	private String email;
	private String reservationDate;
	private List<ReservationInfoPrice> price;

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public List<ReservationInfoPrice> getPrice() {
		return price;
	}

	public void setPrice(List<ReservationInfoPrice> price) {
		this.price = price;
	}

	@Override
	public String toString() {
		String pre = "UserReservationInput [name=" + name + ", tel=" + tel + ", email=" + email + ", price=\n\t";
		String post = "\n]";
		String mid = "";
		for (ReservationInfoPrice p : price) {
			mid += p + "\n";
		}
		return pre + mid + post;
	}
}
