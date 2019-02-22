/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sqls;

/**
* @author  : 이승수
*/
public class FileSqls {
	public static final String SELECT_FILE_ID_BY_PRODUCT_ID = "SELECT "
		+ "file_id "
		+ "FROM product_image "
		+ "WHERE product_id = :productId "
		+ "AND type = 'th'";
	public static final String SELECT_FILE_ID_BY_DISPLAY_INFO_ID = "SELECT "
		+ "file_id "
		+ "FROM display_info_image "
		+ "WHERE display_info_id = :displayInfoId ";

	public static final String SELECT_FILE_BY_ID = "SELECT "
		+ "file_name"
		+ ", save_file_name"
		+ ", content_type "
		+ "FROM file_info "
		+ "WHERE id = :fileId ";

	public static final String INSERT_FILE_INFO = "INSERT INTO "
		+ "file_info"
		+ "(file_name"
		+ ", save_file_name"
		+ ", content_type"
		+ ", delete_flag"
		+ ", create_date"
		+ ", modify_date) "
		+ "VALUES"
		+ "(:fileName"
		+ ", :saveFileName"
		+ ", :contentType"
		+ ", 0"
		+ ", NOW()"
		+ ", NOW())";
}
