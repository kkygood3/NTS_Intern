/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts;

import com.nts.jdbc.dao.TodoDao;

public class TodoDaoProvider {
	private static TodoDao todoDao;

	public static TodoDao getTodoDaoInstance() {
		if (todoDao == null) {
			todoDao = new TodoDao();
		}
		return todoDao;
	}
}
