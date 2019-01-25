/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.dao;

public class DisplayInfoDaoSqls {
	public static final String SELECT_DISPLAYINFO_BY_PRODUCT_ID = "SELECT id, product_id, opening_hours, place_name, place_lot, place_street, tel, homepage, email, create_date, modify_date FROM display_info WHERE product_id = :id";
}
