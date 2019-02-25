/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
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

import com.nts.reservation.common.interceptor.LoginInterceptor;
import com.nts.reservation.common.interceptor.RequestLoggingInterceptor;

/**
 * @author 임상현, life4lord93@nts-corp.com
 * Spring Web MVC 설정 class
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.nts.reservation"})
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {

	private static final int YEAR_SECONDS = 31536000;

	/** 
	 * view가 아닌 resource file 경로에 대한 접근 설정
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(YEAR_SECONDS);
		registry.addResourceHandler("/file/**").addResourceLocations("file:/reservation/file/")
			.setCachePeriod(YEAR_SECONDS);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/** 
	 * welcome view 설정
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}

	/**
	 * view의 prefix, suffix 설정
	 */
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	/**
	 * multipart resolver 설정
	 */
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10485760);
		return multipartResolver;
	}

	/**
	 * interceptor 설정
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(new RequestLoggingInterceptor()).addPathPatterns("/**");
	}

}
