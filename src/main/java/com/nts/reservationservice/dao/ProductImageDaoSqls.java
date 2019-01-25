/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.dao;

public class ProductImageDaoSqls {
	public static final String SELECT_PRODUCT_IMAGE_BY_PRODUCT_ID_AND_TYPE = "SELECT id, product_id, type, file_id FROM product_image WHERE product_id = :id AND type = :type";
}
