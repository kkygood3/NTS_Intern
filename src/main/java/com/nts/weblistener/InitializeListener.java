/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.weblistener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author 임상현, life4lord93@nts-corp.com
 * tomcat 실행, 종료시 호출 
 */
@WebListener
public class InitializeListener implements ServletContextListener {

	/* 
	 * tomcat 실행시 jdbc Driver를 Memory에 load
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {}

}
