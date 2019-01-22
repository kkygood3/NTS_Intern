import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.nts.TodoDaoProvider;
import com.nts.jdbc.dao.TodoDao;

/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

@WebListener
public class WasLifeCycleListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {}

	public void contextDestroyed(ServletContextEvent event) {
		TodoDao todoDao = TodoDaoProvider.getTodoDaoInstance();
		todoDao.disconnectDatabase();
	}
}