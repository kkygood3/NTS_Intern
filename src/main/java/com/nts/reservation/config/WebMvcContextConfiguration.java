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
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.nts.reservation.controller")
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {

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
		System.out.println("addViewControllers가 호출됩니다.");
		registry.addViewController("/").setViewName("main");
	}

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
}