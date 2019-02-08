/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.query;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class CategoryDaoQuerys {
	public static final String SELECT_CATEGORY = "SELECT id, name FROM category ORDER BY id DESC limit :limit";
}
