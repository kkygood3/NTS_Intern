package com.nts.reservation.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.nts.reservation.common.interceptor.LogInterceptor;
import com.nts.reservation.common.interceptor.LoginInterceptor;

/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.nts.reservation"})
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	static final int PERIOD = 365 * 24 * 60 * 60;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/css/**").addResourceLocations("/resources/css/").setCachePeriod(PERIOD);
		registry.addResourceHandler("/resources/img/**").addResourceLocations("/resources/img/").setCachePeriod(PERIOD);
		registry.addResourceHandler("/resources/js/**").addResourceLocations("/resources/js/").setCachePeriod(PERIOD);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("mainpage");
	}

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO pattern 추가
		registry.addInterceptor(new LogInterceptor());
		registry.addInterceptor(new LoginInterceptor())
			.addPathPatterns("/myreservation")
			.addPathPatterns("/reservations/**");
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(1024 * 1024 * 10);
		return multipartResolver;
	}
}
