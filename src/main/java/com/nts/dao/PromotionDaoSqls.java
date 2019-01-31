/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao;

/**
 * 프로모션 데이터에 대한 SQL문을 모아둔 클래스
 * @author jinwoo.bae
 */
public class PromotionDaoSqls {
	public static final String SELECT_LIMITED_LIST = "SELECT  "
		+ "    p.id AS id, "
		+ "    p.product_id AS productId, "
		+ "    fi.save_file_name AS productImageUrl "
		+ "FROM "
		+ "    promotion p, "
		+ "    product_image pi, "
		+ "    file_info fi "
		+ "WHERE "
		+ "    p.product_id = pi.product_id "
		+ "        AND pi.type = 'th' "
		+ "        AND pi.file_id = fi.id "
		+ "ORDER BY p.id ASC LIMIT :limit;";
}
