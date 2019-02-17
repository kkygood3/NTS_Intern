package com.nts.reservation.dto;

import java.util.List;

public class UserReservationInput {
	private String name;
	private String tel;
	private String email;
	private List<UserPriceInput> price;

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

	public List<UserPriceInput> getPrice() {
		return price;
	}

	public void setPrice(List<UserPriceInput> price) {
		this.price = price;
	}

	@Override
	public String toString() {
		String pre = "UserReservationInput [name=" + name + ", tel=" + tel + ", email=" + email + ", price=\n\t";
		String post = "\n]";
		String mid = "";
		for (UserPriceInput p : price) {
			mid += p + "\n";
		}
		return pre + mid + post;
	}


}
