/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sql;

/**
 * 프로모션 데이터에 대한 SQL문을 모아둔 클래스
 * @author jinwoo.bae
 */
public class PromotionDaoSqls {
	public static final String SELECT_PRPMOTIONS = ""
		+ "SELECT  "
		+ "    p.id AS id, "
		+ "    p.product_id AS productId, "
		+ "    di.id AS displayInfoId, "
		+ "    fi.save_file_name AS productImageUrl, "
		+ "    di.place_Street AS placeStreet "
		+ "FROM "
		+ "    promotion p "
		+ "        INNER JOIN "
		+ "    product_image pi ON p.product_id = pi.product_id "
		+ "        INNER JOIN "
		+ "    file_info fi ON pi.file_id = fi.id "
		+ "        INNER JOIN "
		+ "    display_info di ON p.product_id = di.product_id "
		+ "WHERE "
		+ "    pi.type = :type "
		+ "ORDER BY p.id ASC LIMIT :limit";
}
