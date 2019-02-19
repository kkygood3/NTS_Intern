/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.productprice.model;

import java.util.List;

import com.nts.reservation.common.model.Response;
import com.nts.reservation.productdisplay.model.ProductDisplay;

public class ProductDisplayPriceResponse extends Response {

	private ProductDisplay productDisplay;
	private List<ProductPrice> productPriceList;
	private String reservationDate;

	public ProductDisplayPriceResponse() {}

	public ProductDisplayPriceResponse(ProductDisplay productDisplay, List<ProductPrice> productPriceList) {
		this.productDisplay = productDisplay;
		this.productPriceList = productPriceList;
	}

	@Override
	public String toString() {
		return "ProductPriceResponse [productDisplay=" + productDisplay + ", productPriceList=" + productPriceList
			+ ", reservationDate=" + reservationDate + "]";
	}

	public ProductDisplay getProductDisplay() {
		return productDisplay;
	}

	public void setProductDisplay(ProductDisplay productDisplay) {
		this.productDisplay = productDisplay;
	}

	public List<ProductPrice> getProductPriceList() {
		return productPriceList;
	}

	public void setProductPriceList(List<ProductPrice> productPriceList) {
		this.productPriceList = productPriceList;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

}
