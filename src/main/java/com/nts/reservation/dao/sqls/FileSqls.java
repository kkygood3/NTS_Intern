/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sqls;

/**
* @author  : 이승수
*/
public class FileSqls {
	public static final String SELECT_FILE_BY_PRODUCT_ID = "SELECT "
		+ "file_name"
		+ ", save_file_name"
		+ ", content_type "
		+ "FROM file_info AS fi "
		+ "INNER JOIN product_image AS pi "
		+ "ON fi.id = pi.file_id "
		+ "WHERE pi.product_id = :productId "
		+ "AND pi.type = 'th'";
}
