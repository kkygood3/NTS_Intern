/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.nts.interceptor.ReservationUserInterceptor;
import static com.nts.util.FileSizeUtil.*;

/**
 * @desc dispatcher Servlet 설정들
 * @author 전연빈
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.nts.controller", "com.nts.interceptor" , "com.nts.aspect"})
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {

	private static final int YEAR_SECONDS = 31536000;

	@Autowired
	private ReservationUserInterceptor reservationUserInterceptor;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/").setCachePeriod(YEAR_SECONDS);
		registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(YEAR_SECONDS);
		registry.addResourceHandler("/img_map/**").addResourceLocations("/img_map/").setCachePeriod(YEAR_SECONDS);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(reservationUserInterceptor)
				.addPathPatterns("/reservations/**")
				.addPathPatterns("/reviewWrite/**");
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
        multipartResolver.setMaxUploadSize(MEGE_BYTE * 10); // 1024 * 1024 * 10
        return multipartResolver;
    }
	
	
}
