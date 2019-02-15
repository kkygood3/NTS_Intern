/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sql;

/**
 * @author jinwoo.bae
 *
 */
public class DisplayInfoDaoSqls {
	public static final String SELECT_DISPLAY_INFO = ""
		+ "SELECT "
		+ "    di.id AS id, "
		+ "    p.description AS productDescription, "
		+ "    p.content AS productContent, "
		+ "    di.place_name AS placeName, "
		+ "    di.place_lot AS placeLot, "
		+ "    di.place_street AS placeStreet, "
		+ "    di.tel AS telephone, "
		+ "    fi.save_file_name AS saveFileName "
		+ "FROM "
		+ "    display_info di "
		+ "        INNER JOIN "
		+ "    display_info_image dii ON di.id = dii.display_info_id "
		+ "        INNER JOIN "
		+ "    file_info fi ON dii.file_id = fi.id "
		+ "        INNER JOIN "
		+ "    product p ON p.id = di.product_id "
		+ "WHERE "
		+ "    di.id = :displayInfoId";
}
