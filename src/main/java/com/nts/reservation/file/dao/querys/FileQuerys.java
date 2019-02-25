/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.file.dao.querys;

public class FileQuerys {

	public static final String INSERT_FILE_INFO = "insert into "
		+ "file_info values("
		+ "default"
		+ ", :fileName"
		+ ", :saveFileName"
		+ ", :contentType"
		+ ", 0"
		+ ", now()"
		+ ", now()"
		+ ")";
}
