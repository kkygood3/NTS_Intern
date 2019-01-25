/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.dao;

public class FileInfoDaoSqls {
	public static final String SELECT_FILEINFO_BY_ID = "SELECT id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date FROM file_info WHERE id = :id";
}
