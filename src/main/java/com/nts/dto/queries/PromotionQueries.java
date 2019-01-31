/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dto.queries;

public class PromotionQueries {
	public static final String PROMOTION_SELECT_ALL = "SELECT id,product_id FROM promotion";
	public static final String PROMOTION_SELECT_COUNT = "SELECT count(id) FROM promotion";
}
