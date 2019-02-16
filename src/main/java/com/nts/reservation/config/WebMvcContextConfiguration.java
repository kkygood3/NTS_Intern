/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * MVC에서 Controller단을 관리.
 */
@Configuration
@EnableWebMvc
@ComponentScan({"com.nts.reservation.controller","com.nts.reservation.error"})
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	/**
	 * 31556926 seconds = 1 year
	 * @value 31556926
	 */
	private static final int DEFAULT_PERIOD = 31556926;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(DEFAULT_PERIOD);
		registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(DEFAULT_PERIOD);
		registry.addResourceHandler("/img_map/**").addResourceLocations("/img_map/").setCachePeriod(DEFAULT_PERIOD);
		registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(DEFAULT_PERIOD);
		registry.addResourceHandler("/font/**").addResourceLocations("/font/").setCachePeriod(DEFAULT_PERIOD);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
		registry.addViewController("error").setViewName("error/invalidAccess");
	}

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
}
