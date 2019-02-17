package com.nts.reservation.dto;

public class UserPriceInput {
	private long productPriceId;
	private int count;
	private int price;

	public long getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(long productPriceId) {
		this.productPriceId = productPriceId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "UserPriceInput [productPriceId=" + productPriceId + ", count=" + count + ", price=" + price + "]";
	}
}
