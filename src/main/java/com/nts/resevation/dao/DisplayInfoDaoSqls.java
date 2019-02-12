/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.resevation.dao;

/**
 * @author jinwoo.bae
 *
 */
public class DisplayInfoDaoSqls {
	public static final String SELECT_DISPLAY_INFO = ""
		+ "SELECT "
		+ "    p.id AS productId, "
		+ "    c.id AS categoryId, "
		+ "    di.id AS displayInfoId, "
		+ "    c.name AS categoryName, "
		+ "    p.description AS productDescription, "
		+ "    p.content AS productContent, "
		+ "    p.event AS productEvent, "
		+ "    di.opening_hours AS openingHours, "
		+ "    di.place_name AS placeName, "
		+ "    di.place_lot AS placeLot, "
		+ "    di.place_street AS placeStreet, "
		+ "    di.tel AS telephone, "
		+ "    di.homepage AS homepage, "
		+ "    di.email AS email, "
		+ "    di.create_date AS createDate, "
		+ "    di.modify_date AS modifyDate "
		+ "FROM "
		+ "    display_info di, "
		+ "    product p, "
		+ "    category c "
		+ "WHERE "
		+ "    di.product_id = p.id "
		+ "        AND p.category_id = c.id "
		+ "        AND di.id = :displayInfoId ";
	public static final String SELECT_DISPLAY_INFO_IMAGE = ""
		+ "SELECT  "
		+ "    dii.id AS displayInfoImageId, "
		+ "    dii.display_info_id AS displayInfoId, "
		+ "    fi.id AS fileId, "
		+ "    fi.file_name AS fileName, "
		+ "    fi.save_file_name AS saveFileName, "
		+ "    fi.content_type AS contentType, "
		+ "    fi.delete_flag AS deleteFlag, "
		+ "    fi.create_date AS createDate, "
		+ "    fi.modify_date AS modifyDate "
		+ "FROM "
		+ "    display_info_image dii, "
		+ "    file_info fi "
		+ "WHERE "
		+ "    dii.file_id = fi.id "
		+ "        AND dii.display_info_id = :displayInfoId";
}
