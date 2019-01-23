/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.db;

public class SQL {
	public static final String ADD_TODO = "insert into todo(title, name, sequence) values(?,?,?)";
	public static final String GET_TODOS = "select id, name, date_format(regdate, \"%Y.%m.%d\") as regdate, sequence, title, type from todo";
	public static final String UPDATE_TODO = "update todo set type = ? where id = ?";
}
