/*
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
*
* @description : 톰캣 실행 시 Init 호출, 종료 시 destroy 호출
* @filename : ContextListener.java
* @package : com.nts
* @author : Seokhyeon Choi
* @method : void contextInitialized(ServletContextEvent sce))
* @method : void contextDestroyed(ServletContextEvent sce)
*/
@WebListener
public class ContextListener implements ServletContextListener {

	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {}

}
