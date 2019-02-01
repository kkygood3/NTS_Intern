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
 * @author 육성렬
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.nts.reservation.controller"})
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	static final int CACHE_PERIOD = 31556926;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/").setCachePeriod(CACHE_PERIOD);
		registry.addResourceHandler("/image/**").addResourceLocations("/resources/img/").setCachePeriod(CACHE_PERIOD);
		registry.addResourceHandler("/font/**").addResourceLocations("/resources/font/").setCachePeriod(CACHE_PERIOD);
		registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/").setCachePeriod(CACHE_PERIOD);
		registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(CACHE_PERIOD);
		registry.addResourceHandler("/img_map/**").addResourceLocations("/img_map/").setCachePeriod(CACHE_PERIOD);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("mainPage");
		registry.addViewController("/displayInfo/{id}").setViewName("detailPage");
		registry.addViewController("/displayInfo/review/{id}").setViewName("reviewPage");
	}

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
}
