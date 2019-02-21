/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.config;

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

import com.nts.reservation.interceptor.AddCommentViewInterceptor;
import com.nts.reservation.interceptor.AuthInterceptor;
import com.nts.reservation.interceptor.PreventPathAttackInterceptor;
import com.nts.reservation.interceptor.RequestLogInterceptor;

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
		registry.addViewController("/bookingLogin").setViewName("bookingLoginPage");
		registry.addViewController("myReservation").setViewName("myReservationPage");
	}

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10485760); // 1024 * 1024 * 10
		return multipartResolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new RequestLogInterceptor());
		registry.addInterceptor(new PreventPathAttackInterceptor()).addPathPatterns("/api/download/**");

		registry.addInterceptor(new AuthInterceptor()).addPathPatterns(new String[] {
			"/myReservation", "/addComment/{reservationInfoId}"
		});
		registry.addInterceptor(addCommentViewInterceptor()).addPathPatterns("/addComment/{reservationInfoId}");
	}

	@Bean
	public AddCommentViewInterceptor addCommentViewInterceptor() {
		return new AddCommentViewInterceptor();
	}
}
