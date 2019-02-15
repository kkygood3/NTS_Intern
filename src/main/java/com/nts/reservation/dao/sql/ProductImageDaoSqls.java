/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sql;

/**
 * 상품 이미지 SQLs
 * @author jinwoo.bae
 */
public class ProductImageDaoSqls {
	public static final String SELECT_PRODUCT_IMAGE_COUNT_BY_PRODUCT_ID_AND_TYPE = ""
		+ "SELECT "
		+ "    COUNT(id) "
		+ "FROM "
		+ "    product_image "
		+ "WHERE "
		+ "    product_id = :productId AND type = :type";
	public static final String SELECT_PRODUCT_IMAGES_BY_TYPE = ""
		+ "SELECT "
		+ "    pi.product_id AS productId, "
		+ "    pi.id AS productImageId, "
		+ "    pi.type AS type, "
		+ "    fi.id AS fileInfoId, "
		+ "    fi.file_name AS fileName, "
		+ "    fi.save_file_name AS saveFileName, "
		+ "    fi.content_type AS contentType, "
		+ "    fi.delete_flag AS deleteFlag, "
		+ "    fi.create_date AS createDate, "
		+ "    fi.modify_date AS modifyDate "
		+ "FROM "
		+ "    product_image pi "
		+ "        INNER JOIN "
		+ "    file_info fi ON pi.file_id = fi.id "
		+ "WHERE "
		+ "    pi.type = :type AND pi.product_id = :productId "
		+ "ORDER BY pi.id "
		+ "LIMIT :limit";
}
