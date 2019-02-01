/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author 육성렬
 */
public class WebApplicationInitializer implements org.springframework.web.WebApplicationInitializer {

	private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		FilterRegistration charEncodingFilterReg = servletContext.addFilter("CharacterEncodingFilter",
			CharacterEncodingFilter.class);
		charEncodingFilterReg.setInitParameter("encoding", "UTF-8");
		charEncodingFilterReg.setInitParameter("forceEncoding", "true");
		charEncodingFilterReg.addMappingForUrlPatterns(null, true, "/*");
		registerDispatcherServlet(servletContext);
	}

	private void registerDispatcherServlet(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext dispatcherContext = createContext(WebMvcContextConfiguration.class);
		ServletRegistration.Dynamic dispatcher;
		dispatcher = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}

	private AnnotationConfigWebApplicationContext createContext(final Class<?>... annotatedClasses) {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(annotatedClasses);
		context.setConfigLocation("com.nts.reservation.config");
		return context;
	}

}
