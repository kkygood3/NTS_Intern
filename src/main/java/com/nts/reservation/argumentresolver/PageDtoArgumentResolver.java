/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.argumentresolver;

import static com.nts.reservation.constant.ParameterDefaultValue.*;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.nts.reservation.annotation.PageDefault;
import com.nts.reservation.dto.param.PageDto;

/**
 * 
 * @author jinwoo.bae
 */
public class PageDtoArgumentResolver implements HandlerMethodArgumentResolver {

	/* 
	 * 
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// TODO Auto-generated method stub
		return parameter.getParameterType() == PageDto.class;
	}

	/* 
	 * 
	 */
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		int start = Integer.parseInt(DEFAULT_START); 
		int limit = Integer.parseInt(DEFAULT_LIMIT);
		
		if (parameter.hasParameterAnnotation(PageDefault.class)) {
			PageDefault pageDefault = parameter.getParameterAnnotation(PageDefault.class);
			start = Integer.parseInt(pageDefault.start());
			limit = Integer.parseInt(pageDefault.limit());
		}

		if (webRequest.getParameter("start") != null) {
			start = Integer.parseInt(webRequest.getParameter("start"));
		}
		
		if (webRequest.getParameter("limit") != null) {
			limit = Integer.parseInt(webRequest.getParameter("limit"));
		}
		
		return new PageDto(start, limit);
	}

}
