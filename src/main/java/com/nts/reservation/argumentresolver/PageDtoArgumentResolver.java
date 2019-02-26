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
 * PageDto용 ArgumentResolver
 * @author jinwoo.bae
 */
public class PageDtoArgumentResolver implements HandlerMethodArgumentResolver {

	/**
	 * 파라미터 타입이 PageDto.class 일경우에만 resolveArgument 메소드가 호출되어집니다.
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// TODO Auto-generated method stub
		return parameter.getParameterType() == PageDto.class;
	}

	/* 
	 * 페이징 처리용 start, limit값을 적절히 입력해줍니다.
	 * PageDefault 애노테이션이 파라미터 앞에 지정되어져있는 경우 이값을 Default로 지정해줍니다.
	 * WebRequest 파라미터에 start, limit값이 있는경우 이값을 넣어줍니다.
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
