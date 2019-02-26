/**
 * Copyright 2015 Naver Corp. All rights Reserved.
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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.nts.reservation.argumentresolver.PageDtoArgumentResolver;
import com.nts.reservation.interceptor.AuthInterceptor;
import com.nts.reservation.interceptor.LogInterceptor;

/**
 * Spring WebMVC 설정 클래스
 * @author jinwoo.bae
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.nts.reservation.controller")
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	// 1Year = 31556926 second
	private final int CACHE_PERIOD = 31556926;
	// 1024 * 1024 * 10
	private final int MAX_UPLOAD_SIZE = 10485760;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(CACHE_PERIOD);
		registry.addResourceHandler("/font/**").addResourceLocations("/font/").setCachePeriod(CACHE_PERIOD);
		registry.addResourceHandler("/img/**").setCachePeriod(CACHE_PERIOD);
		registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(CACHE_PERIOD);
	}

	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("main");
		registry.addViewController("/main").setViewName("main");
		registry.addViewController("/bookinglogin").setViewName("bookinglogin");
		registry.addViewController("/products/{prodcutId}/comment").setViewName("comment");
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
		registry.addInterceptor(new AuthInterceptor())
			.addPathPatterns("/api/reservations/**", "/myreservation/**")
			.excludePathPatterns("/api/reservations"); // 일정예약하기
		registry.addInterceptor(new LogInterceptor());
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new PageDtoArgumentResolver());
	}

	/**
	 * 멀티파트 요청 처리를 위한 리졸버 설정
	 */
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(MAX_UPLOAD_SIZE);
		return multipartResolver;
	}

}
