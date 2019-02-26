/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.nts.reservation.argumentresolver.ReviewWriteArgumentResolver;
import com.nts.reservation.interceptor.LogInterceptor;
import com.nts.reservation.interceptor.SessionInterceptor;
import com.nts.reservation.property.Properties;

/**
 * MVC에서 Controller단을 관리.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.nts.reservation.controller")
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	@Bean
	public DispatcherServlet dispatcherServlet() {
		DispatcherServlet dispatcherServlet = new DispatcherServlet();
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		return dispatcherServlet;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("main");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(Properties.DEFAULT_CACHE_PERIOD);
		registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(Properties.DEFAULT_CACHE_PERIOD);
		registry.addResourceHandler("/img_map/**").addResourceLocations("/img_map/").setCachePeriod(Properties.DEFAULT_CACHE_PERIOD);
		registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(Properties.DEFAULT_CACHE_PERIOD);
		registry.addResourceHandler("/font/**").addResourceLocations("/font/").setCachePeriod(Properties.DEFAULT_CACHE_PERIOD);
	}

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new ReviewWriteArgumentResolver());
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SessionInterceptor());
		registry.addInterceptor(new LogInterceptor());
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10485760);
		return multipartResolver;
	}
}
