package com.nts.reservation.config;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.nts.reservation.interceptor.LogInterceptor;
import com.nts.reservation.interceptor.UserEmailCheckInterceptor;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.nts.reservation.controller"})
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
			.setCachePeriod(31556926);
		registry.addResourceHandler("/img/**")
			.addResourceLocations("file:///C:/Users/USER/eclipse-workspace/reservation/resources/img/")
			.setCachePeriod(31556926);
		registry.addResourceHandler("/img_map/**")
			.addResourceLocations("file:///C:/Users/USER/eclipse-workspace/reservation/resources/img_map/")
			.setCachePeriod(31556926);
		registry.addResourceHandler("/img_uploaded/**")
			.addResourceLocations("file:///C:/Users/USER/eclipse-workspace/reservation/resources/img_uploaded/")
			.setCachePeriod(31556926);
	}

	// default servlet handler를 사용하게 합니다.
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/redirect").setViewName("redirect");
		registry.addViewController("/detail").setViewName("detail");
		registry.addViewController("/review").setViewName("review");
		registry.addViewController("/reserve").setViewName("reserve");
		registry.addViewController("/bookinglogin").setViewName("bookinglogin");
		registry.addViewController("/myreservation").setViewName("myreservation");
		registry.addViewController("/myreservation/reviewWrite").setViewName("reviewWrite");

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
		registry.addInterceptor(new UserEmailCheckInterceptor())
			.addPathPatterns("/myreservation/**");
		registry.addInterceptor(new LogInterceptor());
	}

	@Bean
	public MultipartResolver multipartResolver() {
		org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10485760); // 1024 * 1024 * 10
		return multipartResolver;
	}

}
