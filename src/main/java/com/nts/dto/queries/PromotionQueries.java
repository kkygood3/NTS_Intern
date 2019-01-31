/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dto.queries;

public class PromotionQueries {
	public static final String SELECT_PROMOTIONS = "SELECT id,product_id FROM promotion limit :limit";
	public static final String SELECT_COUNT = "SELECT count(id) FROM promotion";
}
