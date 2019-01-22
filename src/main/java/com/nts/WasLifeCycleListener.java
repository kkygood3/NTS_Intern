/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.nts.jdbc.dao.TodoDao;

/**
 * @author 육성렬
 */
@WebListener
public class WasLifeCycleListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {
		TodoDao todoDao = TodoDao.getInstance();
		try {
			todoDao.connectDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
		TodoDao todoDao = TodoDao.getInstance();
		todoDao.disconnectDatabase();
	}
}